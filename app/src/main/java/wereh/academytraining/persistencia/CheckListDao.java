package wereh.academytraining.persistencia;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.CheckList;

/**
 * Created by wellington on 05/06/17.
 */

public class CheckListDao extends BaseDaoImpl<CheckList, Integer> {
    public CheckListDao(ConnectionSource connectionSource) throws SQLException {
        super(CheckList.class);
        setConnectionSource(connectionSource);
        initialize();
    }

}
