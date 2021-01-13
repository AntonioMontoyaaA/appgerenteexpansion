package expansion.neto.com.mx.gerenteapp.ui.dashboard;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityLoginBinding;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.gerenteapp.utils.AppHelper;
import expansion.neto.com.mx.gerenteapp.utils.Util;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.VERSION_APP;

/**
 * Created by marcosmarroquin on 20/03/18.
 */

public class ActivityLogin extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Usuario usuario;
    private String idAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        permisos();
        idAndroid = Settings.Secure.getString( getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        initDataBinding();

    }

    /**
     * Método que setea la vista con el binding y setea los tipos de fuente a los textinputlayout
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.scrollBar.setVerticalScrollBarEnabled(false);
        binding.pass.setTypeface(Util.changeFont(this,1));
        binding.usuario.setTypeface(Util.changeFont(this,1));
        binding.entrar.setTypeface(Util.changeFont(this,1));
        binding.txtAppVersion.setText(VERSION_APP);
        usuario = new Usuario("", "", this, binding);
        binding.setLoginViewModel(usuario);
        binding.entrar.setEnabled(true);

        binding.tvIdAndroid.setText( "ID: " + idAndroid);
    }

    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar.make(binding.container,
                    Html.fromHtml("<b><font color=\"#254581\">" +
                            getString(R.string.intentaSalir) +
                            "</font></b>"), Snackbar.LENGTH_SHORT);

            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(getResources().getColor(R.color.snackBar));
            snackbar.show();
        }
        back_pressed = System.currentTimeMillis();
    }

    private final int REQUEST_PERMISSION_ACCESS_NETO = 1;

    public void permisos(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) ==
                PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                 PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED

                ){





        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,

            }, REQUEST_PERMISSION_ACCESS_NETO);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_ACCESS_NETO:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED
                        && grantResults[4] == PackageManager.PERMISSION_GRANTED
                        && grantResults[5] == PackageManager.PERMISSION_GRANTED){




                } else {
                    Toast.makeText(this, "Permisos no completados", Toast.LENGTH_SHORT).show();
                }
        }
    }


    public static boolean canGetLocation(Context context) {
        return isLocationEnabled(context); // application context
    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    public void compartir(View view) {

        Intent intentCompartir = new Intent();
        intentCompartir.setAction(Intent.ACTION_SEND);
        intentCompartir.putExtra(Intent.EXTRA_TEXT, idAndroid);
        intentCompartir.setType("text/plain");
        startActivity(Intent.createChooser(intentCompartir, "Compartir via"));
    }
}
