package expansion.neto.com.mx.gerenteapp.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.google.gson.Gson;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Localizador;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderLocalizador;


/**
 * Created by yruizm on 14/03/17.
 */
public class ServicioRutas extends Service implements LocationListener {

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int idArranque) {
        try {

            LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1800000, 500000.0f, this);
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1800000,500000.0f, this);
            Log.e("Service ejecutado!","Service ejecutado");

        } catch(SecurityException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return START_STICKY;
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
                            Log.e("Service ejecutado!","Service enviado");
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
    public void onDestroy() { }
    @Override
    public IBinder onBind(Intent intencion) { return null; }

    @Override
    public void onLocationChanged(Location location) {
        //Boolean horario = Util.getEntreFechas();
        //if(horario){
            sendLocalizador();
        //}
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
