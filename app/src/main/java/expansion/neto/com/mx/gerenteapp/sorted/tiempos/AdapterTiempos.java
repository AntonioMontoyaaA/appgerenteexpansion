package expansion.neto.com.mx.gerenteapp.sorted.tiempos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.databinding.ItemTiemposPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.TiemposProceso;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Clase que implementa el adaptador de los Recycler View
 * Created by Kevin on 26/6/2017.
 */
public class AdapterTiempos extends SortedListAdapter<TiemposProceso.Seguimiento> {

    TiemposHolder.Listener listener;

    Context context;

    public AdapterTiempos(Context context, Comparator<TiemposProceso.Seguimiento> comparator, TiemposHolder.Listener listener) {
        super(context, TiemposProceso.Seguimiento.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected ViewHolder<? extends TiemposProceso.Seguimiento> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemTiemposPrickerBinding binding = ItemTiemposPrickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        return new TiemposHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(TiemposProceso.Seguimiento item1, TiemposProceso.Seguimiento item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(TiemposProceso.Seguimiento oldItem, TiemposProceso.Seguimiento newItem) {
        return false;
    }
}