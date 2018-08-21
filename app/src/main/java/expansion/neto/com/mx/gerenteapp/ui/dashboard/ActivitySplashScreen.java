package expansion.neto.com.mx.gerenteapp.ui.dashboard;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import expansion.neto.com.mx.gerenteapp.utils.ServicioRutas;
import io.fabric.sdk.android.Fabric;
import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;

import java.util.ArrayList;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.cron.ReminderUtilities;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Permiso;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.gerenteapp.modelView.loginModel.UsuarioLogin;
import expansion.neto.com.mx.gerenteapp.provider.loginProvider.ExpansionGerenteProviderLogin;
import expansion.neto.com.mx.gerenteapp.utils.Util;
import expansion.neto.com.mx.gerenteapp.databinding.ActivitySplashScreenBinding;


/**
 * Clase inicial, que muestra el splash screen
 */
public class ActivitySplashScreen extends AppCompatActivity {

	private ActivitySplashScreenBinding binding;
	private static final int TIME_TO_SHOW = 2000;
	SharedPreferences preferences;
	Snackbar snackbar;
	String usuario;
	String contra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fabric.with(this, new Crashlytics());
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		binding  = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

		View decoracion = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN;
		decoracion.setSystemUiVisibility(uiOptions);

		setAnimation();

		Intent serviceIntent = new Intent(getApplicationContext(), ServicioRutas.class);
		startService(serviceIntent);

		preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
		usuario = preferences.getString("usuario","");
		contra = preferences.getString("contra","");

		Usuario usuarios = new Usuario(usuario, contra, this, null);
		ReminderUtilities.scheduleCronReminder(this);

		if(usuario!=null && !usuario.equals("")){//condición para saber si usuario esta logueado o no, traer variable de sesión de shared preference u sqlite, comprobar también si la constraseña no ha caducado
			compruebaUsuario(usuarios);
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					if(Util.isOnline(ActivitySplashScreen.this)){
						goMainActivity();
					} else {
						snackbar = Snackbar.make(binding.container,
								Html.fromHtml("<b><font color=\"#254581\">" +
										getString(R.string.internet) +
										"</font></b>"), Snackbar.LENGTH_LONG);

						snackbar.setAction(Html.fromHtml("<b><font color=\"#254581\">" +
								getString(R.string.reintentar)  +
								"</font></b>"), new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								if(Util.isOnline(ActivitySplashScreen.this) && !usuario.equals("")){
									goMainActivity();
								}else{
									snackbar = Snackbar.make(binding.container,
											Html.fromHtml("<b><font color=\"#FF20609F\">" +
													getString(R.string.internet) +
													"</font></b>"), Snackbar.LENGTH_LONG);

									snackbar.setAction(Html.fromHtml("<b><font color=\"#254581\">" +
											getString(R.string.reintentar) +
											"</font></b>"), new View.OnClickListener() {
										@Override
										public void onClick(View v) {
											if(Util.isOnline(ActivitySplashScreen.this) && !usuario.equals("")){
												goMainActivity();
											}else{
												snackbar = Snackbar.make(binding.container,
														Html.fromHtml("<b><font color=\"#254581\">" +
																getString(R.string.reintenta) +
																"</font></b>"), Snackbar.LENGTH_LONG);
											}
										}
									});

									View snackBarView = snackbar.getView();
									snackBarView.setBackgroundColor(getResources().getColor(R.color.snackBar));
									snackbar.show();
								}
							}
						});

