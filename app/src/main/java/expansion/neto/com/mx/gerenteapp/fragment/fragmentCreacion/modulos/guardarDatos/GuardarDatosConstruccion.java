package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion.modulos.guardarDatos;

import android.content.Context;
import android.content.SharedPreferences;

public class GuardarDatosConstruccion {

    public static void salvarDatosConstruccion(String json,
                                               SharedPreferences.Editor editor){

        if(json!=null && json.length()>0){
            editor.putString("json", json);
            editor.apply();
        }else{
            editor.putString("json", "");
            editor.apply();
        }

    }

    public static String obtenerConstruccion(Context context){
        String json = "";
        SharedPreferences preferences = context.getSharedPreferences("datosConstruccion", Context.MODE_PRIVATE);
        json = preferences.getString("json", "");
        return json;
    }

}
