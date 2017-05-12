package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 12/05/17.
 */

public class Mensaje {
    String emisor_id;
    String receptor_id;
    String mensaje;

    public static List<Mensaje> darTodosLosMensajesEntre(String id_usuario, String id_contacto, Context context){
        List<Mensaje> lista = new ArrayList<>();
        Mensaje mensaje;
        DataBase baseDatos = new DataBase(context, "BASE_DATOS_CHAT", null, 2);

        if(baseDatos.existeConversacion(id_usuario, id_contacto)){
            Cursor c = baseDatos.getReadableDatabase().rawQuery("SELECT mensaje, esDeUser FROM MensajeXConv WHERE conversacion_id="+baseDatos.darIdConversacion(id_usuario, id_contacto), null);
            c.moveToFirst();
            do {
                String mensajee= c.getString(0);
                Boolean esDeUser = c.getInt(1) > 0;
                mensaje=new Mensaje();
                mensaje.mensaje = mensajee;
                if(esDeUser){
                    mensaje.emisor_id = id_usuario;
                    mensaje.receptor_id = id_contacto;
                }
                else{
                    mensaje.emisor_id = id_contacto;
                    mensaje.receptor_id = id_usuario;
                }
                lista.add(mensaje);
            } while(c.moveToNext());
            return lista;
        }


    }
}
