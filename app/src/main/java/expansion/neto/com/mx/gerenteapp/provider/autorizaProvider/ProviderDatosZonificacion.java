package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosConstruccion;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Zonificacion;
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
public class ProviderDatosZonificacion {

    private static ProviderDatosZonificacion instance;
    Context context;
    String respuesta;
    Zonificacion callback = null;

    public ProviderDatosZonificacion() {}

    public static ProviderDatosZonificacion getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosZonificacion();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosConstruccion(final String mdId, final String usuarioId, final ConsultaDatosZonificacion promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Zonificacion>() {
            @Override
            protected Zonificacion doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DATOS_ZONIFICACION)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    callback = gson.fromJson(jsonInString, Zonificacion.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return callback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Zonificacion();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Zonificacion();
                        callback.setCodigo(403);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Zonificacion zonificacion){
                promise.resolve(zonificacion);
            }
        }).execute();
    }

    public interface ConsultaDatosZonificacion {
        void resolve(Zonificacion zonificacion);
        void reject(Exception e);
    }

}
