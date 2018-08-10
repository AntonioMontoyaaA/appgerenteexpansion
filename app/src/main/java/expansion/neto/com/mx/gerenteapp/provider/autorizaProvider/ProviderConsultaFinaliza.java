package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosPuntuacion;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderConsultaFinaliza {

    private static ProviderConsultaFinaliza instance;
    Context context;
    String respuesta;
    DatosPuntuacion callback = null;

    public ProviderConsultaFinaliza() {}

    public static ProviderConsultaFinaliza getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderConsultaFinaliza();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerPuntos(final String mdId, final String usuario, final ConsultaPuntos promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, DatosPuntuacion>() {
            @Override
            protected DatosPuntuacion doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuario);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTA_FINALIZA_MD)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, DatosPuntuacion.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new DatosPuntuacion();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new DatosPuntuacion();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(DatosPuntuacion datosPuntuacion){
                promise.resolve(datosPuntuacion);
            }
        }).execute();
    }

    public interface ConsultaPuntos {
        void resolve(DatosPuntuacion datosPuntuacion);
        void reject(Exception e);
    }

}
