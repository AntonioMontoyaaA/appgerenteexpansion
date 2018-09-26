package expansion.neto.com.mx.gerenteapp.sorted.autorizadas;

import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizadasPickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizadasPickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class AutorizadasHolder extends SortedListAdapter.ViewHolder<Autorizadas.Autorizada> {

    ItemAutorizadasPickerBinding itemProcesoPickerBinding;

    public AutorizadasHolder(final ItemAutorizadasPickerBinding itemProcesoPickerBinding, final AutorizadasHolder.Listener listener) {
        super(itemProcesoPickerBinding.getRoot());
        this.itemProcesoPickerBinding = itemProcesoPickerBinding;
    }

    @Override
    protected void performBind(Autorizadas.Autorizada item) {
        itemProcesoPickerBinding.setProcesoModel(item);
    }

    public interface Listener {
        void onProcesoSelect(Autorizadas.Autorizada model);
    }
}
