package expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentDialogCancelarBinding;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AutorizaResponse;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.MotivosRechazo;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderAutorizaModulo;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderMotivosRechazo;

import static expansion.neto.com.mx.gerenteapp.utils.Util.loadingProgress;

/**
 *
 */

public class FragmentDialogCancelar extends DialogFragment {

    FragmentDialogCancelarBinding binding;
    SharedPreferences preferences;
    List<String> listaDescripciones = new ArrayList<String>();
    List<MotivoVO> listaMotivos = new ArrayList<MotivoVO>();
    private int motivoSeleccionadoId = 0;
    private int rechazoDefinitivo = 0;
    private int moduloSeleccionado = 0;
    ProgressDialog progressDialog;

    private final int MODULO_1 = 1;
    private final int MODULO_2 = 2;
    private final int MODULO_3 = 3;
    private final int MODULO_4 = 4;
    private final int MODULO_5 = 5;
    private final int MODULO_6 = 6;
    private final int MODULO_7 = 7;
    private final int MODULO_GENERAL = 0;

    OnModuloRechazadoListener listener;

    public interface OnModuloRechazadoListener {
        public void onModuloRechazado(int modulo);
    }

    private final int RECHAZA_ID = 2;
    private final int VALIDACION_RECHAZA = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_cancelar,container,false);
        View view = binding.getRoot();
        progressDialog = new ProgressDialog(getContext(), R.style.AppCompatAlertDialogStyle);
        binding.aceptar.setEnabled(false);
        binding.aceptar.setAlpha(.4f);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        moduloSeleccionado = preferences.getInt("moduloAutorizaRechaza",0);

        ProviderMotivosRechazo.getInstance(getContext()).obtenerMotivosRechazo(String.valueOf(moduloSeleccionado), new ProviderMotivosRechazo.ConsultaMotivosRechazo() {
            @Override
            public void resolve(MotivosRechazo motivosRechazo) {

                if(motivosRechazo.getCodigo() == 200) {

                    Spinner spinnerMotivos = binding.motivosRechazoSpinner;
                    if(motivosRechazo.getMotivos() != null && motivosRechazo.getMotivos().size() > 0) {
                        listaDescripciones.add(":: SELECCIONA UN MOTIVO ::");
                        for(int i = 0; i < motivosRechazo.getMotivos().size(); i++) {
                            listaDescripciones.add(motivosRechazo.getMotivos().get(i).getDescripcion());
                            listaMotivos.add(new MotivoVO(motivosRechazo.getMotivos().get(i).getMotivoId(),
                                    motivosRechazo.getMotivos().get(i).getDescripcion(),
                                    motivosRechazo.getMotivos().get(i).getRechazoDefinitvo()));
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_spinner_item, listaDescripciones) ;
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerMotivos.setAdapter(dataAdapter);
                        dataAdapter.notifyDataSetChanged();

                        spinnerMotivos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                if(i != 0) {
                                    motivoSeleccionadoId = listaMotivos.get(i-1).getIdMotivo();
                                    rechazoDefinitivo = listaMotivos.get(i-1).getRechazoDefinitivo();
                                    binding.aceptar.setEnabled(true);
                                    binding.aceptar.setAlpha(1);
                                }else{
                                    motivoSeleccionadoId = 0;
                                    rechazoDefinitivo = 0;
                                    binding.aceptar.setEnabled(false);
                                    binding.aceptar.setAlpha(.4f);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }

                }else{
                    Toast.makeText(getContext(), "Error al cargar los datos. Codigo: " + motivosRechazo.getCodigo() + " mensaje: " + motivosRechazo.getMensaje(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });

        return view;
    }

    public void setModuloCanceladoListener(OnModuloRechazadoListener listener) {
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

                if(motivoSeleccionadoId == 0) {
                    binding.mensajesErrores.setText("Debes seleccionar un motivo");
                } else if(binding.comentario.getText().toString().equals("")) {
                    binding.mensajesErrores.setText("Debes introducir un comentario");
                } else {
                    loadingProgress(progressDialog, 0);
                    dismiss();
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorRechazos = preferences.edit();

                    switch(moduloSeleccionado) {
                        case  MODULO_1 :
                            editorRechazos.putInt("MODULO_1_TIPO_AUTORIZACION", RECHAZA_ID);
                            editorRechazos.putInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO", motivoSeleccionadoId);
                            editorRechazos.putInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", rechazoDefinitivo);
                            editorRechazos.apply();
                            break;
                        case  MODULO_2 :
                            editorRechazos.putInt("MODULO_2_TIPO_AUTORIZACION", RECHAZA_ID);
                            editorRechazos.putInt("MODULO_2_TIPO_AUTORIZACION_MOTIVO", motivoSeleccionadoId);
                            editorRechazos.putInt("MODULO_2_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", rechazoDefinitivo);
                            editorRechazos.apply();
                            break;
                        case  MODULO_3 :
                            editorRechazos.putInt("MODULO_3_TIPO_AUTORIZACION", RECHAZA_ID);
                            editorRechazos.putInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO", motivoSeleccionadoId);
                            editorRechazos.putInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", rechazoDefinitivo);
                            editorRechazos.apply();
                            break;
                        case  MODULO_4 :
                            editorRechazos.putInt("MODULO_4_TIPO_AUTORIZACION", RECHAZA_ID);
                            editorRechazos.putInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO", motivoSeleccionadoId);
                            editorRechazos.putInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", rechazoDefinitivo);
                            editorRechazos.apply();
                            break;
                        case  MODULO_5 :
                            editorRechazos.putInt("MODULO_5_TIPO_AUTORIZACION", RECHAZA_ID);
                            editorRechazos.putInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO", motivoSeleccionadoId);
                            editorRechazos.putInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", rechazoDefinitivo);
                            editorRechazos.apply();
                            break;
                        case  MODULO_6 :
                            editorRechazos.putInt("MODULO_6_TIPO_AUTORIZACION", RECHAZA_ID);
                            editorRechazos.putInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO", motivoSeleccionadoId);
                            editorRechazos.putInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", rechazoDefinitivo);
                            editorRechazos.apply();
                            break;
                        case  MODULO_7 :
                            editorRechazos.putInt("MODULO_7_TIPO_AUTORIZACION", RECHAZA_ID);
                            editorRechazos.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO", motivoSeleccionadoId);
                            editorRechazos.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", rechazoDefinitivo);
                            editorRechazos.apply();
                            break;
                        case MODULO_GENERAL :
                            editorRechazos.putInt("MODULO_GENERAL_TIPO_AUTORIZACION", RECHAZA_ID);
                            editorRechazos.putInt("MODULO_GENERAL_TIPO_AUTORIZACION_MOTIVO", motivoSeleccionadoId);
                            editorRechazos.putString("MODULO_GENERAL_COMENTARIO", binding.comentario.getText().toString());
                            editorRechazos.apply();
                            break;
                    };

                    ProviderAutorizaModulo.getInstance(getContext()).autorizaModulo(mdId, usuarioId, moduloSeleccionado, VALIDACION_RECHAZA, motivoSeleccionadoId, binding.comentario.getText().toString(), new ProviderAutorizaModulo.AutorizaModulo() {
                        @Override
                        public void resolve(AutorizaResponse autorizaResponse) {
                            if(autorizaResponse.getCodigo() == 200) {
                                listener.onModuloRechazado(moduloSeleccionado);
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
                            Toast.makeText(getContext(), "Error al conectarse al servicio que autoriza/rechaza la pantalla: ",
                                    Toast.LENGTH_LONG).show();
                            loadingProgress(progressDialog, 1);

                        }
                    });
                }
            }
        });
    }

    public class MotivoVO {
        public MotivoVO(int idMotivo, String descripcion, int rechazoDefinitivo) {
            this.idMotivo = idMotivo;
            this.descripcion = descripcion;
            this.rechazoDefinitivo = rechazoDefinitivo;
        }
        private int idMotivo;
        private String descripcion;
        private int rechazoDefinitivo;

        public int getIdMotivo() {
            return idMotivo;
        }

        public void setIdMotivo(int idMotivo) {
            this.idMotivo = idMotivo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public int getRechazoDefinitivo() {
            return rechazoDefinitivo;
        }

        public void setRechazoDefinitivo(int rechazoDefinitivo) {
            this.rechazoDefinitivo = rechazoDefinitivo;
        }
    }
}
