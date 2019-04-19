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
    private Double frente;
    @SerializedName("fondo")
    @Expose
    private Double fondo;
    @SerializedName("puntuacion")
    @Expose
    private Double puntuacion;
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

    public Double getFrente() {
        return frente;
    }

    public void setFrente(Double frente) {
        this.frente = frente;
    }

    public Double getFondo() {
        return fondo;
    }

    public void setFondo(Double fondo) {
        this.fondo = fondo;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
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
        private Double fondo;
        @SerializedName("puntuacion")
        @Expose
        private Double puntuacion;
        @SerializedName("valorreal")
        @Expose
        private Double valorreal;
        @SerializedName("imgLateral2Id")
        @Expose
        private String imgLateral2Id;
        @SerializedName("imgLateral1Id")
        @Expose
        private String imgLateral1Id;
        @SerializedName("imgPredial")
        @Expose
        private String imgPredial;
        @SerializedName("imgEnt1")
        @Expose
        private String imgEnt1;
        @SerializedName("fecha_ent1")
        @Expose
        private String fecha_ent1;
        @SerializedName("imgEnt2")
        @Expose
        private String imgEnt2;
        @SerializedName("fecha_ent2")
        @Expose
        private String fecha_ent2;
        @SerializedName("imgEnt3")
        @Expose
        private String imgEnt3;
        @SerializedName("fecha_ent3")
        @Expose
        private String fecha_ent3;
        @SerializedName("imgAgua")
        @Expose
        private String imgAgua;
        @SerializedName("imgLuz")
        @Expose
        private String imgLuz;


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

        public Double getFondo() {
            return fondo;
        }

        public void setFondo(Double fondo) {
            this.fondo = fondo;
        }

        public Double getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(Double puntuacion) {
            this.puntuacion = puntuacion;
        }

        public Double getValorreal() {
            return valorreal;
        }

        public void setValorreal(Double valorreal) {
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

        public String getImgPredial() {
            return imgPredial;
        }

        public void setImgPredial(String imgPredial) {
            this.imgPredial = imgPredial;
        }

        public String getImgEnt1() {
            return imgEnt1;
        }

        public void setImgEnt1(String imgEnt1) {
            this.imgEnt1 = imgEnt1;
        }

        public String getFecha_ent1() {
            return fecha_ent1;
        }

        public void setFecha_ent1(String fecha_ent1) {
            this.fecha_ent1 = fecha_ent1;
        }

        public String getImgEnt2() {
            return imgEnt2;
        }

        public void setImgEnt2(String imgEnt2) {
            this.imgEnt2 = imgEnt2;
        }

        public String getFecha_ent2() {
            return fecha_ent2;
        }

        public void setFecha_ent2(String fecha_ent2) {
            this.fecha_ent2 = fecha_ent2;
        }

        public String getImgEnt3() {
            return imgEnt3;
        }

        public void setImgEnt3(String imgEnt3) {
            this.imgEnt3 = imgEnt3;
        }

        public String getFecha_ent3() {
            return fecha_ent3;
        }

        public void setFecha_ent3(String fecha_ent3) {
            this.fecha_ent3 = fecha_ent3;
        }

        public String getImgAgua() {
            return imgAgua;
        }

        public void setImgAgua(String imgAgua) {
            this.imgAgua = imgAgua;
        }

        public String getImgLuz() {
            return imgLuz;
        }

        public void setImgLuz(String imgLuz) {
            this.imgLuz = imgLuz;
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
