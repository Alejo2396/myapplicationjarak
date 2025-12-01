package com.jarak.myapplicationjarak;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // Botón "Aprender"
    public void aprender(View view){
        Intent intent = new Intent(Home.this, Aprender.class);
        startActivity(intent);
    }

    // Botón "Jugar"
    public void jugar(View view){
        Intent intent = new Intent(Home.this, MemoryGameActivity.class);
        startActivity(intent);
    }
    public void animales(View view){
        Intent MoverActivityAnimals = new Intent(Home.this, Animales.class);
        startActivity(MoverActivityAnimals);
    }

    public void numbers(View view){
        Intent MoverActivityNumbers = new Intent(Home.this, Numbers.class);
        startActivity(MoverActivityNumbers);
    }

    public void colors(View view){
        Intent MoverActivityColors = new Intent(Home.this, Colores.class);
        startActivity(MoverActivityColors);
    }

    public void fruits(View view){
        Intent MoverActivityFruits = new Intent(Home.this, Fruits.class);
        startActivity(MoverActivityFruits);
    }
}