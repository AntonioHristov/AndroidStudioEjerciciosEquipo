package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas;

import android.app.AlertDialog;
import android.content.DialogInterface;

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

public class HuDemoler  extends AHechizo {
    public HuDemoler() {
        super("Demoler", "Demoler uso. Destruye todos los monstruos del oponente", R.drawable.hu_demoler, EAccionHechizo.USAR);
    }

    public HuDemoler(HuDisparo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        HorizontalVista hvMonstruosRival=Variables.is_turnoJugador1()?Lista.getBy(EIdHorizontalVista.J2_MONSTRUO):Lista.getBy(EIdHorizontalVista.J1_MONSTRUO);

        for (CartaVista cv:hvMonstruosRival.get_cartasVista()) {
            cv.convertirseVacio(false);
        }
    }
}
