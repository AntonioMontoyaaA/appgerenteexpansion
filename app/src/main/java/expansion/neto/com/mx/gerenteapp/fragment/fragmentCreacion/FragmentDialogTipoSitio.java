package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion;

import android.app.Dialog;
import android.content.Context;
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
import expansion.neto.com.mx.gerenteapp.databinding.FragmentSwitchSitioBinding;

/**
 * Created by Kevin on 26/6/2017.
 */

public class FragmentDialogTipoSitio extends DialogFragment {

    FragmentSwitchSitioBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_switch_sitio,container,false);
        View view = binding.getRoot();
        getDialog().setCanceledOnTouchOutside(false);
        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorExpansion = preferences.edit();

        binding.ciudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                editorExpansion.putString("tipoSitio", "1");
                editorExpansion.apply();
            }
        });
        binding.rural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                editorExpansion.putString("tipoSitio", "2");
                editorExpansion.apply();
            }
        });

        return view;
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
        window.setBackgroundDrawableResource( R.drawable.background );
        if (width <= 400 && height <= 400){
            window.setLayout( width-50 , height-50 );
        }else {
            window.setLayout( width - 100, height - 150 );
        }
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()){
            @Override
            public void onBackPressed() {
                //do your stuff
            }
        };
    }



}
