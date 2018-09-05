package expansion.neto.com.mx.gerenteapp.provider.loginProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.FORMATO_FOTO;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderObtenerUrl {

    private static ProviderObtenerUrl instance;
    Context context;
    String respuesta;
    Codigos callback = null;

    public ProviderObtenerUrl() {}

    public static ProviderObtenerUrl getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderObtenerUrl();
        }
        instance.context = context;
        return instance;
    }

    public static String TIPO_ARCHIVO = "1";


    public void obtenerUrl(final String mdId, final String nombreImg, final String b64, final ConsultaUrl promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    SharedPreferences preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    String usuarioId = preferences.getString("usuario","");

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("nombreArc", nombreImg)
                            .add("archivo", b64)
                            .add("formato", FORMATO_FOTO)
                            .add("usuarioId", usuarioId)
                            .add("tipoArchivo", TIPO_ARCHIVO);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DATOS_COMPETENCIA_CLOUDINARY)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, Codigos.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Codigos();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Codigos();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Codigos codigos){
                promise.resolve(codigos);
            }
        }).execute();
    }

    public interface ConsultaUrl {
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
