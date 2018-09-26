package expansion.neto.com.mx.gerenteapp.provider.crearProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.modelView.crearModel.CompetenciasGeneradoresV2;
import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosCompetencias {

    private static ProviderDatosCompetencias instance;
    Context context;
    String respuesta;
    CompetenciasGeneradoresV2 callback = null;

    public ProviderDatosCompetencias() {}

    public static ProviderDatosCompetencias getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosCompetencias();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosCompetencias(final String usuario, final String mdId, final ConsultaDatosCompetencia promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, CompetenciasGeneradoresV2>() {
            @Override
            protected CompetenciasGeneradoresV2 doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuario);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DATOS_COMPETENCIA_GENERADORES)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, CompetenciasGeneradoresV2.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new CompetenciasGeneradoresV2();
                        //callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new CompetenciasGeneradoresV2();
                        //callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(CompetenciasGeneradoresV2 competenciasGeneradores){
                promise.resolve(competenciasGeneradores);
            }
        }).execute();
    }

    public interface ConsultaDatosCompetencia {
        void resolve(CompetenciasGeneradoresV2 competenciasGeneradores);
        void reject(Exception e);
    }

}
