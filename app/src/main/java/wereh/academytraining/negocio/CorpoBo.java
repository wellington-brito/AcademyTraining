package wereh.academytraining.negocio;

import android.content.Context;
import android.widget.EditText;

import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.apresentacao.MedidasCorporaisActivity;
import wereh.academytraining.entidade.Corpo;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.persistencia.CorpoDao;
import wereh.academytraining.persistencia.DatabaseHelper;

/**
 * Created by wellington on 04/08/17.
 */

public class CorpoBo {
    public void salvar(Corpo corpoCorrente, MedidasCorporaisActivity medidasCorporaisActivity) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(medidasCorporaisActivity);
        CorpoDao corpoDao = new CorpoDao(dh.getConnectionSource());
        corpoDao.create(corpoCorrente);
    }

    public List<Corpo> buscarMedidas(Context context) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(context);
        CorpoDao aguaDao = new CorpoDao(dh.getConnectionSource());
        List<Corpo> listaMedidas = aguaDao.queryForAll();
        return listaMedidas;
    }

    public void verificarCampos(EditText braco, EditText perna, EditText quadril) {

        if (braco.getText().toString().equals("")) {
            throw new CampoObrigatorioException("BRAÇO");
        }
        if (perna.getText().toString().equals("")) {
            throw new CampoObrigatorioException("PERNA");
        }
        if (quadril.getText().toString().equals("")) {
            throw new CampoObrigatorioException("QUADRIL");
        }
    }
}
