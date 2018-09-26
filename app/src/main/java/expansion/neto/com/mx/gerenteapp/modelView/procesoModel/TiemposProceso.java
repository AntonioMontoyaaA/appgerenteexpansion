package expansion.neto.com.mx.gerenteapp.modelView.procesoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class TiemposProceso implements SortedListAdapter.ViewModel  {
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("nomsitio")
    @Expose
    private String nomsitio;
    @SerializedName("puntuacion")
    @Expose
    private int puntuacion;
    @SerializedName("creacion")
    @Expose
    private String creacion;
    @SerializedName("seguimiento")
    @Expose
    private List<Seguimiento> seguimiento = null;
    @SerializedName("mtvRechazoid")
    @Expose
    private int mtvRechazoid;
    @SerializedName("mtvRechazo")
    @Expose
    private String mtvRechazo;
    @SerializedName("recDefinitivo")
    @Expose
    private int recDefinitivo;
    @SerializedName("puntajeTot")
    @Expose
    private int puntajeTot;

    public int getPuntajeTot() {
        return puntajeTot;
    }

    public void setPuntajeTot(int puntajeTot) {
        this.puntajeTot = puntajeTot;
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

    public String getNomsitio() {
        return nomsitio;
    }

    public void setNomsitio(String nomsitio) {
        this.nomsitio = nomsitio;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getCreacion() {
        return creacion;
    }

    public void setCreacion(String creacion) {
        this.creacion = creacion;
    }

    public List<Seguimiento> getSeguimiento() {
        return seguimiento;
    }

    public void setSeguimiento(List<Seguimiento> seguimiento) {
        this.seguimiento = seguimiento;
    }

    public class Seguimiento implements SortedListAdapter.ViewModel {
        @SerializedName("areaId")
        @Expose
        private int areaId;
        @SerializedName("entiempo")
        @Expose
        private String entiempo;
        @SerializedName("fechaLimite")
        @Expose
        private String fechaLimite;
        @SerializedName("nombreArea")
        @Expose
        private String nombreArea;
        @SerializedName("fechaAtencion")
        @Expose
        private String fechaAtencion;
        @SerializedName("perfil")
        @Expose
        private int perfil;
        @SerializedName("tieneRechazo")
        @Expose
        private int tieneRechazo;
        @SerializedName("nomArea")
        @Expose
        private String nomArea;

        public String getNomArea() {
            return nomArea;
        }

        public void setNomArea(String nomArea) {
            this.nomArea = nomArea;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getEntiempo() {
            return entiempo;
        }

        public void setEntiempo(String entiempo) {
            this.entiempo = entiempo;
        }

        public String getFechaLimite() {
            return fechaLimite;
        }

        public void setFechaLimite(String fechaLimite) {
            this.fechaLimite = fechaLimite;
        }

        public String getNombreArea() {
            return nombreArea;
        }

        public void setNombreArea(String nombreArea) {
            this.nombreArea = nombreArea;
        }

        public String getFechaAtencion() {
            if(fechaAtencion==null){
                fechaAtencion = "-";
            }
            return fechaAtencion;
        }

        public void setFechaAtencion(String fechaAtencion) {
            this.fechaAtencion = fechaAtencion;
        }

        public int getPerfil() {
            return perfil;
        }

        public void setPerfil(int perfil) {
            this.perfil = perfil;
        }

        public int getTieneRechazo() {
            return tieneRechazo;
        }

        public void setTieneRechazo(int tieneRechazo) {
            this.tieneRechazo = tieneRechazo;
        }
    }
}
