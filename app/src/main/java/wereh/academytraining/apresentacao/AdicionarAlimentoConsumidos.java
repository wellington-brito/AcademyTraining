package wereh.academytraining.apresentacao;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Alimento;
import wereh.academytraining.entidade.AlimentosConsumidos;
import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.exceptions.TreinoDuplicadoException;
import wereh.academytraining.negocio.AlimentosConsumidosBo;
import wereh.academytraining.negocio.GrupoAlimentarBo;

public class AdicionarAlimentoConsumidos extends AppCompatActivity {

    NumberPicker numberpicker;
    TextView textview;
    int numeroDePorcoes;
    Alimento alimentoSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_alimento_consumo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addAlimentoNaCheckList();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                // finish();
            }
        });

        final Alimento alimento = (Alimento) getIntent().getSerializableExtra("alimento");


            carregarNumberPicker(alimento);


       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    private void carregarNumberPicker(final Alimento alimento) {
        numberpicker = (NumberPicker)findViewById(R.id.numberPicker1);
        textview = (TextView)findViewById(R.id.textView1);
        numberpicker.setMinValue(0);
        numberpicker.setMaxValue(5);
        TextView txtAlimento = (TextView) findViewById(R.id.textViewNomeAlimento);
        txtAlimento.setText(alimento.getNomeAlimento());
        numberpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textview.setText("Número de Porções : " + newVal);
                try {
                    carregarTextViews(newVal, alimento);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void carregarTextViews(int newVal, Alimento alimento) throws SQLException, ParseException{
        TextView txtCalorias = (TextView) findViewById(R.id.textViewCal);
        int calorias =  totalCaloriasPorcao(alimento.getGrupoAlimentar());
        txtCalorias.setText(Integer.toString(calorias * newVal));
        this.numeroDePorcoes = newVal;
        this.alimentoSelecionado = alimento;
        //addAlimentoNaCheckList(alimento, newVal);
    }

    private int totalCaloriasPorcao(int idGrupoAlimentar) throws SQLException {
        GrupoAlimentarBo grupoAlimentarBo = new GrupoAlimentarBo();
        List<GrupoAlimentar> listaGrupos = grupoAlimentarBo.buscarGrupos(this);
        for (GrupoAlimentar ga : listaGrupos){
            if (ga.getId() == idGrupoAlimentar){
                int calorias = ga.getCalorias();
                return calorias;
            }
        }
       return 0;
    }

    public void addAlimentoNaCheckList() throws SQLException, ParseException{
        AlimentosConsumidos alimentoConsumido = new AlimentosConsumidos();
        AlimentosConsumidosBo alimentosConsumidosBo = new AlimentosConsumidosBo();

        alimentoConsumido.setAlimennto(alimentoSelecionado.getNomeAlimento());
        alimentoConsumido.setIdAlimento(alimentoSelecionado.getId());
        alimentoConsumido.setNumeroPorcoes(this.numeroDePorcoes);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formatter.parse(getDateTime());
        alimentoConsumido.setDia(data);
        //alimentosConsumidosBo.verificarLista(alimentoConsumido, this);
        alimentosConsumidosBo.salvar(alimentoConsumido, this);
        finish();
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
