package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class Points implements SortedListAdapter.ViewModel {

    int icono;
    Double lat;
    Double lot;
    String nombre;
    Bitmap bitmap;


    public Points(int icono, Double lat, Double lot, String nombre, Bitmap bitmap) {
        this.icono = icono;
        this.lat = lat;
        this.lot = lot;
        this.nombre = nombre;
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLot() {
        return lot;
    }

    public void setLot(Double lot) {
        this.lot = lot;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
