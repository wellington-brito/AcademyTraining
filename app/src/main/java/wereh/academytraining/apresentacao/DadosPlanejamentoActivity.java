package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.apresentacao.fragments.FichaDeTreinoAdapter;
import wereh.academytraining.negocio.TreinoBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.TreinoDao;

public class DadosPlanejamentoActivity extends AppCompatActivity {

    private ListView mListView;
    private DatabaseHelper dh;
    private List<Treino> listaTreinos;
    private TreinoDao treinoDao;
    private Planejamento p ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_planejamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DadosPlanejamentoActivity.this, AdicionarTreinoActivity.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListView = (ListView) findViewById(R.id.listViewFichaDeTreino);
        this.p = (Planejamento) getIntent().getSerializableExtra("planejamento");

    }

    @Override
    public void onResume() {
        try {
            super.onResume();
            this.carregarLista();
            this.carregarDadosPlanejamento();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  void carregarLista() throws SQLException {
        dh = new DatabaseHelper(this);
        this.treinoDao  = new TreinoDao(dh.getConnectionSource());
        try {
            this.listaTreinos = this.treinoDao.queryForEq("idPlanejamento", p.getId() );
            this.mListView = (ListView)findViewById(R.id.listViewFichaDeTreino);
            this.mListView.setAdapter( new FichaDeTreinoAdapter(this, this.listaTreinos));
            registerForContextMenu(this.mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void carregarDadosPlanejamento() {
        TextView planej =  (TextView) findViewById(R.id.textViewDescricaoPlanej);
        TextView objetivo =  (TextView) findViewById(R.id.textViewDescricaoObjetivo);
        TextView diasSemana =  (TextView) findViewById(R.id.txtViewDiasPorSemana);
        TextView dataIni =  (TextView) findViewById(R.id.txtViewDataInicio);
        TextView validade =  (TextView) findViewById(R.id.txtViewValidade);

        planej.setText(p.getNomePlanejamento());
        objetivo.setText(p.getObjetivo());
        diasSemana.setText(Integer.toString(p.getVezesNaSemana()));
        SimpleDateFormat formatt = new SimpleDateFormat("dd-MM-yyyy");
        String data = formatt.format(p.getDataInicio());
        dataIni.setText(data);
        validade.setText(Integer.toString(p.getValidade()));
    }

    //método para carregar o menu de opçoes no item da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(listaTreinos.get(info.position).getNomeExercicio());
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_listview, menu);
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();
        if (id == R.id.action_Menu_Apagar) {
            try {
                TreinoBo treinoBo = new TreinoBo();
                treinoBo.apagarTreino(listaTreinos.get(info.position),this);
                this.carregarLista();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (id == R.id.action_Menu_Alterar) {
                Intent i = new Intent(this, AdicionarTreinoActivity.class);
                i.putExtra("treino", (Parcelable) listaTreinos.get(info.position));
                startActivity(i);
            }

        if (id == R.id.action_Menu_Detalhes) {
            Intent intent = new Intent(this, TreinoDetalhesActivity.class);
            intent.putExtra("treino", (Parcelable) listaTreinos.get(info.position));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
