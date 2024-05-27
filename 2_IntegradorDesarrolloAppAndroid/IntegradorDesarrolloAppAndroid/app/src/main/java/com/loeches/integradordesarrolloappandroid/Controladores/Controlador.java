package com.loeches.integradordesarrolloappandroid.Controladores;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;

import com.loeches.integradordesarrolloappandroid.Modelos.Modelo;
import com.loeches.integradordesarrolloappandroid.Vistas.MainActivity;

public class Controlador {

    public static void InicializarValoresDefecto() {
        Modelo.setNumeroAleatorioGenerado(1,100);
        Modelo.setIntentosJugador(0);
        Modelo.setLimiteIntentos(8);
    }

    public static void nuevoIntento(Context context, int numeroIntentadoPorJugador, ImageView ivUp, ImageView ivDown) {
        Modelo.setUltimoNumeroIntentadoPorJugador(numeroIntentadoPorJugador);
        Modelo.setIntentosJugador(Modelo.getIntentosJugador()+1);
        ActualizarVista(ivUp,ivDown);
    }

    public static void setColorImagen(ImageView iv, int color) {
        //color = Color.parseColor("#FF0000"); //ROJO
        iv.setColorFilter(color);
    }

    public static void ActualizarVista(ImageView ivUp, ImageView ivDown) {
        // AQUI SE CAMBIA LAS IMÁGENES DE LAS FLECHAS OPACO O BRILLANTE
        if (Modelo.getUltimoNumeroIntentadoPorJugador() < Modelo.getNumeroAleatorioGenerado()) {
            /*
            imagenFlechaArriba = "Imagen Flecha Arriba Color Azul Brillante (EL NÚMERO GENERADO ES MAYOR)";
            imagenFlechaAbajo = "Imagen Flecha Abajo Color Rojo Opaco (EL NÚMERO GENERADO ES MAYOR)";*/
            setColorImagen(ivUp, Color.parseColor("#0000FF"));
            setColorImagen(ivDown, Color.parseColor("#FF8C9E"));
        } else /*if (Modelo.getUltimoNumeroIntentadoPorJugador() > Modelo.getNumeroAleatorioGenerado())*/{
            /*
            imagenFlechaArriba = "Imagen Flecha Arriba Color Azul Opaco (EL NÚMERO GENERADO ES MENOR)";
            imagenFlechaAbajo = "Imagen Flecha Abajo Color Rojo Brillante (EL NÚMERO GENERADO ES MENOR)";*/
            setColorImagen(ivUp, Color.parseColor("#96FFFF"));
            setColorImagen(ivDown, Color.parseColor("#FF0000"));
        }
    }

    public static void PruebaIV(ImageView ivUp, ImageView ivDown){
        Modelo.setIntentosJugador(0);
        setColorImagen(ivUp, Color.parseColor("#0000FF"));
        setColorImagen(ivDown, Color.parseColor("#FF0000"));
    }
}
