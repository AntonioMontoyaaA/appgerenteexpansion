package expansion.neto.com.mx.gerenteapp.modelView.procesoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class ChatProceso implements SortedListAdapter.ViewModel {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("nombreSitio")
    @Expose
    private String nombreSitio;
    @SerializedName("puntuacion")
    @Expose
    private int puntuacion;
    @SerializedName("fechaCreacion")
    @Expose
    private String fechaCreacion;
    @SerializedName("comentarios")
    @Expose
    private List<MensajeChat> comentarios = null;
    @SerializedName("mtvRechazoid")
    @Expose
    private int mtvRechazoid;
    @SerializedName("mtvRechazo")
    @Expose
    private String mtvRechazo;
    @SerializedName("recDefinitivo")
    @Expose
    private int recDefinitivo;

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombreSitio() {
        return nombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        this.nombreSitio = nombreSitio;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<MensajeChat> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<MensajeChat> comentarios) {
        this.comentarios = comentarios;
    }

    public int getMtvRechazoid() {
        return mtvRechazoid;
    }

    public void setMtvRechazoid(int mtvRechazoid) {
        this.mtvRechazoid = mtvRechazoid;
    }

    public String getMtvRechazo() {
        return mtvRechazo;
    }

    public void setMtvRechazo(String mtvRechazo) {
        this.mtvRechazo = mtvRechazo;
    }

    public int getRecDefinitivo() {
        return recDefinitivo;
    }

    public void setRecDefinitivo(int recDefinitivo) {
        this.recDefinitivo = recDefinitivo;
    }

    public static class MensajeChat  implements SortedListAdapter.ViewModel {

        @SerializedName("nombreusuario")
        @Expose
        private String nombreusuario;
        @SerializedName("areaid")
        @Expose
        private Integer areaid;
        @SerializedName("usuarioid")
        @Expose
        private Integer usuarioid;
        @SerializedName("nombrefactor")
        @Expose
        private String nombrefactor;
        @SerializedName("fecharegistro")
        @Expose
        private String fecharegistro;
        @SerializedName("factorbusquedaid")
        @Expose
        private Integer factorbusquedaid;
        @SerializedName("tipocomentario")
        @Expose
        private Integer tipocomentario;
        @SerializedName("comentario")
        @Expose
        private String comentario;
        @SerializedName("nombrearea")
        @Expose
        private String nombrearea;

        public String getNombreusuario() {
            return nombreusuario;
        }

        public void setNombreusuario(String nombreusuario) {
            this.nombreusuario = nombreusuario;
        }

        public Integer getAreaid() {
            return areaid;
        }

        public void setAreaid(Integer areaid) {
            this.areaid = areaid;
        }

        public Integer getUsuarioid() {
            return usuarioid;
        }

        public void setUsuarioid(Integer usuarioid) {
            this.usuarioid = usuarioid;
        }

        public String getNombrefactor() {
            return nombrefactor;
        }

        public void setNombrefactor(String nombrefactor) {
            this.nombrefactor = nombrefactor;
        }

        public String getFecharegistro() {
            return fecharegistro;
        }

        public void setFecharegistro(String fecharegistro) {
            this.fecharegistro = fecharegistro;
        }

        public Integer getFactorbusquedaid() {
            return factorbusquedaid;
        }

        public void setFactorbusquedaid(Integer factorbusquedaid) {
            this.factorbusquedaid = factorbusquedaid;
        }

        public Integer getTipocomentario() {
            return tipocomentario;
        }

        public void setTipocomentario(Integer tipocomentario) {
            this.tipocomentario = tipocomentario;
        }

        public String getComentario() {
            return comentario;
        }

        public void setComentario(String comentario) {
            this.comentario = comentario;
        }

        public String getNombrearea() {
            return nombrearea;
        }

        public void setNombrearea(String nombrearea) {
            this.nombrearea = nombrearea;
        }
    }
}
