package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HEspejoDragon extends AHechizo {
    public HEspejoDragon() {
        super("Espejo dragón", "Espejo dragón equipable. El daño que haga la criatura lo ganas en vida", R.drawable.h_espejodragon, EAccionHechizo.EQUIPAR);
    }
    public HEspejoDragon(HEscudo copia) {
        super(copia);
    }
    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_llevaEspejoDragon(true);
    }
}