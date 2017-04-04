package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipe on 23/08/16.
 */
public class Contacto implements PersonaDescargadora,Serializable {
    private String nombre;
    private String id;
    private String imagen; //Es una ruta

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public static List<Contacto> darTodosContactos(Context context){
        List<Contacto> contactos = new ArrayList<>();
        Contacto contacto;
        DataBase baseDatos = new DataBase(context, "BASE_DATOS_CHAT", null, 2);
        //System.out.println("Empezando a leer..");
        SharedPreferences preferencia = context.getSharedPreferences("usuarioPreferencias", context.MODE_PRIVATE);
        Cursor c = baseDatos.getReadableDatabase().rawQuery("SELECT contacto_id,contacto_nombre,contacto_uri_foto FROM Contactos WHERE user_id = "+ preferencia.getString("id","defecto"), null);
        if(c.moveToFirst()){
            do {
                contacto = new Contacto();
                contacto.setNombre(c.getString(1));
                contacto.setId(c.getString(0));
                contacto.setImagen(c.getString(2));
                //System.out.println("Nombre del contacto: " + contacto.getNombre());
                contactos.add(contacto);
            }while(c.moveToNext());
        }
        return contactos;
    }

}
