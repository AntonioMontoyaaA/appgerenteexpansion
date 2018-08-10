package expansion.neto.com.mx.gerenteapp.sorted.proceso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.databinding.ItemProcesoPickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Proceso;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;
import expansion.neto.com.mx.gerenteapp.sorted.proceso.ProcesoHolder;

public class AdapterProceso extends SortedListAdapter<Proceso.Memoria> {

    ProcesoHolder.Listener listener;

    Context context;

    public AdapterProceso(Context context, Comparator<Proceso.Memoria> comparator, ProcesoHolder.Listener listener) {
        super(context, Proceso.Memoria.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected SortedListAdapter.ViewHolder<? extends Proceso.Memoria> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemProcesoPickerBinding binding = ItemProcesoPickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        return new ProcesoHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(Proceso.Memoria item1, Proceso.Memoria item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(Proceso.Memoria oldItem, Proceso.Memoria newItem) {
        return false;
    }
}
