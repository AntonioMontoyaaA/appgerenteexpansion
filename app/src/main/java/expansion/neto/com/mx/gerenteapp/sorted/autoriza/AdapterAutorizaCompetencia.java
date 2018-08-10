package expansion.neto.com.mx.gerenteapp.sorted.autoriza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizaCompetenciaPrickerBinding;
import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizaPeatonalPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Zonificacion;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;


/**
 * Clase que implementa el adaptador de los Recycler View
 * Created by Kevin on 26/6/2017.
 */
public class AdapterAutorizaCompetencia extends SortedListAdapter<Zonificacion.Competencium> {

    AutorizaHolderCompetencia.Listener listener;
    Context context;

    public AdapterAutorizaCompetencia(Context context, Comparator<Zonificacion.Competencium> comparator, AutorizaHolderCompetencia.Listener listener) {
        super(context, Zonificacion.Competencium.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected ViewHolder<? extends Zonificacion.Competencium> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemAutorizaCompetenciaPrickerBinding binding = ItemAutorizaCompetenciaPrickerBinding.inflate(inflater, parent, false);
        //binding.setListener(listener);
        return new AutorizaHolderCompetencia(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(Zonificacion.Competencium item1, Zonificacion.Competencium item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(Zonificacion.Competencium oldItem, Zonificacion.Competencium newItem) {
        return false;
    }

}