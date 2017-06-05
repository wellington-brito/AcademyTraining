package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;
import wereh.academytraining.R;
import wereh.academytraining.entidade.Dieta;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.negocio.DietaBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.PlanejamentoDao;

public class AdicionarDietaActivity extends AppCompatActivity {

    Planejamento planejamento;
    DietaBo dietaBo;
    Dieta d = null;
    int verificardor;
    EditText dataInicio;
    MaskEditTextChangedListener maskDATA;

    final static String DIETA = "dite";
    final static String VEZES_SEMANA = "vzsSemana";
    final static String DATA_INICIO = "dataInicio";
    final static String VALIDADE = "validade";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_dieta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                    Toast.makeText(AdicionarDietaActivity.this, c.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        this.d = (Dieta) getIntent().getSerializableExtra("dieta");

        if (this.d != null){
            getSupportActionBar().setTitle("Editar Dieta");
            verificardor = 1;
            EditText nomeDieta = (EditText) findViewById(R.id.editTextDieta);
            EditText planejamento = (EditText) findViewById(R.id.editTextPlanejamento);
            EditText vezesSemana = (EditText) findViewById(R.id.editTextVezesSemana);
            EditText dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
            EditText validade = (EditText) findViewById(R.id.editTextValidade);

            nomeDieta.setText(d.getNomeDieta());
            try {
                planejamento.setText(resgatarNomePlanejamento(d.getIdPlanejamento()));                              // id do planejamento está vindo sempre 0
            } catch (SQLException e) {
                e.printStackTrace();
            }
            vezesSemana.setText(Integer.toString(d.getVezesNaSemana()));
            SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy");
            String data = formatt.format(d.getDataInicio());
            dataInicio.setText(data);
            validade.setText(Integer.toString(d.getValidade()));
        }

        this.dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        this.maskDATA = new MaskEditTextChangedListener("##/##/####", dataInicio);
        this.dataInicio.addTextChangedListener(this.maskDATA);

    }


    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstaceState){
        EditText nomeDieta = (EditText) findViewById(R.id.editTextDieta);
        EditText vezesSemana = (EditText) findViewById(R.id.editTextVezesSemana);
        EditText dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        EditText validade = (EditText) findViewById(R.id.editTextValidade);
        savedInstaceState.putString(DIETA, nomeDieta.getText().toString() );
        savedInstaceState.putString(VEZES_SEMANA, vezesSemana.getText().toString() );
        savedInstaceState.putString(DATA_INICIO, dataInicio.getText().toString() );
        savedInstaceState.putString(VALIDADE, validade.getText().toString() );
        super.onSaveInstanceState(savedInstaceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Sempre chame a superclasse para que possa
        // restaurar a hierarquia da view
        super.onRestoreInstanceState(savedInstanceState);
        EditText nomeDieta = (EditText) findViewById(R.id.editTextDieta);
        EditText vezesSemana = (EditText) findViewById(R.id.editTextVezesSemana);
        EditText dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        EditText validade = (EditText) findViewById(R.id.editTextValidade);
        // Restaura estados membros da instância salva
        nomeDieta.setText(savedInstanceState.getString(DIETA));
        vezesSemana.setText(savedInstanceState.getString(VEZES_SEMANA));
        dataInicio.setText(savedInstanceState.getString(DATA_INICIO));
        validade.setText(savedInstanceState.getString(VALIDADE));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == PlanejamentoListaActivity.CODIGO_ACTITIVITY_PLANEJAMENTO){
            if(data != null ){
                EditText txtPlanejamento = (EditText) findViewById(R.id.editTextPlanejamento);
                Bundle bulndle = data.getExtras();
                this.planejamento = bulndle.getParcelable("planejamento");
                txtPlanejamento.setText(planejamento.getNomePlanejamento());
                Toast.makeText(this, planejamento.getNomePlanejamento(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void DefinirObjetosCampoDeTexto(View view) throws ParseException, SQLException {

            EditText nomeDieta = (EditText) findViewById(R.id.editTextDieta);
            EditText planejamento = (EditText) findViewById(R.id.editTextPlanejamento);
            EditText vezesNaSemana = (EditText) findViewById(R.id.editTextVezesSemana);
            this.dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
            EditText validade = (EditText) findViewById(R.id.editTextValidade);
            this.dietaBo = new DietaBo();
            this.dietaBo.validarCamposDeTexto(nomeDieta, planejamento, vezesNaSemana, this.dataInicio, validade );
            definirDadosDieta(nomeDieta, planejamento, vezesNaSemana, this.dataInicio, validade);

    }

    private void definirDadosDieta(EditText nomeDieta, EditText planejamento, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws ParseException, SQLException {
        Dieta dietaCorrente = new Dieta();
        dietaCorrente.setNomeDieta(nomeDieta.getText().toString());
        dietaCorrente.setVezesNaSemana(Integer.parseInt(vezesNaSemana.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dataIni = formatter.parse(dataInicio.getText().toString());
        dietaCorrente.setDataInicio(dataIni);
        dietaCorrente.setValidade(Integer.parseInt(validade.getText().toString()));

        if(this.verificardor == 1){
            dietaCorrente.setId(this.d.getId());
            dietaCorrente.setIdPlanejamento(this.d.getIdPlanejamento());
            this.dietaBo.atualizar(dietaCorrente,this, this.d);
            limparCampos(nomeDieta, planejamento, vezesNaSemana, dataInicio, validade);
            finish();
        }
        else {
            dietaCorrente.setIdPlanejamento(this.planejamento.getId());
            this.dietaBo.verificarDieta(dietaCorrente, this);
            this.dietaBo.salvar(dietaCorrente, this);
            limparCampos(nomeDieta, planejamento, vezesNaSemana, dataInicio, validade);
            finish();
        }
        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void limparCampos(EditText nomeDieta, EditText planejamento, EditText vezesNaSemana, EditText dataInicio, EditText validade) {
        nomeDieta.setText("");
        planejamento.setText("");
        vezesNaSemana.setText("");
        dataInicio.setText("");
        validade.setText("");
    }


    public void carregarPlanejamentos(View view){
        Intent i = new Intent(this, PlanejamentoListaActivity.class); //listagem de todos os planejamentos
        startActivityForResult(i, PlanejamentoListaActivity.CODIGO_ACTITIVITY_PLANEJAMENTO);
        //startActivity(i);
    }

    private String resgatarNomePlanejamento(int idPlanejamento) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(this);
        PlanejamentoDao planejamentoDao = new PlanejamentoDao(dh.getConnectionSource());
        List<Planejamento> listaPlanejamento = planejamentoDao.queryForAll();
        for(Planejamento p : listaPlanejamento){
            if (p.getId() == idPlanejamento){   // essa parte precisa ser refeita
                return p.getNomePlanejamento();
            }
        }
        return null;
    }

}
