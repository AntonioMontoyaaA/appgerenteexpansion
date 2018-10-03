package expansion.neto.com.mx.gerenteapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import expansion.neto.com.mx.gerenteapp.R;

/**
 * Created by hsamano on 27/06/17.
 * Se crea un Cliente para los servicios de Google
 * Clase que ayuda a verificar permisos de GPS
  */

public class GoogleApiHelper implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = GoogleApiHelper.class.getSimpleName();

    Context context;
    GoogleApiClient mGoogleApiClient;

    LocationManager manager;

    public GoogleApiHelper(Context context) {
        this.context = context;
        buildGoogleApliClient();
        connect();
    }
    public void connect() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }
    public void disconnect() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }
    /*
     * Metodo constructor para regresar el cliente
     */
    public GoogleApiClient mGoogleApiClient(){
        return this.mGoogleApiClient;
    }
    /*
     * Genera el cliente para su uso
     */
    private void buildGoogleApliClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(AppIndex.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnectionSuspended: googleApiClient.connect()");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended: googleApiClient.connect()");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult != null) {
            //TAG, "onConnectionFailed: connectionResult.toString() = " + connectionResult.toString());
        }
    }

    /*
     * Ejecuta el dialogo para Activar el GPS
     */
    public void ejecutaGPSOptions(final Context contexto) {
        contexto.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    /*
     * Se crea una alerta de No GPS
     */
    public void buildAlertMessageNoGPS(final Context contexto) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        builder.setMessage(R.string.app_no_gps_mensaje)
                .setCancelable(false)
                .setPositiveButton(R.string.app_no_gps_activar_btn, new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        ejecutaGPSOptions(contexto);
                    }
                })
                .setNegativeButton(R.string.app_no_gps_cancelar_btn, new DialogInterface.OnClickListener(){
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    /*
     * Retorna un booleano si el GPS se encuentra activado
     */
    public boolean isEnabledGps() {
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context.getApplicationContext(), R.string.app_no_gps_desactivado_toast, Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }
}
