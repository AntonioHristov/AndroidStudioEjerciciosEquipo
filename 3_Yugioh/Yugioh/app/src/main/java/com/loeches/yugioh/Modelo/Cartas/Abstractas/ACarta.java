package com.loeches.yugioh.Modelo.Cartas.Abstractas;

import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Cartas.Global.Lista;

import java.util.Objects;

public abstract class ACarta {
    private String _nombre, _descripcion;
    private int _imagen;

    public ACarta(String nombre, String descripcion, int imagen) {
        _nombre = nombre;
        _descripcion = descripcion;
        _imagen = imagen;

        boolean seRepite=false;
        for (ACarta carta:Lista.get_cartasUnicas()) {
            if(this.equals(carta)){
                seRepite=true;
                break;
            }
        }
        if(!seRepite && !(this instanceof CartaVacia)){
            Lista.get_cartasUnicas().add(this);
        }
    }

    public abstract void RealizarAccion(ACarta posibleObjetivo);

    public String get_nombre() {
        return _nombre;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public int get_imagen() {
        return _imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ACarta aCarta = (ACarta) o;
        return _imagen == aCarta._imagen && Objects.equals(_nombre, aCarta._nombre) && Objects.equals(_descripcion, aCarta._descripcion);
    }
}
