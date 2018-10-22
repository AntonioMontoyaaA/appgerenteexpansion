package expansion.neto.com.mx.gerenteapp.sorted.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.databinding.ItemNumMensajesPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatNumMensajes;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Clase que implementa el adaptador de los Recycler View
 * Created by Kevin on 26/6/2017.
 */
public class AdapterNumMensajes extends SortedListAdapter<ChatNumMensajes.Comentario> {

    NumMensajesHolder.Listener listener;

    Context context;

    public AdapterNumMensajes(Context context, Comparator<ChatNumMensajes.Comentario> comparator, NumMensajesHolder.Listener listener) {
        super(context, ChatNumMensajes.Comentario.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected ViewHolder<? extends ChatNumMensajes.Comentario> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemNumMensajesPrickerBinding binding = ItemNumMensajesPrickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        return new NumMensajesHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(ChatNumMensajes.Comentario item1, ChatNumMensajes.Comentario item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(ChatNumMensajes.Comentario oldItem, ChatNumMensajes.Comentario newItem) {
        return false;
    }
}