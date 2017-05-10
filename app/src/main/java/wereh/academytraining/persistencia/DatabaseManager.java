package wereh.academytraining.persistencia;

import android.content.Context;

/**
 * Created by Were on 05/04/2017.
 */

public class DatabaseManager {
    private static DatabaseManager instance;
    private DatabaseHelper helper;

    public static void init(Context ctx) {
        if (null == instance) {
            instance = new DatabaseManager(ctx);
        }
    }

    public static DatabaseManager getInstance( ) {
        return instance;
    }

    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }


//    /**
//     *
//     * @return Listagem de dados
//     */
//    public List<GrupoMuscular> findAllGruposMusculares() {
//        List<GrupoMuscular> list = null;
//        try {
//            list = getHelper().getAllGrupoMuscular().queryBuilder()
//                    .orderBy("id", false).query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (java.sql.SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    public List<Exercicio> findAllExercicios() {
//        List<Exercicio> list = null;
//        try {
//            list = getHelper().getAllExercicio().queryBuilder()
//                    .orderBy("id", false).query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (java.sql.SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

}
