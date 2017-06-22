package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.TipoAlimento;

/**
 * Created by wellington on 19/06/17.
 */

public class TipoAlimentoDao extends BaseDaoImpl<TipoAlimento, Integer>{
        public TipoAlimentoDao(ConnectionSource connectionSource) throws SQLException {
            super(TipoAlimento.class);
            setConnectionSource(connectionSource);
            initialize();
        }
}
