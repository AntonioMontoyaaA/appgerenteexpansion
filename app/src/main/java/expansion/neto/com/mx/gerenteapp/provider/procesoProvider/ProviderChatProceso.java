package expansion.neto.com.mx.gerenteapp.provider.procesoProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderChatProceso {

    private static ProviderChatProceso instance;
    Context context;
    String respuesta;
    ChatProceso callback = null;

    public ProviderChatProceso() {}

    public static ProviderChatProceso getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderChatProceso();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerChatProceso(final String mdId, final int areaId, final String usuarioId, final ConsultaChatProceso promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, ChatProceso>() {
            @Override
            protected ChatProceso doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("areaId", String.valueOf(areaId))
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_CHAT_EN_PROCESO)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    callback = gson.fromJson(jsonInString, ChatProceso.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                    }
                    return callback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new ChatProceso();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new ChatProceso();
                        callback.setCodigo(403);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(ChatProceso chat){
                promise.resolve(chat);
            }
        }).execute();
    }

    public interface ConsultaChatProceso {
        void resolve(ChatProceso chat);
        void reject(Exception e);
    }
}
