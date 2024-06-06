package com.loeches.yugioh.Controlador;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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

    public static int getAnchoTelefonoPxV2() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }


    public static int getAnchoTelefonoDp(Context context) {
        return pxToDp(context,getAnchoTelefonoPx(context));
    }

    public static int getAltoTelefonoPx(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        }
        return 0;
    }

    public static int getAltoTelefonoPxV2() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static Drawable.ConstantState toConst(Context context, int img){
        return ContextCompat.getDrawable(context, img).getConstantState();
    }

    public static String getDrawableName(Context context, int drawableId) {
        // Obtiene el nombre del recurso a partir del ID
        String resourceName = context.getResources().getResourceEntryName(drawableId);
        // Construye el nombre completo con la extensión
        String fullResourceName = resourceName + ".png"; // O ".jpg", dependiendo de la extensión que utilices
        return fullResourceName;
    }

    // Método para obtener el identificador del recurso
    public static int getDrawableIdByName(Context context, String resourceName) {
        // Elimina la extensión del nombre del archivo, si existe
        int dotIndex = resourceName.lastIndexOf('.');
        if (dotIndex != -1) {
            resourceName = resourceName.substring(0, dotIndex);
        }

        // Obtiene el identificador del recurso drawable por su nombre
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }

    // Método para cambiar la imagen en el ImageView
    public static void setImageViewByName(Context context, ImageView imageView, String imageName) {
        // Obtiene el identificador del recurso drawable
        int drawableId = getDrawableIdByName(context, imageName);

        // Verifica si el identificador es válido y establece la imagen en el ImageView
        if (drawableId != 0) {
            imageView.setImageResource(drawableId);
        }else{
            imageView.setImageResource(getDrawableIdByName(context, "not_found.png"));
        }
    }

    public static double limitarDecimalesConRedondeo(Double numero, int numeroDecimales){
        String pattern="#.";
        for (int i = 0; i < numeroDecimales; i++) {
            pattern+="#";
        }
        // Definir el formato con redondeo al más próximo y dos decimales
        DecimalFormat formato = new DecimalFormat(pattern);
        formato.setRoundingMode(RoundingMode.HALF_UP);

        // Aplicar el formato al número
        return Double.parseDouble(formato.format(numero));
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

    // SP = TypedValue.COMPLEX_UNIT_SP

}