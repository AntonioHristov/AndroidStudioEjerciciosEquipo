package com.loeches.yugioh.Modelo.Cartas.Ejemplares.Monstruos;

import com.loeches.yugioh.Controlador.Controlador;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Global.Variables;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.Vista.HorizontalVista;

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
        if (posibleObjetivo == null) {
            int posJugadorRival = Variables.is_turnoJugador1() ? 1 : 0;
            int nuevaVida = Lista.get_jugadores().get(posJugadorRival).get_vida() - this.get_ataque();
            if (nuevaVida <= 0) {
                nuevaVida = 0;
            }
            Lista.get_jugadores().get(posJugadorRival).set_vida(nuevaVida);
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
            Jugador j = Lista.get_jugadores().get(Variables.is_turnoJugador1() ? 0 : 1);
            j.set_vida(j.get_vida()+this.get_ataque());
        }
    }

    @Override
    public String toString() {
        return "Tipo: MonstruoGenerico | "+super.toString();
    }
}
