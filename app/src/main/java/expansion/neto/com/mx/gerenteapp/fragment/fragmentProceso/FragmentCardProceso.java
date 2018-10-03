package expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso;

import android.annotation.SuppressLint;
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
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityProcesoListBinding;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.UsuarioLogin;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Proceso;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Totales;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderDatosProceso;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderTotales;
import expansion.neto.com.mx.gerenteapp.sorted.proceso.AdapterProceso;
import expansion.neto.com.mx.gerenteapp.sorted.proceso.ProcesoHolder;
import expansion.neto.com.mx.gerenteapp.ui.proceso.ActivityProceso;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_AUDITORIA;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_CONSTRUCCION;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_EXPANSION;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_GESTORIA;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_GEXPANSION;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ID_OPERACIONES;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.STATUS_TOTALES;

public class FragmentCardProceso extends Fragment implements ProcesoHolder.Listener  {

    private ActivityProcesoListBinding binding;
    UsuarioLogin.Perfil perfil;
    AdapterProceso adapter;
    List<Proceso.Memoria> listaMemorias = null;

    String mes;
    String area;
    String usuario;
    String anio;
    String semana;
    Boolean atrasada = false;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_proceso_list,container,false);
        View view = binding.getRoot();

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        mes = preferences.getString("mesConsulta","");
        area = preferences.getString("areaxpuesto","");
        usuario = preferences.getString("usuario","");
        semana = preferences.getString("semanaConsulta","");

        Calendar calendar = Calendar.getInstance();
        anio = String.valueOf(calendar.get(Calendar.YEAR));

        getListaProceso("0");

        adapter = new AdapterProceso(getContext(),ALPHABETICAL_COMPARATOR, this);
        binding.recyclerAutoriza.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerAutoriza.setAdapter(adapter);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        binding.recyclerAutoriza.setLayoutManager(mLayoutManager);
        binding.recyclerAutoriza.addItemDecoration(new FragmentCardProceso.GridSpacingItemDecoration(1, dpToPx(1), true));
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
                    List<Proceso.Memoria> listaTemporal = new ArrayList<Proceso.Memoria>();

                    binding.recyclerAutoriza.removeAllViews();
                    adapter.edit().removeAll().commit();
                    if (texto.equals("")) {
                        adapter.edit().replaceAll(listaMemorias).commit();
                        adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                    } else {
                        for(Proceso.Memoria memoria : listaMemorias) {
                            if(memoria.getCreador().toLowerCase().contains(texto.toLowerCase()) || memoria.getNombresitio().toLowerCase().contains(texto.toLowerCase())) {
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
                resizeRecycler(binding, 470 );

                binding.vermas.setEnabled(false);
                binding.vermas.setAlpha(0.4f);

                getListaProceso("1");


            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private static final Comparator<Proceso.Memoria> ALPHABETICAL_COMPARATOR = new Comparator<Proceso.Memoria>() {
        @Override
        public int compare(Proceso.Memoria a, Proceso.Memoria b) {
            return 0;
        }
    };


    private ProcesoHolder.Listener autorizaHolder = new ProcesoHolder.Listener() {
        @Override
        public void onProcesoSelect(Proceso.Memoria model) {

            SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("mdIdterminar", "");
            editor.putString("nombreSitio", "");
            editor.apply();
            editor.putString("mdIdterminar", model.getMemoriaid());
            editor.putString("nombreSitio", model.getNombresitio());
            editor.putInt("atrasa", model.getAtrasada());

            editor.apply();
            Intent main = new Intent(getContext(), ActivityProceso.class);
            getContext().startActivity(main);
            getActivity().finish();
        }
    };

    public void getListaProceso(UsuarioLogin.Perfil perfil){

        binding.prog.setVisibility(View.VISIBLE);

        ProviderDatosProceso.getInstance(getContext()).obtenerDatosProceso("", mes, area, new ProviderDatosProceso.ConsultaDatosProceso() {
            @Override
            public void resolve(Proceso memorias) {
                if(memorias!=null){
                    if(memorias.getCodigo()==200){
                        if(memorias.getCodigo()!=404) {
                            if(memorias.getMemorias() != null && memorias.getMemorias().size() > 0) {



                                listaMemorias = memorias.getMemorias();
                                adapter.edit().replaceAll(memorias.getMemorias()).commit();
                                adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                                binding.prog.setVisibility(View.GONE);
                                binding.rootView.setVisibility(View.VISIBLE);
                                binding.vermas.setVisibility(View.VISIBLE);

                            } else {
                                binding.vermas.setVisibility(View.VISIBLE);
                                binding.prog.setVisibility(View.GONE);
                                Snackbar snackbar = Snackbar.make(binding.layout,
                                        Html.fromHtml("<b><font color=\"#254581\">" +
                                                memorias.getMensaje() +
                                                "</font></b>"), Snackbar.LENGTH_SHORT);
                                View snackBarView = snackbar.getView();
                                snackBarView.setBackgroundColor(getContext().getResources().getColor(R.color.snackBar));
                                snackbar.show();
                            }

                        }else{
                            binding.vermas.setVisibility(View.VISIBLE);
                            binding.prog.setVisibility(View.GONE);
                            Snackbar snackbar = Snackbar.make(binding.layout,
                                    Html.fromHtml("<b><font color=\"#254581\">" +
                                            getContext().getString(R.string.mdnull) +
                                            "</font></b>"), Snackbar.LENGTH_SHORT);
                            View snackBarView = snackbar.getView();
                            snackBarView.setBackgroundColor(getContext().getResources().getColor(R.color.snackBar));
                            snackbar.show();
                        }
                    }else{
                        binding.prog.setVisibility(View.GONE);
                        Snackbar snackbar = Snackbar.make(binding.layout,
                                Html.fromHtml("<b><font color=\"#254581\">" +
                                        getContext().getString(R.string.conexionInternet) +
                                        "</font></b>"), Snackbar.LENGTH_SHORT);
                        View snackBarView = snackbar.getView();
                        snackBarView.setBackgroundColor(getContext().getResources().getColor(R.color.snackBar));
                        snackbar.show();
                    }
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    public void getListaProceso(String vermas){

        binding.prog.setVisibility(View.VISIBLE);

        ProviderDatosProceso.getInstance(getContext()).obtenerDatosProcesos(vermas, area, mes, new ProviderDatosProceso.ConsultaDatosProceso() {
            @Override
            public void resolve(Proceso memorias) {
                if(memorias!=null){
                    if(memorias.getCodigo()==200){
                        if(memorias.getCodigo()!=404) {
                            if(memorias.getMemorias() != null && memorias.getMemorias().size() > 0) {
                                adapter = new AdapterProceso(getContext(),ALPHABETICAL_COMPARATOR, autorizaHolder);
                                if(listaMemorias!=null){
                                    binding.recyclerAutoriza.setLayoutManager(new LinearLayoutManager(getContext()));
                                    listaMemorias.clear();
                                    adapter.edit().replaceAll(listaMemorias).commit();
                                    adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                                    binding.recyclerAutoriza.setAdapter(adapter);
                                }

                                listaMemorias = memorias.getMemorias();
                                adapter.edit().replaceAll(memorias.getMemorias()).commit();
                                adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                                binding.recyclerAutoriza.setAdapter(adapter);

                                binding.prog.setVisibility(View.GONE);
                                binding.rootView.setVisibility(View.VISIBLE);
                                binding.vermas.setVisibility(View.VISIBLE);

                            } else {
                                binding.vermas.setVisibility(View.VISIBLE);
                                binding.prog.setVisibility(View.GONE);
                                Snackbar snackbar = Snackbar.make(binding.layout,
                                        Html.fromHtml("<b><font color=\"#254581\">" +
                                                memorias.getMensaje() +
                                                "</font></b>"), Snackbar.LENGTH_SHORT);
                                View snackBarView = snackbar.getView();
                                snackBarView.setBackgroundColor(getContext().getResources().getColor(R.color.snackBar));
                                snackbar.show();
                            }

                        }else{
                            binding.prog.setVisibility(View.GONE);
                            Snackbar snackbar = Snackbar.make(binding.layout,
                                    Html.fromHtml("<b><font color=\"#254581\">" +
                                            getContext().getString(R.string.mdnull) +
                                            "</font></b>"), Snackbar.LENGTH_SHORT);
                            View snackBarView = snackbar.getView();
                            snackBarView.setBackgroundColor(getContext().getResources().getColor(R.color.snackBar));
                            snackbar.show();
                        }
                    }else{
                        binding.prog.setVisibility(View.GONE);
                        Snackbar snackbar = Snackbar.make(binding.layout,
                                Html.fromHtml("<b><font color=\"#254581\">" +
                                        getContext().getString(R.string.conexionInternet) +
                                        "</font></b>"), Snackbar.LENGTH_SHORT);
                        View snackBarView = snackbar.getView();
                        snackBarView.setBackgroundColor(getContext().getResources().getColor(R.color.snackBar));
                        snackbar.show();
                    }
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    @Override
    public void onProcesoSelect(Proceso.Memoria model) {

        // Log.e("*****", model.getMemoriaid()+"");

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mdIdterminar", "");
        editor.putString("nombreSitio", "");
        editor.apply();
        editor.putString("mdIdterminar", model.getMemoriaid());
        editor.putString("nombreSitio", model.getNombresitio());
        editor.putInt("atrasa", model.getAtrasada());

        editor.apply();
        Intent main = new Intent(getContext(), ActivityProceso.class);
        getContext().startActivity(main);
        getActivity().finish();
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

    public void resizeRecycler(ActivityProcesoListBinding binding, int tam){
        ViewGroup.LayoutParams params = binding.recyclerAutoriza.getLayoutParams();
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (tam * scale + 0.5f);
        params.height=pixels;
        binding.recyclerAutoriza.setLayoutParams(params);
    }


    public void getTotales(String id){
        ProviderTotales.getInstance(getContext()).obtenerTotales(usuario,
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
