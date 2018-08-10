package expansion.neto.com.mx.gerenteapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.HorasPeatonales;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderHorasPeatonales {

    private static ProviderHorasPeatonales instance;
    Context context;
    String respuesta;
    HorasPeatonales codigo = null;
    private final String FACTOR_ID_HORAS_CONTEOS = "7";

    public ProviderHorasPeatonales() {}

    public static ProviderHorasPeatonales getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderHorasPeatonales();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerHoras(final String mdId, final String usuarioId, final InterfaceObtieneHoras promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, HorasPeatonales>() {
            @Override
            protected HorasPeatonales doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuarioId)
                            .add("factorId", FACTOR_ID_HORAS_CONTEOS);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTA_HORAS_PEATONAL)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    codigo = gson.fromJson(jsonInString, HorasPeatonales.class);
                    if(codigo.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return codigo;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        codigo = new HorasPeatonales();
                        codigo.setCodigo(1);
                        return codigo;
                    }else{
                        codigo = new HorasPeatonales();
                        codigo.setCodigo(403);
                        return codigo;
                    }
                }
            }
            @Override
            protected void onPostExecute(HorasPeatonales horasPeatonales){
                promise.resolve(horasPeatonales);
            }
        }).execute();
    }

    public interface InterfaceObtieneHoras {
        void resolve(HorasPeatonales horasPeatonales);
        void reject(Exception e);
    }

}