package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HGarraDorada extends AHechizo {
    public HGarraDorada() {
        super("Garra dorada", "Garra dorada equipable. Sube 1500 de ataque a una criatura", R.drawable.h_garradorada, EAccionHechizo.EQUIPAR);
    }
    public HGarraDorada(HEscudo copia) {
        super(copia);
    }
    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_ataque(posibleObjetivo.get_ataque()+1500);
    }
}