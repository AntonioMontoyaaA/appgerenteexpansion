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
import android.widget.Toast;


import com.android.calendarlibrary.CollapseCalendarView;
import com.android.calendarlibrary.manager.CalendarManager;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.ConsultaEvento;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderObtieneEventos;
import expansion.neto.com.mx.gerenteapp.sorted.agenda.AdapterAgendaEventos;
import expansion.neto.com.mx.gerenteapp.sorted.agenda.AgendaEventosHolder;
import expansion.neto.com.mx.gerenteapp.utils.Util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAgendaPendientesBinding;

public class FragmentAgendaPendientes extends Fragment {

    private FragmentAgendaPendientesBinding binding;
    AdapterAgendaEventos adapter;

    AgendaEventosHolder.Listener listener = new AgendaEventosHolder.Listener() {
        @Override
        public void onAgendaSelect(ConsultaEvento.Agenda model) {

        }
    };
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agenda_pendientes,container,false);
        View view = binding.getRoot();
        Date date = new Date();
        binding.nombreSemana.setText("Semana "+getSemana(date));

        adapter = new AdapterAgendaEventos(getContext(), ALPHABETICAL_COMPARATOR, listener);
        binding.recyclerAutoriza.setHasFixedSize(true);
        binding.recyclerAutoriza.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerAutoriza.setAdapter(adapter);

        Date hoy = Calendar.getInstance().getTime();
        binding.fecha.setText(Util.getFechaFormat(hoy));
        String upperString = Util.getFechaDay(hoy).substring(0,1).toUpperCase() + Util.getFechaDay(hoy).substring(1);
        binding.dia.setText(upperString);
        getEventos();
        initView();


        binding.calendar.setDateSelectListener(new CollapseCalendarView.OnDateSelect() {

            @Override
            public void onDateSelected(LocalDate dates) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = fmt.parse(dates.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                binding.nombreSemana.setText("Semana "+getSemana(date));

                binding.fecha.setText(Util.getFechaFormat(date));
                String upperString = Util.getFechaDay(date).substring(0,1).toUpperCase() + Util.getFechaDay(date).substring(1);
                binding.dia.setText(upperString);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String format = simpleDateFormat.format(date);

                SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                String usuarioId = preferences.getString("usuario", "");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("fechaSeleccionada", format);
                editor.apply();

                adapter.notifyDataSetChanged();
                ProviderObtieneEventos.getInstance(getContext()).obtenerEventos(usuarioId, format, "1", "1", new ProviderObtieneEventos.InterfaceObtieneEventos() {
                    @Override
                    public void resolve(ConsultaEvento eventos) {
                        if(eventos!=null){
                            ArrayList<String> participantes;
                            if (eventos.getCodigo()==200){
                                participantes = new ArrayList<>();
                                binding.recyclerAutoriza.removeAllViews();
                                adapter.edit().removeAll().commit();

                                adapter.edit().replaceAll(eventos.getAgenda()).commit();
                                adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                                adapter.notifyDataSetChanged();
                                eventos.getAgenda().clear();
                                binding.prog.setVisibility(View.GONE);


//                                for(int i = 0;i<eventos.getAgenda().size();i++){
//                                    for(int j=0;j<eventos.getAgenda().get(i).getListaUsuariosAsignados().size();j++){
//                                        participantes.set(j, eventos.getAgenda().get(i).getListaUsuariosAsignados().get(j).getNombre());
//                                    }
//                                }
//
//                                binding.setPar(participantes);


                            }else{

                                binding.recyclerAutoriza.removeAllViews();
                                adapter.edit().removeAll().commit();
                                adapter.notifyDataSetChanged();

                                binding.prog.setVisibility(View.GONE);

                                Toast.makeText(getContext(), eventos.getMensaje(),
                                        Toast.LENGTH_SHORT).show();

                                binding.prog.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void reject(Exception e) {

                    }
                });
            }
        });

        return view;
    }

    private CalendarManager mManager;

    private void initView() {
        mManager = new CalendarManager(LocalDate.now(),
                CalendarManager.State.MONTH,
                LocalDate.now().withYear(100),
                LocalDate.now().plusYears(100));
        binding.calendar.init(mManager);
        binding.calendar.showChinaDay(false);
        mManager.toggleView();
        binding.calendar.populateLayout();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void getEventos(){

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String format = df.format(c);

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuarioId = preferences.getString("usuario", "");
        ProviderObtieneEventos.getInstance(getContext()).obtenerEventos(usuarioId, format, "1","1",  new ProviderObtieneEventos.InterfaceObtieneEventos() {
            @Override
            public void resolve(ConsultaEvento eventos) {
                if(eventos!=null){
                    if (eventos.getCodigo()==200){

                        binding.recyclerAutoriza.removeAllViews();
                        adapter.edit().removeAll().commit();

                        adapter.edit().replaceAll(eventos.getAgenda()).commit();
                        adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                        adapter.notifyDataSetChanged();
                        eventos.getAgenda().clear();
                        binding.prog.setVisibility(View.GONE);
                    }else{

                        binding.recyclerAutoriza.removeAllViews();
                        adapter.edit().removeAll().commit();
                        adapter.notifyDataSetChanged();

                        binding.prog.setVisibility(View.GONE);

                        Toast.makeText(getContext(), eventos.getMensaje(),
                                Toast.LENGTH_SHORT).show();

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

    private static final Comparator<ConsultaEvento.Agenda> ALPHABETICAL_COMPARATOR = new Comparator<ConsultaEvento.Agenda>() {
        @Override
        public int compare(ConsultaEvento.Agenda a, ConsultaEvento.Agenda b) {
            return 0;
        }
    };

}
