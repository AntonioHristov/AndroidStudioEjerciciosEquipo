package com.loeches.yugioh.Modelo.Global;

import static com.loeches.yugioh.Controlador.Controlador.EscribirXmlDivider;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import com.loeches.yugioh.Controlador.Controlador;
import com.loeches.yugioh.Modelo.Vista.CartaVista;
import com.loeches.yugioh.R;

public class Variables {
    private static boolean _turnoJugador1;
    private static Context _gameActivityContext;
    private static CartaVista _cartaVistaSeleccionada;

    public static void nuevoTurno(){
        set_turnoJugador1(!_turnoJugador1);
        Controlador.ActualizarVistaCartas();
    }

    public static boolean is_turnoJugador1() {
        return _turnoJugador1;
    }

    public static void set_turnoJugador1(boolean esTurnoJugador1) {
        _turnoJugador1 = esTurnoJugador1;
    }

    public static Context get_gameActivityContext() {
        return _gameActivityContext;
    }

    public static void set_gameActivityContext(Context _gameActivityContext) {
        Variables._gameActivityContext = _gameActivityContext;
    }

    public static CartaVista get_cartaVistaSeleccionada() {
        return _cartaVistaSeleccionada;
    }

    public static void set_cartaVistaSeleccionada(CartaVista _cartaVistaSeleccionada) {
        Variables._cartaVistaSeleccionada = _cartaVistaSeleccionada;
    }
}
