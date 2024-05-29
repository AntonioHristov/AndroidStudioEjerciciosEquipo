package com.loeches.yugioh.Modelo.Global;

import android.widget.FrameLayout;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Cartas.Vista.CartaVista;
import com.loeches.yugioh.Modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

public class Lista {
    public static final int CANTIDAD_MONSTRUOS_ELEGIDOS = 5, CANTIDAD_HECHIZOS_ELEGIDOS = 5;
    /*
     LAS _unicas SON TODAS LAS CARTAS (LAS 50, EXCEPTO LA VACIA) QUE NO SE DEBERÍAN REPETIR. TAL VEZ SE USE PARA GENERAR UNA CARTA AL AZAR, SINO CONSIDERAR ELIMINARLO
     (ESO PENSÓ ANTONIO, CON FEEDBACK PODRÍA VARIAR...)
     */
    private static List<ACarta> _cartasUnicas = new ArrayList<>();
    private static List<CartaVista> cartasVista = new ArrayList<>();
    private static List<Jugador> jugadores=new ArrayList<>();


    public static List<ACarta> get_cartasUnicas() {
        return _cartasUnicas;
    }

    public static List<CartaVista> getCartasVista() {
        return cartasVista;
    }

    public static List<Jugador> getJugadores() {
        return jugadores;
    }
}
