package com.loeches.yugioh.Vista;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loeches.yugioh.Modelo.Global.Enums.ECodigosTCP;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SalaEspera extends AppCompatActivity {

    LinearLayout jugadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sala_espera);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        jugadores = findViewById(R.id.lJugadores);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Solicitarjugadores();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

    }

    public void Solicitarjugadores() {
        try {
            Socket socket = new Socket("10.0.2.2", 83);

            PrintWriter enviar = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader recibir = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            enviar.println(ECodigosTCP.EN_ESPERA);
            enviar.println(Global.get_datosGuardablesJSON1Dispositivo().get_apodoEnRed());
            String jugador = "";
            while (!(jugador = recibir.readLine()).equals("fin")) {
                TextView t = new TextView(this);
                t.setText(jugador);
                String finalJugador = jugador;
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Global.jugador2 = finalJugador;
                    }
                });


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        jugadores.removeAllViews();
                        jugadores.addView(t);
                    }
                });
            }

            enviar.close();
            recibir.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}