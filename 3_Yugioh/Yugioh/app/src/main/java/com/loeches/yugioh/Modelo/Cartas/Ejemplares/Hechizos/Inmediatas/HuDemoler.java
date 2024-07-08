package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.InterfazVista.CartaVista;
import com.loeches.yugioh.Modelo.InterfazVista.HorizontalVista;
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
        HorizontalVista hvMonstruosRival=Global.is_turnoJugador1()? Global.getBy(EIdHorizontalVista.J2_MONSTRUO): Global.getBy(EIdHorizontalVista.J1_MONSTRUO);

        for (CartaVista cv:hvMonstruosRival.get_cartasVista()) {
            cv.convertirseVacio(false);
        }
    }
}
