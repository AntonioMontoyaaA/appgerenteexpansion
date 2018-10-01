package expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazadas;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Comparator;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.fragment.fragmentDetalle.FragmentDetalle;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentTiempos;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.TiemposProceso;
import expansion.neto.com.mx.gerenteapp.sorted.tiempos.AdapterTiempos;
import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentTiemposBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentInicioProceso;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderTiemposProceso;
import expansion.neto.com.mx.gerenteapp.sorted.tiempos.TiemposHolder;
import expansion.neto.com.mx.gerenteapp.ui.detalle.ActivityDetalle;
import expansion.neto.com.mx.gerenteapp.ui.detalle.ActivityDetalleRechazadas;

import static expansion.neto.com.mx.gerenteapp.fragment.fragmentDetalle.FragmentDetalle.loadingProgress;


public class FragmentTiemposRechazadas extends Fragment implements TiemposHolder.Listener{

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


    public static FragmentTiemposRechazadas newInstance(int tipoPantalla) {
        FragmentTiemposRechazadas fragmentTiempos = new FragmentTiemposRechazadas();
        Bundle args = new Bundle();
        fragmentTiempos.setArguments(args);
        tipoPantallas = tipoPantalla;
        return fragmentTiempos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    List<TiemposProceso> listaTiempos = null;
    AdapterTiempos adapter;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final FragmentTiemposBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tiempos,container,false);
        view = binding.getRoot();

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdId = preferences.getString("mdIdterminar","");
        String usuario = preferences.getString("usuario","");

        adapter = new AdapterTiempos(getContext(),ALPHABETICAL_COMPARATOR, this);
        binding.recyclerTiempos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerTiempos.setAdapter(adapter);

        progressDialog = new ProgressDialog(getContext());

        loadingProgress(progressDialog, 0);

        ProviderTiemposProceso.getInstance(getContext()).obtenerTiemposProceso(mdId, usuario, new ProviderTiemposProceso.ConsultaTiemposProceso() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void resolve(TiemposProceso tiempos) {
                if(tiempos!=null){
                    if(tiempos.getCodigo() == 200) {

                        binding.nombreMd.setText(tiempos.getNomsitio()+"");
                        binding.setCategoria(tiempos.getCategoria()+"");
                        binding.puntuacion.setText(tiempos.getPuntajeTot()+"");
                        binding.categoria.setText(tiempos.getCategoria()+"");
                        binding.nombreCreo.setText(getString(R.string.creadael)+tiempos.getCreacion()+"");

                        adapter.edit().replaceAll(tiempos.getSeguimiento()).commit();
                        adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                        loadingProgress(progressDialog, 1);

                        for(int i=0;i<tiempos.getSeguimiento().size();i++){
                            if(tiempos.getSeguimiento().get(i).getEntiempo().equals("Atrasada")){
                                binding.colorStatus.setBackgroundColor(getResources().getColor(R.color.atrasadas));
                                binding.verdetalle.setBackgroundResource(R.drawable.estilo_boton_azul_atrasadas);
                            }
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
            public void reject(Exception e) {

            }
        });

        binding.verdetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), ActivityDetalleRechazadas.class);
                getContext().startActivity(main);
                getActivity().finish();

            }
        });


        return view;
    }

    @Override
    public void onAutorizaSelect(TiemposProceso.Seguimiento model) {
        Log.e("me hiciste click", "jiji");
    }

    private static final Comparator<TiemposProceso.Seguimiento> ALPHABETICAL_COMPARATOR = new Comparator<TiemposProceso.Seguimiento>() {
        @Override
        public int compare(TiemposProceso.Seguimiento a, TiemposProceso.Seguimiento b) {
            return 0;
        }
    };

}
