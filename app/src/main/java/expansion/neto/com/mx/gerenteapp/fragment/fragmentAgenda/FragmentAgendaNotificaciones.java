package expansion.neto.com.mx.gerenteapp.fragment.fragmentAgenda;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAgendaNotificacionesBinding;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Notificaciones;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderObtieneNotificaciones;
import expansion.neto.com.mx.gerenteapp.sorted.agenda.AdapterAgendaNotificaciones;
import expansion.neto.com.mx.gerenteapp.sorted.agenda.AgendaEventosNotificacionesHolder;
import expansion.neto.com.mx.gerenteapp.utils.Util;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.TIPO_NOTIFICACION;

public class FragmentAgendaNotificaciones extends Fragment {

    private FragmentAgendaNotificacionesBinding binding;
    AdapterAgendaNotificaciones adapter;

    AgendaEventosNotificacionesHolder.Listener listener = new AgendaEventosNotificacionesHolder.Listener() {
        @Override
        public void onAgendaSelect(Notificaciones.Notificacione model) {

        }
    };

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agenda_notificaciones,container,false);
        View view = binding.getRoot();

        Date date = new Date();
        binding.nombreSemana.setText("Semana "+getSemana(date));

        adapter = new AdapterAgendaNotificaciones(getContext(), ALPHABETICAL_COMPARATOR, listener);
        binding.recyclerAutoriza.setHasFixedSize(true);
        binding.recyclerAutoriza.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerAutoriza.setAdapter(adapter);


        Date hoy = Calendar.getInstance().getTime();
        binding.fecha.setText(Util.getFechaFormat(hoy));
        String upperString = Util.getFechaDay(hoy).substring(0,1).toUpperCase() + Util.getFechaDay(hoy).substring(1);
        binding.dia.setText(upperString);
        getNotificaciones();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void getNotificaciones(){
        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuarioId = preferences.getString("usuario", "");
        ProviderObtieneNotificaciones.getInstance(getContext()).obtenerNotificaciones(usuarioId, TIPO_NOTIFICACION, new ProviderObtieneNotificaciones.InterfaceObtieneNotificaciones() {
            @Override
            public void resolve(Notificaciones eventos) {
                if(eventos!=null){
                    if (eventos.getCodigo()==200){

                        binding.recyclerAutoriza.removeAllViews();
                        adapter.edit().removeAll().commit();

                        adapter.edit().replaceAll(eventos.getNotificaciones()).commit();
                        adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                        adapter.notifyDataSetChanged();
                        eventos.getNotificaciones().clear();
                        binding.prog.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }


    public String getSemana(Date date){
        //date = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, month);
        now.set(Calendar.DATE, day);
        return  String.valueOf(now.get(Calendar.WEEK_OF_YEAR));
    }

    private static final Comparator<Notificaciones.Notificacione> ALPHABETICAL_COMPARATOR = new Comparator<Notificaciones.Notificacione>() {
        @Override
        public int compare(Notificaciones.Notificacione a, Notificaciones.Notificacione b) {
            return 0;
        }
    };

}
