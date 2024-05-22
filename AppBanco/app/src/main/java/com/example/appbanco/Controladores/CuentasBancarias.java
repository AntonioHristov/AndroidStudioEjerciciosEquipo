package com.example.appbanco.Controladores;

import com.example.appbanco.Modelos.CuentaBancaria;

import java.util.ArrayList;
import java.util.List;

public class CuentasBancarias {
    private static List<CuentaBancaria> cuentasBancarias=new ArrayList<>();

    public static List<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }

    public static CuentaBancaria getCuentaById(int idCUenta){
        for (CuentaBancaria cuenta: cuentasBancarias) {
            if(cuenta.getIdentificador()==idCUenta){
                return cuenta;
            }
        }
        return null;
    }

    public static List<Integer> getIdsCuentasBancarias() {
        List<Integer> ids = new ArrayList<>();
        for (CuentaBancaria cuenta: cuentasBancarias) {
            ids.add(cuenta.getIdentificador());
        }
        return ids;
    }

    public static void NuevaCuenta(){
        CuentasBancarias.getCuentasBancarias().add(new CuentaBancaria());
    }
    public static void NuevaCuenta(int cantidad){
        for (int i = 0; i < cantidad; i++) {
            CuentasBancarias.getCuentasBancarias().add(new CuentaBancaria());
        }
    }
}
