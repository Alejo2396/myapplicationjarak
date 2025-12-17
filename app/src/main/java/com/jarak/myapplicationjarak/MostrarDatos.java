package com.jarak.myapplicationjarak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jarak.myapplicationjarak.model.Manager;

import java.util.List;

public class MostrarDatos extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Manager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        listView = findViewById(R.id.listDatos);

        manager = new Manager(this);

        List<String> datos = manager.listarDataTexto();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                datos
        );

        listView.setAdapter(adapter);
    }

    // BOTÓN ¡EMPEZAR!
    public void iniciar(View view) {
        Intent intent = new Intent(MostrarDatos.this, MainActivity.class);

        // LIMPIA TODAS LAS PANTALLAS ANTERIORES
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();
    }
}