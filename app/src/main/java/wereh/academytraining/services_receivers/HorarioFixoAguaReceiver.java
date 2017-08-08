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
import wereh.academytraining.apresentacao.AlertasActivity;
import wereh.academytraining.apresentacao.DetalhesConsumoAguaActivity;

/**
 * Created by wellington on 26/07/17.
 */

public class HorarioFixoAguaReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        gerarNotificação(context, new Intent(context, AlertasActivity.class), "Nova Mensagem", "AcademyTraining", "Que tal tomar água?");
    }
    private void gerarNotificação(Context context, Intent intent, String s, String título, String s1) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, new Intent(context, DetalhesConsumoAguaActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(s);
        builder.setContentTitle(título);
        builder.setContentText(s1);
        builder.setSmallIcon(R.mipmap.launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.water));
        builder.setContentIntent(p);

        Notification n = builder.build();
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
