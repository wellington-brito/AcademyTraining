package wereh.academytraining.negocio;

import android.widget.EditText;

import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.apresentacao.AdicionarDietaActivity;
import wereh.academytraining.apresentacao.fragments.FragmentActivityPlanejamentos;
import wereh.academytraining.apresentacao.fragments.FragmentDietaActivity;
import wereh.academytraining.entidade.Dieta;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.entidade.Refeicao;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.DependenciaDeTreinoException;
import wereh.academytraining.exceptions.TreinoDuplicadoException;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.DietaDao;
import wereh.academytraining.persistencia.PlanejamentoDao;
import wereh.academytraining.persistencia.RefeicaoDao;

/**
 * Created by wellington on 10/05/17.
 */

public class DietaBo {


    DatabaseHelper dh;
    DietaDao dietaDao;
    public DietaBo(){

    }

    public void validarCamposDeTexto(EditText nomeDieta, EditText planejamento, EditText vezesNaSemana, EditText dataInicio, EditText validade) {
        if (nomeDieta.getText().toString().equals("")) {
            throw new CampoObrigatorioException("NOME DIETA");
        }
        if (planejamento.getText().toString().equals("")) {
            throw new CampoObrigatorioException("PLANEJAMENTO");
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

    public void verificarDieta(Dieta dietaCorrente, AdicionarDietaActivity adicionarDietaActivity)throws SQLException{
        this.dh = new DatabaseHelper(adicionarDietaActivity);
        this.dietaDao = new DietaDao(this.dh.getConnectionSource());
        List<Dieta> listaDieta = this.dietaDao.queryForAll();

        for(Dieta d: listaDieta){
            if((d.getId() == dietaCorrente.getId() || d.getNomeDieta().equals(dietaCorrente.getNomeDieta() )) && d.getId() == dietaCorrente.getId()){
                throw new TreinoDuplicadoException("Já existe uma dieta igual!");
            }
        }
    }

    public void salvar(Dieta dietaCorrente, AdicionarDietaActivity adicionarDietaActivity) throws SQLException {
        this.dh = new DatabaseHelper(adicionarDietaActivity);
        this.dietaDao = new DietaDao(this.dh.getConnectionSource());
        dietaDao.create(dietaCorrente);
    }

    public void atualizar(Dieta dietaCorrente, AdicionarDietaActivity adicionarDietaActivity, Dieta d) throws SQLException{
        this.dh = new DatabaseHelper(adicionarDietaActivity);
        DietaDao dietaDao = new DietaDao(this.dh.getConnectionSource());
        UpdateBuilder<Dieta, Integer> updateBuilder;
        updateBuilder = configurarUpdateBuilder(dietaCorrente, dietaDao, d);
        updateBuilder.update();
    }

    private UpdateBuilder<Dieta,Integer> configurarUpdateBuilder(Dieta dietaCorrente, DietaDao dietaDao, Dieta d) throws SQLException{
        UpdateBuilder<Dieta, Integer> updateBuilder = dietaDao.updateBuilder();
        updateBuilder.updateColumnValue("nomeDieta",dietaCorrente.getNomeDieta());
        updateBuilder.updateColumnValue("idPlanejamento",dietaCorrente.getIdPlanejamento());
        updateBuilder.updateColumnValue("vezesNaSemana",dietaCorrente.getVezesNaSemana());
        updateBuilder.updateColumnValue("observacao",dietaCorrente.getObservacao());
        updateBuilder.updateColumnValue("dataInicio",dietaCorrente.getDataInicio());
        updateBuilder.updateColumnValue("validade",dietaCorrente.getValidade());
        updateBuilder.where().eq("id", d.getId());
        return updateBuilder;
    }

    public List<Dieta> buscarDietaPorPlanejamento(Planejamento planejamento, FragmentActivityPlanejamentos fragmentActivityPlanejamentos) throws SQLException{
        this.dh = new DatabaseHelper(fragmentActivityPlanejamentos.getContext());
        this.dietaDao = new DietaDao(this.dh.getConnectionSource());
        List<Dieta> listaDietas = this.dietaDao.queryForEq("id", planejamento.getId());
        return listaDietas;
    }

//    public void apagarDieta(Dieta dieta, FragmentDietaActivity fragmentDietaActivity) throws SQLException{
//
//        this.dh = new DatabaseHelper(fragmentDietaActivity.getContext());
//        DietaDao dietaDao = new DietaDao(this.dh.getConnectionSource());
//        RefeicaoDao refeicaoDao = new RefeicaoDao(this.dh.getConnectionSource());
//        List<Refeicao> listaRefeicao = refeicaoDao.queryForEq("idDieta", dieta.getId());
//        for (Refeicao r: listaRefeicao){
//            if (r.getIdDieta() == dieta.getId()){
//                throw new DependenciaDeTreinoException("Apague todos as refeições relacionadas a esta dieta e tente novamente!");
//            }
//        }
//        dietaDao.deleteById(dieta.getId());
//    }
}
