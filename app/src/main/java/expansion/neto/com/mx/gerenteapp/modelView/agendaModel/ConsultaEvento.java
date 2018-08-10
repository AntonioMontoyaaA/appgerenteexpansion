package expansion.neto.com.mx.gerenteapp.modelView.agendaModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class ConsultaEvento {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("agenda")
    @Expose
    private List<Agenda> agenda = null;

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

    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    public class Agenda implements SortedListAdapter.ViewModel {

        @SerializedName("lugar")
        @Expose
        private String lugar;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("fechaCompleta")
        @Expose
        private String fechaCompleta;
        @SerializedName("fechaCompletaFinal")
        @Expose
        private String fechaCompletaFinal;
        @SerializedName("diaMes")
        @Expose
        private String diaMes;
        @SerializedName("tiempoInicio")
        @Expose
        private String tiempoInicio;
        @SerializedName("nombre")
        @Expose
        private String nombre;
        @SerializedName("tiempoFinal")
        @Expose
        private String tiempoFinal;
        @SerializedName("descripcion")
        @Expose
        private String descripcion;

        @SerializedName("nombreUsuarioAsignado")
        @Expose
        private String nombreUsuarioAsignado;
        @SerializedName("nombreUsuarioAsigna")
        @Expose
        private String nombreUsuarioAsigna;
        @SerializedName("listaUsuariosAsignados")
        @Expose
        private List<ListaUsuariosAsignado> listaUsuariosAsignados = null;

        public List<ListaUsuariosAsignado> getListaUsuariosAsignados() {
            return listaUsuariosAsignados;
        }

        public void setListaUsuariosAsignados(List<ListaUsuariosAsignado> listaUsuariosAsignados) {
            this.listaUsuariosAsignados = listaUsuariosAsignados;
        }

        public String getFechaCompletaFinal() {

            fechaCompletaFinal = fechaCompletaFinal.substring(0,10);

            return fechaCompletaFinal;
        }

        public void setFechaCompletaFinal(String fechaCompletaFinal) {
            this.fechaCompletaFinal = fechaCompletaFinal;
        }

        public String getNombreUsuarioAsignado() {
            return nombreUsuarioAsignado;
        }

        public void setNombreUsuarioAsignado(String nombreUsuarioAsignado) {
            this.nombreUsuarioAsignado = nombreUsuarioAsignado;
        }

        public String getNombreUsuarioAsigna() {
            return nombreUsuarioAsigna;
        }

        public void setNombreUsuarioAsigna(String nombreUsuarioAsigna) {
            this.nombreUsuarioAsigna = nombreUsuarioAsigna;
        }

        public String getLugar() {
            return lugar;
        }

        public void setLugar(String lugar) {
            this.lugar = lugar;
        }

        public String getStatus() {

            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFechaCompleta() {


            fechaCompleta = fechaCompleta.substring(0,10);

            return fechaCompleta;
        }

        public void setFechaCompleta(String fechaCompleta) {
            this.fechaCompleta = fechaCompleta;
        }

        public String getDiaMes() {
            return diaMes;
        }

        public void setDiaMes(String diaMes) {
            this.diaMes = diaMes;
        }

        public String getTiempoInicio() {

            tiempoInicio = tiempoInicio.substring(0,5);

            return tiempoInicio;
        }

        public void setTiempoInicio(String tiempoInicio) {
            this.tiempoInicio = tiempoInicio;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTiempoFinal() {
            tiempoFinal = tiempoFinal.substring(0,5);
            return tiempoFinal;
        }

        public void setTiempoFinal(String tiempoFinal) {
            this.tiempoFinal = tiempoFinal;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripciN) {
            this.descripcion = descripcion;
        }

    }

    public class ListaUsuariosAsignado {

        @SerializedName("nombre")
        @Expose
        private String nombre;
        @SerializedName("id")
        @Expose
        private String id;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
