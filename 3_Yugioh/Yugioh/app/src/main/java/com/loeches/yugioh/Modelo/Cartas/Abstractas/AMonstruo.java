package com.loeches.yugioh.Modelo.Cartas.Abstractas;

public abstract class AMonstruo extends ACarta{
    private int _danio,_defensa, /*SUGERENCIAS DE ANTONIO*/_vida, _ataque;
    private boolean _modoDefensa;


    public AMonstruo(String nombre, String descripcion, int imagen, int danio, int defensa, int vida, int ataque) {
        super(nombre, descripcion, imagen);
        _danio = danio;
        _defensa = defensa;
        _vida = vida;
        _ataque = ataque;
        _modoDefensa = false;
    }

    public AMonstruo(String nombre, String descripcion, int imagen, int danio, int defensa, int vida, int ataque, boolean modoDefensa) {
        super(nombre, descripcion, imagen);
        _danio = danio;
        _defensa = defensa;
        _vida = vida;
        _ataque = ataque;
        _modoDefensa = modoDefensa;
    }



    public int get_danio() {
        return _danio;
    }

    public void set_danio(int danio) {
        _danio = danio;
    }

    public int get_defensa() {
        return _defensa;
    }

    public void set_defensa(int defensa) {
        _defensa = defensa;
    }

    public int get_vida() {
        return _vida;
    }

    public void set_vida(int vida) {
        _vida = vida;
    }

    public int get_ataque() {
        return _ataque;
    }

    public void set_ataque(int ataque) {
        _ataque = ataque;
    }

    public boolean is_modoDefensa() {
        return _modoDefensa;
    }

    public void set_modoDefensa(boolean modoDefensa) {
        _modoDefensa = modoDefensa;
    }
}
