package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HCirculoDeFe extends AHechizo {

    public HCirculoDeFe() {
        super("Círculo de fe", "Círculo de fe equipable. Sube 1500 de defensa", R.drawable.h_circulodefe, EAccionHechizo.EQUIPAR);
    }

    public HCirculoDeFe(HEscudo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_defensa(posibleObjetivo.get_defensa()+1500);
    }

}