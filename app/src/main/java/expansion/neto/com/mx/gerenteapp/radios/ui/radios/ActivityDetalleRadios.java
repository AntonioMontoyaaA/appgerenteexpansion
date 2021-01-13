package expansion.neto.com.mx.gerenteapp.radios.ui.radios;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

    ProgressDialog progressDialog;
    String radioID;
    private static ActivityDetalleRadios myContext;

    public static ActivityDetalleRadios getMyContext() {
        return myContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initDataBinding();



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
        googleMap.addCircle(new CircleOptions()
        .center(new LatLng(latitud,longitud))
        .radius(500)
                .strokeColor(resource.getColor(R.color.radios))
                .fillColor(resource.getColor(R.color.radios)));
        for (int i = 0; i<listaCompetencia.size();i++){
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
        }

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

}