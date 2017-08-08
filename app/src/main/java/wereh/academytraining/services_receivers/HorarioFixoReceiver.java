package wereh.academytraining.services_receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.AlimentosConsumidosLista;
import wereh.academytraining.apresentacao.ConfigurarAlarmeActivity;
import wereh.academytraining.apresentacao.AlertasActivity;

/**
 * Created by wellington on 20/07/17.
 */

public class HorarioFixoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        gerarNotificação(context, new Intent(context, AlertasActivity.class), "Nova Mensagem", "AcademyTraining", "Lanche ou Refeição? não deixe para depois!");
    }

    private void gerarNotificação(Context context, Intent intent, String s, String título, String s1) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, new Intent(context, ConfigurarAlarmeActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(s);
        builder.setContentTitle(título);
        builder.setContentText(s1);
        builder.setSmallIcon(R.mipmap.launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.salad));
        builder.setContentIntent(p);

        Notification n = builder.build();
        builder.setPriority(Notification.PRIORITY_HIGH);
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.mipmap.ic_launcher, n);

        try {
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        } catch (Exception e) {
        }

    }
}
