package wereh.academytraining.negocio;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.apresentacao.AdicionarTreinoActivity;
import wereh.academytraining.entidade.Carga;
import wereh.academytraining.persistencia.CargaDao;
import wereh.academytraining.persistencia.DatabaseHelper;

/**
 * Created by wellington on 06/08/17.
 */

public class CargaBo {
    public void salvar(Carga cargaCOrrente, AdicionarTreinoActivity adicionarTreinoActivity) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(adicionarTreinoActivity);
        CargaDao cargaDao = new CargaDao(dh.getConnectionSource());
        cargaDao.create(cargaCOrrente);
    }

    public List<Carga> buscarMedidas(Context context) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(context);
        CargaDao cargaDao = new CargaDao(dh.getConnectionSource());
        List<Carga> listaMedidas = cargaDao.queryForAll();
        return listaMedidas;
    }

}
