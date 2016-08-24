package com.uaq.HUMUI.Activities.retos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.uaq.HUMUI.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ActividadPrincipal extends AppCompatActivity {

    public ViewPager mViewPager;
    String idUser;
    public SectionsPagerAdapter adapter;
    private Timer timer;
    ProgressDialog progressDialog;
    Actualizar actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_principal);
        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();


        if(extras.containsKey("idUser")) {
            idUser = getIntent().getExtras().getString("idUser");
        }

        setToolbar();
        //Actualizar
        timer = new Timer();
        actualizar = new Actualizar();
        timer.scheduleAtFixedRate(actualizar, 1000, 5000);

        // Setear adaptador al viewpager.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(mViewPager);

        progressDialog = new ProgressDialog(ActividadPrincipal.this);
        progressDialog.setMessage("Cargando Retos...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }


    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Retos");
        setSupportActionBar(toolbar);
    }

    public void actualizaAdapter(){
            setupViewPager(mViewPager);

    }

    public void setupViewPager(ViewPager viewPager) {
        adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(GridFragment.newInstance(1, idUser), "Retos");
        adapter.addFragment(GridFragment.newInstance(2, idUser), "Retos Inscrito");
        adapter.notifyDataSetChanged();

        int pagina = viewPager.getCurrentItem();
            viewPager.setAdapter(null);
            viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(pagina);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
        timer.purge();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        timer.purge();
    }
    class Actualizar extends TimerTask {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                public void run() {
                    actualizaAdapter();
                    if(progressDialog.isShowing()) {
                        progressDialog.cancel();
                    }
                }
            });
        }
    }


}