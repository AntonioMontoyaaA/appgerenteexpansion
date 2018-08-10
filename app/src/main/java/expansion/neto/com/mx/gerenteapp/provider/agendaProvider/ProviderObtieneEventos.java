package expansion.neto.com.mx.gerenteapp.provider.agendaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.ConsultaEvento;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderObtieneEventos {

    private static ProviderObtieneEventos instance;
    Context context;
    String respuesta;
    ConsultaEvento usuarioCallback = null;


    public ProviderObtieneEventos() {}

    public static ProviderObtieneEventos getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderObtieneEventos();
        }
        instance.context = context;
        return instance;
    }



    public void obtenerEventos(final String usuarioId, final String fecha, final String tipoConsulta, final String apartirDe, final InterfaceObtieneEventos promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, ConsultaEvento>() {
            @Override
            protected ConsultaEvento doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId)
                            .add("fecha", fecha)
                            .add("tipoEvento", tipoConsulta)
                            .add("apartirDe", apartirDe);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_AGENDA)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return usuarioCallback = gson.fromJson(jsonInString, ConsultaEvento.class);

                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new ConsultaEvento();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new ConsultaEvento();
                        usuarioCallback.setCodigo(404);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(ConsultaEvento eventos){
                promise.resolve(eventos);
            }
        }).execute();
    }

    public interface InterfaceObtieneEventos {
        void resolve(ConsultaEvento eventos);
        void reject(Exception e);
    }

}
