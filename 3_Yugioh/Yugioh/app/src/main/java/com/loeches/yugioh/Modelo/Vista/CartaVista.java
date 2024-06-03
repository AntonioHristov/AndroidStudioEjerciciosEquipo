package com.loeches.yugioh.Modelo.Vista;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.loeches.yugioh.Controlador.Utilidades;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Global.Variables;
import com.loeches.yugioh.R;

public class CartaVista {
    private FrameLayout _frameLayout;
    private ImageView _imageView;
    private ACarta _carta;
    private HorizontalVista _horizontalVista;

    public CartaVista(HorizontalVista horizontalVista, ACarta carta) {
        Context ca = Variables.get_gameActivityContext();
        set_frameLayoutAndImageView();
        set_horizontalVista(horizontalVista);
        set_carta(carta);
    }

    public CartaVista(CartaVista copia) {
        _frameLayout=copia._frameLayout;
        _imageView=copia._imageView;
        _carta=copia._carta;
        _horizontalVista=copia._horizontalVista;
    }


    public void EscribirCodigoXML() {
        set_frameLayoutAndImageView();
        _frameLayout.addView(_imageView);
        _horizontalVista.get_llHorizontal().addView(_frameLayout);
    }

    public FrameLayout get_frameLayout() {
        return _frameLayout;
    }

    public ImageView get_imageView() {
        return _imageView;
    }

    public void set_frameLayoutAndImageView() {
        _frameLayout= crear_frameLayout();
        _imageView= crear_imageView();
    }

    public FrameLayout crear_frameLayout() {
        Context context = Variables.get_gameActivityContext();
        // Crear el FrameLayout
        FrameLayout frameLayout = new FrameLayout(context);
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(
                Utilidades.getAnchoTelefonoPx(context) / 5 - Utilidades.dpToPx(context, 5),
                //Utilidades.getAnchoTelefonoDp(context)/5-Utilidades.dpToPx(context,5),
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        frameLayoutParams.setMargins(Utilidades.dpToPx(context, 2), Utilidades.dpToPx(context, 2), Utilidades.dpToPx(context, 2), Utilidades.dpToPx(context, 2));
        frameLayout.setLayoutParams(frameLayoutParams);
        frameLayout.setPadding(Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1));
        frameLayout.setBackgroundResource(R.drawable.imageview_border_default);
        frameLayout.setRotation(0);
        return frameLayout;
    }
    public ImageView crear_imageView() {
        // Crear el ImageView
        ImageView imageView = new ImageView(Variables.get_gameActivityContext());
        FrameLayout.LayoutParams imageViewParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        imageView.setLayoutParams(imageViewParams);
        imageView.setRotation(0);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        //imageView.setImageResource(R.color.black);
        if(get_carta()==null){
            imageView.setImageResource(R.color.black);
        }else{
            imageView.setImageResource(get_carta().get_imagen());
        }

        return imageView;
    }

    public void cambiarCartaVista(CartaVista destino, boolean eliminarEsta){
        int posHvThis = Lista.getPosHorizontalVista(this), posHvDestino=Lista.getPosHorizontalVista(destino);
        HorizontalVista aux;
        this._horizontalVista.get_cartasVista().set(posHvThis,destino);
        destino._horizontalVista.get_cartasVista().set(posHvDestino,this);
        aux=this._horizontalVista;
        this._horizontalVista=destino._horizontalVista;
        destino._horizontalVista=aux;
        if(eliminarEsta){
            destino._horizontalVista.get_cartasVista().remove(destino);
        }
    }

    public void seleccionarOQuitarSeleccionNoVacias(){
        Context context = Variables.get_gameActivityContext();
        if (!this.get_imageView().getDrawable().getConstantState().equals(ContextCompat.getDrawable(context, R.drawable.carta_vacia).getConstantState())) {
            if( Variables.get_cartaVistaSeleccionada()==null||Variables.get_cartaVistaSeleccionada()==this ){
                if (this.get_frameLayout().getBackground().getConstantState().equals(ContextCompat.getDrawable(context, R.drawable.imageview_border_default).getConstantState())) {
                    // Los drawables son iguales
                    this.get_frameLayout().setBackgroundResource(R.drawable.imageview_border_selected);
                    this.get_frameLayout().setPadding(Utilidades.dpToPx(context, 5), Utilidades.dpToPx(context, 5), Utilidades.dpToPx(context, 5), Utilidades.dpToPx(context, 5));
                    Variables.set_cartaVistaSeleccionada(this);
                    _horizontalVista.crearOnClickCartaVistas();
                } else {
                    // Los drawables son diferentes
                    this.get_frameLayout().setBackgroundResource(R.drawable.imageview_border_default);
                    this.get_frameLayout().setPadding(Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1));
                    Variables.set_cartaVistaSeleccionada(null);
                    _horizontalVista.crearOnClickCartaVistas();
                }
            }
        }
    }

    public boolean igualImagen(Drawable.ConstantState imagen){
        // RECIENTEMENTE CREADO POR PATRON MUY COMUN Y CODIGO MEDIANAMENTE LARGO. FIXME: USARLO DONDE NO LO USÉ
        // NO CONFUNDIR CON this.get_frameLayout() ETC
        return this.get_imageView().getDrawable().getConstantState().equals(imagen);
    }

    public boolean igualImagenVacia(){
        return this.get_imageView().getDrawable().getConstantState().equals(ContextCompat.getDrawable(Variables.get_gameActivityContext(), R.drawable.carta_vacia).getConstantState());
    }

    public ACarta get_carta() {
        return _carta;
    }

    public void set_carta(ACarta carta) {
        _carta = carta;
        _imageView.setImageResource(carta.get_imagen());
        carta.set_cartaVista(this);
    }

    public HorizontalVista get_horizontalVista() {
        return _horizontalVista;
    }

    public void set_horizontalVista(HorizontalVista horizontalVista) {
        if(_horizontalVista!=null){
            _horizontalVista.get_cartasVista().remove(this);
        }
        if(horizontalVista!=null){
            horizontalVista.get_cartasVista().add(this);
        }
        _horizontalVista = horizontalVista;
    }
}
