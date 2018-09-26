package expansion.neto.com.mx.gerenteapp.provider.procesoProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Totales;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderTotales {

    private static ProviderTotales instance;
    Context context;
    String respuesta;
    Totales usuarioCallback = null;

    public ProviderTotales() {}

    public static ProviderTotales getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderTotales();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerTotales(final String usuarioId, final String estatus, final String area,
                               final String mes, final String semana, final String anio, final InterfaceObtieneTotales promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Totales>() {
            @Override
            protected Totales doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId)
                            .add("estatus", estatus)
                            .add("area", area)
                            .add("mes", mes)
                            .add("semana", semana)
                            .add("anio", anio);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_CONSULTA_TOTALES)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return usuarioCallback = gson.fromJson(jsonInString, Totales.class);

                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new Totales();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new Totales();
                        usuarioCallback.setCodigo(404);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Totales totales){
                promise.resolve(totales);
            }
        }).execute();
    }

    public interface InterfaceObtieneTotales {
        void resolve(Totales totales);
        void reject(Exception e);
    }

}
