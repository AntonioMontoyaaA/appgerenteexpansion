package expansion.neto.com.mx.gerenteapp.modelView.crearModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompetenciasGeneradores {

    @SerializedName("generadoresNivel")
    @Expose
    private List<GeneradoresNivel> generadoresNivel = null;
    @SerializedName("competencias")
    @Expose
    private List<Competencia> competencias = null;
    @SerializedName("generadores")
    @Expose
    private List<Generadore> generadores = null;
    @SerializedName("competenciasNivel")
    @Expose
    private List<CompetenciasNivel> competenciasNivel = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public List<GeneradoresNivel> getGeneradoresNivel() {
        return generadoresNivel;
    }

    public void setGeneradoresNivel(List<GeneradoresNivel> generadoresNivel) {
        this.generadoresNivel = generadoresNivel;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }

    public List<Generadore> getGeneradores() {
        return generadores;
    }

    public void setGeneradores(List<Generadore> generadores) {
        this.generadores = generadores;
    }

    public List<CompetenciasNivel> getCompetenciasNivel() {
        return competenciasNivel;
    }

    public void setCompetenciasNivel(List<CompetenciasNivel> competenciasNivel) {
        this.competenciasNivel = competenciasNivel;
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

    public class Generadore {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;

        public String getNombreGenerador() {
            return nombreGenerador;
        }

        public void setNombreGenerador(String nombreGenerador) {
            this.nombreGenerador = nombreGenerador;
        }

        public Integer getGeneradorId() {
            return generadorId;
        }

        public void setGeneradorId(Integer generadorId) {
            this.generadorId = generadorId;
        }

    }

    public class GeneradoresNivel {

        @SerializedName("descripcion")
        @Expose
        private String descipcion;
        @SerializedName("factorId")
        @Expose
        private Integer factorId;
        @SerializedName("toleranciaMaxima")
        @Expose
        private Integer toleranciaMaxima;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;
        @SerializedName("toleeranciaMinima")
        @Expose
        private Integer toleeranciaMinima;
        @SerializedName("detalle")
        @Expose
        private List<Detalle> detalle = null;
        @SerializedName("unidadMedicion")
        @Expose
        private String unidadMedicion;

        public String getDescipcion() {
            return descipcion;
        }

        public void setDescipcion(String descipcion) {
            this.descipcion = descipcion;
        }

        public Integer getFactorId() {
            return factorId;
        }

        public void setFactorId(Integer factorId) {
            this.factorId = factorId;
        }

        public Integer getToleranciaMaxima() {
            return toleranciaMaxima;
        }

        public void setToleranciaMaxima(Integer toleranciaMaxima) {
            this.toleranciaMaxima = toleranciaMaxima;
        }

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

        public Integer getToleeranciaMinima() {
            return toleeranciaMinima;
        }

        public void setToleeranciaMinima(Integer toleeranciaMinima) {
            this.toleeranciaMinima = toleeranciaMinima;
        }

        public List<Detalle> getDetalle() {
            return detalle;
        }

        public void setDetalle(List<Detalle> detalle) {
            this.detalle = detalle;
        }

        public String getUnidadMedicion() {
            return unidadMedicion;
        }

        public void setUnidadMedicion(String unidadMedicion) {
            this.unidadMedicion = unidadMedicion;
        }

    }

    public class Detalle_ {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;

        public String getNombreGenerador() {
            return nombreGenerador;
        }

        public void setNombreGenerador(String nombreGenerador) {
            this.nombreGenerador = nombreGenerador;
        }

        public Integer getGeneradorId() {
            return generadorId;
        }

        public void setGeneradorId(Integer generadorId) {
            this.generadorId = generadorId;
        }

    }

    public class Detalle {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;

        public String getNombreGenerador() {
            return nombreGenerador;
        }

        public void setNombreGenerador(String nombreGenerador) {
            this.nombreGenerador = nombreGenerador;
        }

        public Integer getGeneradorId() {
            return generadorId;
        }

        public void setGeneradorId(Integer generadorId) {
            this.generadorId = generadorId;
        }

    }

    public class CompetenciasNivel {

        @SerializedName("descipcion")
        @Expose
        private String descipcion;
        @SerializedName("factorId")
        @Expose
        private Integer factorId;
        @SerializedName("toleranciaMaxima")
        @Expose
        private Integer toleranciaMaxima;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;
        @SerializedName("toleeranciaMinima")
        @Expose
        private Integer toleeranciaMinima;
        @SerializedName("detalle")
        @Expose
        private List<Detalle_> detalle = null;
        @SerializedName("unidadMedicion")
        @Expose
        private String unidadMedicion;

        public String getDescipcion() {
            return descipcion;
        }

        public void setDescipcion(String descipcion) {
            this.descipcion = descipcion;
        }

        public Integer getFactorId() {
            return factorId;
        }

        public void setFactorId(Integer factorId) {
            this.factorId = factorId;
        }

        public Integer getToleranciaMaxima() {
            return toleranciaMaxima;
        }

        public void setToleranciaMaxima(Integer toleranciaMaxima) {
            this.toleranciaMaxima = toleranciaMaxima;
        }

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

        public Integer getToleeranciaMinima() {
            return toleeranciaMinima;
        }

        public void setToleeranciaMinima(Integer toleeranciaMinima) {
            this.toleeranciaMinima = toleeranciaMinima;
        }

        public List<Detalle_> getDetalle() {
            return detalle;
        }

        public void setDetalle(List<Detalle_> detalle) {
            this.detalle = detalle;
        }

        public String getUnidadMedicion() {
            return unidadMedicion;
        }

        public void setUnidadMedicion(String unidadMedicion) {
            this.unidadMedicion = unidadMedicion;
        }

    }

    public class Competencia {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;

        public String getNombreGenerador() {
            return nombreGenerador;
        }

        public void setNombreGenerador(String nombreGenerador) {
            this.nombreGenerador = nombreGenerador;
        }

        public Integer getGeneradorId() {
            return generadorId;
        }

        public void setGeneradorId(Integer generadorId) {
            this.generadorId = generadorId;
        }

    }


}
