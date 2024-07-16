package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HEspadaDelHonor extends AHechizo {
    public HEspadaDelHonor() {
        super("Espada del honor", "Espada del honor equipable. Sube 2000 de ataque a una criatura", R.drawable.h_espada_del_honor, EAccionHechizo.EQUIPAR);
    }
    public HEspadaDelHonor(HEscudo copia) {
        super(copia);
    }
    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_ataque(posibleObjetivo.get_ataque()+2000);
    }
}