package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion.modulos.guardarDatos;

import android.content.Context;
import android.content.SharedPreferences;

import expansion.neto.com.mx.gerenteapp.modelView.crearModel.CrearGeneralidades;

public class GuardarDatosGeneralidades {

    public static void salvarDatosGeneralidades(Context context, CrearGeneralidades generalidades,
                                                SharedPreferences.Editor editor, SharedPreferences preferences){

        if(generalidades!=null){
            editor.putString("mdId", generalidades.getMdId());
            editor.putString("usuarioId", generalidades.getUsuarioId());
            editor.putString("renta", generalidades.getRenta());
            editor.putString("disponibilidad", generalidades.getDisponibilidad());
            editor.putString("fechadisponible", generalidades.getFechadisponible());
            editor.putString("porcentajeamortiza", generalidades.getPorcentajeamortiza());
            editor.putString("periodoamortizacion", generalidades.getPeriodoamortizacion());
            editor.putString("periodogracia", generalidades.getPeriodogracia());
            editor.putString("numtelefono", generalidades.getNumtelefono());
            editor.putString("versionapp", generalidades.getVersionapp());
            editor.apply();
        }

    }

    public static CrearGeneralidades obtenerGeneralidades(Context context){

        CrearGeneralidades generalidades = null;
        generalidades = new CrearGeneralidades();
        SharedPreferences preferences = context.getSharedPreferences("datosGeneralidades", Context.MODE_PRIVATE);
        generalidades.setMdId(preferences.getString("mdId", ""));
        generalidades.setUsuarioId(preferences.getString("usuarioId", ""));
        generalidades.setRenta(preferences.getString("renta", ""));
        generalidades.setDisponibilidad(preferences.getString("disponibilidad", ""));
        generalidades.setFechadisponible(preferences.getString("fechadisponible", ""));
        generalidades.setPorcentajeamortiza(preferences.getString("porcentajeamortiza", ""));
        generalidades.setPeriodoamortizacion(preferences.getString("periodoamortizacion", ""));
        generalidades.setPeriodogracia(preferences.getString("periodogracia", ""));
        generalidades.setNumtelefono(preferences.getString("numtelefono", ""));
        generalidades.setVersionapp(preferences.getString("versionapp", ""));
        return generalidades;


    }



}
