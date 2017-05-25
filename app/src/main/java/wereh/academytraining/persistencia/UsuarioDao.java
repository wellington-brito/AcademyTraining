package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.Usuario;

/**
 * Created by wellington on 04/05/17.
 */

public class UsuarioDao extends BaseDaoImpl<Usuario, Integer> {

    public UsuarioDao(ConnectionSource connectionSource) throws SQLException {
        super(Usuario.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
