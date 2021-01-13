package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SinSitios {

    @SerializedName("estatus")
    @Expose
    private String estatus;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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
