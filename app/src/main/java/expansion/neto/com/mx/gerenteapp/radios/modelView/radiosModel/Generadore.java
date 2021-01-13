
package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Generadore {

    @SerializedName("hospitales")
    @Expose
    private String hospitales;
    @SerializedName("escuelas")
    @Expose
    private String escuelas;
    @SerializedName("mercados")
    @Expose
    private String mercados;
    @SerializedName("templos")
    @Expose
    private String templos;

    public String getHospitales() {
        return hospitales;
    }

    public void setHospitales(String hospitales) {
        this.hospitales = hospitales;
    }

    public String getEscuelas() {
        return escuelas;
    }

    public void setEscuelas(String escuelas) {
        this.escuelas = escuelas;
    }

    public String getMercados() {
        return mercados;
    }

    public void setMercados(String mercados) {
        this.mercados = mercados;
    }

    public String getTemplos() {
        return templos;
    }

    public void setTemplos(String templos) {
        this.templos = templos;
    }

}
