package com.loeches.yugioh.Modelo.Cartas.Abstractas;

import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;

public abstract class AHechizo extends ACarta{
    private EAccionHechizo _accionHechizo;

    public AHechizo() {
        super();
    }

    public AHechizo(String nombre, String descripcion, int imagen, EAccionHechizo accionHechizo) {
        super(nombre, descripcion, imagen);
        _accionHechizo = accionHechizo;
    }

    public AHechizo(AHechizo copia) {
        super(copia.get_nombre(), copia.get_descripcion(), copia.get_imagen());
        _accionHechizo = copia._accionHechizo;
    }

    public EAccionHechizo get_accionHechizo() {
        return _accionHechizo;
    }

    @Override
    public String toString() {
        return "Tipo: "+this.getClass().getSimpleName()+" | "+super.toString()+"AccionHechizo: " + _accionHechizo + " | ";
    }
}
