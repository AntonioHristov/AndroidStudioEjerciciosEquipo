package IntegradorDesarrolloAppAndroid;

import java.util.Random;

public class Modelo {
	public static final int MINIMO_ALEATORIO=1,MAXIMO_ALEATORIO=100,LIMITE_INTENTOS_JUGADOR=8;
	private int numeroAleatorioGenerado,intentosJugador;
	
	public Modelo() {
		numeroAleatorioGenerado=new Random().nextInt(Modelo.MINIMO_ALEATORIO,Modelo.MAXIMO_ALEATORIO+1); // 1 AL 100 INCLUIDOS
		intentosJugador=0;
	}
	
	public void nuevoIntento() {
		intentosJugador++;
	}
	
	public int getNumeroAleatorioGenerado() {
		return numeroAleatorioGenerado;
	}
	public int getIntentosJugador() {
		return intentosJugador;
	}
	
}
