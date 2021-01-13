package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Competencia implements Parcelable {

    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("competenciaId")
    @Expose
    private String competenciaId;
    @SerializedName("generador")
    @Expose
    private String generador;
    @SerializedName("longitud")
    @Expose
    private String longitud;

    protected Competencia(Parcel in) {
        latitud = in.readString();
        competenciaId = in.readString();
        generador = in.readString();
        longitud = in.readString();
    }

    public static final Creator<Competencia> CREATOR = new Creator<Competencia>() {
        @Override
        public Competencia createFromParcel(Parcel in) {
            return new Competencia( in );
        }

        @Override
        public Competencia[] newArray(int size) {
            return new Competencia[size];
        }
    };

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(String competenciaId) {
        this.competenciaId = competenciaId;
    }

    public String getGenerador() {
        return generador;
    }

    public void setGenerador(String generador) {
        this.generador = generador;
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
        dest.writeString( competenciaId );
        dest.writeString( generador );
        dest.writeString( longitud );
    }
}
