package Main;

import DAO.Implementaciones.DatosGuardablesJSONApodosServidor;
import Enumerados.ECodigosTCP;

import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    public final static String RUTA_Y_ARCHIVO_JSON = "./datosServer.json";
    public final static int PUERTO = 83;

    private static DatosGuardablesJSONApodosServidor _datosGuardablesJSONServidor;
    private static Map<String, Socket> _clientes = new HashMap<>();
    private static Map<String, String> _partidas = new HashMap<>();

    public static Set<String> jugadoresEsp = new HashSet<>();
    public static String[] jugadoresEnJuego = new String[2];

    public static void main(String[] args) {
        set_datosGuardablesJSONServidor(new DatosGuardablesJSONApodosServidor());
        _datosGuardablesJSONServidor.cargarDesdeJSON();

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                try {
                    Socket cliente = servidor.accept();

                    System.out.println("Modelos.Dudoso.Cliente conectado: " + cliente.getInetAddress());

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

                    String codigo = entrada.readLine();
                    if (codigo != null) {
                        System.out.println("CODIGO: " + codigo);
                        ECodigosTCP codigoTCP = ECodigosTCP.valueOf(codigo);
                        //System.out.println("CODIGO PARSEADO: "+codigoTCP.name());
                        switch (codigoTCP) {
                            case REGISTRARSE:
                                String apodo = entrada.readLine();
                                boolean resultado = _datosGuardablesJSONServidor.guardar(apodo);
                                salida.println(resultado);
                                break;
                            case LOGEARSE:
                                apodo = entrada.readLine();
                                salida.println(_datosGuardablesJSONServidor.loginValido(apodo));
                                break;
                            case EXISTE_APODO:
                                apodo = entrada.readLine();
                                salida.println(_datosGuardablesJSONServidor.existeApodo(apodo));
                                break;
                            case LOGOUT:
                                apodo = entrada.readLine();
                                salida.println(_datosGuardablesJSONServidor.logout(apodo));
                                break;
                            case EN_ESPERA:
                                apodo = entrada.readLine();
                                System.out.println("Espera " + apodo);
                                for (String jug : jugadoresEsp) {
                                    System.out.println("Lista " + jug);
                                    if (!apodo.equals(jug)) {
                                        salida.println(jug);
                                        System.out.println("Enviando " + jug);
                                    }
                                }
                                salida.println("fin");
                                System.out.println("FIN");
                                jugadoresEsp.add(apodo);
                                break;
                            case JUGADORES_EN_RED:
                                apodo = entrada.readLine();
                                System.out.println("Jugadores En Red " + apodo);
                                switch (jugadoresEnJuego.length) {
                                    case 0:
                                    case 2:
                                        jugadoresEnJuego[0] = apodo;
                                        jugadoresEnJuego[1] = null;
                                        break;
                                    case 1:
                                        jugadoresEnJuego[1] = apodo;
                                        break;
                                }

                                for (String jug : jugadoresEnJuego) {
                                    System.out.println("Lista " + jug);
                                    salida.println(jug);
                                }
                                salida.println("fin");
                                System.out.println("FIN");

                                break;
                            case INICIAR_PARTIDA:
                                String jug1 = entrada.readLine();
                                String jug2 = entrada.readLine();
                                _clientes.put(jug1, cliente);
                                while (!_clientes.containsKey(jug2) && !(_partidas.containsKey(jug1) || _partidas.containsKey(jug2))) {
                                    System.out.println(jug1 + " esperando " + jug2);
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                if (!(_partidas.containsKey(jug1) || _partidas.containsKey(jug2))) {
                                    _partidas.put(jug1, jug2);
                                    ComunicacionPartida comunicacion = new ComunicacionPartida(_clientes.get(jug1), _clientes.get(jug2));
                                    comunicacion.start();
                                    System.out.println(jug1 + " JUEGO INICIADO " + jug2);
                                }

                                break;

                            default:
                                break;
                        }

                        if(codigoTCP != ECodigosTCP.INICIAR_PARTIDA){
                            cliente.close();
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
            }
        }).start();
    }


    public static DatosGuardablesJSONApodosServidor get_datosGuardablesJSONServidor() {
        return _datosGuardablesJSONServidor;
    }

    public static void set_datosGuardablesJSONServidor(DatosGuardablesJSONApodosServidor _datosGuardablesJSONServidor) {
        Main._datosGuardablesJSONServidor = _datosGuardablesJSONServidor;
    }

}