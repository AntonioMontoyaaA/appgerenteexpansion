package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.DialogTareaAgendaBinding;
import expansion.neto.com.mx.gerenteapp.utils.Util;

/**
 * Created by marcosmarroquin on 28/03/18.
 */

public class FragmentAgregarTarea extends DialogFragment {

    DialogTareaAgendaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_tarea_agenda,container,false);
        View view = binding.getRoot();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        binding.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        //Log.e("","...cerrar");

        binding.spinnerFecha.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            View view = getActivity().getCurrentFocus();
                            if (view != null) {
                                Util.hideSoftKeyboard(getActivity());
                            }
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



}



