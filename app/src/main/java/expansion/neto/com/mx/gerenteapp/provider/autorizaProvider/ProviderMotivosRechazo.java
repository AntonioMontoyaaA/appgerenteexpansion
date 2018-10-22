package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.DatosSitio;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.MotivosRechazo;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderMotivosRechazo {

    private static ProviderMotivosRechazo instance;
    Context context;
    String respuesta;
    MotivosRechazo usuarioCallback = null;
    private final String TIPO_MODULO_RECHAZA_GERENTE = "1";
    private final String TIPO_MODULO_RECHAZA_STATUS = "2";

    public ProviderMotivosRechazo() {}

    public static ProviderMotivosRechazo getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderMotivosRechazo();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerMotivosRechazo(final String modulo, final String usuario, final int status, final ProviderMotivosRechazo.ConsultaMotivosRechazo promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, MotivosRechazo>() {
            @Override
            protected MotivosRechazo doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuario)
                            .add("nivelEvaluacion", String.valueOf(status))
                            .add("modulo", modulo)
                            .add("tipomodulo", TIPO_MODULO_RECHAZA_GERENTE);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTA_MOTIVOS_RECHAZO)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    usuarioCallback = gson.fromJson(jsonInString, MotivosRechazo.class);
                    if(usuarioCallback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return usuarioCallback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new MotivosRechazo();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new MotivosRechazo();
                        usuarioCallback.setCodigo(403);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(MotivosRechazo motivosRechazo){
                promise.resolve(motivosRechazo);
            }
        }).execute();
    }

    public interface ConsultaMotivosRechazo {
        void resolve(MotivosRechazo motivosRechazo);
        void reject(Exception e);
    }
}
