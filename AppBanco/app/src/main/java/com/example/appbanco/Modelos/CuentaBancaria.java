package com.example.appbanco.Modelos;

import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {
    private static int proximoIdentificador=0;
    private int identificador;
    private double saldo;
    private List<String> historialOperaciones;

    public CuentaBancaria() {
        identificador=proximoIdentificador;
        proximoIdentificador++;
        saldo=0;
        historialOperaciones=new ArrayList<>();
    }

    public int getIdentificador() {
        return identificador;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<String> getHistorialOperaciones() {
        return historialOperaciones;
    }


    public void ingreso(double dinero)
    {
        saldo+=dinero;
        historialOperaciones.add("Dinero recibido: "+dinero);
    }

    public boolean pagar(double dinero){
        if(saldo>=dinero){
            saldo-=dinero;
            historialOperaciones.add("Dinero perdido: "+dinero);
            return true;
        }
        historialOperaciones.add("Sin dinero suficiente intentó perder: "+dinero);
        return false;
    }

    public boolean transferencia(CuentaBancaria destino, double dinero){
        if(pagar(dinero)){
            destino.ingreso(dinero);
            historialOperaciones.add("Transferencia exitosa a la cuenta: "+destino.identificador);
            return true;
        }
        historialOperaciones.add("Transferencia fallida a la cuenta: "+destino.identificador);
        return false;
    }
}
