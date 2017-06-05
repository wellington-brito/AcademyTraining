package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.Dieta;

/**
 * Created by wellington on 05/06/17.
 */

public class DietaDao extends BaseDaoImpl<Dieta, Integer> {
    public DietaDao(ConnectionSource connectionSource) throws SQLException {
        super(Dieta.class);
        setConnectionSource(connectionSource);
        initialize();
    }

}
