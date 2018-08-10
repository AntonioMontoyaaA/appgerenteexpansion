package expansion.neto.com.mx.gerenteapp.provider.dashboardProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.Calendar;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.modelView.dashboardModel.Dashboard;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Clase singleton que regresa la validación del usuario así como también regresa el modelo de la persona
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosDashboard {

    private static ProviderDatosDashboard instance;
    Context context;
    String respuesta;
    Dashboard callback = null;
    private final String ESTATUS_DASHBOARD_APP_GERENTE  =   "1";
    private final String TIPO_CONSULTA_DASHBOARD_APP_GERENTE = "1";

    public ProviderDatosDashboard() {}

    public static ProviderDatosDashboard getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosDashboard();
        }
        instance.context = context;
        return instance;
    }
    //    @estatus (regresa todos, madar 1 por default)
//    @area (traer dato del login)
//    @mes (por default mes actual en numero, si la consulta es por semana mandar el mes = 0)
//    @semana (si la consulta es por mes, mandar la semana = 0, sino se dara prioridad a la semana)
//    @anio (enviar el año correspondiente)
//    @tipoconsulta (1 = resumen total)
//    @usuarioId
    public void obtenerDatosAutorizadas(final String semana,
                                        final String mes,
                                        final String usuarioId,
                                        final String area, final ConsultaDatosDashboard promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Dashboard>() {
            @Override
            protected Dashboard doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    Calendar fecha = Calendar.getInstance();
                    int anio = fecha.get(Calendar.YEAR);

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("estatus", ESTATUS_DASHBOARD_APP_GERENTE)
                            .add("area", area)
                            .add("mes", mes)
                            .add("semana", semana)
                            .add("anio", String.valueOf(anio))
                            .add("tipoconsulta", TIPO_CONSULTA_DASHBOARD_APP_GERENTE)
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DASHBOARD)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    callback = gson.fromJson(jsonInString, Dashboard.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return callback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Dashboard();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Dashboard();
                        callback.setCodigo(403);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Dashboard dashboard){
                promise.resolve(dashboard);
            }
        }).execute();
    }

    public interface ConsultaDatosDashboard {
        void resolve(Dashboard dashboard);
        void reject(Exception e);
    }

}
