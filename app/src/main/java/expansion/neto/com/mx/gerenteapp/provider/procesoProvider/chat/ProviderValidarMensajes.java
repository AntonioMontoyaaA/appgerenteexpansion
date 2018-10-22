package expansion.neto.com.mx.gerenteapp.provider.procesoProvider.chat;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatGuardaProceso;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderValidarMensajes {

    private static ProviderValidarMensajes instance;
    Context context;
    String respuesta;
    ChatGuardaProceso callback = null;

    public ProviderValidarMensajes() {}

    public static ProviderValidarMensajes getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderValidarMensajes();
        }
        instance.context = context;
        return instance;
    }

    public void guardarValidacionMensajes(final String mdId, final String usuarioId, final String statusId, final InterfaceValidacionMensajes promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, ChatGuardaProceso>() {
            @Override
            protected ChatGuardaProceso doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuarioId)
                            .add("nivelEstatusArea", statusId);

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_GUARDAR_VALIDACION)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;

                    return callback = gson.fromJson(jsonInString, ChatGuardaProceso.class);

                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new ChatGuardaProceso();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new ChatGuardaProceso();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(ChatGuardaProceso chat){
                promise.resolve(chat);
            }
        }).execute();
    }

    public interface InterfaceValidacionMensajes {
        void resolve(ChatGuardaProceso chat);
        void reject(Exception e);
    }
}
