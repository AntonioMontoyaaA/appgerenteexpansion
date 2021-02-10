package expansion.neto.com.mx.gerenteapp.radios.ui.radios;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.databinding.ActivityDetalleRadiosBinding;
import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityDetalleRadiosBinding;
import expansion.neto.com.mx.gerenteapp.modelView.Ubicacion;
import expansion.neto.com.mx.gerenteapp.radios.fragment.radios.FragmentAceptar;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.Competencia;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.GeneradoresRadio;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.GuardarV;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.SinSitios;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.ValidaUb;
import expansion.neto.com.mx.gerenteapp.radios.provider.radiosProvider.ProvaiderDatosRadios;
import expansion.neto.com.mx.gerenteapp.ui.autoriza.ActivityAutorizar;
import expansion.neto.com.mx.gerenteapp.utils.ServicioGPS;

import static android.view.View.GONE;
import static expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion.FragmentAutoriza.loadingProgress;

public class ActivityDetalleRadios extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityDetalleRadiosBinding binding;

    public static final String LATITUD = "latitud";
    public static final String FECHAASIGANDO = "tvFechaAsignado";
    public static final String USUARIO_ASIGNADO = "tvusuarioAsignado";
    public static final String RADIOID = "radioId";
    public static final String RECHAZADO = "rechazado";
    public static final String SIN_SITIO = "sin_sitio";
    public static final String CANCELADO = "cancelado";
    public static final String COMPETENCIA = "competencia";
    public static final String LONGITUD = "longitud";
    public static final String STATUSID = "tvStatusId";
    public static final String fcTotalCompetencia = "fcTotalCompetencia";
    public static final String fcPobFlotante = "fcPobFlotante";
    public static final String fcPobTotal = "fcPobTotal";
    public static final String fcTotalGeneradores = "fcTotalGeneradores";
    public static final String fcMas60 = "fcMas60";
    public static final String fcAmaDeCasa = "fcAmaDeCasa";
    public static final String fcTrabajoPorCuenta = "fcTrabajoPorCuenta";
    public static final String fcPea = "fcPea";
    public static final String fcTemplos = "fcTemplos";
    public static final String fcJubilado = "fcJubilado";
    public static final String fcDesempleado = "fcDesempleado";
    public static final String fcOficinasGobierno = "fcOficinasGobierno";
    public static final String fcUrl = "fcUrl";
    public static final String fcMunicipio = "fcMunicipio";
    public static final String fcBAE = "fcBAE";
    public static final String fcMercados = "fcMercados";
    public static final String fcHospitales = "fcHospitales";
    public static final String fcMenos26 = "fcMenos26";
    public static final String fcOtraOcupacion = "fcOtraOcupacion";
    public static final String fcServidorPublico = "fcServidorPublico";
    public static final String fcEstudiante= "fcEstudiante";
    public static final String fcDe31a40 = "fcDe31a40";
    public static final String fcVentaArticulos= "fcVentaArticulos";
    public static final String fcServicios = "fcServicios";
    public static final String fcTotalUE = "fcTotalUE";
    public static final String fcDe51a60 = "fcDe51a60";
    public static final String fcJornalero = "fcJornalero";
    public static final String fcMIBA = "fcMIBA";
    public static final String fcEmpresario = "fcEmpresario";
    public static final String fcCallePrincipal = "fcCallePrincipal";
    public static final String fcObrero = "fcObrero";
    public static final String fcEmpleado = "fcEmpleado";
    public static final String fcDe41a50 = "fcDe41a50";
    public static final String fcProfesionista = "fcProfesionista";
    public static final String fcTotalHogares = "fcTotalHogares";
    public static final String fcBBB = "fcBBB";
    public static final String fcDe26a30 = "fcDe26a30";
    public static final String fcCalle2 = "fcCalle2";
    public static final String fcVentaAlimentos = "fcVentaAlimentos";
    public static final String fcCalle1 = "fcCalle1";
    public static final String fcEscuelas = "fcEscuelas";
    public static final String fcColonia = "fcColonia";
    public static final String fcEjidatario = "fcEjidatario";
    public static final String VISITAS_RADIO = "visitasRadio";
    public static final String GENERADORES_RADIOS = "generadoresRadios";
    public static final String STATUS = "tvStatus";
    public static final String LONG_RADIO = "tvLongRadio";
    public static final String NOMBRERADIO = "tvNombreRadio";

    private ArrayList<Marker> marcadoresTiendas3B = new ArrayList<>();
    private ArrayList<Marker> marcadoresBodegaAurreraEx = new ArrayList<>();
    private ArrayList<Marker> marcadoresMiBodegaAurrera = new ArrayList<>();
    private ArrayList<Marker> marcadoresPanaderia = new ArrayList<>();
    private ArrayList<Marker> marcadoresTortilleria = new ArrayList<>();
    private ArrayList<Marker> marcadoresAbarrotes = new ArrayList<>();
    private ArrayList<Marker> marcadoresCarniceria = new ArrayList<>();
    private ArrayList<Marker> marcadoresPolleria = new ArrayList<>();
    private ArrayList<Marker> marcadoresHospitales = new ArrayList<>();
    private ArrayList<Marker> marcadoresEscuelas = new ArrayList<>();
    private ArrayList<Marker> marcadoresMercado = new ArrayList<>();
    private ArrayList<Marker> marcadoresOGobierno = new ArrayList<>();
    private ArrayList<Marker> marcadoresRecauderias = new ArrayList<>();
    private ArrayList<Marker> marcadoresIglesias = new ArrayList<>();


    private ImageView ivMenu,ivMenu2 ;
    private LinearLayout lyMenu ;


    ProgressDialog progressDialog;
    String radioID;
    private static ActivityDetalleRadios myContext;

    final Handler handler = new Handler();

    public static ActivityDetalleRadios getMyContext() {
        return myContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initDataBinding();

        ivMenu = findViewById( R.id.iv_menu );
        ivMenu2 = findViewById( R.id.iv_menu2 );
        lyMenu = findViewById( R.id.ly_menu );


        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(ActivityDetalleRadios.this);
        mapFragment.getView().setVisibility(View.INVISIBLE);

        progressDialog = new ProgressDialog(ActivityDetalleRadios.this);
        TabLayout tabLayout = binding.tabLayout;
        tabLayout.addTab(tabLayout.newTab().setText( " "+getString(R.string.detalle)));
        tabLayout.addTab(tabLayout.newTab().setText( " "+getString(R.string.mapa)));
        tabLayout.setTabGravity( TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    binding.tvDetalle.setVisibility(View.VISIBLE);
                    binding.lldetalle.setVisibility(View.VISIBLE);
                    mapFragment.getView().setVisibility(View.INVISIBLE);
                }else {
                    binding.tvDetalle.setVisibility(GONE);
                    binding.lldetalle.setVisibility(GONE);
                    mapFragment.getView().setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(ActivityDetalleRadios.this, ActivityRadios.class);
                startActivity(i);
            }
        });

        pintarDatos();

    }

    /* Método que setea la vista con el binding */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalle_radios);
    }

    private void pintarDatos() {
        Bundle bundle = getIntent().getExtras();
        binding.tvNombreRadio.setText( " "+bundle.getString(NOMBRERADIO));
        binding.tvFechaAsignadoD.setText( "Asignado el " + bundle.getString(FECHAASIGANDO));
        binding.tvDetalleNombre.setText( " "+ bundle.getString( NOMBRERADIO ) );
        binding.tvAsignado.setText( "Asignado: " + bundle.getString( USUARIO_ASIGNADO ) );
        binding.tvCoordenas.setText( " "+bundle.getString( LATITUD ) + " , "+bundle.getString( LONGITUD ) );
        binding.tvRadio.setText( " "+ bundle.getString( LONG_RADIO  ) + " mts" );
        binding.tvUrl.setText( " "+  bundle.getString( fcUrl )  );

        binding.tvEstatus.setText( " "+  bundle.getString( STATUS )  );
        binding.tvCalleP.setText( " "+  bundle.getString( fcCallePrincipal )  );
        binding.tvCalle1.setText( " "+  bundle.getString( fcCalle1 )  );
        binding.tvCalle2.setText( " "+  bundle.getString( fcCalle2 )  );
        binding.tvColonia.setText( " "+  bundle.getString( fcColonia )  );
        binding.tvMunicipio.setText( " "+  bundle.getString( fcMunicipio )  );
        binding.tvNoVisitas.setText( " "+  bundle.getString( VISITAS_RADIO )  );
        binding.tvValorMas61.setText( " "+ bundle.getString( fcMenos26 ) );
        binding.tvValor2130.setText( " "+ bundle.getString( fcDe26a30 ) );
        binding.tvValor3140.setText( " "+ bundle.getString( fcDe31a40 ) );
        binding.tvValor4150.setText( " "+ bundle.getString( fcDe41a50 ) );
        binding.tvValor5160.setText( " "+ bundle.getString( fcDe51a60 ) );
        binding.tvValorMenor20.setText( " "+ bundle.getString( fcMas60 ) );
        binding.tvMenor20.setText("<-20" );
        binding.tvMayor60.setText("60-<" );
        binding.tvPTotal.setText( " "+ bundle.getString( fcPobTotal ) );
        binding.tvPFlotante.setText( " "+ bundle.getString( fcPobFlotante ) );
        binding.tvPea.setText( " "+ bundle.getString( fcPea ) );
        binding.tvHogares.setText( " "+ bundle.getString( fcTotalHogares ) );
        binding.tvValorMercados.setText( " "+ bundle.getString( fcMercados ) );
        binding.tvValorEscuelas.setText( " "+ bundle.getString( fcEscuelas ) );
        binding.tvValorHospitales.setText( " "+ bundle.getString( fcHospitales ) );
        binding.tvValorTemplos.setText( " "+ bundle.getString( fcTemplos ) );
        binding.tvOficinasGob.setText( " "+ bundle.getString( fcOficinasGobierno ) );
        List<Competencia> listaCompetencia = bundle.getParcelableArrayList( COMPETENCIA );
        //binding.tvValorCompetencia1.setText( " "+ listaCompetencia.get( 0 ).getCompetenciaId() );
        binding.tvValorCompetencia1.setText( " "+ bundle.getString( fcOficinasGobierno ) );
        binding.tvValorCompetencia2.setText( " "+ bundle.getString( fcOficinasGobierno ) );
        binding.tvValorCompetencia3.setText( " "+ bundle.getString( fcOficinasGobierno ) );
        binding.tvValorPreparacionAlim.setText( " "+ bundle.getString( fcVentaAlimentos ) );
        binding.tvValorVentaArt.setText( " "+ bundle.getString( fcVentaArticulos ) );
        binding.tvValorServicios.setText( " "+ bundle.getString( fcServicios ) );
        binding.tvStatus.setText( " "+bundle.getString(STATUS));

        System.out.println( "Valor Rechazados: "+ bundle.getString( RECHAZADO )+ "VALOR NOMBRE: " + bundle.getString( NOMBRERADIO ) );
        if (bundle.getString( RECHAZADO ).equals( "0" ) || bundle.getString( RECHAZADO ).equals( "4" )|| bundle.getString( RECHAZADO ).equals( "2" )){
            binding.llBotones.setVisibility( GONE );
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) binding.lldetalle.getLayoutParams();
            params.bottomMargin = 15;
            binding.lldetalle.setLayoutParams( params );
        }

        radioID = bundle.getString(RADIOID);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Bundle bundle = getIntent().getExtras();
        List<Competencia> listaCompetencia = bundle.getParcelableArrayList( COMPETENCIA );
        List<GeneradoresRadio> listaGeneradores = bundle.getParcelableArrayList( GENERADORES_RADIOS );
        Double latitud = Double.parseDouble(bundle.getString(LATITUD));
        Double longitud = Double.parseDouble(bundle.getString(LONGITUD));
        Resources resource = ActivityDetalleRadios.this.getResources();
        googleMap.addMarker( new MarkerOptions()
                .anchor( 0.5f,0.5f )
                .position( new LatLng( latitud,longitud ) )
                .zIndex( 0f )
                .icon( BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_AZURE ) ));
        googleMap.addCircle(new CircleOptions()
                .center(new LatLng(latitud,longitud))
                .radius(500)
                .strokeColor(resource.getColor(R.color.radios))
                .fillColor(resource.getColor(R.color.radios)));

        PintarMarcadores( googleMap, listaCompetencia, listaGeneradores );
        /*for (int i = 0; i<listaCompetencia.size();i++){
            switch (listaCompetencia.get( i ).getGenerador()){
                case "Tiendas 3B":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaCompetencia.get( i ).getLatitud()),Double.parseDouble(listaCompetencia.get( i ).getLongitud() )))
                            .zIndex( 1.0f )
                            .title( listaCompetencia.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_tres_b2_foreground ) ));
                    break;
                case "BODEGA AURRERA EXPRESS":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaCompetencia.get( i ).getLatitud()),Double.parseDouble(listaCompetencia.get( i ).getLongitud() )))
                            .zIndex( 1.0f )
                            .title( listaCompetencia.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_aurrera_express2_foreground ) ));
                    break;
                case "MI BODEGA AURRERA":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaCompetencia.get( i ).getLatitud()),Double.parseDouble(listaCompetencia.get( i ).getLongitud() )))
                            .zIndex( 1.0f )
                            .title( listaCompetencia.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_aurrera2_foreground ) ));
                    break;
                default:
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaCompetencia.get( i ).getLatitud()),Double.parseDouble(listaCompetencia.get( i ).getLongitud() )))
                            .zIndex( 1.0f )
                            .title( listaCompetencia.get( i ).getGenerador() )
                            .anchor( 0.5f,0.5f )
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.icon_templo ) ));
                    break;
            }
        }





        for (int i = 0; i<listaGeneradores.size();i++){
            switch (listaGeneradores.get( i ).getGenerador()){
                case "PANADERIA":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_panaderia2_foreground ) ));
                    break;
                case "TORTILLERIA":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_tortilleria2_foreground ) ));
                    break;
                case "ABARROTES":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_abarrotes2_foreground ) ));
                    break;
                case "CARNICERIA":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_carniceria2_foreground ) ));
                    break;
                case "POLLERIA":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_polleria2_foreground ) ));
                    break;
                case "HOSPITAL":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_hospital2_foreground ) ));
                    break;
                case "ESCUELA":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_escuela2_foreground ) ));
                    break;
                case "MERCADO":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_mercado2_foreground ) ));
                    break;
                case "OFICINA DE GOBIERNO":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_ofgobierno2_foreground ) ));
                    break;
                case "RECAUDERIA":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_recauderia2_foreground ) ));
                    break;
                case "IGLESIA":
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor(0.5f,0.5f)
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_templo2_foreground ) ));
                    break;
                default:
                    googleMap.addMarker( new MarkerOptions()
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getGenerador() )
                            .anchor( 0.5f,0.5f )
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.icon_templo ) ));
                    break;


            }
        }*/

        /*googleMap.addMarker( new MarkerOptions()
        .position( new LatLng( Double.parseDouble(listaGeneradores.get( 0 ).getLatitud()),Double.parseDouble(listaGeneradores.get( 0 ).getLongitud() )))
        .title( listaGeneradores.get( 0 ).getGenerador() )
        .anchor(0f.5,0.f5
        .icon( BitmapDescriptorFactory.fromResource( R.mipmap.icon_bbbicon_pin_abarrotes2_foreground ) ));*/
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitud,longitud))
                .zoom(15)
                .bearing(0)
                .tilt(0)
                .build();
        googleMap.animateCamera( CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void PintarMarcadores(GoogleMap googleMap, List<Competencia> listaCompetencia, List<GeneradoresRadio> listaGeneradores) {
        for (int i = 0; i<listaCompetencia.size();i++){
            switch (listaCompetencia.get( i ).getGenerador()){
                case "Tiendas 3B":
                    marcadoresTiendas3B.add(  googleMap.addMarker( new MarkerOptions()
                            .anchor( 0.5f,0.5f )
                            .position( new LatLng( Double.parseDouble(listaCompetencia.get( i ).getLatitud()),Double.parseDouble(listaCompetencia.get( i ).getLongitud() )))
                            .zIndex( 1.0f )
                            .title( listaCompetencia.get( i ).getGenerador() )
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_tres_b2_foreground ) ) ));
                    /*if (listaCompetencia.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresTiendas3B.get( i ).setVisible( false );
                    }else {
                        marcadoresTiendas3B.get( i ).setVisible( true );

                    }*/
                    break;
                case "BODEGA AURRERA EXPRESS":
                    marcadoresBodegaAurreraEx.add(  googleMap.addMarker( new MarkerOptions()
                            .anchor( 0.5f,0.5f )
                            .position( new LatLng( Double.parseDouble(listaCompetencia.get( i ).getLatitud()),Double.parseDouble(listaCompetencia.get( i ).getLongitud() )))
                            .zIndex( 1.0f )
                            .title( listaCompetencia.get( i ).getGenerador() )
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_aurrera_express2_foreground ) ) ));
                    /*if (listaCompetencia.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresBodegaAurreraEx.get( i ).setVisible( false );
                    }else {
                        marcadoresBodegaAurreraEx.get( i ).setVisible( true );

                    }*/
                    break;
                case "MI BODEGA AURRERA":
                    marcadoresMiBodegaAurrera.add(  googleMap.addMarker( new MarkerOptions()
                            .anchor( 0.5f,0.5f )
                            .position( new LatLng( Double.parseDouble(listaCompetencia.get( i ).getLatitud()),Double.parseDouble(listaCompetencia.get( i ).getLongitud() )))
                            .zIndex( 1.0f )
                            .title( listaCompetencia.get( i ).getGenerador() )
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_aurrera2_foreground ) ) ));
                    /*if (listaCompetencia.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresMiBodegaAurrera.get( i ).setVisible( false );
                    }else {
                        marcadoresMiBodegaAurrera.get( i ).setVisible( true );

                    }*/
                    break;
                default:
                    googleMap.addMarker( new MarkerOptions()
                            .anchor( 0.5f,0.5f )
                            .position( new LatLng( Double.parseDouble(listaCompetencia.get( i ).getLatitud()),Double.parseDouble(listaCompetencia.get( i ).getLongitud() )))
                            .zIndex( 1.0f )
                            .title( listaCompetencia.get( i ).getGenerador() )
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.icon_templo ) ));
                    break;
            }
        }


        for (int i = 0; i<listaGeneradores.size();i++){
            switch (listaGeneradores.get( i ).getGenerador()){
                case "PANADERIA":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresPanaderia.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_panaderia2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresPanaderia.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_panaderia2_foreground ) )
                                .visible( true )));
                        binding.checkPanaderia.setChecked( true );


                    }
                    break;
                case "TORTILLERIA":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresTortilleria.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_tortilleria2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresTortilleria.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_tortilleria2_foreground ) )
                                .visible( true )));
                        binding.checkTortilleria.setChecked( true );


                    }
                    break;
                case "ABARROTES":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresAbarrotes.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_abarrotes2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresAbarrotes.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_abarrotes2_foreground ) )
                                .visible( true )));
                        binding.checkAbarrotes.setChecked( true );


                    }
                    break;
                case "CARNICERIA":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresCarniceria.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_carniceria2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresCarniceria.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_carniceria2_foreground ) )
                                .visible( true )));
                        binding.checkCarniceria.setChecked( true );


                    }
                    break;
                case "POLLERIA":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresPolleria.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_polleria2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresPolleria.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_polleria2_foreground ) )
                                .visible( true )));
                        binding.checkPolleria.setChecked( true );


                    }
                    break;
                case "HOSPITALES":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresHospitales.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_hospital2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresHospitales.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_hospital2_foreground ) )
                                .visible( true )));
                        binding.checkHospitales.setChecked( true );


                    }
                    break;
                case "ESCUELAS":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresEscuelas.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_escuela2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresEscuelas.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_escuela2_foreground ) )
                                .visible( true )));
                        binding.checkEscuelas.setChecked( true );


                    }
                    break;
                case "MERCADO":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresMercado.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_mercado2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresMercado.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_mercado2_foreground ) )
                                .visible( true )));
                        binding.checkMercado.setChecked( true );


                    }
                    break;
                case "OFICINA DE GOBIERNO":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresOGobierno.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_ofgobierno2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresOGobierno.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_ofgobierno2_foreground ) )
                                .visible( true )));
                        binding.checkOGobierno.setChecked( true );


                    }
                    break;
                case "RECAUDERIAS":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresRecauderias.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_recauderia2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresRecauderias.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_recauderia2_foreground ) )
                                .visible( true )));
                        binding.checkRecauderias.setChecked( true );


                    }
                    break;
                case "IGLESIAS":
                    if (listaGeneradores.get( i ).getTipogeneradorId().equals( "2" )){
                        marcadoresIglesias.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_templo2_foreground ) )
                                .visible( false )));

                    }else {
                        marcadoresIglesias.add(  googleMap.addMarker( new MarkerOptions()
                                .anchor( 0.5f,0.5f )
                                .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                                .title( listaGeneradores.get( i ).getGenerador() )
                                .icon( BitmapDescriptorFactory.fromResource( R.mipmap.pin_templo2_foreground ) )
                                .visible( true )));
                        binding.checkIglesias.setChecked( true );


                    }
                    break;
                default:
                    googleMap.addMarker( new MarkerOptions()
                            .anchor( 0.5f,0.5f )
                            .position( new LatLng( Double.parseDouble(listaGeneradores.get( i ).getLatitud()),Double.parseDouble(listaGeneradores.get( i ).getLongitud() )))
                            .title( listaGeneradores.get( i ).getLatitud() )
                            .icon( BitmapDescriptorFactory.fromResource( R.mipmap.icon_templo ) ));
                    break;


            }
        }
    }


    public void Autorizar(final View view) {

        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");
        Bundle bundle = getIntent().getExtras();
        Ubicacion gpsUbica;
        gpsUbica = gps();
        String latitud= String.valueOf( gpsUbica.lat ), longitud = String.valueOf( gpsUbica.lng );

        loadingProgress(progressDialog, 0);
        ProvaiderDatosRadios.getInstance( this ).validaUbicacion( usuario, bundle.getString( RADIOID ), latitud, longitud, new ProvaiderDatosRadios.ValidaUbF() {
            @Override
            public void resolve(ValidaUb validaUb) {
                if(validaUb.getCodigo()== 200){
                    SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    String usuario = preferences.getString("usuario", "");
                    ProvaiderDatosRadios.getInstance(ActivityDetalleRadios.getMyContext()).sinSitiosDisponibles(usuario, radioID,"1", new ProvaiderDatosRadios.SinSitiosI() {
                        @Override
                        public void resolve( SinSitios sinSitios ) {
                            loadingProgress(progressDialog, 1);
                            if(sinSitios.getCodigo()== 200){
                                FragmentAceptar a = new FragmentAceptar();
                                a.setMensaje("autorizar");
                                a.show(getSupportFragmentManager(),"child");
                            }

                        }

                        @Override
                        public void reject(Exception e) {
                            loadingProgress(progressDialog, 1);
                            MostrarSnack( view, "No se pudo actualizar el estatus");

                        }
                    });

                }else {
                    loadingProgress( progressDialog,1 );
                    MostrarSnack( view, "Tiene que encontrarse dentro del radio asignado para poder Autorizar el radio");

                }

            }

            @Override
            public void reject(Exception e) {

                loadingProgress(progressDialog, 1);
                MostrarSnack( view, "No se pudo consultar la información de la ubicación actual");


            }
        } );
    }

    public void Rechazar(final View view) {

        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");
        Bundle bundle = getIntent().getExtras();
        Ubicacion gpsUbica;
        gpsUbica = gps();
        String latitud= String.valueOf( gpsUbica.lat ), longitud = String.valueOf( gpsUbica.lng );
        loadingProgress(progressDialog, 0);

        ProvaiderDatosRadios.getInstance( this ).validaUbicacion( usuario, bundle.getString( RADIOID ), latitud, longitud, new ProvaiderDatosRadios.ValidaUbF() {
            @Override
            public void resolve(ValidaUb validaUb) {
                if(validaUb.getCodigo()== 200){
                    SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    String usuario = preferences.getString("usuario", "");
                    ProvaiderDatosRadios.getInstance(ActivityDetalleRadios.getMyContext()).sinSitiosDisponibles(usuario, radioID,"0", new ProvaiderDatosRadios.SinSitiosI() {
                        @Override
                        public void resolve( SinSitios sinSitios ) {
                            loadingProgress(progressDialog, 1);
                            if(sinSitios.getCodigo()== 200){
                                FragmentAceptar a = new FragmentAceptar();
                                a.setMensaje("rechazar");
                                a.show(getSupportFragmentManager(),"child");
                            }

                        }

                        @Override
                        public void reject(Exception e) {
                            loadingProgress(progressDialog, 1);
                            MostrarSnack( view, "No se pudo actualizar el estatus");

                        }
                    });

                }else {
                    loadingProgress( progressDialog,1 );
                    MostrarSnack( view, "Tiene que encontrarse dentro del radio asignado para poder rechazar el radio");

                }

            }

            @Override
            public void reject(Exception e) {

                loadingProgress(progressDialog, 1);
                MostrarSnack( view, "No se pudo consultar la información de la ubicación actual");


            }
        } );
    }


    /******** gps **************/
    ServicioGPS gpsUbicas;

    Double latitude, longitude, latitudeLast = 0.0, longitudeLast = 0.0;

    public Ubicacion gps() {
        Ubicacion ubicacion;
        latitudeLast = latitude;
        longitudeLast = longitude;
        gpsUbicas = new ServicioGPS(getApplicationContext());
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

    public void MostrarSnack(View view, String texto){
        Snackbar snackbar = Snackbar.make(view, texto, Snackbar.LENGTH_LONG);
        View view2 = snackbar .getView();
        TextView textView = (TextView) view2.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public void verMacador(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.checkTiendas3B:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkTiendas3B ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresTiendas3B.size();i ++ ){
                        marcadoresTiendas3B.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresTiendas3B.size();i ++ ){
                        marcadoresTiendas3B.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkBodegaAurreraEx:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkBodegaAurreraEx ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresBodegaAurreraEx.size();i ++ ){
                        marcadoresBodegaAurreraEx.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresBodegaAurreraEx.size();i ++ ){
                        marcadoresBodegaAurreraEx.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkMiBodegaAurrera:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkMiBodegaAurrera ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresMiBodegaAurrera.size();i ++ ){
                        marcadoresMiBodegaAurrera.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresMiBodegaAurrera.size();i ++ ){
                        marcadoresMiBodegaAurrera.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkPanaderia:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkPanaderia ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresPanaderia.size();i ++ ){
                        marcadoresPanaderia.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresPanaderia.size();i ++ ){
                        marcadoresPanaderia.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkTortilleria:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkTortilleria ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresTortilleria.size();i ++ ){
                        marcadoresTortilleria.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresTortilleria.size();i ++ ){
                        marcadoresTortilleria.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkAbarrotes:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkAbarrotes ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresAbarrotes.size();i ++ ){
                        marcadoresAbarrotes.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresAbarrotes.size();i ++ ){
                        marcadoresAbarrotes.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkCarniceria:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkCarniceria ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresCarniceria.size();i ++ ){
                        marcadoresCarniceria.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresCarniceria.size();i ++ ){
                        marcadoresCarniceria.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkPolleria:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkPolleria ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresPolleria.size();i ++ ){
                        marcadoresPolleria.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresPolleria.size();i ++ ){
                        marcadoresPolleria.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkHospitales:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkHospitales ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresHospitales.size();i ++ ){
                        marcadoresHospitales.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresHospitales.size();i ++ ){
                        marcadoresHospitales.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkEscuelas:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkEscuelas ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresEscuelas.size();i ++ ){
                        marcadoresEscuelas.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresEscuelas.size();i ++ ){
                        marcadoresEscuelas.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkMercado:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkMercado ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresMercado.size();i ++ ){
                        marcadoresMercado.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresMercado.size();i ++ ){
                        marcadoresMercado.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkOGobierno:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkOGobierno ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresOGobierno.size();i ++ ){
                        marcadoresOGobierno.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresOGobierno.size();i ++ ){
                        marcadoresOGobierno.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkRecauderias:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkRecauderias ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresRecauderias.size();i ++ ){
                        marcadoresRecauderias.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresRecauderias.size();i ++ ){
                        marcadoresRecauderias.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
            case R.id.checkIglesias:
                if (checked) {
                    System.out.println( "~~~~~~~~~~~~~~~~~ checkIglesias ~~~~~~~~~~~~~~~~~ " );
                    for (int i = 0; i < marcadoresIglesias.size();i ++ ){
                        marcadoresIglesias.get( i ).setVisible( true );
                    }
                }else {
                    for (int i = 0; i < marcadoresIglesias.size();i ++ ){
                        marcadoresIglesias.get( i ).setVisible( false );
                    }
                    System.out.println(" no esta seleccionado :/ ");
                }
                break;
        }
    }

    public void desaparecer(View view) {
        ivMenu.animate().setDuration( 1000 ).alpha( 0f );
        ivMenu2.animate().setDuration( 1000 ).alpha( 0f );
        lyMenu.animate().setDuration( 1000 ).alpha( 0f );

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ivMenu2.setVisibility( View.INVISIBLE );
                ivMenu.setVisibility( View.INVISIBLE );
                lyMenu.setVisibility( View.INVISIBLE );
            }
        }, 1000);
    }

    public void mostrarMenu(View view) {
        ivMenu.animate().setDuration( 1000 ).alpha( 1f );
        ivMenu.setVisibility( View.VISIBLE );
        ivMenu2.animate().setDuration( 1000 ).alpha( 1f );
        ivMenu2.setVisibility( View.VISIBLE );
        lyMenu.animate().setDuration( 1000 ).alpha( 1f );
        lyMenu.setVisibility( View.VISIBLE );
    }

}