package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatosPuntuacion {
    @SerializedName("nomcategoria")
    @Expose
    private String nomcategoria;
    @SerializedName("totalPuntos")
    @Expose
    private Double totalPuntos;
    @SerializedName("ubicacionMD")
    @Expose
    private String ubicacionMD;
    @SerializedName("categoriaid")
    @Expose
    private Integer categoriaid;
    @SerializedName("factores")
    @Expose
    private List<Factore> factores = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public String getNomcategoria() {
        return nomcategoria;
    }

    public void setNomcategoria(String nomcategoria) {
        this.nomcategoria = nomcategoria;
    }

    public Double getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(Double totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    public String getUbicacionMD() {
        return ubicacionMD;
    }

    public void setUbicacionMD(String ubicacionMD) {
        this.ubicacionMD = ubicacionMD;
    }

    public Integer getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(Integer categoriaid) {
        this.categoriaid = categoriaid;
    }

    public List<Factore> getFactores() {
        return factores;
    }

    public void setFactores(List<Factore> factores) {
        this.factores = factores;
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

    public class Factore {

        @SerializedName("factorid")
        @Expose
        private Integer factorid;
        @SerializedName("totalxfactor")
        @Expose
        private Double totalxfactor;
        @SerializedName("puntuacion")
        @Expose
        private Double puntuacion;
        @SerializedName("nombrenivel")
        @Expose
        private String nombrenivel;
        @SerializedName("rangoubica")
        @Expose
        private String rangoubica;
        @SerializedName("finivelid")
        @Expose
        private Integer finivelid;
        @SerializedName("rangoubicaid")
        @Expose
        private Integer rangoubicaid;

        public Integer getFactorid() {
            return factorid;
        }

        public void setFactorid(Integer factorid) {
            this.factorid = factorid;
        }

        public Double getTotalxfactor() {
            return totalxfactor;
        }

        public void setTotalxfactor(Double totalxfactor) {
            this.totalxfactor = totalxfactor;
        }

        public Double getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(Double puntuacion) {
            this.puntuacion = puntuacion;
        }

        public String getNombrenivel() {
            return nombrenivel;
        }

        public void setNombrenivel(String nombrenivel) {
            this.nombrenivel = nombrenivel;
        }

        public String getRangoubica() {
            return rangoubica;
        }

        public void setRangoubica(String rangoubica) {
            this.rangoubica = rangoubica;
        }

        public Integer getFinivelid() {
            return finivelid;
        }

        public void setFinivelid(Integer finivelid) {
            this.finivelid = finivelid;
        }

        public Integer getRangoubicaid() {
            return rangoubicaid;
        }

        public void setRangoubicaid(Integer rangoubicaid) {
            this.rangoubicaid = rangoubicaid;
        }

    }

}
