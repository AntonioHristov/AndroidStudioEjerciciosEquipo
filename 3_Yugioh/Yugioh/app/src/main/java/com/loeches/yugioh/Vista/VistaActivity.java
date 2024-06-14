package com.loeches.yugioh.Vista;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
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
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.Vista.HorizontalVista;
import com.loeches.yugioh.R;

public class VistaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Global.set_activity(this);

        if(Global.get_horizontalesVista().isEmpty()){
            Controlador.NuevaPartida();
        }else{
            if(Controlador.partidaTerminada()){
                mostrarGanador();
            }else{
                actualizar();
            }
        }
    }

    public static void vaciar(){
        Global.get_linearMain().removeAllViews();
        for (HorizontalVista hv: Global.get_horizontalesVista()) {
            hv.get_llHorizontal().removeAllViews();
        }
    }

    public static void actualizar(){
        vaciar();
        if(Global.is_turnoJugador1()){
            // 1 PORQUE NO MOSTRAMOS LAS CARTAS DE LA MANO DEL RIVAL
            for (int i = 1; i < Global.get_horizontalesVista().size(); i++) {
                if(i==3){
                    mostrarDatosJugadoresCentro();
                }
                Global.get_horizontalesVista().get(i).EscribirCodigoXML(!Global.get_horizontalesVista().get(i).esSuTurno());
            }
        }else{
            for (int i = Global.get_horizontalesVista().size()-2; i > -1; i--) {
                if(i==2){
                    mostrarDatosJugadoresCentro();
                }
                Global.get_horizontalesVista().get(i).EscribirCodigoXML(!Global.get_horizontalesVista().get(i).esSuTurno());
            }
        }
    }

    public static void mostrarGanador(){
        VistaActivity.vaciar();
        Context context = Global.get_context();
        LinearLayout main = ((Activity) context).findViewById(R.id.main);

        TextView textView = new TextView(context);
        if(Global.get_jugadores().get(0).get_vida()==0 && Global.get_jugadores().get(1).get_vida()==0){
            textView.setText("EMPATE, NADIE GANÓ");
        }else if(Global.get_jugadores().get(1).get_vida()==0){
            textView.setText("GANÓ EL JUGADOR 1");
        }else if(Global.get_jugadores().get(0).get_vida()==0){
            textView.setText("GANÓ EL JUGADOR 2");
        }else{
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
                0,1
        ));

        main.addView(textView);


        Button bt = new Button(context);
        bt.setText("Nueva partida");
        //bt.setTextSize(30);
        bt.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.NuevaPartida();
            }
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,1
        );
        params.gravity = Gravity.CENTER_HORIZONTAL;

        params.setMargins(0, Utilidades.dpToPx(10), 0, Utilidades.dpToPx(10));

        bt.setLayoutParams(params);

        main.addView(bt);
    }

    public static LinearLayout crearLinearVerticalJugadoresCentroVista(){
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

    public static void escribirXmlDivider(ViewGroup contenedor){
        Context context=Global.get_context();
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

    public static void mostrarDatosJugadoresCentro(){
        LinearLayout llHorizontal = crearLinearVerticalJugadoresCentroVista();
        Global.get_linearMain().addView(llHorizontal);
        if(Global.is_turnoJugador1()){
            Global.get_jugadores().get(1).EscribirCodigoXML(llHorizontal);
            escribirXmlDivider(llHorizontal);
            Global.get_jugadores().get(0).EscribirCodigoXML(llHorizontal);
        }else{
            Global.get_jugadores().get(0).EscribirCodigoXML(llHorizontal);
            escribirXmlDivider(llHorizontal);
            Global.get_jugadores().get(1).EscribirCodigoXML(llHorizontal);
        }
    }


}