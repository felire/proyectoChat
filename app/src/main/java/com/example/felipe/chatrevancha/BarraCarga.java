package com.example.felipe.chatrevancha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import com.facebook.GraphRequestBatch;

/**
 * Created by felipe on 24/08/16.
 */
public class BarraCarga extends AsyncTask<Void,Void,Void> {
    ProgressDialog progressDialog;
    LoginFacebook loginFacebook;
    GraphRequestBatch batch;
    public BarraCarga(LoginFacebook login, GraphRequestBatch batch){
        this.loginFacebook = login;
        this.batch = batch;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        batch.addCallback(new GraphRequestBatch.Callback() {
            @Override
            public void onBatchCompleted(GraphRequestBatch batch) {
               progressDialog.dismiss();
                loginFacebook.iniciarMenu();
            }
        });
        batch.executeAsync();
        return null;
    }

    @Override
    public void onPreExecute(){
        progressDialog = new ProgressDialog(loginFacebook);
        progressDialog.setMessage("Descargando info...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    public void onPostExecute(Void berga){

    }
}
