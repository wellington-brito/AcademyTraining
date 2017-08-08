package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.TempoGasto;

/**
 * Created by wellington on 06/07/17.
 */

public class TempoGastoDao extends BaseDaoImpl<TempoGasto, Integer> {
    public TempoGastoDao(ConnectionSource connectionSource) throws SQLException {
        super(TempoGasto.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
