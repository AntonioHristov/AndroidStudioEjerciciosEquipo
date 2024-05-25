package com.example.contactos.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contactos.R;
import com.example.contactos.controlador.Contactos;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayoutVertical = findViewById(R.id.idLlVerticalContactos);
        if (Contactos.contactos.isEmpty()) {
            Contactos.InicializarContactos();
        }
        MostrarContactos(linearLayoutVertical);
        Button bNuevoContacto = findViewById(R.id.bNuevoContacto);
        bNuevoContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NuevoContacto.class);
                startActivity(intent);
            }
        });
    }

    public LinearLayout CrearCopiaConAtributosDe(LinearLayout original){
        LinearLayout copia = new LinearLayout(getApplicationContext());
        copia.setLayoutParams(original.getLayoutParams());
        return copia;
    }

    public TextView CrearCopiaConAtributosDe(TextView original){
        TextView copia = new TextView(getApplicationContext());
        copia.setLayoutParams(original.getLayoutParams());
        copia.setBackground(original.getBackground());
        copia.setText(original.getText());
        return copia;
    }

    public void MostrarContactos(LinearLayout linearLayoutVertical) {
        LinearLayout llHorizontalOriginal = findViewById(R.id.idLlHorizontalContactos);
        TextView tvLetraContactoOriginal = findViewById(R.id.idTvLetraContacto);
        TextView tvNombreContactoOriginal = findViewById(R.id.idTvNombreContacto);
        TextView tvTelefonoContactoOriginal = findViewById(R.id.idTvTelefonoContacto);

        for (int i = 0; i < Contactos.contactos.size(); i++) {
            tvLetraContactoOriginal.setText(Contactos.getNombre(i).charAt(0) + "");
            tvNombreContactoOriginal.setText(Contactos.getNombre(i));
            tvTelefonoContactoOriginal.setText(Contactos.getTelefono(i));

            LinearLayout llHorizontal = CrearCopiaConAtributosDe(llHorizontalOriginal);

            llHorizontal.addView(CrearCopiaConAtributosDe(tvLetraContactoOriginal));
            llHorizontal.addView(CrearCopiaConAtributosDe(tvNombreContactoOriginal));
            llHorizontal.addView(CrearCopiaConAtributosDe(tvTelefonoContactoOriginal));

            linearLayoutVertical.addView(llHorizontal);
        }
    }
}