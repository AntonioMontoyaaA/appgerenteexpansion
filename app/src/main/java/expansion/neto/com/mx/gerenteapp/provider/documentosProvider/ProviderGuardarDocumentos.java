package expansion.neto.com.mx.gerenteapp.provider.documentosProvider;

import android.content.Context;
import android.os.AsyncTask;
import com.google.gson.Gson;
import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.GuardarNotificacion;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import okhttp3.*;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.TIPO_SERVICIO;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.TIPO_SERVICIO_DOC;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderGuardarDocumentos {

    private static ProviderGuardarDocumentos instance;
    Context context;
    String respuesta;
    Codigos codigo = null;

    public ProviderGuardarDocumentos() {}

    public static ProviderGuardarDocumentos getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderGuardarDocumentos();
        }
        instance.context = context;
        return instance;
    }

    public void guardarDocumentos(final String usuario, final String mdId, final String json, final String fecha,
                                  final InterfaceGuardarDocumentos promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuario)
                            .add("mdId", mdId)
                            .add("tipoServicio", TIPO_SERVICIO_DOC)
                            .add("fecha", fecha)
                            .add("documentos", json);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_GUARDAR_DOCUMENTOS)
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
    public void guardarDocumentos1(final String usuario, final String mdId,final String tipoServicio, final String json, final String fecha,
                                  final InterfaceGuardarDocumentos promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuario)
                            .add("mdId", mdId)
                            .add("tipoServicio", tipoServicio)
                            .add("fecha", fecha)
                            .add("documentos", json);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_GUARDAR_DOCUMENTOS)
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

    public interface InterfaceGuardarDocumentos{
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
