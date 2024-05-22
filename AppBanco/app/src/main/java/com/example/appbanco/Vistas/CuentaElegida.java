package com.example.appbanco.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanco.Controladores.Asignar;
import com.example.appbanco.Modelos.CuentaBancaria;
import com.example.appbanco.Controladores.CuentasBancarias;
import com.example.appbanco.R;

public class CuentaElegida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cuenta_elegida);
        EscribirDatosCuentaElegida();
    }
    public void EscribirDatosCuentaElegida(){
        Intent intent=getIntent();
        CuentaBancaria cuentaElegida = CuentasBancarias.getCuentaById(intent.getIntExtra("idCuenta",-1));
        Spinner spinner = findViewById(R.id.idSpinnerObjetivoTransferencia);
        String historialOperaciones="";
        if(cuentaElegida!=null){
            ((TextView)findViewById(R.id.idtvIdCuenta)).setText("ID: "+cuentaElegida.getIdentificador());
            ((TextView)findViewById(R.id.idtvSaldo)).setText("SALDO: "+cuentaElegida.getSaldo());
            Asignar.SpinnerConLista(spinner,CuentasBancarias.getIdsCuentasBancarias(), this.getApplicationContext());

            findViewById(R.id.idBIngresar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cuentaElegida.ingreso(Double.parseDouble(((EditText)findViewById(R.id.idtndDineroOperacion)).getText().toString()));
                    EscribirDatosCuentaElegida();
                }
            });

            findViewById(R.id.idBPagar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cuentaElegida.pagar(Double.parseDouble(((EditText)findViewById(R.id.idtndDineroOperacion)).getText().toString()));
                    EscribirDatosCuentaElegida();
                }
            });

            findViewById(R.id.idBTransferir).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cuentaElegida.transferencia(CuentasBancarias.getCuentaById(Integer.parseInt(spinner.getSelectedItem().toString())),Double.parseDouble(((EditText)findViewById(R.id.idtndDineroOperacion)).getText().toString()));
                    EscribirDatosCuentaElegida();
                }
            });

            findViewById(R.id.idBElegirCuenta).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sp=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(sp);
                }
            });


            //FIXME: CREAR FUNCIÓN QUE DEVUELVA UN STRING CON LOS VALORES DE UNA LISTA DE STRINGS, SEPARADOS POR UN DELIMITADOR ESPECIFICADO/PERSONALIZABLE
            for (String operacion:cuentaElegida.getHistorialOperaciones()) {
                historialOperaciones+=operacion+" | ";
            }
            ((TextView)findViewById(R.id.idtvHistorialOperaciones)).setText("HISTORIAL: "+historialOperaciones);
        }
    }
}