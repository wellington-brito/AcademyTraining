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
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Alimento;
import wereh.academytraining.entidade.AlimentosConsumidos;
import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.exceptions.ObjetoDuplicadoException;
import wereh.academytraining.negocio.AlimentoBo;
import wereh.academytraining.negocio.AlimentosConsumidosBo;
import wereh.academytraining.persistencia.AlimentoDao;
import wereh.academytraining.persistencia.DatabaseHelper;

public class AlimentosListaActivity extends AppCompatActivity {

    private DatabaseHelper dh;
    private AlimentoDao alimentoDao;
    private int idSelected;
    private List<Alimento> alimentosDoGrupoSelecionado;
    public  List<Alimento> lista;
    private ListView mListView;

    final static String SERIES = "series";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final GrupoAlimentar gaFinal = (GrupoAlimentar) getIntent().getSerializableExtra("GrupoAlimentar");
        getSupportActionBar().setTitle(gaFinal.getNomeGrupoAlimentar());

        try {
            verificarBaseDeAlimentos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        inflater.inflate(R.menu.menu_listview_alimentos, menu);
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
                verificaAlimentoEscolhido(alimentosDoGrupoSelecionado.get(info.position));
                Intent intent = new Intent(this, AdicionarAlimentoConsumidos.class);
                intent.putExtra("alimento",(Parcelable) alimentosDoGrupoSelecionado.get(info.position));
                startActivity(intent);
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (ObjetoDuplicadoException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Vocẽ pode editar a lista de alimentos consumidos!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void verificaAlimentoEscolhido(Alimento alimento) throws SQLException {
        AlimentosConsumidosBo alimentosConsumidosBo = new AlimentosConsumidosBo();
        List<AlimentosConsumidos> lista = alimentosConsumidosBo.buscarAlimentosCheckList(this);
        for (AlimentosConsumidos a : lista){
            if (a.getAlimennto().equals(alimento.getNomeAlimento())){
                alimentosConsumidosBo.verificarLista(a, this);
            }
        }
    }


//    public void addAlimentoNaCheckList(Alimento alimento) throws SQLException {
//        AlimentosConsumidos alimentoListaConsumidos = new AlimentosConsumidos();
//        alimentoListaConsumidos.setAlimennto(alimento.getNomeAlimento());
//        alimentoListaConsumidos.setIdAlimento(alimento.getId());
//        AlimentosConsumidosBo alimentosConsumidosBo = new AlimentosConsumidosBo();
//        alimentosConsumidosBo.salvar(alimentoListaConsumidos, this);
//    }



}
