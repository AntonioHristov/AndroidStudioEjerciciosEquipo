package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.R;

public class HuDisparo extends AHechizo {
    public HuDisparo() {
        super("Disparo", "Hechizo uso. Hace 1000 de daño a cada jugador", R.drawable.hu_disparo, EAccionHechizo.USAR);
    }

    public HuDisparo(HuDisparo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        for (Jugador j: Global.get_jugadores()) {
            int nuevaVida=j.get_vida()-1000;
            if(nuevaVida<0){
                nuevaVida=0;
            }
            j.set_vida(nuevaVida);
        }
    }
}
