package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.CrearPeatonal;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.NUM_TELEFONO;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.TIPOSERVICIO;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.TIPO_SERVICIO;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.VERSION_APP;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderCrearMonto {

    private static ProviderCrearMonto instance;
    Context context;
    String respuesta;
    Codigos codigo = null;

    public ProviderCrearMonto() {}

    public static ProviderCrearMonto getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderCrearMonto();
        }
        instance.context = context;
        return instance;
    }

    public void guardarMonto(final String fecha, final String mdId, final String usuarioId, final String monto, final InterfaceCrearMonto promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {


                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId)
                            .add("mdId", mdId)
                            .add("tipoServicio", TIPOSERVICIO)
                            .add("fecha", fecha)
                            .add("monto", monto);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_CONSULTA_MONTO)
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

    public interface InterfaceCrearMonto {
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
