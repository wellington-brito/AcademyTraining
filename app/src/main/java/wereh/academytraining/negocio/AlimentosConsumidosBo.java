package wereh.academytraining.negocio;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.apresentacao.AdicionarAlimentoConsumidos;
import wereh.academytraining.apresentacao.AlimentosListaActivity;
import wereh.academytraining.apresentacao.AlimentosConsumidosLista;
import wereh.academytraining.entidade.AlimentosConsumidos;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.ObjetoDuplicadoException;
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
                    throw new ObjetoDuplicadoException("Já se encontra na lista de alimentos consumidos!");
                }
            }
    }


    public void atualizar(AlimentosConsumidos alimentoConsumido, AdicionarAlimentoConsumidos adicionarAlimentoConsumidos) throws SQLException {
        this.dh = new DatabaseHelper(adicionarAlimentoConsumidos);
        AlimentosConsumidosDao alimentoConsDao = new AlimentosConsumidosDao(this.dh.getConnectionSource());
        UpdateBuilder<AlimentosConsumidos, Integer> updateBuilder = alimentoConsDao.updateBuilder();
        updateBuilder.updateColumnValue("numeroPorcoes",alimentoConsumido.getNumeroPorcoes());
        updateBuilder.updateColumnValue("dia",alimentoConsumido.getDia());
        updateBuilder.where().eq("id", alimentoConsumido.getId());
        updateBuilder.update();
        Toast.makeText(adicionarAlimentoConsumidos, "Porção consumida atualizada com sucesso!!", Toast.LENGTH_SHORT).show();
    }

    public void validarCamposDeTexto(EditText quantidade) {
        if (quantidade.getText().toString().equals("")) {
            throw new CampoObrigatorioException("NÚMERO DE PORÇÕES");
        }
    }

}
