package expansion.neto.com.mx.gerenteapp.modelView.agendaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;
import expansion.neto.com.mx.gerenteapp.utils.Util;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class Notificaciones implements SortedListAdapter.ViewModel {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("totalNotificaciones")
    @Expose
    private Integer totalNotificaciones;
    @SerializedName("notificaciones")
    @Expose
    private List<Notificacione> notificaciones = null;

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

    public Integer getTotalNotificaciones() {
        return totalNotificaciones;
    }

    public void setTotalNotificaciones(Integer totalNotificaciones) {
        this.totalNotificaciones = totalNotificaciones;
    }

    public List<Notificacione> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacione> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public class Notificacione implements SortedListAdapter.ViewModel{

        @SerializedName("mdId")
        @Expose
        private String mdId;
        @SerializedName("estatus")
        @Expose
        private String estatus;
        @SerializedName("usuarioRegistra")
        @Expose
        private String usuarioRegistra;
        @SerializedName("tipoNotificacion")
        @Expose
        private String tipoNotificacion;
        @SerializedName("fechaRegistro")
        @Expose
        private String fechaRegistro;
        @SerializedName("mensaje")
        @Expose
        private String mensaje;
        @SerializedName("nombreSitio")
        @Expose
        private String nombreSitio;
        @SerializedName("nivelEstatusAreaId")
        @Expose
        private String nivelEstatusAreaId;

        public String getNivelEstatusAreaId() {
            return nivelEstatusAreaId;
        }

        public void setNivelEstatusAreaId(String nivelEstatusAreaId) {
            this.nivelEstatusAreaId = nivelEstatusAreaId;
        }

        public String getMdId() {
            return mdId;
        }

        public void setMdId(String mdId) {
            this.mdId = mdId;
        }

        public String getEstatus() {
            if(estatus==null){
                estatus = "1";
            }
            return estatus;
        }

        public void setEstatus(String estatus) {
            this.estatus = estatus;
        }

        public String getUsuarioRegistra() {
            return usuarioRegistra;
        }

        public void setUsuarioRegistra(String usuarioRegistra) {
            this.usuarioRegistra = usuarioRegistra;
        }

        public String getTipoNotificacion() {
            return tipoNotificacion;
        }

        public void setTipoNotificacion(String tipoNotificacion) {
            this.tipoNotificacion = tipoNotificacion;
        }

        public String getFechaRegistro() {
            return fechaRegistro;
        }

        public void setFechaRegistro(String fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getNombreSitio() {
            return nombreSitio;
        }

        public void setNombreSitio(String nombreSitio) {
            this.nombreSitio = nombreSitio;
        }


    }

}
