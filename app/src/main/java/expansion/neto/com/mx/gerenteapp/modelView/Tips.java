package expansion.neto.com.mx.gerenteapp.modelView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tips {


    @SerializedName("tips")
    @Expose
    private List<Tip> tips = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public List<Tip> getTips() {
        return tips;
    }

    public void setTips(List<Tip> tips) {
        this.tips = tips;
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

}