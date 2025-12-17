package com.jarak.myapplicationjarak.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private ConexionBd conexionBd;
    private SQLiteDatabase db;

    public Manager(Context context) {
        conexionBd = new ConexionBd(context);
    }

    private void openBdWr() {
        db = conexionBd.getWritableDatabase();
    }

    private void openBdRd() {
        db = conexionBd.getReadableDatabase();
    }

    private void closeBD() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    public long insertData(Datos datos) {
        openBdWr();

        ContentValues values = new ContentValues();
        values.put("nombre", datos.getNombre());
        values.put("apellido", datos.getApellido());
        values.put("correo", datos.getCorreo());

        long id = db.insert(ConexionBd.TABLE_DATOS, null, values);
        closeBD();

        return id;
    }

    public List<String> listarDataTexto() {
        List<String> lista = new ArrayList<>();
        openBdRd();

        Cursor cursor = db.rawQuery(
                "SELECT nombre, apellido, correo FROM " + ConexionBd.TABLE_DATOS,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                lista.add(
                        cursor.getString(0) + " " +
                                cursor.getString(1) + " - " +
                                cursor.getString(2)
                );
            } while (cursor.moveToNext());
        }

        cursor.close();
        closeBD();

        return lista;
    }
}
