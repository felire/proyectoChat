package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 12/05/17.
 */

public class ActualizadorChat implements Runnable {
    RecyclerView mensajes;
    Context context;
    String idUser;
    String idContacto;
    List<Mensaje> listaMensajes;
    public ActualizadorChat(RecyclerView mensa, Context context, String idUser, String idContacto){
        this.mensajes = mensa;
        this.context = context;
        this.idUser = idUser;
        this.idContacto = idContacto;
        this.listaMensajes = new ArrayList<>();
    }
    @Override
    public void run() {

    }
}
