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

import wereh.academytraining.R;
import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.entidade.Usuario;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.negocio.ExercicioBo;
import wereh.academytraining.negocio.UsuarioBo;

public class AdicionarExercicioActivity extends AppCompatActivity {

    Usuario usuario;
    GrupoMuscular grupoMuscular;
    ExercicioBo exercicioBo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_exercicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        this.grupoMuscular = (GrupoMuscular) getIntent().getSerializableExtra("idGrupo");

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
                    Toast.makeText(AdicionarExercicioActivity.this, mensagem, Toast.LENGTH_SHORT).show();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void DefinirObjetosCampoDeTexto(View view) throws SQLException {
       // Exercicio exercicioCorrente = new Exercicio();
        EditText nomeExercicio = (EditText) findViewById(R.id.editTextNomeExercicio);
        EditText grupoMuscular = (EditText) findViewById(R.id.editTextGrupo);
        EditText descricao = (EditText) findViewById(R.id.editTextDescricao);
    
        this.exercicioBo = new ExercicioBo();
        this.exercicioBo.validarCamposDeTexto(nomeExercicio, grupoMuscular, descricao);
        this.definirDadosExercicio(nomeExercicio, grupoMuscular, descricao);
    }

    private void definirDadosExercicio(EditText nomeExercicio, EditText grupoMuscular, EditText descricao) throws SQLException {
        Exercicio exercicioCorrente = new Exercicio();

        exercicioCorrente.setNomeExercicio(nomeExercicio.getText().toString());
        exercicioCorrente.setDescricao(descricao.getText().toString());
        exercicioCorrente.setGrupoMuscular(this.grupoMuscular.getId());
        exercicioCorrente.setIdUsuario(Integer.toString(this.usuario.getId()));


//       if(this.verificardor == 1){
//            planejamentoCorrente.setId(this.p.getId());
//            this.atualizar(planejamentoCorrente, nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
//            limparCampos(nomePlanejamento, objetivo, vezesNaSemana, dataInicio, validade);
//        }
//        else {
            this.exercicioBo.verificarExercicio(exercicioCorrente,this);
            this.exercicioBo.salvar(exercicioCorrente, this);
            limparCampos(nomeExercicio, grupoMuscular, descricao);
//        }
        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void limparCampos(EditText nomeExercicio, EditText grupoMuscular, EditText descricao) {

    }



}
