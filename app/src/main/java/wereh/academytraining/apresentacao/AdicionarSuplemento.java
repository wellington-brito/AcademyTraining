package wereh.academytraining.apresentacao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.ParseException;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Suplemento;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.negocio.SuplementoBo;

public class AdicionarSuplemento extends AppCompatActivity {


    Spinner spinnerHora;
    SuplementoBo suplementoBo;
    private String[] status = new String[]{"Pré Treino","Pós Treino"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_suplemento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, status);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        this.spinnerHora = (Spinner) findViewById(R.id.spinnerHora);
        this.spinnerHora.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    definirObjetosCampoDeTexto(view);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (CampoObrigatorioException c){
                    String mensagem =  c.getMessage();
                    Toast.makeText(AdicionarSuplemento.this, mensagem, Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void definirObjetosCampoDeTexto(View view) throws ParseException, SQLException {
        EditText nomeSuplemento = (EditText) findViewById(R.id.editTextNomeSuplemento);
        this.spinnerHora = (Spinner) findViewById(R.id.spinnerHora);
        this.suplementoBo = new SuplementoBo();
        this.suplementoBo.validarCamposDeTexto(nomeSuplemento);
        definirDadosPlanejamento(nomeSuplemento);
    }

    private void definirDadosPlanejamento(EditText nomeSuplemento) throws  SQLException{
        Suplemento suplementoCorrente = new Suplemento();
        suplementoCorrente.setNome(nomeSuplemento.getText().toString());
        suplementoCorrente.setHorario(spinnerHora.getSelectedItem().toString());
        this.suplementoBo.verificarPlanejamento(suplementoCorrente, this);
        this.suplementoBo.salvar(suplementoCorrente, this);
        limparCampos(nomeSuplemento);
        finish();
        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }
    private void limparCampos(EditText nomeSuplemento) throws SQLException {
        nomeSuplemento.setText("");
    }



}
