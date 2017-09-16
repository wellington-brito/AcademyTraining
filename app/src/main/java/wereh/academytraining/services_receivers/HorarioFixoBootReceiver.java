package wereh.academytraining.services_receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import wereh.academytraining.entidade.LembretesStatus;
import wereh.academytraining.negocio.LembretesStatusBo;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by wellington on 12/07/17.
 */

public class HorarioFixoBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            try {
                verificarEstadoAlarme(context);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void verificarEstadoAlarme(Context context) throws SQLException {
        LembretesStatusBo lembretesStatusBo = new LembretesStatusBo();
        List<LembretesStatus>listFlags = lembretesStatusBo.buscarFlag(context);
        for (LembretesStatus a : listFlags) {
            if (a.getFlag() && a.getTipo() == 1) {
                configurarAlarmeConsumoAlimentar(context);
            }
            if (a.getFlag() && a.getTipo() == 2){
                configurarAlarmeConsumoAgua(context);
            }
        }
    }

    private void configurarAlarmeConsumoAgua(Context contexto) {
        Intent intent = new Intent("LEMBRETE_CONSUMO_AGUA");
        PendingIntent p = PendingIntent.getBroadcast(contexto, 0, intent, 0);
        Calendar c1 = Calendar.getInstance();
        /**c1.set(Calendar.HOUR_OF_DAY,9);
        c1.set(Calendar.MINUTE, 0);**/
        AlarmManager alarmManager1 = (AlarmManager) contexto.getSystemService(ALARM_SERVICE);
        alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, c1.getTimeInMillis(),3600000,p);  // notification ativo a cada 1 hora
        //alarmManager1.set(AlarmManager.RTC_WAKEUP, c1.getTimeInMillis(),p);
        Toast.makeText(contexto, "Lembrete Consumo de água Ativado novamente a cada 2hr", Toast.LENGTH_SHORT).show();

    }


    public void configurarAlarmeConsumoAlimentar(Context contexto) throws SQLException {
        Intent intent = new Intent("LEMBRETE_CONSUMO_ALIMENTAR_FIXO");
        PendingIntent p = PendingIntent.getBroadcast(contexto, 0, intent, 0);
        Calendar c1 = Calendar.getInstance();
        /**c1.set(Calendar.HOUR_OF_DAY,9);
        c1.set(Calendar.MINUTE, 0);**/
        AlarmManager alarmManager1 = (AlarmManager) contexto.getSystemService(ALARM_SERVICE);
        alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, c1.getTimeInMillis(),10800000,p);     // notification ativo a cada 3 horas 10800000
        /**alarmManager1.set(AlarmManager.RTC_WAKEUP, c1.getTimeInMillis(),p);**/
        Toast.makeText(contexto, "Lembrete Alimentação diária Ativada novamente a cada 3hr", Toast.LENGTH_SHORT).show();
    }
}