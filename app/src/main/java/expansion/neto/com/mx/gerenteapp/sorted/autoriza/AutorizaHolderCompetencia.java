package expansion.neto.com.mx.gerenteapp.sorted.autoriza;


import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizaCompetenciaPrickerBinding;
import expansion.neto.com.mx.gerenteapp.databinding.ItemAutorizaPeatonalPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Zonificacion;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class AutorizaHolderCompetencia extends SortedListAdapter.ViewHolder<Zonificacion.Competencium> {

    ItemAutorizaCompetenciaPrickerBinding itemAutorizaCompetenciaPrickerBinding;

    public AutorizaHolderCompetencia(final ItemAutorizaCompetenciaPrickerBinding itemAutorizaCompetenciaPrickerBinding, final Listener listener) {
        super(itemAutorizaCompetenciaPrickerBinding.getRoot());
        this.itemAutorizaCompetenciaPrickerBinding = itemAutorizaCompetenciaPrickerBinding;
    }


    @Override
    protected void performBind(Zonificacion.Competencium item) {

    }

    public interface Listener {
        void onAutorizaSelect(Zonificacion.Competencium model);
    }

}
