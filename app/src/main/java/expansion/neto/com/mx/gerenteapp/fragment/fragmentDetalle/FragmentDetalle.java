package expansion.neto.com.mx.gerenteapp.fragment.fragmentDetalle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.VectorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza1Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza2Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza3Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza4Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza5Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza6Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza7Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutorizaBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FragmentAutoriza;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FragmentDialogAceptar;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FragmentDialogCancelar;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FragmentInicioAutoriza;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentInicioProceso;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazo.FragmentInicioRechazadas;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosConstruccion;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosPuntuacion;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosSitio;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.GeneralidadesSitio;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonales;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Points;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Propietario;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Superficie;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Zonificacion;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderConsultaFinaliza;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosConstruccion;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosGeneralidadesSitio;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosPeatonal;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosPropietario;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosSitio;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosSuperficie;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosZonificacion;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AdapterAutorizaPeatonal;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AdapterListaHoras;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AutorizaHolderPeatonal;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaCompetencia;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaGeneradores;
import expansion.neto.com.mx.gerenteapp.ui.agenda.ActivityNotificaciones;


public class FragmentDetalle extends Fragment implements
        AutorizaHolderPeatonal.Listener {

    private View view;
    private static final String ARG_POSITION = "position";
    private int position;
    AdapterListaCompetencia adapter;
    AdapterListaGeneradores adapter2;
    SharedPreferences preferences;
    AutorizaHolderPeatonal.Listener n;
    String urlFrente = "";
    String urlLateral1 = "";
    String urlLateral2 = "";
    String mensaje = null;

    private final int BACK_EN_PROCESO = 1;
    private final int BACK_RECHAZO = 2;

    List<Zonificacion.Detalle> list = new ArrayList<>();
    List<Zonificacion.Detalle> listGeneradores = new ArrayList<>();

    private LatLng mCenterLatLong;

    private OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            if(lat!=null){

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
            }else{
                //  Toast.makeText(getContext(), "Error al cargar los datos",
                //        Toast.LENGTH_SHORT).show();
            }

        }
    };

    BitmapDescriptor icon;
    private OnMapReadyCallback onMapReadyCallbackZonificacion = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            if(puntosCompentencias!=null){

                for(int i = 0;i<puntosCompentencias.size();i++){

                    if(puntosCompentencias.get(i).getIcono()==1){
                        icon = getBitmapDescriptor(R.drawable.bbb);
                    }else if(puntosCompentencias.get(i).getIcono()==2){
                        icon = getBitmapDescriptor(R.drawable.oxxo);
                    }else if(puntosCompentencias.get(i).getIcono()==3){
                        icon = getBitmapDescriptor(R.drawable.bodegaa);
                    }else if(puntosCompentencias.get(i).getIcono()==4){
                        icon = getBitmapDescriptor(R.drawable.abarrotes);
                    }else if(puntosCompentencias.get(i).getIcono()==5){
                        icon = getBitmapDescriptor(R.drawable.g_iglesia);
                    }else if(puntosCompentencias.get(i).getIcono()==6){
                        icon = getBitmapDescriptor(R.drawable.g_mercado);
                    }else if(puntosCompentencias.get(i).getIcono()==7){
                        icon = getBitmapDescriptor(R.drawable.escuela);
                    }else if(puntosCompentencias.get(i).getIcono()==8){
                        icon = getBitmapDescriptor(R.drawable.g_busstop);
                    }else if(puntosCompentencias.get(i).getIcono()==9){
                        icon = getBitmapDescriptor(R.drawable.otros);
                    }else if(puntosCompentencias.get(i).getIcono()==10){
                        icon = getBitmapDescriptor(R.drawable.neto);
                    }else if(puntosCompentencias.get(i).getIcono()==11){
                        icon = getBitmapDescriptor(R.drawable.g_recauderia);
                    }else if(puntosCompentencias.get(i).getIcono()==12){
                        icon = getBitmapDescriptor(R.drawable.g_comida);
                    }else if(puntosCompentencias.get(i).getIcono()==13){
                        icon = getBitmapDescriptor(R.drawable.g_mercado);
                    }else if(puntosCompentencias.get(i).getIcono()==14){
                        icon = getBitmapDescriptor(R.drawable.g_tianguis);
                    }else if(puntosCompentencias.get(i).getIcono()==15){
                        icon = getBitmapDescriptor(R.drawable.g_tortilleria);
                    }else if(puntosCompentencias.get(i).getIcono()==16){
                        icon = getBitmapDescriptor(R.drawable.g_carniceria);
                    }else if(puntosCompentencias.get(i).getIcono()==17){
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

            }else{
                // Toast.makeText(getContext(), "Error al cargar los datos",
                //       Toast.LENGTH_SHORT).show();
            }

        }
    };


    private static boolean noti = false;

    public static FragmentDetalle newInstance(int position, boolean notificacion) {
        FragmentDetalle f = new FragmentDetalle();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        noti = notificacion;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    private SlideUp slideCompetencia;
    private SlideUp slideGenerador;
    FragmentAutorizaBinding binding;
    FragmentAutoriza3Binding bindingZonificacion;
    FragmentAutoriza2Binding bindingSuperficie;
    FragmentAutoriza1Binding bindingPropietario;
    private Double lat, lot;
    ArrayList<Points> puntosCompentencias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final Resources resource = getContext().getResources();
        if (position == 0) {

            mensaje = "fragment 1";
            final FragmentAutorizaBinding binding;
            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_autoriza,container,false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.datossitio));

            //binding.help.setVisibility(View.VISIBLE);
            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId","");
            String usuarioId = preferences.getString("usuario","");
            binding.escogeSitio.setEnabled(false);

            ProviderDatosSitio.getInstance(getContext()).obtenerDatosSitio(mdId, usuarioId, new ProviderDatosSitio.ConsultaDatosSitio() {
                @Override
                public void resolve(DatosSitio datosSitio) {

                    if(datosSitio.getDatossitio()!= null && datosSitio.getCodigo()==200){
                        binding.nombreSitioTitulo.setText(datosSitio.getDatossitio().get(0).getNombreSitio());

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("nombreSitio", datosSitio.getDatossitio().get(0).getNombreSitio().toString());
                        editor.apply();

                        binding.categoria.setText(datosSitio.getDatossitio().get(0).getCategoria()+"");
                        binding.puntuacion.setText(datosSitio.getDatossitio().get(0).getTotalMdId()+"");
                        binding.direccion.setText(datosSitio.getDatossitio().get(0).getDireccion()+"");
                        binding.estado.setText(datosSitio.getDatossitio().get(0).getEstado()+"");
                        lat = Double.valueOf(datosSitio.getDatossitio().get(0).getLatitud());
                        lot = Double.valueOf(datosSitio.getDatossitio().get(0).getLongitud());
                        if(datosSitio.getDatossitio().get(0).getTipoUbicacionMD().equals("RURAL")){
                            binding.escogeSitio.setChecked(true);
                            binding.rural.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.azul));
                        }else{
                            binding.escogeSitio.setChecked(false);
                            binding.ciudad.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.azul));
                        }
                        if(datosSitio.getDatossitio().get(0).getCategoria().equals("A")) {
                            binding.estrella1.setVisibility(View.VISIBLE);
                            binding.estrella2.setVisibility(View.VISIBLE);
                            binding.estrella3.setVisibility(View.VISIBLE);
                        } else if(datosSitio.getDatossitio().get(0).getCategoria().equals("B")) {
                            binding.estrella1.setVisibility(View.VISIBLE);
                            binding.estrella2.setVisibility(View.VISIBLE);
                            binding.estrella3.setVisibility(View.GONE);
                        } else if(datosSitio.getDatossitio().get(0).getCategoria().equals("C")) {
                            binding.estrella1.setVisibility(View.VISIBLE);
                            binding.estrella2.setVisibility(View.GONE);
                            binding.estrella3.setVisibility(View.GONE);
                        }

                        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                                .findFragmentById(R.id.map);

                        mapFragment.getMapAsync(onMapReadyCallback);

                    }else{
                        Toast.makeText(getContext(), "Error al cargar los datos",
                                Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void reject(Exception e) { }
            });

            binding.aceptar.setVisibility(View.GONE);
            binding.cancelar.setVisibility(View.GONE);

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int tipoBack = preferences.getInt("TIPO_BACK",0);
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else
                    if(tipoBack == BACK_EN_PROCESO) {
                        Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                        getContext().startActivity(main);
                    } else if(tipoBack == BACK_RECHAZO) {
                        Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                        getContext().startActivity(main);
                    }
                }
            });

        } else if (position == 1) {

            final FragmentAutoriza1Binding binding;
            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_autoriza_1,container,false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.datospropietario));



            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId","");
            String usuarioId = preferences.getString("usuario","");

            ProviderDatosPropietario.getInstance(getContext())
                    .obtenerDatosPropietario(mdId, usuarioId, new ProviderDatosPropietario.ConsultaDatosPropietario() {
                        @Override
                        public void resolve(Propietario propietario) {

                            if(propietario.getCodigo()==200){
                                binding.nombrepropietario.setText(propietario.getNombrePropietario()+ " "+
                                        propietario.getAPaternoPropietario()+ " "+ propietario.getAMaternoPropietario());
                                binding.telefono.setText(PhoneNumberUtils.formatNumber(propietario.getTelefono(), Locale.getDefault().getCountry()));
                                binding.email.setText(propietario.getMail());
                                if(propietario.getRentaMasLocales() > 0) {
                                    binding.robotoTextView11.setText("YA RENTA A NETO");
                                } else {
                                    binding.robotoTextView11.setText("NO RENTA A NETO");
                                }

                                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editorPropietario = preferences.edit();
                                if(propietario.getTip() != null && propietario.getTip().size() > 0) {
                                    StringBuffer tipMod2 = new StringBuffer("");
                                    for(Propietario.Tip tip : propietario.getTip()) {
                                        tipMod2.append(tip.getDetalle() + "\n");
                                    }
                                    editorPropietario.putString("tip_modulo_2", tipMod2.toString());
                                } else {
                                    editorPropietario.putString("tip_modulo_2", "Agrega un tip a esta pantalla");
                                }
                                editorPropietario.apply();
                            }else{
                                Toast.makeText(getContext(), "Error al cargar los datos",
                                        Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void reject(Exception e) {

                        }
                    });

            binding.aceptar.setVisibility(View.GONE);
            binding.cancelar.setVisibility(View.GONE);


            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int tipoBack = preferences.getInt("TIPO_BACK",0);

                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else

                    if(tipoBack == BACK_EN_PROCESO) {
                        Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                        getContext().startActivity(main);
                    } else if(tipoBack == BACK_RECHAZO) {
                        Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                        getContext().startActivity(main);
                    }
                }
            });


        }else if (position == 2) {

            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId","");
            String usuarioId = preferences.getString("usuario","");
            final String nombreSitio = preferences.getString("nombreSitio","");

            final FragmentAutoriza2Binding binding;
            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_autoriza_2,container,false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.datossuperficie));
            binding.escogeEsquina.setEnabled(false);
            ProviderDatosSuperficie.getInstance(getContext())
                    .obtenerDatosSuperficie(mdId, usuarioId, new ProviderDatosSuperficie.ConsultaDatosSuperficie() {
                        @Override
                        public void resolve(final Superficie superficie) {

                            if(superficie.getCodigo()==200){
                                preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                int valorFoto = 0;
                                int valorFrente = 0;
                                int valorFondo = 0;
                                int valorEsquina = 0;
                                for(int i = 0;i<superficie.getNiveles().size();i++){
                                    if(superficie.getNiveles().get(i).getNivel()==4 ||
                                            superficie.getNiveles().get(i).getNivel()==5){
                                        Picasso.get().load(superficie.getNiveles().get(i).getImgFrenteId()).into(binding.imagen);
                                        valorFoto = i;
                                        valorFondo = i;
                                    }

                                    if(superficie.getNiveles().get(i).getNivel()==6 ||
                                            superficie.getNiveles().get(i).getNivel()==7){
                                        valorFrente = i;
                                    }

                                    if(superficie.getNiveles().get(i).getNivel()==8){
                                        valorEsquina = i;
                                    }
                                }

                                int esquina = superficie.getNiveles().get(valorEsquina).getValorreal();

                                if(esquina==1){
                                    binding.escogeEsquina.setChecked(true);
                                }else{
                                    binding.escogeEsquina.setChecked(false);
                                }
                                getContext().getSharedPreferences("datosSuperficie", 0).edit().clear().apply();
                                String superficieS = String.valueOf(superficie.getNiveles().get(valorFrente).getValorreal());
                                superficieS = superficieS.replace(" ", "");

                                String fondoS = String.valueOf(superficie.getNiveles().get(valorFondo).getFondo());
                                fondoS = fondoS.replace(" ", "");

                                binding.frente.setText("  "+superficieS);
                                binding.profundidad.setText("  "+fondoS);

                                String total = String.valueOf((Integer.valueOf(superficieS)
                                        *(Integer.valueOf(fondoS))));
                                binding.total.setText("  "+total+"mts2");

                                binding.frontal.setAlpha(1.0f);
                                binding.lateral1.setAlpha(0.35f);
                                binding.lateral2.setAlpha(0.35f);
                                binding.robotoTextView2.setText(nombreSitio);

                                final int finalValorFoto = valorFoto;

                                if(!superficie.getNiveles().get(finalValorFoto).getImgFrenteId().equals("")){
                                    Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgFrenteId()).into(binding.imagen);
                                }

                                binding.frontal.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgFrenteId()).into(binding.imagen);
                                        binding.frontal.setAlpha(1.0f);
                                        binding.lateral1.setAlpha(0.35f);
                                        binding.lateral2.setAlpha(0.35f);
                                        if(superficie.getNiveles().get(finalValorFoto).getImgFrenteId().length()>0){
                                            if(urlFrente.length()>0){
                                                Picasso.get().load(urlFrente).into(binding.imagen);
                                            } else {
                                                Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgFrenteId()).into(binding.imagen);
                                            }

                                        }else{

                                        }
                                    }
                                });

                                binding.lateral1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgLateral1Id()).into(binding.imagen);
                                        binding.lateral1.setAlpha(1.0f);
                                        binding.frontal.setAlpha(0.35f);
                                        binding.lateral2.setAlpha(0.35f);
                                        if(superficie.getNiveles().get(finalValorFoto).getImgLateral1Id().length()>0){
                                            if(urlLateral1.length()>0){
                                                Picasso.get().load(urlLateral1).into(binding.imagen);
                                            } else {
                                                Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgLateral1Id()).into(binding.imagen);
                                            }
                                        }else{

                                        }
                                    }
                                });


                                urlFrente = superficie.getNiveles().get(finalValorFoto).getImgFrenteId();
                                urlLateral1 = superficie.getNiveles().get(finalValorFoto).getImgLateral1Id();
                                urlLateral2 = superficie.getNiveles().get(finalValorFoto).getImgLateral2Id();

                                binding.lateral2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        binding.lateral2.setAlpha(1.0f);
                                        binding.frontal.setAlpha(0.35f);
                                        binding.lateral1.setAlpha(0.35f);
                                        if(superficie.getNiveles().get(finalValorFoto).getImgLateral2Id().length()>0){
                                            if(urlLateral2.length()>0){
                                                Picasso.get().load(urlLateral2).into(binding.imagen);
                                            } else {
                                                Picasso.get().load(superficie.getNiveles().get(finalValorFoto).getImgLateral2Id()).into(binding.imagen);
                                            }
                                        }else{

                                        }
                                    }
                                });



                            }else{
                                Toast.makeText(getContext(), superficie.getMensaje()+"",
                                        Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void reject(Exception e) {

                        }
                    });


            binding.aceptar.setVisibility(View.GONE);
            binding.cancelar.setVisibility(View.GONE);

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int tipoBack = preferences.getInt("TIPO_BACK",0);
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else
                    if(tipoBack == BACK_EN_PROCESO) {
                        Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                        getContext().startActivity(main);
                    } else if(tipoBack == BACK_RECHAZO) {
                        Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                        getContext().startActivity(main);
                    }
                }
            });


        }else if (position == 3) {

            mensaje = "fragment 2";
            final FragmentAutoriza3Binding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_3,container,false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.zonifica));

            slideUX(binding);

            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId","");
            String usuarioId = preferences.getString("usuario","");
            final String nombreSitio = preferences.getString("nombreSitio","");

            ProviderDatosZonificacion.getInstance(getContext())
                    .obtenerDatosConstruccion(mdId, usuarioId, new ProviderDatosZonificacion.ConsultaDatosZonificacion() {
                        @Override
                        public void resolve(Zonificacion zonificacion) {
                            if(zonificacion.getCodigo()==200){
                                list = new ArrayList<>();
                                listGeneradores = new ArrayList<>();
                                puntosCompentencias = new ArrayList<>();
                                int puntuacionCompetencia = 0;
                                int puntuacionGeneradores = 0;

                                for (int i = 0; i < zonificacion.getCompetencia().size(); i++) {

                                    for(int j=0;j<zonificacion.getCompetencia().get(i).getDetalle().size();j++){
                                        list.add(zonificacion.getCompetencia().get(i).getDetalle().get(j));
                                        Double compentenciaLatitud =  Double.valueOf(zonificacion.getCompetencia().get(i).getDetalle().get(j).getLatitud());
                                        Double compentenciaLongitud =  Double.valueOf(zonificacion.getCompetencia().get(i).getDetalle().get(j).getLongitud());

                                        int idIcono = zonificacion.getCompetencia().get(i).getDetalle().get(j).getGeneradorId();
                                        String nombre = zonificacion.getCompetencia().get(i).getDetalle().get(j).getNombreGenerador();

                                        puntosCompentencias.add(new Points(idIcono, compentenciaLatitud, compentenciaLongitud, nombre,null));


                                    }
                                }

                                for (int i = 0; i < zonificacion.getGeneradores().size(); i++) {
                                    for(int n=0;n<zonificacion.getGeneradores().get(i).getDetalle().size();n++){

                                        listGeneradores.add(zonificacion.getGeneradores().get(i).getDetalle().get(n));

                                        Double compentenciaLatitud =  Double.valueOf(zonificacion.getGeneradores().get(i).getDetalle().get(n).getLatitud());
                                        Double compentenciaLongitud =  Double.valueOf(zonificacion.getGeneradores().get(i).getDetalle().get(n).getLongitud());

                                        int idIcono = zonificacion.getGeneradores().get(i).getDetalle().get(n).getGeneradorId();
                                        String nombre = zonificacion.getGeneradores().get(i).getDetalle().get(n).getNombreGenerador();

                                        puntosCompentencias.add(new Points(idIcono, compentenciaLatitud, compentenciaLongitud, nombre,null));
                                    }
                                }

//                                if(zonificacion.getCompetencia() != null && zonificacion.getCompetencia().size() > 0) {
//                                    puntuacionCompetencia = Integer.parseInt(zonificacion.getCompetencia().get(0).getPuntuacion());
//                                }
//                                if(zonificacion.getGeneradores() != null && zonificacion.getGeneradores().size() > 0) {
//                                    puntuacionGeneradores = Integer.parseInt(zonificacion.getGeneradores().get(0).getPuntuacion());
//                                }

                                binding.robotoTextView2.setText("MD " + nombreSitio);

                                adapter = new AdapterListaCompetencia(list, getContext());
                                binding.contenido.contentLista.setLayoutManager(new LinearLayoutManager(getContext()));
                                binding.contenido.contentLista.setAdapter(adapter);

                                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 4);
                                binding.contenido.contentLista.setLayoutManager(mLayoutManager);
                                binding.contenido.contentLista.addItemDecoration(new FragmentDetalle.GridSpacingItemDecoration(3, dpToPx(4), true));
                                binding.contenido.contentLista.setItemAnimator(new DefaultItemAnimator());


                                adapter2 = new AdapterListaGeneradores(listGeneradores, getContext());
                                binding.content2.contentLista.setLayoutManager(new LinearLayoutManager(getContext()));
                                binding.content2.contentLista.setAdapter(adapter2);

                                RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getContext(), 4);
                                binding.content2.contentLista.setLayoutManager(mLayoutManager2);
                                binding.content2.contentLista.addItemDecoration(new FragmentDetalle.GridSpacingItemDecoration(4, dpToPx(5), true));
                                binding.content2.contentLista.setItemAnimator(new DefaultItemAnimator());

                                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                                        .findFragmentById(R.id.map);

                                mapFragment.getMapAsync(onMapReadyCallbackZonificacion);

                            }else{
                                Toast.makeText(getContext(), "Error al obtener los datos",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void reject(Exception e) {

                        }
                    });

            binding.competencia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideCompetencia.show();
                }
            });

            binding.generador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slideGenerador.show();
                }
            });


            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int tipoBack = preferences.getInt("TIPO_BACK",0);

                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else

                    if(tipoBack == BACK_EN_PROCESO) {
                        Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                        getContext().startActivity(main);
                    } else if(tipoBack == BACK_RECHAZO) {
                        Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                        getContext().startActivity(main);
                    }
                }
            });

            binding.aceptar.setVisibility(View.GONE);
            binding.cancelar.setVisibility(View.GONE);


        }else if (position == 4) {

            final FragmentAutoriza4Binding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_4,container,false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.construccion));


            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId","");
            String usuarioId = preferences.getString("usuario","");
            final String nombreSitio = preferences.getString("nombreSitio","");


            ProviderDatosConstruccion.getInstance(getContext())
                    .obtenerDatosConstruccion(mdId, usuarioId, new ProviderDatosConstruccion.ConsultaDatosConstruccion() {
                        @Override
                        public void resolve(DatosConstruccion datosSitio) {
                            if(datosSitio.getCodigo()==200 &&  datosSitio.getConstruccion().size() > 0) {
                                int sumaPuntuacion = 0;

                                for(int i = 0;i < datosSitio.getConstruccion().size(); i++) {
                                    if(datosSitio.getConstruccion().get(i).getNivelid() < 3) {
                                        binding.robotoTextView5.setText(datosSitio.getConstruccion().get(i).getNombrenivel());
                                        creaTablaSubfactores(binding, datosSitio.getConstruccion().get(i).getDetalles());
                                    }
                                    if(datosSitio.getConstruccion().get(i).getNivelid() == 3 || datosSitio.getConstruccion().get(i).getNivelid() == 4 || datosSitio.getConstruccion().get(i).getNivelid() == 5) {
                                        binding.condicion.setText(datosSitio.getConstruccion().get(i).getNombrenivel());
                                    }
                                   // sumaPuntuacion += Integer.parseInt(datosSitio.getConstruccion().get(i).getPuntuacion());
                                }

                                binding.titulo.setText("MD " + nombreSitio);

                            } else{
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
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int tipoBack = preferences.getInt("TIPO_BACK",0);
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else
                    if(tipoBack == BACK_EN_PROCESO) {
                        Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                        getContext().startActivity(main);
                    } else if(tipoBack == BACK_RECHAZO) {
                        Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                        getContext().startActivity(main);
                    }
                }
            });

        } else if (position == 5) {

            preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            String mdId = preferences.getString("mdId","");
            String usuarioId = preferences.getString("usuario","");
            final String nombreSitio = preferences.getString("nombreSitio","");

            final FragmentAutoriza5Binding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_5,container,false);
            view = binding.getRoot();
            binding.toolbar.nombreTitulo.setText(getString(R.string.generalidades));

            ProviderDatosGeneralidadesSitio.getInstance(getContext())
                    .obtenerDatosGeneralidades(mdId, usuarioId, new ProviderDatosGeneralidadesSitio.ConsultaGeneralidadesSitio() {
                        @Override
                        public void resolve(GeneralidadesSitio datosSitio) {
                            DecimalFormat formatter = new DecimalFormat("#,###");
                            if(datosSitio!=null && datosSitio.getCodigo()==200) {

                                int sumaPuntuacion = 0;
                                for(int i = 0; i < datosSitio.getGeneralidades().size(); i++) {

                                    if(datosSitio.getGeneralidades().get(i).getNivelid() == 1 || datosSitio.getGeneralidades().get(i).getNivelid() == 2 || datosSitio.getGeneralidades().get(i).getNivelid() == 3){
                                        binding.renta.setText("$" + formatter.format(datosSitio.getGeneralidades().get(i).getValor()) +" al mes");

                                        if(datosSitio.getGeneralidades().get(i).getDetalles() != null && datosSitio.getGeneralidades().get(i).getDetalles().size() > 0) {
                                            binding.periodogracia.setText(datosSitio.getGeneralidades().get(i).getDetalles().get(0).getValor() + " " + datosSitio.getGeneralidades().get(i).getDetalles().get(0).getUnidadmedicion());
                                        }
                                    }

                                    if(datosSitio.getGeneralidades().get(i).getNivelid() == 4 || datosSitio.getGeneralidades().get(i).getNivelid() == 5 || datosSitio.getGeneralidades().get(i).getNivelid() == 6){
                                        if(datosSitio.getGeneralidades().get(i).getNivelid() == 5) {
                                            binding.disponibilidad.setText(getString(R.string.bien) + " " + datosSitio.getGeneralidades().get(i).getNombrenivel() + " " + datosSitio.getGeneralidades().get(i).getFechadisponible());
                                        } else {
                                            binding.disponibilidad.setText(getString(R.string.bien) + " " + datosSitio.getGeneralidades().get(i).getNombrenivel());
                                        }
                                    }

                                    if(datosSitio.getGeneralidades().get(i).getNivelid() == 7 || datosSitio.getGeneralidades().get(i).getNivelid() == 8 || datosSitio.getGeneralidades().get(i).getNivelid() == 9){
                                        binding.amortizacion.setText(datosSitio.getGeneralidades().get(i).getValor() + "%");

                                        if(datosSitio.getGeneralidades().get(i).getDetalles() != null && datosSitio.getGeneralidades().get(i).getDetalles().size() > 0) {
                                            binding.tiempoamortizacion.setText(datosSitio.getGeneralidades().get(i).getDetalles().get(0).getValor() + " " + datosSitio.getGeneralidades().get(i).getDetalles().get(0).getUnidadmedicion());
                                        }
                                    }
                                    //sumaPuntuacion += datosSitio.getGeneralidades().get(i).getPuntuacion();

                                }

                                binding.robotoTextView2.setText("MD " + nombreSitio);

                            }
                        }

                        @Override
                        public void reject(Exception e) {

                        }
                    });



            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int tipoBack = preferences.getInt("TIPO_BACK",0);
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else
                    if(tipoBack == BACK_EN_PROCESO) {
                        Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                        getContext().startActivity(main);
                    } else if(tipoBack == BACK_RECHAZO) {
                        Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                        getContext().startActivity(main);
                    }
                }
            });

            binding.aceptar.setVisibility(View.GONE);
            binding.cancelar.setVisibility(View.GONE);

        }else if (position == 6) {
            final FragmentAutoriza6Binding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_6,container,false);
            view = binding.getRoot();

            listaPeatonal(binding);

            binding.toolbar.nombreTitulo.setText(getString(R.string.flujopeatonal));


            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int tipoBack = preferences.getInt("TIPO_BACK",0);
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else
                    if(tipoBack == BACK_EN_PROCESO) {
                        Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                        getContext().startActivity(main);
                    } else if(tipoBack == BACK_RECHAZO) {
                        Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                        getContext().startActivity(main);
                    }
                }
            });

            binding.aceptar.setVisibility(View.GONE);
            binding.cancelar.setVisibility(View.GONE);

        } else if (position == 7) {
            FragmentAutoriza7Binding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_7,container,false);
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

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    int tipoBack = preferences.getInt("TIPO_BACK",0);
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else
                    if(tipoBack == BACK_EN_PROCESO) {
                        Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                        getContext().startActivity(main);
                    } else if(tipoBack == BACK_RECHAZO) {
                        Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                        getContext().startActivity(main);
                    }
                }
            });
        }
        return view;
    }

    public void creaTablaSubfactores(FragmentAutoriza4Binding binding, List<DatosConstruccion.Detalle> listaSubfactores) {
        limpiaTabla(binding);
        Resources resource = getContext().getResources();

        if(listaSubfactores != null && listaSubfactores.size() > 0) {
            TableLayout ll = binding.tablaSubfactores;
            TableRow row = new TableRow(getContext());
            row.setGravity(Gravity.LEFT);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(lp);

            int widthCeldaImg = (int) (ll.getWidth() * 0.2);
            int widthCeldaDescripcion = (int) (ll.getWidth() * 0.8);

            for (int i = 0; i < listaSubfactores.size(); i++) {
                row = new TableRow(getContext());
                row.setGravity(Gravity.CENTER_HORIZONTAL);
                row.setPadding(0,10,0,0);
                lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                lp.height = 85;
                row.setLayoutParams(lp);

                ImageView imgSubfactor = new ImageView(getContext());
                imgSubfactor.setImageDrawable(resource.getDrawable(R.drawable.ic_btn_select));
                imgSubfactor.setMaxWidth(14);
                imgSubfactor.setPadding(0, 0, 5, 1);
                imgSubfactor.setLayoutParams(lp);

                TextView descripcionText = new TextView(getContext());
                descripcionText.setText(listaSubfactores.get(i).getNombredetalle());
                descripcionText.setWidth(widthCeldaDescripcion);
                descripcionText.setTextColor(resource.getColor(R.color.azul));
                descripcionText.setPadding(0, 5, 5, 1);
                descripcionText.setLayoutParams(lp);
                descripcionText.setGravity(Gravity.LEFT);


                row.addView(imgSubfactor);
                row.addView(descripcionText);
                ll.addView(row, i);

            }
        }
    }

    public void limpiaTabla(FragmentAutoriza4Binding binding) {

        ScrollView sv = binding.containerScrollView;
        sv.scrollTo(0, 0);
        sv.computeScroll();
        TableLayout ll = binding.tablaSubfactores;

        int count = ll.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = ll.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }
    }

    ArrayList<Peatonal> peatonales;
    AdapterListaHoras adapterHoras;
    public void listaPeatonal(final FragmentAutoriza6Binding binding){
        final Resources resource = getContext().getResources();
        final ArrayList<String> horarios = new ArrayList<>();
        preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdId = preferences.getString("mdId","");
        String usuarioId = preferences.getString("usuario","");
        final String nombreSitio = preferences.getString("nombreSitio","");

        ProviderDatosPeatonal.getInstance(getContext()).obtenerDatosPeatonal(mdId, usuarioId, new ProviderDatosPeatonal.ConsultaPeatonal() {
            @Override
            public void resolve(Peatonales peatonal) {

                peatonales = new ArrayList<>();

                if(peatonal!=null && peatonal.getCodigo()==200){
                    int sumaPuntuacion = 0;

                    for(int i = 0; i < peatonal.getConteos().size(); i++){
                        for(int j=0;j<peatonal.getConteos().get(i).getDetalle().size();j++){

                            peatonales.add(new Peatonal(j,
                                    peatonal.getConteos().get(i).getDetalle().get(j).getFecha(),
                                    Integer.valueOf(peatonal.getConteos().get(i).getDetalle().get(j).getValor()),
                                    0.0,0.0, peatonal.getConteos().get(i).getDetalle().get(j).getNombreGenerador()));
                        }
                        sumaPuntuacion += peatonal.getConteos().get(i).getPuntuacion();
                    }

                    binding.robotoTextView2.setText("MD " + nombreSitio);
                    if(peatonal.getConteos() != null && peatonal.getConteos().size() > 0) {
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

                }else{
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

    public void slideUX(final FragmentAutoriza3Binding binding){
        slideGenerador = new SlideUpBuilder(binding.content2.slideView)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {
                        binding.dim.setAlpha(1 - (percent / 100));
                        //if (binding.fab.isShown() && percent < 100) {
                        // binding.fab.hide();
                        // }
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility == View.GONE){
                            //fab.show();
                        }
                    }
                }).withStartGravity(Gravity.BOTTOM).withLoggingEnabled(true).withGesturesEnabled(true)
                .withStartState(SlideUp.State.HIDDEN).withSlideFromOtherView(binding.rootView)
                .withTouchableAreaPx(100)
                .withTouchableAreaDp(100)
                .build();

        slideCompetencia = new SlideUpBuilder(binding.contenido.slideView)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {
                        binding.dim2.setAlpha(1 - (percent / 100));
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility == View.GONE){
                            //fab.show();
                        }
                    }
                }).withStartGravity(Gravity.BOTTOM).withLoggingEnabled(true).withGesturesEnabled(true)
                .withStartState(SlideUp.State.HIDDEN).withSlideFromOtherView(binding.rootView2)
                .withTouchableAreaPx(100)
                .withTouchableAreaDp(100)
                .build();

    }
    String puntuacion, categoria;
    ArrayList<DatosPuntuacion.Factore> factoresMacro;
    ArrayList<DatosPuntuacion.Factore> factoresMicro;
    public void getDatos(final FragmentAutoriza7Binding binding){

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdid = preferences.getString("mdId", "");
        String usuario = preferences.getString("usuario", "");

        ProviderConsultaFinaliza.getInstance(getContext()).obtenerPuntos(mdid, usuario, new ProviderConsultaFinaliza.ConsultaPuntos() {
            @Override
            public void resolve(DatosPuntuacion datosPuntuacion) {
                if(datosPuntuacion.getCodigo()==200){

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
                        if(datosPuntuacion.getFactores().get(i).getRangoubica()!=null){
                            if (datosPuntuacion.getFactores().get(i).getRangoubica().equals(getString(R.string.micro_ub))) {
                                factoresMacro.add(datosPuntuacion.getFactores().get(i));
                            } else{
                                factoresMicro.add(datosPuntuacion.getFactores().get(i));
                            }
                        }else{
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
            public void reject(Exception e) { }
        });
    }


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void generarDetallesMacro(FragmentAutoriza7Binding binding,  ArrayList<DatosPuntuacion.Factore> datosPuntuacion){
        Resources resource = this.getResources();
        binding.factores.removeAllViews();
        TableRow rowPlomo = new TableRow(getContext());
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        int paddingDp = 2;

        float density = getResources().getDisplayMetrics().density;
        int paddingPixel = (int)(paddingDp * density);

        for(int i = 0; i < datosPuntuacion.size(); i ++){

            TableRow tbrow = new TableRow(getContext());
            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));
            TextView t1v1 = new TextView(getContext());
            t1v1.setTextSize(12);
            t1v1.setText(datosPuntuacion.get(i).getNombrenivel()+"");
            t1v1.setTextColor(resource.getColor(R.color.azul));
            t1v1.setPadding(0, paddingPixel,0,0);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(getContext());
            t3v1.setTextSize(12);

            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");

            t3v1.setTextColor(resource.getColor(R.color.azul));
            t3v1.setGravity(Gravity.END);
            t3v1.setLayoutParams( new TableRow.LayoutParams( 50,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v1);

            TextView t3v2 = new TextView(getContext());
            t3v2.setTextSize(12);
            if(datosPuntuacion.get(i).getTotalxfactor()!=null){
                t3v2.setText("/"+datosPuntuacion.get(i).getTotalxfactor()+"");
            }else{
                binding.tituloMacro.setVisibility(View.GONE);
                binding.tituloMicro.setVisibility(View.GONE);
            }
            t3v2.setTextColor(resource.getColor(R.color.azul));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);

            binding.factores.addView(tbrow);
        }
    }

    public void generarDetallesMicro(FragmentAutoriza7Binding binding,  ArrayList<DatosPuntuacion.Factore> datosPuntuacion){

        Resources resource = this.getResources();
        binding.factoresMicro.removeAllViews();
        TableRow rowPlomo = new TableRow(getContext());
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        int paddingDp = 2;

        float density = getResources().getDisplayMetrics().density;
        int paddingPixel = (int)(paddingDp * density);

        for(int i = 0; i < datosPuntuacion.size(); i ++){

            TableRow tbrow = new TableRow(getContext());
            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));
            TextView t1v1 = new TextView(getContext());
            t1v1.setTextSize(12);
            t1v1.setText(datosPuntuacion.get(i).getNombrenivel()+"");
            t1v1.setTextColor(resource.getColor(R.color.azul));
            t1v1.setPadding(0, paddingPixel,0,0);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(getContext());
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.azul));
            t3v1.setGravity(Gravity.END);
            t3v1.setLayoutParams( new TableRow.LayoutParams( 50,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v1);

            TextView t3v2 = new TextView(getContext());
            t3v2.setTextSize(12);
            if(datosPuntuacion.get(i).getTotalxfactor()!=null){
                t3v2.setText("/"+datosPuntuacion.get(i).getTotalxfactor()+"");
            }else{
                binding.tituloMacro.setVisibility(View.GONE);
                binding.tituloMicro.setVisibility(View.GONE);
            }
            t3v2.setTextColor(resource.getColor(R.color.azul));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);

            binding.factoresMicro.addView(tbrow);
        }
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
}