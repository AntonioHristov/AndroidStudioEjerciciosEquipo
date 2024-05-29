package com.loeches.yugioh.Controlador;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Cartas.Vista.CartaVista;
import com.loeches.yugioh.Modelo.Enums.EUbicacionCartaVista;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.R;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    public static void NuevaPartida(Context contextActivity){
        CrearJugadoresYMostrarlos(contextActivity);
        CrearCartasVista(contextActivity);
    }

    public static void CrearJugadoresYMostrarlos(Context contextActivity){
        Activity activity=(Activity)contextActivity;
        new Jugador(activity.findViewById(R.id.tvVidaJ1),"Vida Jugador 1: ");
        new Jugador(activity.findViewById(R.id.tvVidaJ2),"Vida Jugador 2: ");
    }

    public static void CrearCartasVista(Context contextActivity){
        Activity activity=(Activity)contextActivity;
        List<LinearLayout> linearsLayoutsHorizontales = new ArrayList<>();
        linearsLayoutsHorizontales.add(activity.findViewById(R.id.llHMonstruosJ1));
        linearsLayoutsHorizontales.add(activity.findViewById(R.id.llHMonstruosJ2));
        linearsLayoutsHorizontales.add(activity.findViewById(R.id.llHHechizosJ1));
        linearsLayoutsHorizontales.add(activity.findViewById(R.id.llHHechizosJ2));
        linearsLayoutsHorizontales.add(activity.findViewById(R.id.llHAbajoJ1));
        for (LinearLayout linearLayout :linearsLayoutsHorizontales) {
            linearLayout.removeAllViews();
        }

        for (int i = 0; i < Lista.CANTIDAD_MONSTRUOS_ELEGIDOS; i++) {
            new CartaVista(contextActivity, linearsLayoutsHorizontales.get(0),new CartaVacia(), EUbicacionCartaVista.J1_MONSTRUO);
            new CartaVista(contextActivity, linearsLayoutsHorizontales.get(1),new CartaVacia(), EUbicacionCartaVista.J2_MONSTRUO);
        }

        for (int i = 0; i < Lista.CANTIDAD_HECHIZOS_ELEGIDOS; i++) {
            new CartaVista(contextActivity, linearsLayoutsHorizontales.get(2),new CartaVacia(), EUbicacionCartaVista.J1_HECHIZO);
            new CartaVista(contextActivity, linearsLayoutsHorizontales.get(3),new CartaVacia(), EUbicacionCartaVista.J2_HECHIZO);
        }

    }



}
