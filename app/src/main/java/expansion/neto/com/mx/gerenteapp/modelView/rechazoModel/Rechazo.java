package expansion.neto.com.mx.gerenteapp.modelView.rechazoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Proceso;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class Rechazo implements SortedListAdapter.ViewModel {
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("memorias")
    @Expose
    private List<Rechazo.Memoria> memorias = null;

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

    public List<Rechazo.Memoria> getMemorias() {
        return memorias;
    }

    public void setMemorias(List<Rechazo.Memoria> memorias) {
        this.memorias = memorias;
    }

    public class Memoria implements SortedListAdapter.ViewModel {

        @SerializedName("memoriaid")
        @Expose
        private String memoriaid;
        @SerializedName("categoria")
        @Expose
        private String categoria;
        @SerializedName("parciales")
        @Expose
        private Integer parciales;
        @SerializedName("totales")
        @Expose
        private Integer totales;
        @SerializedName("puntosxcategoria")
        @Expose
        private Integer puntosxcategoria;
        @SerializedName("puntuacionmd")
        @Expose
        private Double puntuacionmd;
        @SerializedName("nombresitio")
        @Expose
        private String nombresitio;
        @SerializedName("creador")
        @Expose
        private String creador;
        @SerializedName("faltantes")
        @Expose
        private List<Proceso.Faltante> faltantes;
        @SerializedName("atrasada")
        @Expose
        private int atrasada;

        public String getMemoriaid() {
            return memoriaid;
        }

        public void setMemoriaid(String memoriaid) {
            this.memoriaid = memoriaid;
        }

        public String getCategoria() {



            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public Integer getPuntosxcategoria() {
            return puntosxcategoria;
        }

        public void setPuntosxcategoria(Integer puntosxcategoria) {
            this.puntosxcategoria = puntosxcategoria;
        }

        public Double getPuntuacionmd() {
            return puntuacionmd;
        }

        public void setPuntuacionmd(Double puntuacionmd) {
            this.puntuacionmd = puntuacionmd;
        }

        public Integer getParciales() {
            return parciales;
        }

        public void setParciales(Integer parciales) {
            this.parciales = parciales;
        }

        public Integer getTotales() {
            return totales;
        }

        public void setTotales(Integer totales) {
            this.totales = totales;
        }

        public String getNombresitio() {
            return nombresitio;
        }

        public void setNombresitio(String nombresitio) {
            this.nombresitio = nombresitio;
        }

        public String getCreador() {
            return creador;
        }

        public void setCreador(String creador) {
            this.creador = creador;
        }

        public List<Proceso.Faltante> getFaltantes() {
            return faltantes;
        }

        public void setFaltantes(List<Proceso.Faltante> faltantes) {
            this.faltantes = faltantes;
        }

        public int getAtrasada() {
            return atrasada;
        }

        public void setAtrasada(int atrasada) {
            this.atrasada = atrasada;
        }
    }

    public class Faltante {
        @SerializedName("areaId")
        @Expose
        private Integer areaId;
        @SerializedName("validaciones")
        @Expose
        private Integer validaciones;

        public Integer getAreaId() {
            return areaId;
        }

        public void setAreaId(Integer areaId) {
            this.areaId = areaId;
        }

        public Integer getValidaciones() {
            return validaciones;
        }

        public void setValidaciones(Integer validaciones) {
            this.validaciones = validaciones;
        }
    }
}
