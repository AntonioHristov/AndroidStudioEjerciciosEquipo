package com.loeches.yugioh.Controlador;

import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
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
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.Modelo.Global.Enums.ETurnosPosiblesEmpezarPartida;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.InterfazVista.CartaVista;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.Modelo.InterfazVista.HorizontalVista;
import com.loeches.yugioh.R;
import com.loeches.yugioh.Vista.Jugar.JugandoActivity;

import java.util.ArrayList;
import java.util.Random;

public class Controlador{
// INFORMACIÓN SOBRE GUARDAR Y CARGAR DATOS DE JSON, EN LA CLASE CONTROLADOR AL FINAL DEL MÉTODO nuevoTurno()
    public static void NuevaPartida(){
        Global.set_jugadores(new ArrayList<>());
        new Jugador(Global.get_iniciandoJugador1Prefijo(),Global.get_iniciandoJugador1Sufijo(),Global.get_iniciandoJugador1Vida());
        new Jugador(Global.get_iniciandoJugador2Prefijo(),Global.get_iniciandoJugador2Sufijo(),Global.get_iniciandoJugador2Vida());

        if(Global.get_datosGuardablesJSON().get_iniciandoTurnoJugador()== ETurnosPosiblesEmpezarPartida.AL_AZAR){
            Global.set_turnoJugador1((new Random().nextInt(2)==0)?true:false);
        }else{
            Global.set_turnoJugador1(Global.get_datosGuardablesJSON().get_iniciandoTurnoJugador()!= ETurnosPosiblesEmpezarPartida.JUGADOR_1);
        }


        Global.set_horizontalesVista(new ArrayList<>());
        HorizontalVista hVManoJ2,hVHechizoJ2,hVMonstruoJ2,hVMonstruoJ1, hVHechizoJ1,hVManoJ1;
        hVManoJ2= new HorizontalVista(EIdHorizontalVista.J2_MANO);
        for (int i = 0; i < Global.get_iniciandoPartidaCantidadManoHorizontalJ2()-1; i++) {
            ACarta nuevaCarta=Controlador.getCartaJugableRandom();
            nuevaCarta.set_idHorizontalVista(hVManoJ2.get_id());
            new CartaVista(hVManoJ2, nuevaCarta);
        }
        hVHechizoJ2= new HorizontalVista(EIdHorizontalVista.J2_HECHIZO);
        for (int i = 0; i < Global.get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2(); i++) {
            ACarta nuevaCarta=new CartaVacia(hVHechizoJ2.get_id());
            new CartaVista(hVHechizoJ2, nuevaCarta);
        }
        hVMonstruoJ2= new HorizontalVista(EIdHorizontalVista.J2_MONSTRUO);
        for (int i = 0; i < Global.get_iniciandoPartidaCantidadMonstruosHorizontalJ2(); i++) {
            ACarta nuevaCarta=new CartaVacia(hVMonstruoJ2.get_id());
            new CartaVista(hVMonstruoJ2, nuevaCarta);
        }
        hVMonstruoJ1= new HorizontalVista(EIdHorizontalVista.J1_MONSTRUO);
        for (int i = 0; i < Global.get_iniciandoPartidaCantidadMonstruosHorizontalJ1(); i++) {
            ACarta nuevaCarta=new CartaVacia(hVMonstruoJ1.get_id());
            new CartaVista(hVMonstruoJ1, nuevaCarta);
        }
        hVHechizoJ1= new HorizontalVista(EIdHorizontalVista.J1_HECHIZO);
        for (int i = 0; i < Global.get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1(); i++) {
            ACarta nuevaCarta=new CartaVacia(hVHechizoJ1.get_id());
            new CartaVista(hVHechizoJ1, nuevaCarta);
        }
        hVManoJ1= new HorizontalVista(EIdHorizontalVista.J1_MANO);
        for (int i = 0; i < Global.get_iniciandoPartidaCantidadManoHorizontalJ1()-1; i++) {
            ACarta nuevaCarta=Controlador.getCartaJugableRandom();
            nuevaCarta.set_idHorizontalVista(hVManoJ1.get_id());
            new CartaVista(hVManoJ1, nuevaCarta);
        }


    }


