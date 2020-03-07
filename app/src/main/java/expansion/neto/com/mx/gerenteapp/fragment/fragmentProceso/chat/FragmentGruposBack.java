package expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.chat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentGruposBackBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentInicioProceso;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazadas.FragmentInicioRechazadas;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatNumMensajes;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.chat.ProviderNumMensajes;
import expansion.neto.com.mx.gerenteapp.sorted.chat.AdapterNumMensajes;
import expansion.neto.com.mx.gerenteapp.sorted.chat.NumMensajesHolder;
import expansion.neto.com.mx.gerenteapp.sorted.rechazadas.ActivityDetalleModifica;

import static expansion.neto.com.mx.gerenteapp.fragment.fragmentDetalle.FragmentDetalleRechazadas.loadingProgress;
import static expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.chat.FragmentGrupos.leerMensajes;


public class FragmentGruposBack extends Fragment implements NumMensajesHolder.Listener{

    SharedPreferences preferences = null;
    private View view = null;

    private final String CATEGORIA_A = "A";
    private final String CATEGORIA_B = "B";
    private final String CATEGORIA_C = "C";

    private final int AREA_EXPANSION = 1;
    private final int AREA_GESTORIA = 2;
    private final int AREA_CONSTRUCCION = 3;
    private final int AREA_OPERACIONES = 5;
    private final int AREA_AUDITORIA = 4;

    private final int PERFIL_GERENTE_EXPANSION = 3;
    private final int PERFIL_EXPANSION = 4;

    private static int tipoPantallas = 0;
    private static final int PANTALLA_EN_PROCESO = 1;
    private static final int PANTALLA_RECHAZADAS = 2;
    String mdId = "";
    String usuario = "";

    public static FragmentGruposBack newInstance(int tipoPantalla) {
        FragmentGruposBack fragmentTiempos = new FragmentGruposBack();
        Bundle args = new Bundle();
        fragmentTiempos.setArguments(args);
        tipoPantallas = tipoPantalla;
        return fragmentTiempos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    AdapterNumMensajes adapter;
    ArrayList<ChatNumMensajes.Comentario> comentarios;

    ProgressDialog progressDialog;
    FragmentGruposBackBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_grupos_back,container,false);
        view = binding.getRoot();
        binding.recyclerGrupos.setVisibility(View.VISIBLE);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        mdId = preferences.getString("mdIdterminar","");
        usuario = preferences.getString("usuario","");

        adapter = new AdapterNumMensajes(getContext(),ALPHABETICAL_COMPARATOR, this);
        binding.recyclerGrupos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerGrupos.setAdapter(adapter);

        progressDialog = new ProgressDialog(getContext());

        loadingProgress(progressDialog, 0);

        ProviderNumMensajes.getInstance(getContext()).
                obtenerNumMensajes(mdId, usuario, new ProviderNumMensajes.ConsultaNumMensajes() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void resolve(ChatNumMensajes tiempos) {
                if(tiempos!=null){
                    if(tiempos.getCodigo() == 200) {

                        comentarios = new ArrayList<>();

                        for(int i = 0;i<tiempos.getComentarios().size();i++){
                          //  if(tiempos.getComentarios().get(i).getEstatusId()!=2 ){
                                if(tiempos.getComentarios().get(i).getEstatusEvaluacion()!=0){
                                    comentarios.add(tiempos.getComentarios().get(i));
                                }
                          //  }
                        }

                        for(int j=0;j<comentarios.size();j++){
                            comentarios.get(j).setNumero(String.valueOf(j));
                        }

                        adapter.edit().replaceAll(comentarios).commit();
                        adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                        loadingProgress(progressDialog, 1);


                        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        int modifica = preferences.getInt("estatusIds",0);

                        if(modifica==4 || modifica==17){
                            binding.rechazo.setVisibility(View.VISIBLE);
                            binding.modifica.setVisibility(View.GONE);
                            ViewGroup.LayoutParams params = binding.recyclerGrupos.getLayoutParams();
                            final float scale = getContext().getResources().getDisplayMetrics().density;
                            int pixels = (int) (470 * scale + 0.5f);
                            params.height=pixels;
                            binding.recyclerGrupos.setLayoutParams(params);
                            binding.motivoRechazoGeneral.setText(tiempos.getMtvRechazo()+"");

                            binding.modifica.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Intent main = new Intent(getContext(), ActivityDetalleModifica.class);
                                    getContext().startActivity(main);
                                    getActivity().finish();

                                }
                            });



                        }else{
                            ViewGroup.LayoutParams params = binding.recyclerGrupos.getLayoutParams();
                            final float scale = getContext().getResources().getDisplayMetrics().density;
                            int pixels = (int) (540 * scale + 0.5f);
                            params.height=pixels;
                            binding.recyclerGrupos.setLayoutParams(params);
                            binding.rechazo.setVisibility(View.GONE);
                        }

                    } else {

                        loadingProgress(progressDialog, 1);

                        if(tipoPantallas == PANTALLA_RECHAZADAS) {
                            Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                            getContext().startActivity(main);
                        }else{
                            Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                            getContext().startActivity(main);
                        }
                        Toast.makeText(getContext(), tiempos.getMensaje(),
                                Toast.LENGTH_LONG).show();

                    }
                }else{
                    loadingProgress(progressDialog, 1);
                }
            }

            @Override
            public void reject(Exception e) { }
        });
        return view;
    }

    @Override
    public void onAutorizaSelect(ChatNumMensajes.Comentario model) {


        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("estatusId", String.valueOf(model.getEstatusId()));
        editor.putString("estatusNombre", model.getEstatus());
        editor.putString("num", model.getNumero());

        editor.apply();

        binding.recyclerGrupos.setVisibility(View.GONE);

        leerMensajes(getContext(), String.valueOf(model.getEstatusId()), mdId, usuario);

        FragmentEstatusChat fragment = new FragmentEstatusChat();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bodys, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private static final Comparator<ChatNumMensajes.Comentario> ALPHABETICAL_COMPARATOR = new Comparator<ChatNumMensajes.Comentario>() {
        @Override
        public int compare(ChatNumMensajes.Comentario a, ChatNumMensajes.Comentario b) {
            return 0;
        }
    };
}
