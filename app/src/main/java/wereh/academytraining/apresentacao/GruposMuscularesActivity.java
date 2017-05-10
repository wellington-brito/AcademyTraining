package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.GrupoMuscularDao;

public class GruposMuscularesActivity extends AppCompatActivity {

    private List<GrupoMuscular> listaGruposMusculares;
    private ListView mListView;
    private DatabaseHelper dh;
    private GrupoMuscularDao grupoMuscularDao;
    private GrupoMuscular grupoMuscular;

    public static final int CODIGO_ACTITIVITY_GRUPOS_MUSCULARES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos_musculares);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGruposMusculares);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.listViewGruposMusculares);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // se for a partir de um fragment
                // Intent intent = new Intent(getActivity(), /* activity a ser chamada */ DetailsActivity.class);

                // se for a partir de uma activity
                Intent intent = new Intent(view.getContext(), /* activity a ser chamada */ ExerciciosListaActivity.class);
                // set no intent o id ou a position do item selecionado
                intent.putExtra("GrupoMusculares", (Parcelable) listaGruposMusculares.get(position));
                //intent.putExtra("ID", id);
                //intent.putExtra("POSITION", position);
                startActivityForResult(intent,2);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        try {
            dh = new DatabaseHelper(GruposMuscularesActivity.this);
            grupoMuscularDao = new GrupoMuscularDao(dh.getConnectionSource());
            listaGruposMusculares = new ArrayList<GrupoMuscular>();


            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Peitoral");
            //grupoMuscular.setQuantidadeExercicios();
            listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Bíceps");
            //grupoMuscular.setQuantidadeExercicios();
            listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Tríceps");
            //grupoMuscular.setQuantidadeExercicios();
            listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Costas");
            //grupoMuscular.setQuantidadeExercicios();
            listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Deltóides");
            //grupoMuscular.setQuantidadeExercicios();
            listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Panturrílhas");
            //grupoMuscular.setQuantidadeExercicios();
            listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Quadríceps");
            //grupoMuscular.setQuantidadeExercicios();
            listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Abdominais");
            //grupoMuscular.setQuantidadeExercicios();
            listaGruposMusculares.add(grupoMuscular);

            for (GrupoMuscular gm : listaGruposMusculares) {
                grupoMuscularDao.create(gm);
            }

            grupoMuscularDao = null;

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == ExerciciosListaActivity.CODIGO_ACTITIVITY_ADICIONAR_TREINO){

            Toast.makeText(this, "passou Grupo", Toast.LENGTH_SHORT).show();
            if(data != null ){
                setResult(CODIGO_ACTITIVITY_GRUPOS_MUSCULARES,data);
                finish();
            }
        }
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
//        ArrayAdapter<GrupoMuscular> adapter;
//        int adapterLayout = android.R.layout.simple_list_item_1;
        grupoMuscularDao  = new GrupoMuscularDao(dh.getConnectionSource());

        try {
            listaGruposMusculares = grupoMuscularDao.queryForAll();
            //adapter = new ArrayAdapter<GrupoMuscular>(this, adapterLayout, listaGruposMusculares);
            mListView = (ListView) findViewById(R.id.listViewGruposMusculares);
            mListView.setAdapter( new GruposMuscularesAdapter(this, listaGruposMusculares));
            //registerForContextMenu(mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}