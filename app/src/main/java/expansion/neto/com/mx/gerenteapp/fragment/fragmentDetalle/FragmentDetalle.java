package expansion.neto.com.mx.gerenteapp.fragment.fragmentDetalle;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.VectorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityFinalizaBinding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza3Binding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentAutoriza4DetalleBinding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentDetalleGenBinding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentDetallePropietarioBinding;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentDetalleSitioBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentDialogCancelarMdProceso;
import expansion.neto.com.mx.gerenteapp.modelView.Ubicacion;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosConstruccions;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosPuntuacion;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosSitio;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.GeneralidadesSitio;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Peatonales;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Propietario;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Superficie;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Zonificacion;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.CompetenciasGeneradoresV2;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.CrearZonificacion;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.FactoresConstruccion;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderConsultaFinaliza;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosConstruccion;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosGeneralidadesSitio;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosPeatonal;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosPropietario;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosSitio;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosSuperficie;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosZonificacion;
import expansion.neto.com.mx.gerenteapp.provider.crearProvider.ProviderDatosCompetencias;
import expansion.neto.com.mx.gerenteapp.provider.crearProvider.ProviderDatosFactoresConstruccion;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AdapterAutorizaPeatonal;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AdapterListaHoras;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.AutorizaHolderPeatonal;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaCompetencia;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaGeneradores;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaGeneradoresMercadoPublico;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaGeneradoresNegocios;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaGeneradoresNegociosComida;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaGeneradoresTianguis;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaGeneradoresTransporte;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaTiendaNeto;
import expansion.neto.com.mx.gerenteapp.ui.agenda.ActivityNotificaciones;
import expansion.neto.com.mx.gerenteapp.utils.ServicioGPS;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import expansion.neto.com.mx.gerenteapp.utils.desing.MainSliderAdapter;
import expansion.neto.com.mx.gerenteapp.utils.desing.PicassoImageLoadingService;
import ss.com.bannerslider.Slider;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.VERSION_APP;


