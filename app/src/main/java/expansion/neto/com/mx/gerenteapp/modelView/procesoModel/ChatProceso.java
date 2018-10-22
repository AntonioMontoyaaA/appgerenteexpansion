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
    @SerializedName("mtvRechazo")
    @Expose
    private String mtvRechazo;
    @SerializedName("mtvRechazoid")
    @Expose
    private String mtvRechazoid;
    @SerializedName("recDefinitivo")
    @Expose
    private String recDefinitivo;
    @SerializedName("correccionesMaximas")
    @Expose
    private String correccionesMaximas;


    public String getCorreccionesMaximas() {
        return correccionesMaximas;
    }

    public void setCorreccionesMaximas(String correccionesMaximas) {
        this.correccionesMaximas = correccionesMaximas;
    }

    public String getMtvRechazo() {
        return mtvRechazo;
    }

    public void setMtvRechazo(String mtvRechazo) {
        this.mtvRechazo = mtvRechazo;
    }

    public String getMtvRechazoid() {
        return mtvRechazoid;
    }

    public void setMtvRechazoid(String mtvRechazoid) {
        this.mtvRechazoid = mtvRechazoid;
    }

    public String getRecDefinitivo() {
        return recDefinitivo;
    }

    public void setRecDefinitivo(String recDefinitivo) {
        this.recDefinitivo = recDefinitivo;
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

    public static class MensajeChat  implements SortedListAdapter.ViewModel {

        @SerializedName("autor")
        @Expose
        private String autor;
        @SerializedName("puesto")
        @Expose
        private String puesto;
        @SerializedName("areaId")
        @Expose
        private Integer areaId;
        @SerializedName("usuarioId")
        @Expose
        private Integer usuarioId;
        @SerializedName("nombreFactor")
        @Expose
        private Object nombreFactor;
        @SerializedName("area")
        @Expose
        private String area;
        @SerializedName("factorBusquedaId")
        @Expose
        private Integer factorBusquedaId;
        @SerializedName("fecha")
        @Expose
        private String fecha;
        @SerializedName("tipoComentario")
        @Expose
        private Integer tipoComentario;
        @SerializedName("comentario")
        @Expose
        private String comentario;

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public String getPuesto() {
            return puesto;
        }

        public void setPuesto(String puesto) {
            this.puesto = puesto;
        }

        public Integer getAreaId() {
            return areaId;
        }

        public void setAreaId(Integer areaId) {
            this.areaId = areaId;
        }

        public Integer getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(Integer usuarioId) {
            this.usuarioId = usuarioId;
        }

        public Object getNombreFactor() {
            return nombreFactor;
        }

        public void setNombreFactor(Object nombreFactor) {
            this.nombreFactor = nombreFactor;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public Integer getFactorBusquedaId() {
            return factorBusquedaId;
        }

        public void setFactorBusquedaId(Integer factorBusquedaId) {
            this.factorBusquedaId = factorBusquedaId;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public Integer getTipoComentario() {
            return tipoComentario;
        }

        public void setTipoComentario(Integer tipoComentario) {
            this.tipoComentario = tipoComentario;
        }

        public String getComentario() {
            return comentario;
        }

        public void setComentario(String comentario) {
            this.comentario = comentario;
        }


//        @SerializedName("nombreusuario")
//        @Expose
//        private String nombreusuario;
//        @SerializedName("areaid")
//        @Expose
//        private Integer areaid;
//        @SerializedName("usuarioid")
//        @Expose
//        private Integer usuarioid;
//        @SerializedName("nombrefactor")
//        @Expose
//        private String nombrefactor;
//        @SerializedName("fecharegistro")
//        @Expose
//        private String fecharegistro;
//        @SerializedName("factorbusquedaid")
//        @Expose
//        private Integer factorbusquedaid;
//        @SerializedName("tipocomentario")
//        @Expose
//        private Integer tipocomentario;
//        @SerializedName("comentario")
//        @Expose
//        private String comentario;
//        @SerializedName("nombrearea")
//        @Expose
//        private String nombrearea;
//        @SerializedName("correccionesMD")
//        @Expose
//        private String correccionesMD;
//
//        public String getCorreccionesMD() {
//            return correccionesMD;
//        }
//
//        public void setCorreccionesMD(String correccionesMD) {
//            this.correccionesMD = correccionesMD;
//        }
//
//        public String getNombreusuario() {
//            return nombreusuario;
//        }
//
//        public void setNombreusuario(String nombreusuario) {
//            this.nombreusuario = nombreusuario;
//        }
//
//        public Integer getAreaid() {
//            return areaid;
//        }
//
//        public void setAreaid(Integer areaid) {
//            this.areaid = areaid;
//        }
//
//        public Integer getUsuarioid() {
//            return usuarioid;
//        }
//
//        public void setUsuarioid(Integer usuarioid) {
//            this.usuarioid = usuarioid;
//        }
//
//        public String getNombrefactor() {
//            return nombrefactor;
//        }
//
//        public void setNombrefactor(String nombrefactor) {
//            this.nombrefactor = nombrefactor;
//        }
//
//        public String getFecharegistro() {
//            return fecharegistro;
//        }
//
//        public void setFecharegistro(String fecharegistro) {
//            this.fecharegistro = fecharegistro;
//        }
//
//        public Integer getFactorbusquedaid() {
//            return factorbusquedaid;
//        }
//
//        public void setFactorbusquedaid(Integer factorbusquedaid) {
//            this.factorbusquedaid = factorbusquedaid;
//        }
//
//        public Integer getTipocomentario() {
//            return tipocomentario;
//        }
//
//        public void setTipocomentario(Integer tipocomentario) {
//            this.tipocomentario = tipocomentario;
//        }
//
//        public String getComentario() {
//            return comentario;
//        }
//
//        public void setComentario(String comentario) {
//            this.comentario = comentario;
//        }
//
//        public String getNombrearea() {
//            return nombrearea;
//        }
//
//        public void setNombrearea(String nombrearea) {
//            this.nombrearea = nombrearea;
//        }
    }
}
