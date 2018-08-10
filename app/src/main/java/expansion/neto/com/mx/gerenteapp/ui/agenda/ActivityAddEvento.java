package expansion.neto.com.mx.gerenteapp.ui.agenda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityAddEventBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAgenda.FragmentDialogAgendaDia;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAgenda.FragmentInicioAgenda;
import expansion.neto.com.mx.gerenteapp.modelView.Ubicacion;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Empleados;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Eventos;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.GuardarEvento;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderAgendaEmpleados;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderAgendaEventos;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderAgendaTareas;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderCrearEvento;
import expansion.neto.com.mx.gerenteapp.utils.ServicioGPS;
import expansion.neto.com.mx.gerenteapp.utils.Util;

public class ActivityAddEvento extends AppCompatActivity {

    private ActivityAddEventBinding binding;
    private GuardarEvento evento;
    String fechaAgenda;
    String fechaFinProgramada;
    String horaInicio;
    String horaFinal;
    String tareaxId = "4";
    String usuarioId;

    String direccionF = "";
    Ubicacion gpsUbica;
    String fechaAgendaFinal;
    String fechaAgendaF;
    ArrayList<String> nombresEventos;
    HashMap<String, String> nombresEmpleados;

    /******** gps **************/
    ServicioGPS gpsUbicas;
    Boolean todoDia = false;
    Double latitude, longitude, latitudeLast = 0.0, longitudeLast = 0.0;

    private static final DateFormat TWELVE_TF = new SimpleDateFormat("hh:mm a");
    private static final DateFormat TWENTY_FOUR_TF = new SimpleDateFormat("HH:mm");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initDataBinding();

