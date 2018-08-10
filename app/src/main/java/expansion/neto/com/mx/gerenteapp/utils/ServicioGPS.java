package expansion.neto.com.mx.gerenteapp.utils;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class ServicioGPS extends Service {

    private Context mContext;
    // bandera para saber si el gps esta encendido o apagado
    boolean gpsEncendido = false;
    // bandera para saber si el internet esta encendido o apagado
    boolean redEncendida = true;
    boolean canGetLocation = false;
    Location location; // Locacion
    double latitude; // Latitude
    double longitude; // Longitude
    //La distancia mínima para cambiar Actualizaciones en metros
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 100; // 10 metros
    // El tiempo minino entre actualizaciones de el gps
    private static final long MIN_TIME_BW_UPDATES = 1000 * 20 * 1; // 1 minute
    // Variable tipo LocationManager
    protected LocationManager locationManager;

    public double lastLatitude, lastLotitude;
    public ServicioGPS() {

    }

    public ServicioGPS(Context context) {
        this.mContext = context;
        getLocation();
    }

    @SuppressLint("MissingPermission")
    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            // Obetenemos el status del gps
            gpsEncendido = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            // Obtenemos el statuts de la red telefónica
            redEncendida = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!gpsEncendido && !redEncendida) {
                // no hay internet ni gps
            } else { // de lo contrario obtener coordenadas mediante la red telefonica
                this.canGetLocation = true;
                if (redEncendida ) {
                    int requestPermissionsCode = 50;
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, mLocationListener);
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                        }
                    }
                }
            }
            // si gps esta activo obtener coordenadas mediante gps
            if (gpsEncendido) {
                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, mLocationListener);
                    if (locationManager != null) {

                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }

                }

            }else{

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return location;
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {

            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };



    /**
     * Function to show settings alert dialog.
     * On pressing the Settings button it will launch Settings Options.
     * */


    /**
     * Function obtiene latitud
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }


    /**
     * Funcion obtiene longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }
        return longitude;
    }

    /**
     * Funcion para detectar si esta encendido el gps/wifi
     * @return boolean
     * */
    public double getLastLatitude(){
        if(location != null){
            lastLatitude = location.getLatitude();
        }else{
            lastLatitude = longitude;
        }
        return lastLatitude;
    }

    public double getLastLotitude(){
        if(location != null){
            lastLatitude = location.getLongitude();
        }else{
            lastLatitude = longitude;
        }
        return lastLatitude;
    }

    public boolean canGetLocation() {
        getLastLatitude();
        getLastLotitude();
        return this.canGetLocation;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}