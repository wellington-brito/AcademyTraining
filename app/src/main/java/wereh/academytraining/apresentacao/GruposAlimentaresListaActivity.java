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
import wereh.academytraining.apresentacao.adpters.GruposAlimentaresAdapter;
import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.negocio.GrupoAlimentarBo;
import wereh.academytraining.persistencia.DatabaseHelper;

public class GruposAlimentaresListaActivity extends AppCompatActivity {

    private List<GrupoAlimentar> listaGruposAlimentares;
    private ListView mListView;
    private DatabaseHelper dh;
    private GrupoAlimentarBo grupoAlimentarBo;
    private GrupoAlimentar grupoAlimentar;

    public static final int CODIGO_ACTITIVITY_GRUPOS_ALIMENTARES = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupos_alimentares_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.listViewGruposAlimentares);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), AlimentosListaActivity.class);
                intent.putExtra("GrupoAlimentar", (Parcelable) listaGruposAlimentares.get(position));
                startActivity(intent);
            }
        });

    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == ExerciciosListaActivity.CODIGO_ACTITIVITY_ADICIONAR_TREINO){
//            // Toast.makeText(this, "passou Grupo", Toast.LENGTH_SHORT).show();
//            if(data != null ){
//                setResult(CODIGO_ACTITIVITY_GRUPOS_ALIMENTARES,data);
//                finish();
//            }
//        }
//    }

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
        this.grupoAlimentarBo = new GrupoAlimentarBo();
        try {
            this.listaGruposAlimentares = this.grupoAlimentarBo.buscarGrupos(this);
            mListView = (ListView) findViewById(R.id.listViewGruposAlimentares);
            mListView.setAdapter( new GruposAlimentaresAdapter(this, listaGruposAlimentares));
            //registerForContextMenu(mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
