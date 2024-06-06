package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Global.Variables;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.Vista.CartaVista;
import com.loeches.yugioh.Modelo.Vista.HorizontalVista;
import com.loeches.yugioh.R;

public class HuDrenar extends AHechizo {
    public HuDrenar() {
        super("Drenar", "Drenar uso. Ganas 2000 de vida", R.drawable.hu_drenar, EAccionHechizo.USAR);
    }

    public HuDrenar(HuDisparo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        if(posibleObjetivo!=null){
            posibleObjetivo.set_defensa(posibleObjetivo.get_defensa()+2000);
        }else{
            Jugador j=Lista.get_jugadores().get(Variables.is_turnoJugador1()?0:1);
            j.set_vida(j.get_vida()+2000);
        }
    }
}