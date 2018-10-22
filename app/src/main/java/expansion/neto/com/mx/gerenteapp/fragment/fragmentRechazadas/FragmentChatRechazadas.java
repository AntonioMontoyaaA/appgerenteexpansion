package expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazadas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.databinding.FragmentChatRechazadasBinding;
import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatGuardaProceso;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.TiemposProceso;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderChatProceso;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderGuardaMensaje;
import expansion.neto.com.mx.gerenteapp.sorted.proceso.adapter.MensajeChatAdapter;

public class FragmentChatRechazadas extends Fragment {

    SharedPreferences preferences = null;
    private View view = null;
    private String mdId = null;
    private FragmentChatRechazadasBinding binding;

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

    private static int tipoPantallas = 0;
    private static final int PANTALLA_EN_PROCESO = 1;
    private static final int PANTALLA_RECHAZADAS = 2;

    private static final int TIPO_COMENTARIO_CHAT_GRAL = 1;
    private final int AREA_CONSULTA_AUDITORIA = 4;


    private RecyclerView mMessageRecycler;
    private MensajeChatAdapter mMessageAdapter;

    public static FragmentChatRechazadas newInstance(int tipoPantalla) {
        FragmentChatRechazadas fragmentChat = new FragmentChatRechazadas();
        Bundle args = new Bundle();
        fragmentChat.setArguments(args);
        tipoPantallas = tipoPantalla;
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

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chat_rechazadas,container,false);
        view = binding.getRoot();

        binding.content2.viewge.setAlpha(1);
        binding.content2.imggerente.setAlpha(1.0f);
        binding.content2.txtgexpansion.setAlpha(1.0f);

