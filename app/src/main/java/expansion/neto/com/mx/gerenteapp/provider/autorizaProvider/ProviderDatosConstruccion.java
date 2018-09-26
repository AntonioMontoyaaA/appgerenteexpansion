package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosConstruccion;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosConstruccions;
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
    DatosConstruccions callback = null;
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
        (new AsyncTask<Void, Void, DatosConstruccions>() {
            @Override
            protected DatosConstruccions doInBackground(Void... voids) {
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

                    return callback = gson.fromJson(jsonInString, DatosConstruccions.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new DatosConstruccions();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new DatosConstruccions();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(DatosConstruccions datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDatosConstruccion {
        void resolve(DatosConstruccions datosSitio);
        void reject(Exception e);
    }

}
