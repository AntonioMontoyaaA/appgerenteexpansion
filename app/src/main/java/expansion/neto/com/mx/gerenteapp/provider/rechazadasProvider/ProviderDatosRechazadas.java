package expansion.neto.com.mx.gerenteapp.provider.rechazadasProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.Calendar;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.Proceso;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.ANIO_ACTUAL;
import static expansion.neto.com.mx.gerenteapp.provider.dashboardProvider.ProviderDatosDashboard.TIPO_APP;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosRechazadas {

    private static ProviderDatosRechazadas instance;
    Context context;
    String respuesta;
    Proceso callback = null;
    private final String ESTATUS_POR_TERMINAR  =   "3";
    private final String TIPO_CONSULTA_TIPO = "3";
    private final String TIPO_CONSULTA_SEMANA = "0";


    public ProviderDatosRechazadas() {}

    public static ProviderDatosRechazadas getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosRechazadas();
        }
        instance.context = context;
        return instance;
    }

    Calendar fecha = Calendar.getInstance();
    final int meses = fecha.get(Calendar.MONTH) + 1;

    public void obtenerDatosRechazadas(final String usuarioId,
                                       final String area, final String mes, final ConsultaDatosRechazadas promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Proceso>() {
            @Override
            protected Proceso doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    Calendar fecha = Calendar.getInstance();
                    int anio = fecha.get(Calendar.YEAR);

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("estatus", ESTATUS_POR_TERMINAR)
                            .add("area", area)
                            .add("mes", mes)
                            .add("semana", TIPO_CONSULTA_SEMANA)
                            .add("anio", String.valueOf(anio))
                            .add("tipoapp", TIPO_APP)
                            .add("tipoconsulta", TIPO_CONSULTA_TIPO)
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_AUTORIZADAS_LISTA)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;

                    return callback = gson.fromJson(jsonInString, Proceso.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Proceso();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Proceso();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Proceso datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDatosRechazadas {
        void resolve(Proceso datosSitio);
        void reject(Exception e);
    }

}
