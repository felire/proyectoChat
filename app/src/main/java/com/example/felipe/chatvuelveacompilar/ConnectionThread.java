package com.example.felipe.chatvuelveacompilar;

import android.os.AsyncTask;

/**
 * Created by root on 04/04/17.
 */

public class ConnectionThread extends AsyncTask<Void,Void,Void> {
    private ConnectionClient connectionClient;

    public ConnectionThread(ConnectionClient connectionClient_){
        connectionClient = connectionClient_;
    }
    @Override
    protected Void doInBackground(Void... nada) {
        connectionClient.conectar();
        return null;
    }
}
