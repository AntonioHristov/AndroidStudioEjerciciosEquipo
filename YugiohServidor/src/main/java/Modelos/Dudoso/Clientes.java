package Modelos.Dudoso;

import java.util.ArrayList;
import java.util.List;

public class Clientes {
    private static List<Cliente> _clientes=new ArrayList<>();


    public static boolean AddClienteIfNotExist(Cliente cliente){
        for (Cliente clienteList:_clientes){
            if(cliente.equals(clienteList)){
                return false;
            }
        }
        _clientes.add(cliente);
        return true;
    }



    public static List<Cliente> get_clientes() {
        return _clientes;
    }

    public static void set_clientes(List<Cliente> _clientes) {
        Clientes._clientes = _clientes;
    }
}
