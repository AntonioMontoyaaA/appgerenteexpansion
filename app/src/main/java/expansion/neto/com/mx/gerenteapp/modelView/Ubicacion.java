package expansion.neto.com.mx.gerenteapp.modelView;

public class Ubicacion {
    public double lat;
    public double lng;
    public boolean gps;


    public Ubicacion(){}

    public Ubicacion(double lat, double lng, boolean gps) {
        this.lat = lat;
        this.lng = lng;
        this.gps = gps;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }
}
