package IntegradorDesarrolloAppAndroid;

import java.util.Random;
import java.util.Scanner;

public class Controlador {

	public static void main(String[] args) {
		Scanner entrada=new Scanner(System.in);//ELIMINAR EN ANDROID STUDIO, SIMULA ENTRADA DE DATOS EN ECLIPSE/INTELIJ
		
		Modelo modelo=new Modelo();
		
		/* EVENTO DE UN INTENTO DEL USUARIO (UN setOnClickListener BOTÓN ENVIAR O TAL VEZ ALGÚN EVENTO OnChange DEL NÚMERO O ALGO ASI...)
		 * EN ANDROID STUDIO QUITAR EL DO WHILE Y LAS 2 PRIMERAS LINEAS QUE TIENE DENTRO
		*/
		int numeroIntentado;
		do {
			System.out.println("ESPERANDO A QUE EL JUGADOR INTENTE ADIVINAR EL NÚMERO GENERADO DEL 1 AL 100 (ESCRIBE UN NÚMERO, INTENTOS RESTANTES: "+(Modelo.LIMITE_INTENTOS_JUGADOR-modelo.getIntentosJugador())+")");
			numeroIntentado=Integer.parseInt(entrada.nextLine());
			modelo.nuevoIntento();
			// IF PARTIDA TERMINADA
			if(numeroIntentado==modelo.getNumeroAleatorioGenerado()||modelo.getIntentosJugador()==Modelo.LIMITE_INTENTOS_JUGADOR) {
				// CAMBIAR A LA 2º PANTALLA QUE MUESTRA EL RESULTADO (CON Intent, CON PutExtra LLEVANDO LOS VALORES numeroIntentado, modelo.getNumeroAleatorioGenerado(), modelo.getIntentosJugador() )
				if(numeroIntentado==modelo.getNumeroAleatorioGenerado()) {
					System.out.println("FELICIDADES, GANASTE. LO HAS LOGRADO EN "+modelo.getIntentosJugador()+" INTENTOS");
				}else {
					System.out.println("PERDISTE");
				}
				System.out.println("EL NÚMERO GENERADO FUE: "+modelo.getNumeroAleatorioGenerado());
				break;
			}
			Vista.ActualizarVista(numeroIntentado, modelo.getNumeroAleatorioGenerado());
		}while(true);
	}
}