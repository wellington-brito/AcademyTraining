package wereh.academytraining.negocio;

import android.content.Context;

import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.apresentacao.DetalhesConsumoAguaActivity;
import wereh.academytraining.entidade.Agua;
import wereh.academytraining.persistencia.AguaDao;
import wereh.academytraining.persistencia.DatabaseHelper;

/**
 * Created by wellington on 26/07/17.
 */

public class AguaBo {
    public void salvar(Agua aguaCorrente, DetalhesConsumoAguaActivity detalhesConsumoAguaActivity) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(detalhesConsumoAguaActivity);
        AguaDao aguaDao = new AguaDao(dh.getConnectionSource());
        aguaDao.create(aguaCorrente);
    }

    public void atualizar(Agua aguaCorrente, DetalhesConsumoAguaActivity detalhesConsumoAguaActivity, Agua agua) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(detalhesConsumoAguaActivity);
        AguaDao aguaDao = new AguaDao(dh.getConnectionSource());
        UpdateBuilder<Agua, Integer> updateBuilder;
        updateBuilder = configurarUpdateBuilder(aguaCorrente, aguaDao, agua);
        updateBuilder.update();
    }
    private UpdateBuilder<Agua, Integer> configurarUpdateBuilder(Agua aguaCorrente, AguaDao aguaDao, Agua agua) throws SQLException {
        UpdateBuilder<Agua, Integer> updateBuilder = aguaDao.updateBuilder();
        updateBuilder.updateColumnValue("metaDiaria",aguaCorrente.getMetaDIaria());
        updateBuilder.updateColumnValue("quantidadeConsumida",aguaCorrente.getQuantidadeConsumida());
        updateBuilder.updateColumnValue("dia",aguaCorrente.getDia());
        updateBuilder.where().eq("id", agua.getId());
        return updateBuilder;
    }

    public List<Agua> buscarCoonsumoDeAgua(Context context) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(context);
        AguaDao aguaDao = new AguaDao(dh.getConnectionSource());
        List<Agua> listaConsumoDeAgua = aguaDao.queryForAll();
        return listaConsumoDeAgua;
    }

    public void apagar(Agua agua, DetalhesConsumoAguaActivity detalhesConsumoAguaActivity) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(detalhesConsumoAguaActivity);
        AguaDao aguaDao = new AguaDao(dh.getConnectionSource());
        aguaDao.deleteById(agua.getId());

    }


}
