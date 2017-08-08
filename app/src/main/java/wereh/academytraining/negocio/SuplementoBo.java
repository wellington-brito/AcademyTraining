package wereh.academytraining.negocio;

import android.widget.EditText;
import android.widget.ListView;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import wereh.academytraining.apresentacao.AdicionarSuplemento;
import wereh.academytraining.apresentacao.SuplementosActivity;
import wereh.academytraining.entidade.Suplemento;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.ObjetoDuplicadoException;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.SumplementoDao;

/**
 * Created by wellington on 28/07/17.
 */

public class SuplementoBo {
    DatabaseHelper dh;
    List<Suplemento> listaSupmentos;
    public void validarCamposDeTexto(EditText nomeSuplemento) throws ParseException {

        if (nomeSuplemento.getText().toString().equals("")) {
            throw new CampoObrigatorioException("NOME SUPLEMENTO");
        }

    }

    public void verificarPlanejamento(Suplemento suplementoCorrente, AdicionarSuplemento adicionarSuplemento) throws SQLException {
        this.dh = new DatabaseHelper(adicionarSuplemento);
        SumplementoDao sumplementoDao = new SumplementoDao(this.dh.getConnectionSource());
        List<Suplemento> listaSuplementos = sumplementoDao.queryForAll();

        for(Suplemento p: listaSuplementos){
            if((p.getId() == suplementoCorrente.getId() || p.getNome().equals(suplementoCorrente.getNome() )) && p.getId() == suplementoCorrente.getId()){
                throw new ObjetoDuplicadoException("Já existe um Suplemento com esse nome!");
            }
        }
    }

    public void salvar(Suplemento suplementoCorrente, AdicionarSuplemento adicionarSuplemento) throws SQLException {
        this.dh = new DatabaseHelper(adicionarSuplemento);
        SumplementoDao suplementoDao   = new SumplementoDao(this.dh.getConnectionSource());
        suplementoDao.create(suplementoCorrente);
    }

    public List<Suplemento> carregarListaSuplementos(SuplementosActivity suplementosActivity ) throws  SQLException{
        this.dh = new DatabaseHelper(suplementosActivity);
        this.dh = new DatabaseHelper(suplementosActivity);
        SumplementoDao suplementoDao = new SumplementoDao(this.dh.getConnectionSource());
        this.listaSupmentos = suplementoDao.queryForAll();

//        try {
//            QueryBuilder<Suplemento, Integer> queryBuilder = suplementoDao.queryBuilder();
//            queryBuilder.where().eq(Suplemento.HORARIO_FIELD_NAME, hora);
//            PreparedQuery<Suplemento> preparedQuery = queryBuilder.prepare();
//            this.listaSupmentosPreTreino = suplementoDao.query(preparedQuery);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return this.listaSupmentos;
    }

    public void apagar(Suplemento suplemento, SuplementosActivity suplementosActivity) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(suplementosActivity);
        SumplementoDao suplementoDao = new SumplementoDao(dh.getConnectionSource());
        suplementoDao.deleteById(suplemento.getId());

    }
}
