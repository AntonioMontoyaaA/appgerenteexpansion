package expansion.neto.com.mx.gerenteapp.provider.agendaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.GuardarNotificacion;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderNotificacionVisto {

    private static ProviderNotificacionVisto instance;
    Context context;
    String respuesta;
    Codigos codigo = null;

    public ProviderNotificacionVisto() {}

    public static ProviderNotificacionVisto getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderNotificacionVisto();
        }
        instance.context = context;
        return instance;
    }

    public void guardarNotificacion(final GuardarNotificacion notificacion, final InterfaceNotificacionVista promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", notificacion.getUsuarioId())
                            .add("tipoNotificacion", notificacion.getTipoNotificacion())
                            .add("mdId", notificacion.getMdId())
                            .add("fecha", notificacion.getFecha())
                            .add("nivelEstatusArea", notificacion.getNivelEstatusArea());

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_GUARDAR_NOTIFICACION)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return codigo = gson.fromJson(jsonInString, Codigos.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        codigo = new Codigos();
                        codigo.setCodigo(1);
                        return codigo;
                    }else{
                        codigo = new Codigos();
                        codigo.setCodigo(404);
                        return codigo;
                    }
                }
            }
            @Override
            protected void onPostExecute(Codigos returncodigo){
                promise.resolve(returncodigo);
            }
        }).execute();
    }

    public interface InterfaceNotificacionVista{
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
