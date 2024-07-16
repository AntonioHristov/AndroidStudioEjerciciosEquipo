package com.loeches.yugioh.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loeches.yugioh.Controlador.Controlador;
import com.loeches.yugioh.Modelo.Global.Enums.ECodigosTCP;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class RegistroActivity extends AppCompatActivity {
    EditText etApodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        if(Global.get_datosGuardablesJSON().get_apodo()!=null){
            startActivity(new Intent(getApplicationContext(), MenuPrincipalActivity.class));
        }


        etApodo=findViewById(R.id.etEscribirApodo);

        findViewById(R.id.bRegistrarse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentarRegistrarApodo();
            }
        });

        findViewById(R.id.bLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentarIniciarSesion();
            }
        });

    }

    public void intentarRegistrarApodo(){
        if(Controlador.registrarApodoEnServidor(etApodo.getText().toString())){
            guardarApodoIrAMenuPrincipal();
        }
    }

    public void intentarIniciarSesion(){
        if(Controlador.existeApodoEnServidor(etApodo.getText().toString())){
            guardarApodoIrAMenuPrincipal();
        }
    }

    public void guardarApodoIrAMenuPrincipal(){
        Global.get_datosGuardablesJSON().set_apodo(etApodo.getText().toString());
        Global.get_datosGuardablesJSON().guardar();
        startActivity(new Intent(getApplicationContext(), MenuPrincipalActivity.class));
    }
}