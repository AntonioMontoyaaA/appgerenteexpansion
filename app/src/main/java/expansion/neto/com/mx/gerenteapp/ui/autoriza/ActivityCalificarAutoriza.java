package expansion.neto.com.mx.gerenteapp.ui.autoriza;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityAutorizaCalificaBinding;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class ActivityCalificarAutoriza extends AppCompatActivity {

    private ActivityAutorizaCalificaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initDataBinding();
        binding.enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    /**
     * Método que setea la vista con el binding
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_autoriza_califica);
    }

}
