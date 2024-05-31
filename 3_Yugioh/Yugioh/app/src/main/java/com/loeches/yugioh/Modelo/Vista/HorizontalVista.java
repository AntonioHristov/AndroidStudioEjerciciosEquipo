package com.loeches.yugioh.Modelo.Vista;

import android.app.Activity;
import android.content.Context;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.loeches.yugioh.Controlador.Utilidades;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Global.Variables;
import com.loeches.yugioh.R;

import java.util.ArrayList;
import java.util.List;

public class HorizontalVista {
    private EIdHorizontalVista _id;
    private LinearLayout _llHorizontal;
    private List<CartaVista> _cartasVista;


    public HorizontalVista(EIdHorizontalVista id) {
        this._id = id;
        set_llHorizontal();
        this._cartasVista = new ArrayList<>();
        if(Lista.getBy(_id)==null){
            Lista.get_horizontalesVista().add(this);
        }
    }

    public void EscribirCodigoXML(){
        LinearLayout main=((Activity)Variables.get_gameActivityContext()).findViewById(R.id.main);

        for (CartaVista cv:_cartasVista) {
            cv.EscribirCodigoXML();
        }

        if(_cartasVista.size()>5){
            XMLScroll();
        }else{
            main.addView(_llHorizontal);
        }
    }

    public void XMLScroll(){
        Context context=Variables.get_gameActivityContext();
        LinearLayout main=((Activity)context).findViewById(R.id.main);

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        horizontalScrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Utilidades.getAltoTelefonoPx()/5-50,
                1 // Peso igual a 1 para llenar el espacio restante
        ));

        horizontalScrollView.addView(_llHorizontal);
        main.addView(horizontalScrollView);
        _llHorizontal= crear_llHorizontal();
    }

    public EIdHorizontalVista get_id() {
        return _id;
    }

    public LinearLayout get_llHorizontal() {
        return _llHorizontal;
    }

    public void set_llHorizontal() {
        // PROBLEMAS CON XMLScroll SI JUNTO AMBAS FUNCIONES EN UNA
        _llHorizontal = crear_llHorizontal();
    }

    public LinearLayout crear_llHorizontal(){
        LinearLayout llHorizontal = new LinearLayout(Variables.get_gameActivityContext());
        LinearLayout.LayoutParams llHParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Utilidades.getAltoTelefonoPx()/5-50
        );
        llHorizontal.setOrientation(LinearLayout.HORIZONTAL);
        //llHParams.weight=1;
        llHorizontal.setLayoutParams(llHParams);
        return llHorizontal;
    }

    public List<CartaVista> get_cartasVista() {
        return _cartasVista;
    }
}
