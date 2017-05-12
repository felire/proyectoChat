package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.facebook.login.LoginManager;

import java.io.IOException;

/**
 * Created by root on 04/04/17.
 */

public class ConnectionThread extends AsyncTask<Void,Void,Void> {
    private ConnectionClientt connectionClient;
    public ConnectionThread(ConnectionClientt connectionClient_){
        connectionClient = connectionClient_;
    }
    @Override
    protected Void doInBackground(Void... nada){
        try {
            connectionClient.conectar();
        } catch (IOException e) {
            LoginManager.getInstance().logOut();
        }
        return null;
    }
}
