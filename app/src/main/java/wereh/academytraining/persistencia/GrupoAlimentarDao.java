package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.entidade.GrupoMuscular;

/**
 * Created by wellington on 05/06/17.
 */

public class GrupoAlimentarDao  extends BaseDaoImpl<GrupoAlimentar, Integer> {
    public GrupoAlimentarDao(ConnectionSource connectionSource) throws SQLException {
        super(GrupoAlimentar.class);
        setConnectionSource(connectionSource);
        initialize();
    }

}
