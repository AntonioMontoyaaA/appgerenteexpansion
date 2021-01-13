package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentCancelarMdBinding;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;

/**
 * Created by Kevin on 26/6/2017.
 */

public class FragmentDialogCancelarMd extends DialogFragment {


    FragmentCancelarMdBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cancelar_md,container,false);
        View view = binding.getRoot();

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), ActivityMain.class);
                getContext().startActivity(main);
                cleanShared(getContext());

            }
        });

        binding.cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

    }



    public static void cleanShared(Context context){
        SharedPreferences preferencesConstruccion = context.getApplicationContext().getSharedPreferences("datosConstruccion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesConstruccion.edit();
        editor.clear();
        editor.apply();
        context.getSharedPreferences("datosConstruccion", 0).edit().clear().apply();


        SharedPreferences preferencesGeneralidades = context.getApplicationContext().getSharedPreferences("datosGeneralidades", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorGeneralidades = preferencesGeneralidades.edit();
        editorGeneralidades.clear();
        editorGeneralidades.apply();
        context.getSharedPreferences("datosGeneralidades", 0).edit().clear().apply();



        SharedPreferences preferencesPropietario = context.getApplicationContext().getSharedPreferences("datosPropietario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorPropietario = preferencesPropietario.edit();
        editorPropietario.clear();
        editorPropietario.apply();
        context.getSharedPreferences("datosPropietario", 0).edit().clear().apply();


        SharedPreferences preferencesSuperficie = context.getApplicationContext().getSharedPreferences("datosSuperficie", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorSuperficie = preferencesSuperficie.edit();
        editorSuperficie.clear();
        editorSuperficie.apply();
        context.getSharedPreferences("datosSuperficie", 0).edit().clear().apply();


        SharedPreferences preferencesZonificacion = context.getApplicationContext().getSharedPreferences("datosZonificacion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorZonificacion = preferencesZonificacion.edit();
        editorZonificacion.clear();
        editorZonificacion.apply();
        context.getSharedPreferences("datosZonificacion", 0).edit().clear().apply();


        SharedPreferences preferencesSitio = context.getApplicationContext().getSharedPreferences("datosSitio", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorSitio = preferencesSitio.edit();
        editorSitio.clear();
        editorSitio.apply();
        context.getSharedPreferences("datosSitio", 0).edit().clear().apply();


        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorExpansion = preferences.edit();
        editorExpansion.putLong("mdId", 0);
        //editorExpansion.putString("nombreSitio", "");
        editorExpansion.putFloat("latMd", 0);
        editorExpansion.putFloat("lotMd", 0);
        editorExpansion.putString("radio", "");
        editor.putString("tipoSitio","");
        editorExpansion.apply();

    }

    public void onResume() {
        super.onResume();

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        System.out.println( "ancho absoluto en pixels "+ width );
        int height = metrics.heightPixels;
        System.out.println( "alto absoluto en pixels " + height );

        Window window = getDialog().getWindow();


        if (width <= 400 && height <= 400){

            window.setLayout(240, 120);
        }else if (width <= 500){
            window.setLayout( 450, 240);
        }else{
            window.setLayout(1064, 420);
        }

        window.setGravity(Gravity.CENTER);
    }
}