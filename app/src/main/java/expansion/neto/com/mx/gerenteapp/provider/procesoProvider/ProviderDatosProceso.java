package expansion.neto.com.mx.gerenteapp.provider.procesoProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.Calendar;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Proceso;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderDatosAutorizadas;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.gerenteapp.provider.dashboardProvider.ProviderDatosDashboard.TIPO_APP;

public class ProviderDatosProceso {

    private static ProviderDatosProceso instance;
    Context context;
    String respuesta;
    Proceso callback = null;
    private final String ESTATUS_EN_PROCESO_APP_GERENTE  =   "2";
    private final String TIPO_CONSULTA_MD_POR_AUTORIZAR = "3";

    public ProviderDatosProceso() {}

    public static ProviderDatosProceso getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosProceso();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosProceso(final String semana, final String mes, final ProviderDatosProceso.ConsultaDatosProceso promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Proceso>() {
            @Override
            protected Proceso doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    int areaId = 0;
                    if(Usuario.sharedGet(context).getAreasxpuesto() != null && Usuario.sharedGet(context).getAreasxpuesto().size() > 0) {
                        areaId = Usuario.sharedGet(context).getAreasxpuesto().get(0).getAreaId();
                    }

                    Calendar fecha = Calendar.getInstance();
                    int anio = fecha.get(Calendar.YEAR);

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("estatus", ESTATUS_EN_PROCESO_APP_GERENTE)
                            .add("area", String.valueOf(areaId))
                            .add("mes", String.valueOf(mes))
                            .add("semana", String.valueOf(semana))
                            .add("anio", String.valueOf(anio))
                            .add("tipoconsulta", TIPO_CONSULTA_MD_POR_AUTORIZAR)
                            .add("tipoapp", TIPO_APP)
                            .add("usuarioId", Usuario.sharedGet(context).getUsuario());

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_AUTORIZADAS_LISTA)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    callback = gson.fromJson(jsonInString, Proceso.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return callback;
                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Proceso();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Proceso();
                        callback.setCodigo(403);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Proceso datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }


    public void obtenerDatosProceso(final String statusDocumentos,final String semana, final String mes, final ProviderDatosProceso.ConsultaDatosProceso promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Proceso>() {
            @Override
            protected Proceso doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    int areaId = 0;
                    if(Usuario.sharedGet(context).getAreasxpuesto() != null && Usuario.sharedGet(context).getAreasxpuesto().size() > 0) {
                        areaId = Usuario.sharedGet(context).getAreasxpuesto().get(0).getAreaId();
                    }

                    Calendar fecha = Calendar.getInstance();
                    int anio = fecha.get(Calendar.YEAR);

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("estatus", statusDocumentos)
                            .add("area", String.valueOf(areaId))
                            .add("mes", String.valueOf(mes))
                            .add("semana", String.valueOf(semana))
                            .add("anio", String.valueOf(anio))
                            .add("tipoapp", TIPO_APP)
                            .add("tipoconsulta", TIPO_CONSULTA_MD_POR_AUTORIZAR)
                            .add("usuarioId", Usuario.sharedGet(context).getUsuario());

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_AUTORIZADAS_LISTA)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    callback = gson.fromJson(jsonInString, Proceso.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return callback;
                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Proceso();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Proceso();
                        callback.setCodigo(403);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Proceso datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDatosProceso {
        void resolve(Proceso memorias);
        void reject(Exception e);
    }
}
