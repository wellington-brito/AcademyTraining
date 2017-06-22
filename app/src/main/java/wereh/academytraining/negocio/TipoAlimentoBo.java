package wereh.academytraining.negocio;

import android.content.Context;
import android.widget.ListView;

import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.entidade.Alimento;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.entidade.TipoAlimento;
import wereh.academytraining.persistencia.AlimentoDao;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.GrupoMuscularDao;
import wereh.academytraining.persistencia.TipoAlimentoDao;

/**
 * Created by wellington on 19/06/17.
 */

public class TipoAlimentoBo   {

    public TipoAlimentoBo(){}

    private List<TipoAlimento> listaTipos;
    private ListView mListView;
    private DatabaseHelper dh;
    private TipoAlimentoDao tipoAlimentoDao;
    private TipoAlimento tipoAlimento;

    public void cadastrarGruposMusculares(Context context){

        try {
            this.dh = new DatabaseHelper(context);
            this.tipoAlimentoDao = new TipoAlimentoDao(this.dh.getConnectionSource());
            this.listaTipos = new ArrayList<TipoAlimento>();

            tipoAlimento = new TipoAlimento();
            tipoAlimento.setNomeTipo("Construtores");
            tipoAlimento.setDescricao("São os alimentos ricos em PROTEÍNAS. Eles vão atuar na formação da massa muscular, regenerar tecidos como a \t\t\t\t\t\tpele, e são muito importante na fase de crescimento, pois atua na formação dos órgãos, músculos e tecidos, \t\t\t\t\t\talém de participar de funções metabólicas, como produção de algumas enzimas.");
            this.listaTipos.add(tipoAlimento);

            tipoAlimento = new TipoAlimento();
            tipoAlimento.setNomeTipo("Reguladores");
            tipoAlimento.setDescricao("São os alimentos ricos em VITAMINAS, MINERAIS E FIBRAS. Esses são responsáveis por regular nosso sistema \t\t\t\timunológico, endócrino, nervoso e respiratório. E as fibras vão regular nossa função intestinal.");
            this.listaTipos.add(tipoAlimento);

            tipoAlimento = new TipoAlimento();
            tipoAlimento.setNomeTipo("Energéticos");
            tipoAlimento.setDescricao("São os alimentos ricos em CARBOIDRATOS e LIPÍDIOS. Eles vão fornecer energia, que é fundamental para nossa sobrevivência.");
            this.listaTipos.add(tipoAlimento);

            for (TipoAlimento ta : this.listaTipos) {
                tipoAlimentoDao.create(ta);
            }
            tipoAlimentoDao = null;

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<TipoAlimento> buscarTiposAlimentos(Context context) throws SQLException {
        List<TipoAlimento> lista = null;
        this.dh = new DatabaseHelper(context);
        this.tipoAlimentoDao = new TipoAlimentoDao(this.dh.getConnectionSource());
        try {
            lista = this.tipoAlimentoDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
