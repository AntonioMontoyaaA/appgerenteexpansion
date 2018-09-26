package expansion.neto.com.mx.gerenteapp.modelView.crearModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompetenciasGeneradoresV2 {

    @SerializedName("generadores")
    @Expose
    private Generadores generadores;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("competencias")
    @Expose
    private Competencias competencias;

    public Generadores getGeneradores() {
        return generadores;
    }

    public void setGeneradores(Generadores generadores) {
        this.generadores = generadores;
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

    public Competencias getCompetencias() {
        return competencias;
    }

    public void setCompetencias(Competencias competencias) {
        this.competencias = competencias;
    }

    public class Competencias {

        @SerializedName("Tienda Neto")
        @Expose
        private List<TiendaNeto> tiendaNeto = null;
        @SerializedName("Competencias")
        @Expose
        private List<Competencia> competencias = null;

        public List<TiendaNeto> getTiendaNeto() {
            return tiendaNeto;
        }

        public void setTiendaNeto(List<TiendaNeto> tiendaNeto) {
            this.tiendaNeto = tiendaNeto;
        }

        public List<Competencia> getCompetencias() {
            return competencias;
        }

        public void setCompetencias(List<Competencia> competencias) {
            this.competencias = competencias;
        }

    }

    public class Competencia {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;

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

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }
    }

    public class Generadores {

        @SerializedName("Otros Generadores")
        @Expose
        private List<OtrosGeneradore> otrosGeneradores = null;
        @SerializedName("Negocios")
        @Expose
        private List<Negocio> negocios = null;
        @SerializedName("Transporte publico")
        @Expose
        private List<TransportePublico> transportePublico = null;
        @SerializedName("Mercado Publico")
        @Expose
        private List<MercadoPublico> mercadoPublico = null;
        @SerializedName("Tianguis")
        @Expose
        private List<Tiangui> tianguis = null;
        @SerializedName("Negocios de comida")
        @Expose
        private List<NegociosDeComida> negociosDeComida = null;

        public List<OtrosGeneradore> getOtrosGeneradores() {
            return otrosGeneradores;
        }

        public void setOtrosGeneradores(List<OtrosGeneradore> otrosGeneradores) {
            this.otrosGeneradores = otrosGeneradores;
        }

        public List<Negocio> getNegocios() {
            return negocios;
        }

        public void setNegocios(List<Negocio> negocios) {
            this.negocios = negocios;
        }

        public List<TransportePublico> getTransportePublico() {
            return transportePublico;
        }

        public void setTransportePublico(List<TransportePublico> transportePublico) {
            this.transportePublico = transportePublico;
        }

        public List<MercadoPublico> getMercadoPublico() {
            return mercadoPublico;
        }

        public void setMercadoPublico(List<MercadoPublico> mercadoPublico) {
            this.mercadoPublico = mercadoPublico;
        }

        public List<Tiangui> getTianguis() {
            return tianguis;
        }

        public void setTianguis(List<Tiangui> tianguis) {
            this.tianguis = tianguis;
        }

        public List<NegociosDeComida> getNegociosDeComida() {
            return negociosDeComida;
        }

        public void setNegociosDeComida(List<NegociosDeComida> negociosDeComida) {
            this.negociosDeComida = negociosDeComida;
        }

    }

    public class MercadoPublico {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;

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

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

    }

    public class Negocio {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;

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

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

    }

    public class NegociosDeComida {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;

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

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

    }

    public class OtrosGeneradore {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;

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

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

    }

    public class Tiangui {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;

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

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

    }

    public class TiendaNeto {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;

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

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

    }

    public class TransportePublico {

        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;

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

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

    }


}
