package expansion.neto.com.mx.gerenteapp.ui.rechazo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityRechazoBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentChat;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentInicioProceso;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentTiempos;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazo.FragmentInicioRechazadas;
import expansion.neto.com.mx.gerenteapp.ui.proceso.ActivityProceso;

public class ActivityRechazo extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;

    private ActivityRechazoBinding binding;
    private ActivityRechazo.ActivityRechazoAdapter adapter;
    private int currentItem;

    private final int BACK_RECHAZO = 2;
    private static final int PANTALLA_RECHAZADAS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rechazo);
        setSupportActionBar(binding.toolbarOpciones);

        binding.toolbar.nombreTitulo.setText(getString(R.string.mdsRechazadas));

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), FragmentInicioRechazadas.class);
                startActivity(main);
            }
        });

        SharedPreferences preferences;
        preferences = getApplicationContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("TIPO_BACK", BACK_RECHAZO);
        editor.apply();

        TabLayout tabLayout = binding.tabLayout;
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.enProcesoMenuTiempos)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.enProcesoMenuChat)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        currentItem = 0;
        binding.anterior.setVisibility(View.INVISIBLE);
        adapter = new ActivityRechazo.ActivityRechazoAdapter(getSupportFragmentManager());
        binding.pager.setAdapter(adapter);

        binding.pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public static class ActivityRechazoAdapter extends FragmentStatePagerAdapter {
        private static int NUM_ITEMS = 2;

        public ActivityRechazoAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FragmentTiempos.newInstance(PANTALLA_RECHAZADAS);
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return FragmentChat.newInstance(PANTALLA_RECHAZADAS);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(getApplicationContext(), FragmentInicioRechazadas.class);
        startActivity(main);
    }
}
