
package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetalleRadio {

    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("estatusRadioId")
    @Expose
    private String estatusRadioId;
    @SerializedName("sinSitio")
    @Expose
    private String sinSitio;
    @SerializedName("datosRadio")
    @Expose
    private DatosRadio datosRadio;
    @SerializedName("fechaAsignado")
    @Expose
    private String fechaAsignado;
    @SerializedName("usuarioAsignado")
    @Expose
    private String usuarioAsignado;
    @SerializedName("radioId")
    @Expose
    private String radioId;
    @SerializedName("generadores")
    @Expose
    private List<GeneradoresRadio> generadores = null;
    @SerializedName("rechazado")
    @Expose
    private String rechazado;
    @SerializedName("longRadio")
    @Expose
    private String longRadio;
    @SerializedName("cancelado")
    @Expose
    private String cancelado;
    @SerializedName("competencia")
    @Expose
    private List<Competencia> competencia = null;
    @SerializedName("estatusRadio")
    @Expose
    private String estatusRadio;
    @SerializedName("visitasRadio")
    @Expose
    private String visitasRadio;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("nombreRadio")
    @Expose
    private String nombreRadio;

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }



    public String getFechaAsignado() {
        return fechaAsignado;
    }

    public void setFechaAsignado(String fechaAsignado) {
        this.fechaAsignado = fechaAsignado;
    }

    public String getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(String usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    public String getRadioId() {
        return radioId;
    }

    public void setRadioId(String radioId) {
        this.radioId = radioId;
    }

    public String getRechazado() {
        return rechazado;
    }

    public void setRechazado(String rechazado) {
        this.rechazado = rechazado;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public List<Competencia> getCompetencia() {
        return competencia;
    }

    public void setCompetencia(List<Competencia> competencia) {
        this.competencia = competencia;
    }

    public String getEstatusRadioId() {
        return estatusRadioId;
    }

    public void setEstatusRadioId(String estatusRadioId) {
        this.estatusRadioId = estatusRadioId;
    }


    public DatosRadio getDatosRadio() {
        return datosRadio;
    }

    public void setDatosRadio(DatosRadio datosRadio) {
        this.datosRadio = datosRadio;
    }

    public String getVisitasRadio() {
        return visitasRadio;
    }

    public void setVisitasRadio(String visitasRadio) {
        this.visitasRadio = visitasRadio;
    }

    public String getLongRadio() {
        return longRadio;
    }

    public void setLongRadio(String longRadio) {
        this.longRadio = longRadio;
    }

    public List<GeneradoresRadio> getGeneradores() {
        return generadores;
    }

    public void setGeneradores(List<GeneradoresRadio> generadores) {
        this.generadores = generadores;
    }

    public String getEstatusRadio() {
        return estatusRadio;
    }

    public void setEstatusRadio(String estatusRadio) {
        this.estatusRadio = estatusRadio;
    }

    public String getNombreRadio() {
        return nombreRadio;
    }

    public void setNombreRadio(String nombreRadio) {
        this.nombreRadio = nombreRadio;
    }

    public String getSinSitio() {
        return sinSitio;
    }

    public void setSinSitio(String sinSitio) {
        this.sinSitio = sinSitio;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }
}
