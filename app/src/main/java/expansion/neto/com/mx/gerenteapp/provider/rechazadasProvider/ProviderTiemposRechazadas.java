package expansion.neto.com.mx.gerenteapp.provider.rechazadasProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.TiemposProceso;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderTiemposRechazadas {

    private static ProviderTiemposRechazadas instance;
    Context context;
    String respuesta;
    TiemposProceso callback = null;

    public ProviderTiemposRechazadas() {}

    public static ProviderTiemposRechazadas getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderTiemposRechazadas();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerTiemposRechazadas(final String mdId, final String usuario, final ConsultaTiemposProceso promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, TiemposProceso>() {
            @Override
            protected TiemposProceso doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuario);

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_TIEMPOS_EN_PROCESO)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;

                    return callback = gson.fromJson(jsonInString, TiemposProceso.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new TiemposProceso();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new TiemposProceso();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(TiemposProceso tiempos){
                promise.resolve(tiempos);
            }
        }).execute();
    }

    public interface ConsultaTiemposProceso {
        void resolve(TiemposProceso tiempos);
        void reject(Exception e);
    }
}