        binding.content2.gexpansion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.content2.viewge.setAlpha(1);
                binding.content2.imggerente.setAlpha(1.0f);
                binding.content2.txtgexpansion.setAlpha(1.0f);

                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imggestoria.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);

                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);

                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);

                consultaChatPorArea(AREA_CONSULTA_GENERAL);
                areaSeleccionada = AREA_CONSULTA_GENERAL;
                binding.edittextChatbox.setHint("Escribir mensaje");

            }
        });

        binding.content2.expansiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                consultaChatPorArea(AREA_CONSULTA_EXPANSION);
                areaSeleccionada = AREA_CONSULTA_EXPANSION;
                binding.edittextChatbox.setHint("Escribir mensaje a expansión");

                binding.content2.viewe.setAlpha(1);
                binding.content2.imgexpansion.setAlpha(1.0f);
                binding.content2.txtexpansion.setAlpha(1.0f);

                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imggestoria.setAlpha(0.2f);
                binding.content2.imggerente.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);


                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);

            }
        });

        binding.content2.gestoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.content2.viewges.setAlpha(1);
                binding.content2.imggestoria.setAlpha(1.0f);
                binding.content2.txtgestoria.setAlpha(1.0f);

                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);
                binding.content2.imggerente.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);

                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);
                consultaChatPorArea(AREA_CONSULTA_GESTORIA);
                areaSeleccionada = AREA_CONSULTA_GESTORIA;
                binding.edittextChatbox.setHint("Escribir mensaje a gestoría");

            }
        });

        binding.content2.construccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                consultaChatPorArea(AREA_CONSULTA_CONSTRUCCION);
                areaSeleccionada = AREA_CONSULTA_CONSTRUCCION;
                binding.edittextChatbox.setHint("Escribir mensaje a construcción");

                binding.content2.viewcon.setAlpha(1);
                binding.content2.imgconstruccion.setAlpha(1.0f);
                binding.content2.txtconstruccion.setAlpha(1.0f);

                binding.content2.imggestoria.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);
                binding.content2.imggerente.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);

                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);

            }
        });

        binding.content2.operaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                consultaChatPorArea(AREA_CONSULTA_OPERACIONES);
                areaSeleccionada = AREA_CONSULTA_OPERACIONES;
                binding.edittextChatbox.setHint("Escribir mensaje a operaciones");

                binding.content2.viewope.setAlpha(1);
                binding.content2.imgoperaciones.setAlpha(1.0f);
                binding.content2.txtoperaciones.setAlpha(1.0f);

                binding.content2.imggestoria.setAlpha(0.2f);
                binding.content2.imgauditoria.setAlpha(0.2f);
                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);
                binding.content2.imggerente.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewaudi.setAlpha(0.2f);

                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);
                binding.content2.txtauditoria.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);

            }
        });

        binding.content2.auditorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.content2.viewaudi.setAlpha(1);
                binding.content2.imgauditoria.setAlpha(1.0f);
                binding.content2.txtauditoria.setAlpha(1.0f);

                binding.content2.imggerente.setAlpha(0.2f);
                binding.content2.imgoperaciones.setAlpha(0.2f);
                binding.content2.imgconstruccion.setAlpha(0.2f);
                binding.content2.imgexpansion.setAlpha(0.2f);
                binding.content2.imggestoria.setAlpha(0.2f);

                binding.content2.viewge.setAlpha(0.2f);
                binding.content2.viewe.setAlpha(0.2f);
                binding.content2.viewges.setAlpha(0.2f);
                binding.content2.viewcon.setAlpha(0.2f);
                binding.content2.viewope.setAlpha(0.2f);

                binding.content2.txtconstruccion.setAlpha(0.2f);
                binding.content2.txtexpansion.setAlpha(0.2f);
                binding.content2.txtoperaciones.setAlpha(0.2f);
                binding.content2.txtgestoria.setAlpha(0.2f);
                binding.content2.txtgexpansion.setAlpha(0.2f);

                consultaChatPorArea(AREA_CONSULTA_AUDITORIA);
                areaSeleccionada = AREA_CONSULTA_AUDITORIA;
                binding.edittextChatbox.setHint("Escribir mensaje");

            }
        });

        /************************** nuevo version **************************/

        binding.chatGeneralBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_on));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));

                consultaChatPorArea(AREA_CONSULTA_GENERAL);
                areaSeleccionada = AREA_CONSULTA_GENERAL;
                binding.edittextChatbox.setHint(R.string.escribir);
            }
        });

        binding.chatAuditoriaBtn.setOnClickListener(new View.OnClickListener() {
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
                binding.edittextChatbox.setHint(R.string.escribir);
            }
        });

        binding.chatGexpansionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_gerente_on));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));

                consultaChatPorArea(AREA_CONSULTA_GERENTE);
                areaSeleccionada = AREA_CONSULTA_GERENTE;
                binding.edittextChatbox.setHint(R.string.escribir);
            }
        });

        binding.chatExpansionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_expansion_on));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));

                consultaChatPorArea(AREA_CONSULTA_EXPANSION);
                areaSeleccionada = AREA_CONSULTA_EXPANSION;
                binding.edittextChatbox.setHint(R.string.escribir_e);
            }
        });

        binding.chatGestoriaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_gestoria_on));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));

                consultaChatPorArea(AREA_CONSULTA_GESTORIA);
                areaSeleccionada = AREA_CONSULTA_GESTORIA;
                binding.edittextChatbox.setHint(R.string.escribir_g);
            }
        });

        binding.chatConstruccionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_construccion_on));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));

                consultaChatPorArea(AREA_CONSULTA_CONSTRUCCION);
                areaSeleccionada = AREA_CONSULTA_CONSTRUCCION;
                binding.edittextChatbox.setHint(R.string.escribir_c);
            }
        });

        binding.chatOperacionesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.chatGeneralBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_general_off));
                binding.chatGexpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.gerenteazul));
                binding.chatExpansionBtn.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                binding.chatGestoriaBtn.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                binding.chatConstruccionBtn.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                binding.chatOperacionesBtn.setImageDrawable(resource.getDrawable(R.drawable.ic_operaciones_on));

                consultaChatPorArea(AREA_CONSULTA_OPERACIONES);
                areaSeleccionada = AREA_CONSULTA_OPERACIONES;
                binding.edittextChatbox.setHint(R.string.escribir_o);
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
                } else {
                    binding.buttonChatboxSend.setAlpha(0.3f);
                    binding.buttonChatboxSend.setEnabled(false);
                }
            }
        });

        binding.buttonChatboxSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                final String usuarioId = preferences.getString("usuario","");

                ProviderGuardaMensaje.getInstance(getContext()).guardarChatProceso(mdId, binding.edittextChatbox.getText().toString(), usuarioId, areaSeleccionada, new ProviderGuardaMensaje.GuardaMensajeChatProceso() {
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
                    public void reject(Exception e) {

                    }
                });
            }
        });


        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        mdId = preferences.getString("mdIdterminar","");
        modifica = preferences.getInt("estatusId",0);

        consultaChatPorArea(AREA_CONSULTA_GENERAL);

        if(modifica==4){
            //binding.modifica.setVisibility(View.VISIBLE);
        }


        binding.modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent main = new Intent(getContext(), ActivityDetalleModifica.class);
