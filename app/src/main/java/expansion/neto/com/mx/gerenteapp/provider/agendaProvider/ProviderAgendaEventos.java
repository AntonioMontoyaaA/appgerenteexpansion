package expansion.neto.com.mx.gerenteapp.provider.agendaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Eventos;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.gerenteapp.modelView.dashboardModel.Dashboard;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderAgendaEventos {

    private static ProviderAgendaEventos instance;
    Context context;
    String respuesta;
    Eventos usuarioCallback = null;

    public ProviderAgendaEventos() {}

    public static ProviderAgendaEventos getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderAgendaEventos();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerEventos(final String usuarioId, final InterfaceObtieneEventos promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Eventos>() {
            @Override
            protected Eventos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_EVENTOS)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    usuarioCallback = gson.fromJson(jsonInString, Eventos.class);
                    if(usuarioCallback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return usuarioCallback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new Eventos();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new Eventos();
                        usuarioCallback.setCodigo(403);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Eventos eventos){
                promise.resolve(eventos);
            }
        }).execute();
    }

    public interface InterfaceObtieneEventos {
        void resolve(Eventos eventos);
        void reject(Exception e);
    }

}
