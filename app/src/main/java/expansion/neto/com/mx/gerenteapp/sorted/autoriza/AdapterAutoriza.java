package expansion.neto.com.mx.gerenteapp.sorted.autoriza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizaPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Clase que implementa el adaptador de los Recycler View
 * Created by Kevin on 26/6/2017.
 */
public class AdapterAutoriza extends SortedListAdapter<Autorizadas.Memoria> {

    AutorizaHolder.Listener listener;

    Context context;

    public AdapterAutoriza(Context context, Comparator<Autorizadas.Memoria> comparator, AutorizaHolder.Listener listener) {
        super(context, Autorizadas.Memoria.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected SortedListAdapter.ViewHolder<? extends Autorizadas.Memoria> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemAutorizaPrickerBinding binding = ItemAutorizaPrickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        return new AutorizaHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(Autorizadas.Memoria item1, Autorizadas.Memoria item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(Autorizadas.Memoria oldItem, Autorizadas.Memoria newItem) {
        return false;
    }
}