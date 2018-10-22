package expansion.neto.com.mx.gerenteapp.modelView.procesoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

import static expansion.neto.com.mx.gerenteapp.utils.Util.getFechita;

public class ChatNumMensajes {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("comentarios")
    @Expose
    private List<Comentario> comentarios = null;
    @SerializedName("mtvRechazo")
    @Expose
    private String mtvRechazo;

    public String getMtvRechazo() {
        return mtvRechazo;
    }

    public void setMtvRechazo(String mtvRechazo) {
        this.mtvRechazo = mtvRechazo;
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

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public class Comentario implements SortedListAdapter.ViewModel {

        @SerializedName("estatus")
        @Expose
        private String estatus;
        @SerializedName("estatusId")
        @Expose
        private Integer estatusId;
        @SerializedName("numMensajesSum")
        @Expose
        private Integer numMensajesSum;
        @SerializedName("numMensajesNuevos")
        @Expose
        private Integer numMensajesNuevos;
        @SerializedName("numMensajesNuevosSum")
        @Expose
        private Integer numMensajesNuevosSum;
        @SerializedName("estatusEvaluacion")
        @Expose
        private Integer estatusEvaluacion;
        @SerializedName("numMensajes")
        @Expose
        private Integer numMensajes;
        @SerializedName("mensaje")
        @Expose
        private String mensaje;
        @SerializedName("prioridad")
        @Expose
        private Integer prioridad;
        @SerializedName("fechaMensaje")
        @Expose
        private String fechaMensaje;
        @SerializedName("numero")
        @Expose
        private String numero;


        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getFechaMensaje() {

            String fechaHoy = getFechita();

            if(!fechaMensaje.equals("")){
                String fechaServicio = fechaMensaje.substring(0,10);
                if(fechaHoy.equals(fechaServicio)){
                    return fechaMensaje.substring(11, 19);
                }else{
                    return fechaServicio;
                }
            }
            return "-";
        }

        public void setFechaMensaje(String fechaMensaje) {
            this.fechaMensaje = fechaMensaje;
        }

        public String getEstatus() {
            return estatus;
        }

        public void setEstatus(String estatus) {
            this.estatus = estatus;
        }

        public Integer getEstatusId() {
            return estatusId;
        }

        public void setEstatusId(Integer estatusId) {
            this.estatusId = estatusId;
        }

        public Integer getNumMensajesSum() {
            return numMensajesSum;
        }

        public void setNumMensajesSum(Integer numMensajesSum) {
            this.numMensajesSum = numMensajesSum;
        }

        public Integer getNumMensajesNuevos() {
            return numMensajesNuevos;
        }

        public void setNumMensajesNuevos(Integer numMensajesNuevos) {
            this.numMensajesNuevos = numMensajesNuevos;
        }

        public Integer getNumMensajesNuevosSum() {
            return numMensajesNuevosSum;
        }

        public void setNumMensajesNuevosSum(Integer numMensajesNuevosSum) {
            this.numMensajesNuevosSum = numMensajesNuevosSum;
        }

        public Integer getEstatusEvaluacion() {
            return estatusEvaluacion;
        }

        public void setEstatusEvaluacion(Integer estatusEvaluacion) {
            this.estatusEvaluacion = estatusEvaluacion;
        }

        public Integer getNumMensajes() {



            return numMensajes;
        }

        public void setNumMensajes(Integer numMensajes) {
            this.numMensajes = numMensajes;
        }

        public String getMensaje() {

            if(mensaje.length()>50){
                String s = mensaje.substring(0, 48);
                s = s + "...";
                return s;
            }else{
                return mensaje;
            }
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public Integer getPrioridad() {
            return prioridad;
        }

        public void setPrioridad(Integer prioridad) {
            this.prioridad = prioridad;
        }
    }


}
