package com.example.felipe.chatvuelveacompilar;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.facebook.GraphRequestBatch;

/**
 * Created by felipe on 21/12/16.
 */
public class EjecutadorBatch extends AsyncTask<Void,Void,Void> {
    GraphRequestBatch batch;
    public EjecutadorBatch(GraphRequestBatch batch){
        this.batch = batch;
    }


    @Override
    protected Void doInBackground(Void... nada) {
        batch.executeAndWait();
        return null;
    }


}
