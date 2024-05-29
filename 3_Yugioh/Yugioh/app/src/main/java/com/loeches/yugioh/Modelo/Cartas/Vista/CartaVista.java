package com.loeches.yugioh.Modelo.Cartas.Vista;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.loeches.yugioh.Controlador.Utilidades;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Global.Lista;
import com.loeches.yugioh.Modelo.Enums.EUbicacionCartaVista;
import com.loeches.yugioh.R;

public class CartaVista {
    private FrameLayout _frameLayout;
    private ImageView _imageView;
    private ACarta _carta;
    private EUbicacionCartaVista _ubicacionVista;
public CartaVista(Context context, LinearLayout llHorizontal, ACarta carta, EUbicacionCartaVista ubicacion) {
    CrearFrameLayoutEImageView(context, llHorizontal);
    set_carta(carta);
    carta.set_cartaVista(this);
    _ubicacionVista = ubicacion;
    Lista.getCartasVista().add(this);
}

    public void CrearFrameLayoutEImageView(Context context, LinearLayout llHorizontal){
        // Crear el FrameLayout
        FrameLayout frameLayout = new FrameLayout(context);
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(
                Utilidades.getAnchoTelefonoPx(context)/5-Utilidades.dpToPx(context,5),
                //Utilidades.getAnchoTelefonoDp(context)/5-Utilidades.dpToPx(context,5),
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        frameLayoutParams.setMargins(Utilidades.dpToPx(context,2), Utilidades.dpToPx(context,2), Utilidades.dpToPx(context,2), Utilidades.dpToPx(context,2));
        frameLayout.setLayoutParams(frameLayoutParams);
        frameLayout.setBackgroundResource(R.drawable.imageview_border);
        frameLayout.setPadding(Utilidades.dpToPx(context,1), Utilidades.dpToPx(context,1), Utilidades.dpToPx(context,1), Utilidades.dpToPx(context,1));
        frameLayout.setRotation(0);

        // Crear el ImageView
        ImageView imageView = new ImageView(context);
        FrameLayout.LayoutParams imageViewParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        imageView.setLayoutParams(imageViewParams);
        imageView.setRotation(0);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.color.black);

        // Añadir el ImageView al FrameLayout
        frameLayout.addView(imageView);

        // Añadir el FrameLayout al LinearLayout existente
        llHorizontal.addView(frameLayout);

        _frameLayout=frameLayout;
        _imageView=imageView;
    }

    public FrameLayout get_frameLayout() {
        return _frameLayout;
    }

    public ImageView get_imageView() {
        return _imageView;
    }

    public ACarta get_carta() {
        return _carta;
    }

    public void set_carta(ACarta carta) {
        _carta = carta;
        _imageView.setImageResource(carta.get_imagen());
    }

    public EUbicacionCartaVista get_ubicacionVista() {
        return _ubicacionVista;
    }
}
