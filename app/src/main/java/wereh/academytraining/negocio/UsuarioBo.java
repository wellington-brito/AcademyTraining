package wereh.academytraining.negocio;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.field.types.UuidType;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.AdicionarUsuario;
import wereh.academytraining.apresentacao.ExerciciosListaActivity;
import wereh.academytraining.apresentacao.UsuarioActivity;
import wereh.academytraining.entidade.Usuario;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.UsuarioCadastradoException;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.UsuarioDao;

/**
 * Created by wellington on 10/05/17.
 */

public class UsuarioBo {

    public UsuarioBo(){

    }

    public void validarCamposDeTexto(EditText nome, EditText peso, EditText altura, EditText genero) throws SQLException {
        if(nome.getText().toString().equals("") ){
            throw new CampoObrigatorioException("NOME");
        }

        if(peso.getText().toString().equals("")){
            throw new CampoObrigatorioException("PESO");
        }
        if(altura.getText().toString().equals("")){
            throw new CampoObrigatorioException("ALTURA");
        }
        if(genero.getText().toString().equals("")){
            throw new CampoObrigatorioException("GÊNERO");
        }

    }

    public int verificarUsuarioAntesCadastro(Usuario usuarioCorrente, AdicionarUsuario adicionarUsuario) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(adicionarUsuario);
        UsuarioDao usuarioDao = new UsuarioDao(dh.getConnectionSource());
        List<Usuario> listaUsuarios = usuarioDao.queryForAll();

        if(listaUsuarios != null ){
            if(listaUsuarios.size() >=1) {
                //throw new UsuarioCadastradoException("Já existe algun usuario cadastrado");
                return 1; //1 para atualizar
            }else if(listaUsuarios.size() == 0  ){
                //throw new UsuarioCadastradoException("Nenhum usuario cadastrado");
                return  2; //2 para salvar
            }else{
                throw new UsuarioCadastradoException("Problemas na tabela 'usuário'!");
            }
        }
        return 0;
    }

    public void salvar(Usuario usuarioCorrente, AdicionarUsuario adicionarUsuario) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(adicionarUsuario);
        UsuarioDao usuarioDao = new UsuarioDao(dh.getConnectionSource());
        usuarioDao.create(usuarioCorrente);
    }

    public void atualizar(Usuario usuarioCorrente, AdicionarUsuario adicionarUsuario, Usuario usuario) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(adicionarUsuario);
        UsuarioDao usuarioDao = new UsuarioDao(dh.getConnectionSource());
        UpdateBuilder<Usuario, Integer> updateBuilder;
        updateBuilder = configurarUpdateBuilder(usuarioCorrente, usuarioDao, usuario);
        updateBuilder.update();
    }

    private UpdateBuilder<Usuario, Integer> configurarUpdateBuilder(Usuario usuarioCorrente, UsuarioDao usuarioDao, Usuario usuario) throws SQLException {
        UpdateBuilder<Usuario, Integer> updateBuilder = usuarioDao.updateBuilder();
        updateBuilder.updateColumnValue("nomeUsuario",usuarioCorrente.getNomeUsuario());

        updateBuilder.updateColumnValue("peso",usuarioCorrente.getPeso());
        updateBuilder.updateColumnValue("altura",usuarioCorrente.getAltura());
        updateBuilder.updateColumnValue("genero",usuarioCorrente.getGenero());
        updateBuilder.updateColumnValue("imc",usuarioCorrente.getImc());
        updateBuilder.updateColumnValue("tmb",usuarioCorrente.getTmb());
        updateBuilder.where().eq("id", usuario.getId());
        return updateBuilder;
    }

    public void carregarPerfilUsuario(UsuarioActivity usuarioActivity) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(usuarioActivity);
        UsuarioDao usuarioDao = new UsuarioDao(dh.getConnectionSource());
        List<Usuario> listaUsuarios = usuarioDao.queryForAll();

        TextView nome = (TextView) usuarioActivity.findViewById(R.id.textViewNome);

        TextView peso = (TextView) usuarioActivity.findViewById(R.id.textViewPeso);
        TextView altura = (TextView) usuarioActivity.findViewById(R.id.textViewAltura);
        TextView genero = (TextView) usuarioActivity.findViewById(R.id.textViewGenero);
        TextView imc = (TextView) usuarioActivity.findViewById(R.id.textViewImc);
        TextView tmb = (TextView) usuarioActivity.findViewById(R.id.textViewTmb);

        for(Usuario u : listaUsuarios) {
            nome.setText(u.getNomeUsuario());

            peso.setText(Float.toString(u.getPeso()));
            altura.setText(Float.toString(u.getAltura()));
            genero.setText(u.getGenero());
            imc.setText(Float.toString(u.getImc()));
            tmb.setText(Float.toString(u.getTmb()));
        }
    }


    public Usuario buscarUsuario(ExerciciosListaActivity exerciciosListaActivity) throws SQLException{
        DatabaseHelper dh = new DatabaseHelper(exerciciosListaActivity);
        UsuarioDao usuarioDao = new UsuarioDao(dh.getConnectionSource());
        List<Usuario> listaUsuarios = usuarioDao.queryForAll();
        if (listaUsuarios.size()>1){
            throw  new UsuarioCadastradoException("Problema na identificação dos dados de usuário");
        }else if(listaUsuarios.size() ==  0){
            throw  new UsuarioCadastradoException("Certifique-se de que já está cadastrado no aplicativo");
        }else
            return listaUsuarios.get(0);

    }
}
