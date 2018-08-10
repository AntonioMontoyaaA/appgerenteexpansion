package expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazo.FragmentInicioRechazadas;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.TiemposProceso;
import expansion.neto.com.mx.gerenteapp.provider.procesoProvider.ProviderTiemposProceso;

import expansion.neto.com.mx.gerenteapp.databinding.FragmentTiemposBinding;
import expansion.neto.com.mx.gerenteapp.ui.detalle.ActivityDetalle;

public class FragmentTiempos extends Fragment  {

    SharedPreferences preferences = null;
    private View view = null;

    private final String CATEGORIA_A = "A";
    private final String CATEGORIA_B = "B";
    private final String CATEGORIA_C = "C";

    private final int AREA_EXPANSION = 1;
    private final int AREA_GESTORIA = 2;
    private final int AREA_CONSTRUCCION = 3;
    private final int AREA_AUDITORIA = 4;
    private final int AREA_OPERACIONES = 5;





    private final int PERFIL_GERENTE_EXPANSION = 3;
    private final int PERFIL_EXPANSION = 4;

    private static int tipoPantallas = 0;
    private static final int PANTALLA_EN_PROCESO = 1;
    private static final int PANTALLA_RECHAZADAS = 2;


    public static FragmentTiempos newInstance(int tipoPantalla) {
        FragmentTiempos fragmentTiempos = new FragmentTiempos();
        Bundle args = new Bundle();
        fragmentTiempos.setArguments(args);
        tipoPantallas = tipoPantalla;
        return fragmentTiempos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final FragmentTiemposBinding binding;
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tiempos,container,false);
        view = binding.getRoot();

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdId = preferences.getString("mdId","");
        String usuarioId = preferences.getString("usuario","");

