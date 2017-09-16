package wereh.academytraining.negocio;

import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.apresentacao.AdicionarTreinoActivity;
import wereh.academytraining.apresentacao.ContadorTreino;
import wereh.academytraining.apresentacao.DadosPlanejamentoActivity;
import wereh.academytraining.entidade.TempoGasto;
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.ObjetoDuplicadoException;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.TreinoDao;

/**
 * Created by wellington on 10/05/17.
 */

public class TreinoBo {

    DatabaseHelper dh;

    public TreinoBo() {

    }

    public void verificarTreino(Treino treinoCorrente, AdicionarTreinoActivity adicionarTreinoActivity) throws SQLException {
        this.dh = new DatabaseHelper(adicionarTreinoActivity);
        TreinoDao treinoDao = new TreinoDao(this.dh.getConnectionSource());
        List<Treino> listaTreinos = treinoDao.queryForAll();

        for (Treino t : listaTreinos) {
            if ((t.getId() == treinoCorrente.getId() || t.getNomeExercicio().equals(treinoCorrente.getNomeExercicio())) && t.getIdPlanejamento() == treinoCorrente.getIdPlanejamento()) {
                throw new ObjetoDuplicadoException("O exercício selecionado já existe neste planejamento!");
            }
        }
    }

    public void apagarTreino(Treino treinoCorrente, DadosPlanejamentoActivity dadosPlanejamentoActivity) throws SQLException {
        this.dh = new DatabaseHelper(dadosPlanejamentoActivity);
        TreinoDao treinoDao = new TreinoDao(this.dh.getConnectionSource());
        treinoDao.deleteById(treinoCorrente.getId());
    }

    public void salvar(Treino treinoCorrente, AdicionarTreinoActivity adicionarTreinoActivity) throws SQLException {
        this.dh = new DatabaseHelper(adicionarTreinoActivity);
        TreinoDao treinoDao = new TreinoDao(this.dh.getConnectionSource());
        treinoDao.create(treinoCorrente);
    }

    public void atualizar(Treino treinoCorrente, AdicionarTreinoActivity adicionarTreinoActivity, Treino t) throws SQLException {
        this.dh = new DatabaseHelper(adicionarTreinoActivity);
        TreinoDao treinoDao = new TreinoDao(this.dh.getConnectionSource());
        UpdateBuilder<Treino, Integer> updateBuilder = treinoDao.updateBuilder();
        updateBuilder.updateColumnValue("serie", treinoCorrente.getSerie());
        updateBuilder.updateColumnValue("repeticao", treinoCorrente.getRepeticao());
        updateBuilder.updateColumnValue("carga", treinoCorrente.getCarga());
        updateBuilder.updateColumnValue("intervalo", treinoCorrente.getIntervalo());
        updateBuilder.updateColumnValue("observacao", treinoCorrente.getObservacao());
        updateBuilder.updateColumnValue("nomeExercicio", treinoCorrente.getNomeExercicio());
        updateBuilder.updateColumnValue("idPlanejamento", treinoCorrente.getIdPlanejamento());
        updateBuilder.where().eq("id", t.getId());
        updateBuilder.update();
    }

    public void validarCamposDeTexto(EditText serie_edt, EditText repeticoes_edt, EditText carga_edt, EditText intevalo_edt, EditText observacao_edt, TextView exercicio_edt, TextView planejamento_edt) throws SQLException {
        if (serie_edt.getText().toString().equals("")) {
            throw new CampoObrigatorioException("SÉRIES");
        }
        if (carga_edt.getText().toString().equals("")) {
            throw new CampoObrigatorioException("CARGA");
        }
        if (repeticoes_edt.getText().toString().equals("")) {
            throw new CampoObrigatorioException("REPETIÇÕES");
        }
        if (intevalo_edt.getText().toString().equals("")) {
            throw new CampoObrigatorioException("INTERVALO");
        }
        if (exercicio_edt.getText().toString().equals("")) {
            throw new CampoObrigatorioException("EXERCÍCIO");
        }
        if (planejamento_edt.getText().toString().equals("")) {
            throw new CampoObrigatorioException("PLANEJAMENTO");
        }
    }

    public List<Treino> buscarTreinos(DadosPlanejamentoActivity dadosPlanejamentoActivity, String idPlanejamento, int id) throws SQLException {
        dh = new DatabaseHelper(dadosPlanejamentoActivity);
        TreinoDao treinoDao = new TreinoDao(this.dh.getConnectionSource());
        return treinoDao.queryForEq(idPlanejamento, id);
    }

}
