package expansion.neto.com.mx.gerenteapp.provider.dashboardProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Autoriza;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.UsuarioLogin;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Clase singleton que regresa la validación del usuario así como también regresa el modelo de la persona
 * Created by marcosmarroquin on 21/03/18.
 */
public class ExpansionGerenteProviderAutoriza {

    private static ExpansionGerenteProviderAutoriza instance;
    Context context;
    String respuesta;

    public ExpansionGerenteProviderAutoriza() {}

    public static ExpansionGerenteProviderAutoriza getInstance(Context context) {
        if(instance == null) {
            instance = new ExpansionGerenteProviderAutoriza();
        }
        instance.context = context;
        return instance;
    }

    public void compruebaAutoriza(final ConsultaAutoriza promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, ArrayList<Autoriza>>() {
            @Override
            protected ArrayList<Autoriza> doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                ArrayList<Autoriza> autorizaCallback = null;
                Request.Builder builder = new Request.Builder();
                builder.url(RestUrl.REST_ACTION_CONSULTAR_AUTORIZA);
                Request request = builder.build();
                try {
                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    Type listObject = new TypeToken<ArrayList<Autoriza>>(){}.getType();
                    autorizaCallback = gson.fromJson(jsonInString, listObject);
                    return autorizaCallback;
                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        return autorizaCallback;
                    }else{
                        return autorizaCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(ArrayList<Autoriza> autoriza){
                promise.resolve(autoriza);
            }
        }).execute();
    }

    public interface ConsultaAutoriza {
        void resolve(ArrayList<Autoriza> autoriza);
        void reject(Exception e);
    }
}