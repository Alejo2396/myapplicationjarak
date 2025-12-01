package com.jarak.myapplicationjarak;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Fruits extends AppCompatActivity {

    MediaPlayer sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruitas);
    }

    public void sonarFresa(View view){
        sonido = MediaPlayer.create(Fruits.this, R.raw.strawberry);
        sonido.start();
    }

    public void sonarCereza(View view){
        sonido = MediaPlayer.create(Fruits.this, R.raw.cherry);
        sonido.start();
    }

    public void sonarMango(View view){
        sonido = MediaPlayer.create(Fruits.this, R.raw.mang0);
        sonido.start();
    }

    public void sonarNaranja(View view){
        sonido = MediaPlayer.create(Fruits.this, R.raw.orange_fruta);
        sonido.start();
    }

    public void sonarSandia(View view){
        sonido = MediaPlayer.create(Fruits.this, R.raw.wstermelon);
        sonido.start();
    }
}