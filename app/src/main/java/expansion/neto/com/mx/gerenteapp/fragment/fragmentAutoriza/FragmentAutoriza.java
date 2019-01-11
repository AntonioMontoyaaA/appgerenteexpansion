package expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mancj.slideup.SlideUp;


import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza6Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutorizaConstruccionBinding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutorizaEditar6Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza7Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza8Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutorizaGenBinding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutorizaPropietarioBinding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutorizaSitioBinding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutorizaSuperficieBinding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutorizaZonificacionBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FechaApertura.FechaAperturaListener;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AnalistaCalidadOperativa;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AutorizaResponse;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosConstruccions;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosPredial;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosPuntuacion;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosSitio;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.GeneralidadesSitio;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.HorasPeatonales;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonales;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Points;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Propietario;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Superficie;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Zonificacion;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.CrearPeatonal;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Permiso;
import expansion.neto.com.mx.gerenteapp.provider.analistaCalidadOperatiavaProvider.ProviderAnalistaCalidadOperativa;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderAutorizaFinal;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderConsultaFinaliza;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderCrearMonto;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderCrearPeatonal;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosConstruccion;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosGeneralidadesSitio;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosPeatonal;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosPredial;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosPropietario;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosSitio;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosSuperficie;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosZonificacion;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderHorasPeatonales;
import expansion.neto.com.mx.gerenteapp.provider.documentosProvider.ProviderGuardarDocumentos;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AdapterAutorizaPeatonal;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AdapterListaHoras;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AutorizaHolderPeatonal;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaCompetencia;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaGeneradores;
import expansion.neto.com.mx.gerenteapp.utils.CustomTextWatcher;
import expansion.neto.com.mx.gerenteapp.utils.ServicioGPS;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import expansion.neto.com.mx.gerenteapp.utils.desing.MainSliderAdapter;
import expansion.neto.com.mx.gerenteapp.utils.desing.PicassoImageLoadingService;
import ss.com.bannerslider.Slider;


public class FragmentAutoriza extends Fragment implements AutorizaHolderPeatonal.Listener {

    private View view;
    private static final String ARG_POSITION = "position";
    private int position;
    AdapterListaCompetencia adapter;
    AdapterListaGeneradores adapter2;
    SharedPreferences preferences;
    AutorizaHolderPeatonal.Listener n;

    private final int MODULO_PANTALLA_1 = 1;
    private final int MODULO_PANTALLA_2 = 2;
    private final int MODULO_PANTALLA_3 = 3;
    private final int MODULO_PANTALLA_4 = 4;
    private final int MODULO_PANTALLA_5 = 5;
    private final int MODULO_PANTALLA_6 = 6;
    private final int MODULO_PANTALLA_7 = 7;
    private final int MODULO_GENERAL = 0;

    private final int MODULO_ASIGNADAS = 5;

    private final int SIN_AUTORIZACION = 0;
    private final int AUTORIZA_ID = 1;
    private final int RECHAZA_ID = 2;
    private final int RECHAZO_DEFINITIVO_ID = 1;
    private final int AUTORIZA_MD = 1;
    private final int RECHAZA_MD = 0;

    private Slider slider;

    List<Zonificacion.Detalle> list = new ArrayList<>();
    List<Zonificacion.Detalle> listGeneradores = new ArrayList<>();

