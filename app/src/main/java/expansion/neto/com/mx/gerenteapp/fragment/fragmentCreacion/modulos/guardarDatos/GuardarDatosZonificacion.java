package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion.modulos.guardarDatos;

import android.content.Context;
import android.content.SharedPreferences;

public class GuardarDatosZonificacion {

    public static void salvarDatosZonificacion(Context context, String json,
                                               SharedPreferences.Editor editor, SharedPreferences preferences){
        editor = preferences.edit();
        if(json!=null && !json.equals("")){
            editor.putString("json", json);
            editor.apply();
        }else{
            editor.putString("json", "");
            editor.apply();
        }

    }

    public static String obtenerZonificacion(Context context){
        String json = "";
        SharedPreferences preferences = context.getSharedPreferences("datosZonificacion", Context.MODE_PRIVATE);
        json = preferences.getString("json", "");
        return json;
    }



}
