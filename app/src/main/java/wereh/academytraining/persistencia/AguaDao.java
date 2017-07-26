package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.Agua;

/**
 * Created by wellington on 26/07/17.
 */

public class AguaDao extends BaseDaoImpl<Agua, Integer> {

    public AguaDao(ConnectionSource connectionSource) throws SQLException {
        super(Agua.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