//                getContext().startActivity(main);
//                getActivity().finish();

            }
        });




        return view;
    }

    private final int AREA_EXPANSION = 1;
    private final int AREA_GESTORIA = 2;
    private final int AREA_CONSTRUCCION = 3;
    private final int AREA_OPERACIONES = 5;
    private final int PERFIL_EXPANSION = 4;
    private final int PERFIL_GERENTE_EXPANSION = 3;

    public void rechazadas(ArrayList<TiemposProceso.Seguimiento> seguimientos){
        if(seguimientos!= null) {
            for (TiemposProceso.Seguimiento seguimiento : seguimientos) {
                if (seguimiento.getAreaId() == AREA_EXPANSION && seguimiento.getPerfil() == PERFIL_GERENTE_EXPANSION) {
                    if(seguimiento.getTieneRechazo() == 1) {
                        //binding.equisGe.setVisibility(View.VISIBLE);
                    } else {
                       // binding.equisGe.setVisibility(View.GONE);
                    }
                } else if(seguimiento.getAreaId() == AREA_EXPANSION && seguimiento.getPerfil() != PERFIL_GERENTE_EXPANSION) {
                    if(seguimiento.getTieneRechazo() == 1) {
                       // binding.equisE.setVisibility(View.VISIBLE);
                    } else {
                       // binding.equisE.setVisibility(View.GONE);
                    }
                } else if(seguimiento.getAreaId() == AREA_GESTORIA) {
                    if(seguimiento.getTieneRechazo() == 1) {
                       // binding.equisG.setVisibility(View.VISIBLE);
                    } else {
                       // binding.equisG.setVisibility(View.GONE);
                    }
                } else if(seguimiento.getAreaId() == AREA_CONSTRUCCION) {
                    if(seguimiento.getTieneRechazo() == 1) {
                       // binding.equisC.setVisibility(View.VISIBLE);
                    } else {
                       // binding.equisC.setVisibility(View.GONE);
                    }
                } else if(seguimiento.getAreaId() == AREA_OPERACIONES) {
                    if(seguimiento.getTieneRechazo() == 1) {
                       // binding.equisO.setVisibility(View.VISIBLE);
                    } else {
                       // binding.equisO.setVisibility(View.GONE);
                    }
                }
            }
        }
    }



    int modifica;
    public void consultaChatPorArea(int areaId) {
        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final String usuarioId = preferences.getString("usuario","");
        ProviderChatProceso.getInstance(getContext()).obtenerChatProceso(mdId, areaId,usuarioId, new ProviderChatProceso.ConsultaChatProceso() {
            @Override
            public void resolve(ChatProceso chat) {

                if(chat.getCodigo() == 200) {
                    if(tipoPantallas == PANTALLA_EN_PROCESO) {
                        binding.linearMotivoRechazo.setVisibility(View.GONE);
                    } else if(tipoPantallas == PANTALLA_RECHAZADAS && modifica!=4) {
                        binding.linearMotivoRechazo.setVisibility(View.VISIBLE);
                        binding.motivoRechazoGeneral.setText(chat.getMtvRechazo());
                        ViewGroup.LayoutParams params = binding.reyclerviewMessageList.getLayoutParams();
                        final float scale = getContext().getResources().getDisplayMetrics().density;
                        int pixels = (int) (402 * scale + 0.5f);
                        params.height=pixels;
                        binding.reyclerviewMessageList.setLayoutParams(params);
                    } else if(modifica==4){
                        binding.linearMotivoRechazo.setVisibility(View.VISIBLE);
                        binding.motivoRechazoGeneral.setText(chat.getMtvRechazo());
                        ViewGroup.LayoutParams params = binding.reyclerviewMessageList.getLayoutParams();
                        final float scale = getContext().getResources().getDisplayMetrics().density;
                        int pixels = (int) (374 * scale + 0.5f);
                        params.height=pixels;
                        binding.reyclerviewMessageList.setLayoutParams(params);
                    }

                    binding.nombreMdText.setText(chat.getNombreSitio());
                    binding.creacionMdText.setText(getString(R.string.creation) + chat.getFechaCreacion());
                    binding.categoriaText.setText(chat.getCategoria());

                    if(chat.getCategoria().equals(CATEGORIA_B)) {
                        binding.estrella3.setVisibility(View.GONE);
                    } else if(chat.getCategoria().equals(CATEGORIA_C)){
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


//                if(chat.getComentarios() != null && chat.getComentarios().size() > 0) {
//                    for (int i=0;i<chat.getComentarios().size();i++) {
//                        if(Integer.valueOf(chat.getComentarios().get(i).getCorreccionesMD())>Integer.valueOf(chat.getCorreccionesMaximas())){
//                            Toast.makeText(getContext(), getString(R.string.correciones),
//                                    Toast.LENGTH_LONG).show();
//                            binding.modifica.setEnabled(false);
//                            binding.modifica.setAlpha(0.3f);
//                        }else{
//                            binding.modifica.setEnabled(true);
//                            binding.modifica.setAlpha(1.0f);
//                        }
//                    }
//                }

            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                String json = preferences.getString("seguimientos", null);
                Type type = new TypeToken<ArrayList<TiemposProceso.Seguimiento>>() {}.getType();
                ArrayList<TiemposProceso.Seguimiento> seguimientos = gson.fromJson(json, type);
                if(seguimientos!=null && seguimientos.size()>0){
                    rechazadas(seguimientos);
                }
            }
        }, 2000);

    }

}