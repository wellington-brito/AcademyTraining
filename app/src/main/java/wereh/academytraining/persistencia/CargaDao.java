package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.Carga;

/**
 * Created by wellington on 06/08/17.
 */

public class CargaDao extends BaseDaoImpl<Carga, Integer> {
public CargaDao(ConnectionSource connectionSource) throws SQLException {
        super(Carga.class);
        setConnectionSource(connectionSource);
        initialize();
        }
}
