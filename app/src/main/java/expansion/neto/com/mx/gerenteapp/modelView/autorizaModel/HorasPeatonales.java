package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HorasPeatonales {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("detalle")
    @Expose
    private List<Detalle> detalle = null;
    @SerializedName("tiempoConteos")
    @Expose
    private String tiempoConteos;

    public String getTiempoConteos() {
        return tiempoConteos;
    }

    public void setTiempoConteos(String tiempoConteos) {
        this.tiempoConteos = tiempoConteos;
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

    public List<Detalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }

    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("horaMax")
    @Expose
    private String horaMax;
    @SerializedName("UnidadMedicionId")
    @Expose
    private Integer unidadMedicionId;
    @SerializedName("detalleFactorBsquedaId")
    @Expose
    private Integer detalleFactorBsquedaId;
    @SerializedName("horaMin")
    @Expose
    private String horaMin;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHoraMax() {
        return horaMax;
    }

    public void setHoraMax(String horaMax) {
        this.horaMax = horaMax;
    }

    public Integer getUnidadMedicionId() {
        return unidadMedicionId;
    }

    public void setUnidadMedicionId(Integer unidadMedicionId) {
        this.unidadMedicionId = unidadMedicionId;
    }

    public Integer getDetalleFactorBsquedaId() {
        return detalleFactorBsquedaId;
    }

    public void setDetalleFactorBsquedaId(Integer detalleFactorBsquedaId) {
        this.detalleFactorBsquedaId = detalleFactorBsquedaId;
    }

    public String getHoraMin() {
        return horaMin;
    }

    public void setHoraMin(String horaMin) {
        this.horaMin = horaMin;
    }

    public class Detalle {

        @SerializedName("descripcion")
        @Expose
        private String descripcion;
        @SerializedName("horaMax")
        @Expose
        private String horaMax;
        @SerializedName("UnidadMedicionId")
        @Expose
        private Integer unidadMedicionId;
        @SerializedName("detalleFactorBsquedaId")
        @Expose
        private Integer detalleFactorBsquedaId;
        @SerializedName("horaMin")
        @Expose
        private String horaMin;

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getHoraMax() {
            return horaMax;
        }

        public void setHoraMax(String horaMax) {
            this.horaMax = horaMax;
        }

        public Integer getUnidadMedicionId() {
            return unidadMedicionId;
        }

        public void setUnidadMedicionId(Integer unidadMedicionId) {
            this.unidadMedicionId = unidadMedicionId;
        }

        public Integer getDetalleFactorBsquedaId() {
            return detalleFactorBsquedaId;
        }

        public void setDetalleFactorBsquedaId(Integer detalleFactorBsquedaId) {
            this.detalleFactorBsquedaId = detalleFactorBsquedaId;
        }

        public String getHoraMin() {
            return horaMin;
        }

        public void setHoraMin(String horaMin) {
            this.horaMin = horaMin;
        }
    }

}
