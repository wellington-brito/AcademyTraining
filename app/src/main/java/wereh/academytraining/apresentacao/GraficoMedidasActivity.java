package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Corpo;
import wereh.academytraining.negocio.CorpoBo;

public class GraficoMedidasActivity extends AppCompatActivity {
    BarChart chart;
    List<Corpo> listaMedidas;
    int identificador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_medidas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            carregarListaMedidas();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.identificador = bundle.getInt("identificador");
        carregarTitulo();
        carrergarGrafico();
    }

    private void carregarTitulo() {
        TextView titulo = (TextView) findViewById(R.id.textViewTitulo);

        if (this.identificador == 1){
            titulo.setText("Evolução das medidas do seu Bíceps");
        }
        if (this.identificador == 2){
            titulo.setText("Evolução das medidas do seu Quadril");
        }
        if (this.identificador == 3){
            titulo.setText("Evolução das medidas da sua Coxa");
        }
    }

    private void carregarListaMedidas() throws SQLException {
        CorpoBo corpoBo = new CorpoBo();
        this.listaMedidas = corpoBo.buscarMedidas(this);
    }

    public void carrergarGrafico() {
        this.chart = (BarChart) findViewById(R.id.chart) ;
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < this.listaMedidas.size(); i++) {
            if (this.identificador == 1){
                entries.add(new BarEntry(i+1, this.listaMedidas.get(i).getBiceps()));
            }
            if (this.identificador == 2){
                entries.add(new BarEntry(i+1, this.listaMedidas.get(i).getQuadril()));
            }
            if (this.identificador == 3){
                entries.add(new BarEntry(i+1, this.listaMedidas.get(i).getPerna()));
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
    private String converterData(Date dataAlteracao) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM");
        String dataFormatada = formato.format(dataAlteracao);
        return dataFormatada;
    }


}