						View snackBarView = snackbar.getView();
						snackBarView.setBackgroundColor(getResources().getColor(R.color.snackBar));
						snackbar.show();
					}


				}
			}, TIME_TO_SHOW);
		} else {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					if(Util.isOnline(ActivitySplashScreen.this)){
						goLogin();
					} else {
						snackbar = Snackbar.make(binding.container,
								Html.fromHtml("<b><font color=\"#254581\">" +
										getString(R.string.internet) +
										"</font></b>"), Snackbar.LENGTH_LONG);

						snackbar.setAction(Html.fromHtml("<b><font color=\"#254581\">" +
								getString(R.string.reintentar)  +
								"</font></b>"), new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								if(Util.isOnline(ActivitySplashScreen.this)){
									goLogin();
								}else{
									snackbar = Snackbar.make(binding.container,
											Html.fromHtml("<b><font color=\"#254581\">" +
													getString(R.string.internet) +
													"</font></b>"), Snackbar.LENGTH_LONG);

									snackbar.setAction(Html.fromHtml("<b><font color=\"#254581\">" +
											getString(R.string.reintentar) +
											"</font></b>"), new View.OnClickListener() {
										@Override
										public void onClick(View v) {
											if(Util.isOnline(ActivitySplashScreen.this)){
												goLogin();
											}else{

												snackbar = Snackbar.make(binding.container,
														Html.fromHtml("<b><font color=\"#254581\">" +
																getString(R.string.reintenta) +
																"</font></b>"), Snackbar.LENGTH_LONG);

											}
										}
									});

									View snackBarView = snackbar.getView();
									snackBarView.setBackgroundColor(getResources().getColor(R.color.snackBar));
									snackbar.show();
								}
							}
						});

						View snackBarView = snackbar.getView();
						snackBarView.setBackgroundColor(getResources().getColor(R.color.snackBar));
						snackbar.show();
					}


				}
			}, TIME_TO_SHOW);
		}
	}

	/**
	 * método que inicia los métodos para la animación del splash screen
	 */
	private void setAnimation() {
		binding.kenBurnsImages.setImageResource(R.color.colorAccent);
		animation2();
		animation3();
		blockUI();
	}

	/**
	 * animación que trae la imagen de arriba hacía el centro
	 */
	private void animation2() {
		binding.logo.setAlpha(1.0F);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_top_to_center);
		binding.logo.startAnimation(anim);
	}

	/**
	 * animación que trae el texto de alfa a visible
	 */
	private void animation3() {
		ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(binding.welcomeText, "alpha", 0.0F, 1.0F);
		alphaAnimation.setStartDelay(800);
		alphaAnimation.setDuration(400);
		alphaAnimation.start();
	}

	private void goMainActivity() {
		unblockUI();
		Intent main = new Intent(ActivitySplashScreen.this, ActivityMain.class);
		ActivitySplashScreen.this.startActivity(main);
		ActivitySplashScreen.this.finish();
	}


	private void goLogin() {
		unblockUI();
		Intent login = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
		ActivitySplashScreen.this.startActivity(login);
		ActivitySplashScreen.this.finish();
	}

	private void blockUI(){
		Util.addProgressBar(this, binding.container,binding.container.getChildCount()-1 );
	}

	private void unblockUI(){
		binding.container.removeViewAt(binding.container.getChildCount()-2);
	}

	ArrayList<Permiso> permisos;
	/**
	 * Método que regresa el objeto usuario según se encuentre en la BD, también existe condición para saber si el usuario tiene acceso
	 * @param usuario
	 */
	public void compruebaUsuario(final Usuario usuario) {
		ExpansionGerenteProviderLogin.getInstance(this).compruebaLogin(usuario,
				new ExpansionGerenteProviderLogin.ConsultaUsuario() {
					@Override
					public void resolve(UsuarioLogin usuarioLogin) {
						if(usuarioLogin!=null){
							if(usuarioLogin.getCodigo()==200) {
								permisos = new ArrayList<>();
								for(int i=0;i<usuarioLogin.getPerfil().getPerfilesxusuario().get(0).getPermisos().size();i++){
									permisos.add(usuarioLogin.getPerfil().getPerfilesxusuario().get(0).getPermisos().get(i));
								}

								SharedPreferences.Editor editor = preferences.edit();
								Gson gson = new Gson();
								String json = gson.toJson(permisos);
								editor.putString("permisos", json);
								editor.commit();

								unblockUI();
								Usuario.sharedSave(ActivitySplashScreen.this, usuario,
										usuarioLogin);
							}
						}
					}
					@Override
					public void reject(Exception e) {}
				});
	}


}
