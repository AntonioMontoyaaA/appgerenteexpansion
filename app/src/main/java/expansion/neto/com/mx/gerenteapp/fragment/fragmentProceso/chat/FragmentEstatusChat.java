package expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentChatEstatusBinding;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatGuardaProceso;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.chat.ProviderChatProcesoEstatus;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.chat.ProviderGuardaMensaje;
import expansion.neto.com.mx.gerenteapp.sorted.proceso.adapter.MensajeChatAdapter;

public class FragmentEstatusChat extends Fragment {

    SharedPreferences preferences = null;
    private View view = null;
    private String mdId = null;
    private FragmentChatEstatusBinding binding;

    List<ChatProceso.MensajeChat> listaMensajes = null;
    private int areaSeleccionada = 0;

    private final String CATEGORIA_A = "A";
    private final String CATEGORIA_B = "B";
    private final String CATEGORIA_C = "C";

    private final int AREA_CONSULTA_GENERAL = 0;
    private final int AREA_CONSULTA_GERENTE = 111;
    private final int AREA_CONSULTA_EXPANSION = 1;
    private final int AREA_CONSULTA_GESTORIA = 2;
    private final int AREA_CONSULTA_CONSTRUCCION = 3;
    private final int AREA_CONSULTA_OPERACIONES = 5;
    private final int AREA_CONSULTA_AUDITORIA = 4;


    private static final int TIPO_COMENTARIO_CHAT_GRAL = 1;

    private RecyclerView mMessageRecycler;
    private MensajeChatAdapter mMessageAdapter;
    int index;
    public static FragmentEstatusChat newInstance() {
        FragmentEstatusChat fragmentChat = new FragmentEstatusChat();
        Bundle args = new Bundle();
        fragmentChat.setArguments(args);
        return fragmentChat;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat_estatus,container,false);
        view = binding.getRoot();

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String idEstatus = preferences.getString("estatusId", "");
        String numero = preferences.getString("num", "");

        binding.buttonChat.setEnabled(false);
        binding.chat.setVisibility(View.VISIBLE);

        Resources resource = getContext().getResources();

        if(numero.equals("0")){
            binding.image.setText("");
            binding.image.setBackgroundResource(R.drawable.general_chat);
        }else{
            binding.image.setBackgroundResource(R.drawable.rounded_boton);
            binding.image.setText(numero+"");

        }

        binding.backFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chat.setVisibility(View.GONE);
                FragmentGruposBack fragment = new FragmentGruposBack();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.body, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

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

                            mMessageRecycler.removeAllViews();
                            mMessageAdapter = new MensajeChatAdapter(getContext(), listaMensajes);
                            mMessageRecycler.setAdapter(mMessageAdapter);
                            mMessageRecycler.scrollToPosition(listaMensajes.size() - 1);
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

    public void consultaChatPorArea(int areaId) {
        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final String usuarioId = preferences.getString("usuario","");
        ProviderChatProcesoEstatus.getInstance(getContext()).obtenerChatProcesoEstatus(
                mdId, areaId, usuarioId ,  new ProviderChatProcesoEstatus.ConsultaChatProceso() {
            @Override
            public void resolve(ChatProceso chat) {
                if(chat.getCodigo() == 200) {

                    listaMensajes = chat.getComentarios();
                    mMessageRecycler = binding.reyclerviewMessageList;
                    mMessageAdapter = new MensajeChatAdapter(getContext(), listaMensajes);
                    mMessageRecycler.setAdapter(mMessageAdapter);
                    mMessageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));



                } else {
                    Toast.makeText(getContext(), "Error al cargar los datos",
                            Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void reject(Exception e) { }
        });
    }
}
