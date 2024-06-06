package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HPactoConLaPiedra extends AHechizo {
    public HPactoConLaPiedra() {
        super("Pacto con la piedra", "Pacto con la piedra equipable. Duplica la defensa de una criatura", R.drawable.h_pactoconlapiedra, EAccionHechizo.EQUIPAR);
    }
    public HPactoConLaPiedra(HEscudo copia) {
        super(copia);
    }
    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_defensa(posibleObjetivo.get_defensa()*2);
    }
}