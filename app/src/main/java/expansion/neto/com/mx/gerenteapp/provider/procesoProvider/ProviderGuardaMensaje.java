package expansion.neto.com.mx.gerenteapp.provider.procesoProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatGuardaProceso;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderGuardaMensaje {

    private static ProviderGuardaMensaje instance;
    Context context;
    String respuesta;
    ChatGuardaProceso callback = null;

    public ProviderGuardaMensaje() {}

    public static ProviderGuardaMensaje getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderGuardaMensaje();
        }
        instance.context = context;
        return instance;
    }

    public void guardarChatProceso(final String mdId, final String comentarios, final String usuarioId, final int areaId, final GuardaMensajeChatProceso promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, ChatGuardaProceso>() {
            @Override
            protected ChatGuardaProceso doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("comentarios", comentarios)
                            .add("usuarioId", usuarioId)
                            .add("areaId", String.valueOf(areaId));

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_GUARDAR_CHAT_EN_PROCESO)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    callback = gson.fromJson(jsonInString, ChatGuardaProceso.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return callback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new ChatGuardaProceso();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new ChatGuardaProceso();
                        callback.setCodigo(403);
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

    public interface GuardaMensajeChatProceso {
        void resolve(ChatGuardaProceso chat);
        void reject(Exception e);
    }
}
