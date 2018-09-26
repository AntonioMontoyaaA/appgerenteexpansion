package expansion.neto.com.mx.gerenteapp.fragment.fragmentDocumentos;

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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityProcesoListBinding;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.UsuarioLogin;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Proceso;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderDatosProceso;
import expansion.neto.com.mx.gerenteapp.sorted.proceso.AdapterProceso;
import expansion.neto.com.mx.gerenteapp.sorted.proceso.ProcesoHolder;
import expansion.neto.com.mx.gerenteapp.ui.documentos.ActivityDocumentos;

public class FragmentCardDocumentos extends Fragment implements ProcesoHolder.Listener  {

    private ActivityProcesoListBinding binding;
    UsuarioLogin.Perfil perfil;
    AdapterProceso adapter;

    List<Proceso.Memoria> listaMemorias = null;
    private SlideUp slide;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_proceso_list,container,false);
        View view = binding.getRoot();

        binding.rootView.setVisibility(View.GONE);
        getListaProceso(perfil);

        adapter = new AdapterProceso(getContext(),ALPHABETICAL_COMPARATOR, this);
        binding.recyclerAutoriza.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerAutoriza.setAdapter(adapter);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        binding.recyclerAutoriza.setLayoutManager(mLayoutManager);
        binding.recyclerAutoriza.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
        binding.recyclerAutoriza.setItemAnimator(new DefaultItemAnimator());

        binding.buscar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) { }

            @SuppressLint("DefaultLocale")
            @Override
            public void afterTextChanged(Editable editable) {
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
        });

        binding.leyendaLayout.setVisibility(View.GONE);
        binding.content2.leyendacerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slide.hide();
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
            return a.getTotales().compareTo(b.getTotales());
        }
    };

    public void resizeRecycler(ActivityProcesoListBinding binding, int tam){
        ViewGroup.LayoutParams params = binding.recyclerAutoriza.getLayoutParams();
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int pixels = (int) (tam * scale + 0.5f);
        params.height=pixels;
        binding.recyclerAutoriza.setLayoutParams(params);
    }

    public void getListaProceso(UsuarioLogin.Perfil perfil){
        binding.prog.setVisibility(View.VISIBLE);
        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mes = preferences.getString("mesConsulta","");
        String semana = preferences.getString("semanaConsulta","");
        ProviderDatosProceso.getInstance(getContext()).obtenerDatosProceso("5",semana, mes, new ProviderDatosProceso.ConsultaDatosProceso() {
            @Override
            public void resolve(Proceso memorias) {
                if(memorias.getCodigo()==200){
                    if(memorias.getCodigo()!=404) {
                        if(memorias.getMemorias() != null && memorias.getMemorias().size() > 0) {
                            listaMemorias = memorias.getMemorias();
                            adapter.edit().replaceAll(memorias.getMemorias()).commit();
                            adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                            binding.prog.setVisibility(View.GONE);

                            resizeRecycler(binding, 570);

                        } else {
                            binding.prog.setVisibility(View.GONE);
                            Snackbar snackbar = Snackbar.make(binding.layout,
                                    Html.fromHtml("<b><font color=\"#254581\">" +
                                            getContext().getString(R.string.sinMDProceso) +
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

            @Override
            public void reject(Exception e) {

            }
        });
    }

    @Override
    public void onProcesoSelect(Proceso.Memoria model) {

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mdId", model.getMemoriaid());
        editor.putString("nombreSitio", model.getNombresitio());

        editor.apply();

        Intent main = new Intent(getContext(), ActivityDocumentos.class);
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

}
