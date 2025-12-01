package com.jarak.myapplicationjarak;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.jarak.myapplicationjarak.model.ConexionBd;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ConexionBd conexionBd;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        conexionBd = new ConexionBd(MainActivity.this);
        db = conexionBd.getWritableDatabase();
        Toast.makeText(this, "Bd creada", Toast.LENGTH_SHORT).show();



    }

    public void iniciar(View view) {
        Intent intent = new Intent(MainActivity.this, MenuPrincipalActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Iniciando menú...", Toast.LENGTH_SHORT).show();
    }

    public void irMenu(View view) {
        Intent intent = new Intent(MainActivity.this, MenuPrincipalActivity.class);
        startActivity(intent);
    }
}