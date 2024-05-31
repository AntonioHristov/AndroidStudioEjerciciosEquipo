package com.loeches.yugioh.Controlador;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HEscudo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuDisparo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Monstruos.MonstruoGenerico;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Global.Variables;
import com.loeches.yugioh.Modelo.Vista.CartaVista;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.Vista.HorizontalVista;
import com.loeches.yugioh.R;

import java.util.Random;

public class Controlador {
    public static void NuevaPartida(){

        CrearJugadoresYTurno();
        CrearHorizontalesVistaConContenido();
        ActualizarVistaCartas();
        //ActualizarVistaCartas();
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
        for (int i = 0; i < 10; i++) {
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
        for (int i = 0; i < 8; i++) {
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
        //CrearHorizontalesVistaConContenido();
        VaciarVista();
        if(Variables.is_turnoJugador1()){
            // 1 PORQUE NO MOSTRAMOS LAS CARTAS DE LA MANO DEL RIVAL
            for (int i = 1; i < Lista.get_horizontalesVista().size(); i++) {
                if(i==3){
                    Lista.get_jugadores().get(1).EscribirCodigoXML();
                    EscribirXmlDivider();
                    Lista.get_jugadores().get(0).EscribirCodigoXML();
                }
                Lista.get_horizontalesVista().get(i).EscribirCodigoXML();
            }
        }else{
            for (int i = Lista.get_horizontalesVista().size()-2; i >= 0; i--) {
                if(i==2){
                    Lista.get_jugadores().get(0).EscribirCodigoXML();
                    EscribirXmlDivider();
                    Lista.get_jugadores().get(1).EscribirCodigoXML();
                }
                Lista.get_horizontalesVista().get(i).EscribirCodigoXML();
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
}
