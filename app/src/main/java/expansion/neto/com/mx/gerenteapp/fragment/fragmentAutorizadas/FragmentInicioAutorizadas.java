package expansion.neto.com.mx.gerenteapp.fragment.fragmentAutorizadas;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazo.FragmentCardRechazadas;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazo.FragmentInicioRechazadas;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;

public class FragmentInicioAutorizadas extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout back;
    private TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        viewPager = (ViewPager) findViewById(R.id.pager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        back = (LinearLayout) findViewById(R.id.back);

        titulo = (TextView) findViewById(R.id.nombre_titulo);

        titulo.setText(getString(R.string.mdsAutorizadas));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), ActivityMain.class);
                startActivity(main);
            }
        });

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @Override
    public void onBackPressed() {
        Intent main = new Intent(getApplicationContext(), ActivityMain.class);
        startActivity(main);
    }

}
