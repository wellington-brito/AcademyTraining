package wereh.academytraining.negocio;

import android.content.Context;
import android.widget.EditText;

import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import wereh.academytraining.apresentacao.AdicionarPlanejamento;
import wereh.academytraining.apresentacao.fragments.DietaAdatper;
import wereh.academytraining.entidade.Dieta;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.entidade.Treino;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.DependenciaDeDietaException;
import wereh.academytraining.exceptions.DependenciaDeTreinoException;
import wereh.academytraining.exceptions.TreinoDuplicadoException;
import wereh.academytraining.apresentacao.fragments.FragmentActivityPlanejamentos;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.DietaDao;
import wereh.academytraining.persistencia.PlanejamentoDao;
import wereh.academytraining.persistencia.TreinoDao;

/**
 * Created by wellington on 10/05/17.
 */

public class PlanejamentoBo {

    DatabaseHelper dh;
    String a = "";

    public PlanejamentoBo(){

    }

    public void validarCamposDeTexto(EditText nomePlanejamento, EditText objetivo, EditText vezesNaSemana, EditText dataInicio, EditText validade) throws ParseException {
        if (nomePlanejamento.getText().toString().equals("")) {
            throw new CampoObrigatorioException("PLANEJAMENTO");
        }
        if (objetivo.getText().toString().equals("")) {
            throw new CampoObrigatorioException("OBJETIVO");
        }
        if (vezesNaSemana.getText().toString().equals("")) {
            throw new CampoObrigatorioException("VEZES NA SEMANA");
        }

        if(dataInicio.getText().toString().equals("")){
            throw new CampoObrigatorioException("DATA INICIO");
        }

        String data = dataInicio.getText().toString();
        String d;
        d = data.substring(0,2);
        int dia = Integer.parseInt(d);
        d = data.substring(3,5);
        int mes = Integer.parseInt(d);

        if(dia > 31 || mes >12){
            throw new CampoObrigatorioException("DATA INVÁLIDA");
        }

        if(dataInicio.getText().toString().equals("")){
            throw new CampoObrigatorioException("DATA INICIO");
        }

        if(validade.getText().toString().equals("")){
            throw new CampoObrigatorioException("VALIADADE");
        }
    }

    public void salvar(Planejamento planejamentoCorrente, AdicionarPlanejamento adicionarPlanejamento) throws SQLException {
        this.dh = new DatabaseHelper(adicionarPlanejamento);
        PlanejamentoDao planejamentoDao = new PlanejamentoDao(this.dh.getConnectionSource());
        planejamentoDao.create(planejamentoCorrente);
    }

    public void apagarPlanejamento(Planejamento planejamento, FragmentActivityPlanejamentos fichaDeTreino, List<Dieta> listadDietasAssociadas) throws SQLException {
        this.dh = new DatabaseHelper(fichaDeTreino.getContext());
        PlanejamentoDao planejamentoDao = new PlanejamentoDao(this.dh.getConnectionSource());
        DietaDao dietaDao = new DietaDao(this.dh.getConnectionSource());
        TreinoDao treinoDao = new TreinoDao(this.dh.getConnectionSource());
        List<Treino> listaTreinos = treinoDao.queryForEq("idPlanejamento", planejamento.getId());

        for (Treino t: listaTreinos){
            if (t.getIdPlanejamento() == planejamento.getId()){
                throw new DependenciaDeTreinoException("Apague todos os treinos relacionados a este planejamento e tente novamente!");
            }
        }

        for(Dieta d: listadDietasAssociadas){
            if (d.getId() == planejamento.getId()){
                throw new DependenciaDeDietaException("Apague todos as dietas a este planejamento e tente novamente!");
            }
        }
        planejamentoDao.deleteById(planejamento.getId());
    }

    public void verificarPlanejamento(Planejamento planejamentoCorrente, AdicionarPlanejamento adicionarPlanejamento) throws SQLException {
        this.dh = new DatabaseHelper(adicionarPlanejamento);
        PlanejamentoDao planejamentoDao = new PlanejamentoDao(this.dh.getConnectionSource());
        List<Planejamento> listaPlanejamento = planejamentoDao.queryForAll();

        for(Planejamento p: listaPlanejamento){
            if((p.getId() == planejamentoCorrente.getId() || p.getNomePlanejamento().equals(planejamentoCorrente.getNomePlanejamento() )) && p.getId() == planejamentoCorrente.getId()){
                throw new TreinoDuplicadoException("Já existe um planejamento igual!");
            }
        }
    }

    public void atualizar(Planejamento planejamentoCorrente, AdicionarPlanejamento adicionarPlanejamento, Planejamento p) throws SQLException {
        this.dh = new DatabaseHelper(adicionarPlanejamento);
        PlanejamentoDao planejamentoDao = new PlanejamentoDao(this.dh.getConnectionSource());
        UpdateBuilder<Planejamento, Integer> updateBuilder;
        updateBuilder = configurarUpdateBuilder(planejamentoCorrente, planejamentoDao, p);
        updateBuilder.update();
    }

    private UpdateBuilder<Planejamento,Integer> configurarUpdateBuilder(Planejamento planejamentoCorrente, PlanejamentoDao planejamentoDao, Planejamento p) throws SQLException {
        UpdateBuilder<Planejamento, Integer> updateBuilder = planejamentoDao.updateBuilder();
        updateBuilder.updateColumnValue("nomePlanejamento",planejamentoCorrente.getNomePlanejamento());
        updateBuilder.updateColumnValue("objetivo",planejamentoCorrente.getObjetivo());
        updateBuilder.updateColumnValue("vezesNaSemana",planejamentoCorrente.getVezesNaSemana());
        updateBuilder.updateColumnValue("observacao",planejamentoCorrente.getObservacao());
        updateBuilder.updateColumnValue("dataInicio",planejamentoCorrente.getDataInicio());
        updateBuilder.updateColumnValue("validade",planejamentoCorrente.getValidade());
        updateBuilder.where().eq("id", p.getId());
        return updateBuilder;
    }

}
