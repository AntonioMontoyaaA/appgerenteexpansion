package expansion.neto.com.mx.gerenteapp.sorted.chat;

import expansion.neto.com.mx.gerenteapp.databinding.ItemNumMensajesPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatNumMensajes;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class NumMensajesHolder extends SortedListAdapter.ViewHolder<ChatNumMensajes.Comentario> {

    ItemNumMensajesPrickerBinding itemTiemposPrickerBinding;

    public NumMensajesHolder(final ItemNumMensajesPrickerBinding itemTiemposPrickerBinding, final Listener listener) {
        super(itemTiemposPrickerBinding.getRoot());
        this.itemTiemposPrickerBinding = itemTiemposPrickerBinding;
    }

    @Override
    protected void performBind(ChatNumMensajes.Comentario item) {

        itemTiemposPrickerBinding.setAutorizaModel(item);

    }

    public interface Listener {
        void onAutorizaSelect(ChatNumMensajes.Comentario model);
    }

}
