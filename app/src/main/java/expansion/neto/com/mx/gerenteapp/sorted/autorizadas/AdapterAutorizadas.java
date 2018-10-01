package expansion.neto.com.mx.gerenteapp.sorted.autorizadas;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizadasPickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class AdapterAutorizadas extends SortedListAdapter<Autorizadas.Autorizada> {

    AutorizadasHolder.Listener listener;
    Context context;

    public AdapterAutorizadas(Context context, Comparator<Autorizadas.Autorizada> comparator, AutorizadasHolder.Listener listener) {
        super(context, Autorizadas.Autorizada.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected ViewHolder<? extends Autorizadas.Autorizada> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {

        final ItemAutorizadasPickerBinding binding = ItemAutorizadasPickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        binding.setTipoCard(1);
        return new AutorizadasHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(Autorizadas.Autorizada item1, Autorizadas.Autorizada item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(Autorizadas.Autorizada oldItem, Autorizadas.Autorizada newItem) {
        return false;
    }

}
