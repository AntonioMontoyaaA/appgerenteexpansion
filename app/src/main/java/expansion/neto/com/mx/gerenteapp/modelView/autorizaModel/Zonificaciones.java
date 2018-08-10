package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zonificaciones {

    @SerializedName("lat")
    @Expose
    private Integer lat;
    @SerializedName("lot")
    @Expose
    private Integer lot;
    @SerializedName("tipo")
    @Expose
    private String tipo;

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getLot() {
        return lot;
    }

    public void setLot(Integer lot) {
        this.lot = lot;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}