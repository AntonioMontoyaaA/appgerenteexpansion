package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.Calendar;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosConstruccion;
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
public class ProviderDatosAutorizadas {

    private static ProviderDatosAutorizadas instance;
    Context context;
    String respuesta;
    Autorizadas callback = null;
    private final String ESTATUS_POR_AUTORIZAR_APP_GERENTE  =   "1";
    private final String TIPO_CONSULTA_MD_POR_AUTORIZAR = "3";
    private final String SEMANA_0 = "0";

    public ProviderDatosAutorizadas() {}

    public static ProviderDatosAutorizadas getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosAutorizadas();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosAutorizadas(final String semana, final String mes, final ConsultaDatosAutorizadas promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Autorizadas>() {
            @Override
            protected Autorizadas doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    int areaId = 0;
                    if(Usuario.sharedGet(context).getAreasxpuesto() != null && Usuario.sharedGet(context).getAreasxpuesto().size() > 0) {
                        areaId = Usuario.sharedGet(context).getAreasxpuesto().get(0).getAreaId();
                    }

                    Calendar fecha = Calendar.getInstance();
                    int anio = fecha.get(Calendar.YEAR);

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("estatus", ESTATUS_POR_AUTORIZAR_APP_GERENTE)
                            .add("area", String.valueOf(areaId))
                            .add("mes", String.valueOf(mes))
                            .add("semana", String.valueOf(semana))
                            .add("anio", String.valueOf(anio))
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
                    callback = gson.fromJson(jsonInString, Autorizadas.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return callback;
                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Autorizadas();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Autorizadas();
                        callback.setCodigo(403);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Autorizadas datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDatosAutorizadas {
        void resolve(Autorizadas datosSitio);
        void reject(Exception e);
    }

}
