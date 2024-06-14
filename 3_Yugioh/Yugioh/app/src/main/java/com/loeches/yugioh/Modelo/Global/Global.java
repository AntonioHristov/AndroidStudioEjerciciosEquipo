package com.loeches.yugioh.Modelo.Global;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Vista.CartaVista;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.Vista.HorizontalVista;
import com.loeches.yugioh.R;

import java.util.ArrayList;
import java.util.List;

public class Global {
    // ATRIBUTOS
    public final static int POS_ERROR=-1; // SI SE BUSCA RECIBIR UNA POSICIÓN DE UNA LISTA O ARREGLO Y NO SE ENCUENTRA O MANEJAR ALGÚN ERROR RELACIONADO
    private static Activity _activity; // RECIBE LA VistaActivity (ÚNICA ACTIVITY), PARA QUE SEA ACCESIBLE EN EL PROGRAMA, JUNTO AL CONTEXTO Y EL CONTENEDOR PRINCIPAL QUE ES UN LINEAR LAYOUT VERTICAL
    private static boolean _turnoJugador1, _modoOptimoJugando;// SI _modoOptimoJugando ES TRUE LLEVA AUTOMÁTICAMENTE UN MONSTRUO/HECHIZO EQUIPABLE AL 1º ESPACIO DISPONIBLE, SI _modoOptimoJugando VALE FALSE TENDRÍAS QUE ELEGIR ESE 1º ESPACIO DISPONIBLE HACIENDO CLICK. ME HIZO ILUSIÓN PERMITIR LA INNECESARIA OPCIÓN false PARA EL FUTURO MENÚ PERSONALIZAR (SOY ANTONIO HRISTOV)
    private static CartaVista _cartaVistaSeleccionada;// ES LA CARTA AL QUE EL JUGADOR HIZO CLICK Y TIENE EL BORDE EN ROJO
    // LOS _iniciandoPartida... SON PARA DAR UN TAMAÑO ¡¡¡ SOLAMENTE AL INICIO DE CADA PARTIDA, AHÍ NO SE GUARDAN LOS VALORES SI CAMBIAN A LO LARGO DE LA PARTIDA !!! LA IDEA ES QUE EL JUGADOR LO PUEDA CAMBIAR EN EL MENU PERSONALIZAR
    private static int _iniciandoPartidaCantidadMonstruosHorizontalJ1, _iniciandoPartidaCantidadMonstruosHorizontalJ2, _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1, _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2, _iniciandoPartidaCantidadManoHorizontalJ1, _iniciandoPartidaCantidadManoHorizontalJ2, _cantidadCartaVistasPorHorizontalSinScroll; // EL ANCHO DE CADA CARTAVISTA SERÁ EL ANCHO DEL TELÉFONO DIVIDO ENTRE EL VALOR ALMACENADO EN _cantidadCartaVistasPorHorizontalSinScroll


    private static List<HorizontalVista> _horizontalesVista = new ArrayList<>();// Los linear layout horizontales que contienen las cartas y sus datos
    private static List<Jugador> _jugadores =new ArrayList<>();// pos 0=Jugador 1, pos 1=Jugador 2
// FIN ATRIBUTOS

    // MÉTODOS
    public static LinearLayout get_linearMain(){
        return _activity.findViewById(R.id.main);
    }
    public static Context get_context() {
        return _activity.getApplicationContext();
    }

    public static HorizontalVista getBy(EIdHorizontalVista id){
        for (HorizontalVista hv:_horizontalesVista) {
            if(hv.get_id()==id){
                return hv;
            }
        }
        return null;
    }

    public static int getIndexHorizontalVista(CartaVista cv){
        List<CartaVista> cvs= cv.get_horizontalVista().get_cartasVista();
        for (int i = 0; i < cvs.size(); i++) {
            if(cv.equals(cvs.get(i))){
                return i;
            }
        }
        return POS_ERROR;
    }

    public static Jugador getByTurno(){
        return get_jugadores().get(is_turnoJugador1()?0:1);
    }
// FIN MÉTODOS





// GETTERS Y SETTERS

    public static Activity get_activity() {
        return _activity;
    }

    public static void set_activity(Activity activity) {
        _activity = activity;
    }
    public static boolean is_turnoJugador1() {
        return _turnoJugador1;
    }

    public static void set_turnoJugador1(boolean esTurnoJugador1) {
        _turnoJugador1 = esTurnoJugador1;
    }

    public static boolean is_modoOptimoJugando() {
        return _modoOptimoJugando;
    }

    public static void set_modoOptimoJugando(boolean modoOptimoJugando) {
        _modoOptimoJugando = modoOptimoJugando;
    }

    public static CartaVista get_cartaVistaSeleccionada() {
        return _cartaVistaSeleccionada;
    }

    public static void set_cartaVistaSeleccionada(CartaVista cartaVistaSeleccionada) {
        _cartaVistaSeleccionada = cartaVistaSeleccionada;
    }

    public static int get_iniciandoPartidaCantidadMonstruosHorizontalJ1() {
        return _iniciandoPartidaCantidadMonstruosHorizontalJ1;
    }

    public static void set_iniciandoPartidaCantidadMonstruosHorizontalJ1(int iniciandoPartidaCantidadMonstruosHorizontalJ1) {
        _iniciandoPartidaCantidadMonstruosHorizontalJ1 = iniciandoPartidaCantidadMonstruosHorizontalJ1;
    }

    public static int get_iniciandoPartidaCantidadMonstruosHorizontalJ2() {
        return _iniciandoPartidaCantidadMonstruosHorizontalJ2;
    }

    public static void set_iniciandoPartidaCantidadMonstruosHorizontalJ2(int iniciandoPartidaCantidadMonstruosHorizontalJ2) {
        _iniciandoPartidaCantidadMonstruosHorizontalJ2 = iniciandoPartidaCantidadMonstruosHorizontalJ2;
    }

    public static int get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1() {
        return _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1;
    }

    public static void set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1(int iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1) {
        _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1 = iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1;
    }

    public static int get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2() {
        return _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2;
    }

    public static void set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2(int iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2) {
        _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2 = iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2;
    }

    public static int get_iniciandoPartidaCantidadManoHorizontalJ1() {
        return _iniciandoPartidaCantidadManoHorizontalJ1;
    }

    public static void set_iniciandoPartidaCantidadManoHorizontalJ1(int iniciandoPartidaCantidadManoHorizontalJ1) {
        _iniciandoPartidaCantidadManoHorizontalJ1 = iniciandoPartidaCantidadManoHorizontalJ1;
    }

    public static int get_iniciandoPartidaCantidadManoHorizontalJ2() {
        return _iniciandoPartidaCantidadManoHorizontalJ2;
    }

    public static void set_iniciandoPartidaCantidadManoHorizontalJ2(int iniciandoPartidaCantidadManoHorizontalJ2) {
        _iniciandoPartidaCantidadManoHorizontalJ2 = iniciandoPartidaCantidadManoHorizontalJ2;
    }

    public static int get_cantidadCartaVistasPorHorizontalSinScroll() {
        return _cantidadCartaVistasPorHorizontalSinScroll;
    }

    public static void set_cantidadCartaVistasPorHorizontalSinScroll(int cantidadCartaVistasPorHorizontalSinScroll) {
        _cantidadCartaVistasPorHorizontalSinScroll = cantidadCartaVistasPorHorizontalSinScroll;
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

    // FIN GETTERS Y SETTERS
}
