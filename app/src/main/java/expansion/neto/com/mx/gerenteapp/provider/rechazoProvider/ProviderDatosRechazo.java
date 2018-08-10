package expansion.neto.com.mx.gerenteapp.provider.rechazoProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.Calendar;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Proceso;
import expansion.neto.com.mx.gerenteapp.modelView.rechazoModel.Rechazo;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderDatosRechazo {

    private static ProviderDatosRechazo instance;
    Context context;
    String respuesta;
    Rechazo callback = null;
    private final String ESTATUS_RECHAZADAS_APP_GERENTE  =  "3";
    private final String TIPO_CONSULTA_RECHAZADAS = "3";

    public ProviderDatosRechazo() {}

    public static ProviderDatosRechazo getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosRechazo();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosRechazo(final String semana, final String mes, final ConsultaDatosRechazo promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Rechazo>() {
            @Override
            protected Rechazo doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    int areaId = 0;
                    if(Usuario.sharedGet(context).getAreasxpuesto() != null && Usuario.sharedGet(context).getAreasxpuesto().size() > 0) {
                        areaId = Usuario.sharedGet(context).getAreasxpuesto().get(0).getAreaId();
                    }

                    Calendar fecha = Calendar.getInstance();
                    int anio = fecha.get(Calendar.YEAR);

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("estatus", ESTATUS_RECHAZADAS_APP_GERENTE)
                            .add("area", String.valueOf(areaId))
                            .add("mes", String.valueOf(mes))
                            .add("semana", String.valueOf(semana))
                            .add("anio", String.valueOf(anio))
                            .add("tipoconsulta", TIPO_CONSULTA_RECHAZADAS)
                            .add("usuarioId", Usuario.sharedGet(context).getUsuario());

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_RECHAZADAS_LISTA)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    callback = gson.fromJson(jsonInString, Rechazo.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return callback;
                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Rechazo();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Rechazo();
                        callback.setCodigo(403);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Rechazo memorias){
                promise.resolve(memorias);
            }
        }).execute();
    }

    public interface ConsultaDatosRechazo {
        void resolve(Rechazo memorias);
        void reject(Exception e);
    }
}
