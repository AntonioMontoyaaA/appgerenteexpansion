package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Propietario;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Superficie;
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
public class ProviderDatosSuperficie {

    private static ProviderDatosSuperficie instance;
    Context context;
    String respuesta;
    Superficie callback = null;

    public ProviderDatosSuperficie() {}

    public static ProviderDatosSuperficie getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosSuperficie();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosSuperficie(final String mdId, final String usuarioId, final ConsultaDatosSuperficie promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Superficie>() {
            @Override
            protected Superficie doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DATOS_SUPERFICIE)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    callback = gson.fromJson(jsonInString, Superficie.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return callback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Superficie();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Superficie();
                        callback.setCodigo(403);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Superficie superficie){
                promise.resolve(superficie);
            }
        }).execute();
    }

    public interface ConsultaDatosSuperficie {
        void resolve(Superficie propietario);
        void reject(Exception e);
    }

}
