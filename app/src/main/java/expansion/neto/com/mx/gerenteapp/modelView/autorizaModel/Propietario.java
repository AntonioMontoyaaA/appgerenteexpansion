package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Propietario {

    @SerializedName("aPaternoPropietario")
    @Expose
    private String aPaternoPropietario;
    @SerializedName("aMaternoPropietario")
    @Expose
    private String aMaternoPropietario;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("propietarioId")
    @Expose
    private Integer propietarioId;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("nombrePropietario")
    @Expose
    private String nombrePropietario;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("rentaMasLocales")
    @Expose
    private int rentaMasLocales;
    @SerializedName("tip")
    @Expose
    private List<Tip> tip;
    @SerializedName("detallesValidacion")
    @Expose
    private List<DetallesValidacion> detallesValidacion;

    public String getAPaternoPropietario() {
        return aPaternoPropietario;
    }

    public void setAPaternoPropietario(String aPaternoPropietario) {
        this.aPaternoPropietario = aPaternoPropietario;
    }

    public String getAMaternoPropietario() {
        return aMaternoPropietario;
    }

    public void setAMaternoPropietario(String aMaternoPropietario) {
        this.aMaternoPropietario = aMaternoPropietario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(Integer propietarioId) {
        this.propietarioId = propietarioId;
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

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getaPaternoPropietario() {
        return aPaternoPropietario;
    }

    public void setaPaternoPropietario(String aPaternoPropietario) {
        this.aPaternoPropietario = aPaternoPropietario;
    }

    public String getaMaternoPropietario() {
        return aMaternoPropietario;
    }

    public void setaMaternoPropietario(String aMaternoPropietario) {
        this.aMaternoPropietario = aMaternoPropietario;
    }

    public int getRentaMasLocales() {
        return rentaMasLocales;
    }

    public void setRentaMasLocales(int rentaMasLocales) {
        this.rentaMasLocales = rentaMasLocales;
    }

    public List<Tip> getTip() {
        return tip;
    }

    public void setTip(List<Tip> tip) {
        this.tip = tip;
    }

    public List<DetallesValidacion> getDetallesValidacion() {
        return detallesValidacion;
    }

    public void setDetallesValidacion(List<DetallesValidacion> detallesValidacion) {
        this.detallesValidacion = detallesValidacion;
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
