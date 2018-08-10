package expansion.neto.com.mx.gerenteapp.sorted.agenda;


import expansion.neto.com.mx.gerenteapp.databinding.ItemAgendaHorasPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Agenda;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class AgendaHolder extends SortedListAdapter.ViewHolder<Agenda> {

    ItemAgendaHorasPrickerBinding itemAgendaHorasPrickerBinding;

    public AgendaHolder(final ItemAgendaHorasPrickerBinding itemAgendaHorasPrickerBinding, final Listener listener) {
        super(itemAgendaHorasPrickerBinding.getRoot());
        this.itemAgendaHorasPrickerBinding = itemAgendaHorasPrickerBinding;
    }

    @Override
    protected void performBind(Agenda item) {
        itemAgendaHorasPrickerBinding.setAgenda(item);
    }

    public interface Listener {
        void onAgendaSelect(Agenda model);
    }

}
