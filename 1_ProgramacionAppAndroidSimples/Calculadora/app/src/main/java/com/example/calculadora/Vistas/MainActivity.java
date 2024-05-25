package com.example.calculadora.Vistas;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculadora.Controladores.Calculadora;
import com.example.calculadora.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Calculadora calc = new Calculadora(findViewById(R.id.idTVSecundario),findViewById(R.id.idTVPrincipal),findViewById(R.id.idBCambioSigno),findViewById(R.id.idBCE),findViewById(R.id.idBC),findViewById(R.id.idBRetroceso),findViewById(R.id.idBDiv),findViewById(R.id.idBMul),findViewById(R.id.idBRes),findViewById(R.id.idBSum),findViewById(R.id.idBIgual),findViewById(R.id.idBPunto),findViewById(R.id.idB0),findViewById(R.id.idB1),findViewById(R.id.idB2),findViewById(R.id.idB3),findViewById(R.id.idB4),findViewById(R.id.idB5),findViewById(R.id.idB6),findViewById(R.id.idB7),findViewById(R.id.idB8),findViewById(R.id.idB9));
    }
}