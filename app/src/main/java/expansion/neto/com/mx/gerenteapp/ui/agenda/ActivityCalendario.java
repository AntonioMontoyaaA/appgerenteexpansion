package expansion.neto.com.mx.gerenteapp.ui.agenda;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityCalendarBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FragmentAgregarTarea;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Agenda;
import expansion.neto.com.mx.gerenteapp.sorted.agenda.AdapterAgenda;
import expansion.neto.com.mx.gerenteapp.sorted.agenda.AgendaHolder;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;
import io.github.yavski.fabspeeddial.FabSpeedDial;

public class ActivityCalendario extends AppCompatActivity
        implements AgendaHolder.Listener{

    Intent main;
    private ActivityCalendarBinding binding;
    SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initDataBinding();

        listaAgenda(binding);

        binding.fechas.setText(dateFormatForMonth.format(binding.compactcalendarView.getFirstDayOfCurrentMonth()).toUpperCase());
        binding.compactcalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                binding.fechas.setText(dateFormatForMonth.format(dateClicked).toUpperCase());
            }

            @Override
            public void onMonthScroll(Date diaDelMes) {
                binding.fechas.setText(dateFormatForMonth.format(diaDelMes).toUpperCase());
            }
        });

        binding.fabButton.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.add_tarea:
                        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
                        FragmentAgregarTarea dialog = new FragmentAgregarTarea();
                        dialog.show(fm, "dialog_fragment");
                        return true;
                    default:
                        return false;
                }
            }
            @Override
            public void onMenuClosed() { }
        });

    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_perm_contact_calendar_black_24dp);
        binding.toolbar.toolbar.setOverflowIcon(drawable);
        setSupportActionBar(binding.toolbar.toolbar);
        binding.toolbar.semana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main = new Intent(ActivityCalendario.this, ActivityCalendarioApi.class);
                ActivityCalendario.this.startActivity(main);
                ActivityCalendario.this.finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent main;
        main = new Intent(ActivityCalendario.this, ActivityMain.class);
        ActivityCalendario.this.startActivity(main);
        ActivityCalendario.this.finish();
    }

    private ArrayList<Agenda> getList(){
        ArrayList<Agenda> list = new ArrayList<>();
        list.add(new Agenda("1","Prospecto general en las áreas","Prospecto general en las áreas","Alcance: 75%","Meta a alcanzar $1, 000, 000", "Tarea 1"));
        list.add(new Agenda("1","Mercadotecnía","Mercadotecnía","Alcance: 75%","Meta a alcanzar $100, 000", "Tarea 2"));
        list.add(new Agenda("1","Mercadotecnía","Mercadotecnía","Alcance: 75%","Meta a alcanzar $100, 000", "Tarea 3"));
        return list;
    }


    public void listaAgenda(ActivityCalendarBinding binding){
        binding.recyclerAgenda.setHasFixedSize(true);
        AdapterAgenda adapter = new AdapterAgenda(this, ALPHABETICAL_COMPARATOR, this);
        binding.recyclerAgenda.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerAgenda.setAdapter(adapter);
        adapter.edit().replaceAll(getList()).commit();
        adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
    }

    private static final Comparator<Agenda> ALPHABETICAL_COMPARATOR = new Comparator<Agenda>() {
        @Override
        public int compare(Agenda a, Agenda b) {
            return a.nombreCreacionMemoriaDescriptiva.compareTo(b.nombreCreacionMemoriaDescriptiva);
        }
    };

    Long time = null;

    @Override
    public void onAgendaSelect(Agenda model) {
        Date tradeDate = null;
        try {
            tradeDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).parse("20180404");
            time = tradeDate.getTime();
            Event ev1 = new Event(getResources().getColor(R.color.azulclaro),  time, "");
            binding.compactcalendarView.addEvent(ev1);
            binding.compactcalendarView.showCalendarWithAnimation();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

