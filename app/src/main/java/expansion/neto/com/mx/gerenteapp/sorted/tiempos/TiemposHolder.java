package expansion.neto.com.mx.gerenteapp.sorted.tiempos;

import expansion.neto.com.mx.gerenteapp.databinding.ItemTiemposPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.TiemposProceso;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class TiemposHolder extends SortedListAdapter.ViewHolder<TiemposProceso.Seguimiento> {

    ItemTiemposPrickerBinding itemTiemposPrickerBinding;

    public TiemposHolder(final ItemTiemposPrickerBinding itemTiemposPrickerBinding, final Listener listener) {
        super(itemTiemposPrickerBinding.getRoot());
        this.itemTiemposPrickerBinding = itemTiemposPrickerBinding;
    }

    @Override
    protected void performBind(TiemposProceso.Seguimiento item) {
        itemTiemposPrickerBinding.setAutorizaModel(item);
    }

    public interface Listener {
        void onAutorizaSelect(TiemposProceso.Seguimiento model);
    }

}
