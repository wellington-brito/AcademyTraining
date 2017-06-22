package wereh.academytraining.negocio;

import android.content.Context;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.apresentacao.AdicionarAlimentoConsumidos;
import wereh.academytraining.apresentacao.AlimentosListaActivity;
import wereh.academytraining.apresentacao.AlimentosConsumidosLista;
import wereh.academytraining.entidade.Alimento;
import wereh.academytraining.entidade.AlimentosConsumidos;
import wereh.academytraining.exceptions.TreinoDuplicadoException;
import wereh.academytraining.persistencia.AlimentoDao;
import wereh.academytraining.persistencia.AlimentosConsumidosDao;
import wereh.academytraining.persistencia.DatabaseHelper;

/**
 * Created by wellington on 10/05/17.
 */

public class AlimentosConsumidosBo {

    private DatabaseHelper dh;
    private AlimentosConsumidosDao alimentoConsumidoDao;


    public AlimentosConsumidosBo(){}

    public void salvar(AlimentosConsumidos alimentosConsumidosCorrente, AdicionarAlimentoConsumidos alimentosListaActivity) throws SQLException {
        DatabaseHelper dh = new DatabaseHelper(alimentosListaActivity);
        AlimentosConsumidosDao alimentosConsumidosDao = new AlimentosConsumidosDao(dh.getConnectionSource());
        alimentosConsumidosDao.create(alimentosConsumidosCorrente);
        Toast.makeText(alimentosListaActivity, "Salvo com sucesso!!", Toast.LENGTH_SHORT).show();
    }

    public List<AlimentosConsumidos> buscarAlimentosCheckList(Context context) throws SQLException {
        List<AlimentosConsumidos> lista = null;
        DatabaseHelper dh = new DatabaseHelper(context);
        AlimentosConsumidosDao alimentosConsumidosDao = new AlimentosConsumidosDao(dh.getConnectionSource());
        try {
            lista = alimentosConsumidosDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void apagar(AlimentosConsumidos alimentosConsumidos, AlimentosConsumidosLista alimentosListaActivity) throws SQLException{
        DatabaseHelper dh = new DatabaseHelper(alimentosListaActivity);
        AlimentosConsumidosDao alimentosConsumidosDao = new AlimentosConsumidosDao(dh.getConnectionSource());
            alimentosConsumidosDao.deleteById(alimentosConsumidos.getId());

    }


    public void verificarLista(AlimentosConsumidos alimentoConsumido, AlimentosListaActivity adicionarAlimentoConsumidos) throws SQLException{
         this.dh = new DatabaseHelper(adicionarAlimentoConsumidos);
            this.alimentoConsumidoDao = new AlimentosConsumidosDao(this.dh.getConnectionSource());
            List<AlimentosConsumidos> listaAlimentos = alimentoConsumidoDao.queryForAll();

            for(AlimentosConsumidos a: listaAlimentos){
                if((a.getAlimennto().equals(alimentoConsumido.getAlimennto() ))){
                    throw new TreinoDuplicadoException("Já se encontra na lista de alimentos consumidos!");
                }
            }


    }
}
