package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.Exercicio;

/**
 * Created by Were on 06/04/2017.
 */

public class ExercicioDao extends BaseDaoImpl<Exercicio, Integer> {

    public ExercicioDao(ConnectionSource connectionSource) throws SQLException {
        super(Exercicio.class);
        setConnectionSource(connectionSource);
        initialize();
    }

}
