package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.Planejamento;

/**
 * Created by wellington on 07/05/17.
 */

public class PlanejamentoDao  extends BaseDaoImpl<Planejamento, Integer> {
    public PlanejamentoDao(ConnectionSource connectionSource) throws SQLException {
        super(Planejamento.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
