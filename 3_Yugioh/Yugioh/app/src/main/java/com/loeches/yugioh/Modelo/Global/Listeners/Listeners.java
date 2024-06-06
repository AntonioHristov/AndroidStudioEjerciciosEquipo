package com.loeches.yugioh.Modelo.Global.Listeners;

import com.loeches.yugioh.Vista.Interfaces.StaticMethodListener;

import java.util.ArrayList;
import java.util.List;

public class Listeners {
    private static List<StaticMethodListener> listeners = new ArrayList<>();

    // Método para añadir un listener
    public static void addStaticMethodListener(StaticMethodListener listener) {
        listeners.add(listener);
    }

    // Método para eliminar un listener
    public static void removeStaticMethodListener(StaticMethodListener listener) {
        listeners.remove(listener);
    }

    public static void nuevoTurno() {
        // Lógica del método estático
        System.out.println("Operación estática ejecutada");

        // Notificar a todos los listeners registrados
        for (StaticMethodListener listener : listeners) {
            listener.onStaticMethodCalled();
        }
    }
}
