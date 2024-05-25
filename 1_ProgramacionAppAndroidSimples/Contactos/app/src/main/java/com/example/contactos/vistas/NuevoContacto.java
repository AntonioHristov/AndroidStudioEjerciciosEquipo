package com.example.contactos.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contactos.R;
import com.example.contactos.controlador.Contactos;

public class NuevoContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo_contacto);

        EditText nombre=findViewById(R.id.etNombre);
        EditText telefono=findViewById(R.id.etTelefono);
        Button bGuardarContacto = findViewById(R.id.bGuardarContacto);
        Button bCancelarContacto = findViewById(R.id.bCancelarContacto);

        bGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contactos.addContacto(nombre.getText().toString(),telefono.getText().toString());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        bCancelarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


}