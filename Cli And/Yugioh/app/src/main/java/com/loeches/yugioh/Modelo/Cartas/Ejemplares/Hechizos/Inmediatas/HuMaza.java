package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.R;

import java.util.Random;

public class HuMaza extends AHechizo {
    public HuMaza() {
        super("Maza", "Maza uso. Quita 1000 de vida a un jugador", R.drawable.hu_maza, EAccionHechizo.USAR);
    }

    public HuMaza(HuDisparo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        Jugador j= Global.get_jugadores().get(new Random().nextInt(2));
        j.set_vida(j.get_vida()-1000);
    }
}