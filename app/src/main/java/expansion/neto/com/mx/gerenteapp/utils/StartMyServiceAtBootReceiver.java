package expansion.neto.com.mx.gerenteapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartMyServiceAtBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, ServicioRutas.class);
        context.startService(serviceIntent);
    }
}
