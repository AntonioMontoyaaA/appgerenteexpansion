package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion;

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
import expansion.neto.com.mx.gerenteapp.databinding.FragmentDialogGuardarMdBinding;

/**
 * Created by Kevin on 26/6/2017.
 */

public class FragmentDialogGuardar extends DialogFragment {

    FragmentDialogGuardarMdBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_guardar_md,container,false);
        View view = binding.getRoot();

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.aceptars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

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
            window.setLayout( 450, 280);
        }else{
            window.setLayout(1064, 420);
        }

        window.setGravity(Gravity.CENTER);

    }
}
