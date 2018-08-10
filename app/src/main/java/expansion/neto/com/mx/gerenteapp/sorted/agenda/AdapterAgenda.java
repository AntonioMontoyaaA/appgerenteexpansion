package expansion.neto.com.mx.gerenteapp.sorted.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Agenda;
import expansion.neto.com.mx.gerenteapp.databinding.ItemAgendaHorasPrickerBinding;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;


/**
 * Clase que implementa el adaptador de los Recycler View
 * Created by Kevin on 26/6/2017.
 */
public class AdapterAgenda extends SortedListAdapter<Agenda> {

    AgendaHolder.Listener listener;

    Context context;

    public AdapterAgenda(Context context, Comparator<Agenda> comparator, AgendaHolder.Listener listener) {
        super(context, Agenda.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected SortedListAdapter.ViewHolder<? extends Agenda> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemAgendaHorasPrickerBinding binding = ItemAgendaHorasPrickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        return new AgendaHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(Agenda item1, Agenda item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(Agenda oldItem, Agenda newItem) {
        return false;
    }
}