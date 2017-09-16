package wereh.academytraining.apresentacao;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.LembretesStatus;
import wereh.academytraining.negocio.LembretesStatusBo;

public class ConfigurarAlarmeActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private TextView time;
    private Calendar calendar;
    private String format = "";
    LembretesStatus lembretesStatus;
    LembretesStatusBo lembretesStatusBo;
    List<LembretesStatus> listFlags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_alarme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        timePicker = (TimePicker) findViewById(R.id.timePicker1);
        timePicker.setIs24HourView(true);
        time = (TextView) findViewById(R.id.textView1);
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    setTime();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setTime() throws SQLException {
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();
        showTime(hour, min);
        //desativaNotificacaoAlimentacao();
        ativaNotificacaoAlimentacao(hour, min);
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        time.setText(new StringBuilder().append(hour).append(" : ").append(min).append(" ").append(format));
    }

    public void ativaNotificacaoAlimentacao(int hour, int min) throws SQLException {
        Intent intent = new Intent("LEMBRETE_CONSUMO_ALIMENTAR_USUARIO_DISPARADO");
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.HOUR_OF_DAY, hour);
        c1.set(Calendar.MINUTE, min);
        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager1.set(AlarmManager.RTC_WAKEUP, c1.getTimeInMillis(),p);
        this.lembretesStatus = new LembretesStatus();
        this.lembretesStatus.setFlag(true);
        this.lembretesStatusBo = new LembretesStatusBo();
        this.lembretesStatusBo.salvar(lembretesStatus, this);
        Toast.makeText(this, "Você será alertado às "+hour+"horas e "+min+" minutos", Toast.LENGTH_SHORT).show();
        this.finish();
    }
    /**public void desativaNotificacaoAlimentacao() throws SQLException {
        Intent intent = new Intent("ALARME_DISPARADO");
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager1.cancel(p);
        apagarStatusLembrete();
        Toast.makeText(this, "Lembrete a cada 3 horas Desativado", Toast.LENGTH_SHORT).show();
    }

    private void apagarStatusLembrete() throws SQLException{
        this.lembretesStatus = new LembretesStatus();
        this.lembretesStatusBo = new LembretesStatusBo();
        this.listFlags = this.lembretesStatusBo.buscarFlag(this);
        for (LembretesStatus a : this.listFlags) {
            if (a.getFlag() == true && a.getTipo() == 1) {
                this.lembretesStatus.setFlag(a.getFlag());
                this.lembretesStatus.setId(a.getId());
                this.lembretesStatus.setTipo(a.getTipo());
                this.lembretesStatusBo = new LembretesStatusBo();
                this.lembretesStatusBo.apagar(this.lembretesStatus, this);
            }
        }
    }**/

}
