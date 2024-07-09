package com.loeches.yugioh.DAO.Implementaciones;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loeches.yugioh.Controlador.Controlador;
import com.loeches.yugioh.DAO.Implementaciones.Adaptador.CartaTypeAdapter;
import com.loeches.yugioh.DAO.Interfaces.IDatosGuardablesDAO;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Global.Enums.ETurnosPosiblesEmpezarPartida;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.R;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DatosGuardablesJSON implements IDatosGuardablesDAO {
    private ETurnosPosiblesEmpezarPartida _iniciandoTurnoJugador;
    private boolean _turnoJugador1,_modoOptimoJugando, _preguntarConfirmacionAccionesJugando, _empezarPartidaNueva;
    // LOS _iniciandoPartida ¡¡¡ SON SOLAMENTE AL INICIO DE CADA PARTIDA, AHÍ NO SE GUARDAN LOS VALORES SI CAMBIAN A LO LARGO DE LA PARTIDA !!! LA IDEA ES QUE EL JUGADOR LO PUEDA CAMBIAR EN EL MENU PERSONALIZAR

    // EL ANCHO DE CADA CARTAVISTA SERÁ EL ANCHO DEL TELÉFONO DIVIDO ENTRE EL VALOR ALMACENADO EN _cantidadCartaVistasPorHorizontalSinScroll
    private int _iniciandoPartidaCantidadMonstruosHorizontalJ1,
            _iniciandoPartidaCantidadMonstruosHorizontalJ2,
            _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1,
            _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2,
            _iniciandoPartidaCantidadManoHorizontalJ1,
            _iniciandoPartidaCantidadManoHorizontalJ2,
            _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll,
            _iniciandoJugador1Vida,
            _iniciandoJugador2Vida,
            _musicaFondoJugando,
            _sonidoAtaqueMonstruo;
    private String _iniciandoJugador1Prefijo, _iniciandoJugador1Sufijo, _iniciandoJugador2Prefijo, _iniciandoJugador2Sufijo;
    /*
        LOS VALORES ACTUALIZADOS DE LAS CARTAS POR DEFECTO LO TIENEN LOS HORIZONTALES DE LA CLASE Global
        SI QUIERES TENER LOS VALORES ACTUALIZADOS DE LOS HORIZONTALES EN ESTA LISTA _cartas,
        PRIMERO LLAMA A Controlador.actualizarCartasConHorizontales();
     */
    private List<ACarta> _cartas;
    private List<Jugador> _jugadores;// pos 0=Jugador 1, pos 1=Jugador 2

    public DatosGuardablesJSON() {
        restaurarValoresDefecto();
    }

    @Override
    public void restaurarValoresDefecto() {
        _iniciandoTurnoJugador =ETurnosPosiblesEmpezarPartida.AL_AZAR;
        //_turnoJugador1=true;
        _modoOptimoJugando=true;
        _preguntarConfirmacionAccionesJugando=true;
        _empezarPartidaNueva=true;
        _iniciandoPartidaCantidadMonstruosHorizontalJ1=5;
        _iniciandoPartidaCantidadMonstruosHorizontalJ2=5;
        _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1=5;
        _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2=5;
        _iniciandoPartidaCantidadManoHorizontalJ1=5;
        _iniciandoPartidaCantidadManoHorizontalJ2=5;
        _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll=5;
        _iniciandoJugador1Vida=8000;
        _iniciandoJugador2Vida=500;
        _musicaFondoJugando = R.raw.yugiho;
        _sonidoAtaqueMonstruo= R.raw.yugiho;
        _iniciandoJugador1Prefijo="Vida Jugador 1: ";
        _iniciandoJugador1Sufijo="";
        _iniciandoJugador2Prefijo="Vida Jugador 2: ";
        _iniciandoJugador2Sufijo="";
        _cartas=new ArrayList<>();
        _jugadores=new ArrayList<>();
    }

    @Override
    public boolean hayDatos() {
        String uri=Global.get_context().getExternalFilesDir(null).getAbsolutePath();
        File file=new File(uri,Global.NOMBRE_ARCHIVO_GUARDAR_DATOS+".json");
        return file.exists();
    }

    @Override
    public boolean cargar() {
        try {
            //Gson gson=new Gson();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ACarta.class, new CartaTypeAdapter())
                    .create();
            String uri=Global.get_context().getExternalFilesDir(null).getAbsolutePath();
            File file=new File(uri,Global.NOMBRE_ARCHIVO_GUARDAR_DATOS+".json");
            if(!file.exists()){
                return false;
            }
            String contenido=new String(Files.readAllBytes(file.toPath()));
            Global.set_datosGuardablesJSON(gson.fromJson(contenido, DatosGuardablesJSON.class));

            Toast.makeText(Global.get_context(), "JSON CARGADO", Toast.LENGTH_LONG).show();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Global.get_context(), "ERROR AL CARGAR JSON", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void guardarSiHayDatosGuardados() {
        if(hayDatos()){
            guardar();
        }
    }

    @Override
    public void guardar() {
        Controlador.actualizarCartasConHorizontales();
        try{
            //Gson gson=new Gson();
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ACarta.class, new CartaTypeAdapter())
                    .create();
            String uri= Global.get_context().getExternalFilesDir(null).getAbsolutePath();
            File file=new File(uri,Global.NOMBRE_ARCHIVO_GUARDAR_DATOS+".json");
            FileWriter fw=new FileWriter(file);
            gson.toJson(this,fw);
            fw.close();
            Toast.makeText(Global.get_context(), "ARCHIVO JSON GUARDADO", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(Global.get_context(), "ERROR AL GUARDAR JSON", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void borrar() {
        String uri=Global.get_context().getExternalFilesDir(null).getAbsolutePath();
        File file=new File(uri,Global.NOMBRE_ARCHIVO_GUARDAR_DATOS+".json");
        if (file.exists()) {
            file.delete();
            Toast.makeText(Global.get_context(), "ARCHIVO JSON BORRADO", Toast.LENGTH_LONG).show();
        }
    }


    public boolean is_turnoJugador1() {
        return _turnoJugador1;
    }

    public void set_turnoJugador1(boolean _turnoJugador1) {
        this._turnoJugador1 = _turnoJugador1;
    }

    public boolean is_modoOptimoJugando() {
        return _modoOptimoJugando;
    }

    public void set_modoOptimoJugando(boolean _modoOptimoJugando) {
        this._modoOptimoJugando = _modoOptimoJugando;
    }

    public boolean is_preguntarConfirmacionAccionesJugando() {
        return _preguntarConfirmacionAccionesJugando;
    }

    public void set_preguntarConfirmacionAccionesJugando(boolean _preguntarConfirmacionAccionesJugando) {
        this._preguntarConfirmacionAccionesJugando = _preguntarConfirmacionAccionesJugando;
    }

    public boolean is_empezarPartidaNueva() {
        return _empezarPartidaNueva;
    }

    public void set_empezarPartidaNueva(boolean _empezarPartidaNueva) {
        this._empezarPartidaNueva = _empezarPartidaNueva;
    }

    public int get_iniciandoPartidaCantidadMonstruosHorizontalJ1() {
        return _iniciandoPartidaCantidadMonstruosHorizontalJ1;
    }

    public void set_iniciandoPartidaCantidadMonstruosHorizontalJ1(int iniciandoPartidaCantidadMonstruosHorizontalJ1) {
        _iniciandoPartidaCantidadMonstruosHorizontalJ1 = iniciandoPartidaCantidadMonstruosHorizontalJ1;
    }

    public int get_iniciandoPartidaCantidadMonstruosHorizontalJ2() {
        return _iniciandoPartidaCantidadMonstruosHorizontalJ2;
    }

    public void set_iniciandoPartidaCantidadMonstruosHorizontalJ2(int iniciandoPartidaCantidadMonstruosHorizontalJ2) {
        _iniciandoPartidaCantidadMonstruosHorizontalJ2 = iniciandoPartidaCantidadMonstruosHorizontalJ2;
    }

    public int get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1() {
        return _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1;
    }

    public void set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1(int iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1) {
        _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1 = iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1;
    }

    public int get_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2() {
        return _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2;
    }

    public void set_iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2(int iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2) {
        _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2 = iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2;
    }

    public int get_iniciandoPartidaCantidadManoHorizontalJ1() {
        return _iniciandoPartidaCantidadManoHorizontalJ1;
    }

    public void set_iniciandoPartidaCantidadManoHorizontalJ1(int iniciandoPartidaCantidadManoHorizontalJ1) {
        _iniciandoPartidaCantidadManoHorizontalJ1 = iniciandoPartidaCantidadManoHorizontalJ1;
    }

    public int get_iniciandoPartidaCantidadManoHorizontalJ2() {
        return _iniciandoPartidaCantidadManoHorizontalJ2;
    }

    public void set_iniciandoPartidaCantidadManoHorizontalJ2(int iniciandoPartidaCantidadManoHorizontalJ2) {
        _iniciandoPartidaCantidadManoHorizontalJ2 = iniciandoPartidaCantidadManoHorizontalJ2;
    }

    public int get_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll() {
        return _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll;
    }

    public void set_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll(int iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll) {
        _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll = iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll;
    }

    public int get_iniciandoJugador1Vida() {
        return _iniciandoJugador1Vida;
    }

    public void set_iniciandoJugador1Vida(int iniciandoJugador1Vida) {
        _iniciandoJugador1Vida = iniciandoJugador1Vida;
    }

    public int get_iniciandoJugador2Vida() {
        return _iniciandoJugador2Vida;
    }

    public void set_iniciandoJugador2Vida(int iniciandoJugador2Vida) {
        _iniciandoJugador2Vida = iniciandoJugador2Vida;
    }

    public int get_musicaFondoJugando() {
        return _musicaFondoJugando;
    }

    public void set_musicaFondoJugando(int musicaFondoJugando) {
        _musicaFondoJugando = musicaFondoJugando;
    }

    public int get_sonidoAtaqueMonstruo() {
        return _sonidoAtaqueMonstruo;
    }

    public void set_sonidoAtaqueMonstruo(int sonidoAtaqueMonstruo) {
        _sonidoAtaqueMonstruo = sonidoAtaqueMonstruo;
    }

    public ETurnosPosiblesEmpezarPartida get_iniciandoTurnoJugador() {
        return _iniciandoTurnoJugador;
    }

    public void set_iniciandoTurnoJugador(ETurnosPosiblesEmpezarPartida turnoIniciarPartida) {
        _iniciandoTurnoJugador = turnoIniciarPartida;
    }

    public String get_iniciandoJugador1Prefijo() {
        return _iniciandoJugador1Prefijo;
    }

    public void set_iniciandoJugador1Prefijo(String iniciandoJugador1Prefijo) {
        _iniciandoJugador1Prefijo = iniciandoJugador1Prefijo;
    }

    public String get_iniciandoJugador1Sufijo() {
        return _iniciandoJugador1Sufijo;
    }

    public void set_iniciandoJugador1Sufijo(String iniciandoJugador1Sufijo) {
        _iniciandoJugador1Sufijo = iniciandoJugador1Sufijo;
    }

    public String get_iniciandoJugador2Prefijo() {
        return _iniciandoJugador2Prefijo;
    }

    public void set_iniciandoJugador2Prefijo(String iniciandoJugador2Prefijo) {
        _iniciandoJugador2Prefijo = iniciandoJugador2Prefijo;
    }

    public String get_iniciandoJugador2Sufijo() {
        return _iniciandoJugador2Sufijo;
    }

    public void set_iniciandoJugador2Sufijo(String iniciandoJugador2Sufijo) {
        _iniciandoJugador2Sufijo = iniciandoJugador2Sufijo;
    }

    public List<ACarta> get_cartas() {
        return _cartas;
    }

    public void set_cartas(List<ACarta> cartas) {
        _cartas = cartas;
    }

    public List<Jugador> get_jugadores() {
        return _jugadores;
    }

    public void set_jugadores(List<Jugador> jugadores) {
        _jugadores = jugadores;
    }


    @Override
    public String toString() {
        return "DatosGuardablesJSON{" +
                "_turnoJugador1=" + _turnoJugador1 +
                ", _modoOptimoJugando=" + _modoOptimoJugando +
                ", _preguntarConfirmacionAccionesJugando=" + _preguntarConfirmacionAccionesJugando +
                ", _empezarPartidaNueva=" + _empezarPartidaNueva +
                ", _iniciandoPartidaCantidadMonstruosHorizontalJ1=" + _iniciandoPartidaCantidadMonstruosHorizontalJ1 +
                ", _iniciandoPartidaCantidadMonstruosHorizontalJ2=" + _iniciandoPartidaCantidadMonstruosHorizontalJ2 +
                ", _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1=" + _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ1 +
                ", _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2=" + _iniciandoPartidaCantidadHechizosEquipablesHorizontalJ2 +
                ", _iniciandoPartidaCantidadManoHorizontalJ1=" + _iniciandoPartidaCantidadManoHorizontalJ1 +
                ", _iniciandoPartidaCantidadManoHorizontalJ2=" + _iniciandoPartidaCantidadManoHorizontalJ2 +
                ", _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll=" + _iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll +
                ", _iniciandoJugador1Vida=" + _iniciandoJugador1Vida +
                ", _iniciandoJugador2Vida=" + _iniciandoJugador2Vida +
                ", _musicaFondoJugando=" + _musicaFondoJugando +
                ", _sonidoAtaqueMonstruo=" + _sonidoAtaqueMonstruo +
                ", _iniciandoTurnoJugador=" + _iniciandoTurnoJugador +
                ", _iniciandoJugador1Prefijo='" + _iniciandoJugador1Prefijo + '\'' +
                ", _iniciandoJugador1Sufijo='" + _iniciandoJugador1Sufijo + '\'' +
                ", _iniciandoJugador2Prefijo='" + _iniciandoJugador2Prefijo + '\'' +
                ", _iniciandoJugador2Sufijo='" + _iniciandoJugador2Sufijo + '\'' +
                ", _cartas=" + _cartas +
                ", _jugadores=" + _jugadores +
                '}';
    }
}
