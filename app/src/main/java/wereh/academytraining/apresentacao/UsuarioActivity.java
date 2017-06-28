package wereh.academytraining.apresentacao;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.logger.Log;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Usuario;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.UsuarioCadastradoException;
import wereh.academytraining.negocio.UsuarioBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.UsuarioDao;

public class UsuarioActivity extends AppCompatActivity {

    int verificardor;
    Usuario usuario;
    int qntd;
    UsuarioBo usuarioBo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    verificarUsuario();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (UsuarioCadastradoException u) {
                    Toast.makeText(UsuarioActivity.this, u.getMessage(), Toast.LENGTH_SHORT).show();
                    try {
                        DatabaseHelper dh = new DatabaseHelper(UsuarioActivity.this);
                        UsuarioDao usuarioDao = new UsuarioDao(dh.getConnectionSource());
                        List<Usuario> listaUsuarios = usuarioDao.queryForAll();
                        usuarioDao.delete(listaUsuarios);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            this.usuarioBo = new UsuarioBo();
            this.usuarioBo.carregarPerfilUsuario(UsuarioActivity.this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void verificarUsuario() throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(UsuarioActivity.this);
        UsuarioDao usuarioDao = new UsuarioDao(dh.getConnectionSource());
        List<Usuario> listaUsuarios = usuarioDao.queryForAll();
        if (listaUsuarios != null) {
            qntd = listaUsuarios.size();
            if (qntd < 1) {
                Intent i = new Intent(UsuarioActivity.this, AdicionarUsuario.class);
                startActivity(i);
            } else if (qntd == 1) {
                usuario = listaUsuarios.get(0);
                Intent i = new Intent(UsuarioActivity.this, AdicionarUsuario.class);
                i.putExtra("usuario", (Parcelable) usuario);
                startActivity(i);
            } else {
                throw new UsuarioCadastradoException("Existe mais de um usuario registrado!");
            }
        }
    }
}



