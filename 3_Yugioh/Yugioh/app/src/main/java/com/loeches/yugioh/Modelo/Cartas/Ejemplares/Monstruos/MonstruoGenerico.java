package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Monstruos;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;

public class MonstruoGenerico extends AMonstruo {

    public MonstruoGenerico(String nombre, String descripcion, int imagen, int danio, int defensa, int vida, int ataque) {
        super(nombre, descripcion, imagen, danio, defensa, vida, ataque);
    }

    public MonstruoGenerico(String nombre, String descripcion, int imagen, int danio, int defensa, int vida, int ataque, boolean modoDefensa) {
        super(nombre, descripcion, imagen, danio, defensa, vida, ataque, modoDefensa);
    }

    public MonstruoGenerico(AMonstruo copia) {
        super(copia);
    }

    @Override
    public void RealizarAccion(AMonstruo posibleObjetivo) {
        int danio=this.get_ataque()- posibleObjetivo.get_defensa();
        if(danio<0){
            danio=0;
        }
        posibleObjetivo.set_vida(posibleObjetivo.get_vida()-danio);
    }
}
