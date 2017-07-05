package wereh.academytraining.apresentacao;

import android.app.Service;
import android.content.Intent;


import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import wereh.academytraining.R;
import wereh.academytraining.apresentacao.services.Servico;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.entidade.Treino;


public class ContadorTreino extends AppCompatActivity {

    public  Chronometer chronometer;
    private static long milliseconds;
    private static long milliseconds2;
    Treino t;
    static int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador_treino);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.chronometer = (Chronometer) findViewById(R.id.chronometer);
        //this.milliseconds = 0;
        //this.milliseconds2 = 0;

        t = (Treino) getIntent().getSerializableExtra("treino");
    }

    public void escolherPlanejamento(View view) {
        Intent i = new Intent(this, PlanejamentoListaActivity.class); //listagem de todos os planejamentos
        startActivityForResult(i, PlanejamentoListaActivity.CODIGO_ACTITIVITY_PLANEJAMENTO);
    }

    //    public void startService(View view) {
    //        Intent i = new Intent(this, Servico.class);
    //        startService(i);
    //        Servico s = new Servico();
    //        s.startCronometer(i, view);
    //    }

    public void startCronometer(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        flag = 1;
        Log.i("Flag startCronometer", Integer.toString( flag));
    }

    public void pauseCronometer(View view) {
        chronometer.stop();
        flag =0;
        milliseconds2 = 0 ;
        milliseconds = 0;

    }

    public void resetCronometer(View view) {
        flag =0;
        milliseconds2 = 0 ;
        milliseconds = 0;
        String tempoGasto = (String) chronometer.getText();
        chronometer.setText("00:00");
        TextView t = (TextView) findViewById(R.id.textView16);
        t.setText(tempoGasto);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (flag == 1) {
            milliseconds2 = milliseconds2 > 0 ? System.currentTimeMillis() - milliseconds2 : 0;
            Log.i("miliseconds", Long.toString(milliseconds2));
            Log.i("miliseconds2", Long.toString(milliseconds2));
            chronometer.setBase(SystemClock.elapsedRealtime() - (milliseconds + milliseconds2));
            chronometer.start();
        }
    }

    @Override
    protected void onRestart() {
        super.onResume();
        if (flag == 1){
            milliseconds2 = System.currentTimeMillis() ;
            milliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
            Log.i("miliseconds", Long.toString(milliseconds2));
            Log.i("miliseconds2", Long.toString(milliseconds2));
        }
    }

    @Override
    protected void onStop() {
        super.onResume();
        if (flag == 1){
            milliseconds2 = System.currentTimeMillis() ;
            milliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
            Log.i("miliseconds", Long.toString(milliseconds2));
            Log.i("miliseconds2", Long.toString(milliseconds2));
        }
    }

    @Override
    protected void onDestroy() {
        super.onResume();
        if (flag == 1){
            milliseconds2 = System.currentTimeMillis() ;
            milliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
            Log.i("miliseconds", Long.toString(milliseconds2));
            Log.i("miliseconds2", Long.toString(milliseconds2));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PlanejamentoListaActivity.CODIGO_ACTITIVITY_PLANEJAMENTO) {
            if (data != null) {
                TextView txt = (TextView) findViewById(R.id.textView16);
                Bundle bulndle = data.getExtras();
                Planejamento planejamento = bulndle.getParcelable("planejamento");
                txt.setText(planejamento.getNomePlanejamento());
            }
        }
    }
}



