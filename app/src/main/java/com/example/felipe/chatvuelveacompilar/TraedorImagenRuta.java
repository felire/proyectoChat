package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by felipe on 31/08/16.
 */
public class TraedorImagenRuta {

    public String guardarImagenMemoriaInterna(Bitmap bitmapImage, Context context, String id){
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/paqueteChat/app_data/ImagenesChat
        File directory = cw.getDir("ImagenesChat", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory, id+".jpg");

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(directory.getAbsolutePath()); //Chequeamos que devuelva bien la ruta
        return directory.getAbsolutePath();
    }

    public Bitmap cargarImagenDeMemoriaInterna(String path, String id)
    {
        Bitmap bitmap = null;
        try {
            File f=new File(path, id+".jpg");
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f));

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }


}