        binding.toolbar.nombreTitulo.setText(getString(R.string.nuevoevento));
        binding.inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentDialogAgendaDia dFragment = new FragmentDialogAgendaDia("inicio");
                dFragment.show(fm, "Dialog Fragment");
            }
        });

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityAddEvento.super.onBackPressed();
            }
        });


        binding.fina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentDialogAgendaDia dFragment = new FragmentDialogAgendaDia("fin");
                dFragment.show(fm, "Dialog Fragment");
            }
        });


        binding.toolbar.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                usuarioId = preferences.getString("usuario", "");

                if(todoDia==true){

                }else{

                    fechaAgenda = preferences.getString("fechaAgenda", "");
                    fechaFinProgramada = preferences.getString("fechaFinProgramada", "");

                    horaInicio = preferences.getString("horaIni", "");
                    horaFinal = preferences.getString("horaFi", "");

                    if(horaInicio.equals("") && horaFinal.equals("")){
                        horaInicio = "8:00";
                        horaFinal = "20:00";
                    }

                    horaInicio = horaInicio.substring(0, 5);
                    horaFinal = horaFinal.substring(0, 5);

                    fechaAgendaFinal = fechaAgenda + " " + horaInicio+":00";
                    fechaAgendaF = fechaFinProgramada + " " + horaFinal+":00";
                }

                String observaciones = binding.descripcion.getText().toString();
                ArrayList<Asignacion> asignacion = new  ArrayList<>();
                int j = 0;

                if(checks!=null){
                    Iterator iterator = checks.keySet().iterator();
                    while (iterator.hasNext()){
                        String key = (String) iterator.next();
                        String value = checks.get(key);

                        if(value.equals("1")){
                            Asignacion asig = new Asignacion();
                            asig.entidadId = Integer.valueOf(key);
                            asignacion.add(j, asig);
                        }
                    }
                    j++;
                    asignacion.size();
                }

                JSONArray array = new JSONArray();

                for (int i =0;i < asignacion.size(); i++ ) {
                    JSONObject object = new JSONObject();
                    try {
                        object.put("entidadId", asignacion.get(i).entidadId);
                        array.put(object);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                evento = new GuardarEvento(usuarioId,
                        tareaxId,
                        fechaAgendaFinal,
                        fechaAgendaF,
                        observaciones,
                        direccionF,
                        String.valueOf(gpsUbica.lat),
                        String.valueOf(gpsUbica.lng),
                        "3",
                        array.toString()
                );

                ProviderCrearEvento.getInstance(getApplicationContext()).guardarEvento(evento, new ProviderCrearEvento.InterfaceCrearEvento() {
                    @Override
                    public void resolve(Codigos codigo) {
                        if(codigo!=null){
                            if(codigo.getCodigo()==200){
                                Toast.makeText(getApplicationContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                                Intent main = new Intent(getApplicationContext(), FragmentInicioAgenda.class);
                                startActivity(main);
                            }else{
                                Toast.makeText(getApplicationContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void reject(Exception e) {

                    }
                });
            }
        });
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_event);
        final SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuarioId = preferences.getString("usuario", "");

        binding.toolbar.guardar.setVisibility(View.VISIBLE);

        final Date hoy = Calendar.getInstance().getTime();
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "hh:mm");
        final String hora = dateFormat.format(cal1.getTime());

        gpsUbica = gps();
        setDireccion(gpsUbica);

        String fecha = preferences.getString("fechaSeleccionada", "");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("fechaAgenda", fecha);
        editor.putString("fechaFinProgramada", fecha);
        editor.putString("horaIni", "08:00");
        editor.putString("horaFi", "20:00");
        editor.apply();


        binding.inicioFecha.setText(Util.getFechaFormat(hoy)+" "+hora);
        binding.finFecha.setText(Util.getFechaFormat(hoy)+" "+hora);

        ProviderAgendaTareas.getInstance(this).obtenerEventos(usuarioId, new ProviderAgendaTareas.InterfaceObtieneEventos() {
            @Override
            public void resolve(Eventos eventos) {
                if(eventos!=null && eventos.getEventos()!=null){
                    nombresEventos = new ArrayList<>();
                    if(eventos.getEventos().size()>0){
                        for(int i=0;i<eventos.getEventos().size();i++){
                            nombresEventos.add(eventos.getEventos().get(i).getNombre());
                        }
                        binding.spinnerEventos.setItems(nombresEventos);

                        binding.spinnerEventos.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                                switch (position){
                                    case 0:
                                        tareaxId = "4";
                                        break;
                                    case 1:
                                        tareaxId = "3";
                                        break;
                                    case 2:
                                        tareaxId = "2";
                                        break;
                                }
                            }
                        });



                        if(binding.buscarNombre.isChecked()){

                            String fechaSeleccionada = preferences.getString("fechaSeleccionada", "");
                            if(!fechaSeleccionada.equals("")){
                                Date date = StringToDate(fechaSeleccionada);

                                binding.inicioFecha.setText(Util.getFechaFormat(date)+" ~ 08:00");
                                binding.finFecha.setText(Util.getFechaFormat(date)+" ~ 20:00");
                                binding.inicioFecha.setEnabled(false);
                                binding.finFecha.setEnabled(false);

                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                String formattedDate = df.format(date);

                                fechaAgendaFinal = formattedDate + " ~ 08:00:00";
                                fechaAgendaF = formattedDate + " ~ 20:00:00";
                                todoDia = true;
                            }else{
                                binding.inicioFecha.setText(Util.getFechaFormat(hoy)+" ~ 08:00");
                                binding.finFecha.setText(Util.getFechaFormat(hoy)+" ~ 20:00");
                                binding.inicioFecha.setEnabled(false);
                                binding.finFecha.setEnabled(false);

                                Date c = Calendar.getInstance().getTime();

                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                String formattedDate = df.format(c);

                                fechaAgendaFinal = formattedDate + " ~ 08:00:00";
                                fechaAgendaF = formattedDate + " ~ 20:00:00";
                                todoDia = true;
                            }


                        }else{

                            String fechaSeleccionada = preferences.getString("fechaSeleccionada", "");

                            if(fechaSeleccionada.equals("")){

                            }else{
                                Date date = StringToDate(fechaSeleccionada);

                                binding.inicioFecha.setText(Util.getFechaFormat(date)+" ~ 08:00");
                                binding.finFecha.setText(Util.getFechaFormat(date)+" ~ 20:00");

                            }


                        }

                        binding.buscarNombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){

                                    binding.inicioFecha.setText(Util.getFechaFormat(hoy)+" ~ 08:00");
                                    binding.finFecha.setText(Util.getFechaFormat(hoy)+" ~ 20:00");
                                    binding.inicioFecha.setEnabled(false);
                                    binding.finFecha.setEnabled(false);

                                    Date c = Calendar.getInstance().getTime();

                                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                    String formattedDate = df.format(c);

                                    fechaAgendaFinal = formattedDate + " 08:00:00";
                                    fechaAgendaF = formattedDate + " 20:00:00";
                                    todoDia = true;

                                }else{
                                    binding.inicioFecha.setEnabled(true);
                                    binding.finFecha.setEnabled(true);
                                    todoDia = false;
                                }
                            }
                        });


                    }
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });

        ProviderAgendaEmpleados.getInstance(this).obtenerEmpleados(usuarioId, new ProviderAgendaEmpleados.InterfaceObtieneEmpleados() {
            @Override
            public void resolve(Empleados empleados) {
                if(empleados!=null){
                    nombresEmpleados = new HashMap<>();
                    if(empleados.getEmpleados() != null && empleados.getEmpleados().getEXPANSION()!=null){

                        for(int j = 0;j<20;j++){
                            for(int i=0;i<empleados.getEmpleados().getEXPANSION().getGERENTE().size();i++){
                                nombresEmpleados.put(empleados.getEmpleados().getEXPANSION().getGERENTE().get(i).getNombre(),
                                        empleados.getEmpleados().getEXPANSION().getGERENTE().get(i).getEmpleadoId());
                            }
                            for(int i=0;i<empleados.getEmpleados().getEXPANSION().getJEFE().size();i++){
                                nombresEmpleados.put(empleados.getEmpleados().getEXPANSION().getJEFE().get(i).getNombre(),
                                        empleados.getEmpleados().getEXPANSION().getJEFE().get(i).getEmpleadoId());
                            }

                        }

                        generarParticipantes(binding, nombresEmpleados);

                    }
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    public static Date StringToDate(String dtStart){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(dtStart);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setFecha(String fecha, String tipo, String fechaFin){
        if(tipo.equals("inicio")){
            binding.inicioFecha.setText(fecha);
            binding.finFecha.setText(fechaFin);

        }else{
            binding.finFecha.setText(fecha);
        }
    }

    public void setDireccion(Ubicacion gpsUbica){
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(gpsUbica.lat, gpsUbica.lng, 1);
            direccionF = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Ubicacion gps() {
        Ubicacion ubicacion;
        latitudeLast = latitude;
        longitudeLast = longitude;
        gpsUbicas = new ServicioGPS(this);
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

    HashMap<String, String> checks;
    HashMap<String, String> checkParticipantes;

    public void generarParticipantes(ActivityAddEventBinding binding, final HashMap<String, String> participantes){


        Iterator iterator = participantes.keySet().iterator();

        Resources resource = getResources();
        binding.factores.removeAllViews();
        TableRow rowPlomo = new TableRow(this);
        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
        binding.factores.addView(rowPlomo);

        checks = new HashMap<String, String>();
        checkParticipantes = new HashMap<String, String>();


        ColorStateList  colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked}, // unchecked
                        new int[]{android.R.attr.state_checked} , // checked
                },
                new int[]{
                        Color.parseColor("#162341"),
                        Color.parseColor("#213054"),
                }
        );

        int itera = 0;

        while(iterator.hasNext()) {

            String key = (String) iterator.next();
            final String value = participantes.get(key);

            TableRow tbrow = new TableRow(this);
            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));

            final CheckBox check = new CheckBox(this);
            check.setTextColor(resource.getColor(R.color.azul));
            check.setGravity(Gravity.CENTER);

            CompoundButtonCompat.setButtonTintList(check,colorStateList);

            tbrow.addView(check);

            final boolean checked = check.isChecked();

            if (checked) {
                checks.put(value, "1");
              //  checkParticipantes.put(value, "1");
            } else {
                checks.put(value, "0");
                //checkParticipantes.put(value, "0");
            }

            final int finalI = itera;

            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        checks.put(value, "1");
                    } else {
                        checks.put(value, "0");
                    }

                }
            });

            TextView t1v1 = new TextView(this);
            t1v1.setText("   "+key);
            t1v1.setTextColor(resource.getColor(R.color.azul));
            t1v1.setGravity(Gravity.CENTER_VERTICAL);
            tbrow.addView(t1v1);
            binding.factores.addView(tbrow);
            itera++;
        }

    }

    public class Asignacion{
        int entidadId;
    }

}


