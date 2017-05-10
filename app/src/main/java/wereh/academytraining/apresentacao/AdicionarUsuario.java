package wereh.academytraining.apresentacao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Usuario;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.UsuarioCadastradoException;
import wereh.academytraining.negocio.UsuarioBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.UsuarioDao;

public class AdicionarUsuario extends AppCompatActivity {

    Usuario usuario = null;
    int qntd;
    UsuarioBo usuarioBo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        if (usuario != null){
            carregarPerfilUsuario(usuario);
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
                    Toast.makeText(AdicionarUsuario.this, c.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void DefinirObjetosCampoDeTexto(View view) throws SQLException {
        EditText nome = (EditText) findViewById(R.id.editTextNome);
        EditText objetivo = (EditText) findViewById(R.id.editTextObjetivo);
        EditText peso = (EditText) findViewById(R.id.editTextPeso);
        EditText altura = (EditText) findViewById(R.id.editTextAltura);
        EditText genero = (EditText) findViewById(R.id.editTextGenero);
        EditText imc = (EditText) findViewById(R.id.editTextImc);
        EditText tmb = (EditText) findViewById(R.id.editTextTmb);
        this.usuarioBo = new UsuarioBo();
        this.usuarioBo.validarCamposDeTexto(nome, objetivo, peso, altura, genero, imc, tmb);
        definirDadosUsuario(nome, objetivo, peso, altura, genero, imc, tmb);
    }


    private void definirDadosUsuario(EditText nome, EditText objetivo, EditText peso, EditText altura, EditText genero, EditText imc, EditText tmb) throws SQLException {
        Usuario usuarioCorrente = new Usuario();
        usuarioCorrente.setNomeUsuario(nome.getText().toString());
        usuarioCorrente.setObjetivo(objetivo.getText().toString());
        usuarioCorrente.setPeso(Float.parseFloat(peso.getText().toString()));
        usuarioCorrente.setAltura(Float.parseFloat(altura.getText().toString()));
        usuarioCorrente.setGenero(genero.getText().toString());
        usuarioCorrente.setImc(Float.parseFloat(imc.getText().toString()));
        usuarioCorrente.setTmb(Float.parseFloat(tmb.getText().toString()));
        verificarUsuario(usuarioCorrente);
    }

    private void verificarUsuario(Usuario usuarioCorrente) throws SQLException {
        this.usuarioBo = new UsuarioBo();
        try {
            if (this.usuarioBo.verificarUsuarioAntesCadastro(usuarioCorrente, AdicionarUsuario.this) == 1) {
                atualizar(usuarioCorrente);
            }
            if (this.usuarioBo.verificarUsuarioAntesCadastro(usuarioCorrente, AdicionarUsuario.this) == 2) {
                salvar(usuarioCorrente);
            }
        }catch (UsuarioCadastradoException u){
            Toast.makeText(AdicionarUsuario.this, u.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void salvar(Usuario usuarioCorrente) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(AdicionarUsuario.this);
        UsuarioDao usuarioDao = new UsuarioDao(dh.getConnectionSource());
        usuarioDao.create(usuarioCorrente);
        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
    }

        private void atualizar(Usuario usuarioCorrente) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(AdicionarUsuario.this);
        UsuarioDao usuarioDao = new UsuarioDao(dh.getConnectionSource());

        UpdateBuilder<Usuario, Integer> updateBuilder = usuarioDao.updateBuilder();

        updateBuilder.updateColumnValue("nomeUsuario",usuarioCorrente.getNomeUsuario());
        updateBuilder.updateColumnValue("objetivo",usuarioCorrente.getObjetivo());
        updateBuilder.updateColumnValue("peso",usuarioCorrente.getPeso());
        updateBuilder.updateColumnValue("altura",usuarioCorrente.getAltura());
        updateBuilder.updateColumnValue("genero",usuarioCorrente.getGenero());
        updateBuilder.updateColumnValue("imc",usuarioCorrente.getImc());
        updateBuilder.updateColumnValue("tmb",usuarioCorrente.getTmb());


        updateBuilder.where().eq("id", usuario.getId());
        updateBuilder.update();
        Toast.makeText(this, "Alterado com sucesso!", Toast.LENGTH_SHORT).show();

    }

    private void carregarPerfilUsuario(Usuario u) {
        EditText nome = (EditText) findViewById(R.id.editTextNome);
        EditText objetivo = (EditText) findViewById(R.id.editTextObjetivo);
        EditText peso = (EditText) findViewById(R.id.editTextPeso);
        EditText altura = (EditText) findViewById(R.id.editTextAltura);
        EditText genero = (EditText) findViewById(R.id.editTextGenero);
        EditText imc = (EditText) findViewById(R.id.editTextImc);
        EditText tmb = (EditText) findViewById(R.id.editTextTmb);


            nome.setText(u.getNomeUsuario());
            objetivo.setText(u.getObjetivo());
            peso.setText(Float.toString(u.getPeso()));
            altura.setText(Float.toString(u.getAltura()));
            genero.setText(u.getGenero());
            imc.setText(Float.toString(u.getImc()));
            tmb.setText(Float.toString(u.getTmb()));

    }
}
