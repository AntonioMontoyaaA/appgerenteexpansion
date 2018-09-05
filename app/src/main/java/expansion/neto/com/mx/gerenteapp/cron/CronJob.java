package expansion.neto.com.mx.gerenteapp.cron;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Localizador;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderLocalizador;
import expansion.neto.com.mx.gerenteapp.utils.ServicioGPS;


public class CronJob extends JobService {

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        sendLocalizador();
        //Log.e("Service ejecutado!","Cron LOCALIZADOR");
        return false;
    }

    Localizador gpsUbica;

    public void sendLocalizador() {
        final SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuarioId = preferences.getString("usuario", "");
        gpsUbica = gps();
        if(gpsUbica.fcLatitud!=0 && gpsUbica.fcLongitud!=0){
            String json = getJsonString(gpsUbica);
            json = "["+json+"]";
            ProviderLocalizador.getInstance(this).guardaLocalizacion(usuarioId, json, new ProviderLocalizador.InterfaceLocalizador() {
                @Override
                public void resolve(Codigos codigo) {
                    if(codigo!=null){
                        if(codigo.getCodigo()==200){
                            //Log.e("****", codigo.getMensaje()+"");
                        }
                    }
                }

                @Override
                public void reject(Exception e) {

                }
            });
        }
    }

    private String getJsonString(Localizador ubicacion) {
        Gson gson = new Gson();
        String json = gson.toJson(ubicacion);
        return json;
    }

    /******** gps **************/
    ServicioGPS gpsUbicas;

    Double latitude, longitude, latitudeLast = 0.0, longitudeLast = 0.0;

    public Localizador gps() {
        Localizador ubicacion;
        latitudeLast = latitude;
        longitudeLast = longitude;
        gpsUbicas = new ServicioGPS(getApplicationContext());
        if (gpsUbicas.canGetLocation()) {
            latitude = gpsUbicas.getLatitude();
            longitude = gpsUbicas.getLongitude();
            ubicacion = new Localizador(latitude, longitude);
        } else {
            if(latitudeLast!=null){
                ubicacion = new Localizador(latitudeLast, longitudeLast);
            }else{
                ubicacion = new Localizador(0.0, 0.0);
            }
        }
        return ubicacion;
    }


    @Override
    public boolean onStopJob(JobParameters jobParameters) { return false; }

}