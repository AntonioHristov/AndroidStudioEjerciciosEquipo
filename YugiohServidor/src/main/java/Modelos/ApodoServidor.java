package Modelos;

public class ApodoServidor {
    private String _apodo;
    private boolean _sesionIniciada;


    public ApodoServidor() {
    }

    public ApodoServidor(String _apodo, boolean _sesionIniciada) {
        this._apodo = _apodo;
        this._sesionIniciada = _sesionIniciada;
    }

    public String get_apodo() {
        return _apodo;
    }

    public void set_apodo(String _apodo) {
        this._apodo = _apodo;
    }

    public boolean is_sesionIniciada() {
        return _sesionIniciada;
    }

    public void set_sesionIniciada(boolean _sesionIniciada) {
        this._sesionIniciada = _sesionIniciada;
    }
}
