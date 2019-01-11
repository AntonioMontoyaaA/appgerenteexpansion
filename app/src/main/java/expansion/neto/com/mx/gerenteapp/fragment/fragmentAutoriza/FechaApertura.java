package expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentFechaAperturaBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class FechaApertura extends DialogFragment  {

    FragmentFechaAperturaBinding binding;

    public interface FechaAperturaListener {
        void ActualizaFecha(String fechaActualiza);
    }

    private FechaAperturaListener listener;

    public void setFechaAperturaListener(FechaAperturaListener listener){
        this.listener = listener;
    }
    public FechaApertura() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fecha_apertura,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.aceptarApertura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                final String mdId = preferences.getString("mdId", "");
                final SharedPreferences.Editor editor =preferences.edit();
                String fechaApertura = currentDate();
                editor.putString("fechaApertura"+mdId, fechaApertura);
                editor.apply();
                getDialog().dismiss();


            }
        });
    }

    int mes;
    public String currentDate() {
        StringBuilder mcurrentDate = new StringBuilder();
        mes = binding.datePicker.getMonth() + 1;
        if(mes>9){
            mcurrentDate.append(binding.datePicker.getDayOfMonth() + "/" + mes +"/"+binding.datePicker.getYear());
        }else{
            mcurrentDate.append(binding.datePicker.getDayOfMonth() + "/" + "0"+mes +"/"+binding.datePicker.getYear());
        }
        this.listener.ActualizaFecha(mcurrentDate.toString());
        return mcurrentDate.toString();

    }
}

