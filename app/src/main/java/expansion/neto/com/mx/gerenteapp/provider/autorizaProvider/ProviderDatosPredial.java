package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosPredial;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosPredial {

    private static ProviderDatosPredial instance;
    Context context;
    String respuesta;
    DatosPredial usuarioCallback = null;

    public ProviderDatosPredial() {}

    public static ProviderDatosPredial getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosPredial();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosPredial(final String mdId, final String usuarioId, final ConsultaDatosPredial promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, DatosPredial>() {
            @Override
            protected DatosPredial doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId)
                            .add("mdId", mdId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_PREDIAL)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return usuarioCallback = gson.fromJson(jsonInString, DatosPredial.class);

                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new DatosPredial();
                        //usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new DatosPredial();
                        //usuarioCallback.setCodigo(404);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(DatosPredial datosPredial){
                promise.resolve(datosPredial);
            }
        }).execute();
    }

    public interface ConsultaDatosPredial {
        void resolve(DatosPredial datosPredial);
        void reject(Exception e);
    }

}
