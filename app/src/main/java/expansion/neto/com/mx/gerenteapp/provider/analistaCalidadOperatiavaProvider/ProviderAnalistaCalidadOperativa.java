package expansion.neto.com.mx.gerenteapp.provider.analistaCalidadOperatiavaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AnalistaCalidadOperativa;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderAnalistaCalidadOperativa {

    private static ProviderAnalistaCalidadOperativa instance;
    Context context;
    String respuesta;
    AnalistaCalidadOperativa analistaCalidadOperativa;

    public  ProviderAnalistaCalidadOperativa(){}

    public static ProviderAnalistaCalidadOperativa getInstance(Context context){
        if(instance == null ){
            instance = new ProviderAnalistaCalidadOperativa();
        }
        instance.context = context;
        return instance;
    }

    public void AnalistaCalidadOperativa(
            final String usuarioId, final String mdId, final String factorId, final String estatusValidacion,
            final String motivoRechazo,final String comentarios,final String finalizaValidacion,
            final String puestoId, final String areaID, final AnalistaCalidadOperativaInterface promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, AnalistaCalidadOperativa>() {
            @Override
            protected AnalistaCalidadOperativa doInBackground(Void... voids) {
                try{
                    FormBody.Builder forBodyBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId)
                            .add("mdId", mdId)
                            .add("factorId", factorId)
                            .add("estatusValidacion", estatusValidacion)
                            .add("motivoRechazo", motivoRechazo)
                            .add("comentarios",comentarios)
                            .add("finalizaValidacion", finalizaValidacion)
                            .add("puestoId", puestoId)
                            .add("areaId", areaID);
                    RequestBody formBody = forBodyBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_ANALISTA_CALIDAD_OPERATIVA)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return analistaCalidadOperativa = gson.fromJson(jsonInString, AnalistaCalidadOperativa.class);
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        analistaCalidadOperativa = new AnalistaCalidadOperativa();
                        analistaCalidadOperativa.setCodigo(1);
                        return analistaCalidadOperativa;
                    }else {
                        analistaCalidadOperativa = new AnalistaCalidadOperativa();
                        analistaCalidadOperativa.setCodigo(404);
                        return analistaCalidadOperativa;
                    }
                }
            }

            @Override
            protected void onPostExecute(AnalistaCalidadOperativa analistaCalidadOperativa) {
                promise.resolve(analistaCalidadOperativa);
            }
        }).execute();
    }

    public interface AnalistaCalidadOperativaInterface{
        void resolve(AnalistaCalidadOperativa analistaCalidadOperativa);
        void reject(Exception ex);
    }
}
