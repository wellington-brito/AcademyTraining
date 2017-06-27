package wereh.academytraining.negocio;

import android.content.Context;
import android.widget.ListView;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.AlimentosListaActivity;
import wereh.academytraining.apresentacao.AlimentosListaAdapter;
import wereh.academytraining.entidade.Alimento;
import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.persistencia.AlimentoDao;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.ExercicioDao;
import wereh.academytraining.persistencia.GrupoAlimentarDao;

/**
 * Created by wellington on 02/06/17.
 */

public class AlimentoBo {

    private DatabaseHelper dh;
    private AlimentoDao alimentoDao;
    private GrupoAlimentarDao gaDao;
    private Alimento alimento;
    private List<Alimento> alimentos;
    private List<GrupoAlimentar> gruposAlimentares;

    public  AlimentoBo(){}

    public List<Alimento> buscarAlimentos(Context context) throws SQLException {
        List<Alimento> lista = null;
        this.dh = new DatabaseHelper(context);
        this.alimentoDao = new AlimentoDao(this.dh.getConnectionSource());
        try {
            lista = this.alimentoDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void cadastrarAlimentos(Context context) throws SQLException {
        try {
            this.dh = new DatabaseHelper(context);
            this.gaDao = new GrupoAlimentarDao(this.dh.getConnectionSource());
            this.alimentoDao = new AlimentoDao(this.dh.getConnectionSource());
            this.alimentos = new ArrayList<Alimento>();
            this.gruposAlimentares = new ArrayList<GrupoAlimentar>();
            this.gruposAlimentares = this.gaDao.queryForAll();

            for (GrupoAlimentar ga : gruposAlimentares) {
                if (ga.getNomeGrupoAlimentar().equals("Pães, Cereais, Raízes e Tubérculos")) {
                    alimento = new Alimento();
                    alimento.setNomeAlimento("Amido de milho - maisena");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 colheres de sopa");
                    alimento.setPeso(40.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Arroz branco cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 colheres de sopa");
                    alimento.setPeso(125.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Arroz integral cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 colheres de sopa");
                    alimento.setPeso(140.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Batata doce cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 colheres de servir");
                    alimento.setPeso(150.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Batata frita tipo “chips” (salgadinho)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄3 pacote");
                    alimento.setPeso(27.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Batata frita (fatia)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de servir");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);
                }

                if (ga.getNomeGrupoAlimentar().equals("Hortaliças")) {
                    alimento = new Alimento();
                    alimento.setNomeAlimento("Abóbora cozida (menina, japonesa, moranga)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 colheres de sopa");
                    alimento.setPeso(53.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Alface");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("15 folhas");
                    alimento.setPeso(120.0);
                    alimentos.add(alimento);
                }
            }
            for (Alimento al : alimentos) {
                alimentoDao.create(al);
            }
            alimentos = null;

        }catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public  List<Alimento> carregarLista(AlimentosListaActivity alimentosListaActivity, int idSelected) throws SQLException {
        this.dh = new DatabaseHelper(alimentosListaActivity);
        this.alimentoDao  = new AlimentoDao(dh.getConnectionSource());
        try {
            QueryBuilder<Alimento, Integer> queryBuilder = this.alimentoDao.queryBuilder();
            queryBuilder.where().eq(Alimento.IDGRUPOALIMENTAR_FIELD_NAME, idSelected);
            PreparedQuery<Alimento> preparedQuery = queryBuilder.prepare();
            this.alimentos = this.alimentoDao.query(preparedQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.alimentos;
    }

}
