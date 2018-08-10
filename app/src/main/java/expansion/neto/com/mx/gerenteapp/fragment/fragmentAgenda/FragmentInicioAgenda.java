package expansion.neto.com.mx.gerenteapp.fragment.fragmentAgenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.ui.agenda.ActivityAddEvento;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;
import expansion.neto.com.mx.gerenteapp.utils.Util;

/**
 *
 */
public class FragmentInicioAgenda extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout back;
    private TextView titulo;
    private LinearLayout guardar;
    private ImageView agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_layout_agenda);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        back = (LinearLayout) findViewById(R.id.back);
        guardar = (LinearLayout) findViewById(R.id.guardar);

        guardar.setVisibility(View.VISIBLE);
        agregar = (ImageView) findViewById(R.id.agregar);
        titulo = (TextView) findViewById(R.id.nombre_titulo);

        titulo.setText(getString(R.string.agendas));
        agregar.setBackgroundResource(0);
        agregar.setImageResource(R.drawable.ic_add);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), ActivityMain.class);
                startActivity(main);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), ActivityAddEvento.class);
                startActivity(main);
            }
        });

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentAgendaPendientes(), getString(R.string.pendientes));
        adapter.addFragment(new FragmentAgendaNotificaciones(), getString(R.string.notificaciones));
        adapter.addFragment(new FragmentAgendaPorVencer(), getString(R.string.porvencer));
        //adapter.addFragment(new FragmentCardAutoriza(), getString(R.string.area));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {


        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            Intent main = new Intent(getApplicationContext(), ActivityMain.class);
            startActivity(main);
        }
    }

    FragmentAgendaPendientes fragmentAgendaPendientes;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(fragmentAgendaPendientes instanceof Util.IOnFocusListenable) {
            ((Util.IOnFocusListenable) fragmentAgendaPendientes).onWindowFocusChanged(hasFocus);
        }
    }


    public interface IOnFocusListenable {
        public void onWindowFocusChanged(boolean hasFocus);
    }
}
