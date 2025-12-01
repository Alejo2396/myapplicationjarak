package com.jarak.myapplicationjarak;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Numbers extends AppCompatActivity {

    MediaPlayer sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
    }

    public void sonarCero(View view){
        sonido = MediaPlayer.create(Numbers.this, R.raw.zero);
        sonido.start();
    }

    public void sonarUno(View view){
        sonido = MediaPlayer.create(Numbers.this, R.raw.one);
        sonido.start();
    }

    public void sonarDos(View view){
        sonido = MediaPlayer.create(Numbers.this, R.raw.two);
        sonido.start();
    }

    public void sonarTres(View view){
        sonido = MediaPlayer.create(Numbers.this, R.raw.trheee);
        sonido.start();
    }

    public void sonarCuatro(View view){
        sonido = MediaPlayer.create(Numbers.this, R.raw.four);
        sonido.start();
    }

    public void sonarCinco(View view){
        sonido = MediaPlayer.create(Numbers.this, R.raw.five);
        sonido.start();
    }

    public void sonarSeis(View view){
        sonido = MediaPlayer.create(Numbers.this, R.raw.six);
        sonido.start();
    }

    public void sonarSiete(View view){
        sonido = MediaPlayer.create(Numbers.this, R.raw.seven);
        sonido.start();
    }

    public void sonarOcho(View view){
        sonido = MediaPlayer.create(Numbers.this, R.raw.eight);
        sonido.start();
    }

    public void sonarNueve(View view){
        sonido = MediaPlayer.create(Numbers.this, R.raw.nine);
        sonido.start();
    }
}