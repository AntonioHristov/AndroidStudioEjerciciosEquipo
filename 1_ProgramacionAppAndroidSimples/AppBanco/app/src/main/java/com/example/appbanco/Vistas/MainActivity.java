package com.example.appbanco.Vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbanco.Controladores.Asignar;
import com.example.appbanco.Controladores.CuentasBancarias;
import com.example.appbanco.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        if(CuentasBancarias.getCuentasBancarias().isEmpty()){
            CuentasBancarias.NuevaCuenta(5);
        }

        Asignar.SpinnerConListaEstilosItemDefinidos(findViewById(R.id.idSpinnerIdCuentas),CuentasBancarias.getIdsCuentasBancarias(), this.getApplicationContext());
        //Asignar.SpinnerConListaStringsXml(findViewById(R.id.my_spinner),R.array.spinner_items, this.getApplicationContext());
        findViewById(R.id.idBEntrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sp=new Intent(getApplicationContext(),CuentaElegida.class);
                sp.putExtra("idCuenta",Integer.parseInt(((Spinner)findViewById(R.id.idSpinnerIdCuentas)).getSelectedItem().toString()));
                startActivity(sp);
            }
        });
    }
}