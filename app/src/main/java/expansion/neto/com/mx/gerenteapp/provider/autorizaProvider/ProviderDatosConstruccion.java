package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosConstruccion;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosSitio;
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
public class ProviderDatosConstruccion {

    private static ProviderDatosConstruccion instance;
    Context context;
    String respuesta;
    DatosConstruccion callback = null;
    private final String FACTOR_ID_CONSTRUCCION = "5";

    public ProviderDatosConstruccion() {}

    public static ProviderDatosConstruccion getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosConstruccion();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosConstruccion(final String mdId, final String usuarioId, final ConsultaDatosConstruccion promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, DatosConstruccion>() {
            @Override
            protected DatosConstruccion doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuarioId)
                            .add("factorId", FACTOR_ID_CONSTRUCCION);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DATOS_CONSTRUCCION)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    callback = gson.fromJson(jsonInString, DatosConstruccion.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return callback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new DatosConstruccion();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new DatosConstruccion();
                        callback.setCodigo(403);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(DatosConstruccion datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDatosConstruccion {
        void resolve(DatosConstruccion datosSitio);
        void reject(Exception e);
    }

}
