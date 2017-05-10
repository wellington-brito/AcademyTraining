package wereh.academytraining.negocio;

import android.widget.EditText;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.apresentacao.AdicionarUsuario;
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

    public void validarCamposDeTexto(EditText nome, EditText objetivo, EditText peso, EditText altura, EditText genero, EditText imc, EditText tmb) throws SQLException {
        if(nome.getText().toString().equals("") ){
            throw new CampoObrigatorioException("NOME");
        }
        if(objetivo.getText().toString().equals("")){
            throw new CampoObrigatorioException("OBJETIVO");
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
        if(imc.getText().toString().equals("")){
            throw new CampoObrigatorioException("IMC");
        }
        if(tmb.getText().toString().equals("")){
            throw new CampoObrigatorioException("TMB");
        }
    }

    public int verificarUsuarioAntesCadastro(Usuario u, AdicionarUsuario adicionarUsuario) throws SQLException {
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
}
