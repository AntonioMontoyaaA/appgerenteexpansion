package expansion.neto.com.mx.gerenteapp.sorted.autoriza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizaPeatonalPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;


/**
 * Clase que implementa el adaptador de los Recycler View
 * Created by Kevin on 26/6/2017.
 */
public class AdapterAutorizaPeatonal extends SortedListAdapter<Peatonal> {

    AutorizaHolderPeatonal.Listener listener;
    Context context;

    public AdapterAutorizaPeatonal(Context context, Comparator<Peatonal> comparator, AutorizaHolderPeatonal.Listener listener) {
        super(context, Peatonal.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected ViewHolder<? extends Peatonal> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemAutorizaPeatonalPrickerBinding binding = ItemAutorizaPeatonalPrickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        return new AutorizaHolderPeatonal(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(Peatonal item1, Peatonal item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(Peatonal oldItem, Peatonal newItem) {
        return false;
    }
}