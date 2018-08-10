package expansion.neto.com.mx.gerenteapp.modelView.agendaModel;

import com.google.gson.annotations.SerializedName;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class Agenda implements SortedListAdapter.ViewModel {

    @SerializedName("numeroMemoriaDescriptiva") public String numeroMemoriaDescriptiva;
    @SerializedName("lugarMemoriaDescriptiva") public String lugarMemoriaDescriptiva;
    @SerializedName("puntosMemoriaDescriptiva") public String puntosMemoriaDescriptiva;
    @SerializedName("categoriaMemoriaDescriptiva") public String categoriaMemoriaDescriptiva;
    @SerializedName("estrellasMemoriaDescriptiva") public String estrellasMemoriaDescriptiva;
    @SerializedName("nombreCreacionMemoriaDescriptiva") public String nombreCreacionMemoriaDescriptiva;

    public Agenda(String numeroMemoriaDescriptiva, String lugarMemoriaDescriptiva, String puntosMemoriaDescriptiva,
                  String categoriaMemoriaDescriptiva, String estrellasMemoriaDescriptiva, String nombreCreacionMemoriaDescriptiva) {
        this.numeroMemoriaDescriptiva = numeroMemoriaDescriptiva;
        this.lugarMemoriaDescriptiva = lugarMemoriaDescriptiva;
        this.puntosMemoriaDescriptiva = puntosMemoriaDescriptiva;
        this.categoriaMemoriaDescriptiva = categoriaMemoriaDescriptiva;
        this.estrellasMemoriaDescriptiva = estrellasMemoriaDescriptiva;
        this.nombreCreacionMemoriaDescriptiva = nombreCreacionMemoriaDescriptiva;
    }
}
