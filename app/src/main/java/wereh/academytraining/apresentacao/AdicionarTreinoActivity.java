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
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.TreinoDuplicadoException;
import wereh.academytraining.negocio.TreinoBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.PlanejamentoDao;

public class AdicionarTreinoActivity extends AppCompatActivity {


    Exercicio exercicio;
    Planejamento planejamento;
    Treino t = null;
    int verificardor;
    DatabaseHelper dh;
    TreinoBo treinoBo;
    final static String SERIES = "series";
    final static String REPETICOES = "rep";
    final static String CARGA = "carga";
    final static String INTERVALO = "intervalo";
    final static String OBSERVACAO = "OBS";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_adicionar_treino);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAtivityAdicionarTreino);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        t = (Treino) getIntent().getSerializableExtra("treino");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    recuperarDadosCampoDeTexto(view);
                } catch (SQLException e) {
                    e.printStackTrace();
                }catch (TreinoDuplicadoException tr){
                    Toast.makeText(AdicionarTreinoActivity.this, tr.getMessage(), Toast.LENGTH_SHORT).show();
                }catch (CampoObrigatorioException c){
                    Toast.makeText(AdicionarTreinoActivity.this, c.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(t != null) {
            getSupportActionBar().setTitle("Editar");
            verificardor = 1;
            EditText serie_Edt = (EditText) findViewById(R.id.editTextNomeExercicio);
            EditText repeticoes_Edt = (EditText) findViewById(R.id.editTextRepeticoes);
            EditText carga_Edt = (EditText) findViewById(R.id.editTextCarga);
            EditText intervalo_Edt = (EditText) findViewById(R.id.editTextIntervalo);
            EditText observacao_Edt = (EditText) findViewById(R.id.editTextObservacaro);
            EditText exercicio_Edt = (EditText) findViewById(R.id.editTextExercicio);
            EditText planejamento_edt = (EditText) findViewById(R.id.editTextPlanejamento);

            serie_Edt.setText(t.getSerie());
            repeticoes_Edt.setText(t.getRepeticao());
            carga_Edt.setText(t.getCarga());
            intervalo_Edt.setText(t.getIntervalo());
            observacao_Edt.setText(t.getObservacao());
            exercicio_Edt.setText(t.getNomeExercicio());

            try {
                planejamento_edt.setText(resgatarNomePlanejamento(t.getIdPlanejamento()));                              // id do planejamento está vindo sempre 0
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstaceState){
        EditText serie_Edt = (EditText) findViewById(R.id.editTextNomeExercicio);
        EditText repeticoes_Edt = (EditText) findViewById(R.id.editTextRepeticoes);
        EditText carga_Edt = (EditText) findViewById(R.id.editTextCarga);
        EditText intervalo_Edt = (EditText) findViewById(R.id.editTextIntervalo);
        EditText observacao_Edt = (EditText) findViewById(R.id.editTextObservacaro);
        savedInstaceState.putString(SERIES, serie_Edt.getText().toString() );
        savedInstaceState.putString(REPETICOES, repeticoes_Edt.getText().toString() );
        savedInstaceState.putString(CARGA, carga_Edt.getText().toString() );
        savedInstaceState.putString(INTERVALO, intervalo_Edt.getText().toString() );
        savedInstaceState.putString(OBSERVACAO, observacao_Edt.getText().toString() );
        super.onSaveInstanceState(savedInstaceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Sempre chame a superclasse para que possa
        // restaurar a hierarquia da view
        super.onRestoreInstanceState(savedInstanceState);
        EditText serie_Edt = (EditText) findViewById(R.id.editTextNomeExercicio);
        EditText repeticoes_Edt = (EditText) findViewById(R.id.editTextRepeticoes);
        EditText carga_Edt = (EditText) findViewById(R.id.editTextCarga);
        EditText intervalo_Edt = (EditText) findViewById(R.id.editTextIntervalo);
        EditText observacao_Edt = (EditText) findViewById(R.id.editTextObservacaro);
        // Restaura estados membros da instância salva
        serie_Edt.setText(savedInstanceState.getString(SERIES));
        repeticoes_Edt.setText(savedInstanceState.getString(REPETICOES));
        carga_Edt.setText(savedInstanceState.getString(CARGA));
        intervalo_Edt.setText(savedInstanceState.getString(INTERVALO));
        observacao_Edt.setText(savedInstanceState.getString(OBSERVACAO));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == GruposMuscularesListaActivity.CODIGO_ACTITIVITY_GRUPOS_MUSCULARES){
            if(data != null ){
                EditText txtExercicio = (EditText) findViewById(R.id.editTextExercicio);
                Bundle bulndle = data.getExtras();
                this.exercicio = bulndle.getParcelable("exercicio");
                txtExercicio.setText(exercicio.getNomeExercicio());
                Toast.makeText(this, exercicio.getNomeExercicio(), Toast.LENGTH_SHORT).show();
            }
        }

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


    private void recuperarDadosCampoDeTexto(View view) throws SQLException {
        EditText serie_edt = (EditText) findViewById(R.id.editTextNomeExercicio);
        EditText repeticoes_edt = (EditText) findViewById(R.id.editTextRepeticoes);
        EditText carga_edt = (EditText) findViewById(R.id.editTextCarga);
        EditText intervalo_edt = (EditText) findViewById(R.id.editTextIntervalo);
        EditText observacao_edt = (EditText) findViewById(R.id.editTextObservacaro);
        EditText exercicio_edt = (EditText) findViewById(R.id.editTextExercicio);
        EditText planejamento_edt = (EditText) findViewById(R.id.editTextPlanejamento);
        this.treinoBo = new TreinoBo();
        this.treinoBo.validarCamposDeTexto(serie_edt, repeticoes_edt, carga_edt, intervalo_edt, observacao_edt, exercicio_edt,planejamento_edt);
        definirDadosTreino(serie_edt, repeticoes_edt, carga_edt, intervalo_edt, observacao_edt, exercicio_edt, planejamento_edt);
    }

    private void definirDadosTreino(EditText serie_edt, EditText repeticoes_edt, EditText carga_edt, EditText intevalo_edt, EditText observacao_edt, EditText exercicio_edt, EditText planejamento_edt) throws SQLException {
        Treino treinoCorrente = new Treino();
        this.treinoBo = new TreinoBo();
        treinoCorrente.setSerie(Integer.parseInt(serie_edt.getText().toString()));
        treinoCorrente.setRepeticao(Integer.parseInt(repeticoes_edt.getText().toString()));
        treinoCorrente.setCarga(Integer.parseInt(carga_edt.getText().toString()));
        treinoCorrente.setIntervalo(Integer.parseInt(intevalo_edt.getText().toString()));
        treinoCorrente.setObservacao(observacao_edt.getText().toString());
        treinoCorrente.setNomeExercicio(exercicio_edt.getText().toString());

        if(this.verificardor == 1){
            treinoCorrente.setId(this.t.getId());
            treinoCorrente.setIdPlanejamento(this.t.getIdPlanejamento());
            atualizar(treinoCorrente, serie_edt, repeticoes_edt, carga_edt, intevalo_edt, observacao_edt, exercicio_edt, planejamento_edt);
        }
        else {
            treinoCorrente.setIdPlanejamento(this.planejamento.getId());
            this.treinoBo.verificarTreino(treinoCorrente, this);
            salvar(treinoCorrente, serie_edt, repeticoes_edt, carga_edt, intevalo_edt, observacao_edt, exercicio_edt, planejamento_edt);
        }
    }

    private void salvar(Treino treinoCorrente, EditText serie_edt, EditText repeticoes_edt, EditText carga_edt, EditText intevalo_edt, EditText observacao_edt, EditText exercicio_edt, EditText planejamento_edt) throws SQLException {
        this.treinoBo.salvar(treinoCorrente, this);
        limparCampos( serie_edt, repeticoes_edt, carga_edt, intevalo_edt, observacao_edt, exercicio_edt, planejamento_edt );
    }

    private void atualizar(Treino treinoCorrente, EditText serie_edt, EditText repeticoes_edt, EditText carga_edt, EditText intevalo_edt, EditText observacao_edt, EditText exercicio_edt, EditText planejamento_edt) throws SQLException {
        this.treinoBo.atualizar(treinoCorrente, this, this.t);
        limparCampos( serie_edt, repeticoes_edt, carga_edt, intevalo_edt, observacao_edt, exercicio_edt, planejamento_edt );
    }

    private void limparCampos(EditText serie_edt, EditText repeticoes_edt, EditText carga_edt, EditText intevalo_edt, EditText observacao_edt, EditText exercicio_edt, EditText planejamento_edt) throws SQLException {
        serie_edt.setText("");
        repeticoes_edt.setText("");
        carga_edt.setText("");
        intevalo_edt.setText("");
        observacao_edt.setText("");
        exercicio_edt.setText("");
        planejamento_edt.setText("");
        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void carregarActivityGrupoMuscular(View view){
        Intent i = new Intent(this, GruposMuscularesListaActivity.class);
        startActivityForResult(i, GruposMuscularesListaActivity.CODIGO_ACTITIVITY_GRUPOS_MUSCULARES);
    }

    public void carregarPlanejamentos(View view){
        Intent i = new Intent(this, PlanejamentoListaActivity.class); //listagem de todos os planejamentos
        startActivityForResult(i, PlanejamentoListaActivity.CODIGO_ACTITIVITY_PLANEJAMENTO);
        //startActivity(i);
    }

    private String resgatarNomePlanejamento(int idPlanejamento) throws SQLException {
        this.dh = new DatabaseHelper(AdicionarTreinoActivity.this);
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
