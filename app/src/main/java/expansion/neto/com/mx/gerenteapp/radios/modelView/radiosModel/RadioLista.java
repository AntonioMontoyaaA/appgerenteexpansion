package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RadioLista {

    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("estatus")
    @Expose
    private String estatus;
    @SerializedName("radioId")
    @Expose
    private String radioId;
    @SerializedName("UsrAsignado")
    @Expose
    private String UsrAsignado;
    @SerializedName("competencia")
    @Expose
    private ArrayList<String> competencia = null;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("datosRadio")
    @Expose
    private List<DatosRadio> datosRadio = null;
    @SerializedName("visitasRadio")
    @Expose
    private String visitasRadio;
    @SerializedName("estatusId")
    @Expose
    private String estatusId;
    @SerializedName("generadores")
    @Expose
    private List<GeneradoresRadio> generadores = null;
    @SerializedName("usrAsignadoId")
    @Expose
    private String usrAsignadoId;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("longRadio")
    @Expose
    private String longRadio;

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getRadioId() {
        return radioId;
    }

    public void setRadioId(String radioId) {
        this.radioId = radioId;
    }

    public String getUsrAsignado() {
        return UsrAsignado;
    }

    public void setUsrAsignado(String usrAsignado) {
        UsrAsignado = usrAsignado;
    }

    public ArrayList<String> getCompetencia() {
        return competencia;
    }

    public void setCompetencia(ArrayList<String> competencia) {
        this.competencia = competencia;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public List<DatosRadio> getDatosRadio() {
        return datosRadio;
    }

    public void setDatosRadio(List<DatosRadio> datosRadio) {
        this.datosRadio = datosRadio;
    }

    public String getVisitasRadio() {
        return visitasRadio;
    }

    public void setVisitasRadio(String visitasRadio) {
        this.visitasRadio = visitasRadio;
    }

    public String getEstatusId() {
        return estatusId;
    }

    public void setEstatusId(String estatusId) {
        this.estatusId = estatusId;
    }

    public List<GeneradoresRadio> getGeneradores() {
        return generadores;
    }

    public void setGeneradores(List<GeneradoresRadio> generadores) {
        this.generadores = generadores;
    }

    public String getUsrAsignadoId() {
        return usrAsignadoId;
    }

    public void setUsrAsignadoId(String usrAsignadoId) {
        this.usrAsignadoId = usrAsignadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLongRadio() {
        return longRadio;
    }

    public void setLongRadio(String longRadio) {
        this.longRadio = longRadio;
    }
}
