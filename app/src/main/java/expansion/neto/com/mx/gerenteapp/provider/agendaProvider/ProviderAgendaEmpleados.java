package expansion.neto.com.mx.gerenteapp.provider.agendaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Empleados;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Eventos;
import expansion.neto.com.mx.gerenteapp.modelView.dashboardModel.Dashboard;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderAgendaEmpleados {

    private static ProviderAgendaEmpleados instance;
    Context context;
    String respuesta;
    Empleados usuarioCallback = null;

    public ProviderAgendaEmpleados() {}

    public static ProviderAgendaEmpleados getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderAgendaEmpleados();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerEmpleados(final String usuarioId, final ProviderAgendaEmpleados.InterfaceObtieneEmpleados promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Empleados>() {
            @Override
            protected Empleados doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_EMPLEADOS_AGENDA)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    usuarioCallback = gson.fromJson(jsonInString, Empleados.class);
                    if(usuarioCallback.getCodigo() == 404) {
                        Util.cerrarSesion(context);
                        return null;
                    }
                    return usuarioCallback;
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new Empleados();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new Empleados();
                        usuarioCallback.setCodigo(403);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Empleados empleados){
                promise.resolve(empleados);
            }
        }).execute();
    }

    public interface InterfaceObtieneEmpleados {
        void resolve(Empleados empleados);
        void reject(Exception e);
    }
}
