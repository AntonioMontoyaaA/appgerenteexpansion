package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Radio {


    @SerializedName("detalleRadios")
    @Expose
    private List<DetalleRadio> detalleRadios = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("estatusRadios")
    @Expose
    private List<EstatusRadio> estatusRadios = null;

    public List<DetalleRadio> getDetalleRadios() {
        return detalleRadios;
    }

    public void setDetalleRadios(List<DetalleRadio> detalleRadios) {
        this.detalleRadios = detalleRadios;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<EstatusRadio> getEstatusRadios() {
        return estatusRadios;
    }

    public void setEstatusRadios(List<EstatusRadio> estatusRadios) {
        this.estatusRadios = estatusRadios;
    }

    public class DetalleRadio {

        @SerializedName("latitud")
        @Expose
        private String latitud;
        @SerializedName("poblacion")
        @Expose
        private List<Poblacion> poblacion = null;
        @SerializedName("fechaAsignado")
        @Expose
        private String fechaAsignado;
        @SerializedName("radioId")
        @Expose
        private String radioId;
        @SerializedName("longitud")
        @Expose
        private String longitud;
        @SerializedName("estatusRadioId")
        @Expose
        private Integer estatusRadioId;
        @SerializedName("generadores")
        @Expose
        private List<Generadore> generadores = null;
        @SerializedName("tipoEstrategia")
        @Expose
        private String tipoEstrategia;
        @SerializedName("ubicacion")
        @Expose
        private List<Ubicacion> ubicacion = null;
        @SerializedName("estatusRadio")
        @Expose
        private String estatusRadio;
        @SerializedName("nombreRadio")
        @Expose
        private String nombreRadio;

        public String getLatitud() {
            return latitud;
        }

        public void setLatitud(String latitud) {
            this.latitud = latitud;
        }

        public List<Poblacion> getPoblacion() {
            return poblacion;
        }

        public void setPoblacion(List<Poblacion> poblacion) {
            this.poblacion = poblacion;
        }

        public String getFechaAsignado() {
            return fechaAsignado;
        }

        public void setFechaAsignado(String fechaAsignado) {
            this.fechaAsignado = fechaAsignado;
        }

        public String getRadioId() {
            return radioId;
        }

        public void setRadioId(String radioId) {
            this.radioId = radioId;
        }

        public String getLongitud() {
            return longitud;
        }

        public void setLongitud(String longitud) {
            this.longitud = longitud;
        }

        public Integer getEstatusRadioId() {
            return estatusRadioId;
        }

        public void setEstatusRadioId(Integer estatusRadioId) {
            this.estatusRadioId = estatusRadioId;
        }

        public List<Generadore> getGeneradores() {
            return generadores;
        }

        public void setGeneradores(List<Generadore> generadores) {
            this.generadores = generadores;
        }

        public String getTipoEstrategia() {
            return tipoEstrategia;
        }

        public void setTipoEstrategia(String tipoEstrategia) {
            this.tipoEstrategia = tipoEstrategia;
        }

        public List<Ubicacion> getUbicacion() {
            return ubicacion;
        }

        public void setUbicacion(List<Ubicacion> ubicacion) {
            this.ubicacion = ubicacion;
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

    }

    public class EstatusRadio {

        @SerializedName("estatus")
        @Expose
        private String estatus;
        @SerializedName("estatusId")
        @Expose
        private Integer estatusId;

        public String getEstatus() {
            return estatus;
        }

        public void setEstatus(String estatus) {
            this.estatus = estatus;
        }

        public Integer getEstatusId() {
            return estatusId;
        }

        public void setEstatusId(Integer estatusId) {
            this.estatusId = estatusId;
        }

    }

    public class Generadore {

        @SerializedName("hospitales")
        @Expose
        private String hospitales;
        @SerializedName("escuelas")
        @Expose
        private String escuelas;
        @SerializedName("mercados")
        @Expose
        private String mercados;
        @SerializedName("templos")
        @Expose
        private String templos;

        public String getHospitales() {
            return hospitales;
        }

        public void setHospitales(String hospitales) {
            this.hospitales = hospitales;
        }

        public String getEscuelas() {
            return escuelas;
        }

        public void setEscuelas(String escuelas) {
            this.escuelas = escuelas;
        }

        public String getMercados() {
            return mercados;
        }

        public void setMercados(String mercados) {
            this.mercados = mercados;
        }

        public String getTemplos() {
            return templos;
        }

        public void setTemplos(String templos) {
            this.templos = templos;
        }

    }

    public class Poblacion {

        @SerializedName("poblacion")
        @Expose
        private String poblacion;
        @SerializedName("viviendas")
        @Expose
        private String viviendas;
        @SerializedName("pea")
        @Expose
        private String pea;
        @SerializedName("nse")
        @Expose
        private String nse;

        public String getPoblacion() {
            return poblacion;
        }

        public void setPoblacion(String poblacion) {
            this.poblacion = poblacion;
        }

        public String getViviendas() {
            return viviendas;
        }

        public void setViviendas(String viviendas) {
            this.viviendas = viviendas;
        }

        public String getPea() {
            return pea;
        }

        public void setPea(String pea) {
            this.pea = pea;
        }

        public String getNse() {
            return nse;
        }

        public void setNse(String nse) {
            this.nse = nse;
        }

    }

    public class Ubicacion {

        @SerializedName("entreCalle1")
        @Expose
        private String entreCalle1;
        @SerializedName("entreCalle2")
        @Expose
        private String entreCalle2;
        @SerializedName("callePrincipal")
        @Expose
        private String callePrincipal;

        public String getEntreCalle1() {
            return entreCalle1;
        }

        public void setEntreCalle1(String entreCalle1) {
            this.entreCalle1 = entreCalle1;
        }

        public String getEntreCalle2() {
            return entreCalle2;
        }

        public void setEntreCalle2(String entreCalle2) {
            this.entreCalle2 = entreCalle2;
        }

        public String getCallePrincipal() {
            return callePrincipal;
        }

        public void setCallePrincipal(String callePrincipal) {
            this.callePrincipal = callePrincipal;
        }

    }

}
