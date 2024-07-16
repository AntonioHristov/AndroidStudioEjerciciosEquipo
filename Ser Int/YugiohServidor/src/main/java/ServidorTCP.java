import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServidorTCP {
    public final static String RUTA_Y_ARCHIVO_JSON = "./datosServer.json";
    public final static int PUERTO = 83;

    private static DatosGuardablesJSONServidor _datosGuardablesJSONServidor;
    private static List<Socket> _clientes = new ArrayList<>();

    public static void main(String[] args) {
        set_datosGuardablesJSONServidor(new DatosGuardablesJSONServidor());
        _datosGuardablesJSONServidor.cargarDesdeJSON();

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                try {
                    Socket cliente = servidor.accept();
                    System.out.println("Cliente conectado: " + cliente.getInetAddress());

                    _clientes.add(cliente);

                    hiloCliente(cliente);

                    //new Thread(new ManejadorCliente(cliente)).start();
                } catch (IOException e) {
                    System.err.println("Error al aceptar conexión del cliente");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("No se puede iniciar el servidor en el puerto " + PUERTO);
            e.printStackTrace();
        }
    }

    private static void hiloCliente(Socket cliente) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                     PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {

                    while (true) {

                        ECodigosTCP codigoTCP = ECodigosTCP.valueOf(entrada.readLine());

                        switch (codigoTCP) {
                            case REGISTRARSE:
                                String apodo = entrada.readLine();
                                boolean resultado = _datosGuardablesJSONServidor.guardar(apodo);
                                _datosGuardablesJSONServidor.guardar();
                                salida.println(resultado);
                                break;
                            case EXISTE_APODO:
                                apodo = entrada.readLine();
                                salida.println(_datosGuardablesJSONServidor.existeApodo(apodo));
                                break;

                            default:
                                break;
                        }

                    }
                } catch (IOException e) {
                    System.out.println("Error en la comunicación con el cliente");
                    e.printStackTrace();
                }


                try {
                    cliente.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar la conexión con el cliente");
                    e.printStackTrace();
                }
                _clientes.remove(cliente);
            }
        }).start();
    }


    public static DatosGuardablesJSONServidor get_datosGuardablesJSONServidor() {
        return _datosGuardablesJSONServidor;
    }

    public static void set_datosGuardablesJSONServidor(DatosGuardablesJSONServidor _datosGuardablesJSONServidor) {
        ServidorTCP._datosGuardablesJSONServidor = _datosGuardablesJSONServidor;
    }

    public static List<Socket> get_clientes() {
        return _clientes;
    }

    public static void set_clientes(List<Socket> _clientes) {
        ServidorTCP._clientes = _clientes;
    }
}