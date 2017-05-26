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
    Exercicio exercicio;
    int verificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_exercicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        this.grupoMuscular = (GrupoMuscular) getIntent().getSerializableExtra("idGrupo");
        this.exercicio = (Exercicio)  getIntent().getSerializableExtra("exercicio");

        if (this.exercicio != null){
            getSupportActionBar().setTitle("Editar Exercício");
            verificador = 1;
            EditText nome = (EditText) findViewById(R.id.editTextNomeExercicio);
            EditText descricao = (EditText) findViewById(R.id.editTextDescricao);

            nome.setText(this.exercicio.getNomeExercicio());
            descricao.setText(this.exercicio.getDescricao());
        }

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

        EditText descricao = (EditText) findViewById(R.id.editTextDescricao);
    
        this.exercicioBo = new ExercicioBo();
        this.exercicioBo.validarCamposDeTexto(nomeExercicio, descricao);
        this.definirDadosExercicio(nomeExercicio, descricao);
    }

    private void definirDadosExercicio(EditText nomeExercicio, EditText descricao) throws SQLException {
       Exercicio exercicioCorrente = new Exercicio();

       exercicioCorrente.setNomeExercicio(nomeExercicio.getText().toString());
       exercicioCorrente.setDescricao(descricao.getText().toString());

       if(this.verificador == 1){
           exercicioCorrente.setId(this.exercicio.getId());
           this.exercicioBo.atualizar(exercicioCorrente, this, this.exercicio);
           Toast.makeText(this, "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
           limparCampos(nomeExercicio, descricao);
           finish();
        }
        else {
           exercicioCorrente.setIdUsuario(Integer.toString(this.usuario.getId()));
           exercicioCorrente.setGrupoMuscular(this.grupoMuscular.getId());
           this.exercicioBo.verificarExercicio(exercicioCorrente,this);
           this.exercicioBo.salvar(exercicioCorrente, this);
           Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
           limparCampos(nomeExercicio, descricao);
           finish();
        }
    }

    private void limparCampos(EditText nomeExercicio,  EditText descricao) {
        nomeExercicio.setText("");
        descricao.setText(""
        );
    }



}
