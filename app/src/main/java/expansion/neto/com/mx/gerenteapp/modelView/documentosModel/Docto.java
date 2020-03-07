package expansion.neto.com.mx.gerenteapp.modelView.documentosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Docto {

    private Integer documentoId;
    private List<DocumentoHoja> documentos;
    private String descripcion;
    private Integer opcional;

    public Integer getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Integer documentoId) {
        this.documentoId = documentoId;
    }

    public List<DocumentoHoja> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoHoja> documentos) {
        this.documentos = documentos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getOpcional() {
        return opcional;
    }

    public void setOpcional(Integer opcional) {
        this.opcional = opcional;
    }

    public Docto(){}

    public Docto(Integer documentoId, ArrayList<DocumentoHoja> documentos,
                 String descripcion, Integer opcional) {

        this.documentoId = documentoId;
        this.documentos = documentos;
        this.descripcion = descripcion;
        this.opcional = opcional;
    }


    public static class DocumentoHoja {

        private String url;
        private String fecha;
        private String nombreArchivo;

        public DocumentoHoja(){}

        public DocumentoHoja(String url, String fecha, String nombreArchivo) {
            this.url = url;
            this.fecha = fecha;
            this.nombreArchivo = nombreArchivo;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getNombreArchivo() {
            return nombreArchivo;
        }

        public void setNombreArchivo(String nombreArchivo) {
            this.nombreArchivo = nombreArchivo;
        }

    }

}
