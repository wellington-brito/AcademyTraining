package wereh.academytraining.apresentacao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Alimento;
import wereh.academytraining.entidade.CheckList;
import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.negocio.AlimentoBo;
import wereh.academytraining.negocio.CheckListBo;
import wereh.academytraining.persistencia.AlimentoDao;
import wereh.academytraining.persistencia.DatabaseHelper;

public class AlimentosListaActivity extends AppCompatActivity {

    private DatabaseHelper dh;
    private AlimentoDao alimentoDao;
    private int idSelected;
    private List<Alimento> alimentosDoGrupoSelecionado;
    public  List<Alimento> lista;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final GrupoAlimentar gaFinal = (GrupoAlimentar) getIntent().getSerializableExtra("GrupoAlimentar");
        getSupportActionBar().setTitle(gaFinal.getNomeGrupoAlimentar());

        try {
            verificarBaseDeAlimentos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.idSelected = gaFinal.getId();

    }


    @Override
    public void onResume() {
        try {
            super.onResume();
            this.carregarLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verificarBaseDeAlimentos() throws SQLException {
        this.dh = new DatabaseHelper(this);
        this.alimentoDao = new AlimentoDao(dh.getConnectionSource());
        this.alimentosDoGrupoSelecionado = this.alimentoDao.queryForAll();
        if(this.alimentosDoGrupoSelecionado.isEmpty() || this.alimentosDoGrupoSelecionado == null || this.alimentosDoGrupoSelecionado.size() == 0){
            AlimentoBo alimentoBo = new AlimentoBo();
            alimentoBo.cadastrarAlimentos(this);
        }
    }

    public  void carregarLista() throws SQLException {
        AlimentoBo alimentoBo = new AlimentoBo();
        this.alimentosDoGrupoSelecionado = alimentoBo.carregarLista(this,this.idSelected);
        this.mListView = (ListView) findViewById(R.id.listViewAlimentos);
        this.mListView.setAdapter( new AlimentosListaAdapter(this,  alimentoBo.carregarLista(this,this.idSelected)));
        registerForContextMenu(mListView);
    }


    //método para carregar o menu de opçoes no item da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(alimentosDoGrupoSelecionado.get(info.position).getNomeAlimento());
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_listview, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int id = item.getItemId();

//        if (id == R.id.action_Menu_Apagar) {
//            try {
//                ExercicioBo exercicioBo = new ExercicioBo();
//                exercicioBo.apagarExercicio(alimentosDoGrupoSelecionado.get(info.position),this);
//                this.carregarLista();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }catch (ExercicioNaoCadastradoPeloUsuario d ){
//                Toast.makeText(this, d.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }

//        if (id == R.id.action_Menu_Alterar) {
//            ExercicioBo exercicioBo = new ExercicioBo();
//            try {
//                exercicioBo.verificarExercicioCadastradoPeloUsuario(exerciciosDoGrupoSelecionado.get(info.position), this);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }catch (ExercicioNaoCadastradoPeloUsuario e){
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        }

//        if (id == R.id.action_Menu_Detalhes) {
//            Intent intent = new Intent(this, ExercicioDetalhesActivity.class);
//            intent.putExtra("exercicio", (Parcelable) exerciciosDoGrupoSelecionado.get(info.position));
//            startActivity(intent);
//        }

        if (id == R.id.action_Menu_AddCheckList) {
            try {
                addAlimentoNaCheckList(alimentosDoGrupoSelecionado.get(info.position));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return super.onOptionsItemSelected(item);
    }


    public void addAlimentoNaCheckList(Alimento alimento) throws SQLException {
        CheckList checkList = new CheckList();
        checkList.setNomeAlimennto(alimento.getNomeAlimento());
        checkList.setIdAlimento(alimento.getId());
        CheckListBo checkListBo = new CheckListBo();
        checkListBo.salvar(checkList, this);
    }
}
