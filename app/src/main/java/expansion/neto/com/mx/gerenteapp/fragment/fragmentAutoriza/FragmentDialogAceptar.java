package expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza;

import android.app.ProgressDialog;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentDialogAceptarBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AutorizaResponse;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Permiso;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderAutorizaModulo;

import static expansion.neto.com.mx.gerenteapp.utils.Util.loadingProgress;

/**
 * Created by Kevin on 26/6/2017.
 */

public class FragmentDialogAceptar extends DialogFragment {


    FragmentDialogAceptarBinding binding;
    SharedPreferences preferences;
    private int moduloSeleccionado = 0;
    ProgressDialog progressDialog;

    private final int MODULO_1 = 1;
    private final int MODULO_2 = 2;
    private final int MODULO_3 = 3;
    private final int MODULO_4 = 4;
    private final int MODULO_5 = 5;
    private final int MODULO_6 = 6;
    private final int MODULO_7 = 7;

    private final int AUTORIZA_ID = 1;
    private final int VALIDACION_AUTORIZA = 1;

    OnModuloAceptadoListener listener;

    public interface OnModuloAceptadoListener {
        public void onModuloAceptado(int modulo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_aceptar,container,false);
        View view = binding.getRoot();
        progressDialog = new ProgressDialog(getContext(), R.style.AppCompatAlertDialogStyle);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        moduloSeleccionado = preferences.getInt("moduloAutorizaRechaza",0);
        binding.comentario.setEnabled(true);
        if (getArguments() != null) {
            int numModulo = getArguments().getInt("modulo");
            setPermisosComentar(String.valueOf(numModulo));
            if(!permisoComentarP1){
                Toast.makeText(getContext(), "Sin permisos para comentar",
                        Toast.LENGTH_LONG).show();
                binding.comentario.setEnabled(false);
            }
            if(!permisoComentarP2){
                Toast.makeText(getContext(), "Sin permisos para comentar",
                        Toast.LENGTH_LONG).show();
                binding.comentario.setEnabled(false);
            }
            if(!permisoComentarP3){
                Toast.makeText(getContext(), "Sin permisos para comentar",
                        Toast.LENGTH_LONG).show();
                binding.comentario.setEnabled(false);
            }
            if(!permisoComentarP4){
                Toast.makeText(getContext(), "Sin permisos para comentar",
                        Toast.LENGTH_LONG).show();
                binding.comentario.setEnabled(false);
            }
            if(!permisoComentarP5){
                Toast.makeText(getContext(), "Sin permisos para comentar",
                        Toast.LENGTH_LONG).show();
                binding.comentario.setEnabled(false);
            }
            if(!permisoComentarP6){
                Toast.makeText(getContext(), "Sin permisos para comentar",
                        Toast.LENGTH_LONG).show();
                binding.comentario.setEnabled(false);
            }
            if(!permisoComentarP7){
                Toast.makeText(getContext(), "Sin permisos para comentar",
                        Toast.LENGTH_LONG).show();
                binding.comentario.setEnabled(false);
            }
        }

        return view;
    }

