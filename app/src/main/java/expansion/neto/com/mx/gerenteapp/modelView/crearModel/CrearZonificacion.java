package expansion.neto.com.mx.gerenteapp.modelView.crearModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CrearZonificacion {

    @SerializedName("usuarioId")
    @Expose
    String usuariod;
    @SerializedName("mdId")
    @Expose
    String mdId;
    @SerializedName("competencias")
    @Expose
    private List<Zonificacion> competencia = null;
    @SerializedName("generadores")
    @Expose
    private List<Zonificacion> generadores = null;
    @SerializedName("latitud")
    @Expose
    String latitud;
    @SerializedName("longitud")
    @Expose
    String longitud;
    @SerializedName("numTelefono")
    @Expose
    String telefono;
    @SerializedName("versionApp")
    @Expose
    String version;

    public CrearZonificacion(){}

    public CrearZonificacion(String usuariod, String mdId, List<Zonificacion> competencia, List<Zonificacion> generadores, String latitud, String longitud, String telefono, String version) {
        this.usuariod = usuariod;
        this.mdId = mdId;
        this.competencia = competencia;
        this.generadores = generadores;
        this.latitud = latitud;
        this.longitud = longitud;
        this.telefono = telefono;
        this.version = version;
    }

    public String getUsuariod() {
        return usuariod;
    }

    public void setUsuariod(String usuariod) {
        this.usuariod = usuariod;
    }

    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public List<Zonificacion> getCompetencia() {
        return competencia;
    }

    public void setCompetencia(List<Zonificacion> competencia) {
        this.competencia = competencia;
    }

    public List<Zonificacion> getGeneradores() {
        return generadores;
    }

    public void setGeneradores(List<Zonificacion> generadores) {
        this.generadores = generadores;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public static class Zonificacion {

        @SerializedName("NIVELID")
        @Expose
        private String nivelid;
        @SerializedName("DETALLES")
        @Expose
        private List<Detalle> detalles = null;

        public Zonificacion(String nivelid, List<Detalle> detalles) {
            this.nivelid = nivelid;
            this.detalles = detalles;
        }

        public Zonificacion(){}

        public String getNivelid() {
            return nivelid;
        }

        public void setNivelid(String nivelid) {
            this.nivelid = nivelid;
        }

        public List<Detalle> getDetalles() {
            return detalles;
        }

        public void setDetalles(List<Detalle> detalles) {
            this.detalles = detalles;
        }
    }

    public static class Detalle {

        @SerializedName("NIVELID")
        @Expose
        private String nivelId;
        @SerializedName("DETALLEID")
        @Expose
        private String detalleid;
        @SerializedName("LATITUD")
        @Expose
        private String latitud;
        @SerializedName("LONGITUD")
        @Expose
        private String longitud;

        public Detalle(){}

        public Detalle(String detalleid, String latitud, String longitud, String nivelId) {
            this.detalleid = detalleid;
            this.latitud = latitud;
            this.longitud = longitud;
            this.nivelId = nivelId;
        }


        public Detalle(String nivelId) {
            this.nivelId = nivelId;
        }

        public String getNivelId() {
            return nivelId;
        }

        public void setNivelId(String nivelId) {
            this.nivelId = nivelId;
        }

        public String getDetalleid() {
            return detalleid;
        }

        public void setDetalleid(String detalleid) {
            this.detalleid = detalleid;
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
}
