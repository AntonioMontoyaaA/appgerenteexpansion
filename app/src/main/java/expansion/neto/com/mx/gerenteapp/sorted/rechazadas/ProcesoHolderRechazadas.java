package expansion.neto.com.mx.gerenteapp.sorted.rechazadas;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;
import expansion.neto.com.mx.gerenteapp.databinding.ItemProcesoPickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Proceso;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class ProcesoHolderRechazadas extends SortedListAdapter.ViewHolder<Proceso.Memoria> {

    ItemProcesoPickerBinding itemProcesoPickerBinding;

    public ProcesoHolderRechazadas(final ItemProcesoPickerBinding itemProcesoPickerBinding, final ProcesoHolderRechazadas.Listener listener) {
        super(itemProcesoPickerBinding.getRoot());
        this.itemProcesoPickerBinding = itemProcesoPickerBinding;
    }

    @Override
    protected void performBind(Proceso.Memoria item) {
        itemProcesoPickerBinding.setProcesoModel(item);
    }

    public interface Listener {
        void onProcesoSelect(Proceso.Memoria model);
    }
}
