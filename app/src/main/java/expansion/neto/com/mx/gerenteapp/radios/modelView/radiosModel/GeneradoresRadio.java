package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneradoresRadio implements Parcelable {
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("tipoGenerador")
    @Expose
    private String tipoGenerador;
    @SerializedName("generadorId")
    @Expose
    private String generadorId;
    @SerializedName("generador")
    @Expose
    private String generador;
    @SerializedName("tipogeneradorId")
    @Expose
    private String tipogeneradorId;
    @SerializedName("longitud")
    @Expose
    private String longitud;

    protected GeneradoresRadio(Parcel in) {
        latitud = in.readString();
        tipoGenerador = in.readString();
        generadorId = in.readString();
        generador = in.readString();
        tipogeneradorId = in.readString();
        longitud = in.readString();
    }

    public static final Creator<GeneradoresRadio> CREATOR = new Creator<GeneradoresRadio>() {
        @Override
        public GeneradoresRadio createFromParcel(Parcel in) {
            return new GeneradoresRadio( in );
        }

        @Override
        public GeneradoresRadio[] newArray(int size) {
            return new GeneradoresRadio[size];
        }
    };

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getTipoGenerador() {
        return tipoGenerador;
    }

    public void setTipoGenerador(String tipoGenerador) {
        this.tipoGenerador = tipoGenerador;
    }

    public String getGeneradorId() {
        return generadorId;
    }

    public void setGeneradorId(String generadorId) {
        this.generadorId = generadorId;
    }

    public String getGenerador() {
        return generador;
    }

    public void setGenerador(String generador) {
        this.generador = generador;
    }

    public String getTipogeneradorId() {
        return tipogeneradorId;
    }

    public void setTipogeneradorId(String tipogeneradorId) {
        this.tipogeneradorId = tipogeneradorId;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( latitud );
        dest.writeString( tipoGenerador );
        dest.writeString( generadorId );
        dest.writeString( generador );
        dest.writeString( tipogeneradorId );
        dest.writeString( longitud );
    }
}
