package com.loeches.yugioh.Controlador;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Global.Variables;
import com.loeches.yugioh.Modelo.Vista.CartaVista;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.Vista.HorizontalVista;
import com.loeches.yugioh.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controlador{

    public static void NuevaPartida(){
        VaciarDatos();
        CrearJugadoresYTurno();
        CrearHorizontalesVistaConContenido();
        ActualizarVistaCartas();
    }

    public static void VaciarDatos(){
        Lista.set_jugadores(new ArrayList<>());
        Lista.set_horizontalesVista(new ArrayList<>());
    }

    public static void CrearJugadoresYTurno(){
        Activity activity=(Activity)Variables.get_gameActivityContext();
        new Jugador("Vida Jugador 1: ");
        new Jugador("Vida Jugador 2: ");
        Variables.set_turnoJugador1((new Random().nextInt(2)==0)?true:false);
    }

    public static void CrearHorizontalesVistaConContenido(){
        HorizontalVista hVManoJ2,hVHechizoJ2,hVMonstruoJ2,hVMonstruoJ1, hVHechizoJ1,hVManoJ1;

        hVManoJ2= new HorizontalVista(EIdHorizontalVista.J2_MANO);
        for (int i = 0; i < 5; i++) {
            new CartaVista(hVManoJ2,Lista.getCartaJugableRandom());
        }

        hVHechizoJ2= new HorizontalVista(EIdHorizontalVista.J2_HECHIZO);
        for (int i = 0; i < Lista.CANTIDAD_HECHIZOS_ELEGIDOS; i++) {
            new CartaVista(hVHechizoJ2,new CartaVacia());
        }

        hVMonstruoJ2= new HorizontalVista(EIdHorizontalVista.J2_MONSTRUO);
        for (int i = 0; i < Lista.CANTIDAD_MONSTRUOS_ELEGIDOS; i++) {
            new CartaVista(hVMonstruoJ2,new CartaVacia());
        }

        hVMonstruoJ1= new HorizontalVista(EIdHorizontalVista.J1_MONSTRUO);
        for (int i = 0; i < Lista.CANTIDAD_MONSTRUOS_ELEGIDOS; i++) {
            new CartaVista(hVMonstruoJ1,new CartaVacia());
        }

        hVHechizoJ1= new HorizontalVista(EIdHorizontalVista.J1_HECHIZO);
        for (int i = 0; i < Lista.CANTIDAD_HECHIZOS_ELEGIDOS; i++) {
            new CartaVista(hVHechizoJ1,new CartaVacia());
        }

        hVManoJ1= new HorizontalVista(EIdHorizontalVista.J1_MANO);
        for (int i = 0; i < 5; i++) {
            new CartaVista(hVManoJ1,Lista.getCartaJugableRandom());
        }
    }

    public static void VaciarVista(){
        Context context=Variables.get_gameActivityContext();
        LinearLayout main =((Activity)context).findViewById(R.id.main);
        main.removeAllViews();

        for (HorizontalVista hv:Lista.get_horizontalesVista()) {
            hv.get_llHorizontal().removeAllViews();
        }
    }

    public static void ActualizarVistaCartas(){
        VaciarVista();
        if(Variables.is_turnoJugador1()){
            // 1 PORQUE NO MOSTRAMOS LAS CARTAS DE LA MANO DEL RIVAL
            for (int i = 1; i < Lista.get_horizontalesVista().size(); i++) {
                if(i==3){
                    Lista.get_jugadores().get(1).EscribirCodigoXML();
                    EscribirXmlDivider();
                    Lista.get_jugadores().get(0).EscribirCodigoXML();
                }
                if(Lista.get_horizontalesVista().get(i).esSuTurno()){
                    Lista.get_horizontalesVista().get(i).EscribirCodigoXML(false);
                }else{
                    Lista.get_horizontalesVista().get(i).EscribirCodigoXML(true);
                }
            }
        }else{
            for (int i = Lista.get_horizontalesVista().size()-2; i >= 0; i--) {
                if(i==2){
                    Lista.get_jugadores().get(0).EscribirCodigoXML();
                    EscribirXmlDivider();
                    Lista.get_jugadores().get(1).EscribirCodigoXML();
                }
                if(Lista.get_horizontalesVista().get(i).esSuTurno()){
                    Lista.get_horizontalesVista().get(i).EscribirCodigoXML(false);
                }else{
                    Lista.get_horizontalesVista().get(i).EscribirCodigoXML(true);
                }
            }
        }

    }

    public static void EscribirXmlDivider(){
        Context context=Variables.get_gameActivityContext();
        LinearLayout main =((Activity)context).findViewById(R.id.main);
        // Crear el View
        View dividerView = new View(context);

        // Configurar LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics())
        );
        int marginVertical = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics());
        params.setMargins(0, marginVertical, 0, marginVertical);
        dividerView.setLayoutParams(params);

        // Configurar propiedades del View
        dividerView.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
        dividerView.setVisibility(View.VISIBLE);  // O View.INVISIBLE o View.GONE si deseas cambiar la visibilidad

        // Agregar el View al LinearLayout
        main.addView(dividerView);
    }

    public static void nuevoTurno(){
        if(partidaTerminada()){
            mostrarGanador();
        }else{
            Variables.set_cartaVistaSeleccionada(null);
            Variables.set_turnoJugador1(!Variables.is_turnoJugador1());
            if(Variables.is_turnoJugador1()){
                new CartaVista(Lista.getBy(EIdHorizontalVista.J1_MANO),Lista.getCartaJugableRandom());
            }else{
                new CartaVista(Lista.getBy(EIdHorizontalVista.J2_MANO),Lista.getCartaJugableRandom());
            }
            for (HorizontalVista hv:Lista.get_horizontalesVista()) {
                for (CartaVista cv:hv.get_cartasVista()) {
                    if(cv.get_carta() instanceof AMonstruo){
                        // PARA LOS AUMENTOS DE ESTADISTICAS POR CIERTOS TURNOS
                        ((AMonstruo) cv.get_carta()).NuevoTurno();
                    }
                }
            }
            ActualizarVistaCartas();
        }
    }

    public static boolean partidaTerminada(){
        for (Jugador j:Lista.get_jugadores()) {
            if(j.get_vida()<=0){
                return true;
            }
        }
        return false;
    }

    public static void mostrarGanador(){
        VaciarVista();
        Context context = Variables.get_gameActivityContext();
        LinearLayout main = ((Activity) context).findViewById(R.id.main);

        TextView textView = new TextView(context);
        if(Lista.get_jugadores().get(0).get_vida()==0 && Lista.get_jugadores().get(1).get_vida()==0){
            textView.setText("EMPATE, NADIE GANÓ");
        }else if(Lista.get_jugadores().get(1).get_vida()==0){
            textView.setText("GANÓ EL JUGADOR 1");
        }else if(Lista.get_jugadores().get(0).get_vida()==0){
            textView.setText("GANÓ EL JUGADOR 2");
        }else{
            textView.setText("NADIE PERDIÓ, WTF ESTO NO DEBERÍA OCURRIR...");
        }
        textView.setTextSize(50); // Tamaño del texto en "sp" (Scale-independent Pixels)
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD); // Establece el texto en negrita
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        main.addView(textView);


        Button bt = new Button(context);
        bt.setText("Nueva partida");
        bt.setTextSize(30);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.NuevaPartida();
            }
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.CENTER_HORIZONTAL;

        params.setMargins(16, 16, 16, 16);

        bt.setLayoutParams(params);

        main.addView(bt);
    }
}