public class FragmentDetalle extends Fragment implements
        AutorizaHolderPeatonal.Listener, com.google.android.gms.location.LocationListener {

    private View view;
    private static final String ARG_POSITION = "position";
    private int position;
    AdapterListaCompetencia adapter;
    AdapterListaGeneradores adapter2;
    AutorizaHolderPeatonal.Listener n;
    List<CompetenciasGeneradoresV2.Competencia> listCompetencia = new ArrayList<>();
    List<CompetenciasGeneradoresV2.OtrosGeneradore> listGeneradores = new ArrayList<>();
    CrearZonificacion zonificacion = null;

    int valor;
    String nombreGenerador;
    String nombreCompetencia;

    String nombreSitio;

    AdapterListaGeneradoresNegocios adapterNegocios;
    AdapterListaGeneradoresTransporte adapterTransporte;
    AdapterListaTiendaNeto adapterTiendaNeto;
    AdapterListaGeneradoresNegociosComida adapterListaGeneradoresNegociosComida;
    AdapterListaGeneradoresMercadoPublico adapterListaGeneradoresMercadoPublico;
    AdapterListaGeneradoresTianguis adapterListaGeneradoresTianguis;

    public static void loadingProgress(ProgressDialog progressDialog, int i){
        if(i == 0){
            progressDialog.setTitle("Enviando...");
            progressDialog.setMessage("Espera mientras se carga tu informacion...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }else{
            progressDialog.dismiss();
        }
    }

    List<CompetenciasGeneradoresV2.TiendaNeto> listCompetenciaTiendaNeto = new ArrayList<>();
    List<CompetenciasGeneradoresV2.Negocio> listGeneradoresNegocios = new ArrayList<>();
    List<CompetenciasGeneradoresV2.TransportePublico> listGeneradoresTransporte = new ArrayList<>();

    List<CompetenciasGeneradoresV2.NegociosDeComida> negociosDeComidaArrayList = new ArrayList<>();
    List<CompetenciasGeneradoresV2.MercadoPublico> mercadoPublicoArrayList = new ArrayList<>();
    List<CompetenciasGeneradoresV2.Tiangui> tianguiArrayList = new ArrayList<>();
    int nivel;

    private AdapterListaCompetencia.OnItemClick click = new AdapterListaCompetencia.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreCompetencia = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaTiendaNeto.OnItemClick clickTiendaNeto = new AdapterListaTiendaNeto.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreCompetencia = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradores.OnItemClick clicks = new AdapterListaGeneradores.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradoresNegocios.OnItemClick clickNegocio = new AdapterListaGeneradoresNegocios.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradoresTransporte.OnItemClick clickTransporte = new AdapterListaGeneradoresTransporte.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradoresMercadoPublico.OnItemClick clickMercadoPublico = new AdapterListaGeneradoresMercadoPublico.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradoresNegociosComida.OnItemClick clickNegociosComida = new AdapterListaGeneradoresNegociosComida.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private AdapterListaGeneradoresTianguis.OnItemClick clickTiaguis = new AdapterListaGeneradoresTianguis.OnItemClick() {
        @Override
        public void onClick(int value, String nombre, int nivelId) {
            valor = value;
            nombreGenerador = nombre;
            nivel = nivelId;
        }
    };

    private LatLng mCenterLatLong;

    private OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(final GoogleMap googleMap) {
            mMap = googleMap;
            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            mdLat = preferences.getFloat("latMd", 0);
            mdLot = preferences.getFloat("lotMd", 0);


            LatLng mds = new LatLng(mdLat, mdLot);
            icon = getBitmapDescriptor(R.drawable.home);
            googleMap.addMarker(new MarkerOptions().position(mds)
                    .title("")
                    .icon(icon)
            );

            mCenterLatLong = new LatLng(mdLat, mdLot);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(mCenterLatLong));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mCenterLatLong, 15));
            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(8), 1000, null);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(mCenterLatLong)
                    .zoom(14)
                    .bearing(0)
                    .tilt(0)
                    .build();

            googleMap.getUiSettings().setScrollGesturesEnabled(false);
            googleMap.getUiSettings().setZoomGesturesEnabled(false);
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }
    };

    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onLocationChanged(Location location) {
        try {
            if (location != null)
                changeMap(location);
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private GoogleMap mMap;
    private GoogleMap mMapZona;

    @SuppressLint("MissingPermission")
    private void changeMap(Location location) {
        if (mMap != null) {
            mMap.getUiSettings().setZoomControlsEnabled(false);
            LatLng latLong;


            latLong = new LatLng(location.getLatitude(), location.getLongitude());

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLong).zoom(19f).tilt(70).build();

            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
        } else {

        }
    }

    BitmapDescriptor icon;
    Float mdLat;
    Float mdLot;
    ArrayList<Zonificacion.Detalle> detallesGen;
    ArrayList<Zonificacion.Detalle> detallesCom;

    private OnMapReadyCallback onMapReadyCallbackZonificacion = new OnMapReadyCallback() {
        @Override
        public void onMapReady(final GoogleMap googleMap) {

            mMapZona = googleMap;

            SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            mdLat = preferences.getFloat("latMd", 0);
            mdLot = preferences.getFloat("lotMd", 0);
            final Float mdLat = preferences.getFloat("latMd", 0);
            final Float mdLot = preferences.getFloat("lotMd", 0);

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

            if(mdLat!=null || mdLot!=null){
                mds = new LatLng(mdLat, mdLot);
                icon = getBitmapDescriptor(R.drawable.home);
                googleMap.addMarker(new MarkerOptions().position(mds)
                        .title("")
                        .icon(icon)
                );

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(mds));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mds, 15));
                googleMap.animateCamera(CameraUpdateFactory.zoomIn());
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

                cameraPosition = new CameraPosition.Builder()
                        .target(mds)
                        .zoom(14)
                        .bearing(0)
                        .tilt(0)
                        .build();

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                bindingZonificacion.aceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CameraUpdate center=
                                CameraUpdateFactory.newLatLng(new LatLng(mdLat, mdLot));
                        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
                        googleMap.moveCamera(center);
                        googleMap.animateCamera(zoom);

                    }
                });


                googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

                    @Override
                    public void onMapLongClick(LatLng arg0) {
                        // TODO Auto-generated method stub
                    }
                });
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

    private Slider slider;
    private SlideUp slideCompetencia;
    private SlideUp slideGenerador;
    FragmentDetalleSitioBinding binding;
    FragmentAutoriza3Binding bindingZonificacion;

    Float lat, lot;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (position == 0) {

            mensaje = "fragment 1";
            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detalle_sitio,container,false);
            view = binding.getRoot();

            binding.toolbar.nombreTitulo.setText(getString(R.string.detalles));
            binding.toolbar.guardar.setVisibility(View.INVISIBLE);

            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(onMapReadyCallback);

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String usuario = preferences.getString("usuario", "");
            final String mdIdterminar = preferences.getString("mdIdterminar", "");
            int atrasa = preferences.getInt("atrasa",0);


            if(atrasa==1){
                binding.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            }else{
                binding.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }

            binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdProceso a = new FragmentDialogCancelarMdProceso();
                        a.show(getChildFragmentManager(),"child");
                    }

                }
            });

            binding.nombresitio.setEnabled(false);
            binding.direccionsitio.setEnabled(false);
            binding.toolbar.guardar.setVisibility(View.INVISIBLE);
            final SharedPreferences.Editor editorPre = preferences.edit();

            ProviderDatosSitio.getInstance(getContext()).obtenerDatosSitio(mdIdterminar, usuario, new ProviderDatosSitio.ConsultaDatosSitio() {
                @Override
                public void resolve(DatosSitio datosSitio) {
                    if(datosSitio.getDatossitio()!= null && datosSitio.getCodigo()==200){
                        if(datosSitio.getDatossitio().get(0).getTipoUbicacionMD()!=null){
                            if(datosSitio.getDatossitio().get(0).getTipoUbicacionMD().equals("RURAL")){
                                binding.setRural("Rural");
                                editorPre.putString("tipoSitio" ,"2");
                                editorPre.apply();
                            }else{
                                binding.setRural("Ciudad");
                                editorPre.putString("tipoSitio" ,"1");
                                editorPre.apply();
                            }
                        }

                        nombreSitio = datosSitio.getDatossitio().get(0).getNombreSitio();

                        binding.nombresitio.setEnabled(false);
                        binding.nombresitio.setText(datosSitio.getDatossitio().get(0).getNombreSitio());
                        binding.fechaCreacion.setText(datosSitio.getDatossitio().get(0).getFechaCreacion()+"");
                        binding.direccionsitio.setText(datosSitio.getDatossitio().get(0).getDireccion()+"");
                        binding.puntos.setText(datosSitio.getDatossitio().get(0).getTotalmd()+"");
                        binding.setCategoria(datosSitio.getDatossitio().get(0).getCategoria()+"");
                        binding.categoria.setText(datosSitio.getDatossitio().get(0).getCategoria()+"");



                        lat = Float.valueOf(datosSitio.getDatossitio().get(0).getLatitud());
                        lot = Float.valueOf(datosSitio.getDatossitio().get(0).getLongitud());

                        SharedPreferences.Editor editorDatos = preferences.edit();

                        editorDatos.putString("cate", datosSitio.getDatossitio().get(0).getCategoria()+"");
                        editorDatos.putString("punto", datosSitio.getDatossitio().get(0).getTotalmd()+"");
                        editorDatos.putString("fechaCreacion", datosSitio.getDatossitio().get(0).getFechaCreacion()+"");
                        editorDatos.putString("nombreSitio", nombreSitio);

                        editorDatos.putFloat("latMd", lat);
                        editorDatos.putFloat("lotMd", lot);
                        editorDatos.apply();

                        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                                .findFragmentById(R.id.map);
                        mapFragment.getMapAsync(onMapReadyCallback);


                        ProviderDatosPropietario.getInstance(getContext()).obtenerDatosPropietario(mdIdterminar, usuario, new ProviderDatosPropietario.ConsultaDatosPropietario() {
                            @Override
                            public void resolve(Propietario propietario) {
                                if(propietario.getCodigo()==200 && propietario.getAMaternoPropietario()!=null){

                                    if(propietario.getRentaMasLocales() > 0) {
                                        // binding.robotoTextView11.setText("YA RENTA A NETO");
                                    } else {
                                        //binding.robotoTextView11.setText("NO RENTA A NETO");
                                    }

                                    if(propietario.getMail().equals("null")){
                                        propietario.setMail("");
                                    }

                                    binding.nombre.setText(propietario.getNombrePropietario()+ " " +
                                    propietario.getAPaternoPropietario()+" "+propietario.getAMaternoPropietario());
                                    binding.telefono.setText(propietario.getTelefono());
                                    binding.email.setText(propietario.getMail());

                                }
                            }
                            @Override
                            public void reject(Exception e) {
                            }
                        });

                        binding.toolbar.nombreTitulo.setText(getString(R.string.detalles));
                        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(noti){
                                    Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                                    startActivity(main);
                                }else{
                                    FragmentDialogCancelarMdProceso a = new FragmentDialogCancelarMdProceso();
                                    a.show(getChildFragmentManager(),"child");
                                }
                            }
                        });
                    }
                }

                @Override
                public void reject(Exception e) { }
            });

        } else if (position == 1) {

            final FragmentDetallePropietarioBinding bindingSuperficie = DataBindingUtil.inflate(inflater, R.layout.fragment_detalle_propietario,container,false);
            view = bindingSuperficie.getRoot();
            bindingSuperficie.toolbar.nombreTitulo.setText(getString(R.string.detalles));
            bindingSuperficie.toolbar.guardar.setVisibility(View.INVISIBLE);

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String usuario = preferences.getString("usuario", "");
            final String md = preferences.getString("mdIdterminar", "");
            final String nombre = preferences.getString("nombreSitio", "");
            int atrasa = preferences.getInt("atrasa",0);


            if(atrasa==1){
                bindingSuperficie.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            }else{
                bindingSuperficie.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }

            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            loadingProgress(progressDialog, 0);

            ProviderDatosSuperficie.getInstance(getContext())
                    .obtenerDatosSuperficie(md, usuario, new ProviderDatosSuperficie.ConsultaDatosSuperficie() {
                        @Override
                        public void resolve(final Superficie superficie) {
                            if(superficie.getCodigo()==200){
                                loadingProgress(progressDialog, 1);

                                bindingSuperficie.nombresitio.setText(nombre+"");

                                int valorFoto = 0;
                                int valorFrente = 0;
                                int valorFondo = 0;
                                int valorEsquina = 0;

                                for(int i = 0;i<superficie.getNiveles().size();i++){
                                    if(superficie.getNiveles().get(i).getNivel()==4 ||
                                            superficie.getNiveles().get(i).getNivel()==5){
                                        if(!superficie.getNiveles().get(i).getImgFrenteId().isEmpty()){
                                            valorFoto = i;
                                            valorFondo = i;
                                        }
                                    }

                                    if(superficie.getNiveles().get(i).getNivel()==6 ||
                                            superficie.getNiveles().get(i).getNivel()==7){
                                        valorFrente = i;
                                    }

                                    if(superficie.getNiveles().get(i).getNivel()==8){
                                        valorEsquina = i;
                                    }
                                }

                                Double esquina = superficie.getNiveles().get(valorEsquina).getValorreal();

                                if(esquina==1){

                                }else{

                                }

                                String superficieS = String.valueOf(superficie.getNiveles().get(valorFrente).getValorreal());
                                superficieS = superficieS.replace(" ", "");
                                String fondoS = String.valueOf(superficie.getNiveles().get(valorFondo).getFondo());
                                fondoS = fondoS.replace(" ", "");

                                String total = String.valueOf((Double.valueOf(superficieS)
                                        *(Double.valueOf(fondoS))));
                                bindingSuperficie.areaterreno.setText(total+" MTS2");
                                bindingSuperficie.frente.setText(superficieS+" MTS");
                                bindingSuperficie.profundidad.setText(fondoS+" MTS");

                                Slider.init(new PicassoImageLoadingService());
                                slider = bindingSuperficie.map;
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

                                final int[] local = {0};
                                final int[] acceso = {0};
                                final int[] grietas = {0};
                                final int[] goteras = {0};

                                ProviderDatosFactoresConstruccion.getInstance(getContext()).obtenerDatosContruccion(md, new ProviderDatosFactoresConstruccion.ConsultaFactoresConstruccion() {
                                    @Override
                                    public void resolve(final FactoresConstruccion factoresConstruccion) {
                                        if(factoresConstruccion.getCodigo()==200){
                                            if(factoresConstruccion.getCatalogo()!=null) {
                                                ProviderDatosConstruccion.getInstance(getContext()).obtenerDatosConstruccion(md, usuario, new ProviderDatosConstruccion.ConsultaDatosConstruccion() {
                                                    @Override
                                                    public void resolve(DatosConstruccions datosSitio) {
                                                        datosSitios = datosSitio;
                                                        if(datosSitio!=null){
                                                            if(datosSitio.getCodigo()==200 && datosSitio.getConstruccion().size() > 0) {
                                                                for(int i=0; i<datosSitio.getConstruccion().size(); i++){
                                                                    if(datosSitio.getConstruccion().get(i).getNivelid()==1
                                                                            || datosSitio.getConstruccion().get(i).getNivelid()==2){
                                                                        String sitio = datosSitio.getConstruccion().get(i).getNombrenivel();
                                                                        if(sitio.contains("LOCAL")){

                                                                            bindingSuperficie.setTerreno(1);
                                                                            for (int j = 0; j < datosSitio.getConstruccion().get(i).getDetalles().size(); j++) {
                                                                                if(datosSitio.getConstruccion().get(i).getDetalles().get(j).getDetalleid()==1){
                                                                                    local[0] = 1;
                                                                                    bindingSuperficie.setLocal(local[0]);
                                                                                }

                                                                                if(datosSitio.getConstruccion().get(i).getDetalles().get(j).getDetalleid()==2){
                                                                                    acceso[0] = 1;
                                                                                    bindingSuperficie.setAcceso(acceso[0]);
                                                                                }

                                                                                if(datosSitio.getConstruccion().get(i).getDetalles().get(j).getDetalleid()==3){
                                                                                    grietas[0] = 1;
                                                                                    bindingSuperficie.setTechos(grietas[0]);
                                                                                }

                                                                                if(datosSitio.getConstruccion().get(i).getDetalles().get(j).getDetalleid()==4){
                                                                                    goteras[0] = 1;
                                                                                    bindingSuperficie.setPisos(goteras[0]);
                                                                                }
                                                                            }
                                                                        }else{
                                                                            bindingSuperficie.setTerreno(0);
                                                                        }
                                                                        bindingSuperficie.construccion.setText(sitio+"");
                                                                    }

                                                                    if(datosSitio.getConstruccion().get(i).getNivelid()==3
                                                                            || datosSitio.getConstruccion().get(i).getNivelid()==4
                                                                            || datosSitio.getConstruccion().get(i).getNivelid()==5){

                                                                        String condicion = datosSitio.getConstruccion().get(i).getNombrenivel();
                                                                        bindingSuperficie.setCondiciones(condicion+"");

                                                                    }

                                                                }
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void reject(Exception e) {

                                                    }
                                                });
                                            }
                                        }else{
                                            Toast.makeText(getContext(), "Error al consultar factores de construcción", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void reject(Exception e) {

                                    }
                                });



                            }else{
                                loadingProgress(progressDialog, 1);
                            }
                        }
                        @Override
                        public void reject(Exception e) { }
                    });

            bindingSuperficie.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdProceso a = new FragmentDialogCancelarMdProceso();
                        a.show(getChildFragmentManager(),"child");
                    }
                }
            });



        }else if (position == 2) {

            mensaje = "fragment 2";
            bindingZonificacion = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_3,container,false);
            view = bindingZonificacion.getRoot();


            bindingZonificacion.ciudad.setVisibility(View.GONE);
            bindingZonificacion.toolbar.guardar.setVisibility(View.INVISIBLE);

            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);

            mapFragment.getMapAsync(onMapReadyCallbackZonificacion);

            bindingZonificacion.toolbar.guardar.setVisibility(View.INVISIBLE);
            bindingZonificacion.cancelar.setVisibility(View.INVISIBLE);

            final SharedPreferences[] preferences = {getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE)};
            final String mdIdterminar = preferences[0].getString("mdIdterminar", "");
            mdLat = preferences[0].getFloat("latMd", 0);
            mdLot = preferences[0].getFloat("lotMd", 0);
            final String usuario = preferences[0].getString("usuario", "");

            String nombreMd = preferences[0].getString("nombreSitio", "");
            bindingZonificacion.robotoTextView2.setText(nombreMd);
            bindingZonificacion.toolbar.nombreTitulo.setText(getString(R.string.detalles));

            int atrasa = preferences[0].getInt("atrasa",0);


            if(true){
                preferences[0] = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                ProviderDatosZonificacion.getInstance(getContext())
                        .obtenerDatosZonificacion(mdIdterminar, usuario, new ProviderDatosZonificacion.ConsultaDatosZonificacion() {
                            @Override
                            public void resolve(Zonificacion creaZonificacion) {
                                if(creaZonificacion.getCodigo()==200){


                                    LatLng mds = new LatLng(mdLat, mdLot);
                                    LatLng mdsNuevos = null;

                                    detallesCom = new ArrayList<>();
                                    detallesGen = new ArrayList<>();

                                    for(int i=0;i<creaZonificacion.getCompetencia().size();i++){
                                        for(int j=0;j<creaZonificacion.getCompetencia().get(i).getDetalle().size();j++){
                                            detallesCom.add(creaZonificacion.getCompetencia().get(i).getDetalle().get(j));
                                        }
                                    }

                                    for(int i=0;i<creaZonificacion.getGeneradores().size();i++){
                                        for(int j=0;j<creaZonificacion.getGeneradores().get(i).getDetalle().size();j++){
                                            detallesGen.add(creaZonificacion.getGeneradores().get(i).getDetalle().get(j));
                                        }
                                    }


                                    if(detallesGen.size()==0){
                                        // mdsNuevos = new LatLng(0.0, 0.0);
                                        // colocarMarcador(mdsNuevos, mMapZona, 1,
                                        //  usuario, mds, String.valueOf(mdIdterminar), detallesGen, detallesCom);
                                    }else{
                                        for(int j=0;j<detallesGen.size();j++){
                                            mdsNuevos = new LatLng(Double.valueOf(detallesGen.get(j).getLatitud()),
                                                    Double.valueOf(detallesGen.get(j).getLongitud()));

                                            colocarMarcador(mdsNuevos, mMapZona, detallesGen.get(j).getGeneradorId(),
                                                    usuario, mds, String.valueOf(mdIdterminar), detallesGen, detallesCom);
                                        }
                                    }

                                    if(detallesCom.size()==0){
                                        mdsNuevos = new LatLng(0.0, 0.0);
                                        colocarMarcador(mdsNuevos, mMapZona, 1,
                                                usuario, mds, String.valueOf(mdIdterminar), detallesGen, detallesCom);
                                    }else{
                                        for(int j=0;j<detallesCom.size();j++){
                                            mdsNuevos = new LatLng(Double.valueOf(detallesCom.get(j).getLatitud()),
                                                    Double.valueOf(detallesCom.get(j).getLongitud()));
                                            colocarMarcador(mdsNuevos, mMapZona, detallesCom.get(j).getGeneradorId(),
                                                    usuario, mds, String.valueOf(mdIdterminar), detallesGen, detallesCom);
                                        }
                                    }

                                    listGeneradores = new ArrayList<>();

                                }else{
                                    Toast.makeText(getContext(), "Error al obtener los datos",
                                            Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void reject(Exception e) { }
                        });


                slideUX(bindingZonificacion);

                ProviderDatosCompetencias.getInstance(getContext()).obtenerDatosCompetencias(usuario, mdIdterminar, new ProviderDatosCompetencias.ConsultaDatosCompetencia() {
                    @Override
                    public void resolve(CompetenciasGeneradoresV2 competenciasGeneradores) {

                        if(competenciasGeneradores!=null && competenciasGeneradores.getCodigo()==200){
                            listCompetencia = new ArrayList<>();
                            listGeneradores = new ArrayList<>();
                            listCompetenciaTiendaNeto = new ArrayList<>();
                            listGeneradoresNegocios = new ArrayList<>();
                            listGeneradoresTransporte= new ArrayList<>();

                            negociosDeComidaArrayList = new ArrayList<>();
                            mercadoPublicoArrayList = new ArrayList<>();
                            tianguiArrayList = new ArrayList<>();

                            for(int i=0;i<competenciasGeneradores.getCompetencias().getCompetencias().size();i++){
                                listCompetencia.add(competenciasGeneradores.getCompetencias().getCompetencias().get(i));
                            }

                            for(int i=0;i<competenciasGeneradores.getCompetencias().getTiendaNeto().size();i++){
                                listCompetenciaTiendaNeto.add(competenciasGeneradores.getCompetencias().getTiendaNeto().get(i));
                            }

                            for(int i=0;i<competenciasGeneradores.getGeneradores().getOtrosGeneradores().size();i++){
                                listGeneradores.add(competenciasGeneradores.getGeneradores().getOtrosGeneradores().get(i));
                            }

                            for(int i=0;i<competenciasGeneradores.getGeneradores().getNegocios().size();i++){
                                listGeneradoresNegocios.add(competenciasGeneradores.getGeneradores().getNegocios().get(i));
                            }

                            for(int i=0;i<competenciasGeneradores.getGeneradores().getTransportePublico().size();i++){
                                listGeneradoresTransporte.add(competenciasGeneradores.getGeneradores().getTransportePublico().get(i));
                            }
                            ///////
                            for(int i=0;i<competenciasGeneradores.getGeneradores().getNegociosDeComida().size();i++){
                                negociosDeComidaArrayList.add(competenciasGeneradores.getGeneradores().getNegociosDeComida().get(i));
                            }

                            for(int i=0;i<competenciasGeneradores.getGeneradores().getMercadoPublico().size();i++){
                                mercadoPublicoArrayList.add(competenciasGeneradores.getGeneradores().getMercadoPublico().get(i));
                            }

                            for(int i=0;i<competenciasGeneradores.getGeneradores().getTianguis().size();i++){
                                tianguiArrayList.add(competenciasGeneradores.getGeneradores().getTianguis().get(i));
                            }


                            /****** transporte negocio comida *******/

                            adapterListaGeneradoresNegociosComida = new AdapterListaGeneradoresNegociosComida(negociosDeComidaArrayList, getContext(), clickNegociosComida);
                            bindingZonificacion.content2.contentListaComida.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaComida.setAdapter(adapterListaGeneradoresNegociosComida);
                            RecyclerView.LayoutManager mLayoutManagerNegociosComida = new GridLayoutManager(getContext(), 4);

                            bindingZonificacion.content2.contentListaComida.setLayoutManager(mLayoutManagerNegociosComida);
                            bindingZonificacion.content2.contentListaComida.addItemDecoration(new FragmentDetalle.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaComida.setItemAnimator(new DefaultItemAnimator());

                            /****** mercado publico *******/
                            adapterListaGeneradoresMercadoPublico = new AdapterListaGeneradoresMercadoPublico(mercadoPublicoArrayList, getContext(), clickMercadoPublico);
                            bindingZonificacion.content2.contentListaMercado.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaMercado.setAdapter(adapterListaGeneradoresMercadoPublico);
                            RecyclerView.LayoutManager mLayoutManagerMercado = new GridLayoutManager(getContext(), 4);

                            bindingZonificacion.content2.contentListaMercado.setLayoutManager(mLayoutManagerMercado);
                            bindingZonificacion.content2.contentListaMercado.addItemDecoration(new FragmentDetalle.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaMercado.setItemAnimator(new DefaultItemAnimator());

                            /****** tianguis *******/
                            adapterListaGeneradoresTianguis = new AdapterListaGeneradoresTianguis(tianguiArrayList, getContext(), clickTiaguis);
                            bindingZonificacion.content2.contentListaTianguis.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaTianguis.setAdapter(adapterListaGeneradoresTianguis);


                            RecyclerView.LayoutManager mLayoutManagerTianguis = new GridLayoutManager(getContext(), 4);

                            bindingZonificacion.content2.contentListaTianguis.setLayoutManager(mLayoutManagerTianguis);
                            bindingZonificacion.content2.contentListaTianguis.addItemDecoration(new FragmentDetalle.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaTianguis.setItemAnimator(new DefaultItemAnimator());


                            /****** transporte publico *******/
                            adapterTransporte = new AdapterListaGeneradoresTransporte(listGeneradoresTransporte, getContext(), clickTransporte);
                            bindingZonificacion.content2.contentListaTransporte.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaTransporte.setAdapter(adapterTransporte);

                            RecyclerView.LayoutManager mLayoutManagerTransporte = new GridLayoutManager(getContext(), 4);

                            bindingZonificacion.content2.contentListaTransporte.setLayoutManager(mLayoutManagerTransporte);
                            bindingZonificacion.content2.contentListaTransporte.addItemDecoration(new FragmentDetalle.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaTransporte.setItemAnimator(new DefaultItemAnimator());

                            /****** otros generadores *******/
                            adapterNegocios = new AdapterListaGeneradoresNegocios(listGeneradoresNegocios, getContext(), clickNegocio);
                            bindingZonificacion.content2.contentListaNegocios.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentListaNegocios.setAdapter(adapterNegocios);

                            RecyclerView.LayoutManager mLayoutManagerNegocios = new GridLayoutManager(getContext(), 4);
                            bindingZonificacion.content2.contentListaNegocios.setLayoutManager(mLayoutManagerNegocios);
                            bindingZonificacion.content2.contentListaNegocios.addItemDecoration(new FragmentDetalle.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentListaNegocios.setItemAnimator(new DefaultItemAnimator());

                            /****** otros generadores *******/
                            adapter2 = new AdapterListaGeneradores(listGeneradores, getContext(), clicks);
                            bindingZonificacion.content2.contentLista.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.content2.contentLista.setAdapter(adapter2);

                            RecyclerView.LayoutManager mLayoutManager2 = new GridLayoutManager(getContext(), 4);
                            bindingZonificacion.content2.contentLista.setLayoutManager(mLayoutManager2);
                            bindingZonificacion.content2.contentLista.addItemDecoration(new FragmentDetalle.GridSpacingItemDecoration(4, dpToPx(5), true));
                            bindingZonificacion.content2.contentLista.setItemAnimator(new DefaultItemAnimator());

                            /****** competencias *******/
                            adapter = new AdapterListaCompetencia(listCompetencia, getContext(), click);
                            bindingZonificacion.contenido.contentLista.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.contenido.contentLista.setAdapter(adapter);

                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
                            bindingZonificacion.contenido.contentLista.setLayoutManager(mLayoutManager);
                            bindingZonificacion.contenido.contentLista.addItemDecoration(new FragmentDetalle.GridSpacingItemDecoration(3, dpToPx(4), true));
                            bindingZonificacion.contenido.contentLista.setItemAnimator(new DefaultItemAnimator());

                            /****** tiendas neto *******/
                            adapterTiendaNeto = new AdapterListaTiendaNeto(listCompetenciaTiendaNeto, getContext(), clickTiendaNeto);
                            bindingZonificacion.contenido.contentListaTienda.setLayoutManager(new LinearLayoutManager(getContext()));
                            bindingZonificacion.contenido.contentListaTienda.setAdapter(adapterTiendaNeto);

                            RecyclerView.LayoutManager mLayoutManagerTienda = new GridLayoutManager(getContext(), 3);
                            bindingZonificacion.contenido.contentListaTienda.setLayoutManager(mLayoutManagerTienda);
                            bindingZonificacion.contenido.contentListaTienda.addItemDecoration(new FragmentDetalle.GridSpacingItemDecoration(3, dpToPx(4), true));
                            bindingZonificacion.contenido.contentListaTienda.setItemAnimator(new DefaultItemAnimator());

                        }

                        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                                .findFragmentById(R.id.map);
                        mapFragment.getMapAsync(onMapReadyCallbackZonificacion);
                    }

                    @Override
                    public void reject(Exception e) {

                    }
                });

                bindingZonificacion.competencia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideCompetencia.show();
                        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("zonificacion", 0);
                        editor.apply();
                    }
                });

                bindingZonificacion.generador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideGenerador.show();
                        bindingZonificacion.content2.contentSlideUpView.setVisibility(View.VISIBLE);
                        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("zonificacion", 1);
                        editor.apply();
                    }
                });

                bindingZonificacion.toolbar.back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(noti){
                            Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                            startActivity(main);
                        }else{
                            FragmentDialogCancelarMdProceso a = new FragmentDialogCancelarMdProceso();
                            a.show(getChildFragmentManager(),"child");
                        }
                    }
                });

                bindingZonificacion.contenido.competencias.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideCompetencia.hide();
                        bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                    }
                });

                bindingZonificacion.content2.generadores.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideGenerador.hide();
                        bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                    }
                });

                bindingZonificacion.contenido.neto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideCompetencia.hide();
                        bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                    }
                });

                ///////////////////////////////

                bindingZonificacion.content2.transporte.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideGenerador.hide();
                        bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                    }
                });

                bindingZonificacion.content2.negC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideGenerador.hide();
                        bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                    }
                });

                bindingZonificacion.content2.merP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideGenerador.hide();
                        bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                    }
                });

                bindingZonificacion.content2.tiand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideGenerador.hide();
                        bindingZonificacion.content2.contentSlideUpView.setVisibility(View.GONE);
                    }
                });
            }

            /*************************************** terminar zonificacion **************************************************/
        }else if (position == 3) {

            final FragmentDetalleGenBinding bindingSuperficie = DataBindingUtil.inflate(inflater,R.layout.fragment_detalle_gen,container,false);
            view = bindingSuperficie.getRoot();

            bindingSuperficie.toolbar.nombreTitulo.setText(getString(R.string.detalles));
            bindingSuperficie.toolbar.guardar.setVisibility(View.INVISIBLE);

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String usuario = preferences.getString("usuario", "");
            final String md = preferences.getString("mdIdterminar", "");
            final String nombre = preferences.getString("nombreSitio", "");
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            loadingProgress(progressDialog, 0);

            int atrasa = preferences.getInt("atrasa",0);


            if(atrasa==1){
                bindingSuperficie.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            }else{
                bindingSuperficie.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }

            ProviderDatosSuperficie.getInstance(getContext())
                    .obtenerDatosSuperficie(md, usuario, new ProviderDatosSuperficie.ConsultaDatosSuperficie() {
                        @Override
                        public void resolve(final Superficie superficie) {
                            if(superficie.getCodigo()==200){
                                loadingProgress(progressDialog, 1);
                                bindingSuperficie.nombresitio.setText(nombre+"");
                                int valorFoto = 0;
                                int valorFrente = 0;
                                int valorFondo = 0;
                                int valorEsquina = 0;

                                for(int i = 0;i<superficie.getNiveles().size();i++){
                                    if(superficie.getNiveles().get(i).getNivel()==4 ||
                                            superficie.getNiveles().get(i).getNivel()==5){
                                        if(!superficie.getNiveles().get(i).getImgFrenteId().isEmpty()){
                                            valorFoto = i;
                                            valorFondo = i;
                                        }
                                    }

                                    if(superficie.getNiveles().get(i).getNivel()==6 ||
                                            superficie.getNiveles().get(i).getNivel()==7){
                                        valorFrente = i;
                                    }

                                    if(superficie.getNiveles().get(i).getNivel()==8){
                                        valorEsquina = i;
                                    }
                                }

                                Double esquina = superficie.getNiveles().get(valorEsquina).getValorreal();

                                if(esquina==1){ }else{ }

                                Slider.init(new PicassoImageLoadingService());
                                slider = bindingSuperficie.map;
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

                                final DecimalFormat formatters = new DecimalFormat("#,###,###");

                                ProviderDatosGeneralidadesSitio.getInstance(getContext())
                                        .obtenerDatosGeneralidades(md, usuario, new ProviderDatosGeneralidadesSitio.ConsultaGeneralidadesSitio() {
                                            @Override
                                            public void resolve(GeneralidadesSitio datosSitio) {
                                                if(datosSitio!=null && datosSitio.getCodigo()==200) {
                                                    if(datosSitio.getGeneralidades().size()>0){
                                                        String fecha = datosSitio.getGeneralidades().get(0).getFechadisponible();

                                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                                        Date d = null;
                                                        try {
                                                            d = formatter.parse(fecha);
                                                        } catch (ParseException e) {
                                                            e.printStackTrace();
                                                        }

                                                        Calendar thatDay = Calendar.getInstance();
                                                        thatDay.setTime(d);

                                                        int year = thatDay.get(Calendar.YEAR);
                                                        int month = thatDay.get(Calendar.MONTH);
                                                        int day = thatDay.get(Calendar.DAY_OF_MONTH);

                                                        for(int i = 0; i < datosSitio.getGeneralidades().size(); i++) {

                                                            if(datosSitio.getGeneralidades().get(i).getNivelid() == 7 ||
                                                                    datosSitio.getGeneralidades().get(i).getNivelid() == 8 ||
                                                                    datosSitio.getGeneralidades().get(i).getNivelid() == 9){

                                                                bindingSuperficie.periodoamotizacion.setText(
                                                                        datosSitio.getGeneralidades().get(i).getDetalles().get(0).getValor()+""
                                                                );

                                                                bindingSuperficie.amortizaciontotal.setText(
                                                                        "$"+formatters.format(datosSitio.getGeneralidades().get(i).getValor())+".00"
                                                                );

                                                            }

                                                            if(datosSitio.getGeneralidades().get(i).getNivelid() == 4 || datosSitio.getGeneralidades().get(i).getNivelid() == 5 || datosSitio.getGeneralidades().get(i).getNivelid() == 6){

                                                                if(datosSitio.getGeneralidades().get(i).getNivelid() == 4){
                                                                    bindingSuperficie.apartirde.setText(getString(R.string.disponible)+"");
                                                                }

                                                                if(datosSitio.getGeneralidades().get(i).getNivelid() == 5){
                                                                    bindingSuperficie.apartirde.setText(getString(R.string.apartir)+" "+datosSitio.getGeneralidades().get(i).getFechadisponible()+"");
                                                                }

                                                                if(datosSitio.getGeneralidades().get(i).getNivelid() == 6){
                                                                    bindingSuperficie.apartirde.setText(R.string.ocupados);
                                                                }

                                                            }

                                                            if(datosSitio.getGeneralidades().get(i).getNivelid() == 1 ||
                                                                    datosSitio.getGeneralidades().get(i).getNivelid() == 2 ||
                                                                    datosSitio.getGeneralidades().get(i).getNivelid() == 3){

                                                                bindingSuperficie.renta.setText("$"+formatters.format(datosSitio.getGeneralidades().get(i).getValor())+".00");
                                                                bindingSuperficie.periodogracia.setText(
                                                                        datosSitio.getGeneralidades().get(i).getDetalles().get(0).getValor()+ ""
                                                                );
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            @Override
                                            public void reject(Exception e) {

                                            }
                                        });



                            }else{
                                loadingProgress(progressDialog, 1);
                            }
                        }
                        @Override
                        public void reject(Exception e) { }
                    });

            bindingSuperficie.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdProceso a = new FragmentDialogCancelarMdProceso();
                        a.show(getChildFragmentManager(),"child");
                    }
                }
            });


        }else if (position == 4) {

            bindingConstruccion = DataBindingUtil.inflate(inflater, R.layout.fragment_autoriza_4_detalle,container,false);
            view = bindingConstruccion.getRoot();

            bindingConstruccion.toolbar.nombreTitulo.setText(getString(R.string.detalles));
            bindingConstruccion.toolbar.guardar.setVisibility(View.INVISIBLE);

            final SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
            final String usuario = preferences.getString("usuario", "");
            final String md = preferences.getString("mdIdterminar", "");
            final String nombre = preferences.getString("nombreSitio", "");

            final String punto = preferences.getString("punto", "");
            final String cate = preferences.getString("cate", "");
            final String fecha = preferences.getString("fechaCreacion", "");

            bindingConstruccion.puntos.setText(punto+"");
            bindingConstruccion.categoria.setText(cate+"");
            bindingConstruccion.fechaCreacion.setText(fecha+"");
            int atrasa = preferences.getInt("atrasa",0);


            if(atrasa==1){
                bindingConstruccion.view3.setBackgroundColor(Color.parseColor("#E4B163"));
            }else{
                bindingConstruccion.view3.setBackgroundColor(Color.parseColor("#D1D5DE"));
            }
            bindingConstruccion.setCategoria(cate);
            bindingConstruccion.toolbar.nombreTitulo.setText(getString(R.string.detalles));
            final ProgressDialog progressDialog = new ProgressDialog(getContext());

            loadingProgress(progressDialog, 0);

            ProviderDatosSuperficie.getInstance(getContext())
                    .obtenerDatosSuperficie(md, usuario, new ProviderDatosSuperficie.ConsultaDatosSuperficie() {
                        @Override
                        public void resolve(final Superficie superficie) {
                            if(superficie.getCodigo()==200){

                                loadingProgress(progressDialog, 1);
                                bindingConstruccion.nombresitio.setText(nombre+"");
                                int valorFoto = 0;
                                int valorEsquina = 0;

                                for(int i = 0;i<superficie.getNiveles().size();i++){
                                    if(superficie.getNiveles().get(i).getNivel()==4 ||
                                            superficie.getNiveles().get(i).getNivel()==5){
                                        if(!superficie.getNiveles().get(i).getImgFrenteId().isEmpty()){
                                            valorFoto = i;
                                        }
                                    }
                                }

                                Double esquina = superficie.getNiveles().get(valorEsquina).getValorreal();

                                if(esquina==1){ }else{ }

                                Slider.init(new PicassoImageLoadingService());
                                slider = bindingConstruccion.map;
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

                                listaPeatonal(bindingConstruccion);

                            }else{
                                loadingProgress(progressDialog, 1);
                            }
                        }
                        @Override
                        public void reject(Exception e) { }
                    });

            bindingConstruccion.toolbar.back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdProceso a = new FragmentDialogCancelarMdProceso();
                        a.show(getChildFragmentManager(),"child");
                    }
                }
            });

        }else {
            final ActivityFinalizaBinding binding;
            binding = DataBindingUtil.inflate(inflater, R.layout.activity_finaliza,container,false);
            view = binding.getRoot();
            getDatos(binding);
            binding.btnFinalizar.setVisibility(View.INVISIBLE);
            binding.btnGuardar.setVisibility(View.INVISIBLE);
            binding.atras.setVisibility(View.INVISIBLE);
            binding.btnAtras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(noti){
                        Intent main = new Intent(getContext(), ActivityNotificaciones.class);
                        startActivity(main);
                    }else{
                        FragmentDialogCancelarMdProceso a = new FragmentDialogCancelarMdProceso();
                        a.show(getChildFragmentManager(),"child");
                    }

                }
            });
        }
        return view;
    }

    String puntuacion, categoria;
    ArrayList<DatosPuntuacion.Factore> factoresMacro;
    ArrayList<DatosPuntuacion.Factore> factoresMicro;
    public void getDatos(final ActivityFinalizaBinding binding){

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdid = preferences.getString("mdIdterminar", "");
        String usuario = preferences.getString("usuario", "");

        ProviderConsultaFinaliza.getInstance(getContext()).obtenerPuntos(mdid, usuario, new ProviderConsultaFinaliza.ConsultaPuntos() {
            @Override
            public void resolve(DatosPuntuacion datosPuntuacion) {
                if(datosPuntuacion.getCodigo()==200) {

                    factoresMacro = new ArrayList<>();
                    factoresMicro = new ArrayList<>();

                    SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);

                    binding.textoTipo.setText(datosPuntuacion.getUbicacionMD()+"");
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
            public void reject(Exception e) {

            }
        });
    }


    public void generarDetallesMacro(ActivityFinalizaBinding binding,  ArrayList<DatosPuntuacion.Factore> datosPuntuacion){

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
            t1v1.setTextColor(resource.getColor(R.color.grisetxt));
            t1v1.setPadding(0, paddingPixel,0,5);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(getContext());
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.grisetxt));
            t3v1.setGravity(Gravity.RIGHT);
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
            t3v2.setTextColor(resource.getColor(R.color.grisetxt));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);


            binding.factores.addView(tbrow);
        }
    }

    public void generarDetallesMicro(ActivityFinalizaBinding binding,  ArrayList<DatosPuntuacion.Factore> datosPuntuacion){

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
            t1v1.setTextColor(resource.getColor(R.color.grisetxt));
            t1v1.setPadding(0, paddingPixel,0,5);
            t1v1.setGravity(Gravity.START);

            t1v1.setLayoutParams( new TableRow.LayoutParams( 660,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t1v1);

            TextView t3v1 = new TextView(getContext());
            t3v1.setTextSize(12);
            t3v1.setText(datosPuntuacion.get(i).getPuntuacion()+"");
            t3v1.setTextColor(resource.getColor(R.color.grisetxt));
            t3v1.setGravity(Gravity.RIGHT);
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
            t3v2.setTextColor(resource.getColor(R.color.grisetxt));
            t3v2.setGravity(Gravity.LEFT);
            t3v2.setLayoutParams( new TableRow.LayoutParams( 75,
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );
            tbrow.addView(t3v2);

            binding.factoresMicro.addView(tbrow);
        }
    }





    HashMap<Integer, String> checks;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_usuario, menu);
        menu.add(0,1,1, Util.menuIcon(getResources().getDrawable(R.drawable.ic_vpn_key_black_24dp),
                getResources().getString(R.string.cambiarContra)));
        menu.add(0, 2, 2, Util.menuIcon(getResources().getDrawable(R.drawable.ic_exit_to_app_black_24dp),
                getResources().getString(R.string.salir)));
    }
    public static boolean isHourInInterval(String target, String start, String end) {
        return ((target.compareTo(start) >= 0)&& (target.compareTo(end) <= 0));
    }
    /**
     * Método que tiene la acción del menu posterior derecha
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case 1:
                // Log.e("contra", "contra");

                return true;
            case 2:
                Util.cerrarSesion(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    String mensaje = null;

    public void slideUX(final FragmentAutoriza3Binding binding){
        slideGenerador = new SlideUpBuilder(binding.content2.slideView)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {
                        binding.dim.setAlpha(1 - (percent / 100));
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility == 0){

                        }
                    }
                }).withStartGravity(Gravity.BOTTOM).withLoggingEnabled(false).withGesturesEnabled(false)
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
                            if (visibility == 0){

                            }
                        }
                    }
                }).withStartGravity(Gravity.BOTTOM).withLoggingEnabled(false).withGesturesEnabled(false)
                .withStartState(SlideUp.State.HIDDEN).withSlideFromOtherView(binding.rootView2)
                .withTouchableAreaPx(100)
                .withTouchableAreaDp(100)
                .build();

    }

    ArrayList<Peatonal> peatonales;
    public void listaPeatonal(final FragmentAutoriza4DetalleBinding binding){
        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdid = preferences.getString("mdIdterminar", "");
        String usuario = preferences.getString("usuario", "");
        ProviderDatosPeatonal.getInstance(getContext()).obtenerDatosPeatonal(mdid, usuario,new ProviderDatosPeatonal.ConsultaPeatonal() {
            @Override
            public void resolve(Peatonales peatonal) {
                peatonales = new ArrayList<>();
                if(peatonal!=null && peatonal.getCodigo()==200){

                    if(peatonal.getConteos().size()>0){
                        for(int i=0;i<peatonal.getConteos().size();i++){
                            for(int j=0;j<peatonal.getConteos().get(i).getDetalle().size();j++){
                                peatonales.add(new Peatonal(j,
                                        peatonal.getConteos().get(i).getDetalle().get(j).getFecha(),
                                        Integer.valueOf(peatonal.getConteos().get(i).getDetalle().get(j).getValor()),
                                        0.0,0.0, peatonal.getConteos().get(i).getDetalle().get(j).getNombreGenerador()));
                            }
                        }

                        binding.promedio.setText(peatonal.getConteos().get(0).getPromedioPeatonal()+"");
                        binding.recyclerPeatonal.setHasFixedSize(true);
                        AdapterAutorizaPeatonal adapter = new AdapterAutorizaPeatonal(getContext(), ALPHABETICAL_COMPARATOR, n);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

                        binding.recyclerPeatonal.setLayoutManager(layoutManager);
                        binding.recyclerPeatonal.setAdapter(adapter);
                        adapter.edit().replaceAll(peatonales).commit();
                        adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
                    }else{

                    }
                }else{ }
            }
            @Override
            public void reject(Exception e) { }
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

    /******** gps **************/
    ServicioGPS gpsUbicas;

    Double latitude, longitude, latitudeLast = 0.0, longitudeLast = 0.0;

    public Ubicacion gps() {
        Ubicacion ubicacion;
        latitudeLast = latitude;
        longitudeLast = longitude;
        gpsUbicas = new ServicioGPS(getContext());
        if (gpsUbicas.canGetLocation()) {
            latitude = gpsUbicas.getLatitude();
            longitude = gpsUbicas.getLongitude();
            ubicacion = new Ubicacion(latitude, longitude, true);
        } else {
            if(latitudeLast!=null){
                ubicacion = new Ubicacion(latitudeLast, longitudeLast, false);
            }else{
                ubicacion = new Ubicacion(0.0, 0.0, false);
            }
        }
        return ubicacion;
    }

    public void setDireccion(FragmentDetalleSitioBinding binding, Double lat, Double lng){
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            if(mCenterLatLong!=null){
                addresses = geocoder.getFromLocation(lat, lng, 1);
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                binding.direccionsitio.setText(address);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    FragmentAutoriza4DetalleBinding bindingConstruccion;

    Marker market;

    ArrayList<Marker> markers = new ArrayList<>();
    ArrayList<CrearZonificacion.Zonificacion> competencia;
    ArrayList<CrearZonificacion.Zonificacion> generadores;
    ArrayList<CrearZonificacion.Detalle> detallesC = new ArrayList<>();;
    ArrayList<CrearZonificacion.Detalle> detallesG = new ArrayList<>();;

    CrearZonificacion.Detalle detalleC;
    CrearZonificacion.Detalle detalleG;

    CrearZonificacion.Zonificacion zonificacionC = new CrearZonificacion.Zonificacion();
    CrearZonificacion.Zonificacion zonificacionG = new CrearZonificacion.Zonificacion();

    public void colocarMarcador(LatLng latLng, GoogleMap mMap, int valor,
                                String usuario, LatLng mds, String mdIdZ,
                                ArrayList<Zonificacion.Detalle> detallesGene, ArrayList<Zonificacion.Detalle> detallesCompe){

        if(valor==1){
            icon = getBitmapDescriptor(R.drawable.bbb);
        }else if(valor==2){
            icon = getBitmapDescriptor(R.drawable.oxxo);
        }else if(valor==3){
            icon = getBitmapDescriptor(R.drawable.bodegaa);
        }else if(valor==4){
            icon = getBitmapDescriptor(R.drawable.abarrotes);
        }else if(valor==5){
            icon = getBitmapDescriptor(R.drawable.g_iglesia);
        }else if(valor==6){
            icon = getBitmapDescriptor(R.drawable.g_mercado);
        }else if(valor==7){
            icon = getBitmapDescriptor(R.drawable.escuela);
        }else if(valor==8){
            icon = getBitmapDescriptor(R.drawable.g_busstop);
        }else if(valor==9){
            icon = getBitmapDescriptor(R.drawable.otros);
        }else if(valor==10){
            icon = getBitmapDescriptor(R.drawable.netos);
        }else if(valor==11){
            icon = getBitmapDescriptor(R.drawable.g_recauderia);
        }else if(valor==12){
            icon = getBitmapDescriptor(R.drawable.g_comida);
        }else if(valor==13){
            icon = getBitmapDescriptor(R.drawable.g_mercado);
        }else if(valor==14){
            icon = getBitmapDescriptor(R.drawable.g_tianguis);
        }else if(valor==15){
            icon = getBitmapDescriptor(R.drawable.g_tortilleria);
        }else if(valor==16){
            icon = getBitmapDescriptor(R.drawable.g_carniceria);
        }else if(valor==17){
            icon = getBitmapDescriptor(R.drawable.metro);
        }

        String nivel = "";

        if(mMap!=null){


            market = mMap.addMarker(new
                    MarkerOptions().
                    position(latLng).
                    title("").snippet("")
                    .icon(icon));

            markers.add(market);

            if(valor == 1 || valor == 2 || valor == 3
                    || valor == 4){

                nivel = distancia(latLng, mds, "competencia");

                detalleC = new CrearZonificacion.Detalle(
                        String.valueOf(valor),
                        String.valueOf(market.getPosition().latitude),
                        String.valueOf(market.getPosition().longitude),
                        nivel
                );

                detallesC.add(detalleC);


                zonificacionC.setDetalles(detallesC);
                competencia = new ArrayList<>();
                competencia.add(zonificacionC);

            }else if(valor == 5 || valor == 6 || valor == 7 ||
                    valor == 8 || valor == 9 || valor == 10 ||
                    valor == 11 || valor == 12){

                nivel = distancia(latLng, mds, "generadores");

                detalleG = new CrearZonificacion.Detalle(
                        String.valueOf(valor),
                        String.valueOf(market.getPosition().latitude),
                        String.valueOf(market.getPosition().longitude),
                        nivel
                );


                detallesG.add(detalleG);

                zonificacionG.setDetalles(detallesG);
                generadores = new ArrayList<>();
                generadores.add(zonificacionG);
            }

            if(generadores==null){

            }

            if(competencia==null){

            }

            if(detallesGene!=null){
                if(detallesGene.size()==0){
                    detallesG = new ArrayList<>();;

                    detalleG = new CrearZonificacion.Detalle(
                            "6"
                    );

                    detallesG.add(detalleG);
                    zonificacionG.setDetalles(detallesG);

                    generadores = new ArrayList<>();
                    generadores.add(zonificacionG);
                }
            }

            if(detallesCompe!=null){
                if(detallesCompe.size()==0){
                    detallesC = new ArrayList<>();
                    detalleC = new CrearZonificacion.Detalle(
                            "1"
                    );

                    detallesC.add(detalleC);
                    zonificacionC.setDetalles(detallesC);


                    competencia = new ArrayList<>();
                    competencia.add(zonificacionC);
                }
            }


            zonificacion = new CrearZonificacion(
                    usuario,
                    mdIdZ,
                    competencia,
                    generadores,
                    String.valueOf(mdLat),
                    String.valueOf(mdLot),
                    "5555555555",
                    VERSION_APP
            );

            zonificacionJson = getJsonString(zonificacion);
        }

    }

    DatosConstruccions datosSitios;

    public String distancia(LatLng latLng, LatLng mdId, String tipo){
        Location loc1 = new Location("");
        loc1.setLatitude(latLng.latitude);
        loc1.setLongitude(latLng.longitude);

        Location loc2 = new Location("");
        loc2.setLatitude(mdId.latitude);
        loc2.setLongitude(mdId.longitude);

        float distanciaMetros = loc1.distanceTo(loc2);

        if(tipo.equals("competencia")){
            if(distanciaMetros <= 500){
                return "2";
            }else if(distanciaMetros>=500 && distanciaMetros<=1500){
                return "3";
            }else{
                return "1";
            }
        }else{
            if(distanciaMetros <= 500){
                return "4";
            }else if(distanciaMetros>=500 && distanciaMetros<=1500){
                return "5";
            }else{
                return "6";
            }
        }
    }

    String zonificacionJson = "";

    private String getJsonString(CrearZonificacion zonificacion) {
        Gson gson = new Gson();
        String json = gson.toJson(zonificacion);
        return json;
    }

    public String getFechaHora(){
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss");
        String dateforrow = dateFormat.format(cal1.getTime());
        return dateforrow;
    }
}