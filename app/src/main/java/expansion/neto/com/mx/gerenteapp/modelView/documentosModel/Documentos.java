package expansion.neto.com.mx.gerenteapp.modelView.documentosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Documentos {

    @SerializedName("datos")
    @Expose
    private List<Dato> datos = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public List<Dato> getDatos() {
        return datos;
    }

    public void setDatos(List<Dato> datos) {
        this.datos = datos;
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

    public class Dato {

        @SerializedName("descripcion")
        @Expose
        private String descripcion;
        @SerializedName("documentos")
        @Expose
        private List<Documento> documentos = null;
        @SerializedName("opcional")
        @Expose
        private String opcional;
        @SerializedName("documentoId")
        @Expose
        private String documentoId;



        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public List<Documento> getDocumentos() {
            return documentos;
        }

        public void setDocumentos(List<Documento> documentos) {
            this.documentos = documentos;
        }

        public String getOpcional() {
            return opcional;
        }

        public void setOpcional(String opcional) {
            this.opcional = opcional;
        }

        public String getDocumentoId() {
            return documentoId;
        }

        public void setDocumentoId(String documentoId) {
            this.documentoId = documentoId;
        }


    }


    public static class Documento {

        @SerializedName("mdId")
        @Expose
        private String mdId;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("consecutivo")
        @Expose
        private String consecutivo;
        @SerializedName("fecha")
        @Expose
        private String fecha;
        @SerializedName("nombre")
        @Expose
        private String nombreArchivo;

        public Documento(){}

        public Documento(String mdId, String url, String consecutivo) {
            this.mdId = mdId;
            this.url = url;
            this.consecutivo = consecutivo;
        }


        public String getNombreArchivo() {
            return nombreArchivo;
        }

        public void setNombreArchivo(String nombreArchivo) {
            this.nombreArchivo = nombreArchivo;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getMdId() {
            return mdId;
        }

        public void setMdId(String mdId) {
            this.mdId = mdId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getConsecutivo() {
            return consecutivo;
        }

        public void setConsecutivo(String consecutivo) {
            this.consecutivo = consecutivo;
        }

    }

    public static class obtieneDocumento {

        @SerializedName("fechaFoto")
        @Expose
        private String fechaFoto;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("idDocumento")
        @Expose
        private String idDocumento;

        public obtieneDocumento(){}
        public obtieneDocumento(String fechaFoto, String url, String idDocumento) {
            this.fechaFoto = fechaFoto;
            this.url = url;
            this.idDocumento = idDocumento;
        }

        public String getFechaFoto() {
            return fechaFoto;
        }

        public void setFechaFoto(String fechaFoto) {
            this.fechaFoto = fechaFoto;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIdDocumento() {
            return idDocumento;
        }

        public void setIdDocumento(String idDocumento) {
            this.idDocumento = idDocumento;
        }
    }


    public static class Doc {

       String usuarioId;
       String mdId;
       String tipoServicio;
       String fecha;
       String docto;

    }


}
