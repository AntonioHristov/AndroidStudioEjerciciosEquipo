package com.loeches.yugioh;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loeches.yugioh.Vista.MenuPrincipalActivity;


public class tutorialActivity extends AppCompatActivity {
    private Button Regresar;
    private MediaPlayer backgroundPlayer;
    private MediaPlayer clickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tutorial);

        VideoView videoView = findViewById(R.id.videoView2);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fondochulo);
        Log.d("VideoView", "Video URI: " + videoUri);
        videoView.setVideoURI(videoUri);
        videoView.setOnPreparedListener(mediaPlayer -> {
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(1.0f, 1.0f); // Ajusta el volumen si es necesario
            videoView.start();
        });

        Regresar = findViewById(R.id.Regresar);
        Regresar.setOnClickListener(v -> {
            //clickSound.start();
            Intent intent = new Intent(tutorialActivity.this, MenuPrincipalActivity.class);
            startActivity(intent);
        });

        Regresar.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).start();
                    break;
                case MotionEvent.ACTION_UP:
                    v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start();
                    break;
            }
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (backgroundPlayer != null) {
            backgroundPlayer.release();
            backgroundPlayer = null;
        }
        if (clickSound != null) {
            clickSound.release();
            clickSound = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (backgroundPlayer == null) {
            backgroundPlayer = MediaPlayer.create(this, R.raw.interluc);
            backgroundPlayer.setLooping(true);
            backgroundPlayer.start();
        } else if (!backgroundPlayer.isPlaying()) {
            backgroundPlayer.start();
        }
    }
}