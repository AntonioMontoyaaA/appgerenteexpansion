package expansion.neto.com.mx.gerenteapp.sorted.rechazo;

import expansion.neto.com.mx.gerenteapp.databinding.ItemRechazoPickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.rechazoModel.Rechazo;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class RechazoHolder extends SortedListAdapter.ViewHolder<Rechazo.Memoria> {

    ItemRechazoPickerBinding itemRechazoPickerBinding;

    public RechazoHolder(final ItemRechazoPickerBinding itemRechazoPickerBinding, final RechazoHolder.Listener listener) {
        super(itemRechazoPickerBinding.getRoot());
        this.itemRechazoPickerBinding = itemRechazoPickerBinding;
    }

    @Override
    protected void performBind(Rechazo.Memoria item) {
        itemRechazoPickerBinding.setRechazoModel(item);
    }

    public interface Listener {
        void onRechazoSelect(Rechazo.Memoria model);
    }
}
