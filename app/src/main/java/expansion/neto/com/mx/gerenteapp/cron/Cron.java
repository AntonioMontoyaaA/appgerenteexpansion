package expansion.neto.com.mx.gerenteapp.cron;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAgenda.FragmentInicioAgenda;
import expansion.neto.com.mx.gerenteapp.modelView.agendaModel.Notificaciones;
import expansion.neto.com.mx.gerenteapp.provider.agendaProvider.ProviderObtieneNotificaciones;
import expansion.neto.com.mx.gerenteapp.ui.agenda.ActivityNotificaciones;

import static expansion.neto.com.mx.gerenteapp.constantes.RestUrl.TIPO_NOTIFICACION;

public class Cron extends JobService {

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        call(this);
        jobFinished(jobParameters, false);
        return true;
    }


    public void call(Context context) {
        //TODO MAKE NOTIFICATIONS UPDATE!
         getNotificaciones();
    }

    public void getNotificaciones(){
        final SharedPreferences preferences = this.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuarioId = preferences.getString("usuario", "");
        final SharedPreferences.Editor editor = preferences.edit();

        ProviderObtieneNotificaciones.getInstance(getApplicationContext()).obtenerNotificaciones(usuarioId, TIPO_NOTIFICACION, new
                ProviderObtieneNotificaciones.InterfaceObtieneNotificaciones() {
            @Override
            public void resolve(Notificaciones eventos) {
                if(eventos!=null ){
                        if (eventos.getCodigo()==200 && eventos.getTotalNotificaciones()>0){
                        int noti = preferences.getInt("notificaciones", 0);

                        if(noti == eventos.getTotalNotificaciones()){

                        }else{
                            //sendNotification("Notificación", eventos.getNotificaciones().get(0).getMensaje());
                            createNotification("Notificación", eventos.getNotificaciones().get(0).getMensaje());
                            editor.putInt("notificaciones", eventos.getTotalNotificaciones());
                            editor.apply();
                        }


                    }
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }


    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    public void createNotification(String title, String message) {

        Intent resultIntent = new Intent(getApplicationContext() , ActivityNotificaciones.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0 /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(getApplicationContext());
        mBuilder.setSmallIcon(R.drawable.logo);
        mBuilder.setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(false)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(0 /* Request Code */, mBuilder.build());
    }


    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}