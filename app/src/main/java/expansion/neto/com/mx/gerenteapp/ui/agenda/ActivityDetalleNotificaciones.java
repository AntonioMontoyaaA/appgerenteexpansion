package expansion.neto.com.mx.gerenteapp.ui.agenda;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityAutorizaBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FragmentDialogMostrarTip;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentDetalle.FragmentDetalle;
import expansion.neto.com.mx.gerenteapp.modelView.Tips;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderConsultaTip;
import expansion.neto.com.mx.gerenteapp.ui.detalle.ActivityDetalle;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class ActivityDetalleNotificaciones extends AppCompatActivity {

    private ActivityAutorizaBinding binding;
    private PageAdapter adapter;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initDataBinding();
        currentItem = 0;

        binding.anterior.setVisibility(View.INVISIBLE);

        adapter = new PageAdapter(getSupportFragmentManager());
        binding.pager.setAdapter(adapter);
        binding.pager.setCurrentItem(currentItem);
        setNavigator();

        binding.help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTip("1");
            }
        });
        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int position) {


                //Log.e("******", "swipe "+position);

                // TODO Auto-generated method stub
                if (binding.pager.getCurrentItem() == 0) {
                    binding.anterior.setVisibility(View.INVISIBLE);

                    //Log.e("******", "INVISIBLE "+position);


                } else {

                    binding.anterior.setVisibility(View.VISIBLE);
                    //Log.e("******", "VISIBLE "+position);


                }

                if (binding.pager.getCurrentItem() == (binding.pager.getAdapter().getCount() - 1)) {

                    //Log.e("******", "FINISH "+position);

                    binding.siguiente.setText("FINISH");
                } else {
                   // Log.e("******", "NEXT "+position);
                    binding.siguiente.setText("NEXT");
                }
                setNavigator();
            }
        });

        binding.anterior.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (binding.pager.getCurrentItem() != 0) {
                    binding.pager.setCurrentItem(binding.pager.getCurrentItem() - 1);
                }
                setNavigator();
            }
        });

        binding.siguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (binding.pager.getCurrentItem() != (binding.pager.getAdapter().getCount() - 1)) {
                    binding.pager.setCurrentItem(binding.pager.getCurrentItem() + 1);
                } else {
                    Toast.makeText(ActivityDetalleNotificaciones.this, "Finish",
                            Toast.LENGTH_SHORT).show();
                }
                setNavigator();
            }
        });

    }

    public void setNavigator() {
        String navigation = "";
        for (int i = 0; i < adapter.getCount(); i++) {
            if (i == binding.pager.getCurrentItem()) {
                navigation += getString(R.string.material_icon_point_full)
                        + "      ";
            } else {
                navigation += getString(R.string.material_icon_point_empty)
                        + "      ";
            }
        }
        binding.circuloPosicion.setText(navigation);
    }

    public void setCurrentSlidePosition(int position) {
        this.currentItem = position;
    }

    public int getCurrentSlidePosition() {
        return this.currentItem;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                return FragmentDetalle.newInstance(position,true);
            } else if (position == 1) {
                return FragmentDetalle.newInstance(position,true);
            } else {
                return FragmentDetalle.newInstance(position,true);
             }
        }
    }

    /**
     * método que setea la vista con el binding
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_autoriza);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            Intent main = new Intent(getApplicationContext(), ActivityNotificaciones.class);
            startActivity(main);
        }
    }


    ArrayList<Tips.Tip> tips;
    public void mostrarTip(String pantalla){
        ProviderConsultaTip.getInstance(ActivityDetalleNotificaciones.this).obtenerTips(pantalla, new ProviderConsultaTip.ConsultaTips() {
            @Override
            public void resolve(Tips tip) {
                if(tip.getCodigo()==200){
                    tips = new ArrayList<>();

                    if(tip.getTips().size()>0){
                        for(int i=0;i<tip.getTips().size();i++){
                            tips.add(tip.getTips().get(i));
                        }

                        SharedPreferences preferences;
                        preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(tips);
                        editor.putString("tips", json);
                        editor.apply();

                        FragmentManager fm = getSupportFragmentManager();
                        FragmentDialogMostrarTip dFragment = new FragmentDialogMostrarTip();
                        dFragment.show(fm, "Dialog Fragment");

                    }else{
                        Toast.makeText(ActivityDetalleNotificaciones.this, "Aún no se agrega tip para esta opción",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ActivityDetalleNotificaciones.this, tip.getMensaje(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

}
