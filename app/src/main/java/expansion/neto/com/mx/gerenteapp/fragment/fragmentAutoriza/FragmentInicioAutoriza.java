package expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;

/**
 *
 */
public class FragmentInicioAutoriza extends AppCompatActivity {

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
        //setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        back = (LinearLayout) findViewById(R.id.back);

        titulo = (TextView) findViewById(R.id.nombre_titulo);

        titulo.setText(getString(R.string.porAutorizar));

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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentCardAutoriza(), getString(R.string.md));
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



}
// extends AppCompatActivity {
//
//    private static ViewPager mPager;
//    private TabLayout mTabLayout;
//    SharedPreferences sharedPreferences;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.index_layout, container, false);
//
//        mPager = (ViewPager) view.findViewById(R.id.pager);
//        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
//
//        mPager.setAdapter(new TabsAdapter(getChildFragmentManager()));
//        mTabLayout.setupWithViewPager(mPager);
//        setHasOptionsMenu(true);
//
//        return view;
//    }
//
//    class TabsAdapter extends FragmentPagerAdapter {
//        public TabsAdapter(FragmentManager fm) {
//            super(fm);
//        }
//        @Override
//        public int getCount() {
//            return 2;
//        }
//        @Override
//        public Fragment getItem(int i) {
//
//            switch (i) {
//                case 0:
//                    return new FragmentCardAutoriza();
//                case 1:
//                    return new FragmentCardAutoriza();
//               /* case 2:
//                    return new TabGeoCone();
//                case 3:
//                    return new TabGeoCone();
//                case 4:
//                    return new TabGeoCone();*/
//            }
//
//            return null;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "";
//                case 1:
//                    return getString(R.string.porAutorizar);
//               /* case 2:
//                    return "3";
//                case 3:
//                    return "4";
//                case 4:
//                    return "5";*/
//            }
//            return "";
//        }
//    }
//
//
//    class TabsAdapterNacional extends FragmentPagerAdapter {
//
//        public TabsAdapterNacional(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public int getCount() {
//            return 1;
//        }
//
//        @Override
//        public Fragment getItem(int i) {
//
//            switch (i) {
//                case 0:
//                    return new FragmentCardAutoriza();
//            }
//
//            return null;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "";
//            }
//            return "";
//        }
//    }
//
//
//}
