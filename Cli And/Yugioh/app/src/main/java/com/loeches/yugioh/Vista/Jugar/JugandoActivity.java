package com.loeches.yugioh.Vista.Jugar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loeches.yugioh.Controlador.Controlador;
import com.loeches.yugioh.Controlador.Utilidades;
import com.loeches.yugioh.Modelo.Global.Enums.ECodigosTCP;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.InterfazVista.HorizontalVista;
import com.loeches.yugioh.R;
import com.loeches.yugioh.Vista.MenuPrincipalActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class JugandoActivity extends AppCompatActivity {

    private MediaPlayer backgroundPlayer;
    public MediaPlayer attackPlayer;

    private Socket socket;
    public static ObjectOutputStream enviar;
    public static ObjectInputStream recibir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // INFORMACIÓN SOBRE GUARDAR Y CARGAR DATOS DE JSON, EN LA CLASE CONTROLADOR AL FINAL DEL MÉTODO nuevoTurno()
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jugando);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Global.set_activity(this);

/*
        for (ACarta carta:Global.get_datosGuardablesJSON().get_cartas()) {
            System.out.println(carta.get_idHorizontalVista().toString());
            System.out.println(carta.get_nombre());
        }*/


        //System.out.println(Global.get_datosGuardables());
        if (Global.is_empezarPartidaNueva()) {
            Global.set_empezarPartidaNueva(false);
            Controlador.NuevaPartida();
            Controlador.nuevoTurno();
            return;
        }

        if (Controlador.partidaTerminada()) {
            mostrarGanador();
            return;
        }

        try {
            if (Global.get_datosGuardablesJSON1Dispositivo().hayDatos()) {
                Controlador.actualizarHorizontalesConCartas();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }

        actualizarVista();


        IniciarJuego();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (backgroundPlayer != null) {
            backgroundPlayer.release();
            backgroundPlayer = null;
        }
        if (attackPlayer != null) {
            attackPlayer.release();
            attackPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (backgroundPlayer == null) {
            backgroundPlayer = MediaPlayer.create(this, Global.get_musicaFondoJugando());
            backgroundPlayer.setLooping(true);
            backgroundPlayer.start();
        } else if (!backgroundPlayer.isPlaying()) {
            backgroundPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (backgroundPlayer != null && backgroundPlayer.isPlaying()) {
            backgroundPlayer.pause();
        }
    }


    /* COPIA SEGURIDAD
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (backgroundPlayer != null) {
            backgroundPlayer.release();
            backgroundPlayer = null;
        }
        if (attackPlayer != null) {
            attackPlayer.release();
            attackPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (backgroundPlayer == null) {
            backgroundPlayer = MediaPlayer.create(this, R.raw.yugiho);
            backgroundPlayer.setLooping(true);
            backgroundPlayer.start();
        } else if (!backgroundPlayer.isPlaying()) {
            backgroundPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (backgroundPlayer != null && backgroundPlayer.isPlaying()) {
            backgroundPlayer.pause();
        }
    }*/

    public static void vaciar() {
        Global.get_linearMain().removeAllViews();

        for (HorizontalVista hv : Global.get_horizontalesVista()) {
            hv.get_llHorizontal().removeAllViews();
        }
    }

    public static void actualizarVista() {
        vaciar();
        if (Global.is_turnoJugador1()) {
            // 1 PORQUE NO MOSTRAMOS LAS CARTAS DE LA MANO DEL RIVAL
            for (int i = 1; i < Global.get_horizontalesVista().size(); i++) {
                if (i == 3) {
                    mostrarDatosJugadoresCentro();
                }
                Global.get_horizontalesVista().get(i).EscribirCodigoXML(!Global.get_horizontalesVista().get(i).esSuTurno());
            }
        } else {
            for (int i = Global.get_horizontalesVista().size() - 2; i > -1; i--) {
                if (i == 2) {
                    mostrarDatosJugadoresCentro();
                }
                Global.get_horizontalesVista().get(i).EscribirCodigoXML(!Global.get_horizontalesVista().get(i).esSuTurno());
            }
        }
    }

    public static void mostrarGanador() {
        //Global.get_datosGuardablesJSON().guardarSiHayDatosGuardados();
        JugandoActivity.vaciar();
        Context context = Global.get_context();
        LinearLayout main = Global.get_linearMain();

        TextView textView = new TextView(context);
        if (Global.get_jugadores().get(0).get_vida() == 0 && Global.get_jugadores().get(1).get_vida() == 0) {
            textView.setText("EMPATE, NADIE GANÓ");
        } else if (Global.get_jugadores().get(1).get_vida() == 0) {
            textView.setText("GANÓ EL JUGADOR 1");
        } else if (Global.get_jugadores().get(0).get_vida() == 0) {
            textView.setText("GANÓ EL JUGADOR 2");
        } else {
            textView.setText("NADIE PERDIÓ, WTF ESTO NO DEBERÍA OCURRIR...");
        }
        //textView.setTextSize(50); // Tamaño del texto en "sp" (Scale-independent Pixels)
        textView.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD); // Establece el texto en negrita
        /*
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));*/
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0, 1
        ));

        main.addView(textView);


        Button bNuevaPartida = new Button(context);
        bNuevaPartida.setText("Nueva partida");
        //bNuevaPartida.setTextSize(30);
        bNuevaPartida.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        bNuevaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.NuevaPartida();
                Controlador.nuevoTurno();
            }
        });

        LinearLayout.LayoutParams paramsBNuevaPartida = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0, 1
        );
        paramsBNuevaPartida.gravity = Gravity.CENTER_HORIZONTAL;

        paramsBNuevaPartida.setMargins(0, Utilidades.dpToPx(10), 0, Utilidades.dpToPx(10));

        bNuevaPartida.setLayoutParams(paramsBNuevaPartida);

        Button bVolverMenuPrincipal = new Button(context);
        bVolverMenuPrincipal.setText("Menú Principal");
        //bNuevaPartida.setTextSize(30);
        bVolverMenuPrincipal.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        bVolverMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.get_activity().startActivity(new Intent(Global.get_context(), MenuPrincipalActivity.class));
            }
        });

        LinearLayout.LayoutParams paramsBMenuPrincipal = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0, 1
        );
        paramsBMenuPrincipal.gravity = Gravity.CENTER_HORIZONTAL;

        paramsBMenuPrincipal.setMargins(0, Utilidades.dpToPx(10), 0, Utilidades.dpToPx(10));

        bVolverMenuPrincipal.setLayoutParams(paramsBMenuPrincipal);


        main.addView(bNuevaPartida);
        main.addView(bVolverMenuPrincipal);
    }

    public static LinearLayout crearLinearVerticalJugadoresCentroVista() {
        LinearLayout llHorizontal = new LinearLayout(Global.get_context());
        LinearLayout.LayoutParams llHParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        llHorizontal.setOrientation(LinearLayout.VERTICAL);
        llHorizontal.setGravity(Gravity.CENTER);
        llHorizontal.setLayoutParams(llHParams);
        return llHorizontal;
    }

    public static void escribirXmlDivider(ViewGroup contenedor) {
        Context context = Global.get_context();
        View dividerView = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics())
        );
        int marginVertical = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics());
        params.setMargins(0, marginVertical, 0, marginVertical);
        dividerView.setLayoutParams(params);
        dividerView.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
        dividerView.setVisibility(View.VISIBLE);
        contenedor.addView(dividerView);
    }

    public static void mostrarDatosJugadoresCentro() {
        LinearLayout llHorizontal = crearLinearVerticalJugadoresCentroVista();
        Global.get_linearMain().addView(llHorizontal);
        if (Global.is_turnoJugador1()) {
            escribirTextViewJugador(Global.get_jugadores().get(1), llHorizontal);
            escribirXmlDivider(llHorizontal);
            escribirTextViewJugador(Global.get_jugadores().get(0), llHorizontal);
            escribirBotonVolverMenu(llHorizontal);
        } else {
            escribirTextViewJugador(Global.get_jugadores().get(0), llHorizontal);
            escribirXmlDivider(llHorizontal);
            escribirTextViewJugador(Global.get_jugadores().get(1), llHorizontal);
            escribirBotonVolverMenu(llHorizontal);
        }
    }

    public static void escribirTextViewJugador(Jugador jugador, ViewGroup contenedor) {
        TextView tv = new TextView(Global.get_context());
        tv.setText("VIDA " + jugador.get_apodo() + ": " + jugador.get_vida());
        //tv.setText("hola");
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tv.setForegroundGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        tv.setLayoutParams(tvParams);
        contenedor.addView(tv);
    }

    public static Button escribirBotonVolverMenu(ViewGroup contenedor) {
        Button boton = new Button(Global.get_context());
        boton.setText("Volver al menú");
        boton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        //boton.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);

        LinearLayout.LayoutParams paramsBMenuPrincipal = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                0, 1
        );
        paramsBMenuPrincipal.gravity = Gravity.CENTER_HORIZONTAL;

        paramsBMenuPrincipal.setMargins(0, Utilidades.dpToPx(1), 0, Utilidades.dpToPx(1));

        boton.setLayoutParams(paramsBMenuPrincipal);
        contenedor.addView(boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.get_activity().startActivity(new Intent(Global.get_context(), MenuPrincipalActivity.class));
            }
        });

        return boton;
    }


    public void IniciarJuego() {
        try {
            socket = new Socket("10.0.2.2", 83);


            PrintWriter enviar = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader recibir = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            enviar.println(ECodigosTCP.INICIAR_PARTIDA);
            enviar.println(Global.get_datosGuardablesJSON1Dispositivo().get_apodoEnRed());
            enviar.println(Global.jugador2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            enviar = new ObjectOutputStream(socket.getOutputStream());
            recibir = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {

        }
    }

}