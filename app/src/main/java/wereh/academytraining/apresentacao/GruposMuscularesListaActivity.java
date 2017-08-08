package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.adpters.GruposMuscularesAdapter;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.negocio.GruposMuscularesBo;
import wereh.academytraining.persistencia.DatabaseHelper;

public class GruposMuscularesListaActivity extends AppCompatActivity {

    private List<GrupoMuscular> listaGruposMusculares;
    private ListView mListView;
    private DatabaseHelper dh;
    private GruposMuscularesBo grupoMuscularBo;
    private GrupoMuscular grupoMuscular;

    public static final int CODIGO_ACTITIVITY_GRUPOS_MUSCULARES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos_musculares);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGruposMusculares);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.mListView = (ListView) findViewById(R.id.listViewGruposMusculares);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ExerciciosListaActivity.class);
                intent.putExtra("GrupoMusculares", (Parcelable) listaGruposMusculares.get(position));
                startActivityForResult(intent,2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == ExerciciosListaActivity.CODIGO_ACTITIVITY_ADICIONAR_TREINO){
           // Toast.makeText(this, "passou Grupo", Toast.LENGTH_SHORT).show();
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
//       this.grupoMuscularDao  = new GrupoMuscularDao(this.dh.getConnectionSource());
        this.grupoMuscularBo = new GruposMuscularesBo();
        try {
            this.listaGruposMusculares = this.grupoMuscularBo.buscarGrupos(this);
            mListView = (ListView) findViewById(R.id.listViewGruposMusculares);
            mListView.setAdapter( new GruposMuscularesAdapter(this, listaGruposMusculares));
            //registerForContextMenu(mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}