    public void setModuloAceptadoListener(OnModuloAceptadoListener listener) {
        this.listener = listener;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final String mdId = preferences.getString("mdId","");
        final String usuarioId = preferences.getString("usuario","");
        binding.aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorAutoriza = preferences.edit();

                switch(moduloSeleccionado) {
                    case  MODULO_1 :
                        editorAutoriza.putInt("MODULO_1_TIPO_AUTORIZACION", AUTORIZA_ID);
                        editorAutoriza.putInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO", 0);
                        editorAutoriza.putInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                        editorAutoriza.apply();
                        break;
                    case  MODULO_2 :
                        editorAutoriza.putInt("MODULO_2_TIPO_AUTORIZACION", AUTORIZA_ID);
                        editorAutoriza.putInt("MODULO_2_TIPO_AUTORIZACION_MOTIVO", 0);
                        editorAutoriza.putInt("MODULO_2_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                        editorAutoriza.apply();
                        break;
                    case  MODULO_3 :
                        editorAutoriza.putInt("MODULO_3_TIPO_AUTORIZACION", AUTORIZA_ID);
                        editorAutoriza.putInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO", 0);
                        editorAutoriza.putInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                        editorAutoriza.apply();
                        break;
                    case  MODULO_4 :
                        editorAutoriza.putInt("MODULO_4_TIPO_AUTORIZACION", AUTORIZA_ID);
                        editorAutoriza.putInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO", 0);
                        editorAutoriza.putInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                        editorAutoriza.apply();
                        break;
                    case  MODULO_5 :
                        editorAutoriza.putInt("MODULO_5_TIPO_AUTORIZACION", AUTORIZA_ID);
                        editorAutoriza.putInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO", 0);
                        editorAutoriza.putInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                        editorAutoriza.apply();
                        break;
                    case  MODULO_6 :
                        editorAutoriza.putInt("MODULO_6_TIPO_AUTORIZACION", AUTORIZA_ID);
                        editorAutoriza.putInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO", 0);
                        editorAutoriza.putInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                        editorAutoriza.apply();
                        break;
                    case  MODULO_7 :
                        editorAutoriza.putInt("MODULO_7_TIPO_AUTORIZACION", AUTORIZA_ID);
                        editorAutoriza.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO", 0);
                        editorAutoriza.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                        editorAutoriza.apply();
                        break;
                };
                loadingProgress(progressDialog, 0);
                dismiss();
                ProviderAutorizaModulo.getInstance(getContext()).autorizaModulo(mdId, usuarioId, moduloSeleccionado, VALIDACION_AUTORIZA, 0, binding.comentario.getText().toString(), new ProviderAutorizaModulo.AutorizaModulo() {
                    @Override
                    public void resolve(AutorizaResponse autorizaResponse) {
                        if(autorizaResponse.getCodigo() == 200) {
                            listener.onModuloAceptado(moduloSeleccionado);
                            dismiss();
                            loadingProgress(progressDialog, 1);
                        } else {
                            Toast.makeText(getContext(), "Error al autorizar el módulo: " + autorizaResponse.getMensaje(),
                                    Toast.LENGTH_LONG).show();
                            loadingProgress(progressDialog, 1);
                        }
                    }

                    @Override
                    public void reject(Exception e) {
                        loadingProgress(progressDialog, 1);
                        Toast.makeText(getContext(), "Error al conectarse al servicio que autoriza/rechaza la pantalla: ",
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    Boolean permisoComentarP1 = true;
    Boolean permisoComentarP2 = true;
    Boolean permisoComentarP3 = true;
    Boolean permisoComentarP4 = true;
    Boolean permisoComentarP5 = true;
    Boolean permisoComentarP6 = true;
    Boolean permisoComentarP7 = true;

    private final int MODULO_PANTALLA_1 = 1;
    private final int MODULO_PANTALLA_2 = 2;
    private final int MODULO_PANTALLA_3 = 3;
    private final int MODULO_PANTALLA_4 = 4;
    private final int MODULO_PANTALLA_5 = 5;
    private final int MODULO_PANTALLA_6 = 6;
    private final int MODULO_PANTALLA_7 = 7;
    private final int MODULO_ASIGNADAS = 8;


    public void setPermisosComentar(String tipoPantalla){
        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("permisos", null);
        Type type = new TypeToken<ArrayList<Permiso>>() {}.getType();
        ArrayList<Permiso> permisos = gson.fromJson(json, type);

        if(permisos!=null){
            for(int i=0;i<permisos.size();i++){
                if(permisos.get(i).getFimoduloid()==MODULO_ASIGNADAS){
                    if(tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_1))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermitecomentar()==1){
                        permisoComentarP1 = true;
                        break;
                    }else if(tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_2))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermitecomentar()==1){
                        permisoComentarP2 = true;
                        break;
                    }else if(tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_3))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermitecomentar()==1){
                        permisoComentarP3 = true;
                        break;
                    }else if(tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_4))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermitecomentar()==1){
                        permisoComentarP4 = true;
                        break;
                    }else if(tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_5))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermitecomentar()==1){
                        permisoComentarP5 = true;
                        break;
                    }else if(tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_6))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermitecomentar()==1){
                        permisoComentarP6 = true;
                        break;
                    }else if(tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_7))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermitecomentar()==1){
                        permisoComentarP7 = true;
                    }
                }
            }
        }
    }

}
