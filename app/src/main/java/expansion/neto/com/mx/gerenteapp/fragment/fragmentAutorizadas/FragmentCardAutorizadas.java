package expansion.neto.com.mx.gerenteapp.fragment.fragmentAutorizadas;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.databinding.ActivityAutorizadasListBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentDetalle.FragmentDetalle;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AdapterAutoriza;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AutorizaHolder;
import expansion.neto.com.mx.gerenteapp.sorted.autorizadas.AutorizadasHolder;
import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Totales;
import expansion.neto.com.mx.gerenteapp.provider.autorizadasProvider.ProviderDatosAutorizadas;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderTotales;
import expansion.neto.com.mx.gerenteapp.sorted.autorizadas.AdapterAutorizadas;


import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_AUDITORIA;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_CONSTRUCCION;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_EXPANSION;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_GESTORIA;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_GEXPANSION;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_OPERACIONES;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.STATUS_TOTALES;
import static expansion.neto.com.mx.gerenteapp.fragment.fragmentDetalle.FragmentDetalle.loadingProgress;

public class FragmentCardAutorizadas extends Fragment implements AutorizadasHolder.Listener  {

    private ActivityAutorizadasListBinding binding;
    AdapterAutorizadas adapter;
    List<Autorizadas.Autorizada> listaMemorias = null;

