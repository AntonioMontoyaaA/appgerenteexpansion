package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DatosSitio {


    @SerializedName("datossitio")
    @Expose
    private List<datos> datossitio = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public List<datos> getDatossitio() {
        return datossitio;
    }

    public void setDatossitio(List<datos> datossitio) {
        this.datossitio = datossitio;
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


    public class datos{

        @SerializedName("tipoUbicacionMD")
        @Expose
        private String tipoUbicacionMD;
        @SerializedName("mdId")
        @Expose
        private String mdId;
        @SerializedName("latitud")
        @Expose
        private String latitud;
        @SerializedName("nombreSitio")
        @Expose
        private String nombreSitio;
        @SerializedName("puntuacion")
        @Expose
        private Integer puntuacion;
        @SerializedName("ciudad")
        @Expose
        private String ciudad;
        @SerializedName("codigoPostal")
        @Expose
        private String codigoPostal;
        @SerializedName("estado")
        @Expose
        private String estado;
        @SerializedName("municipio")
        @Expose
        private String municipio;
        @SerializedName("direccion")
        @Expose
        private String direccion;
        @SerializedName("longitud")
        @Expose
        private String longitud;
        @SerializedName("localidad")
        @Expose
        private String localidad;
        @SerializedName("detallesValidacion")
        @Expose
        private List<DetallesValidacion> detallesValidacion = null;
        @SerializedName("fechaCreacion")
        @Expose
        private String fechaCreacion;
        @SerializedName("puntajeTotal")
        @Expose
        private String puntajeTotal;
        @SerializedName("categoria")
        @Expose
        private String categoria;
        @SerializedName("totalmd")
        @Expose
        private String totalmd;
        @SerializedName("validado")
        @Expose
        private int validado;

        public int getValidado() {
            return validado;
        }

        public void setValidado(int validado) {
            this.validado = validado;
        }

        public String getTotalmd() {
            return totalmd;
        }

        public void setTotalmd(String totalmd) {
            this.totalmd = totalmd;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public String getPuntajeTotal() {
            return puntajeTotal;
        }

        public void setPuntajeTotal(String puntajeTotal) {
            this.puntajeTotal = puntajeTotal;
        }

        public String getFechaCreacion() {

            if(fechaCreacion!=null){
                fechaCreacion = fechaCreacion.substring(0,10);
            }

            return fechaCreacion;
        }

        public void setFechaCreacion(String fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
        }

        public List<DetallesValidacion> getDetallesValidacion() {
            return detallesValidacion;
        }

        public void setDetallesValidacion(List<DetallesValidacion> detallesValidacion) {
            this.detallesValidacion = detallesValidacion;
        }

        public String getTipoUbicacionMD() {
            return tipoUbicacionMD;
        }

        public void setTipoUbicacionMD(String tipoUbicacionMD) {
            this.tipoUbicacionMD = tipoUbicacionMD;
        }

        public String getMdId() {
            return mdId;
        }

        public void setMdId(String mdId) {
            this.mdId = mdId;
        }

        public String getLatitud() {
            return latitud;
        }

        public void setLatitud(String latitud) {
            this.latitud = latitud;
        }

        public String getNombreSitio() {
            return nombreSitio;
        }

        public void setNombreSitio(String nombreSitio) {
            this.nombreSitio = nombreSitio;
        }

        public Integer getPuntuacion() {
            return puntuacion;
        }

        public void setPuntuacion(Integer puntuacion) {
            this.puntuacion = puntuacion;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

        public String getCodigoPostal() {
            return codigoPostal;
        }

        public void setCodigoPostal(String codigoPostal) {
            this.codigoPostal = codigoPostal;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getMunicipio() {
            return municipio;
        }

        public void setMunicipio(String municipio) {
            this.municipio = municipio;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getLongitud() {
            return longitud;
        }

        public void setLongitud(String longitud) {
            this.longitud = longitud;
        }

        public String getLocalidad() {
            return localidad;
        }

        public void setLocalidad(String localidad) {
            this.localidad = localidad;
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
