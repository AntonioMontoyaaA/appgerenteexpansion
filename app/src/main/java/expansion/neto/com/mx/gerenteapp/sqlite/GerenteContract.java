package expansion.neto.com.mx.gerenteapp.sqlite;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by razp on 23/11/16.
 */

public class GerenteContract {

    public static final String CONTENT_AUTHORITY = "expansion.neto.com.mx.gerenteapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);


    public static final String PATH_RUTA = "perfil";

    public static final class PerfilEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RUTA).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RUTA;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RUTA;

        public static final String TABLE_NAME = PATH_RUTA;


        public static final String COLUMN_INT_NUM_USUARIO = BaseColumns._ID;
        public static final String COLUMN_STR_NOMBRE = "nombre";
        public static final String COLUMN_STR_APELLIDO_P = "apellidoP";
        public static final String COLUMN_STR_APELLIDO_M = "apellidoM";
        public static final String COLUMN_STR_CORREO = "correo";
        public static final String COLUMN_STR_TELEFONO = "telefono";
        public static final String COLUMN_INT_ZONA = "zona";
        public static final String COLUMN_INT_PLAN_MES = "planMes";
        public static final String COLUMN_INT_REAL_MES = "realMes";
        public static final String COLUMN_INT_PLAN_SEMANA = "planSemana";
        public static final String COLUMN_INT_REAL_SEMANA = "realSemana";
        public static final String COLUMN_INT_NUM_AUTORIZADOS = "numAutorizados";


        public static Uri buildAddressUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
