package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Monstruos;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;

public class MonstruoGenerico extends AMonstruo {

    public MonstruoGenerico(String nombre, String descripcion, int imagen, int ataque, int defensa) {
        super(nombre, descripcion, imagen, ataque, defensa);
    }

    public MonstruoGenerico(String nombre, String descripcion, int imagen, int ataque, int defensa, boolean modoDefensa) {
        super(nombre, descripcion, imagen, ataque, defensa, modoDefensa);
    }

    public MonstruoGenerico(AMonstruo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        int nuevaDefensa=posibleObjetivo.get_defensa()-this.get_ataque();
        if(nuevaDefensa<0){
            nuevaDefensa=0;
        }
        posibleObjetivo.set_defensa(nuevaDefensa);
    }
}
