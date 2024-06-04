package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HEscudo extends AHechizo {

    public HEscudo() {
        super("Escudo", "Hechizo equipable. Sube 2000 de defensa a una criatura", R.drawable.h_escudo, EAccionHechizo.EQUIPAR);
    }

    public HEscudo(HEscudo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_defensa(posibleObjetivo.get_defensa()+2000);
        this.get_cartaVista().convertirseVacio();
    }

}
