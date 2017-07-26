package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.LembretesStatus;

/**
 * Created by wellington on 17/07/17.
 */

public class LembretesStatusDao extends BaseDaoImpl<LembretesStatus, Integer> {
    public LembretesStatusDao(ConnectionSource connectionSource) throws SQLException {
        super(LembretesStatus.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
