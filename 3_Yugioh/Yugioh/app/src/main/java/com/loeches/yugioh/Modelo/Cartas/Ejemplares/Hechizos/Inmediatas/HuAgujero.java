package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HuAgujero extends AHechizo {
    public HuAgujero() {
        super("Agujero", "Agujero uso. Destruye un monstruo", R.drawable.hu_agujero, EAccionHechizo.USAR);
    }

    public HuAgujero(HuDisparo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        if(posibleObjetivo!=null){
            posibleObjetivo.get_cartaVista().convertirseVacio(true);
        }
    }
}
