package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by root on 07/04/17.
 */

public class ConnectionThreadRecibirMensaje extends AsyncTask<Void,Void,Void> {
    private ConnectionClientt connectionClient;
    private Context context;

    public ConnectionThreadRecibirMensaje(ConnectionClientt connectionClient_, Context context){
        connectionClient = connectionClient_;
        this.context = context;
    }
    @Override
    protected Void doInBackground(Void... nada) {
        connectionClient.recibirMensaje(context);
        return null;
    }
}
