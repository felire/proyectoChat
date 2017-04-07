package com.example.felipe.chatvuelveacompilar;

import android.os.AsyncTask;

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
        connectionClient.enviarMensaje(idReceptor, mensaje);
        System.out.println("enviarMsj mandado");
        return null;
    }
}
