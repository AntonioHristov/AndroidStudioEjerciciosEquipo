package com.loeches.yugioh.Modelo.Vista;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.loeches.yugioh.Controlador.Utilidades;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
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
        if (Lista.getBy(_id) == null) {
            Lista.get_horizontalesVista().add(this);
        }
    }

    public void EscribirCodigoXML() {
        LinearLayout main = ((Activity) Variables.get_gameActivityContext()).findViewById(R.id.main);

        for (CartaVista cv : _cartasVista) {
            cv.EscribirCodigoXML();
        }
        crearOnClickCartaVistas();

        if (_cartasVista.size() > 5) {
            XMLScroll();
        } else {
            main.addView(_llHorizontal);
        }
    }

    public void XMLScroll() {
        Context context = Variables.get_gameActivityContext();
        LinearLayout main = ((Activity) context).findViewById(R.id.main);

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        horizontalScrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Utilidades.getAltoTelefonoPx() / 5 - 50,
                1 // Peso igual a 1 para llenar el espacio restante
        ));

        horizontalScrollView.addView(_llHorizontal);
        main.addView(horizontalScrollView);
        _llHorizontal = crear_llHorizontal();
    }

    public LinearLayout crear_llHorizontal() {
        LinearLayout llHorizontal = new LinearLayout(Variables.get_gameActivityContext());
        LinearLayout.LayoutParams llHParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                Utilidades.getAltoTelefonoPx() / 5 - 50
        );
        llHorizontal.setOrientation(LinearLayout.HORIZONTAL);
        //llHParams.weight=1;
        llHorizontal.setLayoutParams(llHParams);
        return llHorizontal;
    }


    public void crearOnClickCartaVistas() {
        boolean turno=esSuTurno(); // turno es para evitar ifs cada vez

        int posPrimerVacio = Variables.POS_ERROR;
        for (int i = 0; i < _cartasVista.size(); i++) {
            CartaVista cv = _cartasVista.get(i);
            if (cv.igualImagenVacia()) {
            //if(cv.get_imageView().getDrawable().getConstantState().equals(ContextCompat.getDrawable(Variables.get_gameActivityContext(), R.drawable.carta_vacia).getConstantState())){
                posPrimerVacio = i;
                break;
            }
            if(turno){
                cv.get_frameLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // SI ES UN MONSTRUO Y EL RIVAL NO TIENE MONSTRUO, ATACA AL JUGADOR RIVAL
                        if(esMano()){
                            cv.seleccionarOQuitarSeleccionNoVacias();
                        }else if(cv.get_carta() instanceof AMonstruo){
                            if(Variables.get_cartaVistaSeleccionada()==null&& (Lista.getBy(getHorizontalRival()).get_cartasVista().get(0).igualImagenVacia())  ){
                                // SI EL RIVAL NO TIENE MONSTRUO ATACA AL JUGADOR
                                cv.get_carta().RealizarAccion(null);
                                Variables.nuevoTurno();
                            }else if(Variables.get_cartaVistaSeleccionada()!=null&&Variables.get_cartaVistaSeleccionada().get_carta() instanceof AHechizo){
                                Variables.get_cartaVistaSeleccionada().get_carta().RealizarAccion((AMonstruo) cv.get_carta());
                                Variables.get_cartaVistaSeleccionada().get_horizontalVista().get_cartasVista().get(Lista.getPosHorizontalVista(Variables.get_cartaVistaSeleccionada())).set_carta(new CartaVacia());
                                Variables.set_cartaVistaSeleccionada(null);
                                Variables.nuevoTurno();
                            }else{
                                cv.seleccionarOQuitarSeleccionNoVacias();
                            }
                        }else/* if(cv.get_carta() instanceof AHechizo)*/{
                            cv.seleccionarOQuitarSeleccionNoVacias();
                        }
                    }
                });
            }else{
                cv.get_frameLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Variables.get_cartaVistaSeleccionada()!=null && cv.get_carta()instanceof AMonstruo){
                            Variables.get_cartaVistaSeleccionada().get_carta().RealizarAccion((AMonstruo) cv.get_carta());
                            Variables.nuevoTurno();
                        }
                    }
                });
            }

        }
        if (posPrimerVacio != Variables.POS_ERROR && posPrimerVacio < _cartasVista.size()) {
            CartaVista primerVacio = _cartasVista.get(posPrimerVacio);
            if (primerVacio != null) {
                if(turno){
                    primerVacio.get_frameLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Variables.get_cartaVistaSeleccionada() != null && (Variables.get_cartaVistaSeleccionada().get_carta() instanceof AMonstruo && (_id==EIdHorizontalVista.J1_MONSTRUO||_id==EIdHorizontalVista.J2_MONSTRUO)) || (Variables.get_cartaVistaSeleccionada().get_carta() instanceof AHechizo && ((AHechizo) Variables.get_cartaVistaSeleccionada().get_carta()).get_accionHechizo() == EAccionHechizo.EQUIPAR && (_id==EIdHorizontalVista.J1_HECHIZO||_id==EIdHorizontalVista.J2_HECHIZO))) {
                                Variables.get_cartaVistaSeleccionada().cambiarCartaVista(primerVacio, true);
                                Variables.nuevoTurno();
                            }
                        }
                    });
                }
            }
        }
    }

    public EIdHorizontalVista getHorizontalRival() {
        if(this._id==EIdHorizontalVista.J1_MANO){
            return EIdHorizontalVista.J2_MANO;
        }else if(this._id==EIdHorizontalVista.J2_MANO){
            return EIdHorizontalVista.J1_MANO;
        }else if(this._id==EIdHorizontalVista.J1_HECHIZO){
            return EIdHorizontalVista.J2_HECHIZO;
        }else if(this._id==EIdHorizontalVista.J2_HECHIZO){
            return EIdHorizontalVista.J1_HECHIZO;
        }else if(this._id==EIdHorizontalVista.J1_MONSTRUO){
            return EIdHorizontalVista.J2_MONSTRUO;
        }else if(this._id==EIdHorizontalVista.J2_MONSTRUO){
            return EIdHorizontalVista.J1_MONSTRUO;
        }else{
            return null;
        }
    }

    public boolean esSuTurno(){
        return (Variables.is_turnoJugador1() && (_id == EIdHorizontalVista.J1_MANO || _id == EIdHorizontalVista.J1_HECHIZO || _id == EIdHorizontalVista.J1_MONSTRUO)) || (!Variables.is_turnoJugador1() && (_id == EIdHorizontalVista.J2_MANO || _id == EIdHorizontalVista.J2_HECHIZO || _id == EIdHorizontalVista.J2_MONSTRUO));
    }

    public boolean esMano(){
        return _id==EIdHorizontalVista.J1_MANO||_id==EIdHorizontalVista.J2_MANO;
    }

    public boolean esHechizo(){
        return _id==EIdHorizontalVista.J1_HECHIZO||_id==EIdHorizontalVista.J2_HECHIZO;
    }

    public boolean esMonstruo(){
        return _id==EIdHorizontalVista.J1_MONSTRUO||_id==EIdHorizontalVista.J2_MONSTRUO;
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

    public List<CartaVista> get_cartasVista() {
        return _cartasVista;
    }
}
