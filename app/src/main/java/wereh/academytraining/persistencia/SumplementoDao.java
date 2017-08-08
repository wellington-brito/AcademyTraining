package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.Suplemento;

/**
 * Created by wellington on 28/07/17.
 */

public class SumplementoDao extends BaseDaoImpl<Suplemento, Integer> {
public SumplementoDao(ConnectionSource connectionSource) throws SQLException {
        super(Suplemento.class);
        setConnectionSource(connectionSource);
        initialize();
        }
}
