
package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Radios {

    /*@SerializedName("detalleRadios")
    @Expose
    private List<RadioLista> radiosLista = null;*/
    @SerializedName("detalleRadios")
    @Expose
    private List<DetalleRadio> detalleRadios = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("estatusRadios")
    @Expose
    private List<EstatusRadio> estatusRadios = null;




    /*public List<RadioLista> getRadiosLista() {
        return radiosLista;
    }

    public void setRadiosLista(List<RadioLista> radiosLista) {
        this.radiosLista = radiosLista;
    }*/



    public List<DetalleRadio> getDetalleRadios() {
        return detalleRadios;
    }

    public void setDetalleRadios(List<DetalleRadio> detalleRadios) {
        this.detalleRadios = detalleRadios;
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

    public List<EstatusRadio> getEstatusRadios() {
        return estatusRadios;
    }

    public void setEstatusRadios(List<EstatusRadio> estatusRadios) {
        this.estatusRadios = estatusRadios;
    }
    /*public List<EstatusRadio> getEstatusRadios() {
        return estatusRadios;
    }

    public void setEstatusRadios(List<EstatusRadio> estatusRadios) {
        this.estatusRadios = estatusRadios;
    }*/

}
