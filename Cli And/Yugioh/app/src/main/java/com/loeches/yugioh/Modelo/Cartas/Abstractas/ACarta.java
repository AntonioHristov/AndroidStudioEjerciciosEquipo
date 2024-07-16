package com.loeches.yugioh.Modelo.Cartas.Abstractas;

import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.InterfazVista.CartaVista;

import java.io.Serializable;
import java.util.Objects;

public abstract class ACarta {
    private String _nombre, _descripcion;
    private int _imagen;
    private boolean _nuevoTurnoTrasRealizarAccion;
    private EIdHorizontalVista _idHorizontalVista; // Tipo Enum



    //PARA GUARDAR EN JSON
    public ACarta() {

    }

    public ACarta(String nombre, String descripcion, int imagen) {
        _nombre = nombre;
        _descripcion = descripcion;
        _imagen = imagen;
        _nuevoTurnoTrasRealizarAccion=true;
    }

    public ACarta(String nombre, String descripcion, int imagen, EIdHorizontalVista idHorizontalVista) {
        _nombre = nombre;
        _descripcion = descripcion;
        _imagen = imagen;
        _idHorizontalVista=idHorizontalVista;
        _nuevoTurnoTrasRealizarAccion=true;
    }

    public ACarta(String nombre, String descripcion, int imagen, EIdHorizontalVista idHorizontalVista, boolean nuevoTurnoTrasRealizarAccion) {
        _nombre = nombre;
        _descripcion = descripcion;
        _imagen = imagen;
        _idHorizontalVista=idHorizontalVista;
        _nuevoTurnoTrasRealizarAccion=nuevoTurnoTrasRealizarAccion;
    }

    public ACarta(ACarta copia){
        _nombre=copia._nombre;
        _descripcion=copia._descripcion;
        _imagen=copia._imagen;
        _nuevoTurnoTrasRealizarAccion=copia._nuevoTurnoTrasRealizarAccion;
        _idHorizontalVista = copia._idHorizontalVista;
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

    public EIdHorizontalVista get_idHorizontalVista() {
        return _idHorizontalVista;
    }

    public void set_idHorizontalVista(EIdHorizontalVista idHorizontalVista) {
        _idHorizontalVista = idHorizontalVista;
    }


    public CartaVista get_cartaVista() {
        for (CartaVista cv:Global.getBy(_idHorizontalVista).get_cartasVista()) {
            if(cv.get_carta()==this){
                return cv;
            }
        }
        return null;
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
                "Descripción: " + _descripcion + " | "+
                "Id Horizontal: " + _idHorizontalVista.toString() + " | "+
                "Nuevo turno tras accion: " + _nuevoTurnoTrasRealizarAccion + " | ";
    }
}
