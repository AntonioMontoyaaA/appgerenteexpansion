package expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.chat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentChatEstatusNotificacionesBinding;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatGuardaProceso;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.chat.ProviderChatProcesoEstatus;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.chat.ProviderGuardaMensaje;
import expansion.neto.com.mx.gerenteapp.sorted.proceso.adapter.MensajeChatAdapter;
import expansion.neto.com.mx.gerenteapp.ui.agenda.ActivityNotificaciones;

import static expansion.neto.com.mx.gerenteapp.utils.Util.loadingProgress;


public class FragmentEstatusChatNotificaciones extends AppCompatActivity {

    SharedPreferences preferences = null;
    private View view = null;
    private String mdId = null;
    private FragmentChatEstatusNotificacionesBinding binding;

    List<ChatProceso.MensajeChat> listaMensajes = null;

    private static final int TIPO_COMENTARIO_CHAT_GRAL = 1;





    private MensajeChatAdapter mMessageAdapter;
//    public static FragmentEstatusChatNotificaciones newInstance() {
//        FragmentEstatusChatNotificaciones fragmentChat = new FragmentEstatusChatNotificaciones();
//        Bundle args = new Bundle();
//        fragmentChat.setArguments(args);
//        return fragmentChat;
//    }

//    @Override
//    public void onResume(){
//        super.onResume();
//
//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        binding  = DataBindingUtil.setContentView(this, R.layout.fragment_chat_estatus_notificaciones);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String idEstatus = preferences.getString("estatusId", "");
        String numero = preferences.getString("num", "");
        binding.tol.agregar.setVisibility(View.INVISIBLE);
        binding.tol.nombreTitulo.setText(getString(R.string.chat));

        binding.tol.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentEstatusChatNotificaciones.this, ActivityNotificaciones.class);
                startActivity(intent);
            }
        });


        binding.buttonChat.setEnabled(false);
        binding.chat.setVisibility(View.VISIBLE);

        if(numero.equals("0")){
            binding.image.setText("");
            binding.image.setBackgroundResource(R.drawable.general_chat);
        }else{
            binding.image.setBackgroundResource(R.drawable.rounded_boton);
            binding.image.setText(numero+"");

        }

        binding.edittextChatbox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.edittextChatbox.getText().toString().length() > 0) {
                    binding.buttonChatboxSend.setAlpha(1.0f);
                    binding.buttonChatboxSend.setEnabled(true);
                    binding.buttonChat.setEnabled(true);
                } else{
                    binding.buttonChat.setEnabled(false);
                    binding.buttonChatboxSend.setAlpha(0.3f);
                    binding.buttonChatboxSend.setEnabled(false);
                }
            }
        });

        binding.buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.buttonChat.setEnabled(false);
                preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                final String usuarioId = preferences.getString("usuario","");
                final String status = preferences.getString("estatusId", "");
                ProviderGuardaMensaje.getInstance(FragmentEstatusChatNotificaciones.this).guardarChatProceso(mdId, binding.edittextChatbox.getText().toString(), usuarioId, Integer.parseInt(status), new ProviderGuardaMensaje.GuardaMensajeChatProceso() {
                    @Override
                    public void resolve(ChatGuardaProceso chat) {
                        if(chat.getCodigo() == 200) {
                            ChatProceso.MensajeChat mensaje = new ChatProceso.MensajeChat();
                            mensaje.setComentario(binding.edittextChatbox.getText().toString());
                            mensaje.setTipoComentario(TIPO_COMENTARIO_CHAT_GRAL);
                            mensaje.setUsuarioId(Integer.parseInt(usuarioId));
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            mensaje.setFecha(sdf.format(new Date()));
                            listaMensajes.add(mensaje);

                            binding.reyclerviewMessageList.removeAllViews();
                            mMessageAdapter = new MensajeChatAdapter(FragmentEstatusChatNotificaciones.this, listaMensajes);
                            binding.reyclerviewMessageList.setAdapter(mMessageAdapter);
                            binding.reyclerviewMessageList.scrollToPosition(listaMensajes.size() - 1);
                            binding.edittextChatbox.setText("");
                        } else {
                            Toast.makeText(FragmentEstatusChatNotificaciones.this, "Error al cargar los datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void reject(Exception e) { }
                });
            }
        });

        preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        mdId = preferences.getString("mdIdterminar","");
        String nombreEstatus = preferences.getString("estatusNombre","");
        binding.nombreEstatus.setText(nombreEstatus+"");
        consultaChatPorArea(Integer.parseInt(idEstatus));
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    // Inflate the view for the fragment based on layout XML
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat_estatus_notificaciones,container,false);
//        view = binding.getRoot();
//
//
//
    ;
    //        return view;
//    }
    ProgressDialog progressDialog;
    public void consultaChatPorArea(int areaId) {

        progressDialog = new ProgressDialog(FragmentEstatusChatNotificaciones.this);

        loadingProgress(progressDialog, 0);
        preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final String usuarioId = preferences.getString("usuario","");
        ProviderChatProcesoEstatus.getInstance(FragmentEstatusChatNotificaciones.this).obtenerChatProcesoEstatus(
                mdId, areaId, usuarioId ,  new ProviderChatProcesoEstatus.ConsultaChatProceso() {
                    @Override
                    public void resolve(ChatProceso chat) {
                        if(chat.getCodigo() == 200) {
                            loadingProgress(progressDialog, 1);

                            listaMensajes = chat.getComentarios();
                            mMessageAdapter = new MensajeChatAdapter(FragmentEstatusChatNotificaciones.this, listaMensajes);
                            binding.reyclerviewMessageList.setAdapter(mMessageAdapter);
                            binding.reyclerviewMessageList.setLayoutManager(new LinearLayoutManager(FragmentEstatusChatNotificaciones.this));
                            binding.reyclerviewMessageList.scrollToPosition(listaMensajes.size() - 1);


                        } else {
                            loadingProgress(progressDialog, 1);

                            Toast.makeText(FragmentEstatusChatNotificaciones.this, "Error al cargar los datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void reject(Exception e) {                     loadingProgress(progressDialog, 1);
                    }
                });
    }
}
