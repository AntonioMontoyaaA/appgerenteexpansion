package expansion.neto.com.mx.gerenteapp.ui.rechazadas;

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
import expansion.neto.com.mx.gerenteapp.databinding.ActivityRechazadasBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazadas.FragmentChatRechazadas;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazadas.FragmentDialogCancelarMdDocumentos;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazadas.FragmentTiemposRechazadas;

public class ActivityRechazadas extends AppCompatActivity {

    private ActivityRechazadasBinding binding;
    private ActivityRechazadasAdapter adapter;
    private static final int PANTALLA_RECHAZADAS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rechazadas);
        setSupportActionBar(binding.toolbarOpciones);

        binding.toolbar.nombreTitulo.setText(getString(R.string.rechazadas));
        binding.toolbar.guardar.setVisibility(View.GONE);

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentDialogCancelarMdDocumentos dFragment = new FragmentDialogCancelarMdDocumentos();
                dFragment.show(fm, "Dialog Fragment");
            }
        });

        TabLayout tabLayout = binding.tabLayout;
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.enProcesoMenuTiempos)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.enProcesoMenuChat)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        binding.anterior.setVisibility(View.INVISIBLE);
        adapter = new ActivityRechazadasAdapter(getSupportFragmentManager());
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

    public static class ActivityRechazadasAdapter extends FragmentStatePagerAdapter {

        private static int NUM_ITEMS = 2;

        public ActivityRechazadasAdapter(FragmentManager fragmentManager) {
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
                    return FragmentTiemposRechazadas.newInstance(PANTALLA_RECHAZADAS);
                case 1:
                    return FragmentChatRechazadas.newInstance(PANTALLA_RECHAZADAS);
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
            FragmentDialogCancelarMdDocumentos dFragment = new FragmentDialogCancelarMdDocumentos();
            dFragment.show(fm, "Dialog Fragment");
        }
    }

}
