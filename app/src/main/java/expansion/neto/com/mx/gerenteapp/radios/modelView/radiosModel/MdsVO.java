package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MdsVO implements Parcelable {

    @SerializedName("tiendaId")
    @Expose
    private int tiendaId;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("latitud")
    @Expose
    private double latitud;
    @SerializedName("longitud")
    @Expose
    private double longitud;

    protected MdsVO(Parcel in) {
        tiendaId = in.readInt();
        nombre = in.readString();
        latitud = in.readDouble();
        longitud = in.readDouble();
    }

    public static final Parcelable.Creator<MdsVO> CREATOR = new Parcelable.Creator<MdsVO>() {
        @Override
        public MdsVO createFromParcel(Parcel in) {
            return new MdsVO( in );
        }

        @Override
        public MdsVO[] newArray(int size) {
            return new MdsVO[size];
        }
    };

    public int getTiendaId() {
        return tiendaId;
    }

    public void setTiendaId(int tiendaId) {
        this.tiendaId = tiendaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( tiendaId );
        dest.writeString( nombre );
        dest.writeDouble( latitud );
        dest.writeDouble( longitud );
    }
}
