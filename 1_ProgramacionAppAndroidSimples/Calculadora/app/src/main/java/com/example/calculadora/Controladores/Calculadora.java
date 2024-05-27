package com.example.calculadora.Controladores;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculadora {
    String numeroGuardado = "";
    char operacionGuardada = ' ';
    TextView edSecundario, edPrincipal;
    Button bCambioSigno, bPorcent, bC, bRetroceso, bDiv, bMul, bRes, bSum, bIgual, bPunto, b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;

    public Calculadora(TextView edSecundario, TextView edPrincipal, Button bCambioSigno, Button bCE, Button bC, Button bRetroceso, Button bDiv, Button bMul, Button bRes, Button bSum, Button bIgual, Button bPunto, Button b0, Button b1, Button b2, Button b3, Button b4, Button b5, Button b6, Button b7, Button b8, Button b9) {
        this.edSecundario = edSecundario;
        this.edPrincipal = edPrincipal;
        this.bCambioSigno = bCambioSigno;
        this.bPorcent = bCE;
        this.bC = bC;
        this.bRetroceso = bRetroceso;
        this.bDiv = bDiv;
        this.bMul = bMul;
        this.bRes = bRes;
        this.bSum = bSum;
        this.bIgual = bIgual;
        this.bPunto = bPunto;
        this.b0 = b0;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.b5 = b5;
        this.b6 = b6;
        this.b7 = b7;
        this.b8 = b8;
        this.b9 = b9;
        prepararOnClicks();
    }

    public void prepararOnClicks(){
        for (Button boton: new Button[]{b0,b1,b2,b3,b4,b5,b6,b7,b8,b9}) {
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edPrincipal.setText(edPrincipal.getText().toString() + boton.getText().toString());
                }
            });
        }

        for (Button boton: new Button[]{bSum,bRes,bMul,bDiv,bPorcent,bIgual}) {
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String textoPrincipal=edPrincipal.getText().toString();
                    if(boton.getText().toString().equals("=")){
                        switch (operacionGuardada){
                            case '+':
                                edPrincipal.setText((Double.parseDouble((numeroGuardado.isEmpty()?"0":numeroGuardado))+Double.parseDouble((textoPrincipal.isEmpty()?"0":textoPrincipal)))+"");
                                break;
                            case '-':
                                edPrincipal.setText((Double.parseDouble((numeroGuardado.isEmpty()?"0":numeroGuardado))-Double.parseDouble((textoPrincipal.isEmpty()?"0":textoPrincipal)))+"");
                                break;
                            case 'X':
                                edPrincipal.setText((Double.parseDouble((numeroGuardado.isEmpty()?"0":numeroGuardado))*Double.parseDouble((textoPrincipal.isEmpty()?"0":textoPrincipal)))+"");
                                break;
                            case '/':
                                edPrincipal.setText((Double.parseDouble((numeroGuardado.isEmpty()?"0":numeroGuardado))/Double.parseDouble((textoPrincipal.isEmpty()?"0":textoPrincipal)))+"");
                                break;
                            case '%':
                                edPrincipal.setText((Double.parseDouble((numeroGuardado.isEmpty()?"0":numeroGuardado))%Double.parseDouble((textoPrincipal.isEmpty()?"0":textoPrincipal)))+"");
                                break;
                        }
                        operacionGuardada=' ';
                        numeroGuardado="";
                        edSecundario.setText("");
                        edSecundario.setVisibility(View.INVISIBLE);
                    }
                    else{
                        if(!textoPrincipal.isEmpty() && !numeroGuardado.isEmpty()){
                            switch (operacionGuardada){
                                case '+':
                                    numeroGuardado=(Double.parseDouble((numeroGuardado.isEmpty()?"0":numeroGuardado))+Double.parseDouble((textoPrincipal.isEmpty()?"0":textoPrincipal)))+"";
                                    break;
                                case '-':
                                    numeroGuardado=(Double.parseDouble((numeroGuardado.isEmpty()?"0":numeroGuardado))-Double.parseDouble((textoPrincipal.isEmpty()?"0":textoPrincipal)))+"";
                                    break;
                                case 'X':
                                    numeroGuardado=(Double.parseDouble((numeroGuardado.isEmpty()?"0":numeroGuardado))*Double.parseDouble((textoPrincipal.isEmpty()?"0":textoPrincipal)))+"";
                                    break;
                                case '/':
                                    numeroGuardado=(Double.parseDouble((numeroGuardado.isEmpty()?"0":numeroGuardado))/Double.parseDouble((textoPrincipal.isEmpty()?"0":textoPrincipal)))+"";
                                    break;
                                case '%':
                                    numeroGuardado=(Double.parseDouble((numeroGuardado.isEmpty()?"0":numeroGuardado))%Double.parseDouble((textoPrincipal.isEmpty()?"0":textoPrincipal)))+"";
                                    break;
                            }
                        }
                        else{
                            numeroGuardado = textoPrincipal;
                        }
                        operacionGuardada = boton.getText().toString().charAt(0);
                        edSecundario.setText((numeroGuardado.isEmpty()?"0":numeroGuardado) +" "+ operacionGuardada);
                        edPrincipal.setText("");
                        edSecundario.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        bRetroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoPrincipal = edPrincipal.getText().toString();
                if(!edPrincipal.getText().toString().isEmpty()){
                    edPrincipal.setText(textoPrincipal.substring(0,textoPrincipal.length()-1));
                }
            }
        });

        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroGuardado="";
                operacionGuardada=' ';
                edPrincipal.setText("");
                edSecundario.setText("");
                edSecundario.setVisibility(View.INVISIBLE);
            }
        });

        bCambioSigno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoPrincipal = edPrincipal.getText().toString();
                if(!textoPrincipal.isEmpty()){
                    edPrincipal.setText((0-Double.parseDouble(textoPrincipal))+"");
                }
            }
        });

        bPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoPrincipal = edPrincipal.getText().toString();
                boolean existePunto=false;
                for (int i = 0; !existePunto && i < textoPrincipal.length(); i++) {
                    if(textoPrincipal.charAt(i)=='.'){
                        existePunto=true;
                    }
                }
                if(!existePunto){
                    edPrincipal.setText((textoPrincipal.isEmpty()?"0":textoPrincipal)+".");
                }
            }
        });
    }
}