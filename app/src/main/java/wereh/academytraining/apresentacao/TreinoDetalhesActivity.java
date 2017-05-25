package wereh.academytraining.apresentacao;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.GrupoMuscularDao;

public class TreinoDetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino_detalhes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        final Treino treino = (Treino) getIntent().getSerializableExtra("treino");
        //getSupportActionBar().setTitle(treino.getNomeExercicio());

        collapsingToolbarLayout.setTitle(treino.getNomeExercicio());


//        try {
//            buscaImagems(treino);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        TextView series = (TextView)findViewById(R.id.txtViewSerie);
        series.setText(treino.getSerie());

        TextView repeticoes = (TextView)findViewById(R.id.txtViewRepeticao);
        repeticoes.setText(treino.getRepeticao());

        TextView carga = (TextView)findViewById(R.id.txtViewCarga);
        carga.setText(treino.getCarga());

        TextView intervalo = (TextView)findViewById(R.id.txtIntervalo);
        intervalo.setText(treino.getIntervalo());


        TextView observacao = (TextView)findViewById(R.id.txtViewObs);
        observacao.setText(treino.getObservacao());


    }


//    private void buscaImagems(Treino e) throws SQLException {
//        GrupoMuscularDao grupoMuscularDao;
//        List<GrupoMuscular> listaGruposMusculares;
//        DatabaseHelper dh = new DatabaseHelper(this);;
//        grupoMuscularDao  = new GrupoMuscularDao(dh.getConnectionSource());
//        listaGruposMusculares = grupoMuscularDao.queryForAll();
//
//        ImageView img = (ImageView) findViewById(R.id.imageViewGroupMuscular);
//
//
//        GrupoMuscular g = new GrupoMuscular();
//        img.setImageResource(g.getGrupoImagen(e.getGrupoMuscular()));
//
//
//
//
//    }

}
