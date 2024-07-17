package Modelos;

public class Jugador {
    //private TextView _tvVista;// NO SE PUEDE GUARDAR EN JSON, E INNECESARIO AUNQUE SE PUDIESE
    private String _apodo;
    private int _vida;

// PARA GUARDARLO EN JSON
    public Jugador() {
    }

    public Jugador(String textoPrefijoMostrarVida, int vida) {
        _apodo =textoPrefijoMostrarVida;
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

    public String get_apodo() {
        return _apodo;
    }

    public void set_apodo(String textoMostrarVida) {
        _apodo = textoMostrarVida;
    }

    public int get_vida() {
        return _vida;
    }

    public void set_vida(int vida) {
        _vida = vida;
        //_tvVista.setText(_textoPrefijoMostrarVida+_vida+_textoSufijoMostrarVida);
    }
}
