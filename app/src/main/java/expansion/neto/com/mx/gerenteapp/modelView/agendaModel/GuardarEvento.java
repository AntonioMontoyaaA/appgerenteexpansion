package expansion.neto.com.mx.gerenteapp.modelView.agendaModel;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class GuardarEvento {


    String usuarioIdRegistra;
    String tareaxAreaId;
    String fechaAgenda;
    String fechaFinProgramada;
    String observaciones;
    String direccion;
    String latitud;
    String longitud;
    String tipoAsignacion;
    String porAsignar;

    public GuardarEvento(String usuarioIdRegistra, String tareaxAreaId, String fechaAgenda,
                         String fechaFinProgramada, String observaciones, String direccion,
                         String latitud, String longitud, String tipoAsignacion,
                         String porAsignar) {

        this.usuarioIdRegistra = usuarioIdRegistra;
        this.tareaxAreaId = tareaxAreaId;
        this.fechaAgenda = fechaAgenda;
        this.fechaFinProgramada = fechaFinProgramada;
        this.observaciones = observaciones;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipoAsignacion = tipoAsignacion;
        this.porAsignar = porAsignar;
    }

    public String getUsuarioIdRegistra() {
        return usuarioIdRegistra;
    }

    public void setUsuarioIdRegistra(String usuarioIdRegistra) {
        this.usuarioIdRegistra = usuarioIdRegistra;
    }

    public String getTareaxAreaId() {
        return tareaxAreaId;
    }

    public void setTareaxAreaId(String tareaxAreaId) {
        this.tareaxAreaId = tareaxAreaId;
    }

    public String getFechaAgenda() {
        return fechaAgenda;
    }

    public void setFechaAgenda(String fechaAgenda) {
        this.fechaAgenda = fechaAgenda;
    }

    public String getFechaFinProgramada() {
        return fechaFinProgramada;
    }

    public void setFechaFinProgramada(String fechaFinProgramada) {
        this.fechaFinProgramada = fechaFinProgramada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getTipoAsignacion() {
        return tipoAsignacion;
    }

    public void setTipoAsignacion(String tipoAsignacion) {
        this.tipoAsignacion = tipoAsignacion;
    }

    public String getPorAsignar() {
        return porAsignar;
    }

    public void setPorAsignar(String porAsignar) {
        this.porAsignar = porAsignar;
    }
}
