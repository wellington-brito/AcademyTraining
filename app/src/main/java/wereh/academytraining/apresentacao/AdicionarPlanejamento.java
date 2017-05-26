package wereh.academytraining.apresentacao;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_planejamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            EditText objetivo = (EditText) findViewById(R.id.editTextObjetivo);
            EditText vezesSemana = (EditText) findViewById(R.id.editTextVezesSemana);
          //  EditText oservacao = (EditText) findViewById(R.id.editTextIntervalo);
            EditText dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
            EditText validade = (EditText) findViewById(R.id.editTextValidade);

            nome.setText(p.getNomePlanejamento());
            objetivo.setText(p.getObjetivo());
            vezesSemana.setText(Integer.toString(p.getVezesNaSemana()));
            SimpleDateFormat formatt = new SimpleDateFormat("dd-MM-yyyy");
            String data = formatt.format(p.getDataInicio());
            dataInicio.setText(data);
            validade.setText(Integer.toString(p.getValidade()));
        }
    }

    private void DefinirObjetosCampoDeTexto(View view) throws ParseException, SQLException {
      //  Planejamento planejamentoCorrente = new Planejamento();
        EditText nomePlanejamento = (EditText) findViewById(R.id.editTextPlanejamento);
        EditText objetivo = (EditText) findViewById(R.id.editTextObjetivo);
        EditText vezesNaSemana = (EditText) findViewById(R.id.editTextVezesSemana);
        EditText dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        EditText validade = (EditText) findViewById(R.id.editTextValidade);
        this.planejamentoBo = new PlanejamentoBo();
        this.planejamentoBo.validarCamposDeTexto(nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade );
        definirDadosPlanejamento(nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
    }


    private void definirDadosPlanejamento(EditText nomePlanejamento, EditText objetivo, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws ParseException, SQLException {
        Planejamento planejamentoCorrente = new Planejamento();

        planejamentoCorrente.setNomePlanejamento(nomePlanejamento.getText().toString());
        planejamentoCorrente.setObjetivo(objetivo.getText().toString());
        planejamentoCorrente.setVezesNaSemana(Integer.parseInt(vezesNaSemana.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dataIni = formatter.parse(dataInicio.getText().toString());
        planejamentoCorrente.setDataInicio(dataIni);
        planejamentoCorrente.setValidade(Integer.parseInt(validade.getText().toString()));

        if(this.verificardor == 1){
            planejamentoCorrente.setId(this.p.getId());
            this.atualizar(planejamentoCorrente, nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
            limparCampos(nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
        }
        else {
            this.planejamentoBo.verificarPlanejamento(planejamentoCorrente, this);
            this.salvar(planejamentoCorrente, nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
            limparCampos(nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
        }
        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void atualizar(Planejamento planejamentoCorrente, EditText nomePlanejamento, EditText objetivo, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws SQLException {
        this.planejamentoBo.atualizar(planejamentoCorrente, this, this.p);
    }

    private void salvar(Planejamento planejamentoCorrente, EditText nomePlanejamento, EditText objetivo, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws SQLException {
        this.planejamentoBo.salvar(planejamentoCorrente, AdicionarPlanejamento.this);
    }


    private void limparCampos(EditText nomePlanejamento, EditText objetivo, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws SQLException {
        nomePlanejamento.setText("");
        objetivo.setText("");
        vezesNaSemana.setText("");
        dataInicio.setText("");
        validade.setText("");
    }

    //FALTA FAZER O CADASTRO DO PLANEJAMENTO  ok
    // LISTAR O TREINOS CADASTRADOS QUE ESTÃO RELACIONADOS A UM PLANEJAMENTO ok
    // renomear DadosPlanejamentoActivity para DadosPlanejamento activity, essa tela irá mostrar todos os treinos(exercicios)   ok
    // cadastrados com o id do planejamento selecionado anteriormente ok
}

