package expansion.neto.com.mx.gerenteapp.sqlite;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;


import expansion.neto.com.mx.gerenteapp.sqlite.GerenteContract.*;

public class GerenteDbAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "gerente.db";
    private final Context context;

    public GerenteDbAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    private static final String SQL_CREATE_PERFIL_TABLE = "CREATE TABLE " + PerfilEntry.TABLE_NAME + " ( "
            + PerfilEntry.COLUMN_INT_NUM_USUARIO               + " INTEGER NOT NULL PRIMARY KEY, "
            + PerfilEntry.COLUMN_STR_NOMBRE              + " TEXT, "
            + PerfilEntry.COLUMN_STR_APELLIDO_P  + " TEXT, "
            + PerfilEntry.COLUMN_STR_APELLIDO_M    + " TEXT, "
            + PerfilEntry.COLUMN_STR_CORREO   + " TEXT, "
            + PerfilEntry.COLUMN_STR_TELEFONO      + " TEXT, "
            + PerfilEntry.COLUMN_INT_ZONA       + " INTEGER, "
            + PerfilEntry.COLUMN_INT_PLAN_MES       + " INTEGER, "
            + PerfilEntry.COLUMN_INT_REAL_MES       + " INTEGER, "
            + PerfilEntry.COLUMN_INT_PLAN_SEMANA       + " INTEGER, "
            + PerfilEntry.COLUMN_INT_REAL_SEMANA       + " INTEGER, "
            + PerfilEntry.COLUMN_INT_NUM_AUTORIZADOS     + " INTEGER);";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_PERFIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PerfilEntry.TABLE_NAME);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().clear().apply();
        onCreate(sqLiteDatabase);
    }

}
