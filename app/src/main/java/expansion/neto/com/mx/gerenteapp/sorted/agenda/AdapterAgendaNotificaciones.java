package expansion.neto.com.mx.gerenteapp.sorted.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.databinding.ItemAgendaNotificacionesPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Notificaciones;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;


/**
 * Clase que implementa el adaptador de los Recycler View
 * Created by Kevin on 26/6/2017.
 */
public class AdapterAgendaNotificaciones extends SortedListAdapter<Notificaciones.Notificacione> {

    AgendaEventosNotificacionesHolder.Listener listener;

    Context context;

    public AdapterAgendaNotificaciones(Context context, Comparator<Notificaciones.Notificacione> comparator, AgendaEventosNotificacionesHolder.Listener listener) {
        super(context, Notificaciones.Notificacione.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected ViewHolder<? extends Notificaciones.Notificacione> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemAgendaNotificacionesPrickerBinding binding = ItemAgendaNotificacionesPrickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        return new AgendaEventosNotificacionesHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(Notificaciones.Notificacione item1, Notificaciones.Notificacione item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(Notificaciones.Notificacione oldItem, Notificaciones.Notificacione newItem) {
        return false;
    }
}