package wereh.academytraining.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.entidade.Treino;

/**
 * Created by wellington on 19/04/17.
 */

public class TreinoDao extends BaseDaoImpl<Treino, Integer> {


    public TreinoDao(ConnectionSource connectionSource) throws SQLException {
        super(Treino.class);
        setConnectionSource(connectionSource);
        initialize();
    }



}
