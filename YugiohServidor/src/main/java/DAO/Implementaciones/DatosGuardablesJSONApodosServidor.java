package DAO.Implementaciones;

import Main.Main;
import Modelos.ApodoServidor;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DatosGuardablesJSONApodosServidor {
    // NO COMPARTIR CON CLIENTES, LOS COMPARTIDOS EN OTRO MODELO

    //private List<String> apodos=new ArrayList<>();
    private List<ApodoServidor> _apodos =new ArrayList<>();

    public DatosGuardablesJSONApodosServidor() {

    }



    public boolean cargarDesdeJSON(){

        if (Files.exists(Paths.get(Main.RUTA_Y_ARCHIVO_JSON))) {
            System.out.println("El archivo JSON existe. Cargando datos...");

            try (FileReader reader = new FileReader(Main.RUTA_Y_ARCHIVO_JSON)) {
                Gson gson = new Gson();

                File file=new File(Main.RUTA_Y_ARCHIVO_JSON);
                if(!file.exists()){
                    return false;
                }

                String contenido=new String(Files.readAllBytes(file.toPath()));
                DatosGuardablesJSONApodosServidor datosGuardablesJSONServidor=gson.fromJson(contenido, DatosGuardablesJSONApodosServidor.class);
                if(datosGuardablesJSONServidor==null){
                    Main.set_datosGuardablesJSONServidor(new DatosGuardablesJSONApodosServidor());
                }else{
                    Main.set_datosGuardablesJSONServidor(datosGuardablesJSONServidor);
                }

                System.out.println("ARCHIVO JSON CARGADO");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ARCHIVO JSON NO CARGADO");
            }
        } else {
            System.out.println("El archivo JSON no existe.");
        }
        return false;
    }

    public void guardar() {
        try{
            Gson gson=new Gson();
            File file=new File(Main.RUTA_Y_ARCHIVO_JSON);
            FileWriter fw=new FileWriter(file);
            gson.toJson(this,fw);
            fw.close();
            System.out.println("ARCHIVO JSON GUARDADO");

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("ARCHIVO JSON NO GUARDADO");
        }
    }

    public boolean guardar(String apodo){
        for (ApodoServidor a: _apodos){
            if(a.get_apodo().equals(apodo)){
                return false;
            }
        }
        _apodos.add(new ApodoServidor(apodo,true));
        guardar();
        return true;
    }

    public boolean existeApodo(String apodo){
        for (ApodoServidor a: _apodos){
            if(a.get_apodo().equals(apodo)){
                return true;
            }
        }
        return false;
    }

    public boolean loginValido(String apodo){
        for (ApodoServidor a: _apodos){
            if(a.get_apodo().equals(apodo)){
                return !a.is_sesionIniciada();
            }
        }
        return false;
    }

    public boolean logout(String apodo){
        for (ApodoServidor a: _apodos){
            if(a.get_apodo().equals(apodo)){
                a.set_sesionIniciada(false);
                guardar();
                return true;
            }
        }
        return false;
    }


    public List<ApodoServidor> get_apodos() {
        return _apodos;
    }

    public void set_apodos(List<ApodoServidor> _apodos) {
        this._apodos = _apodos;
    }
}
