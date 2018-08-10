package expansion.neto.com.mx.gerenteapp.sorted.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.databinding.ItemAgendaEventosPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.ConsultaEvento;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;


/**
 * Clase que implementa el adaptador de los Recycler View
 * Created by Kevin on 26/6/2017.
 */
public class AdapterAgendaEventos extends SortedListAdapter<ConsultaEvento.Agenda> {

    AgendaEventosHolder.Listener listener;

    Context context;

    public AdapterAgendaEventos(Context context, Comparator<ConsultaEvento.Agenda> comparator, AgendaEventosHolder.Listener listener) {
        super(context, ConsultaEvento.Agenda.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected ViewHolder<? extends ConsultaEvento.Agenda> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemAgendaEventosPrickerBinding binding = ItemAgendaEventosPrickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        return new AgendaEventosHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(ConsultaEvento.Agenda item1, ConsultaEvento.Agenda item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(ConsultaEvento.Agenda oldItem, ConsultaEvento.Agenda newItem) {
        return false;
    }
}