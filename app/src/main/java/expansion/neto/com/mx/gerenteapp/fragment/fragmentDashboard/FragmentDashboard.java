package expansion.neto.com.mx.gerenteapp.fragment.fragmentDashboard;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.text.style.AlignmentSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eralp.circleprogressview.ProgressAnimationListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.cron.ReminderUtilities;
import expansion.neto.com.mx.gerenteapp.cron.ReminderUtilitiesJob;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentDashboardBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAgenda.FragmentInicioAgenda;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FragmentInicioAutoriza;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutorizadas.FragmentInicioAutorizadas;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentDocumentos.FragmentInicioDocumentos;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentProceso.FragmentInicioProceso;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentRechazo.FragmentInicioRechazadas;
import expansion.neto.com.mx.gerenteapp.modelView.dashboardModel.Dashboard;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Permiso;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.UsuarioLogin;
import expansion.neto.com.mx.gerenteapp.provider.dashboardProvider.ProviderDatosDashboard;
import expansion.neto.com.mx.gerenteapp.ui.autoriza.ActivityAutorizar;
import expansion.neto.com.mx.gerenteapp.ui.autorizadas.ActivityAutorizadas;
import expansion.neto.com.mx.gerenteapp.utils.Util;

public class FragmentDashboard extends Fragment {

    FragmentDashboardBinding binding;
    String mes;
    String semana;
    UsuarioLogin.Perfil perfil = new UsuarioLogin.Perfil();

