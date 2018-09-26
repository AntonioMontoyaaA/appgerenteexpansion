package expansion.neto.com.mx.gerenteapp.provider.crearProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.FactoresConstruccion;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.FACTOR_ID;


/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosFactoresConstruccion {

    private static ProviderDatosFactoresConstruccion instance;
    Context context;
    String respuesta;
    FactoresConstruccion callback = null;

    public ProviderDatosFactoresConstruccion() {}

    public static ProviderDatosFactoresConstruccion getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosFactoresConstruccion();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosContruccion(final String mdId, final ConsultaFactoresConstruccion promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, FactoresConstruccion>() {
            @Override
            protected FactoresConstruccion doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("factorId",FACTOR_ID)
                            .add("mdId", mdId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_OBTENER_FACTORES_CONSTRUCCION)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, FactoresConstruccion.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new FactoresConstruccion();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new FactoresConstruccion();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(FactoresConstruccion factoresConstruccion){
                promise.resolve(factoresConstruccion);
            }
        }).execute();
    }

    public interface ConsultaFactoresConstruccion {
        void resolve(FactoresConstruccion factoresConstruccion);
        void reject(Exception e);
    }


}
