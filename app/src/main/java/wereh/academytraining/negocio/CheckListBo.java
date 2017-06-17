package wereh.academytraining.negocio;

import android.content.Context;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.apresentacao.AlimentosListaActivity;
import wereh.academytraining.apresentacao.AlimentosPrincipaisLista;
import wereh.academytraining.apresentacao.fragments.FragmentAlimentosActivity;
import wereh.academytraining.entidade.CheckList;
import wereh.academytraining.persistencia.CheckListDao;
import wereh.academytraining.persistencia.DatabaseHelper;

/**
 * Created by wellington on 10/05/17.
 */

public class CheckListBo {

    public CheckListBo(){}

    public void salvar(CheckList checkListCorrente, AlimentosListaActivity alimentosListaActivity) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(alimentosListaActivity);
        CheckListDao checkListDao = new CheckListDao(dh.getConnectionSource());
        checkListDao.create(checkListCorrente);
        Toast.makeText(alimentosListaActivity, "Salvo com sucesso!!", Toast.LENGTH_SHORT).show();
    }

    public List<CheckList> buscarAlimentosCheckList(Context context) throws SQLException {
        List<CheckList> lista = null;
        DatabaseHelper dh = new DatabaseHelper(context);
        CheckListDao checkListDao = new CheckListDao(dh.getConnectionSource());
        try {
            lista = checkListDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void apagar(CheckList checkList, AlimentosPrincipaisLista alimentosListaActivity) throws SQLException{
        DatabaseHelper dh = new DatabaseHelper(alimentosListaActivity);
        CheckListDao checkListDao = new CheckListDao(dh.getConnectionSource());
            checkListDao.deleteById(checkList.getId());

    }


}
