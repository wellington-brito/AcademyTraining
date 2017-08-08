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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Carga;
import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.negocio.CargaBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.ExercicioDao;
import wereh.academytraining.persistencia.GrupoMuscularDao;

public class TreinoDetalhesActivity extends AppCompatActivity {

    BarChart chart;
    List<Carga> listaMedidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino_detalhes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        final Treino treino = (Treino) getIntent().getSerializableExtra("treino");
        //getSupportActionBar().setTitle(treino.getNomeExercicio());

        collapsingToolbarLayout.setTitle(treino.getNomeExercicio());


        try {
            buscaImagems(treino);
            carregarListaMedidas();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TextView series = (TextView)findViewById(R.id.txtViewSerie);
        series.setText(Integer.toString(treino.getSerie()));

        TextView repeticoes = (TextView)findViewById(R.id.txtViewRepeticao);
        repeticoes.setText(Integer.toString(treino.getRepeticao()));

        TextView carga = (TextView)findViewById(R.id.txtViewCarga);
        carga.setText(Integer.toString(treino.getCarga()));

        TextView intervalo = (TextView)findViewById(R.id.txtIntervalo);
        intervalo.setText(Integer.toString(treino.getIntervalo()));


        TextView observacao = (TextView)findViewById(R.id.txtViewObs);
        observacao.setText(treino.getObservacao());

        carregarGrafico(treino);

    }




    private void carregarGrafico(Treino treino) {
        this.chart = (BarChart) findViewById(R.id.chart) ;
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < this.listaMedidas.size(); i++) {
            if (treino.getNomeExercicio().equals(this.listaMedidas.get(i).getNomeExercico())){
                entries.add(new BarEntry(i+1, this.listaMedidas.get(i).getCarga()));
            }
        }

        BarDataSet dataset = new BarDataSet(entries, "Medidas que você atualizou!");
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);

        //eixo x
        ArrayList<String> labels = new ArrayList<String>();
        for (int i = 0; i < this.listaMedidas.size(); i++) {
            labels.add(converterData(this.listaMedidas.get(i).getDataAlteracao()));
        }

        this.chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        BarData data = new BarData(dataset);
        this.chart.setData(data);
    }

    private void carregarListaMedidas() throws SQLException{
        CargaBo cargaBo = new CargaBo();
        this.listaMedidas = cargaBo.buscarMedidas(this);
    }

    private void buscaImagems(Treino e) throws SQLException {
        ExercicioDao exercicioDao;
        List<Exercicio> listaGruposMusculares;
        DatabaseHelper dh = new DatabaseHelper(this);;
        exercicioDao  = new ExercicioDao(dh.getConnectionSource());
        listaGruposMusculares = exercicioDao.queryForAll();

        ImageView img = (ImageView) findViewById(R.id.imageView);

        Exercicio ex = new Exercicio();
        img.setImageResource(ex.getExercicioImagen(e.getNomeExercicio()));

    }

    private String converterData(Date dataAlteracao) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM");
        String dataFormatada = formato.format(dataAlteracao);
        return dataFormatada;
    }
}
