package wereh.academytraining.negocio;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.apresentacao.ContadorTreino;
import wereh.academytraining.entidade.TempoGasto;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.TempoGastoDao;

/**
 * Created by wellington on 06/07/17.
 */

public class TempoGastoBo {
    DatabaseHelper dh;

    public void salvar(TempoGasto tempoGastoCorrente, Context context) throws SQLException {
        this.dh = new DatabaseHelper(context);
        TempoGastoDao tempoGastoDaoDao = new TempoGastoDao(this.dh.getConnectionSource());
        tempoGastoDaoDao.create(tempoGastoCorrente);
    }

    public List<TempoGasto> buscarTempos(Context context) throws SQLException {
        dh = new DatabaseHelper(context);
        TempoGastoDao tempoGastoDaoDao = new TempoGastoDao(this.dh.getConnectionSource());
        List<TempoGasto> list = tempoGastoDaoDao.queryForAll();
        return list;
    }

    public void apagarTempo(TempoGasto tempoGastoCorrente, ContadorTreino contadorTreinoActivity) throws SQLException {
        this.dh = new DatabaseHelper(contadorTreinoActivity);
        TempoGastoDao tempoGastoDao = new TempoGastoDao(this.dh.getConnectionSource());
        tempoGastoDao.deleteById(tempoGastoCorrente.getId());
    }
}
