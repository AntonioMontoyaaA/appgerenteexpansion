package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class Zonificacion implements SortedListAdapter.ViewModel {


    @SerializedName("generadores")
    @Expose
    private List<Generadore> generadores = null;
    @SerializedName("competencia")
    @Expose
    private List<Competencium> competencia = null;
    @SerializedName("arrayRadios")
    @Expose
    private List<RadiosArreglo> arrayRadios = null;
    @SerializedName("arrayGenerador")
    @Expose
    private List<GeneradoresArregloInt> arrayGenerador = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
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

    public List<Generadore> getGeneradores() {
        return generadores;
    }

    public void setGeneradores(List<Generadore> generadores) {
        this.generadores = generadores;
    }

    public List<Competencium> getCompetencia() {
        return competencia;
    }

    public void setCompetencia(List<Competencium> competencia) {
        this.competencia = competencia;
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

    public List<RadiosArreglo> getArrayRadios() {
        return arrayRadios;
    }

    public void setArrayRadios(List<RadiosArreglo> arrayRadios) {
        this.arrayRadios = arrayRadios;
    }

    public List<GeneradoresArregloInt> getArrayGenerador() {
        return arrayGenerador;
    }

    public void setArrayGenerador(List<GeneradoresArregloInt> arrayGenerador) {
        this.arrayGenerador = arrayGenerador;
    }

    public void setDetallesValidacion(List<DetallesValidacion> detallesValidacion) {
        this.detallesValidacion = detallesValidacion;
    }



    public class Generadore {

        @SerializedName("descipcion")
        @Expose
        private String descipcion;
        @SerializedName("factorId")
        @Expose
        private Integer factorId;
        @SerializedName("toleranciaMaxima")
        @Expose
        private Integer toleranciaMaxima;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;
        @SerializedName("toleeranciaMinima")
        @Expose
        private Integer toleeranciaMinima;
        @SerializedName("detalle")
        @Expose
        private List<Detalle> detalle = null;
        @SerializedName("unidadMedicion")
        @Expose
        private String unidadMedicion;
        @SerializedName("puntuacion")
        @Expose
        private String puntuacion;

        public String getDescipcion() {
            return descipcion;
        }

        public void setDescipcion(String descipcion) {
            this.descipcion = descipcion;
        }

        public Integer getFactorId() {
            return factorId;
        }

        public void setFactorId(Integer factorId) {
            this.factorId = factorId;
        }

        public Integer getToleranciaMaxima() {
            return toleranciaMaxima;
        }

        public void setToleranciaMaxima(Integer toleranciaMaxima) {
            this.toleranciaMaxima = toleranciaMaxima;
        }

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

        public Integer getToleeranciaMinima() {
            return toleeranciaMinima;
        }

        public void setToleeranciaMinima(Integer toleeranciaMinima) {
            this.toleeranciaMinima = toleeranciaMinima;
        }

        public List<Detalle> getDetalle() {
            return detalle;
        }

        public void setDetalle(List<Detalle> detalle) {
            this.detalle = detalle;
        }

        public String getUnidadMedicion() {
            return unidadMedicion;
        }

        public void setUnidadMedicion(String unidadMedicion) {
            this.unidadMedicion = unidadMedicion;
        }

        public String getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(String puntuacion) {
            this.puntuacion = puntuacion;
        }
    }


    public class Detalle {

        @SerializedName("latitud")
        @Expose
        private String latitud;
        @SerializedName("nombreGenerador")
        @Expose
        private String nombreGenerador;
        @SerializedName("generadorId")
        @Expose
        private Integer generadorId;
        @SerializedName("longitud")
        @Expose
        private String longitud;

        public String getLatitud() {
            return latitud;
        }

        public void setLatitud(String latitud) {
            this.latitud = latitud;
        }

        public String getNombreGenerador() {
            return nombreGenerador;
        }

        public void setNombreGenerador(String nombreGenerador) {
            this.nombreGenerador = nombreGenerador;
        }

        public Integer getGeneradorId() {
            return generadorId;
        }

        public void setGeneradorId(Integer generadorId) {
            this.generadorId = generadorId;
        }

        public String getLongitud() {
            return longitud;
        }

        public void setLongitud(String longitud) {
            this.longitud = longitud;
        }

    }

    public class Competencium implements SortedListAdapter.ViewModel {

        @SerializedName("descipcion")
        @Expose
        private String descipcion;
        @SerializedName("factorId")
        @Expose
        private Integer factorId;
        @SerializedName("toleranciaMaxima")
        @Expose
        private Integer toleranciaMaxima;
        @SerializedName("nivelId")
        @Expose
        private Integer nivelId;
        @SerializedName("toleeranciaMinima")
        @Expose
        private Integer toleeranciaMinima;
        @SerializedName("detalle")
        @Expose
        private List<Detalle> detalle = null;
        @SerializedName("unidadMedicion")
        @Expose
        private String unidadMedicion;
        @SerializedName("puntuacion")
        @Expose
        private String puntuacion;

        public String getDescipcion() {
            return descipcion;
        }

        public void setDescipcion(String descipcion) {
            this.descipcion = descipcion;
        }

        public Integer getFactorId() {
            return factorId;
        }

        public void setFactorId(Integer factorId) {
            this.factorId = factorId;
        }

        public Integer getToleranciaMaxima() {
            return toleranciaMaxima;
        }

        public void setToleranciaMaxima(Integer toleranciaMaxima) {
            this.toleranciaMaxima = toleranciaMaxima;
        }

        public Integer getNivelId() {
            return nivelId;
        }

        public void setNivelId(Integer nivelId) {
            this.nivelId = nivelId;
        }

        public Integer getToleeranciaMinima() {
            return toleeranciaMinima;
        }

        public void setToleeranciaMinima(Integer toleeranciaMinima) {
            this.toleeranciaMinima = toleeranciaMinima;
        }

        public List<Detalle> getDetalle() {
            return detalle;
        }

        public void setDetalle(List<Detalle> detalle) {
            this.detalle = detalle;
        }

        public String getUnidadMedicion() {
            return unidadMedicion;
        }

        public void setUnidadMedicion(String unidadMedicion) {
            this.unidadMedicion = unidadMedicion;
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


    public class RadiosArreglo {
        @SerializedName("latitud")
        @Expose
        private String latitud;
        @SerializedName("competencias")
        @Expose
        private List<CompetenciasArregloInt> competencias = null;
        @SerializedName("generadores")
        @Expose
        private List<GeneradoresArregloInt> generadores = null;
        @SerializedName("anillo")
        @Expose
        private String anillo;
        @SerializedName("radioId")
        @Expose
        private String radioId;
        @SerializedName("longitud")
        @Expose
        private String longitud;

        public String getLatitud() {
            return latitud;
        }

        public void setLatitud(String latitud) {
            this.latitud = latitud;
        }

        public List<GeneradoresArregloInt> getGeneradores() {
            return generadores;
        }

        public void setGeneradores(List<GeneradoresArregloInt> generadores) {
            this.generadores = generadores;
        }

        public String getAnillo() {
            return anillo;
        }

        public void setAnillo(String anillo) {
            this.anillo = anillo;
        }

        public String getRadioId() {
            return radioId;
        }

        public void setRadioId(String radioId) {
            this.radioId = radioId;
        }

        public String getLongitud() {
            return longitud;
        }

        public void setLongitud(String longitud) {
            this.longitud = longitud;
        }

        public List<CompetenciasArregloInt> getCompetencias() {
            return competencias;
        }

        public void setCompetencias(List<CompetenciasArregloInt> competencias) {
            this.competencias = competencias;
        }
    }

    public class CompetenciasArregloInt {

        @SerializedName("latitud")
        @Expose
        private String latitud;
        @SerializedName("competenciaId")
        @Expose
        private String competenciaId;
        @SerializedName("generador")
        @Expose
        private String generador;
        @SerializedName("longitud")
        @Expose
        private String longitud;

        public String getLatitud() {
            return latitud;
        }

        public void setLatitud(String latitud) {
            this.latitud = latitud;
        }

        public String getCompetenciaId() {
            return competenciaId;
        }

        public void setCompetenciaId(String competenciaId) {
            this.competenciaId = competenciaId;
        }

        public String getGenerador() {
            return generador;
        }

        public void setGenerador(String generador) {
            this.generador = generador;
        }

        public String getLongitud() {
            return longitud;
        }

        public void setLongitud(String longitud) {
            this.longitud = longitud;
        }
    }


    public class GeneradoresArregloInt {

        @SerializedName("latitud")
        @Expose
        private String latitud;
        @SerializedName("tipoGenerador")
        @Expose
        private String tipoGenerador;
        @SerializedName("generadorId")
        @Expose
        private String generadorId;
        @SerializedName("generador")
        @Expose
        private String generador;
        @SerializedName("tipogeneradorId")
        @Expose
        private String tipogeneradorId;
        @SerializedName("longitud")
        @Expose
        private String longitud;

        public String getLatitud() {
            return latitud;
        }

        public void setLatitud(String latitud) {
            this.latitud = latitud;
        }

        public String getTipoGenerador() {
            return tipoGenerador;
        }

        public void setTipoGenerador(String tipoGenerador) {
            this.tipoGenerador = tipoGenerador;
        }

        public String getGeneradorId() {
            return generadorId;
        }

        public void setGeneradorId(String generadorId) {
            this.generadorId = generadorId;
        }

        public String getGenerador() {
            return generador;
        }

        public void setGenerador(String generador) {
            this.generador = generador;
        }

        public String getTipogeneradorId() {
            return tipogeneradorId;
        }

        public void setTipogeneradorId(String tipogeneradorId) {
            this.tipogeneradorId = tipogeneradorId;
        }

        public String getLongitud() {
            return longitud;
        }

        public void setLongitud(String longitud) {
            this.longitud = longitud;
        }
    }

}