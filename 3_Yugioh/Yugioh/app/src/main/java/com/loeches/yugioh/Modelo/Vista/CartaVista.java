package com.loeches.yugioh.Modelo.Vista;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.loeches.yugioh.Controlador.Controlador;
import com.loeches.yugioh.Controlador.Utilidades;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.Modelo.Jugador;
import com.loeches.yugioh.R;
import com.loeches.yugioh.Vista.VistaActivity;

public class CartaVista {
    private FrameLayout _frameLayout;
    private ImageView _imageView;
    private ACarta _carta;
    private HorizontalVista _horizontalVista;

    public CartaVista(HorizontalVista horizontalVista, ACarta carta) {
        set_horizontalVista(horizontalVista);
        set_carta(carta);// EN set_carta SE HACE set_imageView()
        set_frameLayout();
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

    public void cambiarCartaVista(CartaVista destino, boolean eliminarEsta){
        int posHvThis = Global.getIndexHorizontalVista(this), posHvDestino= Global.getIndexHorizontalVista(destino);
        HorizontalVista hvThis=this._horizontalVista,hvDestino=destino._horizontalVista;

        this._horizontalVista.get_cartasVista().set(posHvThis,destino);
        destino._horizontalVista.get_cartasVista().set(posHvDestino,this);

        this._horizontalVista= Global.getBy(hvDestino.get_id());
        destino._horizontalVista= Global.getBy(hvThis.get_id());

        if(eliminarEsta){
            // PORQUE "Esta" SE CAMBIÓ POR EL DESTINO
            destino._horizontalVista.get_cartasVista().remove(destino);
        }
    }

    public void seleccionarOQuitarSeleccionNoVacias(){
        Context context = Global.get_context();
        if (get_horizontalVista().esSuTurno()&& !igualImagenVacia()) {
            if( Global.get_cartaVistaSeleccionada()==null||Global.get_cartaVistaSeleccionada()==this ){
                if (this.get_frameLayout().getBackground().getConstantState().equals(ContextCompat.getDrawable(context, R.drawable.imageview_border_default).getConstantState())) {
                    // Los drawables son iguales
                    this.get_frameLayout().setBackgroundResource(R.drawable.imageview_border_selected);
                    this.get_frameLayout().setPadding(Utilidades.dpToPx(context, 5), Utilidades.dpToPx(context, 5), Utilidades.dpToPx(context, 5), Utilidades.dpToPx(context, 5));
                    Global.set_cartaVistaSeleccionada(this);
                    _horizontalVista.crearListenerCartaVistas();
                } else {
                    // Los drawables son diferentes
                    this.get_frameLayout().setBackgroundResource(R.drawable.imageview_border_default);
                    this.get_frameLayout().setPadding(Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1));
                    Global.set_cartaVistaSeleccionada(null);
                    _horizontalVista.crearListenerCartaVistas();
                }
            }
        }
    }

    public boolean igualImagen(int imagenResId){
        // RECIENTEMENTE CREADO POR PATRON MUY COMUN Y CODIGO MEDIANAMENTE LARGO. FIXME: USARLO DONDE NO LO USÉ DESPUÉS DE CREAR UNA COPIA DE SEGURIDAD Y TESTEAR
        // REEMPLAZARLO POR DONDE USÉ this.get_imageView().getDrawable().getConstantState().equals(...);
        // NO CONFUNDIR CON this.get_frameLayout() ETC
        Drawable drawable = Global.get_context().getResources().getDrawable(imagenResId);
        return drawable!=null&&this.get_imageView().getDrawable().getConstantState().equals(drawable.getConstantState());
    }

    public boolean igualImagenVacia(){
        return igualImagen(R.drawable.carta_vacia);
    }

    public void convertirseVacio(boolean ordenar){
        // ordenar DEBERIA SER TRUE CUANDO TERMINAS DE VACIAR TODAS LAS CARTAS DE UN HORIZONTAL VISTA
        // SI SOLO VACIAS UNA CARTA (CONSUMIDA, MONSTRUO VENCIDO) ordenar SERIA TRUE
        // SI VACIAS TODAS LAS CARTAS (O MAS DE 1) EN UN HORIZONTAL, ordenar SERIA FALSE Y (SI NO SON TODAS) ORDENAS EL HORIZONTAL AL TERMINAR
            if(Global.get_cartaVistaSeleccionada()==this){
                Global.set_cartaVistaSeleccionada(null);
            }
        set_carta(new CartaVacia());
        this.get_frameLayout().setOnClickListener(null);
        this.get_frameLayout().setOnLongClickListener(null);
        this.get_frameLayout().setOnDragListener(null);
        if(ordenar){
            get_horizontalVista().ordenar();
        }
    }

