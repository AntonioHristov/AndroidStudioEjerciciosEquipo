import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DatosGuardablesJSONServidor {
    // NO COMPARTIR CON CLIENTES, LOS COMPARTIDOS EN OTRO MODELO

    private List<String> apodos=new ArrayList<>();

    public DatosGuardablesJSONServidor() {
        //this.apodos = apodos;

    }



    public boolean cargarDesdeJSON(){

        if (Files.exists(Paths.get(ServidorTCP.RUTA_Y_ARCHIVO_JSON))) {
            System.out.println("El archivo JSON existe. Cargando datos...");

            try (FileReader reader = new FileReader(ServidorTCP.RUTA_Y_ARCHIVO_JSON)) {
                Gson gson = new Gson();

                File file=new File(ServidorTCP.RUTA_Y_ARCHIVO_JSON);
                if(!file.exists()){
                    return false;
                }

                String contenido=new String(Files.readAllBytes(file.toPath()));
                ServidorTCP.set_datosGuardablesJSONServidor(gson.fromJson(contenido, DatosGuardablesJSONServidor.class));
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
            File file=new File(ServidorTCP.RUTA_Y_ARCHIVO_JSON);
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
        for (String a:apodos){
            if(a.equals(apodo)){
                return false;
            }
        }
        apodos.add(apodo);
        return true;
    }

    public boolean existeApodo(String apodo){
        for (String a:apodos){
            if(a.equals(apodo)){
                return true;
            }
        }
        return false;
    }


    public List<String> getApodos() {
        return apodos;
    }

    public void setApodos(List<String> apodos) {
        this.apodos = apodos;
    }
}
