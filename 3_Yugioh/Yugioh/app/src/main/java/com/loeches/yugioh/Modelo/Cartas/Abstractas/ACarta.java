package com.loeches.yugioh.Modelo.Cartas.Abstractas;

import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.InterfazVista.CartaVista;

import java.io.Serializable;
import java.util.Objects;

// SERIALIZABLE PARA GUARDAR Y CARGAR JSON
public abstract class ACarta implements Serializable {
    private String _nombre, _descripcion;
    private int _imagen;
    private boolean _nuevoTurnoTrasRealizarAccion;
    private EIdHorizontalVista _idHorizontalVista; // Enum Type
    //private CartaVista _cartaVista;



    //PARA GUARDAR EN JSON
    public ACarta() {

    }

    public ACarta(String nombre, String descripcion, int imagen) {
        _nombre = nombre;
        _descripcion = descripcion;
        _imagen = imagen;
        _nuevoTurnoTrasRealizarAccion=true;
        //_cartaVista=null;
    }

    public ACarta(String nombre, String descripcion, int imagen, EIdHorizontalVista idHorizontalVista) {
        _nombre = nombre;
        _descripcion = descripcion;
        _imagen = imagen;
        _idHorizontalVista=idHorizontalVista;
        //Global.get_datosGuardablesJSON().get_cartas().add(this);
        _nuevoTurnoTrasRealizarAccion=true;
        //_cartaVista=null;
    }

    public ACarta(String nombre, String descripcion, int imagen, EIdHorizontalVista idHorizontalVista, boolean nuevoTurnoTrasRealizarAccion) {
        _nombre = nombre;
        _descripcion = descripcion;
        _imagen = imagen;
        _idHorizontalVista=idHorizontalVista;
        //Global.get_datosGuardablesJSON().get_cartas().add(this);
        _nuevoTurnoTrasRealizarAccion=nuevoTurnoTrasRealizarAccion;
        //_cartaVista=null;
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

        /*
        boolean encontrado=false;
        for (ACarta carta:Global.get_datosGuardablesJSON().get_cartas()) {
            if (carta==this)
            {
                encontrado=true;
                break;
                //return;
            }
        }
        if(!encontrado){
            Global.get_datosGuardablesJSON().get_cartas().add(this);
        }*/

    }


    public CartaVista get_cartaVista() {
        for (CartaVista cv:Global.getBy(_idHorizontalVista).get_cartasVista()) {
            if(cv.get_carta()==this){
                return cv;
            }
        }
        return null;
    }


    /*
    public CartaVista get_cartaVista() {
        return _cartaVista;
    }

    public void set_cartaVista(CartaVista cartaVista) {
        _cartaVista = cartaVista;
    }*/

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