    public void verInformacion(){
        VistaActivity.vaciar();
        Context context = Global.get_context();
        LinearLayout main = Global.get_linearMain();
        // Crear el ImageView
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                2
        );
        imageViewParams.setMargins(Utilidades.dpToPx(10), Utilidades.dpToPx(10), Utilidades.dpToPx(10), Utilidades.dpToPx(10));
        imageView.setLayoutParams(imageViewParams);
        imageView.setRotation(0);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if(get_carta()==null){
            imageView.setImageResource(R.color.black);
        }else{
            imageView.setImageResource(get_carta().get_imagen());
            if(get_carta() instanceof AMonstruo){
                AMonstruo cvMonstruo = (AMonstruo) get_carta();
            }
        }
        main.addView(imageView);

        TextView tv = new TextView(context);
        //tv.setText("HOOLA");
        tv.setText(_carta.toString());

        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        //tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        tv.setGravity(Gravity.CENTER);
        //tv.setBackgroundColor(Color.RED);
        tv.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.MATCH_PARENT
                0,
                3
        );
        tvParams.setMargins(Utilidades.dpToPx(10), Utilidades.dpToPx(10), Utilidades.dpToPx(10), Utilidades.dpToPx(10));
        tv.setLayoutParams(tvParams);



        main.addView(tv);

        Button bt = new Button(context);
        bt.setText("Seguir Jugando");
        //bt.setTextSize(30);
        bt.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VistaActivity.actualizar();
            }
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1
        );
        params.gravity = Gravity.CENTER_HORIZONTAL;

        params.setMargins(Utilidades.dpToPx(10), Utilidades.dpToPx(10), Utilidades.dpToPx(10), Utilidades.dpToPx(10));

        bt.setLayoutParams(params);

        main.addView(bt);
    }









    public FrameLayout get_frameLayout() {
        return _frameLayout;
    }

    public void set_frameLayout() {
        _frameLayout= crear_frameLayout();
    }

    public ImageView get_imageView() {
        return _imageView;
    }

    public void set_imageView() {
        _imageView= crear_imageView();
    }
    public void set_frameLayoutAndImageView() {
            set_frameLayout();
            set_imageView();
    }

    public FrameLayout crear_frameLayout() {
        Context context = Global.get_context();
        FrameLayout frameLayout = new FrameLayout(context);
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(
                Utilidades.getAnchoTelefonoPx() / Global.get_cantidadCartaVistasPorHorizontalSinScroll() - Utilidades.dpToPx(context, 5),
                //Variables.get_anchoCartaVista(),
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        frameLayoutParams.setMargins(Utilidades.dpToPx(context, 2), Utilidades.dpToPx(context, 2), Utilidades.dpToPx(context, 2), Utilidades.dpToPx(context, 2));
        frameLayout.setLayoutParams(frameLayoutParams);

        if(Global.get_cartaVistaSeleccionada()==this){
            frameLayout.setPadding(Utilidades.dpToPx(context, 5), Utilidades.dpToPx(context, 5), Utilidades.dpToPx(context, 5), Utilidades.dpToPx(context, 5));
            frameLayout.setBackgroundResource(R.drawable.imageview_border_selected);
        }else{
            frameLayout.setPadding(Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1));
            frameLayout.setBackgroundResource(R.drawable.imageview_border_default);
        }
        frameLayout.setRotation(0);
        return frameLayout;
    }
    public ImageView crear_imageView() {
        // Crear el ImageView
        ImageView imageView = new ImageView(Global.get_context());
        FrameLayout.LayoutParams imageViewParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        imageView.setLayoutParams(imageViewParams);
        imageView.setRotation(0);
        //imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //imageView.setImageResource(R.color.black);
        if(get_carta()==null){
            imageView.setImageResource(R.color.black);
        }else{
            imageView.setImageResource(get_carta().get_imagen());
            if(get_carta() instanceof AMonstruo){
                AMonstruo cvMonstruo = (AMonstruo) get_carta();
                if(cvMonstruo.is_modoDefensa()){
                    imageView.setRotation(90);
                }else{
                    imageView.setRotation(0);
                }
            }
        }
        return imageView;
    }

    public ACarta get_carta() {
        return _carta;
    }

    public void set_carta(ACarta carta) {
        _carta = carta;
        if(_imageView==null){
            set_imageView();
        }
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
