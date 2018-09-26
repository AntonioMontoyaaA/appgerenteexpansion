package expansion.neto.com.mx.gerenteapp.modelView.crearModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FactoresConstruccion {

    @SerializedName("catalogo")
    @Expose
    private List<Catalogo> catalogo = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public List<Catalogo> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<Catalogo> catalogo) {
        this.catalogo = catalogo;
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

    public class Detalle {

        @SerializedName("unidademedicion")
        @Expose
        private String unidademedicion;
        @SerializedName("detalleid")
        @Expose
        private Integer detalleid;
        @SerializedName("toleranciamax")
        @Expose
        private Object toleranciamax;
        @SerializedName("toleranciamin")
        @Expose
        private Object toleranciamin;
        @SerializedName("descripcion")
        @Expose
        private String descripcion;
        @SerializedName("prioridad")
        @Expose
        private Integer prioridad;

        public String getUnidademedicion() {
            return unidademedicion;
        }

        public void setUnidademedicion(String unidademedicion) {
            this.unidademedicion = unidademedicion;
        }

        public Integer getDetalleid() {
            return detalleid;
        }

        public void setDetalleid(Integer detalleid) {
            this.detalleid = detalleid;
        }

        public Object getToleranciamax() {
            return toleranciamax;
        }

        public void setToleranciamax(Object toleranciamax) {
            this.toleranciamax = toleranciamax;
        }

        public Object getToleranciamin() {
            return toleranciamin;
        }

        public void setToleranciamin(Object toleranciamin) {
            this.toleranciamin = toleranciamin;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public Integer getPrioridad() {
            return prioridad;
        }

        public void setPrioridad(Integer prioridad) {
            this.prioridad = prioridad;
        }

    }


    public class Catalogo {

        @SerializedName("factorid")
        @Expose
        private Integer factorid;
        @SerializedName("unidademedicion")
        @Expose
        private String unidademedicion;
        @SerializedName("nivelid")
        @Expose
        private Integer nivelid;
        @SerializedName("detalles")
        @Expose
        private List<Detalle> detalles = null;
        @SerializedName("toleranciamin")
        @Expose
        private Integer toleranciamin;
        @SerializedName("descripcion")
        @Expose
        private String descripcion;
        @SerializedName("toleranciamax")
        @Expose
        private Integer toleranciamax;

        public Integer getFactorid() {
            return factorid;
        }

        public void setFactorid(Integer factorid) {
            this.factorid = factorid;
        }

        public String getUnidademedicion() {
            return unidademedicion;
        }

        public void setUnidademedicion(String unidademedicion) {
            this.unidademedicion = unidademedicion;
        }

        public Integer getNivelid() {
            return nivelid;
        }

        public void setNivelid(Integer nivelid) {
            this.nivelid = nivelid;
        }

        public List<Detalle> getDetalles() {
            return detalles;
        }

        public void setDetalles(List<Detalle> detalles) {
            this.detalles = detalles;
        }

        public Integer getToleranciamin() {
            return toleranciamin;
        }

        public void setToleranciamin(Integer toleranciamin) {
            this.toleranciamin = toleranciamin;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public Integer getToleranciamax() {
            return toleranciamax;
        }

        public void setToleranciamax(Integer toleranciamax) {
            this.toleranciamax = toleranciamax;
        }

    }

}
