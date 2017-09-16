package wereh.academytraining.negocio;

import android.content.Context;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.apresentacao.AlimentosListaActivity;
import wereh.academytraining.entidade.Alimento;
import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.persistencia.AlimentoDao;
import wereh.academytraining.persistencia.DatabaseHelper;
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

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Batata frita (palha)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de servir");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Batata frita (fatia)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colheres de servir");
                    alimento.setPeso(29.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Batata frita (palito)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1/3 colher de servir");
                    alimento.setPeso(58.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Batata sauteé");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 colheres de servir");
                    alimento.setPeso(130.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito tipo “aveia e mel”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("5 unidades");
                    alimento.setPeso(30.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito tipo “bono cracker”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("8 unidades");
                    alimento.setPeso(32.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito tipo “cookies” com gotas de chocolate/ coco");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("6 unidades");
                    alimento.setPeso(30.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito tipo “cream cracker”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("5 unidades");
                    alimento.setPeso(32.5);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito de leite");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("5 unidades");
                    alimento.setPeso(32.5);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito tipo “maçã e canela”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("6 unidades");
                    alimento.setPeso(33.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito tipo “maizena”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("7 unidades");
                    alimento.setPeso(35.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito tipo “maria”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("7 unidades");
                    alimento.setPeso(35.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito recheado tipo “alpino”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidades");
                    alimento.setPeso(30.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito recheado chocolate/doce de leite/ morango");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidades");
                    alimento.setPeso(34.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito tipo “salclic” integral");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("6 unidades");
                    alimento.setPeso(30.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito salgado tipo “triggy”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("7 unidades");
                    alimento.setPeso(34.5);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Biscoito tipo “waffer” chocolate/morango/baunilha");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 unidades");
                    alimento.setPeso(30.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Bolo de chocolate industrializado - mistura em pó");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 fatia");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Cará/inhame cozido/ amassado");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 1⁄2 colher de sopa");
                    alimento.setPeso(126.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Cereal matinal tipo “sucrilhos”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 xícara de chá");
                    alimento.setPeso(43.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Farinha de mandioca");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 colheres de sopa");
                    alimento.setPeso(48.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Farinha de milho");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 colheres de sopa");
                    alimento.setPeso(48.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Farofa de farinha de mandioca");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 colher de servir");
                    alimento.setPeso(37.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Flocos de milho tipo “polentina/milharina”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 colheres de sopa");
                    alimento.setPeso(45.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Macarrão cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 1⁄2 colheres de sopa");
                    alimento.setPeso(105.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Mandioca cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 colheres de sopa");
                    alimento.setPeso(96.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Milho verde em conserva (enlatado)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("7 colheres de sopa");
                    alimento.setPeso(142.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pãozinho caseiro");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 unidade");
                    alimento.setPeso(55.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pão de forma tradicional tipo “pullman”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 fatias");
                    alimento.setPeso(43.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pão de queijo");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(40.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pão francês");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pão hot dog");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 unidade");
                    alimento.setPeso(75.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pão tipo bisnaguinha");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 unidades");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pipoca com sal");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 xícara de chá");
                    alimento.setPeso(22.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Polenta frita");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 fatia");
                    alimento.setPeso(60.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Batata frita (fatia)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de servir");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Polenta sem molho");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 fatias");
                    alimento.setPeso(200.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Purê de batata");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de servir");
                    alimento.setPeso(135.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Torrada salgada tipo “bi tost”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 unidades");
                    alimento.setPeso(40.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Torrada fibras tipo “fibratost”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 unidades");
                    alimento.setPeso(45.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Torrada glúten");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("5 unidades");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Torrada (pão francês)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("6 fatias");
                    alimento.setPeso(33.0);
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
                    alimento.setNomeAlimento("Abobrinha cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 colheres de sopa");
                    alimento.setPeso(81.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Acelga cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 colheres de sopa");
                    alimento.setPeso(85.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Acelga crua (picada)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("9 colheres de sopa");
                    alimento.setPeso(90.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Agrião");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("22 ramos");
                    alimento.setPeso(130.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Aipo cru");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidades");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Alcachofra (coração) cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 unidade");
                    alimento.setPeso(40.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Alcachofra cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄4 unidade");
                    alimento.setPeso(35.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Alface");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("15 folhas");
                    alimento.setPeso(120.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Almeirão");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("15 folhas");
                    alimento.setPeso(120.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Aspargos em conserva");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("8 unidades");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Aspargos fresco cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("6 1⁄2 unidades");
                    alimento.setPeso(73.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Berinjela cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(60.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Beterraba cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 fatias");
                    alimento.setPeso(30.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Beterraba crua ralada");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(42.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Brócolis cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 1⁄2 colheres de sopa");
                    alimento.setPeso(120.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Broto de alfafa cru");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 copo americano");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Broto de bambu cru");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3⁄4 unidade");
                    alimento.setPeso(60.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Broto de feijão cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 colher de servir");
                    alimento.setPeso(81.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Cenoura cozida (fatias)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("7 fatias");
                    alimento.setPeso(35.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Cenoura cozida (picada)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3⁄4 colher de servir");
                    alimento.setPeso(36.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Cenoura crua (picada)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de servir");
                    alimento.setPeso(36.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Chuchu cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 colheres de sopa");
                    alimento.setPeso(57.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Cogumelo em conserva");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("9 unidades");
                    alimento.setPeso(63.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Couve-de-bruxelas cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 unidades");
                    alimento.setPeso(40.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Couve-flor cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 ramos");
                    alimento.setPeso(69.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Couve manteiga cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de servir");
                    alimento.setPeso(42.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Ervilha em conserva");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de sopa");
                    alimento.setPeso(13.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Ervilha fresca");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 colher de sopa");
                    alimento.setPeso(19.5);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Ervilha torta (vagem)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidades");
                    alimento.setPeso(11.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Escarola");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("15 folhas");
                    alimento.setPeso(83.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Espinafre cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 colheres de sopa");
                    alimento.setPeso(60.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Jiló cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 colher de sopa");
                    alimento.setPeso(40.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Mostarda");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("8 folhas");
                    alimento.setPeso(83.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Palmito em conserva");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidades");
                    alimento.setPeso(100.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pepino japonês");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(130.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pepino picado");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 colheres de sopa");
                    alimento.setPeso(116.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Picles em conserva");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("5 colheres de sopa");
                    alimento.setPeso(108.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pimentão cru fatiado (vermelho/verde)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("10 fatias");
                    alimento.setPeso(70.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pimentão cru picado (vermelho/verde)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 colheres de sopa");
                    alimento.setPeso(72.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Rabanete");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 unidades");
                    alimento.setPeso(102.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Repolho branco cru (picado)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("6 colheres de sopa");
                    alimento.setPeso(72.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Repolho cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("5 colheres de sopa");
                    alimento.setPeso(75.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Repolho roxo cru (picado)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("5 colheres de sopa");
                    alimento.setPeso(60.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Rúcula");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("15 folhas");
                    alimento.setPeso(83.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Salsão cru");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(38.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Tomate caqui");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 fatias");
                    alimento.setPeso(75.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Tomate cereja");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("7 unidades");
                    alimento.setPeso(70.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Tomate comum");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 fatias");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Vagem cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(44.0);
                    alimentos.add(alimento);
                }

                if (ga.getNomeGrupoAlimentar().equals("Frutas")) {

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Abacate");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3⁄4 colher sopa");
                    alimento.setPeso(24.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Abacaxi");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 fatia");
                    alimento.setPeso(65.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Acerola");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 xícara das de chá");
                    alimento.setPeso(128.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Ameixa-preta");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 unidade");
                    alimento.setPeso(15.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Ameixa-vermelha");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidades");
                    alimento.setPeso(70.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Banana-prata");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 unidade");
                    alimento.setPeso(43.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Caju");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(81.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Caqui");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 unidade");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Carambola");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(110.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Cereja");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("12 unidades");
                    alimento.setPeso(48.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Damasco seco");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("9 unidades");
                    alimento.setPeso(63.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Fruta do conde");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄4 unidade");
                    alimento.setPeso(35.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Jabuticaba");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("17 unidades");
                    alimento.setPeso(68.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Jaca");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 bagos");
                    alimento.setPeso(66.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Kiwi");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3⁄4 unidade");
                    alimento.setPeso(60.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Laranja-da-baía/seleta");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 gomos");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Laranja-pêra/lima espremida para chupar");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3⁄4 colher sopa");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Limão");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidades");
                    alimento.setPeso(126.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Maçã");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 unidade");
                    alimento.setPeso(60.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Mamão formosa");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 fatia");
                    alimento.setPeso(110.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Mamão papaya");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄3 unidade");
                    alimento.setPeso(93.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Manga bordon");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 unidade");
                    alimento.setPeso(55.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Manga haden");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄4 unidade");
                    alimento.setPeso(55.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Manga polpa batida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("5 colheres sopa");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Maracujá (suco puro)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("5 colheres de sopa");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Melancia");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 fatia");
                    alimento.setPeso(115.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Melão");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 fatia");
                    alimento.setPeso(108.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Morango");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("9 unidades");
                    alimento.setPeso(115.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Nectarina");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3⁄4 unidade");
                    alimento.setPeso(69.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pêra");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 unidade");
                    alimento.setPeso(66.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Pêssego");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3⁄4 unidade");
                    alimento.setPeso(85.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Suco de abacaxi com açúcar");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 copo plástico descartável para água (140 ml)");
                    alimento.setPeso(83.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Suco de laranja (puro)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 copo plástico descartável para água (140 ml)");
                    alimento.setPeso(79.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Suco de melão");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 copo requeijão");
                    alimento.setPeso(85.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Suco de tangerina");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 copo plástico descartável para água (140 ml)");
                    alimento.setPeso(82.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Suco de uva (industrializado) com açúcar");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 copo plástico descartável para água (140 ml)");
                    alimento.setPeso(133.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Tangerina");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("6 gomos");
                    alimento.setPeso(84.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Uva comum");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("11 bagos");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Uva itália");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 bagos");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Uva rubi");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 bagos");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Vitamina (mamão, maça, banana, açúcar, leite)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 copo plástico");
                    alimento.setPeso(70.0);
                    alimentos.add(alimento);

                }

                if (ga.getNomeGrupoAlimentar().equals("Leguminosas")) {

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Ervilha seca cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 colheres de sopa");
                    alimento.setPeso(72.5);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Feijão branco cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 colher de sopa");
                    alimento.setPeso(48.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Feijão cozido (50 % de caldo)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 concha");
                    alimento.setPeso(86.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Feijão cozido (somente grãos)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Grão de bico cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 colheres de sopa");
                    alimento.setPeso(36.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Lentilha cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(48.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Soja cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de servir");
                    alimento.setPeso(43.0);
                    alimentos.add(alimento);

                }

                if (ga.getNomeGrupoAlimentar().equals("Carne Bovina, Suína, Peixe, Frango, Ovos")) {

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Atum enlatado tipo “desfiado”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Atum enlatado tipo “sólido”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(90.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Bacalhoada");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 porção");
                    alimento.setPeso(75.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Bife à role");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(110.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Bife grelhado");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(64.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Camarão cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("20 unidades");
                    alimento.setPeso(160.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Camarão frito");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("10 unidades");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Carne cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 fatia");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Carne cozida de peru tipo “blanquet”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("10 fatias");
                    alimento.setPeso(150.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Carne cozida de peru tipo “rolê”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("12 fatias");
                    alimento.setPeso(180.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Carne moída refogada");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("5 colheres de sopa");
                    alimento.setPeso(90.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Espetinho de carne");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidades");
                    alimento.setPeso(92.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Frango assado inteiro");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 pedaço de peito ou 1 coxa");
                    alimento.setPeso(100.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Frango filé à milanesa");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Frango filé grelhado");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade grande");
                    alimento.setPeso(100.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Frango sobrecoxa cozida com molho");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(100.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Hambúrguer caseiro");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(90.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Hambúrguer industrializado");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(90.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Lingüiça de porco cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 gomo");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Manjuba frita");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("10 unidades");
                    alimento.setPeso(106.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Merluza cozida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 filés médios");
                    alimento.setPeso(200.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Merluza defumada");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 filés");
                    alimento.setPeso(190.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Nugget de frango");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("4 unidades");
                    alimento.setPeso(72.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Omelete simples");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(74.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Ovo frito");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidades");
                    alimento.setPeso(100.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Ovo pochê");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidades");
                    alimento.setPeso(100.0);
                    alimentos.add(alimento);


                    alimento = new Alimento();
                    alimento.setNomeAlimento("Peixe espada cozido");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 porção");
                    alimento.setPeso(100.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Porco lombo assado");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 fatia");
                    alimento.setPeso(80.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Salame");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("11 fatias");
                    alimento.setPeso(75.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Salsicha");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 unidade");
                    alimento.setPeso(60.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Sardinha escabeche");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 unidade");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);
                }

                if (ga.getNomeGrupoAlimentar().equals("Produtos lácteos")) {

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Cream cheese");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 colheres de sopa");
                    alimento.setPeso(77.5);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Iogurte de frutas");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 pote");
                    alimento.setPeso(140.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Iogurte natural");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 copo de requeijão");
                    alimento.setPeso(400.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Iogurte polpa de frutas");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 pote");
                    alimento.setPeso(120.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Iogurte polpa de frutas com geléia");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 pote");
                    alimento.setPeso(130.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Iogurte polpa de frutas 'ninho soleil'");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 pote");
                    alimento.setPeso(120.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Leite em pó integral");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(30.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Leite semidesnatado 'molico'");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(278.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Molho branco com queijo");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 colheres de sopa");
                    alimento.setPeso(62.5);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Queijo-de-minas");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 fatia");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Queijo mussarela");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 fatias");
                    alimento.setPeso(45.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Queijo parmesão");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3 colheres de sopa");
                    alimento.setPeso(30.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Queijo pasteurizado tipo “polenguinho”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 unidade");
                    alimento.setPeso(35.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Queijo pasteurizado tipo “sandwich in”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 fatias");
                    alimento.setPeso(40.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Queijo petit suisse de morango");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 potes");
                    alimento.setPeso(90.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Queijo prato");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 fatias");
                    alimento.setPeso(40.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Queijo provolone");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 fatia");
                    alimento.setPeso(35.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Requeijão cremoso");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 colher de sopa");
                    alimento.setPeso(45.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Ricota");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 fatias");
                    alimento.setPeso(100.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Sobremesa láctea tipo “pudim de leite”");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 pote");
                    alimento.setPeso(90.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Suflê de queijo");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 fatia");
                    alimento.setPeso(50.0);
                    alimentos.add(alimento);

                }

                if (ga.getNomeGrupoAlimentar().equals("Óleos e Gorduras")) {

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Azeite de dendê");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("3⁄4 colher de sopa");
                    alimento.setPeso(9.2);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Azeite de oliva");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de sopa");
                    alimento.setPeso(7.6);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Bacon (gordura)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 fatia");
                    alimento.setPeso(7.5);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Banha de porco");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 colher de sopa");
                    alimento.setPeso(7.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Creme vegetal");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de sopa");
                    alimento.setPeso(14.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Halvarina");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1colher de sopa");
                    alimento.setPeso(19.7);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Manteiga");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 colher de sopa");
                    alimento.setPeso(9.8);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Margarina culinária");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1/10 tablete");
                    alimento.setPeso(10.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Margarina líquida");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de sopa");
                    alimento.setPeso(8.9);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Margarina vegetal");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 colher de sopa");
                    alimento.setPeso(9.8);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Óleo vegetal composto de soja e oliva");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de sopa");
                    alimento.setPeso(8.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Óleo vegetal de girassol");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de sopa");
                    alimento.setPeso(8.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Óleo vegetal de milho");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de sopa");
                    alimento.setPeso(8.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Óleo vegetal de soja");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de sopa");
                    alimento.setPeso(8.0);
                    alimentos.add(alimento);
                }

                if (ga.getNomeGrupoAlimentar().equals("Açúcares.")) {

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Açúcar mascavo fino");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de sopa");
                    alimento.setPeso(25.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Açúcar mascavo grosso");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 1⁄2 colher de sopa");
                    alimento.setPeso(27.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Açúcar refinado");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1 colher de sopa");
                    alimento.setPeso(28.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Dextrosol");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 colher de sopa");
                    alimento.setPeso(32.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Doce industrializado tipo goiabada");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("1⁄2 fatia");
                    alimento.setPeso(45.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("ÓGlucose de milho (Karo)");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 colheres de sopa");
                    alimento.setPeso(40.0);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Mel");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("2 1⁄2 colheres de sopa");
                    alimento.setPeso(37.5);
                    alimentos.add(alimento);

                    alimento = new Alimento();
                    alimento.setNomeAlimento("Nidex");
                    alimento.setGrupoAlimentar(ga.getId());
                    alimento.setMedidaCaseira("6 medidas");
                    alimento.setPeso(30.0);
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
