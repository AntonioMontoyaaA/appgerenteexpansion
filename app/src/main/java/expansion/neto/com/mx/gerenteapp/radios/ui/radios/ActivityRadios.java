package expansion.neto.com.mx.gerenteapp.radios.ui.radios;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityRadiosBinding;
import expansion.neto.com.mx.gerenteapp.radios.fragment.radios.FragmentAceptar;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.DetalleRadio;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.Radios;
import expansion.neto.com.mx.gerenteapp.radios.provider.radiosProvider.ProvaiderDatosRadios;
import expansion.neto.com.mx.gerenteapp.radios.sorted.radios.adapter.AdapterRadios;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;

import static expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion.FragmentAutoriza.loadingProgress;

public class ActivityRadios extends AppCompatActivity {

    private ActivityRadiosBinding binding;
    ProgressDialog progressDialog;
    AdapterRadios adpt = null;
    List<DetalleRadio> detalleRadio = null;
    //List<RadioLista> detalleRadio = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        initDataBinding();
        progressDialog = new ProgressDialog(ActivityRadios.this);
        getData();

        binding.buscar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("DefaultLocale")
            @Override
            public void afterTextChanged(Editable editable) {

                String texto = binding.buscar.getText().toString();
                List<DetalleRadio> listaTemporal = new ArrayList<DetalleRadio>();
                //List<RadioLista> listaTemporal = new ArrayList<RadioLista>();

                binding.rcRadios.removeAllViews();

                if (texto.equals("")) {
                    getData();
                } else {
                    for(DetalleRadio nombreRadio : detalleRadio) {
                        if(nombreRadio.getNombreRadio().toLowerCase().contains(texto.toLowerCase()) ) {
                            listaTemporal.add(nombreRadio);
                            adpt = new AdapterRadios(ActivityRadios.this, listaTemporal);
                            binding.rcRadios.setLayoutManager(new LinearLayoutManager(ActivityRadios.this));
                            //dRecylerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
                            binding.rcRadios.setAdapter(adpt);
                        }
                    }
                    /*for(RadioLista nombreRadio : detalleRadio) {
                        if(nombreRadio.getNombre().toLowerCase().contains(texto.toLowerCase()) ) {
                            listaTemporal.add(nombreRadio);
                            adpt = new AdapterRadios(ActivityRadios.this, listaTemporal);
                            binding.rcRadios.setLayoutManager(new LinearLayoutManager(ActivityRadios.this));
                            //dRecylerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
                            binding.rcRadios.setAdapter(adpt);
                        }
                    }*/
                }

            }
        });
        binding.btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent main = new Intent(getApplicationContext(), ActivityMain.class);
                startActivity(main);
            }
        });

    }

    /* Método que setea la vista con el binding */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_radios);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getData(){
        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");
        loadingProgress(progressDialog, 0);

        ProvaiderDatosRadios.getInstance(this).obtenerDatosRadios(usuario, new ProvaiderDatosRadios.ConsultaDatosRadios() {
            @Override
            public void resolve(Radios datosSitio) {
                if(datosSitio!=null){
                    System.out.println( "datosSitio codigo " + datosSitio.getCodigo() + "datosSitio mensaje " + datosSitio.getMensaje() );
                    if(datosSitio.getCodigo()==200){
                        loadingProgress(progressDialog, 1);

                        /*setRadiosAdapter(datosSitio.getRadiosLista());
                        int tamano = datosSitio.getRadiosLista().size();
                        if(tamano >0 ){
                            detalleRadio = datosSitio.getRadiosLista();*/
                        setRadiosAdapter(datosSitio.getDetalleRadios());
                        int tamano = datosSitio.getDetalleRadios().size();
                        if(tamano >0 ){
                            detalleRadio = datosSitio.getDetalleRadios();
                        }else {
                            FragmentAceptar a = new FragmentAceptar();
                            a.setMensaje("sin_radios_asignados");
                            a.show(getSupportFragmentManager(),"child");
                        }
                    }else{
                        loadingProgress(progressDialog, 1);
                        FragmentAceptar a = new FragmentAceptar();
                        a.setMensaje("sin_radios_asignados");
                        a.show(getSupportFragmentManager(),"child");
                    }
                }else{
                    loadingProgress(progressDialog, 1);
                    FragmentAceptar a = new FragmentAceptar();
                    a.setMensaje("sin_radios_asignados");
                    a.show(getSupportFragmentManager(),"child");
                }
            }

            @Override
            public void reject(Exception e) {
                loadingProgress(progressDialog, 1);
            }
        });


    }


    /*public void setRadiosAdapter(List<RadioLista> listItems){
        adpt = new AdapterRadios(this, listItems);
        binding.rcRadios.setLayoutManager(new LinearLayoutManager(this));
        //dRecylerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        binding.rcRadios.setAdapter(adpt);
    }*/
    public void setRadiosAdapter(List<DetalleRadio> listItems){
        adpt = new AdapterRadios(this, listItems);
        binding.rcRadios.setLayoutManager(new LinearLayoutManager(this));
        //dRecylerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        binding.rcRadios.setAdapter(adpt);
    }

}
