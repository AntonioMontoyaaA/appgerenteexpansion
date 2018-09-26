package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class Autorizadas implements SortedListAdapter.ViewModel {


    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("memorias")
    @Expose
    private List<Memoria> memorias = null;
    @SerializedName("autorizadas")
    @Expose
    private List<Autorizada> autorizadas = null;

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

    public List<Memoria> getMemorias() {
        return memorias;
    }

    public void setMemorias(List<Memoria> memorias) {
        this.memorias = memorias;
    }

    public List<Autorizada> getAutorizadas() {
        return autorizadas;
    }

    public void setAutorizadas(List<Autorizada> autorizadas) {
        this.autorizadas = autorizadas;
    }

    public class Memoria implements SortedListAdapter.ViewModel {
        @SerializedName("puntuacion")
        @Expose
        private Integer puntuacion;
        @SerializedName("categoria")
        @Expose
        private String categoria;
        @SerializedName("fechaCreacion")
        @Expose
        private String fechaCreacion;
        @SerializedName("estatus")
        @Expose
        private String estatus;
        @SerializedName("totales")
        @Expose
        private Integer totales;
        @SerializedName("estatusid")
        @Expose
        private Integer estatusid;
        @SerializedName("fechaVencimiento")
        @Expose
        private String fechaVencimiento;
        @SerializedName("memoriaid")
        @Expose
        private String memoriaid;
        @SerializedName("faltantes")
        @Expose
        private List<Object> faltantes = null;
        @SerializedName("puntosxcategoria")
        @Expose
        private Integer puntosxcategoria;
        @SerializedName("puntuacionmd")
        @Expose
        private Double puntuacionmd;
        @SerializedName("parciales")
        @Expose
        private Integer parciales;
        @SerializedName("numcorrecciones")
        @Expose
        private Integer numcorrecciones;
        @SerializedName("creador")
        @Expose
        private String creador;
        @SerializedName("atrasada")
        @Expose
        private Integer atrasada;
        @SerializedName("nombresitio")
        @Expose
        private String nombresitio;
        @SerializedName("urllayout")
        @Expose
        private String urllayout;

        @SerializedName("pptoauditoria")
        @Expose
        private String pptoauditoria;
        @SerializedName("pptoobra")
        @Expose
        private String pptoobra;

        public String getPptoauditoria() {
            return pptoauditoria;
        }

        public void setPptoauditoria(String pptoauditoria) {
            this.pptoauditoria = pptoauditoria;
        }

        public String getPptoobra() {
            return pptoobra;
        }

        public void setPptoobra(String pptoobra) {
            this.pptoobra = pptoobra;
        }

        public String getUrllayout() {
            return urllayout;
        }

        public void setUrllayout(String urllayout) {
            this.urllayout = urllayout;
        }

        public Integer getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(Integer puntuacion) {
            this.puntuacion = puntuacion;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getFechaCreacion() {
            return fechaCreacion;
        }

        public void setFechaCreacion(String fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
        }

        public String getEstatus() {
            return estatus;
        }

        public void setEstatus(String estatus) {
            this.estatus = estatus;
        }

        public Integer getTotales() {
            return totales;
        }

        public void setTotales(Integer totales) {
            this.totales = totales;
        }

        public Integer getEstatusid() {
            return estatusid;
        }

        public void setEstatusid(Integer estatusid) {
            this.estatusid = estatusid;
        }

        public String getFechaVencimiento() {
            return fechaVencimiento;
        }

        public void setFechaVencimiento(String fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
        }

        public String getMemoriaid() {
            return memoriaid;
        }

        public void setMemoriaid(String memoriaid) {
            this.memoriaid = memoriaid;
        }

        public List<Object> getFaltantes() {
            return faltantes;
        }

        public void setFaltantes(List<Object> faltantes) {
            this.faltantes = faltantes;
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

        public Integer getNumcorrecciones() {
            return numcorrecciones;
        }

        public void setNumcorrecciones(Integer numcorrecciones) {
            this.numcorrecciones = numcorrecciones;
        }

        public String getCreador() {
            return creador;
        }

        public void setCreador(String creador) {
            this.creador = creador;
        }

        public Integer getAtrasada() {
            return atrasada;
        }

        public void setAtrasada(Integer atrasada) {
            this.atrasada = atrasada;
        }

        public String getNombresitio() {
            return nombresitio;
        }

        public void setNombresitio(String nombresitio) {
            this.nombresitio = nombresitio;
        }
    }

    public class Autorizada implements SortedListAdapter.ViewModel {

        @SerializedName("memoriaid")
        @Expose
        private String memoriaid;
        @SerializedName("categoria")
        @Expose
        private String categoria;
        @SerializedName("puntuacion")
        @Expose
        private Integer puntuacion;
        @SerializedName("fechaatorizacion")
        @Expose
        private String fechaatorizacion;
        @SerializedName("nombresitio")
        @Expose
        private String nombresitio;
        @SerializedName("estatus")
        @Expose
        private Integer atrasada;

        public Integer getAtrasada() {
            return atrasada;
        }

        public void setAtrasada(Integer atrasada) {
            this.atrasada = atrasada;
        }

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

        public Integer getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(Integer puntuacion) {
            this.puntuacion = puntuacion;
        }

        public String getFechaatorizacion() {

            String fecha = fechaatorizacion.substring(0, 10);

            return fecha;
        }

        public void setFechaatorizacion(String fechaatorizacion) {
            this.fechaatorizacion = fechaatorizacion;
        }

        public String getNombresitio() {
            return nombresitio;
        }

        public void setNombresitio(String nombresitio) {
            this.nombresitio = nombresitio;
        }

    }
}
