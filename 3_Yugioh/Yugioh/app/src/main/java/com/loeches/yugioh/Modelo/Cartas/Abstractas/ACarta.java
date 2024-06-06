package com.loeches.yugioh.Modelo.Cartas.Abstractas;

import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Vista.CartaVista;

import java.util.Objects;

public abstract class ACarta {
    private String _nombre, _descripcion;
    private int _imagen;
    private boolean _nuevoTurnoTrasRealizarAccion;
    private CartaVista _cartaVista;

    public ACarta(String nombre, String descripcion, int imagen) {
        _nombre = nombre;
        _descripcion = descripcion;
        _imagen = imagen;
        _nuevoTurnoTrasRealizarAccion=true;
        _cartaVista=null;
    }

    public ACarta(ACarta copia){
        _nombre=copia._nombre;
        _descripcion=copia._descripcion;
        _imagen=copia._imagen;
        _nuevoTurnoTrasRealizarAccion=true;
    }

    public abstract void RealizarAccion(AMonstruo posibleObjetivo);

    public String get_nombre() {
        return _nombre;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public int get_imagen() {
        return _imagen;
    }

    public boolean is_nuevoTurnoTrasRealizarAccion() {
        return _nuevoTurnoTrasRealizarAccion;
    }

    public void set_nuevoTurnoTrasRealizarAccion(boolean _nuevoTurnoTrasRealizarAccion) {
        this._nuevoTurnoTrasRealizarAccion = _nuevoTurnoTrasRealizarAccion;
    }

    public CartaVista get_cartaVista() {
        return _cartaVista;
    }

    public void set_cartaVista(CartaVista cartaVista) {
        _cartaVista = cartaVista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ACarta aCarta = (ACarta) o;
        return _imagen == aCarta._imagen && Objects.equals(_nombre, aCarta._nombre) && Objects.equals(_descripcion, aCarta._descripcion);
    }

    @Override
    public String toString() {
        return "Nombre: " + _nombre + " | " +
                "Descripción: " + _descripcion + " | ";
    }
}