    public static void crearHorizontalesSiNoExisten(){
        if(Global.get_horizontalesVista()==null||Global.get_horizontalesVista().isEmpty()){
            Global.set_horizontalesVista(new ArrayList<>());

            new HorizontalVista(EIdHorizontalVista.J2_MANO);
            new HorizontalVista(EIdHorizontalVista.J2_HECHIZO);
            new HorizontalVista(EIdHorizontalVista.J2_MONSTRUO);
            new HorizontalVista(EIdHorizontalVista.J1_MONSTRUO);
            new HorizontalVista(EIdHorizontalVista.J1_HECHIZO);
            new HorizontalVista(EIdHorizontalVista.J1_MANO);
        }
    }

    public static void actualizarHorizontalesConCartas(){
        crearHorizontalesSiNoExisten();
        for (HorizontalVista hv:Global.get_horizontalesVista()) {
            hv.set_cartasVista(new ArrayList<>());
        }
        for (ACarta carta:Global.get_datosGuardablesJSON().get_cartas()) {
            new CartaVista(carta.get_idHorizontalVista(),carta);
        }
    }



    public static void actualizarCartasConHorizontales(){

        Global.get_datosGuardablesJSON().get_cartas().clear();
        for (HorizontalVista hv:Global.get_horizontalesVista()) {
            for(CartaVista cv:hv.get_cartasVista()){
                Global.get_datosGuardablesJSON().get_cartas().add(cv.get_carta());
            }
        }
    }


    public static void nuevoTurno(){
        if(partidaTerminada()){
            JugandoActivity.mostrarGanador();
        }else{

            Global.set_cartaVistaSeleccionada(null);
            Global.set_turnoJugador1(!Global.is_turnoJugador1());
            if(Global.is_turnoJugador1()){

                ACarta nuevaCarta=getCartaJugableRandom();
                nuevaCarta.set_idHorizontalVista(EIdHorizontalVista.J1_MANO);
                new CartaVista(EIdHorizontalVista.J1_MANO, nuevaCarta);
            }else{
                ACarta nuevaCarta=getCartaJugableRandom();
                nuevaCarta.set_idHorizontalVista(EIdHorizontalVista.J2_MANO);
                new CartaVista(EIdHorizontalVista.J2_MANO, nuevaCarta);
            }
            for (HorizontalVista hv: Global.get_horizontalesVista()) {
                for (CartaVista cv:hv.get_cartasVista()) {
                    if(cv.get_carta() instanceof AMonstruo){
                        // PARA LOS AUMENTOS DE ESTADISTICAS POR CIERTOS TURNOS
                        ((AMonstruo) cv.get_carta()).NuevoTurno();
                    }
                }
            }
            // CUANDO SE PUEDA CONFIGURAR EN EL MENU SI GUARDAR EN JSON O NO, DESCOMENTAR Global.get_datosGuardablesJSON().guardarSiHayDatosGuardados();
            // Y QUITAR Global.get_datosGuardablesJSON().guardar(); (ESTÁ POR COMODIDAD POR AHORA)
            // ANTES DE LO ANTERIOR, SI NO QUIERES CARGAR LOS DATOS DEL FICHERO, VE A MainActivity.java Y COMENTA LA LINEA: Global.get_datosGuardablesJSON().cargar();
            //Global.get_datosGuardablesJSON().guardarSiHayDatosGuardados();
            Global.get_datosGuardablesJSON().guardar();
            JugandoActivity.actualizarVista();
        }
    }

    public static boolean partidaTerminada(){
        for (Jugador j: Global.get_jugadores()) {
            if(j.get_vida()<=0){
                return true;
            }
        }
        return false;
    }

