package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MotivosRechazo {

    @SerializedName("motivos")
    @Expose
    private List<motivos> motivos = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;




    public class motivos {
        @SerializedName("descripcion")
        @Expose
        private String descripcion;
        @SerializedName("motivoId")
        @Expose
        private int motivoId;
        @SerializedName("rechazoDefinitvo")
        @Expose
        private int rechazoDefinitvo;

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public int getMotivoId() {
            return motivoId;
        }

        public void setMotivoId(int motivoId) {
            this.motivoId = motivoId;
        }

        public int getRechazoDefinitvo() {
            return rechazoDefinitvo;
        }

        public void setRechazoDefinitvo(int rechazoDefinitvo) {
            this.rechazoDefinitvo = rechazoDefinitvo;
        }
    }

    public List<MotivosRechazo.motivos> getMotivos() {
        return motivos;
    }

    public void setMotivos(List<MotivosRechazo.motivos> motivos) {
        this.motivos = motivos;
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
}
