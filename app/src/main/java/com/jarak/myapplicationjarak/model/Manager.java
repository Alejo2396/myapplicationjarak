package com.jarak.myapplicationjarak.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Manager {

    private ConexionBd conexionBd;
    private SQLiteDatabase db;

    public Manager (Context context){

        conexionBd = new ConexionBd(context);
    }

    public void openBdWr(){
        db= conexionBd.getWritableDatabase();
    }
    public void openBdRd(){
        db= conexionBd.getReadableDatabase();
    }
    public void closeBD(){
        db.close();
    }

    public long insertData(Datos datos){
        openBdRd();
        ContentValues values = new ContentValues();
        values.put("nombre",datos.getNombre());
        values.put("apellido",datos.getApellido());
        values.put("correo",datos.getCorreo());
        long id = db.insert("DATOS", null, values);

        return id;
    }

}