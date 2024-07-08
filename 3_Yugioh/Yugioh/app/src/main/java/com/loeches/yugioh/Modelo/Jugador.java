package com.loeches.yugioh.Modelo;

import com.loeches.yugioh.Modelo.Global.Global;

public class Jugador {
    //private TextView _tvVista;// NO SE PUEDE GUARDAR EN JSON, E INNECESARIO AUNQUE SE PUDIESE
    private String _textoPrefijoMostrarVida;
    private String _textoSufijoMostrarVida;
    private int _vida;

// PARA GUARDARLO EN JSON
    public Jugador() {
    }

    public Jugador(String textoPrefijoMostrarVida, String textoSufijoMostrarVida, int vida) {
        _textoPrefijoMostrarVida =textoPrefijoMostrarVida;
        _textoSufijoMostrarVida=textoSufijoMostrarVida;
        _vida = vida;
        Global.get_jugadores().add(this);
    }

    /*
    public void EscribirCodigoXML(ViewGroup contenedor){
        set_tvVista();
        contenedor.addView(_tvVista);
    }

    public TextView get_tvVista() {
        return _tvVista;
    }

    public void set_tvVista() {
        TextView tv = new TextView(Global.get_context());
        tv.setText(_textoPrefijoMostrarVida+_vida+_textoSufijoMostrarVida);
        //tv.setText("hola");
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tv.setForegroundGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        tv.setLayoutParams(tvParams);
        _tvVista=tv;
    }*/

    public String get_textoPrefijoMostrarVida() {
        return _textoPrefijoMostrarVida;
    }

    public void set_textoPrefijoMostrarVida(String textoMostrarVida) {
        _textoPrefijoMostrarVida = textoMostrarVida;
    }

    public String get_textoSufijoMostrarVida() {
        return _textoSufijoMostrarVida;
    }

    public void set_textoSufijoMostrarVida(String _textoSufijoMostrarVida) {
        this._textoSufijoMostrarVida = _textoSufijoMostrarVida;
    }

    public int get_vida() {
        return _vida;
    }

    public void set_vida(int vida) {
        _vida = vida;
        //_tvVista.setText(_textoPrefijoMostrarVida+_vida+_textoSufijoMostrarVida);
    }
}
