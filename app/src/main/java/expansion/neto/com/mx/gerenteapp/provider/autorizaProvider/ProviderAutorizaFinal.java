package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AutorizaResponse;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderAutorizaFinal {

    private static ProviderAutorizaFinal instance;
    Context context;
    String respuesta;
    AutorizaResponse usuarioCallback = null;
    private final String TIPO_MODULO_RECHAZA_GERENTE = "1";

    public ProviderAutorizaFinal() {}

    public static ProviderAutorizaFinal getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderAutorizaFinal();
        }
        instance.context = context;
        return instance;
    }

    public void autorizaFinal(final String mdId, final String usuarioId, final int estatusValidacion, final int motivoRechazo, final String comentarios, final AutorizaFinal promise) {
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, AutorizaResponse>() {
            @Override
            protected AutorizaResponse doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuarioId)
                            .add("estatusvalidacion", String.valueOf(estatusValidacion))
                            .add("motivorechazo", String.valueOf(motivoRechazo))
                            .add("comentarios", comentarios);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_AUTORIZA_FINAL)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    usuarioCallback = gson.fromJson(jsonInString, AutorizaResponse.class);
                    if(usuarioCallback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return usuarioCallback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new AutorizaResponse();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new AutorizaResponse();
                        usuarioCallback.setCodigo(403);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(AutorizaResponse autorizaResponse){
                promise.resolve(autorizaResponse);
            }
        }).execute();
    }

    public interface AutorizaFinal {
        void resolve(AutorizaResponse autorizaResponse);
        void reject(Exception e);
    }
}
