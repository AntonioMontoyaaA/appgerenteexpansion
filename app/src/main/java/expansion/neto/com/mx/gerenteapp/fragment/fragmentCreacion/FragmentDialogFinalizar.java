package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentFinalizaBinding;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;

import static expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion.FragmentDialogCancelarMd.cleanShared;

/**
 * Created by Kevin on 26/6/2017.
 */

public class FragmentDialogFinalizar extends DialogFragment {

    FragmentFinalizaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_finaliza,container,false);
        View view = binding.getRoot();

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String puntuacion = preferences.getString("puntuacion", "");
        String categoria = preferences.getString("categoria", "");

        binding.puntaje.setText(puntuacion+"");
        binding.categoriaC.setText(categoria+"");
        getDialog().setCanceledOnTouchOutside(false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorExpansion = preferences.edit();
                editorExpansion.putString("mdIdterminar", "");
                editorExpansion.apply();

                cleanShared(getContext());
                Intent main = new Intent(getContext(), ActivityMain.class);
                getContext().startActivity(main);
            }
        });

    }

}
