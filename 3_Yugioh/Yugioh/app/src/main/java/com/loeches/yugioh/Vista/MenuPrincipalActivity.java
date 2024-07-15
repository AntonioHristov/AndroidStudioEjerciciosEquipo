package com.loeches.yugioh.Vista;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loeches.yugioh.R;
import com.loeches.yugioh.Vista.Configurar.MenuConfigurar;
import com.loeches.yugioh.Vista.Jugar.vsActivity;
import com.loeches.yugioh.tutorialActivity;

public class MenuPrincipalActivity extends AppCompatActivity {
    private MediaPlayer backgroundPlayer;
//    public MediaPlayer clickSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            VideoView videoView = findViewById(R.id.videoView);
            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.universo);
            videoView.setVideoURI(videoUri);
            Log.d("VideoView", "Video URI: " + videoUri);
            videoView.setOnPreparedListener(mediaPlayer -> {
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(100, 100); // Ajusta el volumen si es necesario
                float videoRatio = (float) mediaPlayer.getVideoWidth() / mediaPlayer.getVideoHeight();
                float screenRatio = (float) videoView.getWidth() / videoView.getHeight();
                float scaleX = videoRatio / screenRatio;

                if (scaleX >= 1f) {
                    videoView.setScaleX(scaleX);
                } else {
                    videoView.setScaleY(1f / scaleX);
                }

                videoView.start();
            });



            return insets;
        });

        final MediaPlayer clickSound = MediaPlayer.create(this, R.raw.espadajedi);

        TextView marqueeText = findViewById(R.id.marqueeText);
        marqueeText.setSelected(true);

        Button inicioD = findViewById(R.id.incidoD);
        Button tutorial = findViewById(R.id.turotial);
        Button CONFIGURAR = findViewById(R.id.CONFIGURAR);
        Button SALIR = findViewById(R.id.SALIR);



        inicioD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                Intent intent = new Intent(MenuPrincipalActivity.this, vsActivity.class);
                startActivity(intent);

            }

        });
        inicioD.setOnTouchListener((v, event) -> {
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

        tutorial.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clickSound.start();
                Intent intent = new Intent(MenuPrincipalActivity.this, tutorialActivity.class);
                startActivity(intent);

            }
        });
        tutorial.setOnTouchListener((v, event) -> {
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
        CONFIGURAR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clickSound.start();
                Intent intent = new Intent(MenuPrincipalActivity.this, MenuConfigurar.class);
                startActivity(intent);

            }
        });
        CONFIGURAR.setOnTouchListener((v, event) -> {
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

        SALIR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (backgroundPlayer != null) {
            backgroundPlayer.release();
            backgroundPlayer = null;
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (backgroundPlayer == null) {
            backgroundPlayer = MediaPlayer.create(this, R.raw.yugi);
            backgroundPlayer.setLooping(true);
            backgroundPlayer.start();
        } else if (!backgroundPlayer.isPlaying()) {
            backgroundPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (backgroundPlayer != null && backgroundPlayer.isPlaying()) {
            backgroundPlayer.pause();
        }
    }


}