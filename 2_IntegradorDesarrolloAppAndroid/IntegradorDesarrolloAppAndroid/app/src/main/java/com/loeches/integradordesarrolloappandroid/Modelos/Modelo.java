package com.loeches.integradordesarrolloappandroid.Modelos;

import android.graphics.Color;
import android.widget.ImageView;

import java.util.Random;

public class Modelo {
    private static int numeroAleatorioGenerado,intentosJugador, limiteIntentos, ultimoNumeroIntentadoPorJugador;

    public static int getNumeroAleatorioGenerado() {
        return numeroAleatorioGenerado;
    }
    public static void setNumeroAleatorioGenerado(int minRandom,int maxRandom) {
        numeroAleatorioGenerado=new Random().nextInt(maxRandom-minRandom+1)+minRandom; // 1 AL 100 INCLUIDOS
    }

    public static int getIntentosJugador() {
        return intentosJugador;
    }

    public static void setIntentosJugador(int intentosJugador) {
        Modelo.intentosJugador = intentosJugador;
    }

    public static int getLimiteIntentos() {
        return limiteIntentos;
    }

    public static void setLimiteIntentos(int limiteIntentos) {
        Modelo.limiteIntentos = limiteIntentos;
    }

    public static int getUltimoNumeroIntentadoPorJugador() {
        return ultimoNumeroIntentadoPorJugador;
    }

    public static void setUltimoNumeroIntentadoPorJugador(int ultimoNumeroIntentadoPorJugador) {
        Modelo.ultimoNumeroIntentadoPorJugador = ultimoNumeroIntentadoPorJugador;
    }


}
