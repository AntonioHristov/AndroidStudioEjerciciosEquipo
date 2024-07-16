package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HFlauta extends AHechizo {
    public HFlauta() {
        super("Flauta", "Flauta equipable. Sube 1000 de ataque a una criatura", R.drawable.h_flauta, EAccionHechizo.EQUIPAR);
    }
    public HFlauta(HEscudo copia) {
        super(copia);
    }
    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_ataque(posibleObjetivo.get_ataque()+1000);
    }
}