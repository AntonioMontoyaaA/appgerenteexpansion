package expansion.neto.com.mx.gerenteapp.sorted.rechazo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.databinding.ItemRechazoPickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Proceso;
import expansion.neto.com.mx.gerenteapp.modelView.rechazoModel.Rechazo;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;
import expansion.neto.com.mx.gerenteapp.sorted.proceso.ProcesoHolder;

public class AdapterRechazo extends SortedListAdapter<Rechazo.Memoria> {

    RechazoHolder.Listener listener;

    Context context;

    public AdapterRechazo(Context context, Comparator<Rechazo.Memoria> comparator, RechazoHolder.Listener listener) {
        super(context, Rechazo.Memoria.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected SortedListAdapter.ViewHolder<? extends Rechazo.Memoria> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemRechazoPickerBinding binding = ItemRechazoPickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        return new RechazoHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(Rechazo.Memoria item1, Rechazo.Memoria item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(Rechazo.Memoria oldItem, Rechazo.Memoria newItem) {
        return false;
    }

}
