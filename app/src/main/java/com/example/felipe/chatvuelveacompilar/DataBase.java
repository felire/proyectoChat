package com.example.felipe.chatvuelveacompilar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by felipe on 31/08/16.
 */
public class DataBase extends SQLiteOpenHelper {

    private String tablaContactos = "CREATE TABLE Contactos("+
                            "contacto_id VARCHAR(200) PRIMARY KEY,"+
                            "contacto_nombre VARCHAR(30) NOT NULL,"+
                            "contacto_uri_foto VARCHAR(100) NOT NULL);";
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(this.tablaContactos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Contactos");

        sqLiteDatabase.execSQL(this.tablaContactos);
    }
}
