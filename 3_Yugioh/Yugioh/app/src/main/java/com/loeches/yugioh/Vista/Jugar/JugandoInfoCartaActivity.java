package com.loeches.yugioh.Vista.Jugar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.R;
import com.loeches.yugioh.Vista.Jugar.JugandoActivity;

public class JugandoInfoCartaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jugando_info_carta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try{
            Intent intent=getIntent();

            ACarta carta= Global.getBy(EIdHorizontalVista.valueOf(intent.getStringExtra("idHorizontal"))).get_cartasVista().get(intent.getIntExtra("pos",-1)).get_carta();
            ImageView imageView=findViewById(R.id.ivImagenJugandoInfoCarta);
            TextView descripcion=findViewById(R.id.tvDescripcionJugandoInfoCarta);

            imageView.setImageResource(carta.get_imagen());
            descripcion.setText(carta.toString());

            findViewById(R.id.bSeguirJugandoInfoCarta).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), JugandoActivity.class));
                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage().toString());
        }
    }
}