    public static ACarta getCartaJugable(int pos) {
        // EL MÚMERO DE getCartaJugableRandom DEBE SER EL ÚLTIMO NÚMERO DE ESTE SWITCH +1
        // EJEMPLO: SI EL ÚLTIMO NÚMERO DE ESTE SWITCH FUESE 2, EL NÚMERO bound EN getCartaJugableRandom DEBERÍA SER 3
        switch(pos) {
/*
            case 0:// TESTEAR
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
                return new MonstruoGenerico("Ángel caido","Un ángel que ha perdido su pureza, ahora lucha en el lado de las sombras.", R.drawable.m_angelcaido,1700,1200);
            case 21:
                return new MonstruoGenerico("Bebé dragón","Mucho más que sólo un niño, este dragón está dotado de un poder sin descubrir.", R.drawable.m_bebedragon,1200,700);
            case 22:
                return new MonstruoGenerico("Bestia de talwar","Sus alas son de color azul, con un diseño membranoso que les da una apariencia parecida a las de un murciélago.", R.drawable.m_bestiadetalwar,2400,2150);
            case 23:
                return new MonstruoGenerico("Buster blader","Conocido en español como Espadachín de Destrucción, es un poderoso monstruo guerrero.", R.drawable.m_busterblader,2600,2300);
            case 24:
                return new MonstruoGenerico("Caballero comandante","Es una carta muy útil para infligir un gran daño a tu oponente y controlar el campo de batalla.", R.drawable.m_caballerocomandante,1200,1900);
            case 25:
                return new MonstruoGenerico("Cañón tortuga","Una tortuga equipada con un cañón, capaz de disparar proyectiles con gran precisión.", R.drawable.m_canontortuga,1400,1800);
            case 26:
                return new MonstruoGenerico("Chica maga oscura","Esta carta es una versión alternativa y más joven de Maga Oscura.", R.drawable.m_chicamagaoscura,2000,1700);
            case 27:
                return new MonstruoGenerico("Cráneo convocado","Su apariencia es la de un demonio esquelético, que agrega un aspecto intimidante a su ya impresionante presencia en el campo de batalla.", R.drawable.m_craneoconvocado,2500,1200);
            case 28:
                return new MonstruoGenerico("Domador de monstruos","Es una elección popular en mazos centrados en monstruos de tipo Bestia y Guerrero.", R.drawable.m_domadordemonstruos,1800,1600);
            case 29:
                return new MonstruoGenerico("Dragón metálico oscuro","Un dragón de metal formidable con una apariencia aterradora y gran poder de ataque.", R.drawable.m_dragonmetalicooscuro,2400,2100);
            case 30:
                return new MonstruoGenerico("Dragón plateado","Es una de las cartas más icónicas y poderosas del juego, su enorme poder de ataque lo convierte en una amenaza significativa para cualquier oponente.", R.drawable.m_dragonplateado,3000,2500);
            case 31:
                return new MonstruoGenerico("Endimión el mago maestro","Sostiene un cetro o vara mágica en una mano, que emite destellos de energía mágica, indicando su control sobre el poder arcana.", R.drawable.m_endimionelmagomaestro,2700,1700);
            case 32:
                return new MonstruoGenerico("Espadachín de la llama azul","Es un guerrero humanoide con un traje de batalla que está envuelto en llamas azules.", R.drawable.m_espadachindelallamaazul,1800,1600);
            case 33:
                return new MonstruoGenerico("Gran shogun shien","Es un guerrero samurái imponente con una armadura tradicional japonesa.", R.drawable.m_granshogunshien,2500,2400);
            case 34:
                return new MonstruoGenerico("Guerrero castor","Lo que a esta criatura le falta en tamaño le sobra en defensa cuando batalla en la pradera.", R.drawable.m_guerrerocastor,1200,1500);
            case 35:
                return new MonstruoGenerico("Guerrero del escudo","En una mano sostiene un gran escudo que es su arma principal, en la otra mano puede llevar una espada o una lanza.", R.drawable.m_guerrerodelescudo,800,1600);
            case 36:
                return new MonstruoGenerico("Guerrero desconocido","Lleva una armadura ligera de color negro o gris oscuro, que le da un aspecto siniestro y enigmático.", R.drawable.m_guerrerodesconocido,1000,500);
            case 37:
                return new MonstruoGenerico("Guerrero de zera","Es un guerrero humanoide con una figura heroica y noble.", R.drawable.m_guerrerodezera,1600,1600);
            case 38:
                return new MonstruoGenerico("Guerrero minotauro","Tiene un cuerpo musculoso de un humano y la cabeza de un toro.", R.drawable.m_guerrerominotauro,1800,1300);
            case 39:
                return new MonstruoGenerico("Guerrero oscuro","Un guerrero envuelto en sombras, especializado en ataques rápidos y letales.", R.drawable.m_guerrerooscuro,1800,1500);
            case 40:
                return new MonstruoGenerico("Guerrero pantera","Es un guerrero valiente y poderoso, reconocido por su fuerza y habilidades en combate.", R.drawable.m_guerreropantera,2000,1600);
            case 41:
                return new MonstruoGenerico("Hechicero del caos","Un mago oscuro que utiliza poderosos hechizos para controlar el campo de batalla.", R.drawable.m_hechicerodelcaos,2300,2000);
            case 42:
                return new MonstruoGenerico("Jinzo","Es un monstruo verde y rojo muy poderoso", R.drawable.m_jinzo,2400,1500);
            case 43:
                return new MonstruoGenerico("Leogun","Es un león con una presencia imponente y dominante en el campo de batalla.", R.drawable.m_leogun,1750,1550);
            case 44:
                return new MonstruoGenerico("Mago del tiempo","Puede cambiar drásticamente el curso de un duelo, es tan emocionante como peligrosa de usar.", R.drawable.m_magodeltiempo,500,400);
            case 45:
                return new MonstruoGenerico("Muro de sombra","Su presencia está rodeada por un aura oscura que emana de su figura, lo que agrega un sentido de misterio y magia.", R.drawable.m_murodesombra,1600,3000);
            case 46:
                return new MonstruoGenerico("Oscuro dragón de trueno","Es un dragón con una apariencia oscura y misteriosa, que encarna elementos tanto de la oscuridad como del trueno.", R.drawable.m_oscurodragondetrueno,1600,1500);
            case 47:
                return new MonstruoGenerico("Señor de zemia","Tiene un cuerpo robusto y musculoso con piel azul oscuro y grandes alas de murciélago en su espalda.", R.drawable.m_senordezemia,1300,1000);
            case 48:
                return new MonstruoGenerico("Titiritero misterioso","Es una elección popular en mazos que se centran en el control del campo de batalla y en el aprovechamiento de los recursos del oponente.", R.drawable.m_titiriteromisterioso,1000,1500);
            case 49:
                return new MonstruoGenerico("Vela del destino","Tiene una postura heroica, con una mano levantada, a menudo representando poder y autoridad.", R.drawable.m_veladeldestino,600,600);
        }
        return null;
    }

    public static ACarta getCartaJugableRandom() {
        int tipoCarta=new Random().nextInt(3);
            switch (tipoCarta){
                case 0:
                    while(true){
                        int pos=new Random().nextInt(50);
                        if(getCartaJugable(pos)instanceof AMonstruo){
                            return getCartaJugable(pos);
                        }
                    }
                case 1:
                    while(true){
                        int pos=new Random().nextInt(50);
                        if(getCartaJugable(pos)instanceof AHechizo && ((AHechizo) getCartaJugable(pos)).get_accionHechizo()== EAccionHechizo.EQUIPAR){
                            return getCartaJugable(pos);
                        }
                    }
                case 2:
                    while(true){
                        int pos=new Random().nextInt(50);
                        if(getCartaJugable(pos)instanceof AHechizo && ((AHechizo) getCartaJugable(pos)).get_accionHechizo()== EAccionHechizo.USAR){
                            return getCartaJugable(pos);
                        }
                    }
            }
        return null;
        //return getCartaJugable(new Random().nextInt(3));// TESTEAR
    }



}
