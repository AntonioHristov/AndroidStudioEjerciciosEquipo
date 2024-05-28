package com.loeches.yugioh.Modelo.Cartas.Abstractas;

import com.loeches.yugioh.Modelo.Enums.EAccionHechizo;

public abstract class AHechizo extends ACarta{
    private EAccionHechizo _accionHechizo;

    public AHechizo(String nombre, String descripcion, int imagen, EAccionHechizo accionHechizo) {
        super(nombre, descripcion, imagen);
        _accionHechizo = accionHechizo;
    }

    public EAccionHechizo get_accionHechizo() {
        return _accionHechizo;
    }
}
