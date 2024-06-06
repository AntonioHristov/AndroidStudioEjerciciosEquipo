package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Global.Variables;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.R;

public class HuLlamada  extends AHechizo {
    public HuLlamada() {
        super("Llamada", "Llamada uso. Ganas 1500 de vida", R.drawable.hu_llamada, EAccionHechizo.USAR);
    }

    public HuLlamada(HuDisparo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        if(posibleObjetivo!=null){
            posibleObjetivo.set_defensa(posibleObjetivo.get_defensa()+1500);
        }else{
            Jugador j= Lista.get_jugadores().get(Variables.is_turnoJugador1()?0:1);
            j.set_vida(j.get_vida()+1500);
        }
    }
}