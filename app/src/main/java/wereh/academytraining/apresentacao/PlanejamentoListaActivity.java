package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.adpters.PlanejamentoAdapter;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.negocio.PlanejamentoBo;
import wereh.academytraining.persistencia.DatabaseHelper;

public class PlanejamentoListaActivity extends AppCompatActivity {


    public static final int CODIGO_ACTITIVITY_PLANEJAMENTO = 2; /// TELA DE LISTAGEM DE PLANEJAMENTOS
    private DatabaseHelper dh;
    private List<Planejamento> listaPlanejamentos;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planejamento_listagem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mListView = (ListView) findViewById(R.id.listviewPlanejamentos);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.putExtra("planejamento", (Parcelable) listaPlanejamentos.get(position));
                setResult(CODIGO_ACTITIVITY_PLANEJAMENTO, intent);
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ExerciciosListaActivity.CODIGO_ACTITIVITY_ADICIONAR_TREINO) {
            if (data != null) {
                setResult(CODIGO_ACTITIVITY_PLANEJAMENTO, data);
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

    public void carregarLista() throws SQLException {
        PlanejamentoBo planejamentoBo = new PlanejamentoBo();
        this.listaPlanejamentos = planejamentoBo.carregarLista(this, "Ativo");
        this.mListView = (ListView) findViewById(R.id.listviewPlanejamentos);
        this.mListView.setAdapter(new PlanejamentoAdapter(this, listaPlanejamentos));
        //this.mListView.setAdapter( new AlimentosListaAdapter(this,  planejamentoBo.carregarLista(this,this.idSelected)));
        registerForContextMenu(mListView);
    }


}
