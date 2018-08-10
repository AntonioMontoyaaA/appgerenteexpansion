package expansion.neto.com.mx.gerenteapp.sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import expansion.neto.com.mx.gerenteapp.sqlite.GerenteContract.*;


public class GerenteProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = buildUriMatcher();
    private GerenteDbAdapter lidebetaDbAdapter;

    static final int PERFIL = 100;

    @Override
    public String getType(Uri uri) {

        return "";
    }

    @Override
    public boolean onCreate() {
        lidebetaDbAdapter = new GerenteDbAdapter(getContext());

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        final int match = uriMatcher.match(uri);
        switch (match) {
            case PERFIL: {
                retCursor = lidebetaDbAdapter.getReadableDatabase().query(
                        PerfilEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = lidebetaDbAdapter.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case PERFIL: {
                long _id = db.insertWithOnConflict(
                        PerfilEntry.TABLE_NAME,
                        null,
                        values,
                        SQLiteDatabase.CONFLICT_REPLACE
                );
                if ( _id > 0 )
                    returnUri = PerfilEntry.buildAddressUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        {
            final SQLiteDatabase db = lidebetaDbAdapter.getWritableDatabase();
            final int match = uriMatcher.match(uri);
            int returnCount = 0;

            switch (match) {
                case PERFIL:
                    db.beginTransaction();
                    try {
                        for (ContentValues value : values) {
                            long _id = db.insertWithOnConflict(
                                    PerfilEntry.TABLE_NAME,
                                    null,
                                    value,
                                    SQLiteDatabase.CONFLICT_REPLACE
                            );
                            if (_id != -1) {
                                returnCount++;
                            }
                        }
                        db.setTransactionSuccessful();
                    } finally {
                        db.endTransaction();
                    }
                    getContext().getContentResolver().notifyChange(uri, null);
                    return returnCount;
                default:
                    return super.bulkInsert(uri, values);
            }
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = lidebetaDbAdapter.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if ( null == selection ) selection = "1";
        switch (match) {
            case PERFIL:
                rowsDeleted = db.delete(PerfilEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        //Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = lidebetaDbAdapter.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case PERFIL:
                rowsUpdated = db.update(PerfilEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(GerenteContract.CONTENT_AUTHORITY, GerenteContract.PATH_RUTA, PERFIL);
        return matcher;
    }
}
