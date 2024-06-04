package com.loeches.yugioh.Controlador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import com.loeches.yugioh.Modelo.Global.Variables;
import com.loeches.yugioh.R;

import java.util.ArrayList;
import java.util.List;

public class Utilidades {

    public static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static int pxToDp(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(px / density);
    }

    public static int getAnchoTelefonoPx(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        }
        return 0;
    }

    public static int getAnchoTelefonoDp(Context context) {
        return pxToDp(context,getAnchoTelefonoPx(context));
    }

    public static int getAltoTelefonoPx() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static Drawable.ConstantState toConst(Context context, int img){
        return ContextCompat.getDrawable(context, img).getConstantState();
    }

    /*
    public static void showAlertDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Título del Diálogo");
        builder.setMessage("Este es el mensaje del diálogo. ¿Qué opción eliges?");

        // Botón positivo (Opción 1)
        builder.setPositiveButton("Opción 1", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción para la opción 1
            }
        });

        // Botón negativo (Opción 2)
        builder.setNegativeButton("Opción 2", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Acción para la opción 2
            }
        });

        // Crear y mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }*/

    /*
    public class MultiClickListener implements View.OnClickListener {
        private final List<View.OnClickListener> listeners = new ArrayList<>();

        public void addListener(View.OnClickListener listener) {
            listeners.add(listener);
        }

        @Override
        public void onClick(View v) {
            for (View.OnClickListener listener : listeners) {
                listener.onClick(v);
            }
        }
    }*/

}
