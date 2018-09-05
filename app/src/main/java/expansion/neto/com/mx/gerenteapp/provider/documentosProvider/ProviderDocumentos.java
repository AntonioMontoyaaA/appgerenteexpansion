package expansion.neto.com.mx.gerenteapp.provider.documentosProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.Calendar;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.modelView.documentosModel.Documentos;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDocumentos {

    private static ProviderDocumentos instance;
    Context context;
    String respuesta;
    Documentos usuarioCallback = null;

    public ProviderDocumentos() {}

    public static ProviderDocumentos getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDocumentos();
        }
        instance.context = context;
        return instance;
    }

    public void obtieneDocumentos(final String usuarioId, final String mdId, final ConsultaDocumentos promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Documentos>() {
            @Override
            protected Documentos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    Calendar fecha = Calendar.getInstance();
                    int anio = fecha.get(Calendar.YEAR);

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId)
                            .add("mdId", mdId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DOCUMENTOS)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    usuarioCallback = gson.fromJson(jsonInString, Documentos.class);
                    return usuarioCallback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new Documentos();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new Documentos();
                        usuarioCallback.setCodigo(403);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Documentos datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDocumentos {
        void resolve(Documentos documentos);
        void reject(Exception e);
    }

}
