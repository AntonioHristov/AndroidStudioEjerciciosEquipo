package com.loeches.yugioh.DAO.Interfaces;

public interface IDatosGuardablesDAO {
    void restaurarValoresDefecto();
    boolean hayDatos();
    boolean cargar();
    void guardarSiHayDatosGuardados();
    void guardar();
    void borrar();
}
