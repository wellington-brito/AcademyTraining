package wereh.academytraining.apresentacao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.ExercicioDao;
import wereh.academytraining.persistencia.GrupoMuscularDao;

public class ExercicioDetalhesActivity extends AppCompatActivity {

    private List<Exercicio> exercicios;
    private List<GrupoMuscular> gruposMusculares;
    private ListView mListView;
    private DatabaseHelper dh;
    private ExercicioDao exercicioDao;
    private GrupoMuscularDao gmDao;
    private Exercicio exercicio;
    private int idSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios_detalhes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetalhesExercicio);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Exercicio exercicio = (Exercicio) getIntent().getSerializableExtra("exercicio");
        getSupportActionBar().setTitle(exercicio.getNomeExercicio());

        TextView txtNomeManobra = (TextView) findViewById(R.id.txtViewDescricao);
        txtNomeManobra.setText("Descrição \n"+ exercicio.getDescricao());
       // positionSelected = getIntent().getIntExtra("POSITION", 0);

        // Então aqui você utiliza o ID do item(caso tenha) para pesquisar no banco de dados ou a position para pesquisar na list de origem
        // E então setar a View com os detalhes do item.



    }


    @Override
    public void onResume() {
        try {
            super.onResume();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}