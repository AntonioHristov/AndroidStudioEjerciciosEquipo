package com.loeches.yugioh.Modelo.Cartas.Abstractas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.loeches.yugioh.Modelo.Global.Variables;

public abstract class AMonstruo extends ACarta{
    private int _ataqueBase, _defensaBase, _ataque,_defensa, _turnosAumentoAtaque, _turnosAumentoDefensa;
    private boolean _modoDefensa;
    private boolean _llevaEspejoDragon;


    public AMonstruo(String nombre, String descripcion, int imagen, int ataque, int defensa) {
        super(nombre, descripcion, imagen);
        _ataqueBase=ataque;
        _defensaBase=defensa;
        _ataque = ataque;
        _defensa = defensa;
        _turnosAumentoAtaque=0;
        _turnosAumentoDefensa=0;
        _modoDefensa = false;
        _llevaEspejoDragon=false;
    }

    public AMonstruo(String nombre, String descripcion, int imagen, int ataque, int defensa, boolean modoDefensa) {
        super(nombre, descripcion, imagen);
        _ataqueBase=ataque;
        _defensaBase=defensa;
        _ataque = ataque;
        _defensa = defensa;
        _turnosAumentoAtaque=0;
        _turnosAumentoDefensa=0;
        _modoDefensa = modoDefensa;
        _llevaEspejoDragon=false;
    }

    public AMonstruo(AMonstruo copia) {
        super(copia.get_nombre(), copia.get_descripcion(), copia.get_imagen());
        _ataqueBase=copia._ataqueBase;
        _defensaBase=copia._defensaBase;
        _ataque = copia._ataque;
        _defensa = copia._defensa;
        _turnosAumentoAtaque=copia._turnosAumentoAtaque;
        _turnosAumentoDefensa=copia._turnosAumentoDefensa;
        _modoDefensa = copia._modoDefensa;
        _llevaEspejoDragon = copia._llevaEspejoDragon;
    }

    public void NuevoTurno(){
        if(_turnosAumentoAtaque>0){
            _turnosAumentoAtaque--;
            if(_turnosAumentoAtaque<=0){
                _ataque=_ataqueBase;
                _turnosAumentoAtaque=0;
            }
        }
        if(_turnosAumentoDefensa>0){
            _turnosAumentoDefensa--;
            if(_turnosAumentoDefensa<=0){
                _defensa=_defensaBase;
                _turnosAumentoDefensa=0;
            }
        }
    }

    public int get_ataqueBase() {
        return _ataqueBase;
    }

    public int get_defensaBase() {
        return _defensaBase;
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

    public int get_turnosAumentoAtaque() {
        return _turnosAumentoAtaque;
    }

    public void set_turnosAumentoAtaque(int turnosAumento) {
        _turnosAumentoAtaque = turnosAumento;
    }

    public int get_turnosAumentoDefensa() {
        return _turnosAumentoDefensa;
    }

    public void set_turnosAumentoDefensa(int turnosAumentoDefensa) {
        _turnosAumentoDefensa = turnosAumentoDefensa;
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

    public boolean is_llevaEspejoDragon() {
        return _llevaEspejoDragon;
    }

    public void set_llevaEspejoDragon(boolean llevaEspejoDragon) {
        _llevaEspejoDragon = llevaEspejoDragon;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Ataque: " + _ataque + " | " +
                "Defensa: " + _defensa + " | " +
                (_modoDefensa?"ModoDefensa | ":"ModoAtaque | ")
                +(_turnosAumentoAtaque>0?"AumentoAtaque: "+_turnosAumentoAtaque+ " | ":"")
                +(_turnosAumentoDefensa>0?"AumentoDefensa: "+_turnosAumentoDefensa+ " | ":"")
                +(_llevaEspejoDragon?"Lleva Espejo Dragón (Ganas en vida el daño dado) | ":"")
                ;}
}
