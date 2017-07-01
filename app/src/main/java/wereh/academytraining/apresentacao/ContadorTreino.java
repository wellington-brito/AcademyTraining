package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.stmt.query.In;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.PlanejamentoDao;

import static android.R.id.button3;

public class ContadorTreino extends AppCompatActivity {

    private Chronometer chronometer;
    private long milliseconds;
    Treino t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador_treino);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.chronometer = (Chronometer)findViewById(R.id.chronometer);
        this.chronometer.setFormat("%02s:%02s:%02s");
        this.milliseconds = 0;

        t = (Treino) getIntent().getSerializableExtra("treino");

    }

    public void startCronometer(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    public void escolherPlanejamento(View view) {
        Intent i = new Intent(this, PlanejamentoListaActivity.class); //listagem de todos os planejamentos
        startActivityForResult(i, PlanejamentoListaActivity.CODIGO_ACTITIVITY_PLANEJAMENTO);
    }

    public void pauseCronometer(View view){
       milliseconds = chronometer.getBase();
        chronometer.stop();
    }

    public void resetCronometer(View view){
        chronometer.stop();
        String tempoGasto = (String) chronometer.getText();
        chronometer.setText("00:00");


        TextView t = (TextView) findViewById(R.id.textView16);
        t.setText(tempoGasto);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == PlanejamentoListaActivity.CODIGO_ACTITIVITY_PLANEJAMENTO){
            if(data != null ){
                TextView txt = (TextView) findViewById(R.id.textView16);
                Bundle bulndle = data.getExtras();
                Planejamento planejamento = bulndle.getParcelable("planejamento");
                txt.setText(planejamento.getNomePlanejamento());
            }
        }
    }
}
