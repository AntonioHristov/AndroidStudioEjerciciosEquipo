package com.loeches.yugioh.Modelo;

import android.widget.TextView;

import com.loeches.yugioh.Modelo.Global.Lista;

public class Jugador {
    private TextView _tvVista;
    private String _textoPrefijoMostrarVida;
    private int _vida;



    public Jugador(TextView tvVista,String textoMostrarVida,int vida) {
        _tvVista=tvVista;
        _textoPrefijoMostrarVida =textoMostrarVida;
        _vida = vida;
        Lista.getJugadores().add(this);
    }
    public Jugador(TextView tvVista,String textoMostrarVida) {
        _tvVista=tvVista;
        _textoPrefijoMostrarVida =textoMostrarVida;
        set_vida(8000);
        Lista.getJugadores().add(this);
    }

    public Jugador(TextView tvVista) {
        _tvVista=tvVista;
        _textoPrefijoMostrarVida ="";
        set_vida(8000);
        Lista.getJugadores().add(this);
    }

    public TextView get_tvVista() {
        return _tvVista;
    }

    public String get_textoPrefijoMostrarVida() {
        return _textoPrefijoMostrarVida;
    }

    public void set_textoPrefijoMostrarVida(String textoMostrarVida) {
        _textoPrefijoMostrarVida = textoMostrarVida;
    }

    public int get_vida() {
        return _vida;
    }

    public void set_vida(int vida) {
        _vida = vida;
        _tvVista.setText(_textoPrefijoMostrarVida+_vida);
    }


}
