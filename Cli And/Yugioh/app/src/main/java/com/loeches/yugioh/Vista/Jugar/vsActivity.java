package com.loeches.yugioh.Vista.Jugar;

import static com.loeches.yugioh.R.id.botonso3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loeches.yugioh.R;
import com.loeches.yugioh.Vista.MenuPrincipalActivity;

public class vsActivity extends AppCompatActivity {

    private Button botonso;
    private Button botonso2;
    private Button botonso3;
    private MediaPlayer backgroundPlayer;
    private MediaPlayer clickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vs);

        VideoView videoView = findViewById(R.id.videoView3);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vesus);
        Log.d("VideoView", "Video URI: " + videoUri);
        videoView.setVideoURI(videoUri);
        videoView.setOnPreparedListener(mediaPlayer -> {
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(1.0f, 1.0f); // Ajusta el volumen si es necesario
            videoView.start();
        });
        botonso = findViewById(R.id.botonso);
        botonso2 = findViewById(R.id.botonso2);
        botonso3 = findViewById(R.id.botonso3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        botonso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clickSound.start();
                Intent intent = new Intent(vsActivity.this, JugandoActivity.class);
                startActivity(intent);

            }

        });



        botonso2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clickSound.start();
                Intent intent = new Intent(vsActivity.this, JugandoActivity.class);
                startActivity(intent);

            }
        });

        botonso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clickSound.start();
                Intent intent = new Intent(vsActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);


            }
        });


    }

}