    String mes;
    String area;
    String usuarioId;
    String anio;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_autorizadas_list,container,false);
        View view = binding.getRoot();
        progressDialog = new ProgressDialog(getContext());

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        mes = preferences.getString("mesDasbord","");
        area = preferences.getString("areaxpuesto","");
        usuarioId = preferences.getString("usuario","");
        anio = preferences.getString("anioConsulta","");

        Calendar calendar = Calendar.getInstance();
        anio = String.valueOf(calendar.get(Calendar.YEAR));


        getDatos();

        adapter = new AdapterAutorizadas(getContext(),ALPHABETICAL_COMPARATOR, this);
        binding.recyclerAutoriza.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerAutoriza.setAdapter(adapter);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        binding.recyclerAutoriza.setLayoutManager(mLayoutManager);
        binding.recyclerAutoriza.addItemDecoration(new FragmentCardAutorizadas.GridSpacingItemDecoration(1, dpToPx(1), true));
        binding.recyclerAutoriza.setItemAnimator(new DefaultItemAnimator());

        binding.buscar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @SuppressLint("DefaultLocale")
            @Override
            public void afterTextChanged(Editable editable) {
                if(listaMemorias!=null){
                    String texto = binding.buscar.getText().toString();
                    List<Autorizadas.Autorizada> listaTemporal = new ArrayList<Autorizadas.Autorizada>();

                    binding.recyclerAutoriza.removeAllViews();
                    adapter.edit().removeAll().commit();
                    if (texto.equals("")) {
                        adapter.edit().replaceAll(listaMemorias).commit();
                        adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                    } else {
                        for(Autorizadas.Autorizada memoria : listaMemorias) {
                            if(memoria.getNombresitio().toLowerCase().contains(texto.toLowerCase()) || memoria.getNombresitio().toLowerCase().contains(texto.toLowerCase())) {
                                listaTemporal.add(memoria);
                            }
                        }
                        adapter.edit().replaceAll(listaTemporal).commit();
                        adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                    }
                }
            }
        });

        binding.content2.gexpansion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTotales(ID_GEXPANSION);

                binding.content2.viewge.setAlpha(1);
                binding.content2.imggerente.setAlpha(1.0f);
                binding.content2.txtgexpansion.setAlpha(1.0f);


                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imggestoria.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);

                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);


                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);

                binding.esta.setVisibility(View.VISIBLE);
                resizeRecycler(binding, 418);

            }
        });

        binding.content2.expansiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTotales(ID_EXPANSION);

                binding.esta.setVisibility(View.VISIBLE);
                binding.content2.viewe.setAlpha(1);
                binding.content2.imgexpansion.setAlpha(1.0f);
                binding.content2.txtexpansion.setAlpha(1.0f);

                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imggestoria.setAlpha(0.2f);
                binding.content2.imggerente.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);


                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);
                resizeRecycler(binding, 418);

            }
        });

        binding.content2.gestoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTotales(ID_GESTORIA);

                binding.esta.setVisibility(View.VISIBLE);

                binding.content2.viewges.setAlpha(1);
                binding.content2.imggestoria.setAlpha(1.0f);
                binding.content2.txtgestoria.setAlpha(1.0f);

                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);
                binding.content2.imggerente.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);


                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);
                resizeRecycler(binding, 418);

            }
        });

        binding.content2.construccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTotales(ID_CONSTRUCCION);

                binding.esta.setVisibility(View.VISIBLE);

                binding.content2.viewcon.setAlpha(1);
                binding.content2.imgconstruccion.setAlpha(1.0f);
                binding.content2.txtconstruccion.setAlpha(1.0f);

                binding.content2.imggestoria.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);
                binding.content2.imggerente.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);

                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);
                resizeRecycler(binding, 418);

            }
        });

        binding.content2.operaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTotales(ID_OPERACIONES);

                binding.content2.viewope.setAlpha(1);
                binding.content2.imgoperaciones.setAlpha(1.0f);
                binding.content2.txtoperaciones.setAlpha(1.0f);
                binding.esta.setVisibility(View.VISIBLE);

                binding.content2.imggestoria.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);
                binding.content2.imggerente.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);

                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);
                resizeRecycler(binding, 418);

            }
        });

        binding.content2.auditorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getTotales(ID_AUDITORIA);

                binding.content2.viewaudi.setAlpha(1);
                binding.content2.imgauditoria.setAlpha(1.0f);
                binding.content2.txtauditoria.setAlpha(1.0f);

                binding.esta.setVisibility(View.VISIBLE);

                binding.content2.imggerente.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);
                binding.content2.imggestoria.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);

                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);
                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);

                resizeRecycler(binding, 418);

            }
        });


        binding.vermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                binding.esta.setVisibility(View.GONE);

                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);

                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imggestoria.setAlpha(0.2f);
                binding.content2.imggerente.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);


                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);
                resizeRecycler(binding, 490 );

                binding.vermas.setEnabled(false);
                binding.vermas.setAlpha(0.4f);

                getListaProceso();

            }
        });

        return view;
    }

    private AutorizadasHolder.Listener autorizaHolder = new AutorizadasHolder.Listener() {
        @Override
        public void onProcesoSelect(Autorizadas.Autorizada model) {
        }
    };


    public void getListaProceso(){

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");
        String mes = preferences.getString("mesTaco","");
        loadingProgress(progressDialog, 0);

        ProviderDatosAutorizadas.getInstance(getContext()).obtenerDatosAutorizadas(getString(R.string.one),mes, usuario,anio,
                new ProviderDatosAutorizadas.ConsultaDatosSitio() {
                    @Override
                    public void resolve(Autorizadas datosSitio) {
                        if(datosSitio!=null && datosSitio.getAutorizadas()!=null){
                            adapter = new AdapterAutorizadas(getContext(),ALPHABETICAL_COMPARATOR, autorizaHolder);
                            if(listaMemorias!=null){
                                listaMemorias.clear();
                                adapter.edit().replaceAll(listaMemorias).commit();
                                adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                                binding.recyclerAutoriza.setAdapter(adapter);
                            }

                            listaMemorias = datosSitio.getAutorizadas();
                            adapter.edit().replaceAll(listaMemorias).commit();
                            adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                            binding.recyclerAutoriza.setAdapter(adapter);

                            binding.prog.setVisibility(View.GONE);
                            binding.vermas.setVisibility(View.VISIBLE);
                            loadingProgress(progressDialog, 1);


                        }else{
                            binding.vermas.setVisibility(View.VISIBLE);
                            loadingProgress(progressDialog, 1);
                        }
                    }

                    @Override
                    public void reject(Exception e) {
                        loadingProgress(progressDialog, 1);
                    }
                });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private static final Comparator<Autorizadas.Autorizada> ALPHABETICAL_COMPARATOR = new Comparator<Autorizadas.Autorizada>() {
        @Override
        public int compare(Autorizadas.Autorizada a, Autorizadas.Autorizada b) {
            return 0;
        }
    };

    ProgressDialog progressDialog;

    public void getDatos(){

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");
        String mes = preferences.getString("mesConsulta","");
        loadingProgress(progressDialog, 0);

        ProviderDatosAutorizadas.getInstance(getContext()).obtenerDatosAutorizadas(getString(R.string.zero),mes, usuario,anio,
                new ProviderDatosAutorizadas.ConsultaDatosSitio() {
                    @Override
                    public void resolve(Autorizadas datosSitio) {

                        if(datosSitio!=null && datosSitio.getAutorizadas()!=null){

                            listaMemorias = datosSitio.getAutorizadas();
                            adapter.edit().replaceAll(datosSitio.getAutorizadas()).commit();
                            adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                            binding.recyclerAutoriza.setAdapter(adapter);

                            binding.prog.setVisibility(View.GONE);
                            binding.vermas.setVisibility(View.VISIBLE);
                            loadingProgress(progressDialog, 1);


                        }else{
                            binding.vermas.setVisibility(View.VISIBLE);
                            loadingProgress(progressDialog, 1);
                        }
                    }

                    @Override
                    public void reject(Exception e) {
                        loadingProgress(progressDialog, 1);
                    }
                });
    }

    @Override
    public void onProcesoSelect(Autorizadas.Autorizada model) {

    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void resizeRecycler(ActivityAutorizadasListBinding binding, int tam){
        ViewGroup.LayoutParams params = binding.recyclerAutoriza.getLayoutParams();
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (tam * scale + 0.5f);
        params.height=pixels;
        binding.recyclerAutoriza.setLayoutParams(params);
    }

    public void getTotales(String id){
        ProviderTotales.getInstance(getContext()).obtenerTotales(usuarioId,
                STATUS_TOTALES, id, mes, getString(R.string.zero), anio, new ProviderTotales.InterfaceObtieneTotales() {
                    @Override
                    public void resolve(Totales totales) {
                        if(totales!=null){
                            if(totales.getCodigo()==200){
                                binding.totalMd.setText(totales.getTotal()+"");
                                binding.totalAtrasadas.setText(totales.getAtrasada()+"");

                            }
                        }
                    }
                    @Override public void reject(Exception e) {

                    }
                });
    }

}
