package com.loeches.yugioh.Modelo.InterfazVista;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.loeches.yugioh.Controlador.Utilidades;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.AMonstruo;
import com.loeches.yugioh.Modelo.Cartas.Ejemplares.CartaVacia;
import com.loeches.yugioh.Modelo.Global.Enums.EIdHorizontalVista;
import com.loeches.yugioh.Modelo.Global.Global;
import com.loeches.yugioh.R;
import com.loeches.yugioh.Vista.Jugar.JugandoInfoCartaActivity;

public class CartaVista {
    private FrameLayout _frameLayout;
    private ImageView _imageView;
    private ACarta _carta;

    public CartaVista(HorizontalVista horizontalVista, ACarta carta) {
        _carta=carta;
        set_horizontalVista(horizontalVista);
    }

    public CartaVista(EIdHorizontalVista idHorizontalVista, ACarta carta) {
        _carta=carta;
        set_horizontalVista(Global.getBy(idHorizontalVista));
    }

    public CartaVista(CartaVista copia) {
        _frameLayout=copia._frameLayout;
        _imageView=copia._imageView;
        _carta=copia._carta;
    }

    public void EscribirCodigoXML() {
        set_frameLayoutAndImageView();
        _frameLayout.addView(_imageView);
        get_horizontalVista().get_llHorizontal().addView(_frameLayout);
    }

    public void cambiarCartaVista(CartaVista destino, boolean eliminarEsta){
        int posHvThis = Global.getIndexHorizontalVista(this), posHvDestino= Global.getIndexHorizontalVista(destino);
        final HorizontalVista hvThis=this.get_horizontalVista();

        this.get_horizontalVista().get_cartasVista().set(posHvThis,destino);
        destino.get_horizontalVista().get_cartasVista().set(posHvDestino,this);

        this.get_carta().set_idHorizontalVista(destino.get_horizontalVista().get_id());
        destino.get_carta().set_idHorizontalVista(this.get_horizontalVista().get_id());

        if(eliminarEsta){
            // PORQUE "Esta" SE CAMBIÓ POR EL DESTINO
            hvThis.get_cartasVista().remove(destino);
        }
    }

    public void cambiarCartaVistaCopia(CartaVista destino, boolean eliminarEsta){
        int posHvThis = Global.getIndexHorizontalVista(this), posHvDestino= Global.getIndexHorizontalVista(destino);
        HorizontalVista hvThis=this.get_horizontalVista(),hvDestino=destino.get_horizontalVista();

        this.get_horizontalVista().get_cartasVista().set(posHvThis,destino);
        destino.get_horizontalVista().get_cartasVista().set(posHvDestino,this);

        this.get_carta().set_idHorizontalVista(destino.get_horizontalVista().get_id());
        destino.get_carta().set_idHorizontalVista(this.get_horizontalVista().get_id());



        if(eliminarEsta){
            // PORQUE "Esta" SE CAMBIÓ POR EL DESTINO
            destino.get_horizontalVista().get_cartasVista().remove(destino);
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
                    get_horizontalVista().crearListenerCartaVistas();
                } else {
                    // Los drawables son diferentes
                    this.get_frameLayout().setBackgroundResource(R.drawable.imageview_border_default);
                    this.get_frameLayout().setPadding(Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1), Utilidades.dpToPx(context, 1));
                    Global.set_cartaVistaSeleccionada(null);
                    get_horizontalVista().crearListenerCartaVistas();
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
        set_carta(new CartaVacia(),get_horizontalVista());

        this.get_frameLayout().setOnClickListener(null);
        this.get_frameLayout().setOnLongClickListener(null);
        this.get_frameLayout().setOnDragListener(null);
        if(ordenar){
            get_horizontalVista().ordenar();
        }
    }

    public void verInformacion(){
        Intent intent=new Intent(Global.get_context(), JugandoInfoCartaActivity.class);
        intent.putExtra("idHorizontal",get_horizontalVista().get_id().toString());
        intent.putExtra("pos",Global.getIndexHorizontalVista(this));
        Global.get_activity().startActivity(intent);
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
                Utilidades.getAnchoTelefonoPx() / Global.get_iniciandoPartidaCantidadCartaVistasPorHorizontalSinScroll() - Utilidades.dpToPx(context, 5),
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

    public void set_carta(ACarta carta, HorizontalVista horizontalVista) {
        _carta = carta;
        carta.set_idHorizontalVista(horizontalVista.get_id());
    }

    public HorizontalVista get_horizontalVista() {
        return Global.getBy(_carta.get_idHorizontalVista());
    }


    public void set_horizontalVista(HorizontalVista horizontalVista) {
        if(get_horizontalVista()!=null){
            get_horizontalVista().get_cartasVista().remove(this);
        }
        if(horizontalVista!=null){
            horizontalVista.get_cartasVista().add(this);
        }
        _carta.set_idHorizontalVista(horizontalVista.get_id());
    }
}
