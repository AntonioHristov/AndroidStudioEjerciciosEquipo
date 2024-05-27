package com.loeches.integradordesarrolloappandroid.Controladores;

import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loeches.integradordesarrolloappandroid.Modelos.Modelo;
import com.loeches.integradordesarrolloappandroid.R;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvResultado=findViewById(R.id.tvResultado);

        if(Modelo.getUltimoNumeroIntentadoPorJugador()>=Modelo.getNumeroAleatorioGenerado()){
            tvResultado.setText("GANASTE FELICIDADES, EL NÚMERO ES: "+ Modelo.getNumeroAleatorioGenerado()+" Y LO ADIVINASTE EN: "+Modelo.getIntentosJugador()+" INTENTOS");
        }else{
            tvResultado.setText("PERDISTE. EL NÚMERO ES: "+ Modelo.getNumeroAleatorioGenerado());
        }
    }
}