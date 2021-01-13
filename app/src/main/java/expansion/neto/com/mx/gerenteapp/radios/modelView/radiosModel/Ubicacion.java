
package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ubicacion {

    @SerializedName("entreCalle1")
    @Expose
    private String entreCalle1;
    @SerializedName("entreCalle2")
    @Expose
    private String entreCalle2;
    @SerializedName("callePrincipal")
    @Expose
    private String callePrincipal;

    public String getEntreCalle1() {
        return entreCalle1;
    }

    public void setEntreCalle1(String entreCalle1) {
        this.entreCalle1 = entreCalle1;
    }

    public String getEntreCalle2() {
        return entreCalle2;
    }

    public void setEntreCalle2(String entreCalle2) {
        this.entreCalle2 = entreCalle2;
    }

    public String getCallePrincipal() {
        return callePrincipal;
    }

    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

}
