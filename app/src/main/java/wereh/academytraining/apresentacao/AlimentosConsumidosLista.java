package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.AlimentosConsumidos;
import wereh.academytraining.entidade.Usuario;
import wereh.academytraining.negocio.AlimentosConsumidosBo;
import wereh.academytraining.negocio.UsuarioBo;
import wereh.academytraining.persistencia.DatabaseHelper;

public class AlimentosConsumidosLista extends AppCompatActivity {


    ListView mListView;
    private List<AlimentosConsumidos> listaAlimentosConsumidoses;
    private DatabaseHelper dh;
    private AlimentosConsumidosBo alimentosConsumidosBo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos_consumidos_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarListaAlimentosPricipais);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            carregarPierChart();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        try {
            super.onResume();
            this.carregarLista();
            this.carregarDados();
            this.carregarPierChart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() throws SQLException {
        Usuario u = new Usuario();
        UsuarioBo usuarioBo = new UsuarioBo();
        u = usuarioBo.buscarUsuario(this);

        TextView totalmetaCalorias = (TextView)findViewById(R.id.txtViewValorTotal);
        TextView totalCalorias = (TextView)findViewById(R.id.txtViewValorTotalKcal);

        int totalMeta = 0;
        int totalKcal = 0;

        for (AlimentosConsumidos a : listaAlimentosConsumidoses) {
            totalKcal += recuperaKcalGrupoAlimentar(a);
        }
        totalMeta += u.getNecessidadesDiariasCalorias();
        totalmetaCalorias.setText(Integer.toString(totalMeta));
        totalCalorias.setText(Integer.toString(totalKcal));
    }

    private int recuperaKcalGrupoAlimentar(AlimentosConsumidos a) {

        switch (a.getIdGrupoAlimentar()){
            case 1:
                return 150*a.getNumeroPorcoes();
            case 2:
                return 15*a.getNumeroPorcoes();
            case 3:
                return 35*a.getNumeroPorcoes();
            case 4:
                return 55*a.getNumeroPorcoes();
            case 5:
                return 190*a.getNumeroPorcoes();
            case 6:
                return 120*a.getNumeroPorcoes();
            case 7:
                return 73*a.getNumeroPorcoes();
            case 8:
                return 100*a.getNumeroPorcoes();
            default:
                return 0;
        }
    }


    public  void carregarLista() throws SQLException {

        this.alimentosConsumidosBo = new AlimentosConsumidosBo();

        try {
            listaAlimentosConsumidoses = this.alimentosConsumidosBo.buscarAlimentosCheckList(this);
            this.mListView = (ListView)findViewById(R.id.alimentosConsumidos);
            this.mListView.setAdapter( new AlimentosConsumidosAdatper(this, listaAlimentosConsumidoses));
            registerForContextMenu(mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //método para carregar o menu de opçoes no item da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        //menu.setHeaderTitle(listaAlimentosConsumidoses.get(info.position).getNomeDieta());
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_lista_alimentos_consumidos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                int id = item.getItemId();
                if (id == R.id.action_Menu_Apagar) {
                    try {
                        apagar(listaAlimentosConsumidoses.get(info.position));
                        this.carregarLista();
                        this.carregarDados();
                        this.carregarPierChart();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (id == R.id.action_Menu_Alterar) {
                    Intent intent = new Intent(this, AdicionarAlimentoConsumidos.class);
                    intent.putExtra("alimentoConsumido",(Parcelable) listaAlimentosConsumidoses.get(info.position));
                    startActivity(intent);
                    try {
                        this.carregarDados();
                        this.carregarPierChart();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_grafico_alimentos_consumidos, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.item1) {
//            //chamar tela ocm gráficos por data
//            Intent i = new Intent(this, GraficoConsumoAlimData.class);
//            startActivity(i);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void apagar(AlimentosConsumidos alimentosConsumidos) throws SQLException {
        AlimentosConsumidosBo alimentosConsumidosBo = new AlimentosConsumidosBo();
        try {
            alimentosConsumidosBo.apagar(alimentosConsumidos, this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void carregarPierChart() throws SQLException {
        PieChart pieChart = (PieChart) findViewById(R.id.chart);
        AlimentosConsumidosBo alimentosConsumidosBo = new AlimentosConsumidosBo();
        List<AlimentosConsumidos> lista = alimentosConsumidosBo.buscarAlimentosCheckList(this);

        ArrayList<PieEntry> entries = new ArrayList<>();
        for (AlimentosConsumidos a : lista){
            entries.add(new PieEntry(a.getNumeroPorcoes(), a.getAlimennto()));
        }

        PieDataSet dataset = new PieDataSet(entries,"");
        PieData data = new PieData(dataset);
        pieChart.setData(data);
        pieChart.setUsePercentValues(true);
        Legend l = pieChart.getLegend();
        l.setEnabled(false);
        Description d = new Description();
        d.setText(" % Porções Consumidas ");
        pieChart.setDescription(d);
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);
        dataset.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        pieChart.animateY(1000);
    }
}
