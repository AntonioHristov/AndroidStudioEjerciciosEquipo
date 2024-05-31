package com.loeches.yugioh.Modelo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loeches.yugioh.Controlador.Utilidades;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Global.Variables;
import com.loeches.yugioh.Modelo.Vista.CartaVista;
import com.loeches.yugioh.R;

public class Jugador {
    private LinearLayout _llHContenedor;
    private TextView _tvVista;
    private String _textoPrefijoMostrarVida;
    private int _vida;



    public Jugador(String textoMostrarVida,int vida) {
        _textoPrefijoMostrarVida =textoMostrarVida;
        set_tvVistaYllHContenedor();
        _vida = vida;
        Lista.get_jugadores().add(this);
    }
    public Jugador(String textoMostrarVida) {
        _textoPrefijoMostrarVida =textoMostrarVida;
        set_tvVistaYllHContenedor();
        set_vida(8000);
        Lista.get_jugadores().add(this);
    }

    public Jugador() {
        _textoPrefijoMostrarVida ="";
        set_tvVistaYllHContenedor();
        set_vida(8000);
        Lista.get_jugadores().add(this);
    }


    public void EscribirCodigoXML(){
        LinearLayout main=((Activity)Variables.get_gameActivityContext()).findViewById(R.id.main);

        // SI SE HIZO main.removeAllViews(); GENERA DE NUEVO LAS VISTAS PERDIDAS. MISMA LOGICA EN HorizontalVista Y CartaVista
        if(_tvVista.getParent()==null||_llHContenedor.getParent()==null){
            set_tvVistaYllHContenedor();
        }

        main.addView(_llHContenedor);
        _llHContenedor.addView(_tvVista);// ESTO DA ERROR A LA 2º VEZ QUE SE EJECUTA Controlador.ActualizarVistaCartas(); PERO NO A LA 1º
    }



    public TextView get_tvVista() {
        return _tvVista;
    }

    public void set_tvVistaYllHContenedor() {
        Context context = Variables.get_gameActivityContext();
        LinearLayout llHorizontal = new LinearLayout(context);
        LinearLayout.LayoutParams llHParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        llHorizontal.setOrientation(LinearLayout.HORIZONTAL);
        llHorizontal.setGravity(Gravity.CENTER);
        llHorizontal.setLayoutParams(llHParams);

        TextView tv = new TextView(context);
        tv.setText(_textoPrefijoMostrarVida+_vida);
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        // Crear nuevos LayoutParams para el TextView
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        tv.setLayoutParams(tvParams);
        _tvVista=tv;
        _llHContenedor = llHorizontal;
    }

    public String get_textoPrefijoMostrarVida() {
        return _textoPrefijoMostrarVida;
    }

    public void set_textoPrefijoMostrarVida(String textoMostrarVida) {
        _textoPrefijoMostrarVida = textoMostrarVida;
    }

    public int get_vida() {
        return _vida;
    }

    public void set_vida(int vida) {
        _vida = vida;
        _tvVista.setText(_textoPrefijoMostrarVida+_vida);
    }


}
