package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
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
public class ProviderDatosSitio {

    private static ProviderDatosSitio instance;
    Context context;
    String respuesta;
    DatosSitio usuarioCallback = null;

    public ProviderDatosSitio() {}

    public static ProviderDatosSitio getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosSitio();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosSitio(final String mdId, final String usuarioId, final ConsultaDatosSitio promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, DatosSitio>() {
            @Override
            protected DatosSitio doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DATOS_SITIO)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    usuarioCallback = gson.fromJson(jsonInString, DatosSitio.class);
                    if(usuarioCallback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return usuarioCallback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new DatosSitio();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new DatosSitio();
                        usuarioCallback.setCodigo(403);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(DatosSitio datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDatosSitio {
        void resolve(DatosSitio datosSitio);
        void reject(Exception e);
    }

}
