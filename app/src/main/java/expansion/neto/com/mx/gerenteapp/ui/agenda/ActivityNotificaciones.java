package expansion.neto.com.mx.gerenteapp.ui.agenda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentChatNotificaciones;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.chat.FragmentEstatusChatNotificaciones;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.GuardarNotificacion;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderNotificacionVisto;
import expansion.neto.com.mx.gerenteapp.sorted.agenda.AdapterAgendaNotificaciones;
import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAgendaNotificacionesBinding;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Notificaciones;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderObtieneNotificaciones;
import expansion.neto.com.mx.gerenteapp.sorted.agenda.AgendaEventosNotificacionesHolder;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;
import expansion.neto.com.mx.gerenteapp.utils.Util;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.TIPO_NOTIFICACION;

public class ActivityNotificaciones extends AppCompatActivity {

    private FragmentAgendaNotificacionesBinding binding;
    AdapterAgendaNotificaciones adapter;
    AgendaEventosNotificacionesHolder.Listener listener = new AgendaEventosNotificacionesHolder.Listener() {
        @Override
        public void onAgendaSelect(Notificaciones.Notificacione model) {
            goIntent(model.getTipoNotificacion(), model.getMdId(), model.getEstatus());
            notificacionVista(model);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initDataBinding();

        Date date = new Date();
        binding.nombreSemana.setText("Semana "+getSemana(date));

        adapter = new AdapterAgendaNotificaciones(this, ALPHABETICAL_COMPARATOR, listener);
        binding.recyclerAutoriza.setHasFixedSize(true);
        binding.recyclerAutoriza.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerAutoriza.setAdapter(adapter);

        Date hoy = Calendar.getInstance().getTime();
        binding.fecha.setText(Util.getFechaFormat(hoy));
        String upperString = Util.getFechaDay(hoy).substring(0,1).toUpperCase() + Util.getFechaDay(hoy).substring(1);
        binding.dia.setText(upperString);
        getNotificaciones();
        binding.toolbar.nombreTitulo.setText("Notificaciones");
        binding.toolbar.guardar.setVisibility(View.GONE);
        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), ActivityMain.class);
                startActivity(main);
            }
        });
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_agenda_notificaciones);
    }

    public void getNotificaciones(){
        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuarioId = preferences.getString("usuario", "");
        ProviderObtieneNotificaciones.getInstance(this).obtenerNotificaciones(usuarioId, TIPO_NOTIFICACION, new ProviderObtieneNotificaciones.InterfaceObtieneNotificaciones() {
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

    Bundle args = new Bundle();
    Intent main;
    public void goIntent(String tipoNotificacion, String mdId, String estatus){
        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Fragment navFragment = null;;
        switch (tipoNotificacion){
            case "1":
                editor.putString("mdIdterminar", mdId);
                editor.putString("estatusId", estatus);
                editor.putString("num", getString(R.string.zero));
                editor.putString("estatusNombre", getString(R.string.general));
                editor.apply();
//                navFragment = new FragmentEstatusChatNotificaciones();

                main = new Intent(getApplicationContext(), FragmentEstatusChatNotificaciones.class);
                startActivity(main);

                ///args.putInt("index", 1);
                //   navFragment.setArguments(args);
                break;
            case "2":
                editor.putString("mdIdterminar", mdId);
                editor.apply();
                main = new Intent(getApplicationContext(), ActivityDetalleNotificaciones.class);
                startActivity(main);
                break;
            case "3":
                editor.putString("mdIdterminar", mdId);
                editor.apply();
                main = new Intent(getApplicationContext(), ActivityDetalleNotificaciones.class);
                startActivity(main);
                break;
            case "4":
                editor.putString("mdIdterminar", mdId);
                editor.putString("estatusId", estatus);
                editor.putString("num", getString(R.string.zero));
                editor.putString("estatusNombre", getString(R.string.genera));
                editor.apply();
//                navFragment = new FragmentEstatusChatNotificaciones();
//                args = new Bundle();
//                args.putInt("index", 1);
//                navFragment.setArguments(args);

                main = new Intent(getApplicationContext(), FragmentEstatusChatNotificaciones.class);
                startActivity(main);
                break;
            default :
                break;
        }

        if (navFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            try {
                transaction.replace(R.id.content_frame, navFragment).commit();
                binding.frame.setVisibility(View.VISIBLE);
                editor.putString("mdIdterminar", mdId);
                editor.apply();
                noti = false;

            } catch (IllegalStateException ignored) {

            }
        }
    }
    boolean noti = true;
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            if(noti){
                Intent main = new Intent(getApplicationContext(), ActivityMain.class);
                startActivity(main);
            }else{
                Intent main = new Intent(this, ActivityNotificaciones.class);
                startActivity(main);
            }
        }
    }

    public void notificacionVista(Notificaciones.Notificacione datosNotificacion){
        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario","");


        GuardarNotificacion notificacion = new GuardarNotificacion(
                usuario,
                datosNotificacion.getTipoNotificacion(),
                datosNotificacion.getMdId(),
                datosNotificacion.getFechaRegistro(),
                datosNotificacion.getNivelEstatusAreaId()
        );

        ProviderNotificacionVisto.getInstance(this).guardarNotificacion(notificacion, new ProviderNotificacionVisto.InterfaceNotificacionVista() {
            @Override
            public void resolve(Codigos codigo) {
                if(codigo!=null){

                }
            }
            @Override
            public void reject(Exception e) {

            }
        });

    }

}
