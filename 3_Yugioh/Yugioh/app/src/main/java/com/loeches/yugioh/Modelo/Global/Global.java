package com.loeches.yugioh.Modelo.Global;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import com.loeches.yugioh.DAO.Implementaciones.DatosGuardablesJSON;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
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
    public final static String NOMBRE_ARCHIVO_GUARDAR_DATOS="datosGuardados";
    private static Activity _activity; // RECIBE LA VistaActivity (ÚNICA ACTIVITY), PARA QUE SEA ACCESIBLE EN EL PROGRAMA, JUNTO AL CONTEXTO Y EL CONTENEDOR PRINCIPAL QUE ES UN LINEAR LAYOUT VERTICAL
    //private static boolean _turnoJugador1, _turnoAlAzar,_modoOptimoJugando, _preguntarConfirmacionAccionesJugando, _empezarPartidaNueva, _guardarDatosEnArchivo;// SI _modoOptimoJugando ES TRUE LLEVA AUTOMÁTICAMENTE UN MONSTRUO/HECHIZO EQUIPABLE AL 1º ESPACIO DISPONIBLE, SI _modoOptimoJugando VALE FALSE TENDRÍAS QUE ELEGIR MANUALMENTE LA CARTA VACÍA DESTINO.
    private static CartaVista _cartaVistaSeleccionada;// ES LA CARTA AL QUE EL JUGADOR HIZO CLICK Y TIENE EL BORDE EN ROJO
    /*
    // LOS _iniciandoPartida ¡¡¡ SON SOLAMENTE AL INICIO DE CADA PARTIDA, AHÍ NO SE GUARDAN LOS VALORES SI CAMBIAN A LO LARGO DE LA PARTIDA !!! LA IDEA ES QUE EL JUGADOR LO PUEDA CAMBIAR EN EL MENU PERSONALIZAR

    // EL ANCHO DE CADA CARTAVISTA SERÁ EL ANCHO DEL TELÉFONO DIVIDO ENTRE EL VALOR ALMACENADO EN _cantidadCartaVistasPorHorizontalSinScroll
    private static int _iniciandoPartidaCantidadMonstruosHorizontalJ1,
            _iniciandoPartidaCantidadMonstruosHorizontalJ2,
            _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1,
            _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2,
            _iniciandoPartidaCantidadManoHorizontalJ1,
            _iniciandoPartidaCantidadManoHorizontalJ2,
            _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll,
            _iniciandoJugador1Vida,
            _iniciandoJugador2Vida,
            _musicaFondoJugando,
            _sonidoAtaqueMonstruo;
    private static String _iniciandoJugador1Prefijo, _iniciandoJugador1Sufijo, _iniciandoJugador2Prefijo, _iniciandoJugador2Sufijo;
*/
    private static List<HorizontalVista> _horizontalesVista;// Los linear layout horizontales que contienen las cartas y sus datos
    private static List<Jugador> _jugadores;// pos 0=Jugador 1, pos 1=Jugador 2
    private static DatosGuardablesJSON _datosGuardablesJSON;

