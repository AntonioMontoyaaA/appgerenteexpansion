package expansion.neto.com.mx.gerenteapp.modelView.agendaModel;

public class Localizador {

    public double fcLatitud;
    public double fcLongitud;

    public Localizador(double fcLatitud, double fcLongitud) {
        this.fcLatitud = fcLatitud;
        this.fcLongitud = fcLongitud;
    }

    public double getFcLatitud() {
        return fcLatitud;
    }

    public void setFcLatitud(double fcLatitud) {
        this.fcLatitud = fcLatitud;
    }

    public double getFcLongitud() {
        return fcLongitud;
    }

    public void setFcLongitud(double fcLongitud) {
        this.fcLongitud = fcLongitud;
    }
}
