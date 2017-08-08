package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.Corpo;

/**
 * Created by wellington on 04/08/17.
 */

public class CorpoDao extends BaseDaoImpl<Corpo, Integer> {
    public CorpoDao(ConnectionSource connectionSource) throws SQLException {
        super(Corpo.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
