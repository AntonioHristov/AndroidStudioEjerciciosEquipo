package com.example.contactos.controlador;

import com.example.contactos.modelos.Contacto;

import java.util.ArrayList;
import java.util.List;

public class Contactos {
    public static List<Contacto> contactos = new ArrayList<>();

    public static String getNombre(int i) {
        return contactos.get(i).getNombre();
    }

    public static String getTelefono(int i) {
        return contactos.get(i).getTelefono();
    }

    public static void setNombre(int i,String valor) {
        contactos.get(i).setNombre(valor);
    }

    public static void setTelefono(int i,String valor) {
        contactos.get(i).setTelefono(valor);
    }

    public static void addContacto(String nombre, String telefono){
        if(!nombre.trim().isEmpty() && !existName(nombre)){
            contactos.add(new Contacto(nombre,telefono));
        }
    }

    public static boolean existName(String nombre){
        for (Contacto contacto:contactos) {
            if(contacto.getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }
    public static void InicializarContactos(){
        for (int i = 0; i < 20; i++) {
            addContacto("Antonio"+i,"472"+i);
        }
    }
}
