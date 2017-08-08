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

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.adpters.ExerciciosAdapter;
import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.entidade.Usuario;
import wereh.academytraining.exceptions.ExercicioNaoCadastradoPeloUsuario;
import wereh.academytraining.exceptions.UsuarioCadastradoException;
import wereh.academytraining.negocio.ExercicioBo;
import wereh.academytraining.negocio.UsuarioBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.ExercicioDao;

public class ExerciciosListaActivity extends AppCompatActivity {

    public static final int CODIGO_ACTITIVITY_ADICIONAR_TREINO = 2;
    List<Exercicio> exerciciosDoGrupoSelecionado;
    private ListView mListView;
    private DatabaseHelper dh;
    private ExercicioDao exercicioDao;
    private int idSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarListaExercicios);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final GrupoMuscular gmFinal = (GrupoMuscular) getIntent().getSerializableExtra("GrupoMusculares");
        getSupportActionBar().setTitle(gmFinal.getNomeGrupoMuscular());

        try {
            verificarBaseDeExercicios(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //cadastrar novo exercicio
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Usuario u = buscarUsuario();
                    Intent i = new Intent(ExerciciosListaActivity.this, AdicionarExercicioActivity.class);
                    i.putExtra("usuario", (Parcelable) u);
                    i.putExtra("idGrupo", (Parcelable) gmFinal);
                    startActivity(i);
                } catch (SQLException e) {
                    e.printStackTrace();
                }catch (UsuarioCadastradoException u){
                    Toast.makeText(ExerciciosListaActivity.this, u.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

//        if(gmFinal.getNomeGrupoMuscular().equals("Peitoral")){
//            getSupportActionBar().setLogo(R.drawable.ic_peitoral);
//        }else if (gmFinal.getNomeGrupoMuscular().equals("Bíceps")){
//            getSupportActionBar().setLogo(R.drawable.ic_biceps);
//        }

        idSelected = gmFinal.getId();
        // positionSelected = getIntent().getIntExtra("POSITION", 0);
        mListView = (ListView) findViewById(R.id.listViewExercicios);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("exercicio", (Parcelable) exerciciosDoGrupoSelecionado.get(position));
                setResult(CODIGO_ACTITIVITY_ADICIONAR_TREINO,intent);
                finish();
            }
        });
    }

    private void verificarBaseDeExercicios(ExerciciosListaActivity exerciciosListaActivity) throws SQLException {
        this.dh = new DatabaseHelper(this);
        this.exercicioDao  = new ExercicioDao(dh.getConnectionSource());

        if(this.exercicioDao.queryForAll().isEmpty()|| this.exercicioDao.queryForAll() == null){
            ExercicioBo exercicioBo = new ExercicioBo();
            exercicioBo.cadastrarExercicios(this);
        }
    }

    // cadastrar novo exercicio
    private Usuario buscarUsuario() throws SQLException {
        UsuarioBo usuarioBo = new UsuarioBo();
        return usuarioBo.buscarUsuario(this);
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

    public  void carregarLista() throws SQLException {
        this.dh = new DatabaseHelper(this);
        this.exercicioDao  = new ExercicioDao(dh.getConnectionSource());
        try {
            QueryBuilder<Exercicio, Integer> queryBuilder = exercicioDao.queryBuilder();
            queryBuilder.where().eq(Exercicio.IDGRUPOMUSCULAR_FIELD_NAME, idSelected);
            PreparedQuery<Exercicio> preparedQuery = queryBuilder.prepare();
            exerciciosDoGrupoSelecionado = exercicioDao.query(preparedQuery);
            mListView = (ListView) findViewById(R.id.listViewExercicios);
            mListView.setAdapter( new ExerciciosAdapter(this, exerciciosDoGrupoSelecionado));
            registerForContextMenu(mListView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //método para carregar o menu de opçoes no item da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(exerciciosDoGrupoSelecionado.get(info.position).getNomeExercicio());
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_listview_planej, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int id = item.getItemId();

        if (id == R.id.action_Menu_Apagar) {
            try {
                ExercicioBo exercicioBo = new ExercicioBo();
                exercicioBo.apagarExercicio(exerciciosDoGrupoSelecionado.get(info.position),this);
                this.carregarLista();
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (ExercicioNaoCadastradoPeloUsuario d ){
                Toast.makeText(this, d.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        if (id == R.id.action_Menu_Alterar) {
            ExercicioBo exercicioBo = new ExercicioBo();
            try {
                exercicioBo.verificarExercicioCadastradoPeloUsuario(exerciciosDoGrupoSelecionado.get(info.position), this);
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (ExercicioNaoCadastradoPeloUsuario e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        if (id == R.id.action_Menu_Detalhes) {
            Intent intent = new Intent(this, ExercicioDetalhesActivity.class);
            intent.putExtra("exercicio", (Parcelable) exerciciosDoGrupoSelecionado.get(info.position));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}