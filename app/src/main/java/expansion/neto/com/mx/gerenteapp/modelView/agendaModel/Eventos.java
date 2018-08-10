package expansion.neto.com.mx.gerenteapp.modelView.agendaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class Eventos {

    @SerializedName("eventos")
    @Expose
    private List<Evento> eventos = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
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

    public class Evento {

        @SerializedName("eventoId")
        @Expose
        private Integer eventoId;
        @SerializedName("nombre")
        @Expose
        private String nombre;
        @SerializedName("subEventos")
        @Expose
        private List<Object> subEventos = null;

        public Integer getEventoId() {
            return eventoId;
        }

        public void setEventoId(Integer eventoId) {
            this.eventoId = eventoId;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public List<Object> getSubEventos() {
            return subEventos;
        }

        public void setSubEventos(List<Object> subEventos) {
            this.subEventos = subEventos;
        }

    }

}
