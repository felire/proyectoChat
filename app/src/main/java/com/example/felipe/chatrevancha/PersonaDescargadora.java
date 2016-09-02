package com.example.felipe.chatrevancha;

import android.graphics.Bitmap;

/**
 * Created by felipe on 31/08/16.
 */
public interface PersonaDescargadora {
    public String getNombre() ;

    public void setNombre(String nombre);

    public String getId();
    public void setId(String id) ;
    public String getImagen();
    public void setImagen(String imagen);
}
