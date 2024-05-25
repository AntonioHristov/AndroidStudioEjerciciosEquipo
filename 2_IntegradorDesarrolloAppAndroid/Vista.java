package IntegradorDesarrolloAppAndroid;

public class Vista {
	public static String imagenFlechaArriba;
	public static String imagenFlechaAbajo;

	public static void ActualizarVista(int numeroIntentado, int numeroAleatorioGenerado) {
		// AQUI SE CAMBIA LAS IMÁGENES DE LAS FLECHAS OPACO O BRILLANTE
		if (numeroIntentado < numeroAleatorioGenerado) {
			imagenFlechaArriba = "Imagen Flecha Arriba Color Azul Brillante (EL NÚMERO GENERADO ES MAYOR)";
			imagenFlechaAbajo = "Imagen Flecha Abajo Color Rojo Opaco (EL NÚMERO GENERADO ES MAYOR)";
		} else {
			imagenFlechaArriba = "Imagen Flecha Arriba Color Azul Opaco (EL NÚMERO GENERADO ES MENOR)";
			imagenFlechaAbajo = "Imagen Flecha Abajo Color Rojo Brillante (EL NÚMERO GENERADO ES MENOR)";
		}
		System.out.println(imagenFlechaArriba);
		System.out.println(imagenFlechaAbajo);
	}
}