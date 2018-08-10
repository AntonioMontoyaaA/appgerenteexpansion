package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class Autoriza implements SortedListAdapter.ViewModel {



    @SerializedName("autoriza")
    @Expose
    private ArrayList<AutorizaModel> autoriza = new ArrayList<AutorizaModel>();
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

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

    public ArrayList<AutorizaModel> getAutoriza() {
        return autoriza;
    }

    public void setAutoriza(ArrayList<AutorizaModel> autoriza) {
        this.autoriza = autoriza;
    }

    public void onClickCalificar(String algo) {
    }


}
