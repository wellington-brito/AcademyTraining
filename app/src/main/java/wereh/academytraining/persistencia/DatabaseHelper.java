package wereh.academytraining.persistencia;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.entidade.Agua;
import wereh.academytraining.entidade.Corpo;
import wereh.academytraining.entidade.LembretesStatus;
import wereh.academytraining.entidade.Alimento;
import wereh.academytraining.entidade.AlimentosConsumidos;
import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.entidade.Carga;
import wereh.academytraining.entidade.Suplemento;
import wereh.academytraining.entidade.TempoGasto;
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.entidade.Usuario;

/**
 * Created by Were on 05/04/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // nomeGrupoMuscular do database para sua aplicacao
    private static final String DATABASE_NAME = "bd_projeto";

    // sempre que voce mudar objetos em seu database incremente a versao
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

//    // the DAO utilizado para acessar os dados
//    private Dao<GrupoMuscular, Integer> grupoMuscularDao = null;
//    private Dao<Exercicio, Integer> exercicioDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, GrupoMuscular.class);
            TableUtils.createTable(connectionSource, Exercicio.class);
            TableUtils.createTable(connectionSource, Treino.class);
            TableUtils.createTable(connectionSource, Usuario.class);
            TableUtils.createTable(connectionSource, Planejamento.class);
            TableUtils.createTable(connectionSource, GrupoAlimentar.class);
            TableUtils.createTable(connectionSource, AlimentosConsumidos.class);
            TableUtils.createTable(connectionSource, Alimento.class);
            TableUtils.createTable(connectionSource, TempoGasto.class);
            TableUtils.createTable(connectionSource, LembretesStatus.class);
            TableUtils.createTable(connectionSource, Suplemento.class);
            TableUtils.createTable(connectionSource, Agua.class);
            TableUtils.createTable(connectionSource, Corpo.class);
            TableUtils.createTable(connectionSource, Carga.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            List<String> allSql = new ArrayList<String>();
            switch (oldVersion) {
                case 1:
                   // allSql.add("create table Corpo");
                 //   allSql.add("alter table Usuario add column `nivelAtividade` TEXT");
                // allSql.add("alter table AdData add column `new_col2` VARCHAR");
            }
            for (String sql : allSql) {
                db.execSQL(sql);
            }
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade",
                    e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close(){
        super.close();
    }


}

