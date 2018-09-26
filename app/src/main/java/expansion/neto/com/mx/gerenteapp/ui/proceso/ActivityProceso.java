package expansion.neto.com.mx.gerenteapp.ui.proceso;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityProcesoBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentChat;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentDialogCancelarMdProceso;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentTiempos;

public class ActivityProceso extends AppCompatActivity {
    private ActivityProcesoBinding binding;
    private ActivityProcesoAdapter adapter;
    private static final int PANTALLA_EN_PROCESO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_proceso);
        setSupportActionBar(binding.toolbarOpciones);

        binding.toolbar.nombreTitulo.setText(getString(R.string.enProceso));
        binding.toolbar.guardar.setVisibility(View.GONE);

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentDialogCancelarMdProceso dFragment = new FragmentDialogCancelarMdProceso();
                dFragment.show(fm, "Dialog Fragment");
            }
        });

        TabLayout tabLayout = binding.tabLayout;
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.enProcesoMenuTiempos)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.enProcesoMenuChat)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        binding.anterior.setVisibility(View.INVISIBLE);
        adapter = new ActivityProcesoAdapter(getSupportFragmentManager());
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

    public static class ActivityProcesoAdapter extends FragmentStatePagerAdapter {
        private static int NUM_ITEMS = 2;

        public ActivityProcesoAdapter(FragmentManager fragmentManager) {
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
                case 0:
                    return FragmentTiempos.newInstance(PANTALLA_EN_PROCESO);
                case 1:
                    return FragmentChat.newInstance();
                default:
                    return null;
            }
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentDialogCancelarMdProceso dFragment = new FragmentDialogCancelarMdProceso();
            dFragment.show(fm, "Dialog Fragment");
        }
    }
}
