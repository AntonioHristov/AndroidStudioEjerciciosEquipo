package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HDurezaDemoniaca extends AHechizo {

    public HDurezaDemoniaca() {
        super("Dureza demoniaca", "Dureza demoniaca equipable. Sube 1000 de defensa a una criatura", R.drawable.h_durezademoniaca, EAccionHechizo.EQUIPAR);
    }

    public HDurezaDemoniaca(HEscudo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_defensa(posibleObjetivo.get_defensa()+1000);
    }

}