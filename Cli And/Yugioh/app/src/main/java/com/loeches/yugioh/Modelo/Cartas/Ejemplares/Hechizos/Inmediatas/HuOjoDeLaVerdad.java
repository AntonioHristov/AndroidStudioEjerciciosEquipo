package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.R;

public class HuOjoDeLaVerdad extends AHechizo{
    public HuOjoDeLaVerdad() {
        super("Ojo de la verdad", "Ojo de la verdad uso. Duplica el ataque este turno", R.drawable.hu_ojodelaverdad, EAccionHechizo.USAR);
        set_nuevoTurnoTrasRealizarAccion(false);
    }

    public HuOjoDeLaVerdad(HuDisparo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        posibleObjetivo.set_turnosAumentoAtaque(1);//EN EL SIGUIENTE TURNO, SU ATAQUE SERÁ SU ATAQUE BASE
        posibleObjetivo.set_ataque(posibleObjetivo.get_ataque()*2);
    }
}