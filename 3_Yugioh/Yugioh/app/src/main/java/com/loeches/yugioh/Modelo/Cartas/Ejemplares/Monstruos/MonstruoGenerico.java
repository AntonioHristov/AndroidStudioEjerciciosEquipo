package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Monstruos;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.Jugador;

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
        if(!is_modoDefensa()){
            if (posibleObjetivo == null) {
                int posJugadorRival = Global.is_turnoJugador1() ? 1 : 0;
                int nuevaVida = Global.get_jugadores().get(posJugadorRival).get_vida() - this.get_ataque();
                if (nuevaVida <= 0) {
                    nuevaVida = 0;
                }
                Global.get_jugadores().get(posJugadorRival).set_vida(nuevaVida);
            } else {
                if(posibleObjetivo.is_modoDefensa()){
                    posibleObjetivo.set_modoDefensa(false);
                }else{
                    posibleObjetivo.set_defensa(posibleObjetivo.get_defensa() - this.get_ataque());
                    if (posibleObjetivo.get_defensa() <= 0) {
                        posibleObjetivo.get_cartaVista().convertirseVacio(true);
                    }
                }
            }
            if(is_llevaEspejoDragon()){
                Jugador j = Global.get_jugadores().get(Global.is_turnoJugador1() ? 0 : 1);
                j.set_vida(j.get_vida()+this.get_ataque());
            }
        }
    }

    @Override
    public String toString() {
        return "Tipo: MonstruoGenerico | "+super.toString();
    }
}
