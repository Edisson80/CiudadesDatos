package com.df.ciudaesdatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDbCiudades extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static String DATABASE_NAME = "ciudades.db";
    public MyDbCiudades(@Nullable Context context) {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table ciudad(id INTEGER PRIMARY KEY,nombre TEXT,poblacion INTEGER,latitud REAL,longitud REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists ciudad");
        onCreate(db);
    }
    public void insertarCiudad(SQLiteDatabase db,Ciudad ciudad){
        ContentValues values = new ContentValues();

        values.put("id",ciudad.getId());
        values.put("nombre",ciudad.getNombre());
        values.put("poblacion",ciudad.getPoblacion());
        values.put("latitud",ciudad.getLatitud());
        values.put("longitud",ciudad.getLongitud());
        db.insert("ciudad",null,values);
    }
    public ArrayList<Ciudad> selecinarCiudad (SQLiteDatabase db){
        ArrayList<Ciudad>Listaciudades = new ArrayList<Ciudad>();
        Cursor filas = db.rawQuery("Select * from ciudad",null );
        if(filas.moveToFirst()){
            do{
                Ciudad ciudad = new Ciudad(filas.getInt(0),filas.getString(1),filas.getInt(2),
                        filas.getDouble(3),filas.getDouble(4));
                Listaciudades.add(ciudad);

            }while (filas.moveToNext());
        }
        return Listaciudades;
    }
}
