package expansion.neto.com.mx.gerenteapp.modelView.loginModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Toast;
import android.Manifest;

import com.google.gson.Gson;

import java.util.ArrayList;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityLoginBinding;
import expansion.neto.com.mx.gerenteapp.provider.loginProvider.ExpansionGerenteProviderLogin;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityLogin;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;
import expansion.neto.com.mx.gerenteapp.utils.Util;

import static expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityLogin.canGetLocation;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class Usuario {

    String usuario;
    String contra;

    private static final int MODULO_DASHBOARD = 7;
    private static final int MODULO_POR_AUTORIZAR_ID = 7;
    private static final int MODULO_EN_PROCESO_ID = 3;
    private static final int MODULO_AUTORIZADAS_ID = 5;
    private static final int MODULO_RECHAZADAS_ID = 4;
    private static final int MODULO_AGENDA_ID = 6;
    private static final int MODULO_COLABORADORES_ID = 8;

    Context context;
    ActivityLoginBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public Usuario(String usuario, String contra, Context context,
                   ActivityLoginBinding binding) {
        this.usuario = usuario;
        this.contra = contra;
        this.context = context;
        this.binding = binding;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }


    /**
     * Método que contiene el evento para el boton entrar, verifica si existe en la BD de tiendas neto y crea una variable de sesión
     * @param usuario
     * @param contra
     */
    public void onClickEntrar(String usuario, String contra) {
        blockUI();


        boolean act = canGetLocation(context);
        if(act){
            if(usuario.length() > 0 && contra.length() > 0){
                binding.entrar.setAlpha(0.45f);
                binding.entrar.setEnabled(false);
                Usuario user = new Usuario(usuario, contra, context, binding);
                compruebaUsuario(user);
            }else{
                binding.entrar.setEnabled(true);
                binding.entrar.setAlpha(1f);
                Snackbar snackbar = Snackbar.make(binding.container,
                        Html.fromHtml("<b><font color=\"#254581\">" +
                                context.getString(R.string.sizeContra) +
                                "</font></b>"), Snackbar.LENGTH_SHORT);
                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(context.getResources().getColor(R.color.snackBar)); // snackbar background color
                snackbar.show();
                unblockUI();
            }
        }else{
            unblockUI();
            Toast.makeText(context, "Por favor activa tu gps para poder accesar", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }

    ArrayList<Permiso> permisos;
    /**
     * Método que regresa el objeto usuario según se encuentre en la BD, también existe condición para saber si el usuario tiene acceso
     * @param usuario
     */
    public void compruebaUsuario(final Usuario usuario) {
        ExpansionGerenteProviderLogin.getInstance(context).compruebaLogin(usuario,
                new ExpansionGerenteProviderLogin.ConsultaUsuario() {
                    @Override
                    public void resolve(UsuarioLogin usuarioLogin) {
                        if(usuarioLogin!=null){
                            if(usuarioLogin.getCodigo()==200){

                                if(usuarioLogin.getPerfil().getPuestoId().equals("2")){

                                    Snackbar snackbar = Snackbar.make(binding.container,
                                            Html.fromHtml("<b><font color=\"#254581\">" +
                                                    "El Jefe de Expansión no tiene permisos" +
                                                    "</font></b>"), Snackbar.LENGTH_SHORT);

                                    View snackBarView = snackbar.getView();
                                    snackBarView.setBackgroundColor(context.getResources().getColor(R.color.snackBar));
                                    snackbar.show();
                                    binding.entrar.setAlpha(1f);
                                    binding.entrar.setEnabled(true);
                                    unblockUI();

                                }else{
                                    permisos = new ArrayList<>();
                                    for(int i=0;i<usuarioLogin.getPerfil().getPerfilesxusuario().get(0).getPermisos().size();i++){
                                        permisos.add(usuarioLogin.getPerfil().getPerfilesxusuario().get(0).getPermisos().get(i));
                                    }
                                    String puestoId = usuarioLogin.getPerfil().getPuestoId();
                                    int areaId = usuarioLogin.getPerfil().getAreasxpuesto().get(0).getAreaId();
                                    preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    Gson gson = new Gson();
                                    String json = gson.toJson(permisos);
                                    editor.putString("permisos", json);
                                    editor.putString("puestoId", puestoId);
                                    editor.putString("areaId", areaId+"");
                                    editor.commit();


                                    Intent main = new Intent(context, ActivityMain.class);
                                    context.startActivity(main);
                                    binding.entrar.setAlpha(1f);
                                    usuarioLogin.getPerfil().setImei(Util.getImei(context));
                                    sharedSave(context, usuario,
                                            usuarioLogin);
                                    unblockUI();
                                }

                            }else if(usuarioLogin.getCodigo()==1){
                                Snackbar snackbar = Snackbar.make(binding.container,
                                        Html.fromHtml("<b><font color=\"#254581\">" +
                                                context.getString(R.string.conexionInternet) +
                                                "</font></b>"), Snackbar.LENGTH_SHORT);

                                View snackBarView = snackbar.getView();
                                snackBarView.setBackgroundColor(context.getResources().getColor(R.color.snackBar));
                                snackbar.show();
                                binding.entrar.setAlpha(1f);
                                binding.entrar.setEnabled(true);
                                unblockUI();
                            }else{
                                Snackbar snackbar = Snackbar.make(binding.container,
                                        Html.fromHtml("<b><font color=\"#254581\">" +
                                                usuarioLogin.getMensaje() +
                                                "</font></b>"), Snackbar.LENGTH_SHORT);

                                View snackBarView = snackbar.getView();
                                snackBarView.setBackgroundColor(context.getResources().getColor(R.color.snackBar));
                                snackbar.show();
                                binding.entrar.setAlpha(1f);
                                binding.entrar.setEnabled(true);
                                unblockUI();
                            }

                        }else{
                            Snackbar snackbar = Snackbar.make(binding.container,
                                    Html.fromHtml("<b><font color=\"#254581\">" +
                                            usuarioLogin.getMensaje() +
                                            "</font></b>"), Snackbar.LENGTH_SHORT);

                            View snackBarView = snackbar.getView();
                            snackBarView.setBackgroundColor(context.getResources().getColor(R.color.snackBar));
                            snackbar.show();
                            unblockUI();
                        }
                    }
                    @Override
                    public void reject(Exception e) {}
                });
    }

    private void blockUI(){
        Util.addProgressBar(context, binding.container,binding.container.getChildCount()-1 );
    }

    private void unblockUI(){
        binding.container.removeViewAt(binding.container.getChildCount()-2);
    }

    public static void sharedSave(Context context, Usuario usuario, UsuarioLogin usuarioLogin){


        if(usuarioLogin.getPerfil().getPlanSemana()==null){
            usuarioLogin.getPerfil().setPlanSemana(0);
        }

        if(usuarioLogin.getPerfil().getRealSemana()==null){
            usuarioLogin.getPerfil().setRealSemana(0);
        }

        if(usuarioLogin.getPerfil().getRealMes()==null){
            usuarioLogin.getPerfil().setRealMes(0);
        }

        if(usuarioLogin.getPerfil().getPlanMes()==null){
            usuarioLogin.getPerfil().setPlanMes(0);
        }


        SharedPreferences preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario", usuario.getUsuario());
        editor.putString("contra", usuario.getContra());
        editor.putString("imei", usuarioLogin.getPerfil().getImei());
        editor.putString("numero", usuario.getContra());

        editor.putInt("realMes", usuarioLogin.getPerfil().getRealMes());
        editor.putInt("planMes", usuarioLogin.getPerfil().getPlanMes());

        editor.putInt("realSemana", usuarioLogin.getPerfil().getRealSemana());
        editor.putInt("planSemana", usuarioLogin.getPerfil().getPlanSemana());

        editor.putString("nombreCompleto",
                usuarioLogin.getPerfil().getNombre()+" "+
                        usuarioLogin.getPerfil().getApellidoP());
        editor.putString("telefono", usuarioLogin.getPerfil().getTelefono());
        editor.putString("correo", usuarioLogin.getPerfil().getCorreo());

        editor.apply();

    }

    public static UsuarioLogin.Perfil sharedGet(Context context){

        UsuarioLogin.Perfil usuario = new UsuarioLogin.Perfil();
        SharedPreferences preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        usuario.setNombre(preferences.getString("nombreCompleto", ""));
        usuario.setUsuario(preferences.getString("usuario", ""));
        usuario.setContra(preferences.getString("contra", ""));
        usuario.setImei(preferences.getString("imei", ""));
        usuario.setTelefono(preferences.getString("numero", ""));
        usuario.setRealMes(preferences.getInt("realMes", 0));
        usuario.setRealSemana(preferences.getInt("realSemana", 0));
        usuario.setPlanMes(preferences.getInt("planMes", 0));
        usuario.setPlanSemana(preferences.getInt("planSemana", 0));
        usuario.setNombre(preferences.getString("nombreCompleto", ""));
        usuario.setCorreo(preferences.getString("correo", ""));
        return usuario;
        
    }

}
