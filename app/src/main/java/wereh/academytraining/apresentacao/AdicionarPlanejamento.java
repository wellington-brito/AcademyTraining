package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.PlanejamentoDao;
import wereh.academytraining.persistencia.TreinoDao;

import static android.R.attr.format;

public class AdicionarPlanejamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_planejamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    recuperarDadosCampoDeTexto(view);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void recuperarDadosCampoDeTexto(View view) throws ParseException, SQLException {

        Planejamento planejamentoCorrente = new Planejamento();

        EditText nomePlanejamento = (EditText) findViewById(R.id.editTextPlanejamento);
        EditText objetivo = (EditText) findViewById(R.id.editTextObjetivo);
        EditText vezesNaSemana = (EditText) findViewById(R.id.editTextVezesSemana);
        EditText dataInicio = (EditText) findViewById(R.id.editTextDataInicio);
        EditText validade = (EditText) findViewById(R.id.editTextValidade);

        validarCamposDeTexto(nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade );

        definirDadosPlanejamento(nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
    }



    private void validarCamposDeTexto(EditText nomePlanejamento, EditText objetivo, EditText vezesNaSemana, EditText dataInicio, EditText validade) {
        if(nomePlanejamento.getText().toString().equals("") ){
            throw new CampoObrigatorioException("PLANEJAMENTO");
        }
        if(objetivo.getText().toString().equals("")){
            throw new CampoObrigatorioException("OBJETIVO");
        }
        if(vezesNaSemana.getText().toString().equals("")){
            throw new CampoObrigatorioException("VEZES NA SEMANA");
        }
        if(dataInicio.getText().toString().equals("")){
            throw new CampoObrigatorioException("DATA INICIO");
        }
        if(validade.getText().toString().equals("")){
            throw new CampoObrigatorioException("VALIADADE");
        }

    }


    private void definirDadosPlanejamento(EditText nomePlanejamento, EditText objetivo, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws ParseException, SQLException {
        Planejamento planejamentoCorrente = new Planejamento();

        planejamentoCorrente.setNomePlanejamento(nomePlanejamento.getText().toString());
        planejamentoCorrente.setObjetivo(objetivo.getText().toString());
        planejamentoCorrente.setVezesNaSemana(Integer.parseInt(vezesNaSemana.getText().toString()));

        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      //  Date dataIni = formatter.parse(dataInicio.getText().toString());
        //planejamentoCorrente.setDataInicio(dataIni);
        planejamentoCorrente.setValidade(validade.getText().toString());


//        if(this.verificardor == 1){
//            atualizar(planejamentoCorrente, nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
//        }
//        else {
//            salvar(planejamentoCorrente, nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
//        }
        salvar(planejamentoCorrente, nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
    }



    private void salvar(Planejamento planejamentoCorrente, EditText nomePlanejamento, EditText objetivo, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(AdicionarPlanejamento.this);
        PlanejamentoDao planejamentoDao = new PlanejamentoDao(dh.getConnectionSource());
        planejamentoDao.create(planejamentoCorrente);
        limparCampos(nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
    }

    private void limparCampos(EditText nomePlanejamento, EditText objetivo, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws SQLException {
        nomePlanejamento.setText("");
        objetivo.setText("");
        vezesNaSemana.setText("");
        dataInicio.setText("");
        validade.setText("");
        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

    //FALTA FAZER O CADASTRO DO PLANEJAMENTO
    // LISTAR O TREINOS CADASTRADOS QUE ESTÃO RELACIONADOS A UM PLANEJAMENTO
    // renomear DadosPlanejamentoActivity para DadosPlanejamento activity, essa tela irá mostrar todos os treinos(exercicios)
    // cadastrados com o id do planejamento selecionado anteriormente
}

