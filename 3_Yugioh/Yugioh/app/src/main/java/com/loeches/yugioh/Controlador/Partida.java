package com.loeches.yugioh.Controlador;

import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Cartas.Global.Lista;

public class Partida {
    public static void NuevaPartida(){
        CrearCartasUnicas();
    }

    public static void CrearCartasUnicas(){
        CrearMonstruos();
        CrearHechizos();
    }

    public static void CrearMonstruos(){
        // 1º CREAR .java EN Modelo/Cartas/Ejemplares/Monstruos. (PARA SOBREESCRIBIR AHÍ EL MÉTODO ABSTRACTO RealizarAccion(ACarta posibleObjetivo))
    }

    public static void CrearHechizos(){
        // 1º CREAR .java EN Modelo/Cartas/Ejemplares/Hechizos. (PARA SOBREESCRIBIR AHÍ EL MÉTODO ABSTRACTO RealizarAccion(ACarta posibleObjetivo))
    }

    public static void InicializarCartasInicioPartida(){
        for (int i = 0; i < Lista.CANTIDAD_MONSTRUOS_ELEGIDOS; i++) {
            Lista.get_jugador1MonstruosElegidos()[i]=new CartaVacia();
            Lista.get_jugador2MonstruosElegidos()[i]=new CartaVacia();
        }
        for (int i = 0; i < Lista.CANTIDAD_HECHIZOS_ELEGIDOS; i++) {
            Lista.get_jugador1HechizosElegidos()[i]=new CartaVacia();
            Lista.get_jugador2HechizosElegidos()[i]=new CartaVacia();
        }

        // GENERAR (IMAGINO QUE 5) CARTAS NO VACIAS ALEATORIAS A:
        // Lista.get_jugador1CartasAbajo()
        // Lista.get_jugador2CartasAbajo()


    }

    public static void ActualizarVista(){

    }
}
