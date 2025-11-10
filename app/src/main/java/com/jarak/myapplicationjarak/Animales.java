package com.jarak.myapplicationjarak;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Animales extends AppCompatActivity {

    MediaPlayer sonido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);
    }


    public void sonarKanguro(View view){
        sonido = MediaPlayer.create(Animales.this, R.raw.kangaroo);
        sonido.start();
    }

    public void sonarKoala(View view){
        sonido = MediaPlayer.create(Animales.this, R.raw.koala);
        sonido.start();
    }

    public void sonarMono(View view){
        sonido = MediaPlayer.create(Animales.this, R.raw.monkey);
        sonido.start();
    }

    public void sonarPanda(View view){
        sonido = MediaPlayer.create(Animales.this, R.raw.panda);
        sonido.start();
    }

    public void sonarSerpiente(View view){
        sonido = MediaPlayer.create(Animales.this, R.raw.snake);
        sonido.start();
    }

    public void sonarGato(View view){
        sonido = MediaPlayer.create(Animales.this, R.raw.cat);
        sonido.start();
    }

    public void sonarLeon(View view){
        sonido = MediaPlayer.create(Animales.this, R.raw.lion);
        sonido.start();
    }

    public void sonarElefante(View view){
        sonido = MediaPlayer.create(Animales.this, R.raw.elephant);
        sonido.start();
    }

    public void sonarPajaro(View view){
        sonido = MediaPlayer.create(Animales.this, R.raw.bird);
        sonido.start();
    }
}