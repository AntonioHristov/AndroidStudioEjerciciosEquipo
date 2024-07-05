package com.loeches.yugioh.Vista;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.loeches.yugioh.Controlador.Controlador;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Global.Global;

public class MainActivity extends AppCompatActivity {
    // INFORMACIÓN SOBRE GUARDAR Y CARGAR DATOS DE JSON, EN LA CLASE CONTROLADOR AL FINAL DEL MÉTODO nuevoTurno()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Global.set_activity(this);

        Global.restaurarValoresDefecto();
        Global.get_datosGuardablesJSON().cargar();




        startActivity(new Intent(this, MenuPrincipalActivity.class));
    }
}