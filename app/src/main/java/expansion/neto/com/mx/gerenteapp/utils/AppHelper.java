package expansion.neto.com.mx.gerenteapp.utils;

import android.app.Application;

/**
 * Created by hsamano on 27/06/17.
 * Clase que extiende de Android Aplication
 * para el uso de los helpers
 */

public class AppHelper extends Application {

    public static final String TAG = AppHelper.class.getSimpleName();

    private GoogleApiHelper googleApiHelper;
    private static  AppHelper mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        googleApiHelper = new GoogleApiHelper(mInstance);
    }

    public static synchronized AppHelper getInstance() {
        return mInstance;
    }

    public GoogleApiHelper getGoogleApiHelperInstance() {
        return  this.googleApiHelper;
    }
    public static GoogleApiHelper createGoogleApliHelper() {
        GoogleApiHelper googleApiHelper = null;
        try {
            googleApiHelper = getInstance().getGoogleApiHelperInstance();
        } catch (Exception e) {
           // FirebaseCrash.report(new Exception(TAG + "createGoogleApliHelper" + e.getMessage()));
        }
        return googleApiHelper;
    }
}
