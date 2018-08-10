package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.CrearPeatonal;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.TIPO_SERVICIO;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderCrearPeatonal {

    private static ProviderCrearPeatonal instance;
    Context context;
    String respuesta;
    Codigos codigo = null;

    public ProviderCrearPeatonal() {}

    public static ProviderCrearPeatonal getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderCrearPeatonal();
        }
        instance.context = context;
        return instance;
    }

    public void guardarPeatonal(final CrearPeatonal crearPeatonal, final InterfaceCrearDatosPeatonal promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", crearPeatonal.getUsuarioId())
                            .add("mdId", crearPeatonal.getMdId())
                            .add("fecha", crearPeatonal.getFecha())
                            .add("horaInicio", crearPeatonal.getHoraInicio())
                            .add("horaFinal", crearPeatonal.getHoraFinal())
                            .add("total", crearPeatonal.getTotal())
                            .add("latitud", crearPeatonal.getLatitud())
                            .add("longitud", crearPeatonal.getLongitud())
                            .add("bajaConteos", crearPeatonal.getBajaConteos())
                            .add("numTelefono", crearPeatonal.getNumTelefono())
                            .add("versionApp", crearPeatonal.getVersionApp())
                            .add("tipoServicio", TIPO_SERVICIO);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CREAR_CONTEO)
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

    public interface InterfaceCrearDatosPeatonal {
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
