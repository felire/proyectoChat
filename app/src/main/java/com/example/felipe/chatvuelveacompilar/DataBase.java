package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by felipe on 31/08/16.
 */
public class DataBase extends SQLiteOpenHelper {

    private String tablaContactos = "CREATE TABLE Contactos("+
                            "contacto_id VARCHAR(200),"+
                            "user_id VARCHAR(200),"+
                            "contacto_nombre VARCHAR(30) NOT NULL,"+
                            "contacto_uri_foto VARCHAR(100) NOT NULL,"+
                            "PRIMARY KEY (contacto_id, user_id));";

    private String tablaConversacion = "CREATE TABLE Conversacion("+
                                        "conversacion_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                        "user_id VARCHAR(200),"+
                                        "contacto_id VARCHAR(200));";//No olvidemos que es del lado del telefono esto!

    private String tablaMensajexConv = "CREATE TABLE MensajeXConv("+
                                        "mensaje_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                        "conversacion_id INTEGER,"+
                                        "fecha DATETIME,"+
                                        "esDeUser BIT,"+
                                        "mensaje VARCHAR(400),"+
                                        "FOREIGN KEY(conversacion_id) REFERENCES Conversacion(conversacion_id));";
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(this.tablaContactos);
        sqLiteDatabase.execSQL(this.tablaConversacion);
        sqLiteDatabase.execSQL(this.tablaMensajexConv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Contactos");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Conversacion");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MensajeXConv");
        sqLiteDatabase.execSQL(this.tablaContactos);
        sqLiteDatabase.execSQL(this.tablaConversacion);
        sqLiteDatabase.execSQL(this.tablaMensajexConv);
    }

    public Boolean existeConversacion(String usuario, String emisorId){
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT 1 FROM Conversacion WHERE user_id="+usuario+" AND contacto_id="+emisorId,null);
        return cursor.moveToFirst();
    }
    public int darIdConversacion(String usuario, String emisorId){
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT conversacion_id FROM Conversacion WHERE user_id="+usuario+" AND contacto_id="+emisorId,null);
        int converId = -1;
        if(cursor.moveToFirst()){
            converId = cursor.getInt(0);
        }
        return converId;
    }
    //public void agregarMensaje(String usuario, )
}
