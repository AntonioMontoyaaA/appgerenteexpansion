package expansion.neto.com.mx.gerenteapp.sorted.agenda;


import expansion.neto.com.mx.gerenteapp.databinding.ItemAgendaEventosPrickerBinding;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.ConsultaEvento;
import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

public class AgendaEventosHolder extends SortedListAdapter.ViewHolder<ConsultaEvento.Agenda> {

    ItemAgendaEventosPrickerBinding itemAgendaEventosPrickerBinding;

    public AgendaEventosHolder(final ItemAgendaEventosPrickerBinding itemAgendaHorasPrickerBinding, final Listener listener) {
        super(itemAgendaHorasPrickerBinding.getRoot());
        this.itemAgendaEventosPrickerBinding = itemAgendaHorasPrickerBinding;
    }

    @Override
    protected void performBind(ConsultaEvento.Agenda item) {
        itemAgendaEventosPrickerBinding.setAgenda(item);
    }

    public interface Listener {
        void onAgendaSelect(ConsultaEvento.Agenda model);
    }

}
