package com.example.felipe.chatvuelveacompilar;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by root on 07/04/17.
 */

public class ConnectionThreadEnviarMensaje extends AsyncTask<Void,Void,Void> {
    private ConnectionClientt connectionClient;
    String idReceptor;
    String mensaje;

    public ConnectionThreadEnviarMensaje(ConnectionClientt connectionClient_, String idR, String msj){
        connectionClient = connectionClient_;
        idReceptor = idR;
        mensaje = msj;
    }
    @Override
    protected Void doInBackground(Void... nada) {
        System.out.println("vamos a mandar el enviarMsj");
        try {
            connectionClient.enviarMensaje(idReceptor, mensaje);
        } catch (IOException e) {

        }
        System.out.println("enviarMsj mandado");
        return null;
    }
}
