package expansion.neto.com.mx.gerenteapp.provider.documentosProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import expansion.neto.com.mx.gerenteapp.constantes.RestUrl;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.utils.ScalingUtilities;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.FORMATO_FOTO;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderObtenerUrl {

    private static ProviderObtenerUrl instance;
    Context context;
    String respuesta;
    Codigos callback = null;

    public ProviderObtenerUrl() {
    }

    public static ProviderObtenerUrl getInstance(Context context) {
        if (instance == null) {
            instance = new ProviderObtenerUrl();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerUrl(final String mdId, final String nombreImg, final String formato, final String tipoArchivo,
                           final Uri uri, final ConsultaUrl promise) {
        final OkHttpClient client = new OkHttpClient();

        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {
                    File file;
                    if (tipoArchivo == "1" || tipoArchivo == "0") {
                        String path = decodeFile(uri.getPath());
                        file = new File(path);
                    }else {
                        file = new File(uri.getPath());
                    }

                    SharedPreferences preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    String usuarioId = preferences.getString("usuario", "");

                    RequestBody requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("mdId", mdId)
                            .addFormDataPart("nombreArc", nombreImg)
                            .addFormDataPart("formato", formato)
                            .addFormDataPart("tipoArchivo", tipoArchivo)
                            .addFormDataPart("usuarioId", usuarioId)
                            .addFormDataPart("fecha", Util.getFechaKk())
                            .addFormDataPart("archivo", nombreImg,
                                    RequestBody.create(MediaType.parse("application/octet-stream"), file)).build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_GUARDA_TODO_TIPO_DOCUMENTO)
                            .post(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, Codigos.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (e.getMessage().contains("Failed to connect to")) {
                        callback = new Codigos();
                        callback.setCodigo(1);
                        return callback;
                    } else {
                        callback = new Codigos();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }

            @Override
            protected void onPostExecute(Codigos codigos) {
                promise.resolve(codigos);
            }
        }).execute();
    }

    public interface ConsultaUrl {
        void resolve(Codigos codigo);

        void reject(Exception e);
    }

    private String decodeFile(String path) {
        String strMyImagePath = null;
        Bitmap scaledBitmap = null;

        try {
            // Part 1: obtener el bitmap desde la URI path
            Bitmap unscaledBitmap = ScalingUtilities.decodeFile(path, 1600, 1000, ScalingUtilities.ScalingLogic.FIT);
            if (!(unscaledBitmap.getWidth() <= 800 && unscaledBitmap.getHeight() <= 800)) {
                // Part 2: resize al bitmap
                scaledBitmap = ScalingUtilities.createScaledBitmap(unscaledBitmap, 1600, 1000, ScalingUtilities.ScalingLogic.FIT);
            } else {
                unscaledBitmap.recycle();
                return path;
            }
            // guardamos el archivo temporal
            String extr = Environment.getExternalStorageDirectory().toString();
            File mFolder = new File(extr + "/tiendasNeto");
            if (!mFolder.exists()) {
                mFolder.mkdir();
            }

            String s = "tmp.png";
            File f = new File(mFolder.getAbsolutePath(), s);
            strMyImagePath = f.getAbsolutePath();
            FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(f);
                scaledBitmap.compress(Bitmap.CompressFormat.PNG, 70, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            scaledBitmap.recycle();
        } catch (Throwable e) {
        }

        if (strMyImagePath == null) {
            return path;
        }
        return strMyImagePath;
    }

}