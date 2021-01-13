package expansion.neto.com.mx.gerenteapp.radios.provider.radiosProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.GuardarV;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.Radios;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.SinSitios;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.ValidaUb;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProvaiderDatosRadios {

    private static ProvaiderDatosRadios instance;
    Context context;
    String respuesta;
    String respuesta1;
    String respuesta2;
    String respuesta3;
    Radios radios = null;
    SinSitios sinSitios;
    ValidaUb validaUb;
    GuardarV guardarV;


    public ProvaiderDatosRadios () {}

    public static ProvaiderDatosRadios getInstance(Context context) {
        if (instance == null){
            instance = new ProvaiderDatosRadios();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosRadios (final String idUsuario, final ConsultaDatosRadios promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Radios>(){
            @Override
            protected Radios doInBackground(Void... voids) {
                try {
                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId",idUsuario);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_RADIOS)
                            .post(formBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    System.out.println( "idUsuario "+ idUsuario + " respuesta Radios " + jsonInString );
                    return radios = gson.fromJson(jsonInString,Radios.class);
                }catch (Exception e) {
                    System.out.println( "Exception " + e );
                    if (e.getMessage().contains("Failed to connect to")) {
                        radios = new Radios();
                        radios.setCodigo(1);
                        return radios;
                    } else {
                        radios = new Radios();
                        radios.setCodigo(404);
                        return radios;
                    }
                }
            }
            @Override
            protected void onPostExecute(Radios radios) {
                promise.resolve(radios);
            }
        }).execute();
    }

    public void sinSitiosDisponibles (final String idUsuario, final String raidoId, final String valorSol, final SinSitiosI promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, SinSitios>(){
            @Override
            protected SinSitios doInBackground(Void... voids) {
                try{
                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", idUsuario)
                            .add("radioId",raidoId)
                            .add("valorSolicitud", valorSol);
                            //.add("estatusId", "4");

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CAMBIA_ESTATUS_RADIOS)
                            .post(formBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    respuesta1 = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta1;
                    System.out.println( "RESPUESTA CANCELA/SIN SITIOS: valorSol : " + valorSol + " respuesta : "+ respuesta1);
                    return sinSitios = gson.fromJson(jsonInString, SinSitios.class);

                }catch (Exception e){
                    if (e.getMessage().contains("Failed to connect to")) {
                        sinSitios = new SinSitios();
                        sinSitios.setCodigo(1);
                        return sinSitios;
                    } else {
                        sinSitios = new SinSitios();
                        sinSitios.setCodigo(404);
                        return sinSitios;
                    }
                }
            }

            @Override
            protected void onPostExecute(SinSitios sinSitios) {
                promise.resolve(sinSitios);
            }
        }).execute();
    }

    public void validaUbicacion (final String idUsuario, final String raidoId, final String latitud, final String longitud,final ValidaUbF promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, ValidaUb>(){
            @Override
            protected ValidaUb doInBackground(Void... voids) {
                try{
                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", idUsuario)
                            .add("radioId",raidoId)
                            .add("latitud", latitud)
                            .add("longitud", longitud);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_VALIDAR_UBICACION)
                            .post(formBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    respuesta2 = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta2;
                    return validaUb = gson.fromJson(jsonInString, ValidaUb.class);

                }catch (Exception e){
                    if (e.getMessage().contains("Failed to connect to")) {
                        validaUb = new ValidaUb();
                        validaUb.setCodigo(1);
                        return validaUb;
                    } else {
                        validaUb = new ValidaUb();
                        validaUb.setCodigo(404);
                        return validaUb;
                    }
                }
            }

            @Override
            protected void onPostExecute(ValidaUb validaUb) {
                promise.resolve(validaUb);
            }
        }).execute();
    }

    public void guardarVisita (final String idUsuario, final String raidoId, final String latitud, final String longitud,final GuardarVis promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, GuardarV>(){
            @Override
            protected GuardarV doInBackground(Void... voids) {
                try{
                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", idUsuario)
                            .add("radioId",raidoId)
                            .add("latitud", latitud)
                            .add("longitud", longitud);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_GUARDAR_VISITA)
                            .post(formBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    respuesta3 = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta3;
                    return guardarV = gson.fromJson(jsonInString, GuardarV.class);

                }catch (Exception e){
                    if (e.getMessage().contains("Failed to connect to")) {
                        guardarV = new GuardarV();
                        guardarV.setCodigo(1);
                        return guardarV;
                    } else {
                        guardarV = new GuardarV();
                        guardarV.setCodigo(404);
                        return guardarV;
                    }
                }
            }

            @Override
            protected void onPostExecute(GuardarV guardarV) {
                promise.resolve(guardarV);
            }
        }).execute();
    }



    public interface ConsultaDatosRadios {
        void resolve(Radios datosSitio);
        void reject(Exception e);
    }

    public interface SinSitiosI {
        void resolve(SinSitios sinSitios);
        void reject(Exception e);
    }

    public interface ValidaUbF {
        void resolve(ValidaUb validaUb);
        void reject(Exception e);
    }

    public interface GuardarVis {
        void resolve(GuardarV guardarV);
        void reject(Exception e);
    }
}
