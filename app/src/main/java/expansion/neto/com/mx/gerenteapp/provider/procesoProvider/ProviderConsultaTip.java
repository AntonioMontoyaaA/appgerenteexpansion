package expansion.neto.com.mx.gerenteapp.provider.procesoProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.Tips;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.TIPO_APLICACION;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderConsultaTip {

    private static ProviderConsultaTip instance;
    Context context;
    String respuesta;
    Tips callback = null;


    public ProviderConsultaTip() {}

    public static ProviderConsultaTip getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderConsultaTip();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerTips(final String pantalla, final ConsultaTips promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Tips>() {
            @Override
            protected Tips doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("modulo", pantalla)
                            .add("tipoaplicacion", TIPO_APLICACION);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_TIPS)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    callback = gson.fromJson(jsonInString, Tips.class);
                    if(callback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return callback;
                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Tips();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Tips();
                        callback.setCodigo(403);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Tips tip){
                promise.resolve(tip);
            }
        }).execute();
    }

    public interface ConsultaTips {
        void resolve(Tips tip);
        void reject(Exception e);
    }

}
