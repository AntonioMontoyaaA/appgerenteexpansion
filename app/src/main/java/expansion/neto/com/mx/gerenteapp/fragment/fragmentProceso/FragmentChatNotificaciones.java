package expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import expansion.neto.com.mx.gerenteapp.databinding.FragmentChatNotificacionBinding;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatGuardaProceso;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderChatProceso;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderGuardaMensaje;
import expansion.neto.com.mx.gerenteapp.sorted.proceso.adapter.MensajeChatAdapter;
import expansion.neto.com.mx.gerenteapp.ui.agenda.ActivityNotificaciones;

import static android.widget.LinearLayout.OnClickListener;

public class FragmentChatNotificaciones extends Fragment {

    SharedPreferences preferences = null;
    private View view = null;
    private String mdId = null;
    private FragmentChatNotificacionBinding binding;

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
    private static int tipoChats = 0;

    private RecyclerView mMessageRecycler;
    private MensajeChatAdapter mMessageAdapter;
    int index;
    public static FragmentChatNotificaciones newInstance() {
        FragmentChatNotificaciones fragmentChat = new FragmentChatNotificaciones();
        Bundle args = new Bundle();
        fragmentChat.setArguments(args);
        tipoChats = args.getInt("index", 0);
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
        final Resources resource = getContext().getResources();

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat_notificacion,container,false);
        view = binding.getRoot();
        binding.buttonChat.setEnabled(false);
        binding.tol.guardar.setVisibility(View.GONE);
        binding.tol.nombreTitulo.setText(getText(R.string.chat));

        binding.tol.back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                startActivity(main);
            }
        });


        binding.chatGeneralBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_on));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));
                binding.chatAuditoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.auditoria));


                consultaChatPorArea(AREA_CONSULTA_GENERAL);
                areaSeleccionada = AREA_CONSULTA_GENERAL;
                binding.edittextChatbox.setHint("Escribir mensaje");
            }
        });

        binding.chatAuditoriaBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatAuditoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.auditoriaon));
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));

                consultaChatPorArea(AREA_CONSULTA_AUDITORIA);
                areaSeleccionada = AREA_CONSULTA_AUDITORIA;
                binding.edittextChatbox.setHint("Escribir mensaje");
            }
        });

        binding.chatGexpansionBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_gerente_on));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));
                binding.chatAuditoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.auditoria));

                consultaChatPorArea(AREA_CONSULTA_GERENTE);
                areaSeleccionada = AREA_CONSULTA_GERENTE;
                binding.edittextChatbox.setHint("Escribir mensaje");
            }
        });

        binding.chatExpansionBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_expansion_on));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));
                binding.chatAuditoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.auditoria));

                consultaChatPorArea(AREA_CONSULTA_EXPANSION);
                areaSeleccionada = AREA_CONSULTA_EXPANSION;
                binding.edittextChatbox.setHint("Escribir mensaje a expansión");
            }
        });

        binding.chatGestoriaBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_gestoria_on));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));
                binding.chatAuditoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.auditoria));

                consultaChatPorArea(AREA_CONSULTA_GESTORIA);
                areaSeleccionada = AREA_CONSULTA_GESTORIA;
                binding.edittextChatbox.setHint("Escribir mensaje a gestoría");
            }
        });

        binding.chatConstruccionBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_construccion_on));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));
                binding.chatAuditoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.auditoria));

                consultaChatPorArea(AREA_CONSULTA_CONSTRUCCION);
                areaSeleccionada = AREA_CONSULTA_CONSTRUCCION;
                binding.edittextChatbox.setHint("Escribir mensaje a construcción");
            }
        });

        binding.chatOperacionesBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_operaciones_on));
                binding.chatAuditoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.auditoria));

                consultaChatPorArea(AREA_CONSULTA_OPERACIONES);
                areaSeleccionada = AREA_CONSULTA_OPERACIONES;
                binding.edittextChatbox.setHint("Escribir mensaje a operaciones");
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

        binding.buttonChat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.buttonChat.setEnabled(false);
                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                final String usuarioId = preferences.getString("usuario","");
                ProviderGuardaMensaje.getInstance(getContext()).guardarChatProceso(mdId, binding.edittextChatbox.getText().toString(), usuarioId, areaSeleccionada, new ProviderGuardaMensaje.GuardaMensajeChatProceso() {
                    @Override
                    public void resolve(ChatGuardaProceso chat) {
                        if(chat.getCodigo() == 200) {
                            ChatProceso.MensajeChat mensaje = new ChatProceso.MensajeChat();
                            mensaje.setComentario(binding.edittextChatbox.getText().toString());
                            mensaje.setTipocomentario(TIPO_COMENTARIO_CHAT_GRAL);
                            mensaje.setUsuarioid(Integer.parseInt(usuarioId));
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            mensaje.setFecharegistro(sdf.format(new Date()));
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
        consultaChatPorArea(AREA_CONSULTA_GENERAL);
        return view;
    }

    public void consultaChatPorArea(int areaId) {
        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final String usuarioId = preferences.getString("usuario","");
        ProviderChatProceso.getInstance(getContext()).obtenerChatProceso(mdId, areaId, usuarioId ,  new ProviderChatProceso.ConsultaChatProceso() {
            @Override
            public void resolve(ChatProceso chat) {
                if(chat.getCodigo() == 200) {
                    binding.nombreMdText.setText("MD " + chat.getNombreSitio());
                    binding.creacionMdText.setText("Creada el: " + chat.getFechaCreacion());
                    binding.categoriaText.setText(chat.getCategoria());
                    if(chat.getCategoria().equals(CATEGORIA_B)) {
                        binding.estrella3.setVisibility(View.GONE);
                    } else if(chat.getCategoria().equals(CATEGORIA_C)) {
                        binding.estrella2.setVisibility(View.GONE);
                        binding.estrella3.setVisibility(View.GONE);
                    }
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
