package expansion.neto.com.mx.gerenteapp.modelView.procesoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Totales {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("atrasada")
    @Expose
    private Integer atrasada;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getAtrasada() {
        return atrasada;
    }

    public void setAtrasada(Integer atrasada) {
        this.atrasada = atrasada;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
