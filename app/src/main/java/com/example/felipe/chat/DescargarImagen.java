package com.example.felipe.chat;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by felipe on 23/08/16.
 */
public class DescargarImagen extends AsyncTask<String,Void,Bitmap>{

    LoginFacebook mainActivity;
    //ProgressDialog progressDialog;
    public DescargarImagen(LoginFacebook mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        Bitmap imagen = descargarImagen(url);
        return imagen;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        /*progressDialog = new ProgressDialog(mainActivity);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();*/
    }
   /* @Override
    protected void onProgressUpdate(Integer porcentajeProgreso){

    }*/
    @Override
    protected void onPostExecute(Bitmap bitmap){
        super.onPostExecute(bitmap);
        //mainActivity.setImage(bitmap);
        //progressDialog.dismiss();
    }

    private Bitmap descargarImagen(String imageHttpAddress){
        URL imageUrl = null;
        Bitmap imagen = null;
        try{
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            imagen = BitmapFactory.decodeStream(conn.getInputStream());

        }catch (IOException ex){
            System.out.println(ex.toString());
        }

        return imagen;
    }
}
