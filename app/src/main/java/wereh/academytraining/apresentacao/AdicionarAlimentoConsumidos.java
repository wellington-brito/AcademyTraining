package wereh.academytraining.apresentacao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.ObjetoDuplicadoException;
import wereh.academytraining.negocio.AlimentosConsumidosBo;
import wereh.academytraining.negocio.GrupoAlimentarBo;

public class AdicionarAlimentoConsumidos extends AppCompatActivity {

    EditText quantidade;
    TextView txtAlimento;
    int numeroDePorcoes;
  //  Alimento alimentoSelecionado;
    Alimento alimento;
    AlimentosConsumidos alimentoListaConsumidos;
    AlimentosConsumidosBo alimentosConsumidosBo;
    int verificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_alimento_consumo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.alimento = (Alimento) getIntent().getSerializableExtra("alimento");
        this.alimentoListaConsumidos = (AlimentosConsumidos) getIntent().getSerializableExtra("alimentoConsumido");

        if (this.alimentoListaConsumidos != null){
            getSupportActionBar().setTitle("Adicionar porção cosumida");
            this.verificador = 1;
            quantidade = (EditText) findViewById(R.id.editTextPorcao);
            txtAlimento = (TextView) findViewById(R.id.textViewNomeAlimento);
            txtAlimento.setText(this.alimentoListaConsumidos.getAlimennto());
            quantidade.setText(Integer.toString(this.alimentoListaConsumidos.getNumeroPorcoes()));
            txtAlimento = (TextView) findViewById(R.id.textViewNomeAlimento);
            txtAlimento.setText(alimentoListaConsumidos.getAlimennto());
        }

        if (this.alimento != null){
            this.txtAlimento = (TextView) findViewById(R.id.textViewNomeAlimento);
            this.txtAlimento.setText(this.alimento.getNomeAlimento());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    recuperarQuantidade();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }catch (CampoObrigatorioException c){
                    Toast.makeText(AdicionarAlimentoConsumidos.this, c.getMessage(), Toast.LENGTH_SHORT).show();
                }catch (ObjetoDuplicadoException t){
                    Toast.makeText(AdicionarAlimentoConsumidos.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
                // finish();
            }
        });
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void recuperarQuantidade() throws ParseException, SQLException {
        EditText quantidade = (EditText) findViewById(R.id.editTextPorcao);
        TextView nomeAlimento = (TextView) findViewById(R.id.textViewNomeAlimento);
        this.alimentosConsumidosBo = new AlimentosConsumidosBo();
        this.alimentosConsumidosBo.validarCamposDeTexto(quantidade);
        this.definirDadosPorcao(quantidade, nomeAlimento);
    }

    private void definirDadosPorcao(EditText quantidade, TextView nomeAlimento) throws ParseException, SQLException {
        AlimentosConsumidos alimentoConsumido = new AlimentosConsumidos();
        AlimentosConsumidosBo alimentosConsumidosBo = new AlimentosConsumidosBo();
        alimentoConsumido.setNumeroPorcoes(Integer.parseInt(quantidade.getText().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date data = formatter.parse(getDateTime());
        alimentoConsumido.setDia(data);

        if (verificador == 1){
            alimentoConsumido.setAlimennto(alimentoListaConsumidos.getAlimennto());
            alimentoConsumido.setIdAlimento(alimentoListaConsumidos.getId());
            alimentoConsumido.setId(alimentoListaConsumidos.getId());
            alimentosConsumidosBo.atualizar(alimentoConsumido, this);
            finish();
        }else {
            alimentoConsumido.setAlimennto(alimento.getNomeAlimento());
            alimentoConsumido.setIdAlimento(alimento.getId());
            alimentoConsumido.setIdGrupoAlimentar(alimento.getGrupoAlimentar());
            alimentosConsumidosBo.salvar(alimentoConsumido, this);
            finish();
        }
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date date = new Date();
        return dateFormat.format(date);
    }

}
