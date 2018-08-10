package expansion.neto.com.mx.gerenteapp.fragment.fragmentAgenda;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAgendaDiaBinding;
import expansion.neto.com.mx.gerenteapp.utils.Util;

/**
 * Created by Kevin on 26/6/2017.
 */
@SuppressLint("ValidFragment")
public class FragmentDialogAgendaDia extends DialogFragment {

    FragmentAgendaDiaBinding binding;
    String tipo;
    @SuppressLint("ValidFragment")
    public FragmentDialogAgendaDia(String tipo){
        this.tipo = tipo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agenda_dia,container,false);
        View view = binding.getRoot();
        binding.datePicker.setMinDate(System.currentTimeMillis() - 1000);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();


                String fechaAgenda = currentDate();

                if(tipo.equals("inicio")){
                    editor.putString("fechaInicio", Util.getFechaFormat(format(currentDate())));
                    editor.putString("fechaAgenda", fechaAgenda);
                    editor.putString("fechaFinProgramada", fechaAgenda);
                    editor.apply();
                }else{
                    editor.putString("fechaInicio", Util.getFechaFormat(format(currentDate())));
                    editor.putString("fechaFinProgramada", fechaAgenda);
                    editor.apply();
                }

                FragmentManager fm = getFragmentManager();
                FragmentDialogAgendaHora dFragment = new FragmentDialogAgendaHora(tipo);
                dFragment.show(fm, "Dialog Fragment");
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
            return mcurrentDate.toString();
        }else{
            mcurrentDate.append(binding.datePicker.getDayOfMonth() + "/" + "0"+mes +"/"+binding.datePicker.getYear());
            return mcurrentDate.toString();
        }
    }



    public Date format(String fecha){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(fecha);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}