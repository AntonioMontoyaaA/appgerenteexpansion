package expansion.neto.com.mx.gerenteapp.provider.procesoProvider.chat;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatNumMensajes;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderNumMensajes {

    private static ProviderNumMensajes instance;
    Context context;
    String respuesta;
    ChatNumMensajes callback = null;

    public ProviderNumMensajes() {}

    public static ProviderNumMensajes getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderNumMensajes();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerNumMensajes(final String mdId, final String usuario , final ConsultaNumMensajes promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, ChatNumMensajes>() {
            @Override
            protected ChatNumMensajes doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuario);

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_NUM_MENSAJES)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;

                    return callback = gson.fromJson(jsonInString, ChatNumMensajes.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new ChatNumMensajes();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new ChatNumMensajes();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(ChatNumMensajes chat){
                promise.resolve(chat);
            }
        }).execute();
    }

    public interface ConsultaNumMensajes {
        void resolve(ChatNumMensajes chat);
        void reject(Exception e);
    }
}
