package wereh.academytraining.negocio;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.entidade.LembretesStatus;
import wereh.academytraining.persistencia.LembretesStatusDao;
import wereh.academytraining.persistencia.DatabaseHelper;

/**
 * Created by wellington on 17/07/17.
 */

public class LembretesStatusBo {
    DatabaseHelper dh;

    public LembretesStatusBo() {
    }

    public void salvar(LembretesStatus alertaCorrente, Context context) throws SQLException {
        this.dh = new DatabaseHelper(context);
        LembretesStatusDao alimentarDao = new LembretesStatusDao(this.dh.getConnectionSource());
        alimentarDao.create(alertaCorrente);
    }

    public List<LembretesStatus> buscarFlag(Context context) throws SQLException {
        dh = new DatabaseHelper(context);
        LembretesStatusDao lembretesStatusDao = new LembretesStatusDao(this.dh.getConnectionSource());
        List<LembretesStatus> listFlags = lembretesStatusDao.queryForEq("flag", true);
        return listFlags;
    }

    public void apagar(int idStatusLembrete, Context context) throws SQLException {
        this.dh = new DatabaseHelper(context);
        LembretesStatusDao alimentarDao = new LembretesStatusDao(this.dh.getConnectionSource());
        alimentarDao.deleteById(idStatusLembrete);
    }
}
