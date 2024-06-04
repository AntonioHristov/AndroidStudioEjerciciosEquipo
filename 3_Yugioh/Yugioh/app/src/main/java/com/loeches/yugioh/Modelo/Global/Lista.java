package com.loeches.yugioh.Modelo.Global;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HEscudo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuDisparo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Monstruos.MonstruoGenerico;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Vista.CartaVista;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.Vista.HorizontalVista;
import com.loeches.yugioh.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lista {
    public static final int CANTIDAD_MONSTRUOS_ELEGIDOS = 5, CANTIDAD_HECHIZOS_ELEGIDOS = 5;
    private static List<HorizontalVista> _horizontalesVista = new ArrayList<>();
    private static List<Jugador> _jugadores =new ArrayList<>();

    public static HorizontalVista getBy(EIdHorizontalVista id){
        for (HorizontalVista hv:_horizontalesVista) {
            if(hv.get_id()==id){
                return hv;
            }
        }
        return null;
    }

    public static int getPosHorizontalVista(CartaVista cv){
        List<CartaVista> cvs= cv.get_horizontalVista().get_cartasVista();
        for (int i = 0; i < cvs.size(); i++) {
            if(cv.equals(cvs.get(i))){
                return i;
            }
        }
        return Variables.POS_ERROR;
    }

    public static ACarta getCartaJugable(int pos) {
        // EL MÚMERO DE getCartaJugableRandom DEBE SER EL ÚLTIMO NÚMERO DE ESTE SWITCH +1
        // EJEMPLO: SI EL ÚLTIMO NÚMERO DE ESTE SWITCH FUESE 2, EL NÚMERO EN getCartaJugableRandom DEBE SER 3
        switch(pos) {
            case 0:
                return new MonstruoGenerico("Jinzo","Un monstruo verde y rojo", R.drawable.m_jinzo,2400,1500);
            case 1:
                return new HuDisparo();
            case 2:
                return new HEscudo();
        }
        return null;
    }

    public static ACarta getCartaJugableRandom() {
        return getCartaJugable(new Random().nextInt(3));
    }


    public static List<HorizontalVista> get_horizontalesVista() {
        return _horizontalesVista;
    }

    public static void set_horizontalesVista(List<HorizontalVista> horizontalesVista) {
        _horizontalesVista = horizontalesVista;
    }

    public static List<Jugador> get_jugadores() {
        return _jugadores;
    }

    public static void set_jugadores(List<Jugador> jugadores) {
        _jugadores = jugadores;
    }
}
