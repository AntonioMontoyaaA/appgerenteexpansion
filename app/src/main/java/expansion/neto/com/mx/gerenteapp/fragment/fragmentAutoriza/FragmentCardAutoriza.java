/*
 * Copyright 2014 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityAutorizaListBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.UsuarioLogin;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosAutorizadas;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AdapterAutoriza;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AutorizaHolder;
import expansion.neto.com.mx.gerenteapp.ui.autoriza.ActivityAutorizar;


public class FragmentCardAutoriza extends Fragment implements AutorizaHolder.Listener{

	private ActivityAutorizaListBinding binding;
	UsuarioLogin.Perfil perfil;
	AdapterAutoriza adapter;

	List<Autorizadas.Memoria> listaMemorias = null;


	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {

		binding = DataBindingUtil.inflate(inflater, R.layout.activity_autoriza_list,container,false);
		View view = binding.getRoot();

		getListaAutoriza(perfil);

		adapter = new AdapterAutoriza(getContext(),ALPHABETICAL_COMPARATOR, this);
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

				if(listaMemorias!=null){
					String texto = binding.buscar.getText().toString();
					List<Autorizadas.Memoria> listaTemporal = new ArrayList<Autorizadas.Memoria>();

					binding.recyclerAutoriza.removeAllViews();
					adapter.edit().removeAll().commit();
					if (texto.equals("")) {
						adapter.edit().replaceAll(listaMemorias).commit();
						adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
					} else {
						for(Autorizadas.Memoria memoria : listaMemorias) {
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

		return view;
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	private static final Comparator<Autorizadas.Memoria> ALPHABETICAL_COMPARATOR = new Comparator<Autorizadas.Memoria>() {
		@Override
		public int compare(Autorizadas.Memoria a, Autorizadas.Memoria b) {
			return a.getTotales().compareTo(b.getTotales());
		}
	};

	//ArrayList<Autorizadas> autorizadas;

	public void getListaAutoriza(UsuarioLogin.Perfil perfil){

		binding.prog.setVisibility(View.VISIBLE);

		SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
		String mes = preferences.getString("mesConsulta","");
		String semana = preferences.getString("semanaConsulta","");

		ProviderDatosAutorizadas.getInstance(getContext()).obtenerDatosAutorizadas(semana, mes, new ProviderDatosAutorizadas.ConsultaDatosAutorizadas() {
			@Override
			public void resolve(Autorizadas datosSitio) {

				if(datosSitio.getCodigo()==200){
					if(datosSitio.getCodigo()!=404){
					    if(datosSitio.getMemorias() != null && datosSitio.getMemorias().size() > 0) {
                            listaMemorias = datosSitio.getMemorias();
                            adapter.edit().replaceAll(datosSitio.getMemorias()).commit();
                            adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                            binding.prog.setVisibility(View.GONE);
                        } else {
                            binding.prog.setVisibility(View.GONE);
                            Snackbar snackbar = Snackbar.make(binding.layout,
                                    Html.fromHtml("<b><font color=\"#254581\">" +
                                            getContext().getString(R.string.sinMDAutorizadas) +
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
	public void onAutorizaSelect(Autorizadas.Memoria model) {

		SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("atrasa", model.getAtrasada());
		editor.putString("mdId", model.getMemoriaid());
        editor.putInt("estatusId", model.getEstatusid());
        editor.putString("nombreEstatus", model.getEstatus());
        editor.putString("urlLayout", model.getUrllayout());
		editor.putString("monto1", model.getPptoauditoria());
		editor.putString("monto2", model.getPptoobra());

        editor.apply();

		Intent main = new Intent(getContext(), ActivityAutorizar.class);
		getContext().startActivity(main);
		getActivity().finish();
	}

	/**
	 * RecyclerView item decoration - give equal margin around grid item
	 */
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

	/**
	 * Converting dp to pixel
	 */
	private int dpToPx(int dp) {
		Resources r = getResources();
		return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
	}

}
