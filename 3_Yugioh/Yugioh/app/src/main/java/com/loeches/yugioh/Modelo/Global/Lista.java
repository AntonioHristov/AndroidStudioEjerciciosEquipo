package com.loeches.yugioh.Modelo.Global;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HCirculoDeFe;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HDurezaDemoniaca;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HElectroDuplica;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HEscudo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HEspadaDelHonor;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HEspejoDragon;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HFlauta;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HGarraDorada;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HPactoConLaPiedra;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Equipadas.HSenalDeApoyo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuAgujero;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuDemoler;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuDisparo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuDrenar;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuFarsa;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuLlamada;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuMaza;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuOjoDeLaVerdad;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuTrueno;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Hechizos.Inmediatas.HuVirus;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.Monstruos.MonstruoGenerico;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Vista.CartaVista;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.Vista.HorizontalVista;
import com.loeches.yugioh.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lista {
    public static final int CANTIDAD_MONSTRUOS_ELEGIDOS = 5, CANTIDAD_HECHIZOS_ELEGIDOS = 5;
    private static List<HorizontalVista> _horizontalesVista = new ArrayList<>();
    private static List<Jugador> _jugadores =new ArrayList<>();

    public static HorizontalVista getBy(EIdHorizontalVista id){
        for (HorizontalVista hv:_horizontalesVista) {
            if(hv.get_id()==id){
                return hv;
            }
        }
        return null;
    }

    public static int getPosHorizontalVista(CartaVista cv){
        List<CartaVista> cvs= cv.get_horizontalVista().get_cartasVista();
        for (int i = 0; i < cvs.size(); i++) {
            if(cv.equals(cvs.get(i))){
                return i;
            }
        }
        return Variables.POS_ERROR;
    }

    public static ACarta getCartaJugable(int pos) {
        // EL MÚMERO DE getCartaJugableRandom DEBE SER EL ÚLTIMO NÚMERO DE ESTE SWITCH +1
        // EJEMPLO: SI EL ÚLTIMO NÚMERO DE ESTE SWITCH FUESE 2, EL NÚMERO bound EN getCartaJugableRandom DEBE SER 3
        switch(pos) {
/*
            case 0:
                return new MonstruoGenerico("Jinzo","Es un monstruo verde y rojo", R.drawable.m_jinzo,2400,1500);
            case 1:
                return new HEscudo();
            case 2:
                return new HuDisparo();*/
            case 0:
                return new HuAgujero();
            case 1:
                return new HuDemoler();
            case 2:
                return new HuDisparo();
            case 3:
                return new HuDrenar();
            case 4:
                return new HuFarsa();
            case 5:
                return new HuLlamada();
            case 6:
                return new HuMaza();
            case 7:
                return new HuOjoDeLaVerdad();
            case 8:
                return new HuTrueno();
            case 9:
                return new HuVirus();
            case 10:
                return new HCirculoDeFe();
            case 11:
                return new HDurezaDemoniaca();
            case 12:
                return new HElectroDuplica();
            case 13:
                return new HEscudo();
            case 14:
                return new HEspadaDelHonor();
            case 15:
                return new HEspejoDragon();
            case 16:
                return new HFlauta();
            case 17:
                return new HGarraDorada();
            case 18:
                return new HPactoConLaPiedra();
            case 19:
                return new HSenalDeApoyo();
            case 20:
                return new MonstruoGenerico("Jinzo","Es un monstruo verde y rojo muy poderoso", R.drawable.m_jinzo,2400,1500);
            case 21:
                return new MonstruoGenerico("Pantera guerrera","Es un guerrero valiente y poderoso, reconocido por su fuerza y habilidades en combate.", R.drawable.m_panteraguerrera,2000,1600);
            case 22:
                return new MonstruoGenerico("Cráneo convocado","Su apariencia es la de un demonio esquelético, que agrega un aspecto intimidante a su ya impresionante presencia en el campo de batalla.", R.drawable.m_craneoconvocado,2500,1200);
            case 23:
                return new MonstruoGenerico("Dragón plateado","Es una de las cartas más icónicas y poderosas del juego, su enorme poder de ataque lo convierte en una amenaza significativa para cualquier oponente.", R.drawable.m_dragonplateado,3000,2500);
            case 24:
                return new MonstruoGenerico("Mago del tiempo","Puede cambiar drásticamente el curso de un duelo, es tan emocionante como peligrosa de usar.", R.drawable.m_magodeltiempo,500,400);
            case 25:
                return new MonstruoGenerico("Buster blader","Conocido en español como Espadachín de Destrucción, es un poderoso monstruo guerrero", R.drawable.m_busterblader,2600,2300);
            case 26:
                return new MonstruoGenerico("Caballero comandante","Es una carta muy útil para infligir un gran daño a tu oponente y controlar el campo de batalla.", R.drawable.m_caballerocomandante,1200,1900);
            case 27:
                return new MonstruoGenerico("Dragón metálico oscuro","Un dragón de metal formidable con una apariencia aterradora y gran poder de ataque.", R.drawable.m_dragonmetalicooscuro,2400,2100);
            case 28:
                return new MonstruoGenerico("Cañón tortuga","Una tortuga equipada con un cañón, capaz de disparar proyectiles con gran precisión.", R.drawable.m_canontortuga,1400,1800);
            case 29:
                return new MonstruoGenerico("Guerrero oscuro","Un guerrero envuelto en sombras, especializado en ataques rápidos y letales.", R.drawable.m_guerrerooscuro,1800,1500);
            case 30:
                return new MonstruoGenerico("Hechicero del caos","Un mago oscuro que utiliza poderosos hechizos para controlar el campo de batalla.", R.drawable.m_hechicerodelcaos,2300,2000);
            case 31:
                return new MonstruoGenerico("Ángel caido","Un ángel que ha perdido su pureza, ahora lucha en el lado de las sombras.", R.drawable.m_angelcaido,1700,1200);
            case 32:
                return new MonstruoGenerico("Chica maga oscura","Esta carta es una versión alternativa y más joven de Maga Oscura", R.drawable.m_chicamagaoscura,2000,1700);
            case 33:
                return new MonstruoGenerico("Domador de monstruos","Es una elección popular en mazos centrados en monstruos de tipo Bestia y Guerrero.", R.drawable.m_domadordemonstruos,1800,1600);
            case 34:
                return new MonstruoGenerico("Titiritero misterioso","Es una elección popular en mazos que se centran en el control del campo de batalla y en el aprovechamiento de los recursos del oponente.", R.drawable.m_titiriteromisterioso,1000,1500);
        }
        return null;
    }

    public static ACarta getCartaJugableRandom() {
        return getCartaJugable(new Random().nextInt(35));
        //return getCartaJugable(new Random().nextInt(3));
    }

    public static List<HorizontalVista> getMismoTurno(HorizontalVista hv){
        List<HorizontalVista> resultado=new ArrayList<>();
        switch (hv.get_id()){
            case J1_MANO:
            case J1_HECHIZO:
            case J1_MONSTRUO:
                resultado.add(getBy(EIdHorizontalVista.J1_MANO));
                resultado.add(getBy(EIdHorizontalVista.J1_HECHIZO));
                resultado.add(getBy(EIdHorizontalVista.J1_MONSTRUO));
                return resultado;
            case J2_MANO:
            case J2_HECHIZO:
            case J2_MONSTRUO:
                resultado.add(getBy(EIdHorizontalVista.J2_MANO));
                resultado.add(getBy(EIdHorizontalVista.J2_HECHIZO));
                resultado.add(getBy(EIdHorizontalVista.J2_MONSTRUO));
                return resultado;
        }
        return resultado;
    }

    public static List<HorizontalVista> get_horizontalesVista() {
        return _horizontalesVista;
    }

    public static void set_horizontalesVista(List<HorizontalVista> horizontalesVista) {
        _horizontalesVista = horizontalesVista;
    }

    public static List<Jugador> get_jugadores() {
        return _jugadores;
    }

    public static void set_jugadores(List<Jugador> jugadores) {
        _jugadores = jugadores;
    }
}
