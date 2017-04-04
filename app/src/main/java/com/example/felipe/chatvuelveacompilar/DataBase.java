package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
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
                                        "conversacion_id,"+
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
}