        ProviderTiemposProceso.getInstance(getContext()).obtenerTiemposProceso(mdId, usuarioId, new ProviderTiemposProceso.ConsultaTiemposProceso() {
            @Override
            public void resolve(TiemposProceso tiempos) {

                if(tiempos.getCodigo() == 200) {

                    if(tipoPantallas == PANTALLA_EN_PROCESO) {
                        binding.linearlayout4.setVisibility(View.GONE);
                    } else if(tipoPantallas == PANTALLA_RECHAZADAS) {
                        binding.linearlayout4.setVisibility(View.VISIBLE);
                        binding.motivoRechazoGeneral.setText(tiempos.getMtvRechazo());
                    }

                    binding.nombreMdText.setText(tiempos.getNomsitio());
                    binding.creacionMdText.setText(getString(R.string.creada) + tiempos.getCreacion());
                    binding.categoriaText.setText(tiempos.getCategoria());
                    if(tiempos.getCategoria().equals(CATEGORIA_B)) {
                        binding.estrella3.setVisibility(View.GONE);
                    } else if(tiempos.getCategoria().equals(CATEGORIA_C)) {
                        binding.estrella2.setVisibility(View.GONE);
                        binding.estrella3.setVisibility(View.GONE);
                    }

                    if(tiempos.getSeguimiento() != null && tiempos.getSeguimiento().size() > 0) {

                        for(TiemposProceso.Seguimiento seguimiento : tiempos.getSeguimiento()) {

                            if (seguimiento.getAreaId() == AREA_EXPANSION && seguimiento.getPerfil() == PERFIL_GERENTE_EXPANSION) {
                                binding.fechaLimiteGerenteText.setText(seguimiento.getFechaLimite());
                                binding.fechaAutorizacionGerenteText.setText(seguimiento.getFechaAtencion());
                                binding.tipoGerenteText.setText(seguimiento.getEntiempo());
                                if(seguimiento.getTieneRechazo() == 1 && tipoPantallas == PANTALLA_RECHAZADAS) {
                                    binding.rechazadaGerente.setVisibility(View.VISIBLE);
                                } else {
                                    binding.rechazadaGerente.setVisibility(View.GONE);
                                }

                            } else if(seguimiento.getAreaId() == AREA_EXPANSION && seguimiento.getPerfil() != PERFIL_GERENTE_EXPANSION) {
                                binding.fechaLimiteExpansionText.setText(seguimiento.getFechaLimite());
                                binding.fechaAutorizacionExpansionText.setText(seguimiento.getFechaAtencion());
                                binding.tipoExpansionText.setText(seguimiento.getEntiempo());
                                if(seguimiento.getTieneRechazo() == 1 && tipoPantallas == PANTALLA_RECHAZADAS) {
                                    binding.rechazadaExpansion.setVisibility(View.VISIBLE);
                                } else {
                                    binding.rechazadaExpansion.setVisibility(View.GONE);
                                }
                            } else if(seguimiento.getAreaId() == AREA_GESTORIA) {
                                binding.fechaLimiteGestoriaText.setText(seguimiento.getFechaLimite());
                                binding.fechaAutorizacionGestoriaText.setText(seguimiento.getFechaAtencion());
                                binding.tipoGestoriaText.setText(seguimiento.getEntiempo());
                                if(seguimiento.getTieneRechazo() == 1 && tipoPantallas == PANTALLA_RECHAZADAS) {
                                    binding.rechazadaGestoria.setVisibility(View.VISIBLE);
                                } else {
                                    binding.rechazadaGestoria.setVisibility(View.GONE);
                                }
                            } else if(seguimiento.getAreaId() == AREA_CONSTRUCCION) {
                                binding.fechaLimiteConstruccionText.setText(seguimiento.getFechaLimite());
                                binding.fechaAutorizacionConstruccionText.setText(seguimiento.getFechaAtencion());
                                binding.tipoConstruccionText.setText(seguimiento.getEntiempo());
                                if(seguimiento.getTieneRechazo() == 1 && tipoPantallas == PANTALLA_RECHAZADAS) {
                                    binding.rechazadaConstruccion.setVisibility(View.VISIBLE);
                                } else {
                                    binding.rechazadaConstruccion.setVisibility(View.GONE);
                                }
                            } else if(seguimiento.getAreaId() == AREA_OPERACIONES) {
                                binding.fechaLimiteOperacionesText.setText(seguimiento.getFechaLimite());
                                binding.fechaAutorizacionOperacionesText.setText(seguimiento.getFechaAtencion());
                                binding.tipoOperacionesText.setText(seguimiento.getEntiempo());
                                if(seguimiento.getTieneRechazo() == 1 && tipoPantallas == PANTALLA_RECHAZADAS) {
                                    binding.rechazadaOperaciones.setVisibility(View.VISIBLE);
                                } else {
                                    binding.rechazadaOperaciones.setVisibility(View.GONE);
                                }
                            } else if(seguimiento.getAreaId() == AREA_AUDITORIA) {
                                binding.fechaLimiteAuditoriaText.setText(seguimiento.getFechaLimite());
                                binding.fechaAutorizacionAuditoriaText.setText(seguimiento.getFechaAtencion());
                                binding.tipoAuditoriaText.setText(seguimiento.getEntiempo());
                                if(seguimiento.getTieneRechazo() == 1 && tipoPantallas == PANTALLA_RECHAZADAS) {
                                    binding.rechazadaAuditoria.setVisibility(View.VISIBLE);
                                } else {
                                    binding.rechazadaAuditoria.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                } else {
                    if(tipoPantallas == PANTALLA_RECHAZADAS) {
                        Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                        getContext().startActivity(main);
                    }else{
                        Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                        getContext().startActivity(main);
                    }
                    Toast.makeText(getContext(), tiempos.getMensaje(),
                            Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void reject(Exception e) {

            }
        });

        binding.verdetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), ActivityDetalle.class);
                getContext().startActivity(main);
                getActivity().finish();

            }
        });


        return view;
    }

}
