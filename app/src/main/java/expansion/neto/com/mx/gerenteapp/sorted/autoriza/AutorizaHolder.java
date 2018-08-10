package expansion.neto.com.mx.gerenteapp.sorted.autoriza;


import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizaPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class AutorizaHolder extends SortedListAdapter.ViewHolder<Autorizadas.Memoria> {

    ItemAutorizaPrickerBinding itemAutorizaPrickerBinding;

    public AutorizaHolder(final ItemAutorizaPrickerBinding itemAutorizaPrickerBinding, final Listener listener) {
        super(itemAutorizaPrickerBinding.getRoot());
        this.itemAutorizaPrickerBinding = itemAutorizaPrickerBinding;
    }

    @Override
    protected void performBind(Autorizadas.Memoria item) {
        itemAutorizaPrickerBinding.setAutorizaModel(item);
    }

    public interface Listener {
        void onAutorizaSelect(Autorizadas.Memoria model);
    }

}
