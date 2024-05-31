package com.loeches.yugioh.Modelo.Cartas.Abstractas;

import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;

public abstract class AHechizo extends ACarta{
    private EAccionHechizo _accionHechizo;

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
}
