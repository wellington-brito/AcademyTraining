package wereh.academytraining.apresentacao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;
import wereh.academytraining.R;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.negocio.PlanejamentoBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.PlanejamentoDao;

public class AdicionarPlanejamento extends AppCompatActivity {

    PlanejamentoBo planejamentoBo;
    Planejamento p = null;
    int verificardor;
    EditText dataInicio;
    MaskEditTextChangedListener maskDATA;
    private String[] objetivos = new String[]{"Hipertrofia","Perca de peso"};
    Spinner sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_planejamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, objetivos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.sp= (Spinner) findViewById(R.id.spinnerObjetivo);
        this.sp.setAdapter(adapter);

        this.p = (Planejamento) getIntent().getSerializableExtra("planejamento");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DefinirObjetosCampoDeTexto(view);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (CampoObrigatorioException c){
                    String mensagem =  c.getMessage();
                    Toast.makeText(AdicionarPlanejamento.this, mensagem, Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        if (this.p != null){
            getSupportActionBar().setTitle("Editar Planejamento");
            verificardor = 1;
            EditText nome = (EditText) findViewById(R.id.editTextPlanejamento);
            EditText vezesSemana = (EditText) findViewById(R.id.editTextVezesSemana);
            EditText dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
            EditText validade = (EditText) findViewById(R.id.editTextValidade);

            nome.setText(p.getNomePlanejamento());

            if (p.getObjetivo().equals("Hipertrofia")){
                sp.setSelection(0);
            }else{
                sp.setSelection(1);
            }
            vezesSemana.setText(Integer.toString(p.getVezesNaSemana()));
            SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy");
            String data = formatt.format(p.getDataInicio());
            dataInicio.setText(data);
            validade.setText(Integer.toString(p.getValidade()));
        }

        this.dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        this.maskDATA = new MaskEditTextChangedListener("##/##/####", dataInicio);
        this.dataInicio.addTextChangedListener(this.maskDATA);
    }

    private void DefinirObjetosCampoDeTexto(View view) throws ParseException, SQLException {
      //  Planejamento planejamentoCorrente = new Planejamento();
        EditText nomePlanejamento = (EditText) findViewById(R.id.editTextPlanejamento);
       // EditText objetivo = (EditText) findViewById(R.id.editTextObjetivo);
        EditText vezesNaSemana = (EditText) findViewById(R.id.editTextVezesSemana);
        this.dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        EditText validade = (EditText) findViewById(R.id.editTextValidade);
        this.sp = (Spinner) findViewById(R.id.spinnerObjetivo);
        this.planejamentoBo = new PlanejamentoBo();
        this.planejamentoBo.validarCamposDeTexto(nomePlanejamento, vezesNaSemana, this.dataInicio, validade );
        definirDadosPlanejamento(nomePlanejamento, vezesNaSemana, this.dataInicio, validade);
    }


    private void definirDadosPlanejamento(EditText nomePlanejamento, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws ParseException, SQLException {
        Planejamento planejamentoCorrente = new Planejamento();

        planejamentoCorrente.setNomePlanejamento(nomePlanejamento.getText().toString());
        planejamentoCorrente.setObjetivo(sp.getSelectedItem().toString());
        planejamentoCorrente.setVezesNaSemana(Integer.parseInt(vezesNaSemana.getText().toString()));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dataIni = formatter.parse(dataInicio.getText().toString());
        planejamentoCorrente.setDataInicio(dataIni);
        planejamentoCorrente.setValidade(Integer.parseInt(validade.getText().toString()));

        if(this.verificardor == 1){
            planejamentoCorrente.setId(this.p.getId());
            this.atualizar(planejamentoCorrente);
            limparCampos(nomePlanejamento, vezesNaSemana, dataInicio, validade);
            finish();
        }
        else {
            planejamentoBo.verificarPlanejamento(planejamentoCorrente, this);
            this.salvar(planejamentoCorrente);
            limparCampos(nomePlanejamento, vezesNaSemana, dataInicio, validade);
            finish();
        }
        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void atualizar(Planejamento planejamentoCorrente) throws SQLException {
        this.planejamentoBo.atualizar(planejamentoCorrente, this, this.p);
    }

    private void salvar(Planejamento planejamentoCorrente) throws SQLException {
        this.planejamentoBo.salvar(planejamentoCorrente, AdicionarPlanejamento.this);
    }


    private void limparCampos(EditText nomePlanejamento, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws SQLException {
        nomePlanejamento.setText("");
        vezesNaSemana.setText("");
        dataInicio.setText("");
        validade.setText("");
    }

    public void showObjetivos(View v){
        String obj = (String) this.sp.getSelectedItem();
        long id = this.sp.getSelectedItemId();
        int position = this.sp.getSelectedItemPosition();
    }
}

