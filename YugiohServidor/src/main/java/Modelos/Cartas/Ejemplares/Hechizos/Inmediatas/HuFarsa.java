package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HuFarsa extends AHechizo {
    public HuFarsa() {
        super("Farsa", "Farsa uso. Hace 2000 daño a un monstruo", R.drawable.hu_farsa, EAccionHechizo.USAR);
    }

    public HuFarsa(HuDisparo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        if(posibleObjetivo!=null){
            posibleObjetivo.set_defensa(posibleObjetivo.get_defensa()-2000);
            if (posibleObjetivo.get_defensa() <= 0) {
                posibleObjetivo.get_cartaVista().convertirseVacio(true);
            }
        }
    }
}