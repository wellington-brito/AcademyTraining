package wereh.academytraining.apresentacao;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.LembretesStatus;
import wereh.academytraining.exceptions.LembreteInexistenteException;
import wereh.academytraining.negocio.LembretesStatusBo;

public class AlertasActivity extends AppCompatActivity {
    boolean alarmeAtivo;
    LembretesStatus lembretesStatus;
    LembretesStatusBo lembretesStatusBo;
    List<LembretesStatus> listFlags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button buttonDesativar = (Button) findViewById(R.id.buttonDesativar);
        Button buttonDesativar2 = (Button) findViewById(R.id.buttonDesativar2);
        buttonDesativar.setEnabled(false);
        buttonDesativar2.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            this.lembretesStatusBo = new LembretesStatusBo();
            this.listFlags = this.lembretesStatusBo.buscarFlag(this);
            Button buttonAtivar = (Button) findViewById(R.id.buttonAtivar);
            Button buttonDesativar = (Button) findViewById(R.id.buttonDesativar);
            Button buttonAtivar2 = (Button) findViewById(R.id.buttonAtivar2);
            Button buttonDesativar2 = (Button) findViewById(R.id.buttonDesativar2);
            for (LembretesStatus a : listFlags) {
                if (a.getFlag()== true && a.getTipo() == 1) {
                    buttonAtivar.setEnabled(false);
                    buttonDesativar.setEnabled(true);
                }
                if (a.getFlag()== true && a.getTipo() == 2) {
                    buttonAtivar2.setEnabled(false);
                    buttonDesativar2.setEnabled(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            this.lembretesStatusBo = new LembretesStatusBo();
            this.listFlags = this.lembretesStatusBo.buscarFlag(this);
            Button buttonAtivar = (Button) findViewById(R.id.buttonAtivar);
            Button buttonDesativar = (Button) findViewById(R.id.buttonDesativar);
            Button buttonAtivar2 = (Button) findViewById(R.id.buttonAtivar2);
            Button buttonDesativar2 = (Button) findViewById(R.id.buttonDesativar2);
            for (LembretesStatus a : listFlags) {
                if (a.getFlag()== true && a.getTipo() == 1) {
                    buttonAtivar.setEnabled(false);
                    buttonDesativar.setEnabled(true);
                }
                if (a.getFlag()== true && a.getTipo() == 2) {
                    buttonAtivar2.setEnabled(false);
                    buttonDesativar2.setEnabled(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ativaNotificacaoAlimentacao(View view) throws SQLException {
        Intent intent = new Intent("LEMBRETE_CONSUMO_ALIMENTAR_FIXO");
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);
        Calendar c1 = Calendar.getInstance();
        /**c1.set(Calendar.HOUR_OF_DAY, 9);
        c1.set(Calendar.MINUTE, 0);**/
        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, c1.getTimeInMillis(),10800000,p);               // notification ativo a cada 3 horas 10800000
        /**alarmManager1.set(AlarmManager.RTC_WAKEUP, c1.getTimeInMillis(), p);**/
        criarStatusLembrete(view);
        configurarButtonsAoAtivarLembretes(view);
        Toast.makeText(this, "Você receberá um lembrete sempre às 9hr da manhã", Toast.LENGTH_SHORT).show();
    }

    public void ativaNotificacaoAgua(View view) throws SQLException {
        Intent intent = new Intent("LEMBRETE_CONSUMO_AGUA");
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);
        Calendar c1 = Calendar.getInstance();
        /**c1.set(Calendar.HOUR_OF_DAY, 9);
         c1.set(Calendar.MINUTE, 0);**/
        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager1.setRepeating(AlarmManager.RTC_WAKEUP, c1.getTimeInMillis(),10800000,p);               // notification ativo a cada 3 horas 10800000
        /**alarmManager1.set(AlarmManager.RTC_WAKEUP, c1.getTimeInMillis(), p);**/
        criarStatusLembrete(view);
        configurarButtonsAoAtivarLembretes(view);
        Toast.makeText(this, "Você receberá um lembrete a cada 3hr", Toast.LENGTH_SHORT).show();
    }


    private void criarStatusLembrete(View view) throws SQLException {
        this.lembretesStatus = new LembretesStatus();
        if (view.getId() == R.id.buttonAtivar) { // tipo 1 consumo alimentar
            this.lembretesStatus.setTipo(1);
        }
        if (view.getId() == R.id.buttonAtivar2) { // tipo 2 consumo de água
            this.lembretesStatus.setTipo(2);
        }
        this.lembretesStatus.setFlag(true);
        this.lembretesStatusBo = new LembretesStatusBo();
        this.lembretesStatusBo.salvar(lembretesStatus, this);                                      // flag identificar se o lembrete foi ativado
    }

    private void configurarButtonsAoAtivarLembretes(View view) {
        if (view.getId() == R.id.buttonAtivar) { // tipo 1 consumo alimentar
            Button buttonAtivar = (Button) findViewById(R.id.buttonAtivar);
            Button buttonDesativar = (Button) findViewById(R.id.buttonDesativar);
            buttonAtivar.setEnabled(false);
            buttonDesativar.setEnabled(true);
        }
        if (view.getId() == R.id.buttonAtivar2) { // tipo 2 consumo de água
            Button buttonAtivar = (Button) findViewById(R.id.buttonAtivar2);
            Button buttonDesativar = (Button) findViewById(R.id.buttonDesativar2);
            buttonAtivar.setEnabled(false);
            buttonDesativar.setEnabled(true);
        }
    }

    public void desativaNotificacaoAlimentacao(View view) throws SQLException {
        Intent intent = new Intent("LEMBRETE_CONSUMO_ALIMENTAR_FIXO");
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager1.cancel(p);
        configurarButtonsAoDesativarLembretes(view);
        apagarStatusLembrete(view);
        Toast.makeText(this, "Lembrete Desativado", Toast.LENGTH_SHORT).show();
    }

    public void desativaNotificacaoAgua(View view) throws SQLException {
        Intent intent = new Intent("LEMBRETE_CONSUMO_AGUA");
        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager1.cancel(p);
        configurarButtonsAoDesativarLembretes(view);
        apagarStatusLembrete(view);
        Toast.makeText(this, "Lembrete Desativado", Toast.LENGTH_SHORT).show();

    }

    private void apagarStatusLembrete(View view) throws SQLException {
        try {
            this.lembretesStatusBo = new LembretesStatusBo();
            this.lembretesStatus = new LembretesStatus();
            for (LembretesStatus a : this.listFlags) {
                if (a.getFlag() == true && view.getId() == R.id.buttonDesativar && a.getTipo() == 1) {
                    this.lembretesStatusBo.apagar(a.getId(), this);
                }
                if (a.getFlag() == true && view.getId() == R.id.buttonDesativar2 && a.getTipo() == 2) {
                    this.lembretesStatusBo.apagar(a.getId(), this);
                }
            }
        } catch (LembreteInexistenteException l) {
            Toast.makeText(this, l.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void configurarButtonsAoDesativarLembretes(View view) {
        if (view.getId() == R.id.buttonDesativar) { // tipo 1 consumo alimentar
            Button buttonAtivar = (Button) findViewById(R.id.buttonAtivar);
            Button buttonDesativar = (Button) findViewById(R.id.buttonDesativar);
            buttonAtivar.setEnabled(true);
            buttonDesativar.setEnabled(false);
        }
        if (view.getId() == R.id.buttonDesativar2) { // tipo 2 consumo agua
            Button buttonAtivar = (Button) findViewById(R.id.buttonAtivar2);
            Button buttonDesativar = (Button) findViewById(R.id.buttonDesativar2);
            buttonAtivar.setEnabled(true);
            buttonDesativar.setEnabled(false);
        }
    }

    public void carregarDetalhesConsumoAgua(View view){
        Intent i = new Intent(this, DetalhesConsumoAgua.class);
        startActivity(i);
    }
}
