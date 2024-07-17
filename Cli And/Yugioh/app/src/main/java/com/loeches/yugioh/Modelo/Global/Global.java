package com.loeches.yugioh.Modelo.Global;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import com.loeches.yugioh.DAO.Implementaciones.DatosGuardablesJSON;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.InterfazVista.CartaVista;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.InterfazVista.HorizontalVista;
import com.loeches.yugioh.R;

import java.util.ArrayList;
import java.util.List;

public class Global {
    // ATRIBUTOS
    public final static int POS_ERROR = -1; // SI SE BUSCA RECIBIR UNA POSICIÓN DE UNA LISTA O ARREGLO Y NO SE ENCUENTRA O MANEJAR ALGÚN ERROR RELACIONADO
    public final static String NOMBRE_ARCHIVO_GUARDAR_DATOS_1DISPOSITIVO = "datosGuardados", NOMBRE_ARCHIVO_GUARDAR_DATOS_RED = "datosGuardadosRed";
    private static Activity _activity; // RECIBE LA VistaActivity (ÚNICA ACTIVITY), PARA QUE SEA ACCESIBLE EN EL PROGRAMA, JUNTO AL CONTEXTO Y EL CONTENEDOR PRINCIPAL QUE ES UN LINEAR LAYOUT VERTICAL
    //private static boolean _turnoJugador1, _turnoAlAzar,_modoOptimoJugando, _preguntarConfirmacionAccionesJugando, _empezarPartidaNueva, _guardarDatosEnArchivo;// SI _modoOptimoJugando ES TRUE LLEVA AUTOMÁTICAMENTE UN MONSTRUO/HECHIZO EQUIPABLE AL 1º ESPACIO DISPONIBLE, SI _modoOptimoJugando VALE FALSE TENDRÍAS QUE ELEGIR MANUALMENTE LA CARTA VACÍA DESTINO.
    private static CartaVista _cartaVistaSeleccionada;// ES LA CARTA AL QUE EL JUGADOR HIZO CLICK Y TIENE EL BORDE EN ROJO
    private static List<HorizontalVista> _horizontalesVista;// Los linear layout horizontales que contienen las cartas y sus datos
    public static DatosGuardablesJSON _datosGuardablesJSON1Dispositivo, _datosGuardablesJSONRed;

    public static String jugador2;

// FIN ATRIBUTOS

    // MÉTODOS

    public static void restaurarValoresDefecto() {
        _cartaVistaSeleccionada = null;
        _horizontalesVista = new ArrayList<>();
        _datosGuardablesJSON1Dispositivo = new DatosGuardablesJSON(NOMBRE_ARCHIVO_GUARDAR_DATOS_1DISPOSITIVO);
        _datosGuardablesJSONRed = new DatosGuardablesJSON(NOMBRE_ARCHIVO_GUARDAR_DATOS_RED);
        _datosGuardablesJSONRed.restaurarApodosPorDefectoRed();
    }


    public static LinearLayout get_linearMain() {
        return _activity.findViewById(R.id.main);
    }

    public static Context get_context() {
        return _activity.getApplicationContext();
    }

    public static HorizontalVista getBy(EIdHorizontalVista id) {
        for (HorizontalVista hv : _horizontalesVista) {
            if (hv.get_id() == id) {
                return hv;
            }
        }
        return null;
    }

