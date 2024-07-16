package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HElectroDuplica extends AHechizo {
    public HElectroDuplica() {
        super("Electro duplica", "Electro duplica equipable. Duplica el ataque de una criatura", R.drawable.h_electroduplica, EAccionHechizo.EQUIPAR);
    }
    public HElectroDuplica(HEscudo copia) {
        super(copia);
    }
    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_ataque(posibleObjetivo.get_ataque()*2);
    }
}