package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatosConstruccion {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("construccion")
    @Expose
    private List<Construccion> construccion = null;
    @SerializedName("tip")
    @Expose
    private List<Tip> tip;
    @SerializedName("puntoFac")
    @Expose
    private String puntoFac;
    @SerializedName("validado")
    @Expose
    private int validado;
    @SerializedName("detallesValidacion")
    @Expose
    private List<DetallesValidacion> detallesValidacion;

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

    public List<Construccion> getConstruccion() {
        return construccion;
    }

    public void setConstruccion(List<Construccion> construccion) {
        this.construccion = construccion;
    }

    public List<Tip> getTip() {
        return tip;
    }

    public void setTip(List<Tip> tip) {
        this.tip = tip;
    }

    public String getPuntoFac() {
        return puntoFac;
    }

    public void setPuntoFac(String puntoFac) {
        this.puntoFac = puntoFac;
    }

    public int getValidado() {
        return validado;
    }

    public void setValidado(int validado) {
        this.validado = validado;
    }

    public List<DetallesValidacion> getDetallesValidacion() {
        return detallesValidacion;
    }

    public void setDetallesValidacion(List<DetallesValidacion> detallesValidacion) {
        this.detallesValidacion = detallesValidacion;
    }

    public class Detalle {

        @SerializedName("nombredetalle")
        @Expose
        private String nombredetalle;
        @SerializedName("detalleid")
        @Expose
        private Integer detalleid;

        public String getNombredetalle() {
            return nombredetalle;
        }

        public void setNombredetalle(String nombredetalle) {
            this.nombredetalle = nombredetalle;
        }

        public Integer getDetalleid() {
            return detalleid;
        }

        public void setDetalleid(Integer detalleid) {
            this.detalleid = detalleid;
        }

    }

    public class Construccion {

        @SerializedName("nombrenivel")
        @Expose
        private String nombrenivel;
        @SerializedName("usuario")
        @Expose
        private String usuario;
        @SerializedName("puntos")
        @Expose
        private Integer puntos;
        @SerializedName("nivelid")
        @Expose
        private Integer nivelid;
        @SerializedName("detalles")
        @Expose
        private List<Detalle> detalles = null;
        @SerializedName("facorbusquedaid")
        @Expose
        private Integer facorbusquedaid;
        @SerializedName("fecharegistro")
        @Expose
        private String fecharegistro;
        @SerializedName("puntuacion")
        @Expose
        private String puntuacion;

        public String getNombrenivel() {
            return nombrenivel;
        }

        public void setNombrenivel(String nombrenivel) {
            this.nombrenivel = nombrenivel;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public Integer getPuntos() {
            return puntos;
        }

        public void setPuntos(Integer puntos) {
            this.puntos = puntos;
        }

        public Integer getNivelid() {
            return nivelid;
        }

        public void setNivelid(Integer nivelid) {
            this.nivelid = nivelid;
        }

        public List<Detalle> getDetalles() {
            return detalles;
        }

        public void setDetalles(List<Detalle> detalles) {
            this.detalles = detalles;
        }

        public Integer getFacorbusquedaid() {
            return facorbusquedaid;
        }

        public void setFacorbusquedaid(Integer facorbusquedaid) {
            this.facorbusquedaid = facorbusquedaid;
        }

        public String getFecharegistro() {
            return fecharegistro;
        }

        public void setFecharegistro(String fecharegistro) {
            this.fecharegistro = fecharegistro;
        }

        public String getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(String puntuacion) {
            this.puntuacion = puntuacion;
        }
    }

    public class Tip {
        @SerializedName("detalle")
        @Expose
        private String detalle;

        public String getDetalle() {
            return detalle;
        }

        public void setDetalle(String detalle) {
            this.detalle = detalle;
        }
    }

    public class DetallesValidacion {
        @SerializedName("USUARIOVALIDOID")
        @Expose
        private int USUARIOVALIDOID;
        @SerializedName("MOTIVORECHAZOID")
        @Expose
        private int MOTIVORECHAZOID;
        @SerializedName("FECHAVALIDACION")
        @Expose
        private String FECHAVALIDACION;
        @SerializedName("NOMAREA")
        @Expose
        private String NOMAREA;
        @SerializedName("NIVELESTATUSID")
        @Expose
        private int NIVELESTATUSID;
        @SerializedName("NOMPUESTO")
        @Expose
        private String NOMPUESTO;
        @SerializedName("NOMUSUARIO")
        @Expose
        private String NOMUSUARIO;
        @SerializedName("NOMMOTIVORECHAZO")
        @Expose
        private String NOMMOTIVORECHAZO;
        @SerializedName("PUESTOID")
        @Expose
        private int PUESTOID;
        @SerializedName("ESTATUSVALIDACION")
        @Expose
        private String ESTATUSVALIDACION;
        @SerializedName("RECHAZODEFINITIVO")
        @Expose
        private int RECHAZODEFINITIVO;

        public int getUSUARIOVALIDOID() {
            return USUARIOVALIDOID;
        }

        public void setUSUARIOVALIDOID(int USUARIOVALIDOID) {
            this.USUARIOVALIDOID = USUARIOVALIDOID;
        }

        public int getMOTIVORECHAZOID() {
            return MOTIVORECHAZOID;
        }

        public void setMOTIVORECHAZOID(int MOTIVORECHAZOID) {
            this.MOTIVORECHAZOID = MOTIVORECHAZOID;
        }

        public String getFECHAVALIDACION() {
            return FECHAVALIDACION;
        }

        public void setFECHAVALIDACION(String FECHAVALIDACION) {
            this.FECHAVALIDACION = FECHAVALIDACION;
        }

        public String getNOMAREA() {
            return NOMAREA;
        }

        public void setNOMAREA(String NOMAREA) {
            this.NOMAREA = NOMAREA;
        }

        public int getNIVELESTATUSID() {
            return NIVELESTATUSID;
        }

        public void setNIVELESTATUSID(int NIVELESTATUSID) {
            this.NIVELESTATUSID = NIVELESTATUSID;
        }

        public String getNOMPUESTO() {
            return NOMPUESTO;
        }

        public void setNOMPUESTO(String NOMPUESTO) {
            this.NOMPUESTO = NOMPUESTO;
        }

        public String getNOMUSUARIO() {
            return NOMUSUARIO;
        }

        public void setNOMUSUARIO(String NOMUSUARIO) {
            this.NOMUSUARIO = NOMUSUARIO;
        }

        public String getNOMMOTIVORECHAZO() {
            return NOMMOTIVORECHAZO;
        }

        public void setNOMMOTIVORECHAZO(String NOMMOTIVORECHAZO) {
            this.NOMMOTIVORECHAZO = NOMMOTIVORECHAZO;
        }

        public int getPUESTOID() {
            return PUESTOID;
        }

        public void setPUESTOID(int PUESTOID) {
            this.PUESTOID = PUESTOID;
        }

        public String getESTATUSVALIDACION() {
            return ESTATUSVALIDACION;
        }

        public void setESTATUSVALIDACION(String ESTATUSVALIDACION) {
            this.ESTATUSVALIDACION = ESTATUSVALIDACION;
        }

        public int getRECHAZODEFINITIVO() {
            return RECHAZODEFINITIVO;
        }

        public void setRECHAZODEFINITIVO(int RECHAZODEFINITIVO) {
            this.RECHAZODEFINITIVO = RECHAZODEFINITIVO;
        }
    }

}
