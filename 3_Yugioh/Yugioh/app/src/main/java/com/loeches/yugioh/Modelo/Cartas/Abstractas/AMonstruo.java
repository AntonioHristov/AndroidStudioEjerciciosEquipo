package com.loeches.yugioh.Modelo.Cartas.Abstractas;

public abstract class AMonstruo extends ACarta{
    private int _ataque,_defensa;
    private boolean _modoDefensa;


    public AMonstruo(String nombre, String descripcion, int imagen, int ataque, int defensa) {
        super(nombre, descripcion, imagen);
        _ataque = ataque;
        _defensa = defensa;
        _modoDefensa = false;
    }

    public AMonstruo(String nombre, String descripcion, int imagen, int ataque, int defensa, boolean modoDefensa) {
        super(nombre, descripcion, imagen);
        _ataque = ataque;
        _defensa = defensa;
        _modoDefensa = modoDefensa;
    }

    public AMonstruo(AMonstruo copia) {
        super(copia.get_nombre(), copia.get_descripcion(), copia.get_imagen());
        _ataque = copia._ataque;
        _defensa = copia._defensa;
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

    @Override
    public String toString() {
        return super.toString()+
                "Ataque: " + _ataque + " | " +
                "Defensa: " + _defensa + " | " +
                "ModoDefensa: " + _modoDefensa + " | ";
    }
}
