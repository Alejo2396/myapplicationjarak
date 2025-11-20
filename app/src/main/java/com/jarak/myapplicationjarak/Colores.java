package com.jarak.myapplicationjarak;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Colores extends AppCompatActivity {

    MediaPlayer sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colores);
    }


    public void sonarBlue(View view){
        sonido = MediaPlayer.create(Colores.this, R.raw.blue);
        sonido.start();
    }

    public void sonarGreen(View view){
        sonido = MediaPlayer.create(Colores.this, R.raw.green);
        sonido.start();
    }

    public void sonarOrange(View view){
        sonido = MediaPlayer.create(Colores.this, R.raw.orange);
        sonido.start();
    }

    public void sonarRed(View view){
        sonido = MediaPlayer.create(Colores.this, R.raw.red);
        sonido.start();
    }

    public void sonarYellow(View view){
        sonido = MediaPlayer.create(Colores.this, R.raw.yellow);
        sonido.start();
    }
}