// FIN ATRIBUTOS

    // MÉTODOS

    public static void restaurarValoresDefecto(){
        _cartaVistaSeleccionada=null;
        /*
        _turnoJugador1=true;
        _turnoAlAzar=true;
        _modoOptimoJugando=true;
        _preguntarConfirmacionAccionesJugando=true;
        _empezarPartidaNueva=true;
        _guardarDatosEnArchivo=true;
        _cartaVistaSeleccionada=null;
        _iniciandoPartidaCantidadMonstruosHorizontalJ1=5;
        _iniciandoPartidaCantidadMonstruosHorizontalJ2=5;
        _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1=5;
        _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2=5;
        _iniciandoPartidaCantidadManoHorizontalJ1=5;
        _iniciandoPartidaCantidadManoHorizontalJ2=5;
        _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll=5;
        _iniciandoJugador1Vida=8000;
        _iniciandoJugador2Vida=100;
        _musicaFondoJugando = R.raw.yugiho;
        _sonidoAtaqueMonstruo= R.raw.yugiho;
        _iniciandoJugador1Prefijo="Vida Jugador 1: ";
        _iniciandoJugador1Sufijo="";
        _iniciandoJugador2Prefijo="Vida Jugador 2: ";
        _iniciandoJugador2Sufijo="";*/
        _horizontalesVista=new ArrayList<>();
        _jugadores=new ArrayList<>();
        _datosGuardablesJSON =new DatosGuardablesJSON();

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

    /*
    public static int getIndexCarta(ACarta c){
        List<ACarta> cs= get_datosGuardablesJSON().get_cartas();
        for (int i = 0; i < cs.size(); i++) {
            if(c.equals(cs.get(i))){
                return i;
            }
        }
        return POS_ERROR;
    }*/

    /*public static Jugador getByTurno(){
        return _jugadores.get(_turnoJugador1?0:1);
    }*/
// FIN MÉTODOS





// GETTERS Y SETTERS

    public static Activity get_activity() {
        return _activity;
    }

    public static void set_activity(Activity activity) {
        _activity = activity;
    }

    public static boolean is_turnoJugador1() {
        //return _turnoJugador1;
        return _datosGuardablesJSON.is_turnoJugador1();
    }

    public static void set_turnoJugador1(boolean esTurnoJugador1) {
        //_turnoJugador1 = esTurnoJugador1;
        _datosGuardablesJSON.set_turnoJugador1(esTurnoJugador1);
    }

    public static boolean is_turnoAlAzar() {
        //return _turnoAlAzar;
        return _datosGuardablesJSON.is_turnoAlAzar();
    }

    public static void set_turnoAlAzar(boolean turnoAlAzar) {
        //_turnoAlAzar = turnoAlAzar;
        _datosGuardablesJSON.set_turnoAlAzar(turnoAlAzar);
    }

    public static boolean is_modoOptimoJugando() {
        //return _modoOptimoJugando;
        return _datosGuardablesJSON.is_modoOptimoJugando();
    }

    public static void set_modoOptimoJugando(boolean modoOptimoJugando) {
        //_modoOptimoJugando = modoOptimoJugando;
        _datosGuardablesJSON.set_modoOptimoJugando(modoOptimoJugando);
    }

    public static boolean is_preguntarConfirmacionAccionesJugando() {
        //return _preguntarConfirmacionAccionesJugando;
        return _datosGuardablesJSON.is_preguntarConfirmacionAccionesJugando();
    }

    public static void set_preguntarConfirmacionAccionesJugando(boolean preguntarConfirmacionAccionesJugando) {
        //_preguntarConfirmacionAccionesJugando = preguntarConfirmacionAccionesJugando;
        _datosGuardablesJSON.set_preguntarConfirmacionAccionesJugando(preguntarConfirmacionAccionesJugando);
    }

    public static boolean is_empezarPartidaNueva() {
        //return _empezarPartidaNueva;
        return _datosGuardablesJSON.is_empezarPartidaNueva();
    }

    public static void set_empezarPartidaNueva(boolean empezarPartidaNueva) {
        //_empezarPartidaNueva = empezarPartidaNueva;
        _datosGuardablesJSON.set_empezarPartidaNueva(empezarPartidaNueva);
    }

    /*
    public static boolean is_guardarDatosEnArchivo() {
        return _guardarDatosEnArchivo;
    }

    public static void set_guardarDatosEnArchivo(boolean _guardarDatosEnArchivo) {
        Global._guardarDatosEnArchivo = _guardarDatosEnArchivo;
    }*/

    public static CartaVista get_cartaVistaSeleccionada() {
        return _cartaVistaSeleccionada;
    }

    public static void set_cartaVistaSeleccionada(CartaVista cartaVistaSeleccionada) {
        _cartaVistaSeleccionada = cartaVistaSeleccionada;
    }

    public static int get_iniciandoPartidaCantidadMonstruosHorizontalJ1() {
        //return _iniciandoPartidaCantidadMonstruosHorizontalJ1;
        return _datosGuardablesJSON.get_iniciandoPartidaCantidadMonstruosHorizontalJ1();
    }

    public static void set_iniciandoPartidaCantidadMonstruosHorizontalJ1(int iniciandoPartidaCantidadMonstruosHorizontalJ1) {
        //_iniciandoPartidaCantidadMonstruosHorizontalJ1 = iniciandoPartidaCantidadMonstruosHorizontalJ1;
        _datosGuardablesJSON.set_iniciandoPartidaCantidadMonstruosHorizontalJ1(iniciandoPartidaCantidadMonstruosHorizontalJ1);
    }

    public static int get_iniciandoPartidaCantidadMonstruosHorizontalJ2() {
        //return _iniciandoPartidaCantidadMonstruosHorizontalJ2;
        return _datosGuardablesJSON.get_iniciandoPartidaCantidadMonstruosHorizontalJ2();
    }

    public static void set_iniciandoPartidaCantidadMonstruosHorizontalJ2(int iniciandoPartidaCantidadMonstruosHorizontalJ2) {
        //_iniciandoPartidaCantidadMonstruosHorizontalJ2 = iniciandoPartidaCantidadMonstruosHorizontalJ2;
        _datosGuardablesJSON.set_iniciandoPartidaCantidadMonstruosHorizontalJ2(iniciandoPartidaCantidadMonstruosHorizontalJ2);
    }

    public static int get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1() {
        //return _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1;
        return _datosGuardablesJSON.get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1();
    }

    public static void set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1(int iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1) {
        //_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1 = iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1;
        _datosGuardablesJSON.set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1(iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1);
    }

    public static int get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2() {
        //return _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2;
        return _datosGuardablesJSON.get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2();
    }

    public static void set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2(int iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2) {
        //_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2 = iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2;
        _datosGuardablesJSON.set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2(iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2);
    }

    public static int get_iniciandoPartidaCantidadManoHorizontalJ1() {
        //return _iniciandoPartidaCantidadManoHorizontalJ1;
        return _datosGuardablesJSON.get_iniciandoPartidaCantidadManoHorizontalJ1();
    }

    public static void set_iniciandoPartidaCantidadManoHorizontalJ1(int iniciandoPartidaCantidadManoHorizontalJ1) {
        //_iniciandoPartidaCantidadManoHorizontalJ1 = iniciandoPartidaCantidadManoHorizontalJ1;
        _datosGuardablesJSON.set_iniciandoPartidaCantidadManoHorizontalJ1(iniciandoPartidaCantidadManoHorizontalJ1);
    }

    public static int get_iniciandoPartidaCantidadManoHorizontalJ2() {
        //return _iniciandoPartidaCantidadManoHorizontalJ2;
        return _datosGuardablesJSON.get_iniciandoPartidaCantidadManoHorizontalJ2();
    }

    public static void set_iniciandoPartidaCantidadManoHorizontalJ2(int iniciandoPartidaCantidadManoHorizontalJ2) {
        //_iniciandoPartidaCantidadManoHorizontalJ2 = iniciandoPartidaCantidadManoHorizontalJ2;
        _datosGuardablesJSON.set_iniciandoPartidaCantidadManoHorizontalJ2(iniciandoPartidaCantidadManoHorizontalJ2);
    }

    public static int get_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll() {
        //return _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll;
        return _datosGuardablesJSON.get_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll();
    }

    public static void set_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll(int iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll) {
        //_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll = iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll;
        _datosGuardablesJSON.set_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll(iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll);
    }

    public static int get_iniciandoJugador1Vida() {
        //return _iniciandoJugador1Vida;
        return _datosGuardablesJSON.get_iniciandoJugador1Vida();
    }

    public static void set_iniciandoJugador1Vida(int iniciandoJugador1Vida) {
        //_iniciandoJugador1Vida = iniciandoJugador1Vida;
        _datosGuardablesJSON.set_iniciandoJugador1Vida(iniciandoJugador1Vida);
    }

    public static int get_iniciandoJugador2Vida() {
        //return _iniciandoJugador2Vida;
        return _datosGuardablesJSON.get_iniciandoJugador2Vida();
    }

    public static void set_iniciandoJugador2Vida(int iniciandoJugador2Vida) {
        //_iniciandoJugador2Vida = iniciandoJugador2Vida;
        _datosGuardablesJSON.set_iniciandoJugador2Vida(iniciandoJugador2Vida);
    }

    public static int get_musicaFondoJugando() {
        //return _musicaFondoJugando;
        return _datosGuardablesJSON.get_musicaFondoJugando();
    }

    public static void set_musicaFondoJugando(int musicaFondo) {
        //_musicaFondoJugando = musicaFondo;
        _datosGuardablesJSON.set_musicaFondoJugando(musicaFondo);
    }

    public static int get_sonidoAtaqueMonstruo() {
        //return _sonidoAtaqueMonstruo;
        return _datosGuardablesJSON.get_sonidoAtaqueMonstruo();
    }

    public static void set_sonidoAtaqueMonstruo(int sonidoAtaqueMonstruo) {
        //_sonidoAtaqueMonstruo = sonidoAtaqueMonstruo;
        _datosGuardablesJSON.set_sonidoAtaqueMonstruo(sonidoAtaqueMonstruo);
    }

    public static String get_iniciandoJugador1Prefijo() {
        //return _iniciandoJugador1Prefijo;
        return _datosGuardablesJSON.get_iniciandoJugador1Prefijo();
    }

    public static void set_iniciandoJugador1Prefijo(String iniciandoJugador1Prefijo) {
        //_iniciandoJugador1Prefijo = iniciandoJugador1Prefijo;
        _datosGuardablesJSON.set_iniciandoJugador1Prefijo(iniciandoJugador1Prefijo);
    }

    public static String get_iniciandoJugador1Sufijo() {
        //return _iniciandoJugador1Sufijo;
        return _datosGuardablesJSON.get_iniciandoJugador1Sufijo();
    }

    public static void set_iniciandoJugador1Sufijo(String iniciandoJugador1Sufijo) {
        //_iniciandoJugador1Sufijo = iniciandoJugador1Sufijo;
        _datosGuardablesJSON.set_iniciandoJugador1Sufijo(iniciandoJugador1Sufijo);
    }

    public static String get_iniciandoJugador2Prefijo() {
        //return _iniciandoJugador2Prefijo;
        return _datosGuardablesJSON.get_iniciandoJugador2Prefijo();
    }

    public static void set_iniciandoJugador2Prefijo(String iniciandoJugador2Prefijo) {
        //_iniciandoJugador2Prefijo = iniciandoJugador2Prefijo;
        _datosGuardablesJSON.set_iniciandoJugador2Prefijo(iniciandoJugador2Prefijo);
    }

    public static String get_iniciandoJugador2Sufijo() {
        //return _iniciandoJugador2Sufijo;
        return _datosGuardablesJSON.get_iniciandoJugador2Sufijo();
    }

    public static void set_iniciandoJugador2Sufijo(String iniciandoJugador2Sufijo) {
        //_iniciandoJugador2Sufijo = iniciandoJugador2Sufijo;
        _datosGuardablesJSON.set_iniciandoJugador2Sufijo(iniciandoJugador2Sufijo);
    }

    public static List<HorizontalVista> get_horizontalesVista() {
        return _horizontalesVista;
    }

    public static void set_horizontalesVista(List<HorizontalVista> horizontalesVista) {
        _horizontalesVista = horizontalesVista;
    }

    public static List<Jugador> get_jugadores() {
        //return _jugadores;
        return _datosGuardablesJSON.get_jugadores();
    }

    public static void set_jugadores(List<Jugador> jugadores) {
        //_jugadores = jugadores;
        _datosGuardablesJSON.set_jugadores(jugadores);
    }

    public static DatosGuardablesJSON get_datosGuardablesJSON() {
        return _datosGuardablesJSON;
    }

    public static void set_datosGuardablesJSON(DatosGuardablesJSON datosGuardables) {
        _datosGuardablesJSON = datosGuardables;
    }



    // FIN GETTERS Y SETTERS
}
