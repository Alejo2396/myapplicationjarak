package com.jarak.myapplicationjarak;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGameActivity extends AppCompatActivity {

    private GridLayout grid;
    private TextView tvScore, tvErrors;
    private Button btnResolve, btnHome, btnPlay;
    private List<String> shuffled;
    private String[] emojis = {"🐶","🐶","🐱","🐱","🐷","🐷","🐸","🐸","🐵","🐵","🦊","🦊","🐼","🐼","🦁","🦁"};
    private Button first=null, second=null;
    private int score=0, errors=0, matches=0;
    private boolean busy=false;
    private Handler handler=new Handler();

    private MediaPlayer soundFlip, soundCorrect, soundWrong, soundWin, soundReset, soundStart, bgMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        grid = findViewById(R.id.grid);
        tvScore = findViewById(R.id.tvScore);
        tvErrors = findViewById(R.id.tvErrors);
        btnResolve = findViewById(R.id.btnResolve);
        btnHome = findViewById(R.id.btnHome);
        btnPlay = findViewById(R.id.btnPlay);

        // Sonidos
        soundFlip = MediaPlayer.create(this,R.raw.flip);
        soundCorrect = MediaPlayer.create(this,R.raw.correct);
        soundWrong = MediaPlayer.create(this,R.raw.wrong);
        soundWin = MediaPlayer.create(this,R.raw.win);
        soundReset = MediaPlayer.create(this,R.raw.reset);
        soundStart = MediaPlayer.create(this,R.raw.start);
        bgMusic = MediaPlayer.create(this,R.raw.background);
        bgMusic.setLooping(true); bgMusic.start();
        soundStart.start();

        setupGame();

        // 🔹 Botones
        btnPlay.setOnClickListener(v -> {
            setupGame();    // reinicia tablero
            startPreview(); // muestra preview 3 seg
        });

        btnResolve.setOnClickListener(v -> showFullBoardWithHighlights());

        btnHome.setOnClickListener(v -> {
            stopAllSounds();
            startActivity(new Intent(this,MenuPrincipalActivity.class));
            finish();
        });
    }

    private void setupGame(){
        score=errors=matches=0;
        first=second=null;
        busy=false;
        tvScore.setText("Aciertos: 0"); tvErrors.setText("Errores: 0");

        shuffled = new ArrayList<>();
        Collections.addAll(shuffled, emojis);
        Collections.shuffle(shuffled);

        grid.removeAllViews();
        for(int i=0;i<16;i++){
            Button btn = new Button(this);
            btn.setText(""); btn.setTextSize(24f); btn.setAllCaps(false); btn.setTag(i);
            btn.setBackgroundResource(android.R.drawable.btn_default);

            btn.setOnClickListener(v -> {
                if(busy || !btn.getText().toString().isEmpty()) return;
                reveal(btn, shuffled.get((int)btn.getTag()));
            });

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width=0; params.height=GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED,1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED,1f);
            params.setMargins(8,8,8,8);
            btn.setLayoutParams(params);

            grid.addView(btn);
        }
    }

    private void reveal(Button btn,String emoji){
        btn.setText(emoji); soundFlip.start();
        if(first==null){first=btn; return;}
        if(first==btn) return;
        second=btn; busy=true;
        handler.postDelayed(this::checkMatch,700);
    }

    private void checkMatch(){
        if(first==null || second==null){busy=false; return;}
        String a=first.getText().toString(), b=second.getText().toString();
        if(a.equals(b)){
            first.setEnabled(false); second.setEnabled(false); score++; matches++;
            tvScore.setText("Aciertos: "+score); soundCorrect.start();
            if(matches==8){soundWin.start(); Toast.makeText(this,"🎉 ¡Ganaste!",Toast.LENGTH_LONG).show(); bgMusic.pause();}
        }else{
            errors++; tvErrors.setText("Errores: "+errors); soundWrong.start();
            first.setText(""); second.setText("");
        }
        first=second=null; busy=false;
    }

    private void startPreview(){
        for(int i=0;i<grid.getChildCount();i++){
            Button btn=(Button)grid.getChildAt(i);
            btn.setText(shuffled.get((int)btn.getTag()));
        }
        handler.postDelayed(() -> {
            for(int i=0;i<grid.getChildCount();i++){
                Button btn=(Button)grid.getChildAt(i);
                if(btn.isEnabled()) btn.setText("");
            }
        },3000);
    }

    // 🔹 REINICIAR mostrando aciertos y errores
    private void showFullBoardWithHighlights(){
        for(int i=0;i<grid.getChildCount();i++){
            Button btn=(Button)grid.getChildAt(i);
            String emoji=shuffled.get((int)btn.getTag());
            if(!btn.isEnabled()){ // aciertos previos
                btn.setText(emoji); btn.setBackgroundColor(Color.GREEN);
            } else { // errores/no resueltos
                btn.setText(emoji); btn.setBackgroundColor(Color.RED);
            }
        }
        Toast.makeText(this,"🔍 Mostrando aciertos y errores",Toast.LENGTH_SHORT).show();
    }

    private void stopAllSounds(){
        if(bgMusic!=null && bgMusic.isPlaying()) bgMusic.stop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        stopAllSounds();
        release(soundFlip); release(soundCorrect); release(soundWrong); release(soundWin);
        release(soundReset); release(soundStart); release(bgMusic);
    }

    private void release(MediaPlayer mp){ if(mp!=null) mp.release(); }
}
