package wereh.academytraining.negocio;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.EditText;

import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.apresentacao.AdicionarExercicioActivity;
import wereh.academytraining.apresentacao.ExerciciosListaActivity;
import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.ExercicioNaoCadastradoPeloUsuario;
import wereh.academytraining.exceptions.ObjetoDuplicadoException;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.ExercicioDao;
import wereh.academytraining.persistencia.GrupoMuscularDao;

/**
 * Created by wellington on 10/05/17.
 */

public class ExercicioBo {
    private DatabaseHelper dh;
    private ExercicioDao exercicioDao;
    private GrupoMuscularDao gmDao;
    private Exercicio exercicio;
    private List<Exercicio> exercicios;
    private List<GrupoMuscular> gruposMusculares;


    public ExercicioBo(){

    }


    public List<Exercicio> buscarExercicios(Context context) throws SQLException {
        List<Exercicio> lista = null;
        this.dh = new DatabaseHelper(context);
        this.exercicioDao = new ExercicioDao(this.dh.getConnectionSource());
        try {
            lista = this.exercicioDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public void cadastrarExercicios(Context context) {
        try {
            this.dh = new DatabaseHelper(context);
            this.gmDao = new GrupoMuscularDao(this.dh.getConnectionSource());
            this.exercicioDao = new ExercicioDao(this.dh.getConnectionSource());
            this.exercicios = new ArrayList<Exercicio>();
            this.gruposMusculares = new ArrayList<GrupoMuscular>();
            this.gruposMusculares = this.gmDao.queryForAll();

            for (GrupoMuscular gm : gruposMusculares) {
                if(gm.getNomeGrupoMuscular().equals("Peitoral")){

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Supino Reto");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, tríceps e deltoide\n" +
                            "\n" +
                            "Equipamentos: Barra, Smith ou halteres"+
                            "O supino reto é o principal e mais básico exercício para peitoral e um dos três grandes da musculação. Ele configura-se por um exercício que recruta muitas fibras musculares e vários músculos auxiliares. Todavia, a perfeita forma de execução do exercício é necessária, pois, ele é muito propenso de lesionar o ombro, pela angulação em que se encontra e pelo tipo de movimento. Ainda, a posição dos braços deve ser devidamente observada para que não haja problemas relacionados ao ombro, especialmente.\n" +
                            "\n"+"O supino reto pode ser executado tanto com barra quanto com halteres. O Smith machine (barra guiada) também é uma boa opção por conferir uma boa estabilidade e segurança no movimento.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Supino inclinado");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, Deltoides, tríceps\n\n" +
                            "\n" +
                            "Equipamentos: Barra ou halteres"+
                            "Próximo ao supino reto, a diferença marcante que temos no supino inclinado é o trabalho mais específico para a região superior do peitoral. Todavia, um cuidado extra deve ser tomado nesse exercício que é manter a região lombar sempre devidamente apoiada no banco. A maioria dos indivíduos, por utilizar altas quantidades de carga, peca nesse aspecto.\n" +
                            "\n" + "Esse exercício pode ser realizado em angulações de 45º e 30º, sendo ainda possível fazê-lo com barra ou com halteres. O Smith machine (narra guiada) também é uma boa opção por conferir uma boa estabilidade e segurança no movimento, além de menor necessidade de equilíbrio em situações de fadiga (por exemplo, quando esse exercício é colocado ao final do treino).");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Pullover");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Tríceps, peitoral, dorsal, serrátil anterior e deltoides\n" +
                            "\n" +
                            "Equipamentos: Halter, barra ou cabos\n" +
                            "\n" +
                            "Sendo possível de se executar com os três equipamentos supracitados, o pullover, quando realizado para peitoral e não para dorsais requer uma angulação mais fechada dos cotovelos e uma fase excêntrica menos extensiva, a fim de concentrar a força especificamente na região do peitoral, solicitando menos os dorsais e mesmo o serrátil anteior.\n" +
                            "\n" +
                            "O pullover é um exercício que recruta, especialmente aparte lateral e inferior do peitoral, local de difícil foco para a maioria dos outros exercícios. Ele também auxilia a trabalhar a largura dos peitorais.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("PeckDeck");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral\n" +
                            "\n" +
                            "Equipamentos: Peck-Deck\n" +
                            "\n" +
                            "O Peck-Deck era um exercício bem mais observado nas academias antigas, mas, hoje parece que elas tem optado pelo Flye Machine, talvez pela maior versatilidade de poder usá-lo para o trabalho de deltoides posteriores e trapézio também. Todavia, esse é um grande exercício o qual isola por completo os peitorais e é uma ótima opção de pré-exaustão, como costumava fazer Mike Mentzer, antes do supino inclinado.\n" +
                            "\n" +
                            "O peck-deck deve sempre ser valorizado em sua fase excêntrica, promovendo uma completa extensão do peitoral Já na fase concêntrica, não há necessidade de parar em isometria na contração máxima, pois, isso aliviará a tensão causada durante o exercício e, portanto irá diminuir sua eficácia.\n" +
                            "\n" +
                            "Promova nesse exercício movimentos sempre muito bem concentrados e, se possível, sempre lentos também.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Press com cabos");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, deltoides e tríceps\n" +
                            "\n" +
                            "Equipamentos: Cabos\n" +
                            "\n" +
                            "Muitos utilizam os cabos no treinamento de peitorais apenas para flyes, cross over entre outros movimentos. Porém, o press com cabos é um poderoso exercício o qual pode ser utilizado de maneira auxiliar no seu treinamento. Ele basicamente simula um supino na máquina, mas, ao invés dos pegadores na mesma, você utiliza cabos, em uma polia ajustável, claro. Com a utilização dos cabos, você tenderá a deixar os braços cair, portanto, esse exercício recrutará muito das suas forças auxiliares e de sua capacidade neuromuscular para o equilíbrio e estabilidade do movimento.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Cross over");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral\n" +
                            "\n" +
                            "Equipamentos: Cabos\n" +
                            "\n" +
                            "O cross over é talvez o exercício com cabos para peitoral mais típico. Ele é um movimento especialmente para o peitoral menor, na sua variação principal. Todavia, esse movimento também pode ser para o peitoral maior ou mesmo para o peitoral como um todo, dependendo da angulação que ele é executado.\n" +
                            "\n" +
                            "É sempre importante atentar-se à forma desse exercício, pois, são muito comuns lesões de peitoral menor e, principalmente de manguito rotador, por ser um conjunto de músculos que estabilizam o úmero nesse movimento.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Supino declinado");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, tríceps, ombro\n" +
                            "\n" +
                            "Equipamentos: Smith, Barra ou halteres\n" +
                            "\n" +
                            "O supino declinado é um exercício que valoriza maiormente o peitoral menor. Assim como os outros supinos, ele pode ser executado tanto com barra quanto com halteres, a depender do enfoque que você queira dar. Ele é um movimento que facilmente pode lesionar o manguito rotador, portanto, a sua boa execução é primordial e não deve ser negligenciada jamais.\n" +
                            "\n" +
                            "Esse é um movimento que pode ser realizado com barra, halter ou mesmo no Smith, porém, o Smith é um pouco menos utilizado pelo fato de que não existem muitos bancos que possam ser facilmente transportados ao equipamento em questão.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Crucifixo declinado");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("úsculos envolvidos: Peitoral e deltoides\n" +
                            "\n" +
                            "Equipamentos: Halteres\n" +
                            "\n" +
                            "O Crucifixo declinado também é muito pouco visto na maioria das academias de musculação. Ele também visa um trabalho no peitoral inferior e é um ótimo exercício para a delineação na parte inferior do peitoral, desde o externo até a parte lateral do corpo.\n" +
                            "\n" +
                            "Esse movimento pode causar grande instabilidade na articulação glenoumeral, portanto, é fundamental que se execute o movimento com uma boa estabilidade e com controle do movimento, do contrário, a chance de lesão é muito alta.");
                    exercicios.add(exercicio);


                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Mergulho (barras paralelas)");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, deltoides e deltoides\n" +
                            "\n" +
                            "Equipamentos: Paralelas e gravitron\n" +
                            "\n" +
                            "As barras paralelas permitem a realização do mergulho que é um exercício ímpar para quem deseja um corpo realmente diferente! Sendo um exercício multiarticular, composto e, principalmente com um poder de recrutamento muscular excepcional, o mergulho é um exercício que auxiliara no treinamento do peitoral inferior, mais especificamente, mas ainda, recrutará a porção frontal dos deltoides e, principalmente, os tríceps. Aliás, ele é um exercício muito utilizado para tríceps.\n" +
                            "\n" +
                            "As barras paralelas tem a capacidade de serem versáteis, por isso, caso o seu enfoque sejam os peitorais inferiores, curve-se um pouco mais barra frente, fazendo uma leve flexão dos ombros. Porém, se o seu objetivo for o trabalho de tríceps, o ideal é manter-se reto, perpendicular ao solo.\n" +
                            "\n" +
                            "As barras paralelas apesar de serem ótimas, necessitam de cuidados, principalmente com os ombros. Isso porque, o peso de todo corpo (isso, quando não adicionamos peso ao corpo) exige uma enorme força do manguito rotador, pois, a tendência é de que o úmero se empurrado para cima, promovendo, por exemplo, quadros de síndrome do impacto. Portanto, atenção sempre!\n" +
                            "\n" +
                            "Para indivíduos com lesões, com algum tipo de necessidade específica ou muito pesados, talvez o gravitron possa ser uma boa opção. Ele auxiliará também iniciantes e, a maioria das mulheres.");
                    exercicios.add(exercicio);


                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Supinos com pegadas neutras");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, tríceps e deltoides\n" +
                            "\n" +
                            "Equipamentos: Barras, halteres, máquinas\n" +
                            "\n" +
                            "Os supinos com pegadas neutras, geralmente solicitam a porção lateral do peitoral e são ótimo exercícios (a depender da angulação) para trabalharmos a região inferior do peitoral (por exemplo, com a angulação reta). Dessa forma, eles podem ser utilizados com barras (existem barras em H, pouco vistas nas academias com o tamanho da barra de supino) e com halteres e máquinas, que são as formas mais comuns de realizar o movimento pela facilidade de aderência e mesmo segurança.\n" +
                            "\n" +
                            "Esse não é um exercício básico e que vai te trazer ganhos incríveis de massa muscular, mas, certamente será uma ótima ferramenta para trabalhos específicos e para a melhoria de algumas regiões.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Crucifixo reto");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral e deltoides\n" +
                            "\n" +
                            "Equipamentos: Halteres, cabos, máquina\n" +
                            "\n" +
                            "O crucifixo reto é um dos movimentos fundamentais para o peitoral. Trabalhando-o por completo, é um movimento de fácil realização, que não requer grandes trabalhos para a aderência do movimento e que recruta um altíssimo grau de fibras musculares. O crucifixo reto ainda, pode ser realizado em com halteres, que é o modo mais comum, com máquinas (fly machine) ou com cabos, dando uma tensão contínua ao movimento e não deixando que seu peitoral entre em algum tipo de relaxamento. Ele também pode conferir mais segurança a depender do caso.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Crucifixo inclinado");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral e deltoides\n" +
                            "\n" +
                            "Equipamentos: Halteres e cabos\n" +
                            "\n" +
                            "Da mesma forma que o crucifixo reto, o inclinado não se altera em muita coisa. Porém, pela angulação do banco, você acaba recrutando maiormente o peitoral superior, sendo que, a angulação ideal para esse movimento é a de 30º e não a de 45º . Isso se deve ao fato de que a 30º conseguimos uma melhor fase excêntrica do movimento com uma maior extensão do peitoral e sem flexionar demais os cotovelos, o que acaba tirando um pouco da tensão do peitoral maior.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Press inclinado com cabos");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, tríceps e deltoides\n" +
                            "\n" +
                            "Equipamentos: Cabos\n" +
                            "\n" +
                            "Mencionamos anteriormente o supino reto com cabos. O mesmo pode ser feito na versão inclinada, o que recrutará de melhor forma o peitoral na região superior. Além disso, os deltoides frontais também serão mais solicitados no movimento.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Flexões");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, tríceps e deltoides\n" +
                            "\n" +
                            "Equipamentos: Próprio corpo\n" +
                            "\n" +
                            "Muito utilizado em exercícios de calitenia, por exemplo, os exercícios que envolvem flexões são ótimos para quem quer deixar os pesos um pouco de lado ou tentar uma variação diferente. Eles podem fazer com que você comece a saber utilizar a carga do seu próprio corpo para obter resultados.\n" +
                            "\n" +
                            "Eles podem ser executados em inúmeras angulações a cada qual conferirá um trabalho específico em cada região do peitoral (superior, média e inferior).\n" +
                            "\n" +
                            "Obviamente, você não deve esperar ganhos exorbitantes de massa muscular, mas, poderá esperar fatores como resistência, equilíbrio etc.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Press em máquinas articuladas");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, tríceps e deltoides\n" +
                            "\n" +
                            "Equipamentos: Máquinas\n" +
                            "\n" +
                            "Podendo ser em máquinas que simulam o supino declinado, o supino inclinado ou mesmo o supino reto, os exercícios em máquinas são excelentes por conferirem isolamento, segurança e atingirem exatamente onde você quer. São também exercícios ideais para serem utilizados em momentos onde a instabilidade dos músculos auxiliares e/ou estabilizadores está desgastada.\n" +
                            "\n" +
                            "Ainda, a depender da pessoa que estamos falando, são ótimos para auxiliar em lesões e reabilitações das mesmas, sendo assim uma importante ferramenta da fisioterapia.\n" +
                            "\n" +
                            "Existem inúmeras marcas desses equipamentos, porém, entre as mais antigas está a Hammer Strenght que confere uma amplitude muito peculiar aos movimentos.");
                    exercicios.add(exercicio);
                }

                if(gm.getNomeGrupoMuscular().equals("Bíceps")) {
                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca martelo alternada");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps Braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Halteres\n" +
                            "\n" +
                            "Flexão dos cotovelos visando o trabalho na parte externa dos bíceps braquiais. Trabalha também os antebraços.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca alternada em banco inclinado");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps Braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Halters\n" +
                            "\n" +
                            "Flexão de cotovelos visando o trabalho na porção interna os bíceps braquiais.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca direta com barra reta");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculo utilizado: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Barra reta\n" +
                            "\n" +
                            "Exercício mais básico e poderoso para a construção dos bíceps braquiais de maneira geral, além do trabalho nos antebraços.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca spider com barra");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Barra reta ou EZ\n" +
                            "\n" +
                            "Exercício que visa o pico nos bíceps e o trabalho na porção interna do múscul");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca martelo com cabos");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Corda e cabos (polia)\n" +
                            "\n" +
                            "Exercício para trabalho tensionado da porção externa dos bíceps braquiais e antebraços.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca scott com cabos");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Cabos e banco scott\n" +
                            "\n" +
                            "Exercício para pico nos bíceps e trabalho tensionado na porção interna do músculo.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca direta com barra EZ");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Barra EZ\n" +

                            "Exercício poderoso para bíceps que pode ser realizado com pegada aberta ou fechada a depender do enfoque. Quanto mais aberta, mais se trabalha a porção interna dos bíceps braquiais.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca concentrada unilateral");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Halteres\n" +
                            "\n" +
                            "Exercício unilateral isolado para trabalho dos bíceps de maneira concentrada e precisa. Visa o pico dos bíceps.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca martelo cruzada ao corpo");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial e braquiorradial\n" +
                            "\n" +
                            "Equipamento utilizado: Halteres\n" +
                            "\n" +
                            "Próximo a rosca martelo tradicional o exercício enfoca mais ainda na parte externa do bíceps depois da flexão de cotovelos");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("“Drag Curl”");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Barra reta ou EZ\n" +
                            "\n" +
                            "Exercício que visa um trabalho isolado e de pico nos bíceps. Próximo a rosca direta, ele utiliza a barra próxima ao corpo e não utiliza a flexão de ombros durante o movimento, apenas fazendo a flexão de cotovelos.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca alternada com halteres");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Halteres\n" +
                            "\n" +
                            "Próxima a rosca direta, é executada unilateralmente. Pode ser executada em pé ou sentado, porém, sentado, tem-se um movimento mais concentrado.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca simultânea com halteres");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Halteres\n" +
                            "\n" +
                            "A rosca simultânea, é a mesma da rosca alternada, porém, ambos os cotovelos são flexionados simultaneamente. É interessante para trabalhar a unilateralidade do corpo.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca spider com halteres");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Halteres\n" +
                            "\n" +
                            "Visa o trabalho de pico de bíceps unilateralmente (simultaneamente).");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Rosca martelo simultânea");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos utilizados: Bíceps braquial\n" +
                            "\n" +
                            "Equipamento utilizado: Halteres\n" +
                            "\n" +
                            "Visa o trabalho na porção externa dos bíceps, porém, nessa variação, pode-se realizar o exercício sentado ou em pé. Sentado, consegue-se maior isolamento.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Bíceps 21");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("");
                    exercicios.add(exercicio);

                } if(gm.getNomeGrupoMuscular().equals("Tríceps")){

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Bíceps 21");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("");
                    exercicios.add(exercicio);

                }

                if(gm.getNomeGrupoMuscular().equals("Tríceps")){

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Bíceps 21");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("");
                    exercicios.add(exercicio);

                }

                if(gm.getNomeGrupoMuscular().equals("Tríceps")){

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Bíceps 21");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("");
                    exercicios.add(exercicio);

                }

                if(gm.getNomeGrupoMuscular().equals("Tríceps")){

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Bíceps 21");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("");
                    exercicios.add(exercicio);

                }

                if(gm.getNomeGrupoMuscular().equals("Tríceps")){

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Bíceps 21");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("");
                    exercicios.add(exercicio);

                }
            }

            for (Exercicio ex : exercicios) {
                exercicioDao.create(ex);
            }
            exercicios = null;


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validarCamposDeTexto(EditText nomeExercicio, EditText descricao) {
        if (nomeExercicio.getText().toString().equals("")) {
            throw new CampoObrigatorioException("NOME DO EXERCICIO");
        }
        if (descricao.getText().toString().equals("")) {
            throw new CampoObrigatorioException("DESCRIÇÃO");
        }
    }

    public void verificarExercicio(Exercicio exercicioCorrente, AdicionarExercicioActivity adicionarExercicioActiviity) throws SQLException {
        this.dh = new DatabaseHelper(adicionarExercicioActiviity);
        this.exercicioDao = new ExercicioDao(this.dh.getConnectionSource());
        List<Exercicio> listaExercicios = exercicioDao.queryForAll();

        for(Exercicio e: listaExercicios){
            if((e.getNomeExercicio().equals(exercicioCorrente.getNomeExercicio() ))){
                throw new ObjetoDuplicadoException("Já existe um exercicio igual!");
            }
        }
    }

    public void salvar(Exercicio exercicioCorrente, AdicionarExercicioActivity adicionarExercicioActivity) throws SQLException {
        this.dh = new DatabaseHelper(adicionarExercicioActivity);
        this.exercicioDao = new ExercicioDao(this.dh.getConnectionSource());
        this.exercicioDao.create(exercicioCorrente);
    }

    public void apagarExercicio(Exercicio exercicio, ExerciciosListaActivity exerciciosListaActivity) throws SQLException {
        if (verificarIdUsuarioNoExercicio(exercicio, exerciciosListaActivity)){
            this.dh = new DatabaseHelper(exerciciosListaActivity);
            this.exercicioDao = new ExercicioDao(this.dh.getConnectionSource());
            this.exercicioDao.deleteById(exercicio.getId());
        }

    }

    private boolean verificarIdUsuarioNoExercicio(Exercicio exercicio, ExerciciosListaActivity exerciciosListaActivity) throws SQLException {
        if (exercicio.getIdUsuario() == null){
           throw new ExercicioNaoCadastradoPeloUsuario("Impossível, este exercicio você não pode apagar!");
        }
        int id = Integer.parseInt(exercicio.getIdUsuario());
        if ( id == 1) {
            return true;
        }else
            return false;
    }

    public void verificarExercicioCadastradoPeloUsuario(Exercicio exercicio, ExerciciosListaActivity exerciciosListaActivity)throws SQLException {
       if (exercicio.getIdUsuario() == null){
           throw  new ExercicioNaoCadastradoPeloUsuario("Impossível, você não pode editar esse exercício!");
       }else{
           Intent i = new Intent(exerciciosListaActivity, AdicionarExercicioActivity.class);
           i.putExtra("exercicio", (Parcelable) exercicio);
           exerciciosListaActivity.startActivity(i);
       }

    }

    public void atualizar(Exercicio exercicioCorrente, AdicionarExercicioActivity adicionarExercicioActivity, Exercicio exercicio) throws SQLException{
        this.dh = new DatabaseHelper(adicionarExercicioActivity);
        ExercicioDao treinoDao = new ExercicioDao(this.dh.getConnectionSource());
        UpdateBuilder<Exercicio, Integer> updateBuilder = treinoDao.updateBuilder();
        updateBuilder.updateColumnValue("nomeExercicio",exercicioCorrente.getNomeExercicio());
        updateBuilder.updateColumnValue("descricao",exercicioCorrente.getDescricao());
        updateBuilder.where().eq("id", exercicio.getId());
        updateBuilder.update();
    }
}
