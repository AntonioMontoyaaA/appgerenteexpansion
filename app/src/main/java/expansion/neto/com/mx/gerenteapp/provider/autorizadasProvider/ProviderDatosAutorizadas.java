package expansion.neto.com.mx.gerenteapp.provider.autorizadasProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.Calendar;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosSitio;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosAutorizadas {

    private static ProviderDatosAutorizadas instance;
    Context context;
    String respuesta;
    Autorizadas usuarioCallback = null;

    public ProviderDatosAutorizadas() {}

    public static ProviderDatosAutorizadas getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosAutorizadas();
        }
        instance.context = context;
        return instance;
    }

    private final String SEMANA_0 = "0";


    public void obtenerDatosAutorizadas(final String vermas, final String mes, final String usuarioId,final String anio, final ConsultaDatosSitio promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Autorizadas>() {
            @Override
            protected Autorizadas doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId)
                            .add("mes", mes)
                            .add("semana", SEMANA_0)
                            .add("vermas", vermas)
                            .add("anio", anio);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DATOS_AUTORIZADAS)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return usuarioCallback = gson.fromJson(jsonInString, Autorizadas.class);

                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new Autorizadas();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new Autorizadas();
                        usuarioCallback.setCodigo(404);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Autorizadas datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDatosSitio {
        void resolve(Autorizadas datosSitio);
        void reject(Exception e);
    }

}
