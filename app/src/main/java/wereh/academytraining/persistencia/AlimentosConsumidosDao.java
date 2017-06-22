package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.AlimentosConsumidos;

/**
 * Created by wellington on 05/06/17.
 */

public class AlimentosConsumidosDao extends BaseDaoImpl<AlimentosConsumidos, Integer> {
    public AlimentosConsumidosDao(ConnectionSource connectionSource) throws SQLException {
        super(AlimentosConsumidos.class);
        setConnectionSource(connectionSource);
        initialize();
    }

}
