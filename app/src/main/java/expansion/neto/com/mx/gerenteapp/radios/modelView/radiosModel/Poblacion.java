
package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Poblacion {

    @SerializedName("poblacion")
    @Expose
    private String poblacion;
    @SerializedName("viviendas")
    @Expose
    private String viviendas;
    @SerializedName("pea")
    @Expose
    private String pea;
    @SerializedName("nse")
    @Expose
    private String nse;

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getViviendas() {
        return viviendas;
    }

    public void setViviendas(String viviendas) {
        this.viviendas = viviendas;
    }

    public String getPea() {
        return pea;
    }

    public void setPea(String pea) {
        this.pea = pea;
    }

    public String getNse() {
        return nse;
    }

    public void setNse(String nse) {
        this.nse = nse;
    }

}
