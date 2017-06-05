package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.Refeicao;

/**
 * Created by wellington on 05/06/17.
 */

public class RefeicaoDao extends BaseDaoImpl<Refeicao, Integer> {
    public RefeicaoDao(ConnectionSource connectionSource) throws SQLException {
        super(Refeicao.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
