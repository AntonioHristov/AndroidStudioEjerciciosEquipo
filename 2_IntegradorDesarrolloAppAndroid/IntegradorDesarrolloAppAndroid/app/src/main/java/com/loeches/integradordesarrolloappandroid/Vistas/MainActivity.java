package com.loeches.integradordesarrolloappandroid.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loeches.integradordesarrolloappandroid.Controladores.Controlador;
import com.loeches.integradordesarrolloappandroid.Controladores.Resultado;
import com.loeches.integradordesarrolloappandroid.Modelos.Modelo;
import com.loeches.integradordesarrolloappandroid.R;

public class MainActivity extends AppCompatActivity {
    private boolean isTextChanged = false;
    private ImageView ivUp,ivDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Controlador.InicializarValoresDefecto();

        ivUp = findViewById(R.id.ivUp);
        ivDown = findViewById(R.id.ivDown);


        EditText etNumber = findViewById(R.id.etNumber);
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isTextChanged=true;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        etNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && isTextChanged) {
                    Controlador.nuevoIntento(getApplicationContext(),Integer.parseInt(etNumber.getText().toString()), ivUp, ivDown);
                    // Oculta el teclado si está mostrando
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(findViewById(R.id.main).getWindowToken(), 0);
                    }

                    if(Modelo.getIntentosJugador()>=Modelo.getLimiteIntentos()||Modelo.getUltimoNumeroIntentadoPorJugador()==Modelo.getNumeroAleatorioGenerado()){
                        Intent intent = new Intent(getApplicationContext(), Resultado.class);
                        startActivity(intent);
                    }
                    isTextChanged = false;
                }
            }
        });

    }
}