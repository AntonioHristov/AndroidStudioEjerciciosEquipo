package Modelos.Dudoso;

import java.net.Socket;

public class Cliente {
    private Socket _socket;
    private Apodo _apodo;


    public Cliente(Socket _socket, Apodo _apodo) {
        this._socket = _socket;
        this._apodo = _apodo;
        Clientes.AddClienteIfNotExist(this);
    }

    public Socket get_socket() {
        return _socket;
    }

    public void set_socket(Socket _socket) {
        this._socket = _socket;
    }

    public Apodo get_apodo() {
        return _apodo;
    }

    public void set_apodo(Apodo _apodo) {
        this._apodo = _apodo;
    }
}
