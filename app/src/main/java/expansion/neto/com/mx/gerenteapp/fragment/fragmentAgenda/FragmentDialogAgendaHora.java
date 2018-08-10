package expansion.neto.com.mx.gerenteapp.fragment.fragmentAgenda;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TimePicker;

import java.util.Calendar;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAgendaHoraBinding;
import expansion.neto.com.mx.gerenteapp.ui.agenda.ActivityAddEvento;
import expansion.neto.com.mx.gerenteapp.utils.Util;

/**
 * Created by Kevin on 26/6/2017.
 */

@SuppressLint("ValidFragment")
public class FragmentDialogAgendaHora extends DialogFragment {


    FragmentAgendaHoraBinding binding;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    String fechaHora;
    String fechaHoraFin;
    String tipo;

    @SuppressLint("ValidFragment")
    public FragmentDialogAgendaHora(String tipo){
        this.tipo = tipo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_agenda_hora,container,false);
        View view = binding.getRoot();
        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        editor = preferences.edit();
        FragmentDialogAgendaHora fragment = new FragmentDialogAgendaHora(tipo);
        if ( fragment.getDialog() != null )
            fragment.getDialog().setCanceledOnTouchOutside(true);

        binding.aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hourOfDay = binding.datePicker.getCurrentHour();
                int minute = binding.datePicker.getCurrentMinute();

                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);

                String hora = horaFormateada + DOS_PUNTOS + minutoFormateado;
                String horaFin = String.valueOf(Integer.valueOf(horaFormateada)+1) + DOS_PUNTOS + minutoFormateado;


                fechaHora = preferences.getString("fechaInicio", "") +" ~ "+ hora;

                fechaHoraFin = preferences.getString("fechaInicio", "") +" ~ "+ horaFin;

                SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();

                if(tipo.equals("inicio")){
                    editor.putString("horaIni", hora);
                    editor.putString("horaFi", horaFin);
                    editor.apply();
                }else{
                    editor.putString("horaFi", hora);
                    editor.apply();
                }


                ((ActivityAddEvento) getActivity()).setFecha(fechaHora, tipo, fechaHoraFin);



                getDialog().dismiss();

            }
        });
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}