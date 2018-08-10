package expansion.neto.com.mx.gerenteapp.ui.autoriza;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AutorizaModel;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.UsuarioLogin;
import expansion.neto.com.mx.gerenteapp.provider.dashboardProvider.ExpansionGerenteProviderAutoriza;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AdapterAutoriza;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityAutorizaListBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autoriza;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AutorizaHolder;
import expansion.neto.com.mx.gerenteapp.utils.Util;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class ActivityListaAutoriza extends AppCompatActivity
        implements AutorizaHolder.Listener{



    private ActivityAutorizaListBinding binding;
    UsuarioLogin.Perfil perfil;
    AdapterAutoriza adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        initDataBinding();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getListaAutoriza(perfil);

//        binding.recyclerAutoriza.setHasFixedSize(true);
//        adapter = new AdapterAutoriza(this,ALPHABETICAL_COMPARATOR, this);
//        binding.recyclerAutoriza.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        binding.recyclerAutoriza.setAdapter(adapter);
    }

    private static final Comparator<Autorizadas> ALPHABETICAL_COMPARATOR = new Comparator<Autorizadas>() {
        @Override
        public int compare(Autorizadas a, Autorizadas b) {
            return a.getCodigo().compareTo(b.getCodigo());
        }
    };

    /**
     * Método que setea la vista con el binding
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_autoriza_list);
    }

    public void getListaAutoriza(UsuarioLogin.Perfil perfil){

        //binding.prog.setVisibility(View.VISIBLE);

        ExpansionGerenteProviderAutoriza.getInstance(this).compruebaAutoriza(new ExpansionGerenteProviderAutoriza.ConsultaAutoriza() {
            @Override
            public void resolve(ArrayList<Autoriza> autoriza) {
                if(autoriza!=null){
//                    adapter.edit().replaceAll(autoriza.get(0).getAutoriza()).commit();
//                    adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
//                    binding.prog.setVisibility(View.GONE);
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    @Override
    public void onAutorizaSelect(Autorizadas.Memoria model) {
        Intent main = new Intent(ActivityListaAutoriza.this, ActivityAutorizar.class);
        ActivityListaAutoriza.this.startActivity(main);
        ActivityListaAutoriza.this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

//    private void blockUI(){
//        Util.addProgressBar(getApplicationContext(), binding.l,binding.l.getChildCount()-1 );
//    }
//
//    private void unblockUI(){
//        binding.l.removeViewAt(binding.lress.getChildCount()-2);
//    }


}
