package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentDialogErrorBinding;

/**
 * Created by Kevin on 26/6/2017.
 */

public class FragmentDialogError405 extends DialogFragment {

    FragmentDialogErrorBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_error,container,false);
        Bundle arguments = getArguments();
        String mensaje = arguments.getString("mensaje");
        binding.mensajeError.setText(mensaje);
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
        Window window = getDialog().getWindow();
        window.setLayout(1070, 420);
        window.setGravity(Gravity.CENTER);
    }
}
