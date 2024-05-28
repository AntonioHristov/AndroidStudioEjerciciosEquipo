package com.loeches.yugioh.Modelo.Cartas.Global;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;

import java.util.ArrayList;
import java.util.List;

public class Lista {
    public static final int CANTIDAD_MONSTRUOS_ELEGIDOS=5,CANTIDAD_HECHIZOS_ELEGIDOS=5;
    /*
     LAS _unicas SON TODAS LAS CARTAS (LAS 50, EXCEPTO LA VACIA) QUE NO SE DEBERÍAN REPETIR. TAL VEZ SE USE PARA GENERAR UNA CARTA AL AZAR, SINO CONSIDERAR ELIMINARLO
     LA DE LOS JUGADORES SI SE PODRIAN REPETIR
     (ESO PENSÓ ANTONIO, CON FEEDBACK PODRÍA VARIAR...)
     */
    private static List<ACarta> _cartasUnicas=new ArrayList<>(), _jugador1CartasAbajo=new ArrayList<>(), _jugador2CartasAbajo=new ArrayList<>();
    private static ACarta[] _jugador1MonstruosElegidos = new ACarta[CANTIDAD_MONSTRUOS_ELEGIDOS], _jugador2MonstruosElegidos = new ACarta[CANTIDAD_MONSTRUOS_ELEGIDOS];
    private static ACarta[] _jugador1HechizosElegidos = new ACarta[CANTIDAD_HECHIZOS_ELEGIDOS], _jugador2HechizosElegidos = new ACarta[CANTIDAD_HECHIZOS_ELEGIDOS];







    public static List<ACarta> get_cartasUnicas() {
        return _cartasUnicas;
    }

    public static List<ACarta> get_jugador1CartasAbajo() {
        return _jugador1CartasAbajo;
    }

    public static List<ACarta> get_jugador2CartasAbajo() {
        return _jugador2CartasAbajo;
    }

    public static ACarta[] get_jugador1MonstruosElegidos() {
        return _jugador1MonstruosElegidos;
    }

    public static ACarta[] get_jugador2MonstruosElegidos() {
        return _jugador2MonstruosElegidos;
    }

    public static ACarta[] get_jugador1HechizosElegidos() {
        return _jugador1HechizosElegidos;
    }

    public static ACarta[] get_jugador2HechizosElegidos() {
        return _jugador2HechizosElegidos;
    }
}
