package expansion.neto.com.mx.gerenteapp.modelView.loginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsuarioLogin {

    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("perfil")
    @Expose
    private Perfil perfil;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public class Areasxpuesto {

        @SerializedName("areaId")
        @Expose
        private Integer areaId;
        @SerializedName("areaNom")
        @Expose
        private String areaNom;

        public Integer getAreaId() {
            return areaId;
        }

        public void setAreaId(Integer areaId) {
            this.areaId = areaId;
        }

        public String getAreaNom() {
            return areaNom;
        }

        public void setAreaNom(String areaNom) {
            this.areaNom = areaNom;
        }

    }


    public static class Perfil {

        @SerializedName("zonasxusuario")
        @Expose
        private List<Zonasxusuario> zonasxusuario = null;
        @SerializedName("realMes")
        @Expose
        private Integer realMes;
        @SerializedName("realSemana")
        @Expose
        private Integer realSemana;
        @SerializedName("correo")
        @Expose
        private String correo;
        @SerializedName("numAutorizar")
        @Expose
        private Integer numAutorizar;
        @SerializedName("planSemana")
        @Expose
        private Integer planSemana;
        @SerializedName("areasxpuesto")
        @Expose
        private List<Areasxpuesto> areasxpuesto = null;
        @SerializedName("planMes")
        @Expose
        private Integer planMes;
        @SerializedName("apellidoM")
        @Expose
        private String apellidoM;
        @SerializedName("apellidoP")
        @Expose
        private String apellidoP;
        @SerializedName("perfilesxusuario")
        @Expose
        private List<Perfilesxusuario> perfilesxusuario = null;
        @SerializedName("nombre")
        @Expose
        private String nombre;
        @SerializedName("telefono")
        @Expose
        private String telefono;

        private String usuario;
        private String contra;
        private String imei;
        private String puestoId;

        public String getPuestoId() {
            return puestoId;
        }

        public void setPuestoId(String puestoId) {
            this.puestoId = puestoId;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getContra() {
            return contra;
        }

        public void setContra(String contra) {
            this.contra = contra;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public List<Zonasxusuario> getZonasxusuario() {
            return zonasxusuario;
        }

        public void setZonasxusuario(List<Zonasxusuario> zonasxusuario) {
            this.zonasxusuario = zonasxusuario;
        }

        public Integer getRealMes() {
            return realMes;
        }

        public void setRealMes(Integer realMes) {
            this.realMes = realMes;
        }

        public Integer getRealSemana() {
            return realSemana;
        }

        public void setRealSemana(Integer realSemana) {
            this.realSemana = realSemana;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public Integer getNumAutorizar() {
            return numAutorizar;
        }

        public void setNumAutorizar(Integer numAutorizar) {
            this.numAutorizar = numAutorizar;
        }

        public Integer getPlanSemana() {
            return planSemana;
        }

        public void setPlanSemana(Integer planSemana) {
            this.planSemana = planSemana;
        }

        public List<Areasxpuesto> getAreasxpuesto() {
            return areasxpuesto;
        }

        public void setAreasxpuesto(List<Areasxpuesto> areasxpuesto) {
            this.areasxpuesto = areasxpuesto;
        }

        public Integer getPlanMes() {
            return planMes;
        }

        public void setPlanMes(Integer planMes) {
            this.planMes = planMes;
        }

        public String getApellidoM() {
            return apellidoM;
        }

        public void setApellidoM(String apellidoM) {
            this.apellidoM = apellidoM;
        }

        public String getApellidoP() {
            return apellidoP;
        }

        public void setApellidoP(String apellidoP) {
            this.apellidoP = apellidoP;
        }

        public List<Perfilesxusuario> getPerfilesxusuario() {
            return perfilesxusuario;
        }

        public void setPerfilesxusuario(List<Perfilesxusuario> perfilesxusuario) {
            this.perfilesxusuario = perfilesxusuario;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

    }


    public class Perfilesxusuario {

        @SerializedName("perfilid")
        @Expose
        private Integer perfilid;
        @SerializedName("permisos")
        @Expose
        private List<Permiso> permisos = null;
        @SerializedName("nombreperfil")
        @Expose
        private String nombreperfil;

        public Integer getPerfilid() {
            return perfilid;
        }

        public void setPerfilid(Integer perfilid) {
            this.perfilid = perfilid;
        }

        public List<Permiso> getPermisos() {
            return permisos;
        }

        public void setPermisos(List<Permiso> permisos) {
            this.permisos = permisos;
        }

        public String getNombreperfil() {
            return nombreperfil;
        }

        public void setNombreperfil(String nombreperfil) {
            this.nombreperfil = nombreperfil;
        }

    }

    public class Zonasxusuario {

        @SerializedName("FECHAREGISTRO")
        @Expose
        private String fECHAREGISTRO;
        @SerializedName("ZONAID")
        @Expose
        private Integer zONAID;
        @SerializedName("ESTATUS")
        @Expose
        private Integer eSTATUS;
        @SerializedName("USUARIOREGISTRAID")
        @Expose
        private Integer uSUARIOREGISTRAID;

        public String getFECHAREGISTRO() {
            return fECHAREGISTRO;
        }

        public void setFECHAREGISTRO(String fECHAREGISTRO) {
            this.fECHAREGISTRO = fECHAREGISTRO;
        }

        public Integer getZONAID() {
            return zONAID;
        }

        public void setZONAID(Integer zONAID) {
            this.zONAID = zONAID;
        }

        public Integer getESTATUS() {
            return eSTATUS;
        }

        public void setESTATUS(Integer eSTATUS) {
            this.eSTATUS = eSTATUS;
        }

        public Integer getUSUARIOREGISTRAID() {
            return uSUARIOREGISTRAID;
        }

        public void setUSUARIOREGISTRAID(Integer uSUARIOREGISTRAID) {
            this.uSUARIOREGISTRAID = uSUARIOREGISTRAID;
        }

    }

}