    private static final int MODULO_DASHBOARD = 7;
    private static final int MODULO_POR_AUTORIZAR_ID = 7;
    private static final int MODULO_EN_PROCESO_ID = 3;
    private static final int MODULO_AUTORIZADAS_ID = 5;
    private static final int MODULO_RECHAZADAS_ID = 4;
    private static final int MODULO_AGENDA_ID = 6;
    private static final int MODULO_COLABORADORES_ID = 8;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard,container,false);
        View v = binding.getRoot();

        Calendar fecha = Calendar.getInstance();
        final int meses = fecha.get(Calendar.MONTH) + 1;
        getDatos(String.valueOf(meses), "0");

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorDashboard = preferences.edit();
        editorDashboard.putString("mesConsulta", String.valueOf(meses));
        editorDashboard.putString("semanaConsulta", "0");
        editorDashboard.apply();
        binding.nombreJefe.setVisibility(View.GONE);

        getMesSemana();
        String mese = getMes(meses);
        binding.setSemana("Semana "+semana);
        binding.setMes(mese);

        binding.derMes.setEnabled(false);
        binding.derSemana.setEnabled(false);
        
        Date hoy = Calendar.getInstance().getTime();
        String upperString = Util.getFechaDay(hoy).substring(0,1).toUpperCase() + Util.getFechaDay(hoy).substring(1);
        binding.dia.setText("Hoy " + upperString + ", " + Util.getFechaFormat(hoy));

        binding.autorizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                startActivity(main);
            }
        });

        binding.proceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                startActivity(main);
            }
        });

        binding.rechazadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                startActivity(main);
            }
        });

        binding.autorizadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getContext(), ActivityAutorizadas.class);
                startActivity(main);
            }
        });

        binding.agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), FragmentInicioAgenda.class);
                startActivity(main);

            }
        });

        binding.documentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), FragmentInicioDocumentos.class);
                startActivity(main);

            }
        });

        binding.izqSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.derSemana.setEnabled(true);
                binding.derSemana.setAlpha(1.0f);
                if(semanaRestaInt!=0){
                    binding.izqSemana.setEnabled(true);
                    if(bandera==0){
                        semanaRestaInt = Integer.valueOf(getMesSemana())-1;
                        semanaResta = String.valueOf(semanaRestaInt);
                        getDatos("0", semanaResta);
                        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editorDashboard = preferences.edit();
                        editorDashboard.putString("mesConsulta", "0");
                        editorDashboard.putString("semanaConsulta", String.valueOf(semanaResta));
                        editorDashboard.apply();
                        binding.setSemana("Semana "+semanaResta);
                        bandera = 1;
                    }else{
                        semanaRestaInt = (semanaRestaInt)-1;
                        if(semanaRestaInt==0){

                        }else{
                            getDatos("0", String.valueOf(semanaRestaInt));
                            SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editorDashboard = preferences.edit();
                            editorDashboard.putString("mesConsulta", "0");
                            editorDashboard.putString("semanaConsulta", String.valueOf(semanaRestaInt));
                            editorDashboard.apply();
                            binding.setSemana("Semana "+String.valueOf(semanaRestaInt));
                        }
                    }
                }else{
                    binding.izqSemana.setEnabled(false);
                }
            }
        });

        binding.derSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.izqSemana.setEnabled(true);
                semanaRestaInt = (semanaRestaInt)+1;
                int semanaActual = Integer.parseInt(getMesSemana());
                if(semanaRestaInt==semanaActual){
                    getDatos("0", String.valueOf(semanaRestaInt));
                    SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorDashboard = preferences.edit();
                    editorDashboard.putString("mesConsulta", "0");
                    editorDashboard.putString("semanaConsulta", String.valueOf(semanaRestaInt));
                    editorDashboard.apply();
                    binding.setSemana("Semana "+String.valueOf(semanaRestaInt));
                    binding.derSemana.setAlpha(0.0f);
                    binding.derSemana.setEnabled(false);

                }else {
                    getDatos("0", String.valueOf(semanaRestaInt));
                    SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorDashboard = preferences.edit();
                    editorDashboard.putString("mesConsulta", "0");
                    editorDashboard.putString("semanaConsulta", String.valueOf(semanaRestaInt));
                    editorDashboard.apply();
                    binding.setSemana("Semana "+String.valueOf(semanaRestaInt));
                    binding.derSemana.setAlpha(1.0f);
                }
            }
        });



        binding.izqMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.derMes.setEnabled(true);
                binding.derMes.setAlpha(1.0f);
                if(mesRestaInt!=0){
                    binding.izqMes.setEnabled(true);
                    if(banderaMes==0){
                        Calendar fecha = Calendar.getInstance();
                        final int meses = fecha.get(Calendar.MONTH) + 1;
                        mesRestaInt = meses-1;
                        mesResta = String.valueOf(mesRestaInt);
                        getDatos(mesResta, "0");
                        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editorDashboard = preferences.edit();
                        editorDashboard.putString("mesConsulta", String.valueOf(mesResta));
                        editorDashboard.putString("semanaConsulta", "0");
                        editorDashboard.apply();
                        String nombreMes = getMes(mesRestaInt);
                        binding.setMes(nombreMes);
                        banderaMes = 1;
                    }else{
                        mesRestaInt = (mesRestaInt)-1;
                        if(mesRestaInt==0){

                        }else{
                            getDatos(String.valueOf(mesRestaInt), "0");
                            SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editorDashboard = preferences.edit();
                            editorDashboard.putString("mesConsulta", String.valueOf(mesRestaInt));
                            editorDashboard.putString("semanaConsulta", "0");
                            editorDashboard.apply();
                            String nombreMes = getMes(mesRestaInt);
                            binding.setMes(nombreMes);
                        }
                    }
                }else{
                    binding.izqMes.setEnabled(false);
                }
            }
        });

        binding.derMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.izqMes.setEnabled(true);
                mesRestaInt = (mesRestaInt)+1;

                Calendar fecha = Calendar.getInstance();
                int mesActual = fecha.get(Calendar.MONTH) + 1;
                if(mesRestaInt==mesActual){
                    getDatos(String.valueOf(mesRestaInt), "0");
                    SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorDashboard = preferences.edit();
                    editorDashboard.putString("mesConsulta", String.valueOf(mesRestaInt));
                    editorDashboard.putString("semanaConsulta", "0");
                    editorDashboard.apply();
                    String nombreMes = getMes(mesRestaInt);
                    binding.setMes(nombreMes);
                    binding.derMes.setAlpha(0.0f);
                    binding.derMes.setEnabled(false);

                }else {
                    getDatos(String.valueOf(mesRestaInt), "0");
                    SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorDashboard = preferences.edit();
                    editorDashboard.putString("mesConsulta", String.valueOf(mesRestaInt));
                    editorDashboard.putString("semanaConsulta", "0");
                    editorDashboard.apply();
                    String mesNombre = getMes(mesRestaInt);
                    binding.setMes(mesNombre);
                    binding.derMes.setAlpha(1.0f);
                    binding.derMes.setEnabled(true);
                }
            }
        });

        ReminderUtilities.scheduleCronReminder(getContext());
        ReminderUtilitiesJob.scheduleCronReminder(getContext());

        return v;
    }

    int bandera = 0;
    int semanaRestaInt = 1;
    String semanaResta;

    int banderaMes = 0;
    int mesRestaInt = 1;
    String mesResta;

    public String getMesSemana(){
        Date date = new Date();
        mes  = (String)
                DateFormat.format("MMMM",  date); // Jun
        mes = mes.substring(0,1).toUpperCase() + mes.substring(1).toLowerCase();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, month);
        now.set(Calendar.DATE, day);
        semana = String.valueOf(now.get(Calendar.WEEK_OF_YEAR));
        return semana;
    }

    public String getMes(int mesNum){

        if(mesNum==1){
            mes = "Enero";
        }if(mesNum==2){
            mes = "Febrero";
        }if(mesNum==3){
            mes = "Marzo";
        }if(mesNum==4){
            mes = "Abril";
        }if(mesNum==5){
            mes = "Mayo";
        }if(mesNum==6){
            mes = "Junio";
        }if(mesNum==7){
            mes = "Julio";
        }if(mesNum==8){
            mes = "Agosto";
        }if(mesNum==9){
            mes = "Septiembre";
        }if(mesNum==10){
            mes = "Octubre";
        }if(mesNum==11){
            mes = "Noviembre";
        }if(mesNum==12){
            mes = "Diciembre";
        }
        return mes;
    }

    public void setTacometros(int planMes, int realMes, int planSemana, int realSemana){

        binding.tacometroMes.setTextEnabled(false);
        binding.tacometroMes.setInterpolator(new AccelerateDecelerateInterpolator());
        binding.tacometroMes.setStartAngle(270);
        if(planMes != 0) {
            binding.tacometroMes.setProgressWithAnimation((100/planMes*realMes), 2000);
        } else {
            binding.tacometroMes.setProgressWithAnimation(0, 2000);
        }
        binding.tacometroMes.addAnimationListener(new ProgressAnimationListener() {
            @Override
            public void onValueChanged(float value) { }

            @Override
            public void onAnimationEnd() { }
        });

        binding.tacometroSemana.setTextEnabled(false);
        binding.tacometroSemana.setInterpolator(new AccelerateDecelerateInterpolator());
        binding.tacometroSemana.setStartAngle(270);
        if(planSemana != 0) {
            binding.tacometroSemana.setProgressWithAnimation((100/planSemana*realSemana), 2000);
        } else {
            binding.tacometroSemana.setProgressWithAnimation(0, 2000);
        }

        binding.tacometroSemana.addAnimationListener(new ProgressAnimationListener() {
            @Override
            public void onValueChanged(float value) {

            }

            @Override
            public void onAnimationEnd() {
                //Toast.makeText(getContext(), "Animation of CircleProgressView done", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDatos(String buscaMes, String semanas){
        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuarioId = preferences.getString("usuario","");
        String area = preferences.getString("areaxpuesto","");
        //blockUI();

        Gson gson = new Gson();
        String json = preferences.getString("permisos", null);
        Type type = new TypeToken<ArrayList<Permiso>>() {}.getType();
        ArrayList<Permiso> permisos = gson.fromJson(json, type);

        getPermisos(permisos, binding);

        ProviderDatosDashboard.getInstance(getContext()).obtenerDatosAutorizadas(semanas, buscaMes, usuarioId,
                area, new ProviderDatosDashboard.ConsultaDatosDashboard() {
            @Override
            public void resolve(Dashboard dashboard) {

                if(dashboard!=null){
                    if(dashboard.getCodigo()==200 && dashboard!=null){
                        for(int i=0;i<dashboard.getTotales().size();i++){

                            if(dashboard.getTotales().get(i).getEstatusid()==1) {
                                String cadena = "";
                                if(dashboard.getTotales().get(i).getTotal() < 10) {
                                    cadena = dashboard.getTotales().get(i).getTotal() + "   ";
                                } else if(dashboard.getTotales().get(i).getTotal() < 100) {
                                    cadena = dashboard.getTotales().get(i).getTotal() + "  ";
                                } else {
                                    cadena = String.valueOf(dashboard.getTotales().get(i).getTotal());
                                }
                                binding.totalAutoriza.setText(cadena);
                            }
                            if(dashboard.getTotales().get(i).getEstatusid()==2){
                                binding.totalProceso.setText(dashboard.getTotales().get(i).getTotal()+"");
                            }
                            if(dashboard.getTotales().get(i).getEstatusid()==3){
                                binding.totalRechazados.setText(dashboard.getTotales().get(i).getTotal()+"");
                            }

                            if(dashboard.getTotales().get(i).getEstatusid()==4){
                                binding.totalAutorizadas.setText(dashboard.getTotales().get(i).getTotal()+"");
                            }

                            if(dashboard.getTotales().get(i).getEstatusid()==5){
                                binding.totalDocumentos.setText(dashboard.getTotales().get(i).getTotal()+"");
                            }

                        }

                        int planMes;
                        int realMes;
                        int planSem;
                        int realSem;

                        if(dashboard.getProductividad().get(0).getPlanMes()==null){
                            planMes = 0;
                        }else{
                            planMes = dashboard.getProductividad().get(0).getPlanMes();
                        }

                        if(dashboard.getProductividad().get(0).getRealMes()==null){
                            realMes = 0;
                        }else{
                            realMes = dashboard.getProductividad().get(0).getRealMes();
                        }

                        if(dashboard.getProductividad().get(0).getPlanSem()==null){
                            planSem = 0;
                        }else{
                            planSem = dashboard.getProductividad().get(0).getPlanSem();
                        }

                        if(dashboard.getProductividad().get(0).getRealSem()==null){
                            realSem = 0;
                        }else{
                            realSem = dashboard.getProductividad().get(0).getRealSem();
                        }

                        setTacometros(planMes,
                                realMes,
                                planSem,
                                realSem);

                        String mese = getMes(dashboard.getProductividad().get(0).getMes());
                        binding.setMes(mese);

                        binding.setSemana("Semana "+ dashboard.getProductividad().get(0).getSemana());

                        semanaRestaInt =  dashboard.getProductividad().get(0).getSemana();
                        mesRestaInt = dashboard.getProductividad().get(0).getMes();


                        Calendar fecha = Calendar.getInstance();
                        final int meses = fecha.get(Calendar.MONTH) + 1;

                        if(meses==mesRestaInt){
                            binding.derMes.setEnabled(false);
                            binding.derMes.setAlpha(0.0f);
                        }else{
                            binding.derMes.setEnabled(true);
                            binding.derMes.setAlpha(1.0f);
                        }

                        if(Integer.valueOf(getMesSemana())==semanaRestaInt){
                            binding.derSemana.setEnabled(false);
                            binding.derSemana.setAlpha(0.0f);
                        }else{
                            binding.derSemana.setEnabled(true);
                            binding.derSemana.setAlpha(1.0f);
                        }

                        perfil = Usuario.sharedGet(getContext());
                        perfil.setPlanMes(planMes);
                        perfil.setRealMes(realMes);
                        perfil.setPlanSemana(planSem);
                        perfil.setRealSemana(realSem);
                        binding.setPerfil(perfil);
                        binding.nombreJefe.setVisibility(View.VISIBLE);
                    }else{
                        binding.nombreJefe.setVisibility(View.GONE);

                    }
                    //unblockUI();
                }else{
                    Toast.makeText(getContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    private void blockUI(){
        Util.addProgressBar(getContext(), binding.container,binding.container.getChildCount()-0 );
    }

    private void unblockUI(){
        binding.container.removeViewAt(binding.container.getChildCount()-1);
    }

    ArrayList<Permiso> permisosGerente;
    public void getPermisos(ArrayList<Permiso> permiso, FragmentDashboardBinding binding) {
        if(permiso!=null){

            permisosGerente = new ArrayList<>();
            for(int i=0;i<permiso.size();i++){
                if(permiso.get(i).getFimoduloid() == MODULO_DASHBOARD) {
                    permisosGerente.add(permiso.get(i));
                }
            }

            for(int j=0;j< permisosGerente.size();j++){
                int valor = permisosGerente.get(j).getFisubmodulo();

                switch (valor){
                    case MODULO_POR_AUTORIZAR_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            binding.autorizar.setVisibility(View.VISIBLE);
                        }else{
                            binding.autorizar.setVisibility(View.GONE);
                        }
                        break;
                    case MODULO_EN_PROCESO_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            binding.proceso.setVisibility(View.VISIBLE);
                        }else{
                            binding.proceso.setVisibility(View.GONE);
                        }
                        break;
                    case MODULO_AUTORIZADAS_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            binding.autorizadas.setVisibility(View.VISIBLE);
                        }else{
                            binding.autorizadas.setVisibility(View.GONE);
                        }
                        break;
                    case MODULO_RECHAZADAS_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            binding.rechazadas.setVisibility(View.VISIBLE);
                        }else{
                            binding.rechazadas.setVisibility(View.GONE);
                        }
                        break;
                    case MODULO_AGENDA_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            binding.agenda.setVisibility(View.VISIBLE);
                        }else{
                            binding.agenda.setVisibility(View.GONE);
                        }
                        break;
                    case MODULO_COLABORADORES_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            binding.colaboradores.setVisibility(View.VISIBLE);
                        }else{
                            binding.colaboradores.setVisibility(View.GONE);
                        }
                        break;
                }
            }
        }
    }




}

