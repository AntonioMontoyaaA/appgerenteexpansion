package expansion.neto.com.mx.gerenteapp.provider.loginProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.UsuarioLogin;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.TIPO_APLICACION;
import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.VERSION_APP;
import static expansion.neto.com.mx.gerenteapp.utils.Util.md5;

/**
 * Clase singleton que regresa la validación del usuario así como también regresa el modelo de la persona
 * Created by marcosmarroquin on 21/03/18.
 */
public class ExpansionGerenteProviderLogin {

    private static ExpansionGerenteProviderLogin instance;
    Context context;
    String respuesta;
    UsuarioLogin usuarioCallback = null;

    public ExpansionGerenteProviderLogin() {}

    public static ExpansionGerenteProviderLogin getInstance(Context context) {
        if(instance == null) {
            instance = new ExpansionGerenteProviderLogin();
        }
        instance.context = context;
        return instance;
    }
    //"351881091770915"
    public void compruebaLogin(final Usuario usuario, final ConsultaUsuario promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, UsuarioLogin>() {
            @Override
            protected UsuarioLogin doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {
                    //Util.getImei(context)
                    //"202020202020202"
                    //356017073328381
                    //Util.getImei(context)
                    //351881091770691
                    //352818091381102
                    //351881091739183 DES
                    // rosalia
                    //md5 351881091739456
                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuario.getUsuario())
                            .add("contrasena", md5(usuario.getContra()))
                            .add("numImei", Util.getImei(context))
                            .add("versionapp", VERSION_APP)
                            .add("tipoLog", TIPO_APLICACION);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_LOGIN)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return usuarioCallback = gson.fromJson(jsonInString, UsuarioLogin.class);
                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new UsuarioLogin();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new UsuarioLogin();
                        usuarioCallback.setCodigo(404);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(UsuarioLogin usuarioLogin){
                promise.resolve(usuarioLogin);
            }
        }).execute();
    }

    public interface ConsultaUsuario {
        void resolve(UsuarioLogin usuario);
        void reject(Exception e);
    }

}

/* Logic login access offline */

//if(usuarioLogin==null) usuarioLogin = new UsuarioLogin();
//
//                if(usuarioLogin.getCodigo()==404){
//
//                    Uri contentUri = GerenteContract.PerfilEntry.CONTENT_URI;
//                    String[] columns        = new String[]{
//                            GerenteContract.PerfilEntry.COLUMN_INT_NUM_USUARIO,
//                    };
//
//                    String selection      = GerenteContract.PerfilEntry.COLUMN_INT_NUM_USUARIO+"=?";
//                    String[] selectionArgs  = {String.valueOf(usuario.getUsuario())};
//                    Cursor cursor = context.getContentResolver().query(contentUri, columns, selection, selectionArgs, null);
//
//                    if(cursor.getCount()>0 && cursor.moveToFirst()) {
//                        usuario.setUsuario(String.valueOf(cursor.getInt(0)));
//                    }
//                }else{
//
//                    ContentValues contentValues = new ContentValues();
//                    contentValues.put(GerenteContract.PerfilEntry.COLUMN_INT_NUM_USUARIO, usuario.getUsuario());
//                    context.getContentResolver().insert(GerenteContract.PerfilEntry.CONTENT_URI, contentValues);
//                }




//                RequestBody cuerpo = new FormBody.Builder()
//                        .add("usuarioId", usuario.getUsuario())
//                        .add("contrasena", usuario.getContra())
//                        .build();
//
//                Request request = new Request.Builder()
//                        .url(RestUrl.REST_ACTION_CONSULTAR_LOGIN)
//                        .post(cuerpo)
//                        .build();
////                Request.Builder builder = new Request.Builder();
////                builder.url(RestUrl.REST_ACTION_CONSULTAR_LOGIN);
////                Request request = builder.build();