    public static int getIndexHorizontalVista(CartaVista cv) {
        List<CartaVista> cvs = cv.get_horizontalVista().get_cartasVista();
        for (int i = 0; i < cvs.size(); i++) {
            if (cv.equals(cvs.get(i))) {
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
        return _datosGuardablesJSON1Dispositivo.is_turnoJugador1();
    }

    public static void set_turnoJugador1(boolean esTurnoJugador1) {
        //_turnoJugador1 = esTurnoJugador1;
        _datosGuardablesJSON1Dispositivo.set_turnoJugador1(esTurnoJugador1);
    }

    public static boolean is_modoOptimoJugando() {
        //return _modoOptimoJugando;
        return _datosGuardablesJSON1Dispositivo.is_modoOptimoJugando();
    }

    public static void set_modoOptimoJugando(boolean modoOptimoJugando) {
        //_modoOptimoJugando = modoOptimoJugando;
        _datosGuardablesJSON1Dispositivo.set_modoOptimoJugando(modoOptimoJugando);
    }

    public static boolean is_preguntarConfirmacionAccionesJugando() {
        //return _preguntarConfirmacionAccionesJugando;
        return _datosGuardablesJSON1Dispositivo.is_preguntarConfirmacionAccionesJugando();
    }

    public static void set_preguntarConfirmacionAccionesJugando(boolean preguntarConfirmacionAccionesJugando) {
        //_preguntarConfirmacionAccionesJugando = preguntarConfirmacionAccionesJugando;
        _datosGuardablesJSON1Dispositivo.set_preguntarConfirmacionAccionesJugando(preguntarConfirmacionAccionesJugando);
    }

    public static boolean is_empezarPartidaNueva() {
        //return _empezarPartidaNueva;
        return _datosGuardablesJSON1Dispositivo.is_empezarPartidaNueva();
    }

    public static void set_empezarPartidaNueva(boolean empezarPartidaNueva) {
        //_empezarPartidaNueva = empezarPartidaNueva;
        _datosGuardablesJSON1Dispositivo.set_empezarPartidaNueva(empezarPartidaNueva);
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
        return _datosGuardablesJSON1Dispositivo.get_iniciandoPartidaCantidadMonstruosHorizontalJ1();
    }

    public static void set_iniciandoPartidaCantidadMonstruosHorizontalJ1(int iniciandoPartidaCantidadMonstruosHorizontalJ1) {
        //_iniciandoPartidaCantidadMonstruosHorizontalJ1 = iniciandoPartidaCantidadMonstruosHorizontalJ1;
        _datosGuardablesJSON1Dispositivo.set_iniciandoPartidaCantidadMonstruosHorizontalJ1(iniciandoPartidaCantidadMonstruosHorizontalJ1);
    }

    public static int get_iniciandoPartidaCantidadMonstruosHorizontalJ2() {
        //return _iniciandoPartidaCantidadMonstruosHorizontalJ2;
        return _datosGuardablesJSON1Dispositivo.get_iniciandoPartidaCantidadMonstruosHorizontalJ2();
    }

    public static void set_iniciandoPartidaCantidadMonstruosHorizontalJ2(int iniciandoPartidaCantidadMonstruosHorizontalJ2) {
        //_iniciandoPartidaCantidadMonstruosHorizontalJ2 = iniciandoPartidaCantidadMonstruosHorizontalJ2;
        _datosGuardablesJSON1Dispositivo.set_iniciandoPartidaCantidadMonstruosHorizontalJ2(iniciandoPartidaCantidadMonstruosHorizontalJ2);
    }

    public static int get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1() {
        //return _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1;
        return _datosGuardablesJSON1Dispositivo.get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1();
    }

    public static void set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1(int iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1) {
        //_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1 = iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1;
        _datosGuardablesJSON1Dispositivo.set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1(iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1);
    }

    public static int get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2() {
        //return _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2;
        return _datosGuardablesJSON1Dispositivo.get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2();
    }

    public static void set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2(int iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2) {
        //_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2 = iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2;
        _datosGuardablesJSON1Dispositivo.set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2(iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2);
    }

    public static int get_iniciandoPartidaCantidadManoHorizontalJ1() {
        //return _iniciandoPartidaCantidadManoHorizontalJ1;
        return _datosGuardablesJSON1Dispositivo.get_iniciandoPartidaCantidadManoHorizontalJ1();
    }

    public static void set_iniciandoPartidaCantidadManoHorizontalJ1(int iniciandoPartidaCantidadManoHorizontalJ1) {
        //_iniciandoPartidaCantidadManoHorizontalJ1 = iniciandoPartidaCantidadManoHorizontalJ1;
        _datosGuardablesJSON1Dispositivo.set_iniciandoPartidaCantidadManoHorizontalJ1(iniciandoPartidaCantidadManoHorizontalJ1);
    }

    public static int get_iniciandoPartidaCantidadManoHorizontalJ2() {
        //return _iniciandoPartidaCantidadManoHorizontalJ2;
        return _datosGuardablesJSON1Dispositivo.get_iniciandoPartidaCantidadManoHorizontalJ2();
    }

    public static void set_iniciandoPartidaCantidadManoHorizontalJ2(int iniciandoPartidaCantidadManoHorizontalJ2) {
        //_iniciandoPartidaCantidadManoHorizontalJ2 = iniciandoPartidaCantidadManoHorizontalJ2;
        _datosGuardablesJSON1Dispositivo.set_iniciandoPartidaCantidadManoHorizontalJ2(iniciandoPartidaCantidadManoHorizontalJ2);
    }

    public static int get_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll() {
        //return _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll;
        return _datosGuardablesJSON1Dispositivo.get_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll();
    }

    public static void set_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll(int iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll) {
        //_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll = iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll;
        _datosGuardablesJSON1Dispositivo.set_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll(iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll);
    }

    public static int get_iniciandoJugador1Vida() {
        //return _iniciandoJugador1Vida;
        return _datosGuardablesJSON1Dispositivo.get_iniciandoJugador1Vida();
    }

    public static void set_iniciandoJugador1Vida(int iniciandoJugador1Vida) {
        //_iniciandoJugador1Vida = iniciandoJugador1Vida;
        _datosGuardablesJSON1Dispositivo.set_iniciandoJugador1Vida(iniciandoJugador1Vida);
    }

    public static int get_iniciandoJugador2Vida() {
        //return _iniciandoJugador2Vida;
        return _datosGuardablesJSON1Dispositivo.get_iniciandoJugador2Vida();
    }

    public static void set_iniciandoJugador2Vida(int iniciandoJugador2Vida) {
        //_iniciandoJugador2Vida = iniciandoJugador2Vida;
        _datosGuardablesJSON1Dispositivo.set_iniciandoJugador2Vida(iniciandoJugador2Vida);
    }

    public static int get_musicaFondoJugando() {
        //return _musicaFondoJugando;
        return _datosGuardablesJSON1Dispositivo.get_musicaFondoJugando();
    }

    public static void set_musicaFondoJugando(int musicaFondo) {
        //_musicaFondoJugando = musicaFondo;
        _datosGuardablesJSON1Dispositivo.set_musicaFondoJugando(musicaFondo);
    }

    public static int get_sonidoAtaqueMonstruo() {
        //return _sonidoAtaqueMonstruo;
        return _datosGuardablesJSON1Dispositivo.get_sonidoAtaqueMonstruo();
    }

    public static void set_sonidoAtaqueMonstruo(int sonidoAtaqueMonstruo) {
        //_sonidoAtaqueMonstruo = sonidoAtaqueMonstruo;
        _datosGuardablesJSON1Dispositivo.set_sonidoAtaqueMonstruo(sonidoAtaqueMonstruo);
    }

    public static List<HorizontalVista> get_horizontalesVista() {
        return _horizontalesVista;
    }

    public static void set_horizontalesVista(List<HorizontalVista> horizontalesVista) {
        _horizontalesVista = horizontalesVista;
    }

    public static List<Jugador> get_jugadores() {
        //return _jugadores;
        return _datosGuardablesJSON1Dispositivo.get_jugadores();
    }

    public static void set_jugadores(List<Jugador> jugadores) {
        //_jugadores = jugadores;
        _datosGuardablesJSON1Dispositivo.set_jugadores(jugadores);
    }

    public static DatosGuardablesJSON get_datosGuardablesJSON1Dispositivo() {
        return _datosGuardablesJSON1Dispositivo;
    }

    public static void set_datosGuardablesJSON1Dispositivo(DatosGuardablesJSON datosGuardables) {
        _datosGuardablesJSON1Dispositivo = datosGuardables;
    }

    public static DatosGuardablesJSON get_datosGuardablesJSONRed() {
        return _datosGuardablesJSONRed;
    }

    public static void set_datosGuardablesJSONRed(DatosGuardablesJSON _datosGuardablesJSONRed) {
        Global._datosGuardablesJSONRed = _datosGuardablesJSONRed;
    }

    // FIN GETTERS Y SETTERS
}
