package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.AlimentosConsumidos;
import wereh.academytraining.negocio.AlimentosConsumidosBo;
import wereh.academytraining.persistencia.DatabaseHelper;

public class GraficoConsumoAlimData extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico_consumo_alim_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            definindoEntries();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void definindoEntries() throws SQLException {

        BarChart barChart = (BarChart) findViewById(R.id.chart1);

        DatabaseHelper dh = new DatabaseHelper(this);

        AlimentosConsumidosBo alimentosConsumidosBo = new AlimentosConsumidosBo();

        List<AlimentosConsumidos> lista = alimentosConsumidosBo.buscarAlimentosCheckList(this);

        ArrayList<BarEntry> entriesAlimentos = new ArrayList<>();

//        for(int i = 0; i< lista.size(); i++){
//            entriesAlimentos.add(new BarEntry(i, lista.get(i).getNumeroPorcoes()));
//            entriesAlimentos.add(new BarEntry(i,)
//        }

        for(AlimentosConsumidos a: lista){
            entriesAlimentos.add(new BarEntry(a.getId(), a.getNumeroPorcoes(), a.getAlimennto()));
        }
        BarDataSet dataset = new BarDataSet(entriesAlimentos, "Alimentos");


//
//        ArrayList<String> labels = new ArrayList<String>();
//        for(int i = 0; i< lista.size(); i++){
//            labels.add(String.valueOf(lista.get(i).getDia()));
//        }


        BarData data = new BarData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(data);

        // chart.saveToGallery("mychart.jpg", 85); // 85 is the quality of the image
//        Description d = new Description();
//        d.setText(" % Porções Consumidas ");
//        barChart.setDescription(d);


//        PieData data = new PieData(dataset);
//
//        pieChart.setData(data);
//        pieChart.setUsePercentValues(true);
//        Description d = new Description();
//        d.setText(" % Porções Consumidas ");
//        pieChart.setDescription(d);
//        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
//        dataset.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
//
//        pieChart.animateY(1000);
//
//        Intent i = new Intent(this, ExibirProgresso.class);
//        i.putExtra("progresso", listaDeMudancas);
//        startActivity(i);

    }

}
