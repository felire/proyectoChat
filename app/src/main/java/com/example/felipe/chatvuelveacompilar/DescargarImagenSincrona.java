package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by felipe on 21/12/16.
 */
public class DescargarImagenSincrona {
    PersonaDescargadora contacto;
    TraedorImagenRuta traductor;
    Context context;
    String path;

    public DescargarImagenSincrona(PersonaDescargadora contacto, Context contextoEjecucion, String pathImagen){
        this.contacto = contacto;
        traductor = new TraedorImagenRuta();
        this.context = contextoEjecucion;
        this.path = pathImagen;
    }

    private Bitmap descargarImagen(String imageHttpAddress){
        URL imageUrl = null;
        Bitmap imagen = null;
        try{
            imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            imagen = BitmapFactory.decodeStream(conn.getInputStream());

        }catch (IOException ex){
            System.out.println(ex.toString());
        }

        return imagen;
    }

    public void descargar(String direccion){
        String hola = traductor.guardarImagenMemoriaInterna(this.descargarImagen(direccion),context, path);
        contacto.setImagen(hola);
    }
}
