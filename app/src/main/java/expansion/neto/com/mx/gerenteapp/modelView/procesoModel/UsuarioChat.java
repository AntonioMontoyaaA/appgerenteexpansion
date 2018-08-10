package expansion.neto.com.mx.gerenteapp.modelView.procesoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class UsuarioChat implements SortedListAdapter.ViewModel {
    @SerializedName("usuarioid")
    @Expose
    int usuarioid;
    @SerializedName("nombreusuario")
    @Expose
    String nombreusuario;
    @SerializedName("isSender")
    @Expose
    private boolean isSender;

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public boolean isSender() {
        return isSender;
    }

    public void setSender(boolean sender) {
        isSender = sender;
    }
}
