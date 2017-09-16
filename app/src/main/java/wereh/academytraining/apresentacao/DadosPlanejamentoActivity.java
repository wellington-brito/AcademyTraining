package wereh.academytraining.apresentacao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.apresentacao.adpters.FichaDeTreinoAdapter;
import wereh.academytraining.negocio.TempoGastoBo;
import wereh.academytraining.negocio.TreinoBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.TreinoDao;

public class DadosPlanejamentoActivity extends AppCompatActivity {

    private ListView mListView;
    private DatabaseHelper dh;
    private List<Treino> listaTreinos;
    private TreinoDao treinoDao;
    private Planejamento p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_planejamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListView = (ListView) findViewById(R.id.listViewFichaDeTreino);
        this.p = (Planejamento) getIntent().getSerializableExtra("planejamento");

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p.getStatus().equals("Ativo")) {
                    Intent i = new Intent(DadosPlanejamentoActivity.this, AdicionarTreinoActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(DadosPlanejamentoActivity.this, "Ative este planejamento para poder adicionar um treino!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (p.getObjetivo().equals("Hipertrofia")) {
            Button button = (Button) findViewById(R.id.buttonProtocolos);
            button.setEnabled(false);
        }else{
            Button button = (Button) findViewById(R.id.buttonProtocolos);
            button.setText("Protocolos HIIT");
            button.setEnabled(true);
        }


        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), TreinoDetalhesActivity.class);
                intent.putExtra("treino", (Parcelable) listaTreinos.get(position));
                startActivity(intent);
            }
        });
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


    public void carregarLista() throws SQLException {

        TreinoBo treinoBo = new TreinoBo();

        try {
            this.listaTreinos = treinoBo.buscarTreinos(this, "idPlanejamento", p.getId());
            this.mListView = (ListView) findViewById(R.id.listViewFichaDeTreino);
            this.mListView.setAdapter(new FichaDeTreinoAdapter(this, this.listaTreinos));
            registerForContextMenu(mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }


//        dh = new DatabaseHelper(this);
//        this.treinoDao = new TreinoDao(dh.getConnectionSource());
//        try {
//            this.listaTreinos = this.treinoDao.queryForEq("idPlanejamento", p.getId());
//            this.mListView = (ListView) findViewById(R.id.listViewFichaDeTreino);
//            this.mListView.setAdapter(new FichaDeTreinoAdapter(this, this.listaTreinos));
//            registerForContextMenu(this.mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void carregarDadosPlanejamento() {
        TextView planej = (TextView) findViewById(R.id.textViewDescricaoPlanej);
        TextView objetivo = (TextView) findViewById(R.id.textViewDescricaoObjetivo);
        TextView diasSemana = (TextView) findViewById(R.id.txtViewDiasPorSemana);
        TextView dataIni = (TextView) findViewById(R.id.txtViewDataInicio);
        TextView validade = (TextView) findViewById(R.id.txtViewValidade);

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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(listaTreinos.get(info.position).getNomeExercicio());
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_listview_planej, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();
        if (id == R.id.action_Menu_Apagar) {
            new AlertDialog.Builder(this)
                    .setIcon(R.mipmap.ic_delete_black_24dp)
                    .setTitle("Apagando Treino")
                    .setMessage("Tem certeza ?")
                    .setPositiveButton("Sim",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    try {
                                        TreinoBo treinoBo = new TreinoBo();
                                        treinoBo.apagarTreino(listaTreinos.get(info.position), DadosPlanejamentoActivity.this);
                                       carregarLista();
                                        Toast.makeText(DadosPlanejamentoActivity.this, "Treino Apagado!", Toast.LENGTH_SHORT).show();

                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                    .setNegativeButton("Não", null)
                    .show();
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

    public void carregarProtocolos(View view){
        Bundle bundle  = new Bundle();
        bundle.putInt("sistemaTreino", 1);
        Intent i = new Intent(this, ProtocolosHiitListaActiivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

}
