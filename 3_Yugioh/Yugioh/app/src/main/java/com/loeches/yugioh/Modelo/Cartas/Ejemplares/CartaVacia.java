package com.loeches.yugioh.Modelo.Cartas.Ejemplares;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.R;

public class CartaVacia extends ACarta {
    public CartaVacia() {
        super("", "", R.drawable.carta_vacia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {

    }
}
