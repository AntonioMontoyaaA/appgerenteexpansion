package expansion.neto.com.mx.gerenteapp.modelView.crearModel;

public class CrearPeatonal {

    String usuarioId;
    String mdId;
    String fecha;
    String horaInicio;
    String horaFinal;
    String total;
    String latitud;
    String longitud;
    String versionApp;
    String numTelefono;
    String bajaConteos;

    public CrearPeatonal(String usuarioId, String mdId, String fecha,
                         String horaInicio, String horaFinal, String total,
                         String latitud, String longitud, String versionApp,
                         String numTelefono, String bajaConteos) {

        this.usuarioId = usuarioId;
        this.mdId = mdId;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.total = total;
        this.latitud = latitud;
        this.longitud = longitud;
        this.versionApp = versionApp;
        this.numTelefono = numTelefono;
        this.bajaConteos = bajaConteos;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getBajaConteos() {
        return bajaConteos;
    }

    public void setBajaConteos(String bajaConteos) {
        this.bajaConteos = bajaConteos;
    }

    public String getVersionApp() {
        return versionApp;
    }

    public void setVersionApp(String versionApp) {
        this.versionApp = versionApp;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
