package com.example.appbanco.Controladores;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.List;

public class Asignar {
    public static void SpinnerConLista(Spinner spinner, List lista, Context activity){
        // Crear un ArrayAdapter usando el List<String> y un layout por defecto para el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity.getApplicationContext(), android.R.layout.simple_spinner_item, lista);

        // Especifica el layout a usar cuando la lista de opciones aparece
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplica el adaptador al spinner
        spinner.setAdapter(adapter);

        // Añade un listener para cuando se selecciona un item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Acción a realizar cuando se selecciona un item
                Toast.makeText(activity.getApplicationContext(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Acción a realizar cuando no se selecciona nada
            }
        });
    }


    public static void SpinnerConListaStringsXml(Spinner spinner, int R_array_name, Context activity){
        /*
            (strings.xml)
            <string-array name="spinner_items">
            <item>Item 1</item>
            <item>Item 2</item>
            <item>Item 3</item>
            <item>Item 4</item>
            </string-array>

            (Parámetros)
            R_array_name = R.array.spinner_items
            activity = this.getApplicationContext()
         */

        // Crea un ArrayAdapter usando el string array y un layout por defecto para el spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity.getApplicationContext(),
                R_array_name, android.R.layout.simple_spinner_item);

        // Especifica el layout a usar cuando la lista de opciones aparece
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplica el adaptador al spinner
        spinner.setAdapter(adapter);

        // Añade un listener para cuando se selecciona un item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Acción a realizar cuando se selecciona un item
                Toast.makeText(activity.getApplicationContext(), parentView.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Acción a realizar cuando no se selecciona nada
            }
        });
    }
}
