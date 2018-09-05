package expansion.neto.com.mx.gerenteapp.provider.agendaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Notificaciones;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderObtieneNotificaciones {

    private static ProviderObtieneNotificaciones instance;
    Context context;
    String respuesta;
    Notificaciones usuarioCallback = null;

    private static String ESTATUS_NOTIFICACION = "0";
    private static String NUM_REGISTROS = "50";
    private static String TIPO_NOTIFICACION = "1";

    public ProviderObtieneNotificaciones() {}

    public static ProviderObtieneNotificaciones getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderObtieneNotificaciones();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerNotificaciones(final String usuarioId, final String tipoNotificacion, final InterfaceObtieneNotificaciones promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Notificaciones>() {
            @Override
            protected Notificaciones doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("tipoNotificacion", TIPO_NOTIFICACION)
                            .add("estatusNotificacion", ESTATUS_NOTIFICACION)
                            .add("numRegistros", NUM_REGISTROS)
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_NOTIFICACIONES)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return usuarioCallback = gson.fromJson(jsonInString, Notificaciones.class);

                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new Notificaciones();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new Notificaciones();
                        usuarioCallback.setCodigo(404);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Notificaciones eventos){
                promise.resolve(eventos);
            }
        }).execute();
    }

    public interface InterfaceObtieneNotificaciones {
        void resolve(Notificaciones eventos);
        void reject(Exception e);
    }

}
