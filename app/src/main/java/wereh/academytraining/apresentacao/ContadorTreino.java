package wereh.academytraining.apresentacao;

import android.app.Service;
import android.content.Intent;


import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.SystemClock;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.services.Servico;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.entidade.TempoGasto;
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.exceptions.ObjetoDuplicadoException;
import wereh.academytraining.negocio.TempoGastoBo;


public class ContadorTreino extends AppCompatActivity {

    public Chronometer chronometer;
    private Planejamento planejamento;
    static String nomePlanejamento;
    private static long milliseconds;
    private static long milliseconds2;
    private List<TempoGasto> listaTempoGasto;
    private ListView mListView;
    TextView txt;
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
        this.mListView = (ListView) findViewById(R.id.listViewFichaDeTreino);
        t = (Treino) getIntent().getSerializableExtra("treino");
    }

    public void escolherPlanejamento(View view) {
        Intent i = new Intent(this, PlanejamentoListaActivity.class); //listagem de todos os planejamentos
        startActivityForResult(i, PlanejamentoListaActivity.CODIGO_ACTITIVITY_PLANEJAMENTO);
        Log.i("Flag startCronometer", Integer.toString(flag));
    }


    public void startCronometer(View view) {
        if (planejamento != null) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            flag = 1;
            this.nomePlanejamento = this.planejamento.getNomePlanejamento();
            Log.i("Flag startCronometer", Integer.toString(flag));
        } else {
            Toast.makeText(this, "Selecione um Planejamento antes!", Toast.LENGTH_SHORT).show();
        }
    }

    public void pauseCronometer(View view) {
        chronometer.stop();
        flag = 0;
        milliseconds2 = 0;
        milliseconds = 0;
    }

    public void resetCronometer(View view) throws ParseException, SQLException {
        chronometer.stop();
        flag = 0;
        milliseconds2 = 0;
        milliseconds = 0;
        String tempoGasto = (String) chronometer.getText();
        chronometer.setText("00:00");
        salvarTempo(tempoGasto);
        planejamento = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PlanejamentoListaActivity.CODIGO_ACTITIVITY_PLANEJAMENTO) {
            if (data != null) {
                this.txt = (TextView) findViewById(R.id.textView16);
                Bundle bulndle = data.getExtras();
                this.planejamento = bulndle.getParcelable("planejamento");
                this.txt.setText(this.planejamento.getNomePlanejamento());
            }
        }
    }

    private void salvarTempo(String tempoGasto) throws ParseException, SQLException {

        if (this.nomePlanejamento == null && tempoGasto.equals("00:00")){
            Toast.makeText(this, "Impossível salvar", Toast.LENGTH_SHORT).show();
        }else{
            TempoGasto tempoGastoCorrente = new TempoGasto();
            tempoGastoCorrente.setNomePlnejamento(this.nomePlanejamento);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date data = formatter.parse(getDateTime());
            tempoGastoCorrente.setDiaTreino(data);
            tempoGastoCorrente.setTempo(tempoGasto);
            TempoGastoBo tempoGastoBo = new TempoGastoBo();
            tempoGastoBo.salvar(tempoGastoCorrente, this);
            carregarLista();
            this.txt = (TextView) findViewById(R.id.textView16);
            this.txt.setText("Escolha o planejamento para cronometrar hoje");
            Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
            this.nomePlanejamento = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
        if (flag == 1) {
            milliseconds2 = milliseconds2 > 0 ? System.currentTimeMillis() - milliseconds2 : 0;
            chronometer.setBase(SystemClock.elapsedRealtime() - (milliseconds + milliseconds2));
            chronometer.start();
        }
    }

    private void carregarLista() {
        try {
            TempoGastoBo tempoGastoBo = new TempoGastoBo();
            this.listaTempoGasto = tempoGastoBo.buscarTempos(this);
            this.mListView = (ListView) findViewById(R.id.listviewTempoGasto);
            this.mListView.setAdapter(new ContadorTreinoAdapter(this, this.listaTempoGasto));
            registerForContextMenu(mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRestart() {
        super.onResume();
        if (flag == 1) {
            milliseconds2 = System.currentTimeMillis();
            milliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
        }
    }

    @Override
    protected void onStop() {
        super.onResume();
        if (flag == 1) {
            milliseconds2 = System.currentTimeMillis();
            milliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
        }
    }

    @Override
    protected void onDestroy() {
        super.onResume();
        if (flag == 1) {
            milliseconds2 = System.currentTimeMillis();
            milliseconds = SystemClock.elapsedRealtime() - chronometer.getBase();
        }
    }


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    //método para carregar o menu de opçoes no item da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(listaTempoGasto.get(info.position).getNomePlnejamento());
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_listview_contagem_treinos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();
        if (id == R.id.action_Menu_Apagar) {
            try {
                TempoGastoBo tempoGastoBo = new TempoGastoBo();
                tempoGastoBo.apagarTempo(listaTempoGasto.get(info.position), this);
                this.carregarLista();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}



