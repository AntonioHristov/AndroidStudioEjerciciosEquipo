package com.loeches.yugioh.Modelo.Global;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.widget.LinearLayout;

import com.loeches.yugioh.Controlador.Controlador;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Vista.CartaVista;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.Vista.HorizontalVista;
import com.loeches.yugioh.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Global {
    // ATRIBUTOS
    public final static int POS_ERROR=-1; // SI SE BUSCA RECIBIR UNA POSICIÓN DE UNA LISTA O ARREGLO Y NO SE ENCUENTRA O MANEJAR ALGÚN ERROR RELACIONADO
    private static Activity _activity; // RECIBE LA VistaActivity (ÚNICA ACTIVITY), PARA QUE SEA ACCESIBLE EN EL PROGRAMA, JUNTO AL CONTEXTO Y EL CONTENEDOR PRINCIPAL QUE ES UN LINEAR LAYOUT VERTICAL
    private static boolean _turnoJugador1, _turnoAlAzar,_modoOptimoJugando, _preguntarConfirmacionAccionesJugando, _empezarPartidaNueva;// SI _modoOptimoJugando ES TRUE LLEVA AUTOMÁTICAMENTE UN MONSTRUO/HECHIZO EQUIPABLE AL 1º ESPACIO DISPONIBLE, SI _modoOptimoJugando VALE FALSE TENDRÍAS QUE ELEGIR MANUALMENTE LA CARTA VACÍA DESTINO.
    private static CartaVista _cartaVistaSeleccionada;// ES LA CARTA AL QUE EL JUGADOR HIZO CLICK Y TIENE EL BORDE EN ROJO
    // LOS _iniciandoPartida ¡¡¡ SON SOLAMENTE AL INICIO DE CADA PARTIDA, AHÍ NO SE GUARDAN LOS VALORES SI CAMBIAN A LO LARGO DE LA PARTIDA !!! LA IDEA ES QUE EL JUGADOR LO PUEDA CAMBIAR EN EL MENU PERSONALIZAR

    // EL ANCHO DE CADA CARTAVISTA SERÁ EL ANCHO DEL TELÉFONO DIVIDO ENTRE EL VALOR ALMACENADO EN _cantidadCartaVistasPorHorizontalSinScroll
    private static int _iniciandoPartidaCantidadMonstruosHorizontalJ1,
            _iniciandoPartidaCantidadMonstruosHorizontalJ2,
            _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1,
            _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2,
            _iniciandoPartidaCantidadManoHorizontalJ1,
            _iniciandoPartidaCantidadManoHorizontalJ2,
            _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll;

    private static MediaPlayer _musicaFondo;
    public static MediaPlayer _sonidoAtaqueMonstruo;

    private static List<HorizontalVista> _horizontalesVista = new ArrayList<>();// Los linear layout horizontales que contienen las cartas y sus datos
    private static List<Jugador> _jugadores =new ArrayList<>();// pos 0=Jugador 1, pos 1=Jugador 2
// FIN ATRIBUTOS

    // MÉTODOS
    public static void restaurarValoresDefecto(){
        _turnoJugador1=true;
        _turnoAlAzar=true;
        _modoOptimoJugando=true;
        _preguntarConfirmacionAccionesJugando=true;
        _empezarPartidaNueva=true;
        _cartaVistaSeleccionada=null;
        _iniciandoPartidaCantidadMonstruosHorizontalJ1=5;
        _iniciandoPartidaCantidadMonstruosHorizontalJ2=5;
        _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1=5;
        _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2=5;
        _iniciandoPartidaCantidadManoHorizontalJ1=5;
        _iniciandoPartidaCantidadManoHorizontalJ2=5;
        _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll=5;
        _jugadores=new ArrayList<>();
        new Jugador("Vida Jugador 1: ","",8000);
        new Jugador("Vida Jugador 2: ","",8000);
    }


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
        return _jugadores.get(_turnoJugador1?0:1);
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

    public static boolean is_turnoAlAzar() {
        return _turnoAlAzar;
    }

    public static void set_turnoAlAzar(boolean turnoAlAzar) {
        _turnoAlAzar = turnoAlAzar;
    }

    public static boolean is_modoOptimoJugando() {
        return _modoOptimoJugando;
    }

    public static void set_modoOptimoJugando(boolean modoOptimoJugando) {
        _modoOptimoJugando = modoOptimoJugando;
    }

    public static boolean is_preguntarConfirmacionAccionesJugando() {
        return _preguntarConfirmacionAccionesJugando;
    }

    public static void set_preguntarConfirmacionAccionesJugando(boolean preguntarConfirmacionAccionesJugando) {
        _preguntarConfirmacionAccionesJugando = preguntarConfirmacionAccionesJugando;
    }

    public static boolean is_empezarPartidaNueva() {
        return _empezarPartidaNueva;
    }

    public static void set_empezarPartidaNueva(boolean empezarPartidaNueva) {
        _empezarPartidaNueva = empezarPartidaNueva;
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

    public static int get_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll() {
        return _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll;
    }

    public static void set_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll(int iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll) {
        _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll = iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll;
    }

    public static MediaPlayer get_musicaFondo() {
        return _musicaFondo;
    }

    public static void set_musicaFondo(MediaPlayer musicaFondo) {
        _musicaFondo = musicaFondo;
    }

    public static MediaPlayer get_sonidoAtaqueMonstruo() {
        return _sonidoAtaqueMonstruo;
    }

    public static void set_sonidoAtaqueMonstruo(MediaPlayer sonidoAtaqueMonstruo) {
        _sonidoAtaqueMonstruo = sonidoAtaqueMonstruo;
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
