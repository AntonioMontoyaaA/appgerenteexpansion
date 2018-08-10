package expansion.neto.com.mx.gerenteapp.provider.agendaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.GuardarEvento;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderCrearEvento {

    private static ProviderCrearEvento instance;
    Context context;
    String respuesta;
    Codigos codigo = null;

    public ProviderCrearEvento() {}

    public static ProviderCrearEvento getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderCrearEvento();
        }
        instance.context = context;
        return instance;
    }

    public void guardarEvento(final GuardarEvento evento, final InterfaceCrearEvento promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioIdRegistra", evento.getUsuarioIdRegistra())
                            .add("tareaxAreaId", evento.getTareaxAreaId())
                            .add("fechaAgenda", evento.getFechaAgenda())
                            .add("fechaFinProgramada", evento.getFechaFinProgramada())
                            .add("observaciones", evento.getObservaciones())
                            .add("direccion", evento.getDireccion())
                            .add("latitud", evento.getLatitud())
                            .add("longitud", evento.getLongitud())
                            .add("tipoAsignacion", evento.getTipoAsignacion())
                            .add("porAsignar", evento.getPorAsignar());

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CREAR_EVENTO)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    codigo = gson.fromJson(jsonInString, Codigos.class);
                    if(codigo.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return codigo;
                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        codigo = new Codigos();
                        codigo.setCodigo(1);
                        return codigo;
                    }else{
                        codigo = new Codigos();
                        codigo.setCodigo(403);
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

    public interface InterfaceCrearEvento {
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
