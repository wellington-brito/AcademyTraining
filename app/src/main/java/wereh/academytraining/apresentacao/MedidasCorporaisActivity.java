package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Corpo;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.negocio.CorpoBo;

public class MedidasCorporaisActivity extends AppCompatActivity {
    List<Corpo> listaMedidas;
    Corpo medidasCorpo;
    EditText braco;
    EditText perna;
    EditText quadril;
    CorpoBo corpoBo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medidas_corporais);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            carregarMedidas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregarMedidas() throws SQLException {
        this.braco = (EditText) findViewById(R.id.editTextBraco);
        this.perna = (EditText) findViewById(R.id.editTextPerna);
        this.quadril = (EditText) findViewById(R.id.editTextQuadril);
        carregarListaMedidas();
        int dataParaComparar = 0;
        for (Corpo c : this.listaMedidas) {
            if (dataParaComparar < c.getDataAlteracao().getDate()) {
                dataParaComparar = c.getDataAlteracao().getDate();
            }
            this.medidasCorpo = c;
        }

        for (Corpo c : this.listaMedidas) {
            if (c.getDataAlteracao().getDate() == dataParaComparar) {
                this.braco.setText(Double.toString(this.medidasCorpo.getBiceps()));
                this.perna.setText(Double.toString(this.medidasCorpo.getPerna()));
                this.quadril.setText(Double.toString(this.medidasCorpo.getQuadril()));
            }
        }
    }

    private void carregarListaMedidas() throws SQLException {
        CorpoBo corpoBo = new CorpoBo();
        this.listaMedidas = corpoBo.buscarMedidas(this);
    }

    public void salvarMedidas(View view) throws SQLException {
        try {
            this.corpoBo = new CorpoBo();
            Corpo medidasCorrentes = recuperarDados();
            this.corpoBo.salvar(medidasCorrentes, this);
            Toast.makeText(this, "Alterções salvas!", Toast.LENGTH_SHORT).show();
        } catch (CampoObrigatorioException c) {
            String mensagem = c.getMessage();
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
        }
    }

    public Corpo recuperarDados() {
        this.braco = (EditText) findViewById(R.id.editTextBraco);
        this.perna = (EditText) findViewById(R.id.editTextPerna);
        this.quadril = (EditText) findViewById(R.id.editTextQuadril);
        this.corpoBo.verificarCampos(this.braco, this.perna, this.quadril);
        Corpo corpo = definirObjeto();
        return corpo;
    }

    private Corpo definirObjeto() {
        this.medidasCorpo = new Corpo();
        this.medidasCorpo.setBiceps(Float.parseFloat(this.braco.getText().toString()));
        this.medidasCorpo.setPerna(Float.parseFloat(this.perna.getText().toString()));
        this.medidasCorpo.setQuadril(Float.parseFloat(this.quadril.getText().toString()));
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        this.medidasCorpo.setDataAlteracao(data_atual);
        return this.medidasCorpo;
    }

    public void carregarGraficoProgresso(View view) {
        Intent i = new Intent(this, GraficoMedidasActivity.class);
        Bundle bundle = new Bundle();

        if (view.getId() == (R.id.imageButtonBraco)) {
            bundle.putInt("identificador", 1);
            i.putExtras(bundle);
        }
        if (view.getId() == (R.id.imageButtonQuadril)) {
            bundle.putInt("identificador", 2);
            i.putExtras(bundle);
        }
        if (view.getId() == (R.id.imageButtonPerna)) {
            bundle.putInt("identificador", 3);
            i.putExtras(bundle);
        }
        startActivity(i);
    }
}
