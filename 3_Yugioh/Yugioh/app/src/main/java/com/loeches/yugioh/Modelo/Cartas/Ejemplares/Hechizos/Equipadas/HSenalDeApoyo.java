package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HSenalDeApoyo extends AHechizo {
    public HSenalDeApoyo() {
        super("Señal de apoyo", "Señal de apoyo equipable. Sube 1500 de ataque", R.drawable.h_senaldeapoyo, EAccionHechizo.EQUIPAR);
    }
    public HSenalDeApoyo(HEscudo copia) {
        super(copia);
    }
    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_ataque(posibleObjetivo.get_ataque()+1500);
    }
}