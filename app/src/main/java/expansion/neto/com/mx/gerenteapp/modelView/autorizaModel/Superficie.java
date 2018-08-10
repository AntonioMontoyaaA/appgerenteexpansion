package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Superficie {

    @SerializedName("imgFrenteId")
    @Expose
    private String imgFrenteId;
    @SerializedName("frente")
    @Expose
    private Integer frente;
    @SerializedName("fondo")
    @Expose
    private Integer fondo;
    @SerializedName("puntuacion")
    @Expose
    private Integer puntuacion;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("imgLateral2Id")
    @Expose
    private String imgLateral2Id;
    @SerializedName("imgLateral1Id")
    @Expose
    private String imgLateral1Id;
    @SerializedName("puntoFac")
    @Expose
    private String puntoFac;
    @SerializedName("tip")
    @Expose
    private List<Tip> tip;
    @SerializedName("validado")
    @Expose
    private int validado;
    @SerializedName("detallesValidacion")
    @Expose
    private List<DetallesValidacion> detallesValidacion;
    @SerializedName("niveles")
    @Expose
    private List<Nivele> niveles = null;

    public List<Nivele> getNiveles() {
        return niveles;
    }

    public void setNiveles(List<Nivele> niveles) {
        this.niveles = niveles;
    }

    public String getImgFrenteId() {
        return imgFrenteId;
    }

    public void setImgFrenteId(String imgFrenteId) {
        this.imgFrenteId = imgFrenteId;
    }

    public Integer getFrente() {
        return frente;
    }

    public void setFrente(Integer frente) {
        this.frente = frente;
    }

    public Integer getFondo() {
        return fondo;
    }

    public void setFondo(Integer fondo) {
        this.fondo = fondo;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
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

    public String getImgLateral2Id() {
        return imgLateral2Id;
    }

    public void setImgLateral2Id(String imgLateral2Id) {
        this.imgLateral2Id = imgLateral2Id;
    }

    public String getImgLateral1Id() {
        return imgLateral1Id;
    }

    public void setImgLateral1Id(String imgLateral1Id) {
        this.imgLateral1Id = imgLateral1Id;
    }

    public String getPuntoFac() {
        return puntoFac;
    }

    public void setPuntoFac(String puntoFac) {
        this.puntoFac = puntoFac;
    }

    public List<Tip> getTip() {
        return tip;
    }

    public void setTip(List<Tip> tip) {
        this.tip = tip;
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

    public class Nivele {

        @SerializedName("nivel")
        @Expose
        private Integer nivel;
        @SerializedName("nombrenivel")
        @Expose
        private String nombrenivel;
        @SerializedName("imgFrenteId")
        @Expose
        private String imgFrenteId;
        @SerializedName("fondo")
        @Expose
        private Integer fondo;
        @SerializedName("puntuacion")
        @Expose
        private Double puntuacion;
        @SerializedName("valorreal")
        @Expose
        private Integer valorreal;
        @SerializedName("imgLateral2Id")
        @Expose
        private String imgLateral2Id;
        @SerializedName("imgLateral1Id")
        @Expose
        private String imgLateral1Id;

        public Integer getNivel() {
            return nivel;
        }

        public void setNivel(Integer nivel) {
            this.nivel = nivel;
        }

        public String getNombrenivel() {
            return nombrenivel;
        }

        public void setNombrenivel(String nombrenivel) {
            this.nombrenivel = nombrenivel;
        }

        public String getImgFrenteId() {
            return imgFrenteId;
        }

        public void setImgFrenteId(String imgFrenteId) {
            this.imgFrenteId = imgFrenteId;
        }

        public Integer getFondo() {
            return fondo;
        }

        public void setFondo(Integer fondo) {
            this.fondo = fondo;
        }

        public Double getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(Double puntuacion) {
            this.puntuacion = puntuacion;
        }

        public Integer getValorreal() {
            return valorreal;
        }

        public void setValorreal(Integer valorreal) {
            this.valorreal = valorreal;
        }

        public String getImgLateral2Id() {
            return imgLateral2Id;
        }

        public void setImgLateral2Id(String imgLateral2Id) {
            this.imgLateral2Id = imgLateral2Id;
        }

        public String getImgLateral1Id() {
            return imgLateral1Id;
        }

        public void setImgLateral1Id(String imgLateral1Id) {
            this.imgLateral1Id = imgLateral1Id;
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
