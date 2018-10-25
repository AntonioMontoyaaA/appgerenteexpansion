package expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.chat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

import static expansion.neto.com.mx.gerenteapp.fragment.fragmentDetalle.FragmentDetalle.loadingProgress;

public class FragmentEstatusChatNotificaciones extends Fragment {

    SharedPreferences preferences = null;
    private View view = null;
    private String mdId = null;
    private FragmentChatEstatusNotificacionesBinding binding;

    List<ChatProceso.MensajeChat> listaMensajes = null;

    private static final int TIPO_COMENTARIO_CHAT_GRAL = 1;


    private MensajeChatAdapter mMessageAdapter;
    public static FragmentEstatusChatNotificaciones newInstance() {
        FragmentEstatusChatNotificaciones fragmentChat = new FragmentEstatusChatNotificaciones();
        Bundle args = new Bundle();
        fragmentChat.setArguments(args);
        return fragmentChat;
    }


    @Override
    public void onResume(){
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragent based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat_estatus_notificaciones,container,false);
        view = binding.getRoot();
        binding.edittextChatbox.requestFocus();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String idEstatus = preferences.getString("estatusId", "");
        String numero = preferences.getString("num", "");
        binding.tol.agregar.setVisibility(View.GONE);
        binding.tol.nombreTitulo.setText(getString(R.string.chat));

        binding.tol.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityNotificaciones.class);
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
                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                final String usuarioId = preferences.getString("usuario","");
                final String status = preferences.getString("estatusId", "");
                ProviderGuardaMensaje.getInstance(getContext()).guardarChatProceso(mdId, binding.edittextChatbox.getText().toString(), usuarioId, Integer.parseInt(status), new ProviderGuardaMensaje.GuardaMensajeChatProceso() {
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
                            mMessageAdapter = new MensajeChatAdapter(getContext(), listaMensajes);
                            binding.reyclerviewMessageList.setAdapter(mMessageAdapter);
                            binding.reyclerviewMessageList.scrollToPosition(listaMensajes.size() - 1);
                            binding.edittextChatbox.setText("");
                        } else {
                            Toast.makeText(getContext(), "Error al cargar los datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void reject(Exception e) { }
                });
            }
        });

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        mdId = preferences.getString("mdIdterminar","");
        String nombreEstatus = preferences.getString("estatusNombre","");
        binding.nombreEstatus.setText(nombreEstatus+"");
        consultaChatPorArea(Integer.parseInt(idEstatus));
        return view;
    }
    ProgressDialog progressDialog;

    public void consultaChatPorArea(int areaId) {
        progressDialog = new ProgressDialog(getContext());
        loadingProgress(progressDialog, 0);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final String usuarioId = preferences.getString("usuario","");
        ProviderChatProcesoEstatus.getInstance(getContext()).obtenerChatProcesoEstatus(
                mdId, areaId, usuarioId ,  new ProviderChatProcesoEstatus.ConsultaChatProceso() {
            @Override
            public void resolve(ChatProceso chat) {
                if(chat.getCodigo() == 200) {
                    loadingProgress(progressDialog, 1);

                    listaMensajes = chat.getComentarios();
                    mMessageAdapter = new MensajeChatAdapter(getContext(), listaMensajes);
                    binding.reyclerviewMessageList.setAdapter(mMessageAdapter);
                    binding.reyclerviewMessageList.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.reyclerviewMessageList.scrollToPosition(listaMensajes.size() - 1);



                } else {
                    loadingProgress(progressDialog, 1);

                    Toast.makeText(getContext(), "Error al cargar los datos",
                            Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void reject(Exception e) { }
        });
    }
}
