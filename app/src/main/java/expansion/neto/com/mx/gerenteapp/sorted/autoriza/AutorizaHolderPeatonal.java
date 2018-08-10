package expansion.neto.com.mx.gerenteapp.sorted.autoriza;


import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizaPeatonalPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class AutorizaHolderPeatonal extends SortedListAdapter.ViewHolder<Peatonal> {

    ItemAutorizaPeatonalPrickerBinding itemAutorizaPeatonalPrickerBinding;

    public AutorizaHolderPeatonal(final ItemAutorizaPeatonalPrickerBinding itemAutorizaPeatonalPrickerBinding, final Listener listener) {
        super(itemAutorizaPeatonalPrickerBinding.getRoot());
        this.itemAutorizaPeatonalPrickerBinding = itemAutorizaPeatonalPrickerBinding;
    }

    @Override
    protected void performBind(Peatonal item) {
        itemAutorizaPeatonalPrickerBinding.setPeatonal(item);
    }

    public interface Listener {
        void onAutorizaSelect(Peatonal model);
    }

}
