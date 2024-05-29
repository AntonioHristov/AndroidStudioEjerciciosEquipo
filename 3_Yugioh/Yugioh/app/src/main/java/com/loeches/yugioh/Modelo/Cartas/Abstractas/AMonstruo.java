package com.loeches.yugioh.Modelo.Cartas.Abstractas;

public abstract class AMonstruo extends ACarta{
    private int _ataque,_defensa, /*SUGERENCIAS DE ANTONIO*/_vida;
    private boolean _modoDefensa;


    public AMonstruo(String nombre, String descripcion, int imagen, int danio, int defensa, int vida, int ataque) {
        super(nombre, descripcion, imagen);
        _ataque = danio;
        _defensa = defensa;
        _vida = vida;
        _modoDefensa = false;
    }

    public AMonstruo(String nombre, String descripcion, int imagen, int danio, int defensa, int vida, int ataque, boolean modoDefensa) {
        super(nombre, descripcion, imagen);
        _ataque = danio;
        _defensa = defensa;
        _vida = vida;
        _modoDefensa = modoDefensa;
    }

    public AMonstruo(AMonstruo copia) {
        super(copia.get_nombre(), copia.get_descripcion(), copia.get_imagen());
        _ataque = copia._ataque;
        _defensa = copia._defensa;
        _vida = copia._vida;
        _modoDefensa = copia._modoDefensa;
    }



    public int get_ataque() {
        return _ataque;
    }

    public void set_ataque(int danio) {
        _ataque = danio;
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

    public boolean is_modoDefensa() {
        return _modoDefensa;
    }

    public void set_modoDefensa(boolean modoDefensa) {
        _modoDefensa = modoDefensa;
        if(_modoDefensa){
            get_cartaVista().get_imageView().setRotation(90);
        }else{
            get_cartaVista().get_imageView().setRotation(0);
        }
    }
}
