package com.loeches.yugioh.Modelo.Vista;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.loeches.yugioh.Controlador.Controlador;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AHechizo;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Global.Enums.EAccionHechizo;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Global.Global;
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
        if (Global.getBy(_id) == null) {
            Global.get_horizontalesVista().add(this);
        }
    }

    public void EscribirCodigoXML(boolean ordenInverso) {
        //set_llHorizontal();
        if (ordenInverso) {
            for (int i = _cartasVista.size() - 1; i > -1; i--) {
                _cartasVista.get(i).EscribirCodigoXML();
            }
        } else {
            for (CartaVista cv : _cartasVista) {
                cv.EscribirCodigoXML();
            }
        }
        crearListenerCartaVistas();
        XMLScroll();
    }

    public void XMLScroll() {
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(Global.get_context());
        horizontalScrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1
        ));

        horizontalScrollView.addView(_llHorizontal);
        Global.get_linearMain().addView(horizontalScrollView);
        _llHorizontal = crear_llHorizontal();
    }

    public LinearLayout crear_llHorizontal() {
        LinearLayout llHorizontal = new LinearLayout(Global.get_context());
        LinearLayout.LayoutParams llHParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        llHorizontal.setOrientation(LinearLayout.HORIZONTAL);
        llHorizontal.setLayoutParams(llHParams);
        return llHorizontal;
    }


    public void crearListenerCartaVistas() {
        boolean turno = esSuTurno(); // turno es para evitar ifs cada vez

        int posPrimerVacio = Global.POS_ERROR;
        for (int i = 0; i < _cartasVista.size(); i++) {
            CartaVista cv = _cartasVista.get(i);
            if (cv.igualImagenVacia()) {
                //if(cv.get_imageView().getDrawable().getConstantState().equals(ContextCompat.getDrawable(Variables.get_gameActivityContext(), R.drawable.carta_vacia).getConstantState())){
                posPrimerVacio = i;
                break;
            }

            cv.get_frameLayout().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                    v.startDrag(data, shadowBuilder, v, 0);
                    v.setVisibility(View.VISIBLE);
                    return true;
                }
            });

            cv.get_frameLayout().setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            // Acción al iniciar el arrastre
                            return true;
                        case DragEvent.ACTION_DRAG_EXITED:
                            // Acción al salir de la vista destino
                            cv.verInformacion();
                            return true;
                        default:
                            return false;
                    }
                }
            });


            if (turno) {
                cv.get_frameLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (esMano()) {
                            if (Global.get_cartaVistaSeleccionada() == null) {
                                if (cv.get_carta() instanceof AMonstruo) {
                                    CartaVista posibleDestino = getHVMonstruoMismoTurno().getPrimerVacio();
                                    if (posibleDestino != null) {
                                        cv.cambiarCartaVista(posibleDestino, true);
                                        Controlador.nuevoTurno();
                                    } else {
                                        Controlador.nuevoTurno();
                                    }
                                } else if (cv.get_carta() instanceof AHechizo && ((AHechizo) cv.get_carta()).get_accionHechizo() == EAccionHechizo.EQUIPAR) {
                                    CartaVista posibleDestino = getHVHechizoMismoTurno().getPrimerVacio();
                                    if (posibleDestino != null) {
                                        cv.cambiarCartaVista(posibleDestino, true);
                                        Controlador.nuevoTurno();
                                    } else {
                                        Controlador.nuevoTurno();
                                    }
                                } else {
                                    cv.seleccionarOQuitarSeleccionNoVacias();
                                }
                            } else {
                                cv.seleccionarOQuitarSeleccionNoVacias();
                            }
                        } else if (cv.get_carta() instanceof AMonstruo) {
                            if (Global.get_cartaVistaSeleccionada() == null) {
                                if (getHVRival().get_cartasVista().get(0).igualImagenVacia()) {
                                    // SI EL RIVAL NO TIENE MONSTRUO ATACA AL JUGADOR
                                    cv.get_carta().RealizarAccion(null);
                                    if (cv.get_carta().is_nuevoTurnoTrasRealizarAccion()) {
                                        Controlador.nuevoTurno();
                                    }
                                } else if (getHVRival().get_cartasVista().get(1).igualImagenVacia()) {
                                    // SI EL RIVAL SOLO TIENE 1 MONSTRUO, LO ATACA SIN NECESIDAD DE SELECCIONARLO
                                    cv.get_carta().RealizarAccion((AMonstruo) getHVRival().get_cartasVista().get(0).get_carta());
                                    if (cv.get_carta().is_nuevoTurnoTrasRealizarAccion()) {
                                        Controlador.nuevoTurno();
                                    }
                                } else {
                                    cv.seleccionarOQuitarSeleccionNoVacias();
                                }

                            } else if (Global.get_cartaVistaSeleccionada().get_carta() instanceof AHechizo) {

                                Global.get_cartaVistaSeleccionada().get_carta().RealizarAccion((AMonstruo) cv.get_carta());

                                if (((AHechizo) Global.get_cartaVistaSeleccionada().get_carta()).get_accionHechizo() == EAccionHechizo.EQUIPAR) {
                                    boolean nuevoTurno = Global.get_cartaVistaSeleccionada().get_carta().is_nuevoTurnoTrasRealizarAccion();
                                    Global.get_cartaVistaSeleccionada().convertirseVacio(true);
                                    if (nuevoTurno) {
                                        Controlador.nuevoTurno();
                                    } else {
                                        Controlador.ActualizarVistaCartas();
                                    }
                                } else {//if(((AHechizo) Global.get_cartaVistaSeleccionada().get_carta()).get_accionHechizo()==EAccionHechizo.USAR)
                                    Global.get_cartaVistaSeleccionada().get_horizontalVista()._cartasVista.remove(Global.get_cartaVistaSeleccionada());
                                    if (Global.get_cartaVistaSeleccionada().get_carta().is_nuevoTurnoTrasRealizarAccion()) {
                                        Controlador.nuevoTurno();
                                    } else {
                                        Global.set_cartaVistaSeleccionada(null);
                                        Controlador.ActualizarVistaCartas();
                                    }
                                }

                            } else {
                                cv.seleccionarOQuitarSeleccionNoVacias();
                            }
                        } else/* if(cv.get_carta() instanceof AHechizo)*/ {
                            cv.seleccionarOQuitarSeleccionNoVacias();
                        }
                    }
                });
                if (!esMano() && esSuTurno() && cv.get_carta() instanceof AMonstruo) {
                    AMonstruo cvMonstruo = (AMonstruo) cv.get_carta();
                    cv.get_frameLayout().setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            //cv.verInformacion();
                            AlertMonstruoInfoCambiarModo(cv);
                            return true;
                        }
                    });
                } else if (esMano() && cv.get_carta() instanceof AHechizo && ((AHechizo) cv.get_carta()).get_accionHechizo() == EAccionHechizo.USAR) {
                    cv.get_frameLayout().setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            //cv.verInformacion();
                            AlertHechizoInfoUsar(cv);
                            return true;
                        }
                    });
                }
            } else {
                cv.get_frameLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Global.get_cartaVistaSeleccionada() != null && cv.get_carta() instanceof AMonstruo) {
                            if (!Global.get_cartaVistaSeleccionada().get_horizontalVista().esMano() || (Global.get_cartaVistaSeleccionada().get_horizontalVista().esMano() && Global.get_cartaVistaSeleccionada().get_carta() instanceof AHechizo && ((AHechizo) Global.get_cartaVistaSeleccionada().get_carta()).get_accionHechizo() == EAccionHechizo.USAR)) {
                                Global.get_cartaVistaSeleccionada().get_carta().RealizarAccion((AMonstruo) cv.get_carta());
                                if (Global.get_cartaVistaSeleccionada().get_horizontalVista().esMano()) {
                                    Global.get_cartaVistaSeleccionada().get_horizontalVista()._cartasVista.remove(Global.get_cartaVistaSeleccionada());
                                }
                                if (Global.get_cartaVistaSeleccionada().get_carta().is_nuevoTurnoTrasRealizarAccion()) {
                                    Controlador.nuevoTurno();
                                }
                            }
                        }
                    }
                });
            }

        }
        // NO ELIMINAR posPrimerVacio. PLANEO DESCOMENTARLO CUANDO SE PUEDA PERSONALIZAR EL MODO DESTINO AUTOMÁTICO A FALSE (EN TRUE POR DEFECTO ELIJE EL 1º VACIO AUTOMÁTICAMENTE SI EXISTE)
        /*
        if (posPrimerVacio != Global.POS_ERROR && posPrimerVacio < _cartasVista.size()) {
            CartaVista primerVacio = _cartasVista.get(posPrimerVacio);
            if (primerVacio != null) {
                if (turno) {

                    primerVacio.get_frameLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Global.get_cartaVistaSeleccionada() != null &&((Global.get_cartaVistaSeleccionada().get_carta() instanceof AMonstruo && esMonstruo()) || (Global.get_cartaVistaSeleccionada().get_carta() instanceof AHechizo && ((AHechizo) Global.get_cartaVistaSeleccionada().get_carta()).get_accionHechizo() == EAccionHechizo.EQUIPAR && esHechizo()))) {
                                Global.get_cartaVistaSeleccionada().cambiarCartaVista(primerVacio, true);
                                Controlador.nuevoTurno();
                            }
                        }
                    });
                }
            }
        }
         */
    }

    public HorizontalVista getHVRival() {
        if (this._id == EIdHorizontalVista.J1_MANO) {
            return Global.getBy(EIdHorizontalVista.J2_MANO);
        } else if (this._id == EIdHorizontalVista.J2_MANO) {
            return Global.getBy(EIdHorizontalVista.J1_MANO);
        } else if (this._id == EIdHorizontalVista.J1_HECHIZO) {
            return Global.getBy(EIdHorizontalVista.J2_HECHIZO);
        } else if (this._id == EIdHorizontalVista.J2_HECHIZO) {
            return Global.getBy(EIdHorizontalVista.J1_HECHIZO);
        } else if (this._id == EIdHorizontalVista.J1_MONSTRUO) {
            return Global.getBy(EIdHorizontalVista.J2_MONSTRUO);
        } else if (this._id == EIdHorizontalVista.J2_MONSTRUO) {
            return Global.getBy(EIdHorizontalVista.J1_MONSTRUO);
        } else {
            return null;
        }
    }

    public HorizontalVista getHVManoMismoTurno() {
        switch (_id) {
            case J1_MANO:
            case J1_HECHIZO:
            case J1_MONSTRUO:
                return Global.getBy(EIdHorizontalVista.J1_MANO);
            case J2_MANO:
            case J2_HECHIZO:
            case J2_MONSTRUO:
                return Global.getBy(EIdHorizontalVista.J2_MANO);
        }
        return null;
    }

    public HorizontalVista getHVHechizoMismoTurno() {
        switch (_id) {
            case J1_MANO:
            case J1_HECHIZO:
            case J1_MONSTRUO:
                return Global.getBy(EIdHorizontalVista.J1_HECHIZO);
            case J2_MANO:
            case J2_HECHIZO:
            case J2_MONSTRUO:
                return Global.getBy(EIdHorizontalVista.J2_HECHIZO);
        }
        return null;
    }

    public HorizontalVista getHVMonstruoMismoTurno() {
        switch (_id) {
            case J1_MANO:
            case J1_HECHIZO:
            case J1_MONSTRUO:
                return Global.getBy(EIdHorizontalVista.J1_MONSTRUO);
            case J2_MANO:
            case J2_HECHIZO:
            case J2_MONSTRUO:
                return Global.getBy(EIdHorizontalVista.J2_MONSTRUO);
        }
        return null;
    }

    public CartaVista getPrimerBy(Drawable.ConstantState imagen) {
        for (CartaVista cv : _cartasVista) {
            if (cv.igualImagen(imagen)) {
                return cv;
            }
        }
        return null;
    }

    public CartaVista getPrimerVacio() {
        return getPrimerBy(ContextCompat.getDrawable(Global.get_context(), R.drawable.carta_vacia).getConstantState());
    }

    public boolean esSuTurno() {
        return (Global.is_turnoJugador1() && (_id == EIdHorizontalVista.J1_MANO || _id == EIdHorizontalVista.J1_HECHIZO || _id == EIdHorizontalVista.J1_MONSTRUO)) || (!Global.is_turnoJugador1() && (_id == EIdHorizontalVista.J2_MANO || _id == EIdHorizontalVista.J2_HECHIZO || _id == EIdHorizontalVista.J2_MONSTRUO));
    }

    public boolean esMano() {
        return _id == EIdHorizontalVista.J1_MANO || _id == EIdHorizontalVista.J2_MANO;
    }

    public boolean esHechizo() {
        return _id == EIdHorizontalVista.J1_HECHIZO || _id == EIdHorizontalVista.J2_HECHIZO;
    }

    public boolean esMonstruo() {
        return _id == EIdHorizontalVista.J1_MONSTRUO || _id == EIdHorizontalVista.J2_MONSTRUO;
    }

    public void AlertMonstruoInfoCambiarModo(CartaVista cv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Global.get_activity());
        builder.setTitle("Elección");
        builder.setMessage("¿Qué opción eliges?");

        // Botón positivo (Opción 1)
        builder.setPositiveButton("Ver información", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción para la opción 1
                cv.verInformacion();
            }
        });

        // Botón negativo (Opción 2)
        builder.setNegativeButton("Cambiar modo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AMonstruo cvMonstruo = (AMonstruo) cv.get_carta();
                // Acción para la opción 2
                if (cvMonstruo.is_modoDefensa()) {
                    cvMonstruo.set_modoDefensa(false);
                } else {
                    cvMonstruo.set_modoDefensa(true);
                }
                Controlador.nuevoTurno();
            }
        });

        // Crear y mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void AlertHechizoInfoUsar(CartaVista cv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Global.get_activity());
        builder.setTitle("Elección");
        builder.setMessage("¿Qué opción eliges?");

        // Botón positivo (Opción 1)
        builder.setPositiveButton("Ver información", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción para la opción 1
                cv.verInformacion();
            }
        });

        // Botón negativo (Opción 2)
        builder.setNegativeButton("Usar Hechizo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cv.get_carta().RealizarAccion(null);
                cv.get_horizontalVista()._cartasVista.remove(cv);
                if (cv.get_carta().is_nuevoTurnoTrasRealizarAccion()) {
                    Controlador.nuevoTurno();
                }
            }
        });

        // Crear y mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void ordenar() {
        for (int i = 0; i < get_cartasVista().size() - 1; i++) {
            for (int j = 0; j < (get_cartasVista().size() - 1 - i); j++) {
                if (!get_cartasVista().get(j + 1).igualImagenVacia()) {
                    CartaVista aux = get_cartasVista().get(j);
                    get_cartasVista().set(j, get_cartasVista().get(j + 1));
                    get_cartasVista().set(j + 1, aux);
                }
            }
        }
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