    private OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            if (lat != null) {

                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putFloat("latMd", Float.valueOf(String.valueOf(lat)));
                editor.putFloat("lotMd", Float.valueOf(String.valueOf(lot)));
                editor.apply();
                icon = getBitmapDescriptor(R.drawable.home);

                LatLng md = new LatLng(lat, lot);
                googleMap.addMarker(new MarkerOptions().position(md)
                        .title("")
                        .icon(icon));

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(md));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(md, 15));
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(md)
                        .zoom(14)
                        .bearing(0)
                        .tilt(0)
                        .build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            } else {
                //  Toast.makeText(getContext(), "Error al cargar los datos",
                //        Toast.LENGTH_SHORT).show();
            }

        }
    };

    BitmapDescriptor icon;

    private OnMapReadyCallback onMapReadyCallbackZonificacion = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {

            if (puntosCompentencias != null) {

                for (int i = 0; i < puntosCompentencias.size(); i++) {

                    if (puntosCompentencias.get(i).getIcono() == 1) {
                        icon = getBitmapDescriptor(R.drawable.bbb);
                    } else if (puntosCompentencias.get(i).getIcono() == 2) {
                        icon = getBitmapDescriptor(R.drawable.oxxo);
                    } else if (puntosCompentencias.get(i).getIcono() == 3) {
                        icon = getBitmapDescriptor(R.drawable.bodegaa);
                    } else if (puntosCompentencias.get(i).getIcono() == 4) {
                        icon = getBitmapDescriptor(R.drawable.abarrotes);
                    } else if (puntosCompentencias.get(i).getIcono() == 5) {
                        icon = getBitmapDescriptor(R.drawable.g_iglesia);
                    } else if (puntosCompentencias.get(i).getIcono() == 6) {
                        icon = getBitmapDescriptor(R.drawable.g_mercado);
                    } else if (puntosCompentencias.get(i).getIcono() == 7) {
                        icon = getBitmapDescriptor(R.drawable.escuela);
                    } else if (puntosCompentencias.get(i).getIcono() == 8) {
                        icon = getBitmapDescriptor(R.drawable.g_busstop);
                    } else if (puntosCompentencias.get(i).getIcono() == 9) {
                        icon = getBitmapDescriptor(R.drawable.otros);
                    } else if (puntosCompentencias.get(i).getIcono() == 10) {
                        icon = getBitmapDescriptor(R.drawable.neto);
                    } else if (puntosCompentencias.get(i).getIcono() == 11) {
                        icon = getBitmapDescriptor(R.drawable.g_recauderia);
                    } else if (puntosCompentencias.get(i).getIcono() == 12) {
                        icon = getBitmapDescriptor(R.drawable.g_comida);
                    } else if (puntosCompentencias.get(i).getIcono() == 13) {
                        icon = getBitmapDescriptor(R.drawable.g_mercado);
                    } else if (puntosCompentencias.get(i).getIcono() == 14) {
                        icon = getBitmapDescriptor(R.drawable.g_tianguis);
                    } else if (puntosCompentencias.get(i).getIcono() == 15) {
                        icon = getBitmapDescriptor(R.drawable.g_tortilleria);
                    } else if (puntosCompentencias.get(i).getIcono() == 16) {
                        icon = getBitmapDescriptor(R.drawable.g_carniceria);
                    } else if (puntosCompentencias.get(i).getIcono() == 17) {
                        icon = getBitmapDescriptor(R.drawable.metro);
                    }

                    LatLng md = new LatLng(puntosCompentencias.get(i).getLat(), puntosCompentencias.get(i).getLot());
                    googleMap.addMarker(new MarkerOptions().position(md)
                            .title(puntosCompentencias.get(i).getNombre())
                            .icon(icon)
                    );

                }

                SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                Float mdLat = preferences.getFloat("latMd", 0);
                Float mdLot = preferences.getFloat("lotMd", 0);

                LatLng mds = new LatLng(mdLat, mdLot);
                icon = getBitmapDescriptor(R.drawable.home);
                googleMap.addMarker(new MarkerOptions().position(mds)
                        .title("")
                        .icon(icon)
                );

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(mds));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mds, 15));
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(mds)
                        .zoom(14)
                        .bearing(0)
                        .tilt(0)
                        .build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            } else {
                // Toast.makeText(getContext(), "Error al cargar los datos",
                //       Toast.LENGTH_SHORT).show();
            }

        }
    };

    public static FragmentAutoriza newInstance(int position) {
        FragmentAutoriza f = new FragmentAutoriza();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    String nombreSitio;

    private SlideUp slideCompetencia;
    private SlideUp slideGenerador;
    private Double lat, lot;
    ArrayList<Points> puntosCompentencias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("permisos", null);
        Type type = new TypeToken<ArrayList<Permiso>>() {
        }.getType();
        ArrayList<Permiso> permisos = gson.fromJson(json, type);

        final Resources resource = getContext().getResources();
        if (position == 0) {

            mensaje = "fragment 1";
            final FragmentAutorizaSitioBinding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_sitio, container, false);
            view = binding.getRoot();

            setPermisos(permisos, String.valueOf(MODULO_PANTALLA_1));
            setPermisosRechazar(permisos, String.valueOf(MODULO_PANTALLA_1));

            if (permisoP1) {
                binding.acep.setVisibility(View.VISIBLE);
            } else if (permisoRechazarP1) {
                binding.can.setVisibility(View.VISIBLE);
            } else {
                binding.can.setVisibility(View.INVISIBLE);
                binding.acep.setVisibility(View.INVISIBLE);
            }

            binding.toolbar.nombreTitulo.setText(getString(R.string.datossitio));
            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String mdId = preferences.getString("mdId", "");
            final String usuarioId = preferences.getString("usuario", "");
            int atrasa = preferences.getInt("atrasa", 0);
            int estatus = preferences.getInt("estatusId", 0);
            String nombreEstatus = preferences.getString("nombreEstatus", "");
            final String urlLayout = preferences.getString("urlLayout", "");
            final String monto1 = preferences.getString("monto1", "");
            final String monto2 = preferences.getString("monto2", "");
            SharedPreferences.Editor autoriza = preferences.edit();

            Boolean atrasadasAutoriza = false;


            if (estatus == 9) {

                autoriza.putBoolean("goneAutoriza", true);
                autoriza.putString("statusValidacion", "9");
                autoriza.apply();
                atrasadasAutoriza = true;
                binding.nombreStatus.setText(nombreEstatus + "");
                binding.archivo.setVisibility(View.VISIBLE);
                binding.nombreStatus.setVisibility(View.VISIBLE);
                binding.montoFinal.setVisibility(View.GONE);
                binding.montoFinal.setVisibility(View.GONE);
                binding.enviarmonto.setVisibility(View.GONE);
                binding.monto1.setVisibility(View.GONE);
                binding.monto2.setVisibility(View.GONE);
                binding.mxn1.setVisibility(View.VISIBLE);
                binding.mxn2.setVisibility(View.VISIBLE);

                binding.mxn3.setVisibility(View.GONE);
                binding.simbolo.setVisibility(View.GONE);
                binding.nombreppo.setVisibility(View.GONE);
                binding.nombreppa.setVisibility(View.GONE);
                binding.archivo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        if (!urlLayout.equals("-")) {
                            i.setData(Uri.parse(urlLayout));
                            startActivity(i);
                        } else {
                            Toast.makeText(getContext(), "Esta MD no tiene una URL valida",
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                });
            } else {
                autoriza.putBoolean("goneAutoriza", false);
                autoriza.apply();
                atrasadasAutoriza = false;
                binding.nombreStatus.setVisibility(View.GONE);
                binding.archivo.setVisibility(View.GONE);
                binding.montoFinal.setVisibility(View.GONE);
                binding.monto1.setVisibility(View.GONE);
                binding.monto2.setVisibility(View.GONE);

                binding.mxn1.setVisibility(View.GONE);
                binding.mxn2.setVisibility(View.GONE);
                binding.mxn3.setVisibility(View.GONE);
                binding.simbolo.setVisibility(View.GONE);
                binding.enviarmonto.setVisibility(View.GONE);
                binding.nombreppo.setVisibility(View.GONE);
                binding.nombreppa.setVisibility(View.GONE);

                if (estatus == 12) {
                    autoriza.putString("statusValidacion", "9");
                    atrasadasAutoriza = true;
                    autoriza.putBoolean("goneAutoriza", true);
                    autoriza.apply();
                    binding.archivo.setVisibility(View.GONE);
                    binding.nombreStatus.setText(nombreEstatus + "");
                    binding.montoFinal.setVisibility(View.VISIBLE);
                    binding.nombreStatus.setVisibility(View.VISIBLE);
                    binding.monto1.setVisibility(View.VISIBLE);
                    binding.monto2.setVisibility(View.VISIBLE);
                    binding.enviarmonto.setVisibility(View.VISIBLE);
                    binding.monto1.setText("$" + monto1);
                    binding.monto2.setText("$" + monto2);
                    binding.mxn1.setVisibility(View.VISIBLE);
                    binding.mxn2.setVisibility(View.VISIBLE);
                    binding.mxn3.setVisibility(View.VISIBLE);
                    binding.simbolo.setVisibility(View.VISIBLE);
                    binding.nombreppa.setVisibility(View.VISIBLE);
                    binding.nombreppo.setVisibility(View.VISIBLE);

                    binding.montoFinal.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);


                    binding.enviarmonto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String fecha = Util.getFechaKk();
                            if (!binding.montoFinal.getText().toString().equals("")) {
                                ProviderCrearMonto.getInstance(getContext()).guardarMonto(
                                        fecha,
                                        mdId,
                                        usuarioId,
                                        binding.montoFinal.getText().toString(), new ProviderCrearMonto.InterfaceCrearMonto() {
                                            @Override
                                            public void resolve(Codigos codigo) {
                                                if (codigo != null) {
                                                    if (codigo.getCodigo() == 200) {
                                                        ProviderAutorizaFinal.getInstance(getContext()).autorizaFinal(
                                                                mdId, usuarioId, 1,
                                                                0, "", binding.montoFinal.getText().toString(), new ProviderAutorizaFinal.AutorizaFinal() {
                                                                    @Override
                                                                    public void resolve(AutorizaResponse autorizaResponse) {
                                                                        if (autorizaResponse != null) {
                                                                            Toast.makeText(getContext(), autorizaResponse.getMensaje(),
                                                                                    Toast.LENGTH_LONG).show();
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void reject(Exception e) {
                                                                    }
                                                                }
                                                        );
                                                    }
                                                } else {
                                                    Toast.makeText(getContext(), codigo.getMensaje(),
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            }

                                            @Override
                                            public void reject(Exception e) {
                                            }
                                        }
                                );
                            } else {
                                Toast.makeText(getContext(), getString(R.string.monto) + "",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else if (estatus == 16) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    String fechaAperturaText = preferences.getString("fechaApertura" + mdId, "");
                    if (fechaAperturaText.equals("")) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        Date date = new Date();
                        String fecha = dateFormat.format(date);
                        binding.fechaapertura.setText(fecha);
                    } else {
                        binding.fechaapertura.setText(fechaAperturaText);
                    }
                    binding.inicio.setVisibility(View.VISIBLE);
                    binding.inicioenvia.setVisibility(View.VISIBLE);
                    binding.inicio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FragmentManager fm = getChildFragmentManager();
                            FechaApertura fechaApertura = new FechaApertura();
                            fechaApertura.setFechaAperturaListener(new FechaAperturaListener() {
                                @Override
                                public void ActualizaFecha(String fecha) {
                                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                    binding.fechaapertura.setText(fecha);
                                }
                            });
                            fechaApertura.show(fm, "Dialog Fragment");
                        }
                    });
                    binding.enviarfecha.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ProviderGuardarDocumentos.getInstance(getContext()).guardarDocumentos1(usuarioId, mdId, "13", "", binding.fechaapertura.getText().toString() + " 00:00:00",
                                    new ProviderGuardarDocumentos.InterfaceGuardarDocumentos() {
                                        @Override
                                        public void resolve(Codigos codigo) {
                                            if (codigo.getCodigo() == 200) {
                                                Toast.makeText(getContext(), "Fecha guadada correctamente", Toast.LENGTH_SHORT).show();
                                                final SharedPreferences.Editor editor =preferences.edit();
                                                editor.putString(mdId+"fechaApertura", "true");
                                                editor.apply();
                                            }else {
                                                Toast.makeText(getContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        @Override
                                        public void reject(Exception e) {
                                            Toast.makeText(getContext(), "Ocurrio un problema al guardar la fecha", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        }
                    });

                } else {
                    binding.enviarmonto.setVisibility(View.GONE);
                    binding.nombreStatus.setVisibility(View.GONE);
                    binding.archivo.setVisibility(View.GONE);
                    binding.montoFinal.setVisibility(View.GONE);
                    binding.monto1.setVisibility(View.GONE);
                    binding.monto2.setVisibility(View.GONE);
                    binding.mxn1.setVisibility(View.GONE);
                    binding.mxn2.setVisibility(View.GONE);
                    binding.mxn3.setVisibility(View.GONE);
                    binding.simbolo.setVisibility(View.GONE);
                    binding.nombreppo.setVisibility(View.GONE);
                    binding.nombreppa.setVisibility(View.GONE);
                    binding.inicio.setVisibility(View.GONE);
                    binding.inicioenvia.setVisibility(View.GONE);
                    atrasadasAutoriza = false;
                    autoriza.putBoolean("goneAutoriza", false);
                    autoriza.apply();
                }
            }

            if (atrasadasAutoriza) {
                binding.autorizalayout.setVisibility(View.GONE);
            } else {
                binding.autorizalayout.setVisibility(View.VISIBLE);
            }


            if (atrasa == 1) {
                binding.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            } else {
                binding.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }

            ProviderDatosSitio.getInstance(getContext()).obtenerDatosSitio(mdId, usuarioId, new ProviderDatosSitio.ConsultaDatosSitio() {
                @Override
                public void resolve(DatosSitio datosSitio) {

                    if (datosSitio.getDatossitio() != null && datosSitio.getCodigo() == 200) {

                        if (datosSitio.getDatossitio().get(0).getDireccion() != null &&
                                datosSitio.getDatossitio().get(0).getDetallesValidacion() != null &&
                                datosSitio.getDatossitio().get(0).getLatitud() != null &&
                                datosSitio.getDatossitio().get(0).getLongitud() != null &&
                                datosSitio.getDatossitio().get(0).getNombreSitio() != null) {

                            if (datosSitio.getDatossitio().get(0).getTipoUbicacionMD() != null) {
                                if (datosSitio.getDatossitio().get(0).getTipoUbicacionMD().equals("RURAL")) {
                                    binding.setRural("Rural");
                                } else {
                                    binding.setRural("Ciudad");
                                }
                            }

                            nombreSitio = datosSitio.getDatossitio().get(0).getNombreSitio();

                            binding.nombresitio.setEnabled(false);
                            binding.nombresitio.setText(datosSitio.getDatossitio().get(0).getNombreSitio());
                            binding.fechaCreacion.setText(datosSitio.getDatossitio().get(0).getFechaCreacion() + "");
                            binding.direccionsitio.setText(datosSitio.getDatossitio().get(0).getDireccion() + "");
                            binding.puntos.setText(datosSitio.getDatossitio().get(0).getTotalmd() + "");
                            binding.setCategoria(datosSitio.getDatossitio().get(0).getCategoria() + "");
                            binding.categoria.setText(datosSitio.getDatossitio().get(0).getCategoria() + "");

                            lat = Double.valueOf(datosSitio.getDatossitio().get(0).getLatitud());
                            lot = Double.valueOf(datosSitio.getDatossitio().get(0).getLongitud());

                            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                                    .findFragmentById(R.id.map);

                            mapFragment.getMapAsync(onMapReadyCallback);

                            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editorDatosSitio = preferences.edit();
                            editorDatosSitio.putString("nombreSitio", datosSitio.getDatossitio().get(0).getNombreSitio());
                            editorDatosSitio.putString("categoria", datosSitio.getDatossitio().get(0).getCategoria());

                            if (datosSitio.getDatossitio().get(0).getValidado() == 1) {
                                if (datosSitio.getDatossitio().get(0).getDetallesValidacion() != null
                                        && datosSitio.getDatossitio().get(0).getDetallesValidacion().size() > 0
                                        && datosSitio.getDatossitio().get(0).getDetallesValidacion().get(0).getMOTIVORECHAZOID() == 0) {

//                                    Picasso.get()
//                                            .load(R.drawable.aprovado)
//                                            .placeholder(R.drawable.aprovado)
//                                            .into(binding.aceptar);
//


                                    binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                    binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));

                                    editorDatosSitio.putInt("MODULO_1_TIPO_AUTORIZACION", AUTORIZA_ID);
                                    editorDatosSitio.putInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO", 0);
                                    editorDatosSitio.putInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                                } else {
                                    binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                    binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                                    editorDatosSitio.putInt("MODULO_1_TIPO_AUTORIZACION", RECHAZA_ID);
                                    editorDatosSitio.putInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO", datosSitio.getDatossitio().get(0).getDetallesValidacion().get(0).getMOTIVORECHAZOID());
                                    editorDatosSitio.putInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", datosSitio.getDatossitio().get(0).getDetallesValidacion().get(0).getRECHAZODEFINITIVO());
                                }
                            } else {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                editorDatosSitio.putInt("MODULO_1_TIPO_AUTORIZACION", SIN_AUTORIZACION);
                                editorDatosSitio.putInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO", 0);
                                editorDatosSitio.putInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                            }
//                            if(datosSitio.getDatossitio().get(0).getTip().size() > 0) {
//                                StringBuffer tipMod1 = new StringBuffer("");
//                                for(DatosSitio.Tip tip : datosSitio.getDatossitio().get(0).getTip()) {
//                                    tipMod1.append(tip.getDetalle() + "\n");
//                                }
//                                editorDatosSitio.putString("tip_modulo_1", tipMod1.toString());
//                            } else {
//                                editorDatosSitio.putString("tip_modulo_1", "Agrega un tip a esta pantalla");
//                            }
                            editorDatosSitio.apply();

                            //binding.aceptar.setImageTintMode();

                        } else {
                            Toast.makeText(getContext(), "Error al cargar los datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                }

                @Override
                public void reject(Exception e) {
                }
            });

            binding.acep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_1_TIPO_AUTORIZACION", 0);

                    if (accion == RECHAZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_1);
                        editorMotivos.apply();

                        FragmentDialogAceptar a = new FragmentDialogAceptar();
                        Bundle bundle = new Bundle();
                        bundle.putInt("modulo", MODULO_PANTALLA_1);
                        a.setArguments(bundle);
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloAceptadoListener(new FragmentDialogAceptar.OnModuloAceptadoListener() {
                            @Override
                            public void onModuloAceptado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido aceptada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }


                }
            });

            binding.can.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_1_TIPO_AUTORIZACION", 0);

                    if (accion == AUTORIZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_1);
                        editorMotivos.apply();

                        FragmentDialogCancelar a = new FragmentDialogCancelar();
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloCanceladoListener(new FragmentDialogCancelar.OnModuloRechazadoListener() {
                            @Override
                            public void onModuloRechazado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido rechazada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                    getContext().startActivity(main);
                }
            });

        } else if (position == 1) {

            final FragmentAutorizaPropietarioBinding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_propietario, container, false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.datospropietario));

            setPermisos(permisos, String.valueOf(MODULO_PANTALLA_2));
            setPermisosRechazar(permisos, String.valueOf(MODULO_PANTALLA_2));

            binding.aceptar.setVisibility(View.INVISIBLE);
            if (permisoP2) {
                // binding.aceptar.setVisibility(View.VISIBLE);
            } else if (permisoRechazarP2) {
                // binding.cancelar.setVisibility(View.VISIBLE);
            } else {
                binding.cancelar.setVisibility(View.INVISIBLE);
                binding.aceptar.setVisibility(View.INVISIBLE);
            }


            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId", "");
            String usuarioId = preferences.getString("usuario", "");


            int atrasa = preferences.getInt("atrasa", 0);


            if (atrasa == 1) {
                binding.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            } else {
                binding.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }


            ProviderDatosPropietario.getInstance(getContext())
                    .obtenerDatosPropietario(mdId, usuarioId, new ProviderDatosPropietario.ConsultaDatosPropietario() {
                        @Override
                        public void resolve(Propietario propietario) {

                            if (propietario.getCodigo() == 200) {
                                binding.nombrepropietario.setText(propietario.getNombrePropietario() + " " +
                                        propietario.getAPaternoPropietario() + " " + propietario.getAMaternoPropietario());
                                binding.telefono.setText(PhoneNumberUtils.formatNumber(propietario.getTelefono(), Locale.getDefault().getCountry()));
                                binding.email.setText(propietario.getMail());
                                if (propietario.getRentaMasLocales() > 0) {
                                    binding.robotoTextView11.setText("YA RENTA A NETO");
                                } else {
                                    binding.robotoTextView11.setText("NO RENTA A NETO");
                                }

                                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editorPropietario = preferences.edit();
                                if (propietario.getTip() != null && propietario.getTip().size() > 0) {
                                    StringBuffer tipMod2 = new StringBuffer("");
                                    for (Propietario.Tip tip : propietario.getTip()) {
                                        tipMod2.append(tip.getDetalle() + "\n");
                                    }
                                    editorPropietario.putString("tip_modulo_2", tipMod2.toString());
                                } else {
                                    editorPropietario.putString("tip_modulo_2", "Agrega un tip a esta pantalla");
                                }
                                editorPropietario.apply();
                            } else {
                                Toast.makeText(getContext(), propietario.getMensaje(),
                                        Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void reject(Exception e) {
                        }
                    });

            binding.aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_2_TIPO_AUTORIZACION", 0);

                    if (accion == RECHAZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_2);
                        editorMotivos.apply();

                        FragmentDialogAceptar a = new FragmentDialogAceptar();
                        Bundle bundle = new Bundle();
                        bundle.putInt("modulo", MODULO_PANTALLA_2);
                        a.setArguments(bundle);
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloAceptadoListener(new FragmentDialogAceptar.OnModuloAceptadoListener() {
                            @Override
                            public void onModuloAceptado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido aceptada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }


                }
            });


            binding.cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_2_TIPO_AUTORIZACION", 0);

                    if (accion == AUTORIZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_2);
                        editorMotivos.apply();

                        FragmentDialogCancelar a = new FragmentDialogCancelar();
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloCanceladoListener(new FragmentDialogCancelar.OnModuloRechazadoListener() {
                            @Override
                            public void onModuloRechazado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido rechazada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });


            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                    getContext().startActivity(main);
                }
            });


        } else if (position == 2) {

            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId", "");
            String usuarioId = preferences.getString("usuario", "");
            final String nombreSitio = preferences.getString("nombreSitio", "");

            final FragmentAutorizaSuperficieBinding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_superficie, container, false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.datossuperficie));

            setPermisos(permisos, String.valueOf(MODULO_PANTALLA_3));
            setPermisosRechazar(permisos, String.valueOf(MODULO_PANTALLA_2));

            binding.frente.setFilters(new InputFilter[]{new CustomTextWatcher(4, 1)});
            binding.profundidad.setFilters(new InputFilter[]{new CustomTextWatcher(4, 1)});

            int atrasa = preferences.getInt("atrasa", 0);


            if (atrasa == 1) {
                binding.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            } else {
                binding.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }

            Boolean atrasadasAutoriza = preferences.getBoolean("goneAutoriza", false);

            if (atrasadasAutoriza) {
                binding.autorizalayout.setVisibility(View.GONE);
            } else {
                binding.autorizalayout.setVisibility(View.VISIBLE);
            }

            ProviderDatosPredial.getInstance(getContext()).obtenerDatosPredial(mdId, usuarioId, new ProviderDatosPredial.ConsultaDatosPredial() {
                @Override
                public void resolve(DatosPredial datosPredial) {
                    if (datosPredial != null) {
                        if (datosPredial.getCodigo().equals("200")) {
                            if (datosPredial.getAplicaPredial().equals("1")) {
                                //binding.predial.setVisibility(View.VISIBLE);
                            } else {
                                // binding.predial.setVisibility(View.GONE);
                                // urlPredial = " ";
                            }
                        }
                    } else {
                        // binding.predial.setVisibility(View.GONE);
                        // urlPredial = " ";
                    }
                }

                @Override
                public void reject(Exception e) {
                    // binding.predial.setVisibility(View.GONE);
                    // urlPredial = " ";
                }
            });

            if (permisoP3) {
                binding.acep.setVisibility(View.VISIBLE);
            } else if (permisoRechazarP3) {
                binding.can.setVisibility(View.VISIBLE);
            } else {
                binding.acep.setVisibility(View.INVISIBLE);
                binding.can.setVisibility(View.INVISIBLE);
            }

            ProviderDatosSuperficie.getInstance(getContext())
                    .obtenerDatosSuperficie(mdId, usuarioId, new ProviderDatosSuperficie.ConsultaDatosSuperficie() {
                        @Override
                        public void resolve(final Superficie superficie) {
                            if (superficie.getCodigo() == 200) {

                                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editorSuperficie = preferences.edit();

                                int valorFoto = 0;
                                int valorFrente = 0;
                                int valorFondo = 0;
                                int valorEsquina = 0;

                                for (int i = 0; i < superficie.getNiveles().size(); i++) {
                                    if (superficie.getNiveles().get(i).getNivel() == 4 ||
                                            superficie.getNiveles().get(i).getNivel() == 5) {
                                        valorFoto = i;
                                        valorFondo = i;
                                    }

                                    if (superficie.getNiveles().get(i).getNivel() == 6 ||
                                            superficie.getNiveles().get(i).getNivel() == 7) {
                                        valorFrente = i;
                                    }

                                    if (superficie.getNiveles().get(i).getNivel() == 8) {
                                        valorEsquina = i;
                                    }
                                }

                                Double esquina = superficie.getNiveles().get(valorEsquina).getValorreal();
                                binding.nombresitio.setText(nombreSitio + "");
                                if (esquina == 1) {
                                    binding.escogeEsquina.setText(R.string.esquina);
                                } else {
                                    binding.escogeEsquina.setText(R.string.no_esquina);
                                }

                                String superficieS = String.valueOf(superficie.getNiveles().get(valorFrente).getValorreal());
                                superficieS = superficieS.replace(" ", "");
                                String fondoS = String.valueOf(superficie.getNiveles().get(valorFondo).getFondo());
                                fondoS = fondoS.replace(" ", "");

                                String total = String.valueOf((Double.valueOf(superficieS)
                                        * (Double.valueOf(fondoS))));
                                binding.areaterreno.setText(total + " MTS2");
                                binding.frente.setText(superficieS + " MTS");
                                binding.profundidad.setText(fondoS + " MTS");

                                Slider.init(new PicassoImageLoadingService());
                                slider = binding.map;
                                final int finalValorFoto1 = valorFoto;

                                slider.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        slider.setAdapter(new MainSliderAdapter(
                                                superficie.getNiveles().get(finalValorFoto1).getImgFrenteId(),
                                                superficie.getNiveles().get(finalValorFoto1).getImgLateral1Id(),
                                                superficie.getNiveles().get(finalValorFoto1).getImgLateral2Id(),
                                                superficie.getNiveles().get(finalValorFoto1).getImgPredial()
                                        ));
                                        slider.setSelectedSlide(0);
                                    }
                                }, 1500);

                                if (superficie.getValidado() == 1) {
                                    if (superficie.getDetallesValidacion() != null && superficie.getDetallesValidacion().size() > 0 && superficie.getDetallesValidacion().get(0).getMOTIVORECHAZOID() == 0) {
                                        binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                        binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                        editorSuperficie.putInt("MODULO_3_TIPO_AUTORIZACION", AUTORIZA_ID);
                                        editorSuperficie.putInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO", 0);
                                        editorSuperficie.putInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                                    } else {
                                        binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                        binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                                        editorSuperficie.putInt("MODULO_3_TIPO_AUTORIZACION", RECHAZA_ID);
                                        editorSuperficie.putInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO", superficie.getDetallesValidacion().get(0).getMOTIVORECHAZOID());
                                        editorSuperficie.putInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", superficie.getDetallesValidacion().get(0).getRECHAZODEFINITIVO());
                                    }
                                } else {
                                    binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                    binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                    editorSuperficie.putInt("MODULO_3_TIPO_AUTORIZACION", SIN_AUTORIZACION);
                                    editorSuperficie.putInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO", 0);
                                    editorSuperficie.putInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                                }

                                if (superficie.getTip().size() > 0) {
                                    StringBuffer tipMod3 = new StringBuffer("");
                                    for (Superficie.Tip tip : superficie.getTip()) {
                                        tipMod3.append(tip.getDetalle() + "\n");
                                    }
                                    editorSuperficie.putString("tip_modulo_3", tipMod3.toString());
                                } else {
                                    editorSuperficie.putString("tip_modulo_3", "Agrega un tip a esta pantalla");
                                }
                                editorSuperficie.apply();
                            } else {
                                Toast.makeText(getContext(), "Error al obtener los datos",
                                        Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void reject(Exception e) {

                        }
                    });


            binding.acep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_3_TIPO_AUTORIZACION", 0);

                    if (accion == RECHAZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_3);
                        editorMotivos.apply();

                        FragmentDialogAceptar a = new FragmentDialogAceptar();
                        Bundle bundle = new Bundle();
                        bundle.putInt("modulo", MODULO_PANTALLA_3);
                        a.setArguments(bundle);
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloAceptadoListener(new FragmentDialogAceptar.OnModuloAceptadoListener() {
                            @Override
                            public void onModuloAceptado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido aceptada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }


                }
            });


            binding.can.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_3_TIPO_AUTORIZACION", 0);

                    if (accion == AUTORIZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_3);
                        editorMotivos.apply();

                        FragmentDialogCancelar a = new FragmentDialogCancelar();
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloCanceladoListener(new FragmentDialogCancelar.OnModuloRechazadoListener() {
                            @Override
                            public void onModuloRechazado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido rechazada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                    getContext().startActivity(main);
                }
            });


        } else if (position == 3) {

            mensaje = "fragment 2";
            final FragmentAutorizaZonificacionBinding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_zonificacion, container, false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.zonifica));
            binding.ciudad.setVisibility(View.GONE);

            setPermisos(permisos, String.valueOf(MODULO_PANTALLA_4));
            setPermisosRechazar(permisos, String.valueOf(MODULO_PANTALLA_4));

            if (permisoP4) {
                binding.acep.setVisibility(View.VISIBLE);
            } else if (permisoRechazarP4) {
                binding.can.setVisibility(View.VISIBLE);
            } else {
                binding.acep.setVisibility(View.INVISIBLE);
                binding.can.setVisibility(View.INVISIBLE);
            }

            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId", "");
            String usuarioId = preferences.getString("usuario", "");
            final String nombreSitio = preferences.getString("nombreSitio", "");

            int atrasa = preferences.getInt("atrasa", 0);
            if (atrasa == 1) {
                binding.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            } else {
                binding.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }

            Boolean atrasadasAutoriza = preferences.getBoolean("goneAutoriza", false);

            if (atrasadasAutoriza) {
                binding.autorizalayout.setVisibility(View.GONE);
            } else {
                binding.autorizalayout.setVisibility(View.VISIBLE);
            }

            ProviderDatosZonificacion.getInstance(getContext())
                    .obtenerDatosZonificacion(mdId, usuarioId, new ProviderDatosZonificacion.ConsultaDatosZonificacion() {
                        @Override
                        public void resolve(Zonificacion zonificacion) {
                            if (zonificacion.getCodigo() == 200) {
                                list = new ArrayList<>();
                                listGeneradores = new ArrayList<>();
                                puntosCompentencias = new ArrayList<>();
//                        int puntuacionCompetencia = 0;
//                        int puntuacionGeneradores = 0;

                                for (int i = 0; i < zonificacion.getCompetencia().size(); i++) {

                                    for (int j = 0; j < zonificacion.getCompetencia().get(i).getDetalle().size(); j++) {
                                        list.add(zonificacion.getCompetencia().get(i).getDetalle().get(j));
                                        Double compentenciaLatitud = Double.valueOf(zonificacion.getCompetencia().get(i).getDetalle().get(j).getLatitud());
                                        Double compentenciaLongitud = Double.valueOf(zonificacion.getCompetencia().get(i).getDetalle().get(j).getLongitud());

                                        int idIcono = zonificacion.getCompetencia().get(i).getDetalle().get(j).getGeneradorId();
                                        String nombre = zonificacion.getCompetencia().get(i).getDetalle().get(j).getNombreGenerador();

                                        puntosCompentencias.add(new Points(idIcono, compentenciaLatitud, compentenciaLongitud, nombre, null));


                                    }
                                }

                                for (int i = 0; i < zonificacion.getGeneradores().size(); i++) {
                                    for (int n = 0; n < zonificacion.getGeneradores().get(i).getDetalle().size(); n++) {

                                        listGeneradores.add(zonificacion.getGeneradores().get(i).getDetalle().get(n));

                                        Double compentenciaLatitud = Double.valueOf(zonificacion.getGeneradores().get(i).getDetalle().get(n).getLatitud());
                                        Double compentenciaLongitud = Double.valueOf(zonificacion.getGeneradores().get(i).getDetalle().get(n).getLongitud());

                                        int idIcono = zonificacion.getGeneradores().get(i).getDetalle().get(n).getGeneradorId();
                                        String nombre = zonificacion.getGeneradores().get(i).getDetalle().get(n).getNombreGenerador();

                                        puntosCompentencias.add(new Points(idIcono, compentenciaLatitud, compentenciaLongitud, nombre, null));
                                    }
                                }

//                        if(zonificacion.getCompetencia() != null && zonificacion.getCompetencia().size() > 0) {
//                            puntuacionCompetencia = Integer.parseInt(zonificacion.getCompetencia().get(0).getPuntuacion());
//                        }
//                        if(zonificacion.getGeneradores() != null && zonificacion.getGeneradores().size() > 0) {
//                            puntuacionGeneradores = Integer.parseInt(zonificacion.getGeneradores().get(0).getPuntuacion());
//                        }

                                binding.robotoTextView2.setText("MD " + nombreSitio);

                                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editorZonificacion = preferences.edit();
                                //editorZonificacion.putInt("puntajeZonificacion", puntuacionCompetencia + puntuacionGeneradores);
                                // editorZonificacion.putInt("puntajeTotalZonificacion", zonificacion.getPuntoFac() != null ? Integer.parseInt(zonificacion.getPuntoFac()) : 0);
                                if (zonificacion.getValidado() == 1) {
                                    if (zonificacion.getDetallesValidacion() != null && zonificacion.getDetallesValidacion().size() > 0 && zonificacion.getDetallesValidacion().get(0).getMOTIVORECHAZOID() == 0) {
                                        binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                        binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                        editorZonificacion.putInt("MODULO_4_TIPO_AUTORIZACION", AUTORIZA_ID);
                                        editorZonificacion.putInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO", 0);
                                        editorZonificacion.putInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                                    } else {
                                        binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                        binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                                        editorZonificacion.putInt("MODULO_4_TIPO_AUTORIZACION", RECHAZA_ID);
                                        editorZonificacion.putInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO", zonificacion.getDetallesValidacion().get(0).getMOTIVORECHAZOID());
                                        editorZonificacion.putInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", zonificacion.getDetallesValidacion().get(0).getRECHAZODEFINITIVO());
                                    }
                                } else {
                                    binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                    binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                    editorZonificacion.putInt("MODULO_4_TIPO_AUTORIZACION", SIN_AUTORIZACION);
                                    editorZonificacion.putInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO", 0);
                                    editorZonificacion.putInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                                }
                                if (zonificacion.getTip().size() > 0) {
                                    StringBuffer tipMod4 = new StringBuffer("");
                                    for (Zonificacion.Tip tip : zonificacion.getTip()) {
                                        tipMod4.append(tip.getDetalle() + "\n");
                                    }
                                    editorZonificacion.putString("tip_modulo_4", tipMod4.toString());
                                } else {
                                    editorZonificacion.putString("tip_modulo_4", "Agrega un tip a esta pantalla");
                                }
                                editorZonificacion.apply();

                                // adapter = new AdapterListaCompetencia(list, getContext());
                                binding.contenido.contentLista.setLayoutManager(new LinearLayoutManager(getContext()));
                                binding.contenido.contentLista.setAdapter(adapter);

                                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
                                binding.contenido.contentLista.setLayoutManager(mLayoutManager);
                                binding.contenido.contentLista.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(4), true));
                                binding.contenido.contentLista.setItemAnimator(new DefaultItemAnimator());


                                //adapter2 = new AdapterListaGeneradores(listGeneradores, getContext());
                                binding.content2.contentLista.setLayoutManager(new LinearLayoutManager(getContext()));
                                binding.content2.contentLista.setAdapter(adapter2);

                                RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getContext(), 4);
                                binding.content2.contentLista.setLayoutManager(mLayoutManager2);
                                binding.content2.contentLista.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(5), true));
                                binding.content2.contentLista.setItemAnimator(new DefaultItemAnimator());

                                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                                        .findFragmentById(R.id.map);

                                mapFragment.getMapAsync(onMapReadyCallbackZonificacion);

                            } else {
                                Toast.makeText(getContext(), zonificacion.getMensaje() + "",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void reject(Exception e) {

                        }
                    });

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                    getContext().startActivity(main);
                }
            });

            binding.acep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_4_TIPO_AUTORIZACION", 0);

                    if (accion == RECHAZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_4);
                        editorMotivos.apply();

                        FragmentDialogAceptar a = new FragmentDialogAceptar();
                        Bundle bundle = new Bundle();
                        bundle.putInt("modulo", MODULO_PANTALLA_4);
                        a.setArguments(bundle);
                        a.setCancelable(false);
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloAceptadoListener(new FragmentDialogAceptar.OnModuloAceptadoListener() {
                            @Override
                            public void onModuloAceptado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido aceptada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }


                }
            });


            binding.can.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_4_TIPO_AUTORIZACION", 0);

                    if (accion == AUTORIZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_4);
                        editorMotivos.apply();

                        FragmentDialogCancelar a = new FragmentDialogCancelar();
                        a.setCancelable(false);
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloCanceladoListener(new FragmentDialogCancelar.OnModuloRechazadoListener() {
                            @Override
                            public void onModuloRechazado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido rechazada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });


        } else if (position == 4) {

            final FragmentAutorizaConstruccionBinding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_construccion, container, false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.construccion));

            setPermisos(permisos, String.valueOf(MODULO_PANTALLA_5));

            setPermisosRechazar(permisos, String.valueOf(MODULO_PANTALLA_5));


            if (permisoP5) {
                binding.acep.setVisibility(View.VISIBLE);
            } else if (permisoRechazarP5) {
                binding.cancelar.setVisibility(View.VISIBLE);
            } else {
                binding.acep.setVisibility(View.INVISIBLE);
                binding.can.setVisibility(View.INVISIBLE);
            }

            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId", "");
            String usuarioId = preferences.getString("usuario", "");

            final int[] local = {0};
            final int[] acceso = {0};
            final int[] grietas = {0};
            final int[] goteras = {0};


            int atrasa = preferences.getInt("atrasa", 0);


            if (atrasa == 1) {
                binding.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            } else {
                binding.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }

            Boolean atrasadasAutoriza = preferences.getBoolean("goneAutoriza", false);

            if (atrasadasAutoriza) {
                binding.autorizalayout.setVisibility(View.GONE);
            } else {
                binding.autorizalayout.setVisibility(View.VISIBLE);
            }

            ProviderDatosConstruccion.getInstance(getContext())
                    .obtenerDatosConstruccion(mdId, usuarioId, new ProviderDatosConstruccion.ConsultaDatosConstruccion() {
                        @Override
                        public void resolve(DatosConstruccions datosSitio) {
                            if (datosSitio.getCodigo() == 200 && datosSitio.getConstruccion().size() > 0) {
                                String nombreSitio = preferences.getString("nombreSitio", "");
                                binding.nombresitio.setText(nombreSitio + "");
                                for (int i = 0; i < datosSitio.getConstruccion().size(); i++) {
                                    if (datosSitio.getConstruccion().get(i).getNivelid() == 1
                                            || datosSitio.getConstruccion().get(i).getNivelid() == 2) {
                                        String sitio = datosSitio.getConstruccion().get(i).getNombrenivel();
                                        if (sitio.contains("LOCAL")) {
                                            binding.setTerreno(1);
                                            for (int j = 0; j < datosSitio.getConstruccion().get(i).getDetalles().size(); j++) {
                                                if (datosSitio.getConstruccion().get(i).getDetalles().get(j).getDetalleid() == 1) {
                                                    local[0] = 1;
                                                    binding.setLocal(local[0]);
                                                }

                                                if (datosSitio.getConstruccion().get(i).getDetalles().get(j).getDetalleid() == 2) {
                                                    acceso[0] = 1;
                                                    binding.setAcceso(acceso[0]);
                                                }

                                                if (datosSitio.getConstruccion().get(i).getDetalles().get(j).getDetalleid() == 3) {
                                                    grietas[0] = 1;
                                                    binding.setTechos(grietas[0]);
                                                }

                                                if (datosSitio.getConstruccion().get(i).getDetalles().get(j).getDetalleid() == 4) {
                                                    goteras[0] = 1;
                                                    binding.setPisos(goteras[0]);
                                                }
                                            }
                                        } else {
                                            binding.setTerreno(0);
                                        }
                                        binding.construccion.setText(sitio + "");
                                    }

                                    if (datosSitio.getConstruccion().get(i).getNivelid() == 3
                                            || datosSitio.getConstruccion().get(i).getNivelid() == 4
                                            || datosSitio.getConstruccion().get(i).getNivelid() == 5) {

                                        String condicion = datosSitio.getConstruccion().get(i).getNombrenivel();
                                        binding.setCondiciones(condicion + "");

                                    }

                                }


                                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editorConstruccion = preferences.edit();
                                //editorConstruccion.putInt("puntajeConstruccion", sumaPuntuacion);
                                //editorConstruccion.putInt("puntajeTotalConstruccion", datosSitio.getPuntoFac() != null ? Integer.parseInt(datosSitio.getPuntoFac()) : 0);
                                if (datosSitio.getValidado() == 1) {
                                    if (datosSitio.getDetallesValidacion() != null && datosSitio.getDetallesValidacion().size() > 0 && datosSitio.getDetallesValidacion().get(0).getMOTIVORECHAZOID() == 0) {
                                        binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                        binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                        editorConstruccion.putInt("MODULO_5_TIPO_AUTORIZACION", AUTORIZA_ID);
                                        editorConstruccion.putInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO", 0);
                                        editorConstruccion.putInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                                    } else {
                                        binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                        binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                                        editorConstruccion.putInt("MODULO_5_TIPO_AUTORIZACION", RECHAZA_ID);
                                        editorConstruccion.putInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO", datosSitio.getDetallesValidacion().get(0).getMOTIVORECHAZOID());
                                        editorConstruccion.putInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", datosSitio.getDetallesValidacion().get(0).getRECHAZODEFINITIVO());
                                    }
                                } else {
                                    binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                    binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                    editorConstruccion.putInt("MODULO_5_TIPO_AUTORIZACION", SIN_AUTORIZACION);
                                    editorConstruccion.putInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO", 0);
                                    editorConstruccion.putInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                                }
                                if (datosSitio.getTip().size() > 0) {
                                    StringBuffer tipMod5 = new StringBuffer("");
//                            for(DatosConstruccion.Tip tip : datosSitio.getTip()) {
//                                tipMod5.append(tip.getDetalle() + "\n");
//                            }
                                    editorConstruccion.putString("tip_modulo_5", tipMod5.toString());
                                } else {
                                    editorConstruccion.putString("tip_modulo_5", "Agrega un tip a esta pantalla");
                                }
                                editorConstruccion.apply();

                            } else {
                                Toast.makeText(getContext(), "Error al cargar los datos",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void reject(Exception e) {

                        }
                    });

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                    getContext().startActivity(main);
                }
            });

            binding.acep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_5_TIPO_AUTORIZACION", 0);

                    if (accion == RECHAZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_5);
                        editorMotivos.apply();

                        FragmentDialogAceptar a = new FragmentDialogAceptar();
                        Bundle bundle = new Bundle();
                        bundle.putInt("modulo", MODULO_PANTALLA_5);
                        a.setArguments(bundle);
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloAceptadoListener(new FragmentDialogAceptar.OnModuloAceptadoListener() {
                            @Override
                            public void onModuloAceptado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido aceptada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }


                }
            });


            binding.can.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_5_TIPO_AUTORIZACION", 0);

                    if (accion == AUTORIZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_5);
                        editorMotivos.apply();

                        FragmentDialogCancelar a = new FragmentDialogCancelar();
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloCanceladoListener(new FragmentDialogCancelar.OnModuloRechazadoListener() {
                            @Override
                            public void onModuloRechazado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido rechazada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else if (position == 5) {

            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId", "");
            String usuarioId = preferences.getString("usuario", "");
            final String nombreSitio = preferences.getString("nombreSitio", "");

            final FragmentAutorizaGenBinding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_gen, container, false);
            view = binding.getRoot();
            binding.toolbar.nombreTitulo.setText(getString(R.string.generalidades));

            int atrasa = preferences.getInt("atrasa", 0);


            if (atrasa == 1) {
                binding.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            } else {
                binding.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }

            setPermisos(permisos, String.valueOf(MODULO_PANTALLA_6));
            setPermisosRechazar(permisos, String.valueOf(MODULO_PANTALLA_6));


            if (permisoP6) {
                binding.acep.setVisibility(View.VISIBLE);
            } else if (permisoRechazarP6) {
                binding.can.setVisibility(View.VISIBLE);
            } else {
                binding.acep.setVisibility(View.INVISIBLE);
                binding.can.setVisibility(View.INVISIBLE);
            }

            Boolean atrasadasAutoriza = preferences.getBoolean("goneAutoriza", false);

            if (atrasadasAutoriza) {
                binding.autorizalayout.setVisibility(View.GONE);
            } else {
                binding.autorizalayout.setVisibility(View.VISIBLE);
            }
            final DecimalFormat formatter = new DecimalFormat("#,###,###");
            ProviderDatosGeneralidadesSitio.getInstance(getContext())
                    .obtenerDatosGeneralidades(mdId, usuarioId, new ProviderDatosGeneralidadesSitio.ConsultaGeneralidadesSitio() {
                        @Override
                        public void resolve(GeneralidadesSitio datosSitio) {
                            if (datosSitio != null && datosSitio.getCodigo() == 200) {
                                if (datosSitio.getGeneralidades().size() > 0) {

                                    binding.nombresitio.setText(nombreSitio);

                                    for (int i = 0; i < datosSitio.getGeneralidades().size(); i++) {

                                        if (datosSitio.getGeneralidades().get(i).getNivelid() == 7 ||
                                                datosSitio.getGeneralidades().get(i).getNivelid() == 8 ||
                                                datosSitio.getGeneralidades().get(i).getNivelid() == 9) {

                                            binding.periodoamotizacion.setText(
                                                    datosSitio.getGeneralidades().get(i).getDetalles().get(0).getValor() + ""
                                            );

                                            binding.amortizaciontotal.setText(
                                                    "$" + formatter.format(datosSitio.getGeneralidades().get(i).getValor()) + ".00"
                                            );

                                        }

                                        if (datosSitio.getGeneralidades().get(i).getNivelid() == 4 || datosSitio.getGeneralidades().get(i).getNivelid() == 5 || datosSitio.getGeneralidades().get(i).getNivelid() == 6) {

                                            if (datosSitio.getGeneralidades().get(i).getNivelid() == 4) {
                                                binding.apartirde.setText(getString(R.string.disponible) + "");
                                            }

                                            if (datosSitio.getGeneralidades().get(i).getNivelid() == 5) {
                                                binding.apartirde.setText(getString(R.string.apartir) + " " + datosSitio.getGeneralidades().get(i).getFechadisponible() + "");
                                            }

                                            if (datosSitio.getGeneralidades().get(i).getNivelid() == 6) {
                                                binding.apartirde.setText(R.string.ocupados);
                                            }

                                        }

                                        if (datosSitio.getGeneralidades().get(i).getNivelid() == 1 ||
                                                datosSitio.getGeneralidades().get(i).getNivelid() == 2 ||
                                                datosSitio.getGeneralidades().get(i).getNivelid() == 3) {

                                            binding.renta.setText("$" + formatter.format(datosSitio.getGeneralidades().get(i).getValor()) + ".00");
                                            binding.periodogracia.setText(
                                                    datosSitio.getGeneralidades().get(i).getDetalles().get(0).getValor() + ""
                                            );
                                        }
                                    }
                                }

                                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editorGeneralidades = preferences.edit();
                                // editorGeneralidades.putInt("puntajeGeneralidades", sumaPuntuacion);
                                // editorGeneralidades.putInt("puntajeTotalGeneralidades", datosSitio.getPuntoFac() != null ? Integer.parseInt(datosSitio.getPuntoFac()) : 0);
                                if (datosSitio.getValidado() == 1) {
                                    if (datosSitio.getDetallesValidacion() != null && datosSitio.getDetallesValidacion().size() > 0 && datosSitio.getDetallesValidacion().get(0).getMOTIVORECHAZOID() == 0) {
                                        binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                        binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                        editorGeneralidades.putInt("MODULO_6_TIPO_AUTORIZACION", AUTORIZA_ID);
                                        editorGeneralidades.putInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO", 0);
                                        editorGeneralidades.putInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                                    } else {
                                        binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                        binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                                        editorGeneralidades.putInt("MODULO_6_TIPO_AUTORIZACION", RECHAZA_ID);
                                        editorGeneralidades.putInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO", datosSitio.getDetallesValidacion().get(0).getMOTIVORECHAZOID());
                                        editorGeneralidades.putInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", datosSitio.getDetallesValidacion().get(0).getRECHAZODEFINITIVO());
                                    }
                                } else {
                                    binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                    binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                    editorGeneralidades.putInt("MODULO_6_TIPO_AUTORIZACION", SIN_AUTORIZACION);
                                    editorGeneralidades.putInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO", 0);
                                    editorGeneralidades.putInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                                }
                                if (datosSitio.getTip().size() > 0) {
                                    StringBuffer tipMod6 = new StringBuffer("");
                                    for (GeneralidadesSitio.Tip tip : datosSitio.getTip()) {
                                        tipMod6.append(tip.getDetalle() + "\n");
                                    }
                                    editorGeneralidades.putString("tip_modulo_6", tipMod6.toString());
                                } else {
                                    editorGeneralidades.putString("tip_modulo_6", "Agrega un tip a esta pantalla");
                                }
                                editorGeneralidades.apply();

                            }
                        }

                        @Override
                        public void reject(Exception e) {

                        }
                    });


            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                    getContext().startActivity(main);
                }
            });

            binding.acep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_6_TIPO_AUTORIZACION", 0);

                    if (accion == RECHAZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_6);
                        editorMotivos.apply();

                        FragmentDialogAceptar a = new FragmentDialogAceptar();
                        Bundle bundle = new Bundle();
                        bundle.putInt("modulo", MODULO_PANTALLA_6);
                        a.setArguments(bundle);
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloAceptadoListener(new FragmentDialogAceptar.OnModuloAceptadoListener() {
                            @Override
                            public void onModuloAceptado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido aceptada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }


                }
            });


            binding.can.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int accion = preferences.getInt("MODULO_6_TIPO_AUTORIZACION", 0);

                    if (accion == AUTORIZA_ID || accion == SIN_AUTORIZACION) {
                        SharedPreferences.Editor editorMotivos = preferences.edit();
                        editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_6);
                        editorMotivos.apply();

                        FragmentDialogCancelar a = new FragmentDialogCancelar();
                        a.show(getChildFragmentManager(), "child");
                        a.setModuloCanceladoListener(new FragmentDialogCancelar.OnModuloRechazadoListener() {
                            @Override
                            public void onModuloRechazado(int modulo) {
                                binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                            }
                        });
                    } else {
                        Toast.makeText(getContext(), "Esta pantalla ya ha sido rechazada anteriormente",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else if (position == 6) {
            setPermisos(permisos, String.valueOf(MODULO_PANTALLA_7));
            setPermisosRechazar(permisos, String.valueOf(MODULO_PANTALLA_7));

            if (permisoEditar7) {

                final FragmentAutorizaEditar6Binding binding;
                binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_editar_6, container, false);
                view = binding.getRoot();

                if (permisoP7) {
                    binding.acep.setVisibility(View.VISIBLE);
                } else if (permisoRechazarP7) {
                    binding.can.setVisibility(View.VISIBLE);
                } else {
                    binding.acep.setVisibility(View.INVISIBLE);
                    binding.can.setVisibility(View.INVISIBLE);
                }

                Boolean atrasadasAutoriza = preferences.getBoolean("goneAutoriza", false);

                if (atrasadasAutoriza) {
                    binding.autorizalayout.setVisibility(View.GONE);
                } else {
                    binding.autorizalayout.setVisibility(View.VISIBLE);
                }

                final ArrayList<String> horarios = new ArrayList<>();

                final SharedPreferences[] preferences = {getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE)};
                final String usuarioId = preferences[0].getString("usuario", "");
                final String mdIdterminar = preferences[0].getString("mdId", "");

                String nombreMd = preferences[0].getString("nombreSitio", "");
                binding.robotoTextView2.setText(nombreMd);
                int atrasa = preferences[0].getInt("atrasa", 0);


                if (atrasa == 1) {
                    binding.view3.setBackgroundColor(Color.parseColor("#E4B163"));
                } else {
                    binding.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
                }

                listaPeatonal(binding);

                final long[] tiempos = {0};

                binding.peatonalConteo.presion.setAlpha(1f);
                binding.peatonalConteo.presion.setEnabled(true);

                binding.toolbar.guardar.setVisibility(View.GONE);
                binding.peatonalConteo.cronometroPlay.setEnabled(false);
                binding.peatonalConteo.cronometroStop.setEnabled(false);
                binding.peatonalConteo.btnGuardar.setEnabled(false);
                binding.peatonalConteo.btnGuardar.setAlpha(.4f);

                final int[] conteos = {0};
                final int[] tiempo = new int[1];
                final boolean[] hora = {false};

                final CountDownTimer[] downTimer = new CountDownTimer[1];
                binding.peatonalConteo.presion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (conteos[0] == 0) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                binding.peatonalConteo.chronometer1.setCountDown(true);
                            }


                            int sec = 60 * tiempo[0];
                            //int sec = 60* 1;

                            downTimer[0] = new CountDownTimer(1000 * sec, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    String v = String.format("%02d", millisUntilFinished / 60000);
                                    int va = (int) ((millisUntilFinished % 60000) / 1000);
                                    binding.peatonalConteo.contador.setText(v + ":" + String.format("%02d", va));
                                }

                                public void onFinish() {
                                    binding.peatonalConteo.contador.setText("00:00");
                                    binding.peatonalConteo.btnGuardar.setEnabled(true);
                                    binding.peatonalConteo.btnGuardar.setAlpha(1.f);
                                    binding.peatonalConteo.presion.setEnabled(false);
                                }
                            };
                            downTimer[0].start();

                            binding.peatonalConteo.cronometroPlay.setEnabled(false);
                            binding.peatonalConteo.cronometroStop.setEnabled(true);
                            binding.peatonalConteo.btnGuardar.setEnabled(false);
                            binding.peatonalConteo.btnGuardar.setAlpha(.4f);


                        }


                        conteos[0]++;
                        if (conteos[0] <= 9) {
                            binding.peatonalConteo.real.setText(conteos[0] + "");
                        }

                        if (conteos[0] > 9) {
                            String real = String.valueOf(conteos[0]).substring(String.valueOf(conteos[0]).length() - 1);
                            char first = String.valueOf(conteos[0]).charAt(0);
                            binding.peatonalConteo.real.setText(real + "");
                            binding.peatonalConteo.diez.setText(first + "");
                        }

                        if (conteos[0] > 99) {
                            char primerNumero = String.valueOf(conteos[0]).charAt(0);
                            char segundoNumero = String.valueOf(conteos[0]).charAt(1);
                            char tercerNumero = String.valueOf(conteos[0]).charAt(2);
                            binding.peatonalConteo.cien.setText(primerNumero + "");
                            binding.peatonalConteo.diez.setText(segundoNumero + "");
                            binding.peatonalConteo.real.setText(tercerNumero + "");
                        }

                        if (conteos[0] > 999) {

                            char primerNumero = String.valueOf(conteos[0]).charAt(0);
                            char segundoNumero = String.valueOf(conteos[0]).charAt(1);
                            char tercerNumero = String.valueOf(conteos[0]).charAt(2);
                            char cuartoNumero = String.valueOf(conteos[0]).charAt(3);

                            binding.peatonalConteo.mil.setText(primerNumero + "");
                            binding.peatonalConteo.cien.setText(segundoNumero + "");
                            binding.peatonalConteo.diez.setText(tercerNumero + "");
                            binding.peatonalConteo.real.setText(cuartoNumero + "");

                        }


                    }
                });


                binding.peatonalConteo.reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        binding.peatonalConteo.btnGuardar.setEnabled(false);
                        binding.peatonalConteo.btnGuardar.setAlpha(0.4f);

                        conteos[0] = 0;

                        binding.peatonalConteo.cien.setText("0");
                        binding.peatonalConteo.real.setText("0");
                        binding.peatonalConteo.mil.setText("0");
                        binding.peatonalConteo.diez.setText("0");
                        binding.peatonalConteo.contador.setText("00:00");

                        binding.peatonalConteo.presion.setEnabled(true);

                        if (downTimer[0] != null) {
                            downTimer[0].cancel();
                        }

                    }
                });


                ProviderHorasPeatonales.getInstance(getContext()).obtenerHoras(mdIdterminar, usuarioId,
                        new ProviderHorasPeatonales.InterfaceObtieneHoras() {
                            @Override
                            public void resolve(final HorasPeatonales horasPeatonales) {
                                if (horasPeatonales != null) {
                                    if (horasPeatonales.getCodigo() == 200) {

                                        tiempo[0] = Integer.parseInt(horasPeatonales.getTiempoConteos());

                                        binding.peatonalConteo.chronometer1.setBase(SystemClock.elapsedRealtime() - (tiempo[0] * 60000 + 0 * 1000));
                                        tiempos[0] = TimeUnit.MINUTES.toMillis(tiempo[0]);
                                        if (horasPeatonales.getDetalle().size() < 0) {
                                            binding.btnFinalizar.setAlpha(0.35f);
                                        } else {
                                            binding.botones.setVisibility(View.VISIBLE);
                                            binding.btnFinalizar.setEnabled(true);
                                        }

                                        for (int i = 0; i < horasPeatonales.getDetalle().size(); i++) {
                                            horarios.add(horasPeatonales.getDetalle().get(i).getHoraMin() + " - " +
                                                    horasPeatonales.getDetalle().get(i).getHoraMax());
                                        }

                                        adapterHoras = new AdapterListaHoras(horasPeatonales.getDetalle(), getContext());
                                        binding.recyclerHoras.setLayoutManager(new LinearLayoutManager(getContext()));
                                        binding.recyclerHoras.setAdapter(adapterHoras);

                                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
                                        binding.recyclerHoras.setLayoutManager(mLayoutManager);
                                        binding.recyclerHoras.addItemDecoration(new FragmentAutoriza.GridSpacingItemDecoration(3, dpToPx(4), true));
                                        binding.recyclerHoras.setItemAnimator(new DefaultItemAnimator());
                                        binding.peatonalConteo.spinnerHora.setItems(horarios);
                                        listaPeatonal(binding);

                                        horaInicio = horasPeatonales.getDetalle().get(0).getHoraMin();
                                        horaFinal = horasPeatonales.getDetalle().get(0).getHoraMax();

                                        Calendar c = Calendar.getInstance();
                                        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
                                        final String strDate = sdf.format(c.getTime());

                                        binding.toolbar.nombreTitulo.setText(getString(R.string.flujopeatonal));
                                        binding.conteo.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                binding.recyclerHoras.setVisibility(View.GONE);
                                                binding.recyclerPeatonal.setVisibility(View.GONE);

                                                binding.peaton.setVisibility(View.VISIBLE);
                                                binding.btnFinalizar.setVisibility(View.GONE);
                                                binding.promedio.setVisibility(View.GONE);
                                                binding.conteo.setAlpha(0.5f);
                                                binding.conteo.setEnabled(false);
                                                binding.btnFinalizar.setVisibility(View.GONE);
                                                binding.linearLayout.setVisibility(View.INVISIBLE);

                                                String hoI = horasPeatonales.getDetalle().get(0).getHoraMin();
                                                String hoF = horasPeatonales.getDetalle().get(0).getHoraMax();

                                                hoI = hoI.substring(0, 5);
                                                hoF = hoF.substring(0, 5);

                                                hora[0] = isHourInInterval(strDate, hoI, hoF);
                                                if (hora[0] != false) {
                                                    //TODO CONDICION QUE TRAE EL RANGO DE HORAS, Y EL RANGO DE MINUTOS EN EL CRONOMETRO
                                                    binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                    binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                    horaInicio = hoI;
                                                    horaFinal = hoF;
                                                } else {
                                                    binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                    binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                    hora[0] = false;
                                                }

                                                listaPeatonal(binding);
                                                binding.toolbar.guardar.setVisibility(View.GONE);
                                                binding.peatonalConteo.fechaHoy.setText(Util.getFechita());
                                                final String finalHoI = hoI;
                                                final String finalHoF = hoF;

                                                binding.peatonalConteo.spinnerHora.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                                                    @Override
                                                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                                                        if (position == 0) {
                                                            hora[0] = isHourInInterval(strDate, finalHoI, finalHoF);
                                                            if (hora[0] != false) {
                                                                binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                                binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                                horaInicio = finalHoI;
                                                                horaFinal = finalHoF;
                                                            } else {
                                                                binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                                binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                                hora[0] = false;
                                                            }
                                                        }

                                                        if (position == 1) {
                                                            String hoI = horasPeatonales.getDetalle().get(1).getHoraMin();
                                                            String hoF = horasPeatonales.getDetalle().get(1).getHoraMax();
                                                            hora[0] = isHourInInterval(strDate, hoI, hoF);
                                                            if (hora[0] != false) {
                                                                binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                                binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                                horaInicio = hoI;
                                                                horaFinal = hoF;
                                                            } else {
                                                                binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                                binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                                hora[0] = false;
                                                            }
                                                        }

                                                        if (position == 2) {
                                                            String hoI;
                                                            String hoF;
                                                            if (horarios.size() > 1) {
                                                                hoI = horasPeatonales.getDetalle().get(2).getHoraMin();
                                                                hoF = horasPeatonales.getDetalle().get(2).getHoraMax();
                                                            } else {
                                                                hoI = "";
                                                                hoF = "";
                                                            }

                                                            hora[0] = isHourInInterval(strDate, hoI, hoF);
                                                            if (hora[0] != false) {
                                                                binding.peatonalConteo.btnGuardar.setAlpha(1.0f);
                                                                binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                                horaInicio = hoI;
                                                                horaFinal = hoF;
                                                            } else {
                                                                binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                                binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                                hora[0] = false;
                                                            }
                                                        }
                                                    }
                                                });

                                                binding.peatonalConteo.cronometroPlay.setAlpha(0.35f);
                                                binding.peatonalConteo.cronometroStop.setAlpha(0.35f);

                                                binding.peatonalConteo.btnGuardar.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                        if (conteos[0] > 0 && hora[0]) {
                                                            ServicioGPS n = new ServicioGPS(getContext());
                                                            CrearPeatonal crearPeatonal = new CrearPeatonal(
                                                                    usuarioId,
                                                                    mdIdterminar,
                                                                    Util.getFechita(),
                                                                    horaInicio,
                                                                    horaFinal,
                                                                    String.valueOf(conteos[0]),
                                                                    String.valueOf(n.getLatitude()),
                                                                    String.valueOf(n.getLongitude()),
                                                                    "1.0.1",
                                                                    "5555555555",
                                                                    "0"
                                                            );

                                                            ProviderCrearPeatonal.getInstance(getContext()).guardarPeatonal(crearPeatonal, new ProviderCrearPeatonal.InterfaceCrearDatosPeatonal() {
                                                                @Override
                                                                public void resolve(Codigos codigo) {
                                                                    if (codigo.getCodigo() == 200) {
                                                                        binding.recyclerHoras.setVisibility(View.VISIBLE);

                                                                        Toast.makeText(getContext(), "Flujo peatonal guardado con éxito", Toast.LENGTH_SHORT).show();
                                                                        binding.peaton.setVisibility(View.GONE);
                                                                        binding.recyclerPeatonal.setVisibility(View.VISIBLE);
                                                                        listaPeatonal(binding);
                                                                        binding.peatonalConteo.etTotal.setText("");
                                                                        binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                                        binding.promedio.setVisibility(View.VISIBLE);
                                                                        binding.peatonalConteo.chronometer1.stop();
                                                                        binding.peatonalConteo.cronometroStop.setAlpha(1.0f);
                                                                        binding.peatonalConteo.cronometroPlay.setAlpha(0.35f);
                                                                        binding.linearLayout.setVisibility(View.VISIBLE);
                                                                        binding.peatonalConteo.spinnerHora.setItems(horarios);
                                                                        binding.conteo.setAlpha(1f);
                                                                        binding.conteo.setEnabled(true);
                                                                        binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                                        binding.peatonalConteo.btnGuardar.setAlpha(0.4f);
                                                                        conteos[0] = 0;

                                                                        binding.peatonalConteo.cien.setText("0");
                                                                        binding.peatonalConteo.real.setText("0");
                                                                        binding.peatonalConteo.mil.setText("0");
                                                                        binding.peatonalConteo.diez.setText("0");
                                                                        binding.peatonalConteo.presion.setEnabled(true);
                                                                        binding.btnFinalizar.setVisibility(View.GONE);

                                                                        if (downTimer[0] != null) {
                                                                            downTimer[0].cancel();
                                                                        }
                                                                    } else {
                                                                        Toast.makeText(getContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                                                                        binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                                    }
                                                                }

                                                                @Override
                                                                public void reject(Exception e) {
                                                                }
                                                            });
                                                        } else {
                                                            binding.peatonalConteo.btnGuardar.setEnabled(true);
                                                            Toast.makeText(getContext(), R.string.horario_mal, Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                                binding.peatonalConteo.regresar.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        binding.peaton.setVisibility(View.GONE);
                                                        binding.btnFinalizar.setVisibility(View.VISIBLE);
                                                        binding.recyclerPeatonal.setVisibility(View.VISIBLE);
                                                        binding.btnFinalizar.setVisibility(View.GONE);
                                                        binding.promedio.setVisibility(View.VISIBLE);
                                                        binding.linearLayout.setVisibility(View.VISIBLE);
                                                        binding.conteo.setAlpha(1f);
                                                        binding.conteo.setEnabled(true);
                                                        binding.recyclerHoras.setVisibility(View.VISIBLE);

                                                        binding.peatonalConteo.btnGuardar.setEnabled(false);
                                                        binding.peatonalConteo.btnGuardar.setAlpha(0.4f);

                                                        conteos[0] = 0;

                                                        binding.peatonalConteo.cien.setText("0");
                                                        binding.peatonalConteo.real.setText("0");
                                                        binding.peatonalConteo.mil.setText("0");
                                                        binding.peatonalConteo.diez.setText("0");
                                                        binding.peatonalConteo.presion.setEnabled(true);
                                                        binding.peatonalConteo.contador.setText("00:00");
                                                        if (downTimer[0] != null) {
                                                            downTimer[0].cancel();
                                                        }
                                                    }
                                                });
                                            }
                                        });

                                    }
                                }

                            }

                            @Override
                            public void reject(Exception e) {

                            }
                        });


                binding.acep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        preferences[0] = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        int accion = preferences[0].getInt("MODULO_7_TIPO_AUTORIZACION", 0);

                        if (accion == RECHAZA_ID || accion == SIN_AUTORIZACION) {
                            SharedPreferences.Editor editorMotivos = preferences[0].edit();
                            editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_7);
                            editorMotivos.apply();

                            FragmentDialogAceptar a = new FragmentDialogAceptar();
                            Bundle bundle = new Bundle();
                            bundle.putInt("modulo", MODULO_PANTALLA_7);
                            a.setArguments(bundle);
                            a.show(getChildFragmentManager(), "child");
                            a.setModuloAceptadoListener(new FragmentDialogAceptar.OnModuloAceptadoListener() {
                                @Override
                                public void onModuloAceptado(int modulo) {
                                    binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                    binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "Esta pantalla ya ha sido aceptada anteriormente",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });


                binding.can.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        preferences[0] = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        int accion = preferences[0].getInt("MODULO_7_TIPO_AUTORIZACION", 0);

                        if (accion == AUTORIZA_ID || accion == SIN_AUTORIZACION) {
                            SharedPreferences.Editor editorMotivos = preferences[0].edit();
                            editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_7);
                            editorMotivos.apply();

                            FragmentDialogCancelar a = new FragmentDialogCancelar();
                            a.show(getChildFragmentManager(), "child");
                            a.setModuloCanceladoListener(new FragmentDialogCancelar.OnModuloRechazadoListener() {
                                @Override
                                public void onModuloRechazado(int modulo) {
                                    binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                    binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "Esta pantalla ya ha sido rechazada anteriormente",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

            } else {

                final FragmentAutoriza6Binding binding;
                binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_6, container, false);
                view = binding.getRoot();

                listaPeatonal(binding);

                binding.toolbar.nombreTitulo.setText(getString(R.string.flujopeatonal));

                if (permisoP7) {
                    binding.acep.setVisibility(View.VISIBLE);
                } else if (permisoRechazarP7) {
                    binding.can.setVisibility(View.VISIBLE);
                } else {
                    binding.acep.setVisibility(View.INVISIBLE);
                    binding.can.setVisibility(View.INVISIBLE);
                }

                Boolean atrasadasAutoriza = preferences.getBoolean("goneAutoriza", false);

                if (atrasadasAutoriza) {
                    binding.autorizalayout.setVisibility(View.GONE);
                } else {
                    binding.autorizalayout.setVisibility(View.VISIBLE);
                }

                binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                        getContext().startActivity(main);
                    }
                });

                binding.acep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        int accion = preferences.getInt("MODULO_7_TIPO_AUTORIZACION", 0);

                        if (accion == RECHAZA_ID || accion == SIN_AUTORIZACION) {
                            SharedPreferences.Editor editorMotivos = preferences.edit();
                            editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_7);
                            editorMotivos.apply();

                            FragmentDialogAceptar a = new FragmentDialogAceptar();
                            a.show(getChildFragmentManager(), "child");
                            a.setModuloAceptadoListener(new FragmentDialogAceptar.OnModuloAceptadoListener() {
                                @Override
                                public void onModuloAceptado(int modulo) {
                                    binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                    binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "Esta pantalla ya ha sido aceptada anteriormente",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

                binding.can.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        int accion = preferences.getInt("MODULO_7_TIPO_AUTORIZACION", 0);

                        if (accion == AUTORIZA_ID || accion == SIN_AUTORIZACION) {
                            SharedPreferences.Editor editorMotivos = preferences.edit();
                            editorMotivos.putInt("moduloAutorizaRechaza", MODULO_PANTALLA_7);
                            editorMotivos.apply();

                            FragmentDialogCancelar a = new FragmentDialogCancelar();
                            a.show(getChildFragmentManager(), "child");
                            a.setModuloCanceladoListener(new FragmentDialogCancelar.OnModuloRechazadoListener() {
                                @Override
                                public void onModuloRechazado(int modulo) {
                                    binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                    binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                                }
                            });
                        } else {
                            Toast.makeText(getContext(), "Esta pantalla ya ha sido rechazada anteriormente",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        } else if (position == 7) {

            FragmentAutoriza7Binding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_7, container, false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.resumenpuntuacion));

            getDatos(binding);

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                    getContext().startActivity(main);
                }
            });

        } else if (position == 8) {

            final FragmentAutoriza8Binding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_8, container, false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.resumenautorizacion));

            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            int atrasa = preferences.getInt("atrasa", 0);

            if (atrasa == 1) {
                binding.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            } else {
                binding.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }

            creaTablaResumenAutorizacion(binding);

            binding.can.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    autorizaRechazaMD(RECHAZA_MD, binding, resource);
                    binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.rechazado));

                }
            });


            binding.acep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    autorizaRechazaMD(AUTORIZA_MD, binding, resource);
                    binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.aprovado));

                }
            });

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                    getContext().startActivity(main);
                }
            });
        }
        return view;
    }

    String horaInicio, horaFinal;

    public void autorizaRechazaMD(final int tipoAccion, final FragmentAutoriza8Binding binding, final Resources resource) {
        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final String mdId = preferences.getString("mdId", "");
        final String usuarioId = preferences.getString("usuario", "");


        if (tipoAccion == RECHAZA_MD) {
            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            SharedPreferences.Editor editorFinalizar = preferences.edit();
            editorFinalizar.putInt("moduloAutorizaRechaza", MODULO_GENERAL);
            editorFinalizar.apply();

            FragmentDialogCancelar a = new FragmentDialogCancelar();
            a.show(getChildFragmentManager(), "child");

            a.setModuloCanceladoListener(new FragmentDialogCancelar.OnModuloRechazadoListener() {
                @Override
                public void onModuloRechazado(int modulo) {

                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int motivoId = preferences.getInt("MODULO_GENERAL_TIPO_AUTORIZACION_MOTIVO", 0);
                    String motivoIdS = String.valueOf(motivoId);
                    String comentarios = preferences.getString("MODULO_GENERAL_COMENTARIO", "");
                    int estatus = preferences.getInt("estatusId", 0);
                    if (estatus == 16) {
                        String puestoId = preferences.getString("puestoId", "");
                        String areaId = preferences.getString("areaId", "");
                        ProviderAnalistaCalidadOperativa.getInstance(getContext()).AnalistaCalidadOperativa(usuarioId, mdId, "7", "0", motivoIdS, comentarios, "1", puestoId, areaId, new ProviderAnalistaCalidadOperativa.AnalistaCalidadOperativaInterface() {
                            @Override
                            public void resolve(AnalistaCalidadOperativa analistaCalidadOperativa) {
                                if(analistaCalidadOperativa.getCodigo()==200){
                                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editorFinalizar = preferences.edit();
                                    editorFinalizar.putString("tituloFinalizar", "MD Rechazada");
                                    editorFinalizar.putString("descripcionFinalizar", "Se ha rechazado esta MD");
                                    editorFinalizar.apply();
                                    binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                                    binding.autorizaBoton.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                    FragmentDialogFinalizar a = new FragmentDialogFinalizar();
                                    a.show(getChildFragmentManager(), "child");
                                }else {
                                    binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                    Toast.makeText(getContext(), "Error al autorizar el módulo: " + analistaCalidadOperativa.getMensaje(),  Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void reject(Exception ex) {
                                binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                Toast.makeText(getContext(), "Error al conectarse al servicio que autoriza/rechaza la pantalla: ", Toast.LENGTH_LONG).show();
                            }
                        });

                    } else {
                        ProviderAutorizaFinal.getInstance(getContext()).autorizaFinal(mdId, usuarioId,
                                tipoAccion, motivoId, comentarios, new ProviderAutorizaFinal.AutorizaFinal() {
                                    @Override
                                    public void resolve(AutorizaResponse autorizaResponse) {
                                        if (autorizaResponse.getCodigo() == 200) {
                                            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editorFinalizar = preferences.edit();
                                            editorFinalizar.putString("tituloFinalizar", "MD Rechazada");
                                            editorFinalizar.putString("descripcionFinalizar", "Se ha rechazado esta MD");
                                            editorFinalizar.apply();
                                            binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                                            binding.autorizaBoton.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                            FragmentDialogFinalizar a = new FragmentDialogFinalizar();
                                            a.show(getChildFragmentManager(), "child");
                                        } else {
                                            binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                            Toast.makeText(getContext(), "Error al autorizar el módulo: " + autorizaResponse.getMensaje(),  Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    @Override
                                    public void reject(Exception e) {
                                        binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                        Toast.makeText(getContext(), "Error al conectarse al servicio que autoriza/rechaza la pantalla: ", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                }
            });
        } else {
            int estatus = preferences.getInt("estatusId", 0);
            if (estatus == 16) {
                String fechaAperturaTextGuardada = preferences.getString(mdId+"fechaApertura","");

                if (fechaAperturaTextGuardada.equals("")) {
                    Toast.makeText(getContext(), "Por favor pon una fecha de apertura en '1.DATOS DEL SITIO'", Toast.LENGTH_LONG).show();
                } else {
                    String puestoId = preferences.getString("puestoId", "");
                    String areaId = preferences.getString("areaId", "");
                    ProviderAnalistaCalidadOperativa.getInstance(getContext()).AnalistaCalidadOperativa(usuarioId, mdId, "7", "1", "0", " ", "1", puestoId, areaId, new ProviderAnalistaCalidadOperativa.AnalistaCalidadOperativaInterface() {
                        @Override
                        public void resolve(AnalistaCalidadOperativa analistaCalidadOperativa) {
                            if (analistaCalidadOperativa.getCodigo() == 200) {
                                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editorFinalizar = preferences.edit();
                                editorFinalizar.putString("tituloFinalizar", "MD Aceptada");
                                editorFinalizar.putString("descripcionFinalizar", "Se ha autorizado esta MD");
                                editorFinalizar.apply();
                                binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                binding.autorizaBoton.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                                FragmentDialogFinalizar a = new FragmentDialogFinalizar();
                                a.show(getChildFragmentManager(), "child");
                            } else {
                                binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                                binding.autorizaBoton.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                                Toast.makeText(getContext(), "Error al autorizar el módulo: " + analistaCalidadOperativa.getMensaje(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void reject(Exception ex) {

                        }
                    });
                }

            } else {
                ProviderAutorizaFinal.getInstance(getContext()).autorizaFinal(mdId, usuarioId, tipoAccion, 0, "", new ProviderAutorizaFinal.AutorizaFinal() {
                    @Override
                    public void resolve(AutorizaResponse autorizaResponse) {
                        if (autorizaResponse.getCodigo() == 200) {
                            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editorFinalizar = preferences.edit();
                            editorFinalizar.putString("tituloFinalizar", "MD Aceptada");
                            editorFinalizar.putString("descripcionFinalizar", "Se ha autorizado esta MD");
                            editorFinalizar.apply();
                            binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                            binding.autorizaBoton.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                            FragmentDialogFinalizar a = new FragmentDialogFinalizar();
                            a.show(getChildFragmentManager(), "child");
                        } else {
                            binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                            binding.autorizaBoton.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                            Toast.makeText(getContext(), "Error al autorizar el módulo: " + autorizaResponse.getMensaje(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void reject(Exception e) {
                        binding.rechazaBoton.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                        binding.autorizaBoton.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));

                        Toast.makeText(getContext(), "Error al conectarse al servicio que autoriza/rechaza la pantalla: ",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    public void creaTablaResumenAutorizacion(FragmentAutoriza8Binding binding) {
        Resources resource = getContext().getResources();
        TableLayout ll = binding.tablaResumen;
        TableRow row = new TableRow(getContext());
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        row.setPadding(0, 10, 0, 0);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams lpImg = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        lpImg.height = 40;
        LinearLayout.LayoutParams tamanioImg = new LinearLayout.LayoutParams(20, 20);
        row.setLayoutParams(lp);
        boolean esPantallaSinValidar = false;

        int widthCeldaDescripcion = (int) (900 * 0.4);
        int widthCeldaAceptados = (int) (900 * 0.3);
        int widthCeldaRechazados = (int) (900 * 0.3);

        TextView texto1 = new TextView(getContext());
        texto1.setText("");
        texto1.setWidth(widthCeldaDescripcion);
        texto1.setTextColor(resource.getColor(R.color.grisetxt));
        texto1.setPadding(0, 45, 5, 1);
        texto1.setLayoutParams(lp);
        texto1.setGravity(Gravity.LEFT);

        TextView aceptadosHeader = new TextView(getContext());
        aceptadosHeader.setText("Aceptados");
        String sourceString = "<b>" + "Aceptados" + "</b> ";
        aceptadosHeader.setText(Html.fromHtml(sourceString));
        aceptadosHeader.setWidth(widthCeldaAceptados);
        aceptadosHeader.setTextColor(resource.getColor(R.color.azul));
        aceptadosHeader.setPadding(0, 55, 5, 1);
        aceptadosHeader.setLayoutParams(lp);
        aceptadosHeader.setGravity(Gravity.CENTER);

        TextView rechazadosHeader = new TextView(getContext());
        String sourceString2 = "<b>" + "Rechazados" + "</b> ";
        rechazadosHeader.setText(Html.fromHtml(sourceString2));
        rechazadosHeader.setWidth(widthCeldaRechazados);
        rechazadosHeader.setTextColor(resource.getColor(R.color.azul));
        rechazadosHeader.setPadding(0, 55, 5, 1);
        rechazadosHeader.setLayoutParams(lp);
        rechazadosHeader.setGravity(Gravity.CENTER);

        row.addView(texto1);
        row.addView(aceptadosHeader);
        row.addView(rechazadosHeader);
        ll.addView(row, 0);

        row = new TableRow(getContext());
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        row.setPadding(0, 10, 0, 0);
        lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        lp.height = 85;
        row.setLayoutParams(lp);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        int modulo1 = preferences.getInt("MODULO_1_TIPO_AUTORIZACION", 0);
        int motivoId1 = preferences.getInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO", 0);
        int rechazoDefinitivo1 = preferences.getInt("MODULO_1_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);


        TextView sitioText = new TextView(getContext());
        sitioText.setText("Datos del sitio");
        sitioText.setWidth(widthCeldaDescripcion);
        sitioText.setTextColor(resource.getColor(R.color.grisetxt));
        sitioText.setPadding(10, 5, 5, 1);
        sitioText.setLayoutParams(lp);
        sitioText.setGravity(Gravity.LEFT);

        ImageView imgSubfactor = new ImageView(getContext());
        if (modulo1 == AUTORIZA_ID) {

            imgSubfactor.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
            imgSubfactor.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.SRC_IN);

        } else {
            imgSubfactor.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }
        imgSubfactor.setPadding(0, 0, 5, 1);
        imgSubfactor.setLayoutParams(lpImg);

        ImageView imgSubfactor1 = new ImageView(getContext());
        if (modulo1 == RECHAZA_ID) {
            if (rechazoDefinitivo1 == RECHAZO_DEFINITIVO_ID) {
                imgSubfactor1.setImageDrawable(resource.getDrawable(R.drawable.ic_cancel));
            } else {
                imgSubfactor1.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor1.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                imgSubfactor1.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor1.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        } else {

            imgSubfactor1.setColorFilter(ContextCompat.getColor(getContext(), R.color.blanco), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor1.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
            imgSubfactor1.setColorFilter(ContextCompat.getColor(getContext(), R.color.blanco), android.graphics.PorterDuff.Mode.SRC_IN);
            imgSubfactor1.setColorFilter(ContextCompat.getColor(getContext(), R.color.blanco), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        if (modulo1 == SIN_AUTORIZACION) {
            esPantallaSinValidar = true;
        }

        imgSubfactor1.setPadding(0, 0, 5, 1);
        imgSubfactor1.setLayoutParams(lpImg);

        row.addView(sitioText);
        row.addView(imgSubfactor);
        row.addView(imgSubfactor1);
        ll.addView(row, 1);

        row = new TableRow(getContext());
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        row.setPadding(0, 10, 0, 0);
        lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        lp.height = 85;
        row.setLayoutParams(lp);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        int modulo2 = preferences.getInt("MODULO_2_TIPO_AUTORIZACION", 0);
        int motivoId2 = preferences.getInt("MODULO_2_TIPO_AUTORIZACION_MOTIVO", 0);
        int rechazoDefinitivo2 = preferences.getInt("MODULO_2_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);

        row = new TableRow(getContext());
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        row.setPadding(0, 10, 0, 0);
        lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        lp.height = 85;
        row.setLayoutParams(lp);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        int modulo3 = preferences.getInt("MODULO_3_TIPO_AUTORIZACION", 0);
        int motivoId3 = preferences.getInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO", 0);
        int rechazoDefinitivo3 = preferences.getInt("MODULO_3_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);

        TextView superficieText = new TextView(getContext());
        superficieText.setText("Superficie");
        superficieText.setWidth(widthCeldaDescripcion);
        superficieText.setTextColor(resource.getColor(R.color.grisetxt));
        superficieText.setPadding(10, 5, 5, 1);
        superficieText.setLayoutParams(lp);
        superficieText.setGravity(Gravity.LEFT);

        ImageView imgSubfactor5 = new ImageView(getContext());
        if (modulo3 == AUTORIZA_ID) {
            imgSubfactor5.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor5.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
            imgSubfactor5.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor5.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            imgSubfactor5.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }
        imgSubfactor5.setPadding(0, 0, 5, 1);
        imgSubfactor5.setLayoutParams(lpImg);

        ImageView imgSubfactor6 = new ImageView(getContext());
        if (modulo3 == RECHAZA_ID) {
            if (rechazoDefinitivo3 == RECHAZO_DEFINITIVO_ID) {
                imgSubfactor6.setImageDrawable(resource.getDrawable(R.drawable.ic_cancel));
            } else {
                imgSubfactor6.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor6.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                imgSubfactor6.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor6.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        } else {
            imgSubfactor6.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }

        if (modulo3 == SIN_AUTORIZACION) {
            esPantallaSinValidar = true;
        }
        imgSubfactor6.setPadding(0, 0, 5, 1);
        imgSubfactor6.setLayoutParams(lpImg);

        row.addView(superficieText);
        row.addView(imgSubfactor5);
        row.addView(imgSubfactor6);
        ll.addView(row, 2);


        row = new TableRow(getContext());
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        row.setPadding(0, 10, 0, 0);
        lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        lp.height = 85;
        row.setLayoutParams(lp);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        int modulo4 = preferences.getInt("MODULO_4_TIPO_AUTORIZACION", 0);
        int motivoId4 = preferences.getInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO", 0);
        int rechazoDefinitivo4 = preferences.getInt("MODULO_4_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);

        TextView zonificacionText = new TextView(getContext());
        zonificacionText.setText("Zonificación");
        zonificacionText.setWidth(widthCeldaDescripcion);
        zonificacionText.setTextColor(resource.getColor(R.color.grisetxt));
        zonificacionText.setPadding(10, 5, 5, 1);
        zonificacionText.setLayoutParams(lp);
        zonificacionText.setGravity(Gravity.LEFT);

        ImageView imgSubfactor7 = new ImageView(getContext());
        if (modulo4 == AUTORIZA_ID) {
            imgSubfactor7.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor7.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
            imgSubfactor7.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor7.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            imgSubfactor7.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }
        imgSubfactor7.setPadding(0, 0, 5, 1);
        imgSubfactor7.setLayoutParams(lpImg);

        ImageView imgSubfactor8 = new ImageView(getContext());
        if (modulo4 == RECHAZA_ID) {
            if (rechazoDefinitivo4 == RECHAZO_DEFINITIVO_ID) {
                imgSubfactor8.setImageDrawable(resource.getDrawable(R.drawable.ic_cancel));
            } else {
                imgSubfactor8.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor8.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                imgSubfactor8.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor8.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        } else {
            imgSubfactor8.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }

        if (modulo4 == SIN_AUTORIZACION) {
            esPantallaSinValidar = true;
        }
        imgSubfactor8.setPadding(0, 0, 5, 1);
        imgSubfactor8.setLayoutParams(lpImg);

        row.addView(zonificacionText);
        row.addView(imgSubfactor7);
        row.addView(imgSubfactor8);
        ll.addView(row, 3);

        row = new TableRow(getContext());
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        row.setPadding(0, 10, 0, 0);
        lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        lp.height = 85;
        row.setLayoutParams(lp);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        int modulo5 = preferences.getInt("MODULO_5_TIPO_AUTORIZACION", 0);
        int motivoId5 = preferences.getInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO", 0);
        int rechazoDefinitivo5 = preferences.getInt("MODULO_5_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);

        TextView construccionText = new TextView(getContext());
        construccionText.setText("Construcción");
        construccionText.setWidth(widthCeldaDescripcion);
        construccionText.setTextColor(resource.getColor(R.color.grisetxt));
        construccionText.setPadding(10, 5, 5, 1);
        construccionText.setLayoutParams(lp);
        construccionText.setGravity(Gravity.LEFT);

        ImageView imgSubfactor9 = new ImageView(getContext());
        if (modulo5 == AUTORIZA_ID) {
            imgSubfactor9.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor9.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
            imgSubfactor9.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor9.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            imgSubfactor9.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }
        imgSubfactor9.setPadding(0, 0, 5, 1);
        imgSubfactor9.setLayoutParams(lpImg);

        ImageView imgSubfactor10 = new ImageView(getContext());
        if (modulo5 == RECHAZA_ID) {
            if (rechazoDefinitivo5 == RECHAZO_DEFINITIVO_ID) {
                imgSubfactor10.setImageDrawable(resource.getDrawable(R.drawable.ic_cancel));
            } else {
                imgSubfactor10.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor10.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                imgSubfactor10.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor10.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        } else {
            imgSubfactor10.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }

        if (modulo5 == SIN_AUTORIZACION) {
            esPantallaSinValidar = true;
        }
        imgSubfactor10.setPadding(0, 0, 5, 1);
        imgSubfactor10.setLayoutParams(lpImg);

        row.addView(construccionText);
        row.addView(imgSubfactor9);
        row.addView(imgSubfactor10);
        ll.addView(row, 4);

        row = new TableRow(getContext());
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        row.setPadding(0, 10, 0, 0);
        lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        lp.height = 85;
        row.setLayoutParams(lp);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        int modulo6 = preferences.getInt("MODULO_6_TIPO_AUTORIZACION", 0);
        int motivoId6 = preferences.getInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO", 0);
        int rechazoDefinitivo6 = preferences.getInt("MODULO_6_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);

        TextView generalidadesText = new TextView(getContext());
        generalidadesText.setText("Generalidades");
        generalidadesText.setWidth(widthCeldaDescripcion);
        generalidadesText.setTextColor(resource.getColor(R.color.grisetxt));
        generalidadesText.setPadding(10, 5, 5, 1);
        generalidadesText.setLayoutParams(lp);
        generalidadesText.setGravity(Gravity.LEFT);

        ImageView imgSubfactor11 = new ImageView(getContext());
        if (modulo6 == AUTORIZA_ID) {
            imgSubfactor11.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor11.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
            imgSubfactor11.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor11.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            imgSubfactor11.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }
        imgSubfactor11.setPadding(0, 0, 5, 1);
        imgSubfactor11.setLayoutParams(lpImg);

        ImageView imgSubfactor12 = new ImageView(getContext());
        if (modulo6 == RECHAZA_ID) {
            if (rechazoDefinitivo6 == RECHAZO_DEFINITIVO_ID) {
                imgSubfactor12.setImageDrawable(resource.getDrawable(R.drawable.ic_cancel));
            } else {
                imgSubfactor12.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor12.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                imgSubfactor12.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor12.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        } else {
            imgSubfactor12.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }

        if (modulo6 == SIN_AUTORIZACION) {
            esPantallaSinValidar = true;
        }
        imgSubfactor12.setPadding(0, 0, 5, 1);
        imgSubfactor12.setLayoutParams(lpImg);

        row.addView(generalidadesText);
        row.addView(imgSubfactor11);
        row.addView(imgSubfactor12);
        ll.addView(row, 5);

        row = new TableRow(getContext());
        row.setGravity(Gravity.CENTER_HORIZONTAL);
        row.setPadding(0, 10, 0, 0);
        lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        lp.height = 85;
        row.setLayoutParams(lp);

        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        int modulo7 = preferences.getInt("MODULO_7_TIPO_AUTORIZACION", 0);
        int motivoId7 = preferences.getInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO", 0);
        int rechazoDefinitivo7 = preferences.getInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);

        TextView peatonalText = new TextView(getContext());
        peatonalText.setText("Flujo peatonal");
        peatonalText.setWidth(widthCeldaDescripcion);
        peatonalText.setTextColor(resource.getColor(R.color.grisetxt));
        peatonalText.setPadding(10, 5, 5, 1);
        peatonalText.setLayoutParams(lp);
        peatonalText.setGravity(Gravity.LEFT);

        ImageView imgSubfactor13 = new ImageView(getContext());
        if (modulo7 == AUTORIZA_ID) {
            imgSubfactor13.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
            imgSubfactor13.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.MULTIPLY);
            imgSubfactor13.setColorFilter(ContextCompat.getColor(getContext(), R.color.event_color_03), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            imgSubfactor13.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }
        imgSubfactor13.setPadding(0, 0, 5, 1);
        imgSubfactor13.setLayoutParams(lpImg);

        ImageView imgSubfactor14 = new ImageView(getContext());
        if (modulo7 == RECHAZA_ID) {
            if (rechazoDefinitivo7 == RECHAZO_DEFINITIVO_ID) {
                imgSubfactor14.setImageDrawable(resource.getDrawable(R.drawable.ic_cancel));
            } else {

                imgSubfactor14.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor14.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                imgSubfactor14.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
                imgSubfactor14.setColorFilter(ContextCompat.getColor(getContext(), R.color.rojo), android.graphics.PorterDuff.Mode.SRC_IN);

            }
        } else {
            imgSubfactor14.setImageDrawable(resource.getDrawable(R.drawable.background_blanco));
        }

        if (modulo7 == SIN_AUTORIZACION) {
            esPantallaSinValidar = true;
        }
        imgSubfactor14.setPadding(0, 0, 5, 1);
        imgSubfactor14.setLayoutParams(lpImg);

        row.addView(peatonalText);
        row.addView(imgSubfactor13);
        row.addView(imgSubfactor14);
        ll.addView(row, 6);

        if (esPantallaSinValidar) {
            binding.rechazaBoton.setEnabled(false);
            binding.autorizaBoton.setEnabled(false);
            binding.rechazaBoton.setAlpha(0.4f);
            binding.autorizaBoton.setAlpha(0.4f);
        } else if (rechazoDefinitivo1 == 1 || rechazoDefinitivo2 == 1 || rechazoDefinitivo3 == 1 || rechazoDefinitivo4 == 1 ||
                rechazoDefinitivo5 == 1 || rechazoDefinitivo6 == 1 || rechazoDefinitivo7 == 1) {
            binding.layoutNomenclatura.setVisibility(View.VISIBLE);
            binding.rechazaBoton.setEnabled(true);
            binding.autorizaBoton.setEnabled(false);
            binding.rechazaBoton.setAlpha(1.0f);
            binding.autorizaBoton.setAlpha(0.4f);
        } else {
            binding.rechazaBoton.setEnabled(true);
            binding.autorizaBoton.setEnabled(true);
            binding.rechazaBoton.setAlpha(1.0f);
            binding.autorizaBoton.setAlpha(1.0f);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_usuario, menu);
        menu.add(0, 1, 1, Util.menuIcon(getResources().getDrawable(R.drawable.ic_vpn_key_black_24dp),
                getResources().getString(R.string.cambiarContra)));
        menu.add(0, 2, 2, Util.menuIcon(getResources().getDrawable(R.drawable.ic_exit_to_app_black_24dp),
                getResources().getString(R.string.salir)));
    }

    /**
     * Método que tiene la acción del menu posterior derecha
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case 1:
                return true;
            case 2:
                Util.cerrarSesion(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    String mensaje = null;

    ArrayList<Peatonal> peatonales;
    AdapterListaHoras adapterHoras;

    public void listaPeatonal(final FragmentAutorizaEditar6Binding binding) {

        final Resources resource = getContext().getResources();
        final ArrayList<String> horarios = new ArrayList<>();
        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdId = preferences.getString("mdId", "");
        String usuarioId = preferences.getString("usuario", "");

        ProviderDatosPeatonal.getInstance(getContext()).obtenerDatosPeatonal(mdId, usuarioId, new ProviderDatosPeatonal.ConsultaPeatonal() {
            @Override
            public void resolve(Peatonales peatonal) {

                peatonales = new ArrayList<>();

                if (peatonal != null && peatonal.getCodigo() == 200) {
                    // int sumaPuntuacion = 0;

                    for (int i = 0; i < peatonal.getConteos().size(); i++) {
                        for (int j = 0; j < peatonal.getConteos().get(i).getDetalle().size(); j++) {

                            peatonales.add(new Peatonal(j,
                                    peatonal.getConteos().get(i).getDetalle().get(j).getFecha(),
                                    Integer.valueOf(peatonal.getConteos().get(i).getDetalle().get(j).getValor()),
                                    0.0, 0.0, peatonal.getConteos().get(i).getDetalle().get(j).getNombreGenerador()));
                        }
                        // sumaPuntuacion += peatonal.getConteos().get(i).getPuntuacion();
                    }

                    if (peatonal.getConteos() != null && peatonal.getConteos().size() > 0) {
                        binding.promedio.setText("Promedio: " + peatonal.getConteos().get(0).getPromedioPeatonal());
                    } else {
                        binding.promedio.setText("Promedio: 0");
                    }
                    binding.recyclerPeatonal.setHasFixedSize(true);
                    AdapterAutorizaPeatonal adapter = new AdapterAutorizaPeatonal(getContext(), ALPHABETICAL_COMPARATOR, n);
                    binding.recyclerPeatonal.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.recyclerPeatonal.setAdapter(adapter);
                    adapter.edit().replaceAll(peatonales).commit();
                    adapter.notifyItemRangeRemoved(0, adapter.getItemCount());

                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorPeatonales = preferences.edit();
                    // editorPeatonales.putInt("puntajePeatonales", sumaPuntuacion);
                    // editorPeatonales.putInt("puntajeTotalPeatonales", peatonal.getPuntoFac() != null ? Integer.parseInt(peatonal.getPuntoFac()) : 0);
                    if (peatonal.getValidado() == 1) {
                        if (peatonal.getDetallesValidacion() != null && peatonal.getDetallesValidacion().size() > 0 && peatonal.getDetallesValidacion().get(0).getMOTIVORECHAZOID() == 0) {
                            binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                            binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION", AUTORIZA_ID);
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO", 0);
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                        } else {
                            binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                            binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION", RECHAZA_ID);
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO", peatonal.getDetallesValidacion().get(0).getMOTIVORECHAZOID());
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", peatonal.getDetallesValidacion().get(0).getRECHAZODEFINITIVO());
                        }
                    } else {
                        binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                        binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                        editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION", SIN_AUTORIZACION);
                        editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO", 0);
                        editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                    }
                    if (peatonal.getTip().size() > 0) {
                        StringBuffer tipMod7 = new StringBuffer("");
                        for (Peatonales.Tip tip : peatonal.getTip()) {
                            tipMod7.append(tip.getDetalle() + "\n");
                        }
                        editorPeatonales.putString("tip_modulo_7", tipMod7.toString());
                    } else {
                        editorPeatonales.putString("tip_modulo_7", "Agrega un tip a esta pantalla");
                    }
                    editorPeatonales.apply();

                } else {
                    Toast.makeText(getContext(), "Error al obtener los datos",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    public void listaPeatonal(final FragmentAutoriza6Binding binding) {
        final Resources resource = getContext().getResources();
        final ArrayList<String> horarios = new ArrayList<>();
        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdId = preferences.getString("mdId", "");
        String usuarioId = preferences.getString("usuario", "");
        final String nombreSitio = preferences.getString("nombreSitio", "");

        ProviderDatosPeatonal.getInstance(getContext()).obtenerDatosPeatonal(mdId, usuarioId, new ProviderDatosPeatonal.ConsultaPeatonal() {
            @Override
            public void resolve(Peatonales peatonal) {

                peatonales = new ArrayList<>();

                if (peatonal != null && peatonal.getCodigo() == 200) {
                    //int sumaPuntuacion = 0;

                    for (int i = 0; i < peatonal.getConteos().size(); i++) {
                        for (int j = 0; j < peatonal.getConteos().get(i).getDetalle().size(); j++) {

                            peatonales.add(new Peatonal(j,
                                    peatonal.getConteos().get(i).getDetalle().get(j).getFecha(),
                                    Integer.valueOf(peatonal.getConteos().get(i).getDetalle().get(j).getValor()),
                                    0.0, 0.0, peatonal.getConteos().get(i).getDetalle().get(j).getNombreGenerador()));
                        }
                        // sumaPuntuacion += peatonal.getConteos().get(i).getPuntuacion();
                    }

                    if (peatonal.getConteos() != null && peatonal.getConteos().size() > 0) {
                        binding.promedioPeatonal.setText("Promedio: " + peatonal.getConteos().get(0).getPromedioPeatonal());
                    } else {
                        binding.promedioPeatonal.setText("Promedio: 0");
                    }
                    binding.recyclerPeatonal.setHasFixedSize(true);
                    AdapterAutorizaPeatonal adapter = new AdapterAutorizaPeatonal(getContext(), ALPHABETICAL_COMPARATOR, n);
                    binding.recyclerPeatonal.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.recyclerPeatonal.setAdapter(adapter);
                    adapter.edit().replaceAll(peatonales).commit();
                    adapter.notifyItemRangeRemoved(0, adapter.getItemCount());

                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorPeatonales = preferences.edit();
                    // editorPeatonales.putInt("puntajePeatonales", sumaPuntuacion);
                    //editorPeatonales.putInt("puntajeTotalPeatonales", peatonal.getPuntoFac() != null ? Integer.parseInt(peatonal.getPuntoFac()) : 0);
                    if (peatonal.getValidado() == 1) {
                        if (peatonal.getDetallesValidacion() != null && peatonal.getDetallesValidacion().size() > 0 && peatonal.getDetallesValidacion().get(0).getMOTIVORECHAZOID() == 0) {
                            binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprovado));
                            binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION", AUTORIZA_ID);
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO", 0);
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                        } else {
                            binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                            binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.rechazado));
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION", RECHAZA_ID);
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO", peatonal.getDetallesValidacion().get(0).getMOTIVORECHAZOID());
                            editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", peatonal.getDetallesValidacion().get(0).getRECHAZODEFINITIVO());
                        }
                    } else {
                        binding.aceptar.setImageDrawable(resource.getDrawable(R.drawable.aprov_blanco));
                        binding.cancelar.setImageDrawable(resource.getDrawable(R.drawable.recha_blanco));
                        editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION", SIN_AUTORIZACION);
                        editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO", 0);
                        editorPeatonales.putInt("MODULO_7_TIPO_AUTORIZACION_MOTIVO_DEFINITIVO", 0);
                    }
                    if (peatonal.getTip().size() > 0) {
                        StringBuffer tipMod7 = new StringBuffer("");
                        for (Peatonales.Tip tip : peatonal.getTip()) {
                            tipMod7.append(tip.getDetalle() + "\n");
                        }
                        editorPeatonales.putString("tip_modulo_7", tipMod7.toString());
                    } else {
                        editorPeatonales.putString("tip_modulo_7", "Agrega un tip a esta pantalla");
                    }
                    editorPeatonales.apply();

                } else {
                    Toast.makeText(getContext(), "Error al obtener los datos",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });


    }

    private static final Comparator<Peatonal> ALPHABETICAL_COMPARATOR = new Comparator<Peatonal>() {
        @Override
        public int compare(Peatonal a, Peatonal b) {
            return a.getNumConteo().compareTo(b.getNumConteo());
        }
    };

    @Override
    public void onAutorizaSelect(Peatonal model) {

    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private BitmapDescriptor getBitmapDescriptor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            VectorDrawable vectorDrawable = (VectorDrawable) getContext().getDrawable(id);

            int h = vectorDrawable.getIntrinsicHeight();
            int w = vectorDrawable.getIntrinsicWidth();

            vectorDrawable.setBounds(0, 0, w, h);

            Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bm);
            vectorDrawable.draw(canvas);

            return BitmapDescriptorFactory.fromBitmap(bm);

        } else {
            return BitmapDescriptorFactory.fromResource(id);
        }
    }

    long tiempo = 0;

    private void showElapsedTime(FragmentAutorizaEditar6Binding binding) {
        long elapsedMillis = SystemClock.elapsedRealtime() - binding.peatonalConteo.chronometer1.getBase();
        tiempo = elapsedMillis / 1000;
    }

    Boolean permisoP1 = false;
    Boolean permisoP2 = false;
    Boolean permisoP3 = false;
    Boolean permisoP4 = false;
    Boolean permisoP5 = false;
    Boolean permisoP6 = false;
    Boolean permisoP7 = false;
    Boolean permisoEditar7 = false;


    Boolean permisoRechazarP1 = false;
    Boolean permisoRechazarP2 = false;
    Boolean permisoRechazarP3 = false;
    Boolean permisoRechazarP4 = false;
    Boolean permisoRechazarP5 = false;
    Boolean permisoRechazarP6 = false;
    Boolean permisoRechazarP7 = false;
    Boolean permisoRechazarEditar7 = false;

    Boolean permisoEditar1 = false;
    Boolean permisoEditar2 = false;
    Boolean permisoEditar6 = false;


    public static boolean isHourInInterval(String target, String start, String end) {
        return ((target.compareTo(start) >= 0) && (target.compareTo(end) <= 0));
    }

    public void setPermisos(ArrayList<Permiso> permisos, String tipoPantalla) {
        if (permisos != null) {
            for (int i = 0; i < permisos.size(); i++) {
                if (permisos.get(i).getFimoduloid() == MODULO_ASIGNADAS) {
                    if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_1))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiteautorizar() == 1) {
                        permisoP1 = true;

                        if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_1))
                                && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                                && permisos.get(i).getPermiteeditar() == 1) {
                            permisoEditar1 = true;
                            break;
                        }
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_2))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiteautorizar() == 1) {
                        permisoP2 = true;

                        if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_2))
                                && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                                && permisos.get(i).getPermiteeditar() == 1) {
                            permisoEditar2 = true;
                            break;
                        }
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_3))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiteautorizar() == 1) {
                        permisoP3 = true;
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_4))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiteautorizar() == 1) {
                        permisoP4 = true;
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_5))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiteautorizar() == 1) {
                        permisoP5 = true;
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_6))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiteautorizar() == 1) {
                        permisoP6 = true;
                        if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_6))
                                && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                                && permisos.get(i).getPermiteeditar() == 1) {
                            permisoEditar6 = true;
                            break;
                        }
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_7))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiteautorizar() == 1) {
                        permisoP7 = true;

                        if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_7))
                                && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                                && permisos.get(i).getPermiteeditar() == 1) {
                            permisoEditar7 = true;
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }


    public void setPermisosRechazar(ArrayList<Permiso> permisos, String tipoPantalla) {
        if (permisos != null) {
            for (int i = 0; i < permisos.size(); i++) {
                if (permisos.get(i).getFimoduloid() == MODULO_ASIGNADAS) {
                    if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_1))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiterechazar() == 1) {
                        permisoRechazarP1 = true;
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_2))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiterechazar() == 1) {
                        permisoRechazarP2 = true;
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_3))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiterechazar() == 1) {
                        permisoRechazarP3 = true;
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_4))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiterechazar() == 1) {
                        permisoRechazarP4 = true;
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_5))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiterechazar() == 1) {
                        permisoRechazarP5 = true;
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_6))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiterechazar() == 1) {
                        permisoRechazarP6 = true;
                        break;
                    } else if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_7))
                            && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                            && permisos.get(i).getPermiterechazar() == 1) {
                        permisoRechazarP7 = true;

                        if (tipoPantalla.equals(String.valueOf(MODULO_PANTALLA_7))
                                && tipoPantalla.equals(String.valueOf(permisos.get(i).getFisubmodulo()))
                                && permisos.get(i).getPermiteeditar() == 1) {
                            permisoRechazarEditar7 = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    String puntuacion, categoria;
    ArrayList<DatosPuntuacion.Factore> factoresMacro;
    ArrayList<DatosPuntuacion.Factore> factoresMicro;

    public void getDatos(final FragmentAutoriza7Binding binding) {
        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdid = preferences.getString("mdId", "");
        String usuario = preferences.getString("usuario", "");

        int atrasa = preferences.getInt("atrasa", 0);
        if (atrasa == 1) {
            binding.view3.setBackgroundColor(Color.parseColor("#E4B163"));
        } else {
            binding.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
        }

        ProviderConsultaFinaliza.getInstance(getContext()).obtenerPuntos(mdid, usuario, new ProviderConsultaFinaliza.ConsultaPuntos() {
            @Override
            public void resolve(DatosPuntuacion datosPuntuacion) {
                if (datosPuntuacion.getCodigo() == 200) {
                    factoresMacro = new ArrayList<>();
                    factoresMicro = new ArrayList<>();

                    SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);

                    categoria = datosPuntuacion.getNomcategoria();
                    for (int i = 0; i < datosPuntuacion.getFactores().size(); i++) {
                        if (datosPuntuacion.getFactores().get(i).getNombrenivel().equals("TOTAL")) {
                            puntuacion = datosPuntuacion.getFactores().get(i).getPuntuacion().toString();
                        }
                    }

                    for (int i = 0; i < datosPuntuacion.getFactores().size(); i++) {
                        if (datosPuntuacion.getFactores().get(i).getRangoubica() != null) {
                            if (datosPuntuacion.getFactores().get(i).getRangoubica().equals(getString(R.string.micro_ub))) {
                                factoresMacro.add(datosPuntuacion.getFactores().get(i));
                            } else {
                                factoresMicro.add(datosPuntuacion.getFactores().get(i));
                            }
                        } else {
                            factoresMicro.add(datosPuntuacion.getFactores().get(i));
                        }
                    }

                    if (factoresMicro.size() <= 0) {
                        binding.tituloMicro.setVisibility(View.GONE);
                    }

                    if (factoresMacro.size() <= 0) {
                        binding.tituloMacro.setVisibility(View.GONE);
                    }

                    generarDetallesMicro(binding, factoresMicro);
                    generarDetallesMacro(binding, factoresMacro);

                    final SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("categoria", categoria);
                    editor.putString("puntuacion", puntuacion);
                    editor.apply();
                }


            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    public void generarDetallesMacro(FragmentAutoriza7Binding binding, ArrayList<DatosPuntuacion.Factore> datosPuntuacion) {

        Resources resource = this.getResources();
        binding.factores.removeAllViews();
        TableRow rowPlomo = new TableRow(getContext());
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        int paddingDp = 2;

        float density = getResources().getDisplayMetrics().density;
        int paddingPixel = (int) (paddingDp * density);

        for (int i = 0; i < datosPuntuacion.size(); i++) {

            TableRow tbrow = new TableRow(getContext());
            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));
            TextView t1v1 = new TextView(getContext());
            t1v1.setTextSize(12);
            t1v1.setText(datosPuntuacion.get(i).getNombrenivel() + "");
            t1v1.setTextColor(resource.getColor(R.color.grisetxt));
            t1v1.setPadding(0, paddingPixel, 0, 5);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams(new TableRow.LayoutParams(660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0));
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(getContext());
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion() + "");
            t3v1.setTextColor(resource.getColor(R.color.grisetxt));
            t3v1.setGravity(Gravity.RIGHT);
            t3v1.setLayoutParams(new TableRow.LayoutParams(50,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0));
            tbrow.addView(t3v1);


            TextView t3v2 = new TextView(getContext());
            t3v2.setTextSize(12);
            if (datosPuntuacion.get(i).getTotalxfactor() != null) {
                t3v2.setText("/" + datosPuntuacion.get(i).getTotalxfactor() + "");
            } else {
                binding.tituloMacro.setVisibility(View.GONE);
                binding.tituloMicro.setVisibility(View.GONE);
            }
            t3v2.setTextColor(resource.getColor(R.color.grisetxt));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams(new TableRow.LayoutParams(75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0));
            tbrow.addView(t3v2);


            binding.factores.addView(tbrow);
        }
    }

    public void generarDetallesMicro(FragmentAutoriza7Binding binding, ArrayList<DatosPuntuacion.Factore> datosPuntuacion) {

        Resources resource = this.getResources();
        binding.factoresMicro.removeAllViews();
        TableRow rowPlomo = new TableRow(getContext());
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        int paddingDp = 2;

        float density = getResources().getDisplayMetrics().density;
        int paddingPixel = (int) (paddingDp * density);

        for (int i = 0; i < datosPuntuacion.size(); i++) {

            TableRow tbrow = new TableRow(getContext());
            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));
            TextView t1v1 = new TextView(getContext());
            t1v1.setTextSize(12);
            t1v1.setText(datosPuntuacion.get(i).getNombrenivel() + "");
            t1v1.setTextColor(resource.getColor(R.color.grisetxt));
            t1v1.setPadding(0, paddingPixel, 0, 5);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams(new TableRow.LayoutParams(660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0));
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(getContext());
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion() + "");
            t3v1.setTextColor(resource.getColor(R.color.grisetxt));
            t3v1.setGravity(Gravity.RIGHT);
            t3v1.setLayoutParams(new TableRow.LayoutParams(50,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0));
            tbrow.addView(t3v1);

            TextView t3v2 = new TextView(getContext());
            t3v2.setTextSize(12);
            if (datosPuntuacion.get(i).getTotalxfactor() != null) {
                t3v2.setText("/" + datosPuntuacion.get(i).getTotalxfactor() + "");
            } else {
                binding.tituloMacro.setVisibility(View.GONE);
                binding.tituloMicro.setVisibility(View.GONE);
            }
            t3v2.setTextColor(resource.getColor(R.color.grisetxt));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams(new TableRow.LayoutParams(75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0));
            tbrow.addView(t3v2);

            binding.factoresMicro.addView(tbrow);
        }
    }


}
