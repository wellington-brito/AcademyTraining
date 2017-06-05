package wereh.academytraining.negocio;

import android.content.Context;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.apresentacao.HomeActivity;
import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.GrupoAlimentarDao;

/**
 * Created by wellington on 02/06/17.
 */

public class GrupoAlimentarBo {
    private List<GrupoAlimentar> listaGruposAlimentares;
    private ListView mListView;
    private DatabaseHelper dh;
    private GrupoAlimentarDao grupoAlimentarDao;
    private GrupoAlimentar grupoAlimentar;

    public GrupoAlimentarBo(){

    }

    public void cadastrarGruposAlimentares(HomeActivity homeActivity){

        try {
            this.dh = new DatabaseHelper(homeActivity);
            this.grupoAlimentarDao = new GrupoAlimentarDao(this.dh.getConnectionSource());
            this.listaGruposAlimentares = new ArrayList<GrupoAlimentar>();

            grupoAlimentar = new GrupoAlimentar();
            grupoAlimentar.setNomeGrupoAlimentar("Pães, Cereais, Raízes e Tubérculos");
            this.listaGruposAlimentares.add(grupoAlimentar);

            grupoAlimentar = new GrupoAlimentar();
            grupoAlimentar.setNomeGrupoAlimentar("Hortaliças");
            //grupoAlimentar.setQuantidadeAlimetnos();
            this.listaGruposAlimentares.add(grupoAlimentar);

            grupoAlimentar = new GrupoAlimentar();
            grupoAlimentar.setNomeGrupoAlimentar("Frutas");
            //grupoAlimentar.setQuantidadeAlimetnos();
            this.listaGruposAlimentares.add(grupoAlimentar);

            grupoAlimentar = new GrupoAlimentar();
            grupoAlimentar.setNomeGrupoAlimentar("Leguminosas");
            //grupoAlimentar.setQuantidadeAlimetnos();
            this.listaGruposAlimentares.add(grupoAlimentar);

            grupoAlimentar = new GrupoAlimentar();
            grupoAlimentar.setNomeGrupoAlimentar("Carne Bovina, Suína, Peixe, Frango, Ovos");
            //grupoAlimentar.setQuantidadeAlimetnos();
            this.listaGruposAlimentares.add(grupoAlimentar);

            grupoAlimentar = new GrupoAlimentar();
            grupoAlimentar.setNomeGrupoAlimentar("Produtos lácteos.");
            //grupoAlimentar.setQuantidadeAlimetnos();
            this.listaGruposAlimentares.add(grupoAlimentar);

            grupoAlimentar = new GrupoAlimentar();
            grupoAlimentar.setNomeGrupoAlimentar("Óleos e Gorduras");
            //grupoAlimentar.setQuantidadeAlimetnos();
            this.listaGruposAlimentares.add(grupoAlimentar);

            grupoAlimentar = new GrupoAlimentar();
            grupoAlimentar.setNomeGrupoAlimentar("Açúcares.");
            //grupoAlimentar.setQuantidadeAlimetnos();
            this.listaGruposAlimentares.add(grupoAlimentar);

            for (GrupoAlimentar gm : this.listaGruposAlimentares) {
                grupoAlimentarDao.create(gm);
            }
            grupoAlimentarDao = null;

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public List<GrupoAlimentar> buscarGrupos(Context context) throws SQLException {
        List<GrupoAlimentar> lista = null;
        this.dh = new DatabaseHelper(context);
        this.grupoAlimentarDao = new GrupoAlimentarDao(this.dh.getConnectionSource());
        try {
            lista = this.grupoAlimentarDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}