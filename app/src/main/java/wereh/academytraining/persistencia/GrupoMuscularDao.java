package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.GrupoMuscular;

/**
 * Created by Were on 05/04/2017.
 */

public class GrupoMuscularDao extends BaseDaoImpl<GrupoMuscular, Integer> {
    public GrupoMuscularDao(ConnectionSource connectionSource) throws SQLException {
        super(GrupoMuscular.class);
        setConnectionSource(connectionSource);
        initialize();
    }

}
