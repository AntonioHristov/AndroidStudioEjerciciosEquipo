package Main;

import java.io.*;
import java.net.Socket;

public class ComunicacionPartida extends Thread {
    Socket jugador1;
    Socket jugador2;

    public ComunicacionPartida(Socket jugador1, Socket jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    @Override
    public void run() {
        try (ObjectInputStream entradaJug1 = new ObjectInputStream(jugador1.getInputStream());
             ObjectOutputStream salidaJug1 = new ObjectOutputStream(jugador1.getOutputStream());
             BufferedReader entradaJug2 = new BufferedReader(new InputStreamReader(jugador2.getInputStream()));
             ObjectOutputStream salidaJug2 = new ObjectOutputStream(jugador2.getOutputStream())) {
            boolean completado = false;
            do {
                String comando = entradaJug1.readUTF();
                if (comando.equals("jugada")) {
                    Object datos = entradaJug1.readObject();
                    salidaJug2.writeUTF("jugada");
                    salidaJug2.writeObject(datos);
                    salidaJug2.flush();
                }


            } while (!completado);

            jugador1.close();
            jugador2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
