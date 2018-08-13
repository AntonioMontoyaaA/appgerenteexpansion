package expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentDialogFinalizarBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AutorizaResponse;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderAutorizaFinal;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FragmentInicioAutoriza;

import static expansion.neto.com.mx.gerenteapp.utils.Util.loadingProgress;

public class FragmentDialogFinalizar extends DialogFragment {

    FragmentDialogFinalizarBinding binding;
    SharedPreferences preferences;
    final int AUTORIZA_MD = 1;
    final int RECHAZA_MD = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setCancelable(false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_finalizar, container, false);
        View view = binding.getRoot();

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String tituloFinalizar = preferences.getString("tituloFinalizar","");
        String descripcionFinalizar = preferences.getString("descripcionFinalizar","");
        final String creadorMd = preferences.getString("creadorMd", "");
        final String categoria = preferences.getString("categoria", "");

        binding.categoriaMdFin.setText(categoria);
        binding.nombreResponsableMd.setText(creadorMd);
        binding.tipoAutorizacion.setText(tituloFinalizar);
        binding.textAutorizaRechaza.setText(descripcionFinalizar);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                getContext().startActivity(main);


            }
        });
    }
}
