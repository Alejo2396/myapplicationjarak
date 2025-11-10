package com.jarak.myapplicationjarak;

import android.content.Intent;
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
    private Button btnReset, btnHome;
    private Handler handler = new Handler();

    private Button firstButton = null;
    private Button secondButton = null;
    private boolean busy = false;

    private int score = 0;
    private int errors = 0;
    private int matchesFound = 0;

    // 🎵 Sonidos
    private MediaPlayer soundFlip, soundCorrect, soundWrong, soundWin;
    private MediaPlayer soundReset, soundStart, backgroundMusic;

    private String[] emojis = {
            "🐶", "🐶", "🐱", "🐱", "🐷", "🐷", "🐸", "🐸",
            "🐵", "🐵", "🦊", "🦊", "🐼", "🐼", "🦁", "🦁"
    };
    private List<String> shuffled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        grid = findViewById(R.id.grid);
        tvScore = findViewById(R.id.tvScore);
        tvErrors = findViewById(R.id.tvErrors);
        btnReset = findViewById(R.id.btnReset);
        btnHome = findViewById(R.id.btnHome);

        // 🎧 Inicializar sonidos
        soundFlip = MediaPlayer.create(this, R.raw.flip);
        soundCorrect = MediaPlayer.create(this, R.raw.correct);
        soundWrong = MediaPlayer.create(this, R.raw.wrong);
        soundWin = MediaPlayer.create(this, R.raw.win);
        soundReset = MediaPlayer.create(this, R.raw.reset);
        soundStart = MediaPlayer.create(this, R.raw.start);
        backgroundMusic = MediaPlayer.create(this, R.raw.background);

        // 🔁 Música de fondo en bucle
        backgroundMusic.setLooping(true);
        backgroundMusic.start();

        // 🎬 Sonido de inicio del juego
        soundStart.start();

        setupGame();

        btnReset.setOnClickListener(v -> {
            soundReset.start();
            setupGame();
        });

        btnHome.setOnClickListener(v -> {
            stopAllSounds();
            Intent intent = new Intent(MemoryGameActivity.this, MenuPrincipalActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void setupGame() {
        score = 0;
        errors = 0;
        matchesFound = 0;
        firstButton = null;
        secondButton = null;
        busy = false;
        tvScore.setText("Aciertos: 0");
        tvErrors.setText("Errores: 0");

        shuffled = new ArrayList<>();
        Collections.addAll(shuffled, emojis);
        Collections.shuffle(shuffled);

        grid.removeAllViews();

        for (int i = 0; i < 16; i++) {
            final Button btn = new Button(this);
            btn.setText("");
            btn.setTextSize(24f);
            btn.setAllCaps(false);
            btn.setTag(i);
            btn.setBackgroundResource(android.R.drawable.btn_default);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(8, 8, 8, 8);
            btn.setLayoutParams(params);

            btn.setOnClickListener(v -> {
                if (busy) return;
                if (btn.getText().length() > 0) return;
                int index = (int) btn.getTag();
                revealButton(btn, shuffled.get(index));
            });

            grid.addView(btn);
        }
    }

    private void revealButton(final Button btn, String emoji) {
        btn.setText(emoji);
        soundFlip.start();

        if (firstButton == null) {
            firstButton = btn;
            return;
        }

        if (firstButton == btn) return;

        secondButton = btn;
        busy = true;

        handler.postDelayed(this::checkForMatch, 700);
    }

    private void checkForMatch() {
        if (firstButton == null || secondButton == null) {
            busy = false;
            return;
        }

        String a = firstButton.getText().toString();
        String b = secondButton.getText().toString();

        if (a.equals(b)) {
            firstButton.setEnabled(false);
            secondButton.setEnabled(false);
            score++;
            matchesFound++;
            tvScore.setText("Aciertos: " + score);
            soundCorrect.start();

            if (matchesFound == 8) {
                soundWin.start();
                Toast.makeText(this, "🎉 ¡Ganaste!", Toast.LENGTH_LONG).show();
                backgroundMusic.pause();
            }
        } else {
            errors++;
            tvErrors.setText("Errores: " + errors);
            soundWrong.start();
            firstButton.setText("");
            secondButton.setText("");
        }

        firstButton = null;
        secondButton = null;
        busy = false;
    }

    private void stopAllSounds() {
        if (backgroundMusic != null && backgroundMusic.isPlaying()) backgroundMusic.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAllSounds();
        releaseMedia(soundFlip);
        releaseMedia(soundCorrect);
        releaseMedia(soundWrong);
        releaseMedia(soundWin);
        releaseMedia(soundReset);
        releaseMedia(soundStart);
        releaseMedia(backgroundMusic);
    }

    private void releaseMedia(MediaPlayer mp) {
        if (mp != null) {
            mp.release();
        }
    }
}
