package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.location.Location;
import android.support.v4.app.Fragment;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AutorizaHolderPeatonal;


public class FragmentAutoriza extends Fragment implements
        AutorizaHolderPeatonal.Listener, com.google.android.gms.location.LocationListener {

    public static void loadingProgress(ProgressDialog progressDialog, int i){
        if(i == 0){
            progressDialog.setTitle("Enviando...");
            progressDialog.setMessage("Espera mientras se carga tu informacion...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }else{
            progressDialog.dismiss();
        }
    }

    private GoogleApiClient mGoogleApiClient;
    @Override
    public void onLocationChanged(Location location) {
        try {
            if (location != null)
                changeMap(location);
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private GoogleMap mMap;
    @SuppressLint("MissingPermission")
    private void changeMap(Location location) {
        if (mMap != null) {
            mMap.getUiSettings().setZoomControlsEnabled(false);
            LatLng latLong;


            latLong = new LatLng(location.getLatitude(), location.getLongitude());

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLong).zoom(19f).tilt(70).build();

            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.animateCamera( CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        } else {
        }

    }


    @Override
    public void onAutorizaSelect(Peatonal model) {

    }
}
