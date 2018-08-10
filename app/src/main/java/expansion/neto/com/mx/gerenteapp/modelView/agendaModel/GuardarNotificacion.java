package expansion.neto.com.mx.gerenteapp.modelView.agendaModel;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class GuardarNotificacion {


    String usuarioId;
    String tipoNotificacion;
    String mdId;
    String fecha;
    String nivelEstatusArea;

    public GuardarNotificacion(String usuarioId, String tipoNotificacion, String mdId, String fecha, String nivelEstatusArea) {
        this.usuarioId = usuarioId;
        this.tipoNotificacion = tipoNotificacion;
        this.mdId = mdId;
        this.fecha = fecha;
        this.nivelEstatusArea = nivelEstatusArea;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
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

    public String getNivelEstatusArea() {
        return nivelEstatusArea;
    }

    public void setNivelEstatusArea(String nivelEstatusArea) {
        this.nivelEstatusArea = nivelEstatusArea;
    }
}
