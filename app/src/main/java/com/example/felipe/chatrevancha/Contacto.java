package com.example.felipe.chatrevancha;

import android.graphics.Bitmap;

/**
 * Created by felipe on 23/08/16.
 */
public class Contacto implements PersonaDescargadora{
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


}
