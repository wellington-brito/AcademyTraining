package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.Alimento;

/**
 * Created by wellington on 06/06/17.
 */

public class AlimentoDao extends BaseDaoImpl<Alimento, Integer> {

    public AlimentoDao(ConnectionSource connectionSource) throws SQLException {
        super(Alimento.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
