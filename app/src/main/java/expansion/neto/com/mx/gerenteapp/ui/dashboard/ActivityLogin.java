package expansion.neto.com.mx.gerenteapp.ui.dashboard;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityLoginBinding;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.gerenteapp.utils.Util;

/**
 * Created by marcosmarroquin on 20/03/18.
 */

public class ActivityLogin extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        permisos();
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
        usuario = new Usuario("", "", this, binding);
        binding.setLoginViewModel(usuario);
        binding.entrar.setEnabled(true);
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
                PackageManager.PERMISSION_GRANTED


                ){
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,

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
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED){

                } else {
                    Toast.makeText(this, "Permisos no completados", Toast.LENGTH_SHORT).show();
                }
        }
    }

}
