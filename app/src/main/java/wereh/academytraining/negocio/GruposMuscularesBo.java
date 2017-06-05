package wereh.academytraining.negocio;

import android.content.Context;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.apresentacao.HomeActivity;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.GrupoMuscularDao;

/**
 * Created by wellington on 17/05/17.
 */

public class GruposMuscularesBo {

    private List<GrupoMuscular> listaGruposMusculares;
    private ListView mListView;
    private DatabaseHelper dh;
    private GrupoMuscularDao grupoMuscularDao;
    private GrupoMuscular grupoMuscular;

    public GruposMuscularesBo(){

    }

    public void cadastrarGruposMusculares(HomeActivity homeActivity){

        try {
            this.dh = new DatabaseHelper(homeActivity);
            this.grupoMuscularDao = new GrupoMuscularDao(this.dh.getConnectionSource());
            this.listaGruposMusculares = new ArrayList<GrupoMuscular>();

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Peitoral");
            //grupoMuscular.setQuantidadeAlimetnos();
            this.listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Bíceps");
            //grupoMuscular.setQuantidadeAlimetnos();
            this.listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Tríceps");
            //grupoMuscular.setQuantidadeAlimetnos();
            this.listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Costas");
            //grupoMuscular.setQuantidadeAlimetnos();
            this.listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Deltóides");
            //grupoMuscular.setQuantidadeAlimetnos();
            this.listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Panturrílhas");
            //grupoMuscular.setQuantidadeAlimetnos();
            this.listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Quadríceps");
            //grupoMuscular.setQuantidadeAlimetnos();
            this.listaGruposMusculares.add(grupoMuscular);

            grupoMuscular = new GrupoMuscular();
            grupoMuscular.setNomeGrupoMuscular("Abdômen");
            //grupoMuscular.setQuantidadeAlimetnos();
            this.listaGruposMusculares.add(grupoMuscular);

            for (GrupoMuscular gm : this.listaGruposMusculares) {
                grupoMuscularDao.create(gm);
            }
            grupoMuscularDao = null;

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public List<GrupoMuscular> buscarGrupos(Context context) throws SQLException {
        List<GrupoMuscular> lista = null;
        this.dh = new DatabaseHelper(context);
        this.grupoMuscularDao = new GrupoMuscularDao(this.dh.getConnectionSource());
        try {
            lista = this.grupoMuscularDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

