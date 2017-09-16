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


    public ExercicioBo() {

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
                if (gm.getNomeGrupoMuscular().equals("Peitoral")) {

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Supino Reto");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, tríceps e deltoide\n" +
                            "\n" +
                            "Equipamentos: Barra, Smith ou halteres" +
                            "O supino reto é o principal e mais básico exercício para peitoral e um dos três grandes da musculação. Ele configura-se por um exercício que recruta muitas fibras musculares e vários músculos auxiliares. Todavia, a perfeita forma de execução do exercício é necessária, pois, ele é muito propenso de lesionar o ombro, pela angulação em que se encontra e pelo tipo de movimento. Ainda, a posição dos braços deve ser devidamente observada para que não haja problemas relacionados ao ombro, especialmente.\n" +
                            "\n" + "O supino reto pode ser executado tanto com barra quanto com halteres. O Smith machine (barra guiada) também é uma boa opção por conferir uma boa estabilidade e segurança no movimento.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Supino inclinado");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Músculos envolvidos: Peitoral, Deltoides, tríceps\n\n" +
                            "\n" +
                            "Equipamentos: Barra ou halteres" +
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

                // Bíceps
                if (gm.getNomeGrupoMuscular().equals("Bíceps")) {
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

//                    exercicio = new Exercicio();
//                    exercicio.setNomeExercicio("Bíceps 21");
//                    exercicio.setGrupoMuscular(gm.getId());
//                    exercicio.setDescricao("");
//                    exercicios.add(exercicio);

                }
                //Tríceps
                if (gm.getNomeGrupoMuscular().equals("Tríceps")) {

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Fundos em barras paralelas");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Um dos melhores exercícios para trabalhar os tríceps (cabeça medial, lateral e longa dos tríceps), e também trabalha o grande peitoral e deltóides. Segundo análises EMG, os fundos em barras paralelas, juntamente com os fundos entre bancos, são os dois exercícios mais eficientes para trabalhar os tríceps.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Tríceps, grande peitoral.\n" +
                            "Músculo de acção secundária: Deltóides (especialmente a parte anterior).");
                    exercicios.add(exercicio);


                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Fundos em máquina");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação de fundos é ideal para principiantes e indivíduos com limitações físicas ou neurológicas que ainda não possuem a força e/ou coordenação física necessária para realizar fundos entre bancos ou em barras paralelas.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Tríceps, grande peitoral .\n" +
                            "Músculo de acção secundária: Deltóides (especialmente a parte anterior).");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Supino pegada junta");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação do supino permite um maior foco nos tríceps. Este é um exercício interessante, mas segundo análises EMG, não é dos mais eficientes para trabalhar os tríceps.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Tríceps, grande peitoral .\n" +
                            "Músculo de acção secundária: Deltóides (especialmente a parte anterior).");
                    exercicios.add(exercicio);


                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Extensões de tríceps deitado com barra");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este é o segundo exercícios (com equipamento de musculação) mais eficiente para os tríceps. É altamente recomendada a sua inclusão num programa de treino. Não é necessário transformar as extensões de tríceps deitado com barra W num pollover, tal como os culturistas da velha guarda faziam. Uma amplitude de movimento em que a barra não passe para trás da sua cabeça, irá ser o suficiente.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Tríceps.\n" +
                            "Músculo de acção secundária: Deltóides.\n");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Extensões de tríceps sentado com barra");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação do exercícios extensões de tríceps deitado, encontra-se ao mesmo nível em termos de eficiência de ativação dos tríceps.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Tríceps.\n" +
                            "Músculo de acção secundária: Deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Extensões de tríceps sentado com halter");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Mais uma variação, que se encontra a um nível muito próximo, embora inferior das variações anteriores. Permite a realização de repetições forçadas ou excêntricas, pois poderá ajudar-se a si mesmo durante a realização do exercício.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Tríceps.\n" +
                            "Músculo de acção secundária: Deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Puxada de tríceps");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este é o exercício mais eficiente para os tríceps, com equipamento de musculação. Tente a todo o custo manter os cotovelos junto ao tronco e opte por utilizar cargas que realmente consegue controlar.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Tríceps.\n" +
                            "Músculo de acção secundária: Deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Puxada de tríceps em supinação");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Nesta variação usa-se um agarre em supinação (palmas das mãos apontadas para cima) e poderá ser mais indicada para quem tem problemas nas articulações dos pulsos ou cotovelos, pois requer menos peso.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Tríceps.\n" +
                            "Músculo de acção secundária: Deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Puxada de tríceps com corda");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este é o melhor exercício para isolar a cabeça lateral dos tríceps. Usar uma corda em vez de uma barra reta ou em V, torna a puxada de tríceps um exercício de tríceps mais equilibrado no sentido de trabalhar as três cabeças dos tríceps. Se puxar a corda para os lados de forma deliberada no final do movimento, irá atingir a cabeça longa dos seus tríceps de forma muito eficiente e transforma-se assim, o exercício de isolamento nº1 para a cabeça lateral, num exercício de tríceps mais equilibrado.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Tríceps.\n" +
                            "Músculo de acção secundária: Deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Kickback");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("O seu programa de treino de tríceps não ficaria completo sem pelo menos um movimento complementar como os kickbacks de tríceps com uma inclinação de 60°, em que a cabeça longa é claramente o elo mais fraco, e é portanto garantido que irá ser atingida de forma bastante decente, com os pesos ligeiros adequados.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Tríceps.\n" +
                            "Músculo de acção secundária: Parte posterior dos deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Kickback e polia");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação do Kickback tem a vantagem de manter a tensão constante ao longo de toda a amplitude do movimento.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Tríceps.\n" +
                            "Músculo de acção secundária: Parte posterior dos deltóides.");
                    exercicios.add(exercicio);


                }

                //Costas
                if (gm.getNomeGrupoMuscular().equals("Costas")) {

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Peso morto / levantamento terra");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Nesta variação do peso morto, a mais conhecida de todas, tudo é projetado para a maximização técnica da quantidade de peso levantado. Os quadris começam mais elevados do que os joelhos (basicamente colocando os joelhos no mesmo ângulo que um quarto do agachamento) e os ombros estão um pouco atrás da barra na posição de partida. Isto permite-lhe utilizar ao máximo a parte inferior das costas, glúteos, e quadríceps.\n" +
                            "\n" +
                            "NOTA: Deverá manter SEMPRE o arco natural da parte inferior das costas, de forma a minimizar o risco de desenvolvimento de lesões na região lombar e aumentar a eficiência do movimento.\nTipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Grande dorsal, trapézios, eretores da espinha, glúteos, isquitobiais (femorais), quadríceps.\n" +
                            "Músculo de acção secundária: Oblíquos externos, antebraços.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Puxada de dorsais em polia alta");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("A puxada de dorsais em polia alta (pela frente) tem como alvo principal o grande dorsal. Este é um músculo grande que ocupa uma grande extensão das costas.\n" +
                            "\n" +
                            "Nota: A variação de puxada de dorsais que mais ativa a musculatura das costas é na verdade a puxada de dorsais à nuca.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Grande dorsal, parte inferior e intermédia dos trapézios, rombóides.\n" +
                            "Músculo de acção secundária: Parte posterior dos deltóides e bíceps.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Puxada de dorsais em polia alta em supinação");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação da puxada de dorsais em polia alta (pela frente) diferencia-se da variação anterior por recrutar e trabalhar mais os bíceps.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Grande dorsal, parte inferior e intermédia dos trapézios, rombóides.\n" +
                            "Músculo de acção secundária: Parte posterior dos deltóides e bíceps.\n");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Remada com barra");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este é o exercício de remada com barra mais conhecido e mais realizado nas salas de musculação. Mantenha os joelhos ligeiramente dobrados, as costas retas com a zona lombar com um ligeiro arco (côncavo). Inicie o movimento com os músculos das costas e não com as pernas. Evite recorrer ao impulso ou batota (efeito sanfona).\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Grande dorsal, parte inferior e intermédia dos trapézios, rombóides\n" +
                            "Músculo de acção secundária: Parte posterior dos deltóides e bíceps");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Remada com barra em supinação");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Nesta variação da remada com barra utiliza-se um agarre mais junto, em supinação, mas o tipo de movimento / técnica permanece praticamente idêntica à remada com barra clássica. Esta variação  diferencia-se da variação anterior por recrutar e trabalhar mais os bíceps.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Grande dorsal, parte inferior e intermédia dos trapézios, rombóides\n" +
                            "Músculo de acção secundária: Parte posterior dos deltóides e bíceps");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Remada em máquina “hammer”");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação da remada proporciona duas grande vantagens em relação aos restantes tipos de remada. Em primeiro lugar, a máquina proporciona um movimento fixo e estável, e em segundo lugar, liberta a zona lombar do stress das variações com pesos livres e polia baixa, isto desde que realmente se coloque na posição correta e apoie o tronco no suporte acolchoado.\n" +
                            "\n" +
                            "Esta é portanto a variação ideal para o principiantes ou para quem possa ter impedimentos físicos para realizar a remada com barra, como por exemplo quem tem hérnias discais ou problemas de coordenação.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Grande dorsal, parte inferior e intermédia dos trapézios, rombóides\n" +
                            "Músculo de ação secundária: Parte posterior dos deltoides e bíceps.");
                    exercicios.add(exercicio);


                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Remada em polia baixa");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação da remada proporciona duas grande vantagens em relação aos remos com barra e halter. Em primeiro lugar, a máquina proporciona um movimento fixo e estável, e em segundo lugar, liberta a zona lombar do stress das variações com pesos livres, isto desde que realmente se coloque na posição correta e apoie o tronco no suporte acolchoado.\n" +
                            "\n" +
                            "Esta é portanto a variação ideal para o principiantes ou para quem possa ter impedimentos físicos para realizar a remada com barra, como por exemplo quem tem hérnias discais ou problemas de coordenação.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Grande dorsal, parte inferior e intermédia dos trapézios, rombóides.\n" +
                            "Músculo de acção secundária: Parte posterior dos deltóides e bíceps.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Remada em polia baixa a 1 mão");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação da remada em polia baixa permite  trabalhar apenas um lado do corpo de cada vez, proporcionando assim um estímulo diferente. Para além disso, aqui é possível puxar mais para trás o cotovelo do braço que está a trabalhar, aumentando assim a amplitude do movimento de remada.\n" +
                            "\n" +
                            "De forma a minimizar o risco de lesões, assegure-se de manter um ligeiro arco na zona lombar (côncavo) ao longo de todo o movimento de todos os exercícios de remada.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de ação principal: Grande dorsal, parte inferior e intermédia dos trapézios, rombóides.\n" +
                            "Músculo de ação secundária: Parte posterior dos deltóides e bíceps.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Remada com halter");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta é a variação de remada ideal para treina em casa e não tem acesso a máquinas ou barras. Também impõe menos stress nos lombares do que a remada com barra ou em polia baixa.\n" +
                            "\n" +
                            "Nota: É muito fácil fazer batota neste movimento, mantenha o tronco fixo a todo o custo.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Grande dorsal, parte inferior e intermédia dos trapézios, rombóides.\n" +
                            "Músculo de acção secundária: Parte posterior dos deltóides e bíceps.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Remada em barra t");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Tal como acontece com a  remada em máquina hammer, o suporte do peitoral e o ângulo fixo da barra, proporcionam um movimento fixo e estável, e também liberta a zona lombar do stress das variações de remada com pesos livres e polia baixa.\n" +
                            "\n" +
                            "Esta é portanto mais uma excelente variação ideal para o principiantes ou para quem possa ter impedimentos físicos para realizar a remada com barra, como por exemplo quem tem hérnias discais ou problemas de coordenação.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Grande dorsal, parte inferior e intermédia dos trapézios, rombóides\n" +
                            "Músculo de acção secundária: Parte posterior dos deltóides e bíceps.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Encolhimentos de ombros com halteres");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este exercício consiste em elevar os ombros em direção ás orelhas. Evite os pesos exagerados e mantenha sempre uma postura correta – Peito para a frente, abdominais e músculos lombares contraídos. Neste exercícios pode colocar os halteres aos lados do corpo ou à frente.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Parte superior (descendente) dos trapézios");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Encolhimentos de ombros com barra");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("A imagem é auto-explicativa, portanto iremos apenas deixar umas poucas sugestões: Não utilize um peso demasiado exagerado que não possa controlar, pois isso aumentará muito o risco de lesão na zona lombar ou nas costas. mantenha um movimento fluido e controlado e realize uma contração com a duração de 1 segundo na parte final do movimento.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Parte superior (descendente) dos trapézios");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Encolhimentos com barra atrás");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("A única diferença desta variação em relação à anterior encontra-se na posição da barra, que fica atrás do corpo em vez de à frente. Alguns indivíduos afirmam sentir maior ativação dos trapézios com esta variação.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Parte superior (descendente) dos trapézios");
                    exercicios.add(exercicio);

                }

                //Deltóides
                if (gm.getNomeGrupoMuscular().equals("Deltóides")) {

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Press militar com barra");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("O movimento mais conhecido para trabalhar os deltóides. Deverá ter cuidado para não usar demasiada carga que possa comprometer a execução correta e fluída do exercício. A versão de press militar à nuca é na verdade a mais eficiente para trabalhar as três cabeças dos deltóides.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Parte anterior e medial dos deltóides.\n" +
                            "Músculo de acção secundária: Tríceps, parte posterior dos deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Press militar com halteres");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este é muito provavelmente o melhor exercício geral para os deltóides, pois permite a realização de um arco de movimento mais natural.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Parte anterior e medial dos deltóides.\n" +
                            "Músculo de acção secundária: Tríceps, parte posterior dos deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Remada vertical");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este é um excelente movimento composto para atingir a parte medial dos deltóides.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Parte medial dos deltóides, parte superior (ascendente) dos trapézios.\n" +
                            "Músculo de acção secundária: Parte posterior e anterior dos deltóides, bíceps e antebraços");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Elevações frontais com barra");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este exercício permite concentrar o trabalho na parte anterior dos deltóides.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Parte anterior dos deltóides.\n" +
                            "Músculo de acção secundária: Parte medial e posterior dos deltóides.");
                    exercicios.add(exercicio);


                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Elevações frontais com halter / em pronação");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este exercício permite concentrar o trabalho na parte anterior dos deltóides.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Parte anterior dos deltóides.\n" +
                            "Músculo de acção secundária: Parte medial e posterior dos deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Elevações frontais com halter / em semi-pronação");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação das elevações frontais utiliza uma agarre do tipo martelo (semi-pronação). Alguns atletas afirmam sentir mais o trabalho na parte anterior dos deltoides com esta variação.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Elevação lateral com halteres");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este exercício permite concentrar o trabalho na parte medial dos deltóides.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Parte medial dos deltóides.\n" +
                            "Músculo de acção secundária: Parte anterior e posterior dos deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Elevação lateral na máquina");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação do exercício elevação lateral é realizado numa máquina colocando os cotovelos a 90º, as mãos nos agarres e os braços por debaixo dos suportes almofadados.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Elevação lateral na polia");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação do exercício elevação lateral é realizado com recurso a uma polia baixa de uma forma bastante semelhante ao exercício elevação lateral com halter com a vantagem de manter a tensão constante ao longo de todo o movimento.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Voos (elevações posteriores) com halteres");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este exercício permite concentrar o trabalho na parte posterior dos deltóides.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Parte posterior dos deltóides, rombóides.\n" +
                            "Músculo de acção secundária: Parte anterior e medial dos deltóides.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Voos com halteres / cabeça apoiada");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação permite a realização do movimento sem batota, pois restringe o impulso que a grande maioria dos atletas utiliza para iniciar o movimento, permitindo assim um maior isolamento do músculo-alvo, a parte posterior dos deltóides.\n" +
                            "\n");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Voos em máquina");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Os voos em máquina têm diversas vantagens em relação às variações com pesos. Permitir a realização de um movimento estável, que não requer muita coordenação, possui um suporte para o peitoral, o que evita tensões na zona lombar, e mantém uma tensão constante ao longo de todo o movimento.\n" +
                            "\n" +
                            "Esta variação é portanto, a ideal para os principiantes e indivíduos com impedimentos físicos. É excelente para a aquisição de força e aprimoramento da técnica adequada, para posteriormente se partir para os movimentos com pesos livres, caso assim o deseje.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Parte posterior dos deltóides, rombóides.\n" +
                            "Músculo de acção secundária: Parte anterior e medial dos deltóides.\n");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Voos em polia alta");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação permite a manutenção do mesmo nível de tensão ao longo de todo o movimento e também não provoca tensões na zona lombar.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Parte posterior dos deltóides, rombóides.\n" +
                            "Músculo de acção secundária: Parte anterior e medial dos deltóides.");
                    exercicios.add(exercicio);
                }

                //Panturrilhas
                if (gm.getNomeGrupoMuscular().equals("Panturrílhas")) {

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Elevações de gémeos / panturrilhas em pé");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("É imperativo não forçar o “fecho” da articulação do joelho, (especialmente se estiver a usar cargas elevadas) de forma a prevenir lesões.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Gémeos (gastrocnémio medial, gastrocnémio lateral)\n" +
                            "Músculo de acção secundária: Sóleo\n" +
                            "Nota: Não deverá querer modificar a posição dos pés em nenhum exercício de gémeos / sóleo, porque (surpreendentemente?) a posição paralela dos pés é aquela que ativa de forma máxima a parte interna (medial), bem como a parte externa (lateral) do músculo gastrocnémio.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Elevações de gémeos / panturrilhas tipo burro");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Segundos os resultados de um estudo em que foram realizadas análises EMG, esta é a variação de elevações de gémeos mais eficiente para treinar os gémeos.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Gémeos (gastrocnémio medial, gastrocnémio lateral)\n" +
                            "Músculo de acção secundária: Sóleo");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Elevações de gémeos / panturrilhas em máquina");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação é a ideal para quem tem problemas lombares como hérnias discais e não pode portanto suportar grandes cargas.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Gémeos (gastrocnémio medial, gastrocnémio lateral)\n" +
                            "Músculo de acção secundária: Sóleo");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Elevações de gémeos / panturrilhas sentado");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Esta variação do exercício elevações de gémeos trabalha em maior grau o músculo sóleo, que se encontra detrás dos gastrocnémios medial e lateral. Deverá formar parte de um programa que inclua treinos de pernas.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Sóleo\n" +
                            "Músculo de acção secundária: Gémeos (gastrocnémio medial, gastrocnémio lateral)");
                    exercicios.add(exercicio);

                }

                //Quadríceps
                if (gm.getNomeGrupoMuscular().equals("Quadríceps")) {

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Agachamento com barra");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("O exercício número um para trabalhar as pernas, a forma clássica de realizar o agachamento é com uma barra livre à nuca. Este exercício composto deverá formar a base do seu  treino de as pernas e não pode faltar no seu programa de treino.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Quadríceps (vasto lateral, vasto medial, vasto intermédio e reto femoral).\n" +
                            "Músculos de acção secundária: Glúteo maior, isquiotibiais, adutores, eretores da espinha, reto abdominal.\n" +
                            "Nota: Para uma maior eficiência, desça de forma controlada até ficar com os joelhos a um ângulo de 70°. Mantenha-se o mais reto possível durante toda a extensão do movimento, evite deslocar o tronco para a frente. Mantenha sempre o arco natural das costas, de forma a minimizar a ocorrência de lesões.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Agachamento frontal");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("O agachamento frontal é uma variação do agachamento clássico com barra na nuca, que tem a particularidade de obrigar o atleta a manter-se perfeitamente reto durante toda amplitude do movimento. Trabalha os quadríceps com igual intensidade, mas requer menos peso e pode portanto ser mais adequado para quem sofre de problemas lombares e não suporta grandes cargas.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Quadríceps (vasto lateral, vasto medial, vasto intermédio e reto femoral).\n" +
                            "Músculos de acção secundária: Glúteo maior, isquiotibiais, adutores, eretores da espinha, reto abdominal.\n");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Agachamento hack em máquina");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("O agachamento hack (encostado na máquina, joelhos a um ângulo de 50°)  é, segundo um estudo em que se realizarem testes EMG, o exercício que mais ativa os músculos quadríceps. Se tem acesso a esta máquina, não perca a oportunidade de retirar o máximo proveito deste exercício.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Quadríceps (vasto lateral, vasto medial, vasto intermédio e reto femoral).\n" +
                            "Músculos de acção secundária: Glúteo maior, isquiotibiais, adutores, eretores da espinha, reto abdominal.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Prensa de pernas");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("A prensa de pernas fica em terceiro lugar na lista dos exercícios mais eficientes para os quadríceps, logo a seguir ao agachamento hack em máquina e ao agachamento com barra. Esta é uma possível alternativa para quem sofre de problemas lombares e não consegue suportar ou realizar os vários tipos de agachamento com barra ou agachamento em máquina. Também é uma excelente alternativa para adicionar variedade aos seus treinos.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Quadríceps (vasto lateral, vasto medial, vasto intermédio e reto femoral).\n" +
                            "Músculos de acção secundária: Glúteo maior, isquiotibiais, adutores.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Afundo");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Um excelente exercício para aqueles que não conseguem suportar grandes cargas como no agachamento. Devido a só se treinar uma perna de cada vez, a carga que necessita de levantar é reduzida em quase metade. A imagem acima é bastante descritiva, dê um passo à frente e regresse à posição inicial, depois repita com a outra perna.\n" +
                            "\n" +
                            "Nota: Se pretende recrutar mais os quadríceps, dê um passo mais curto, e caso pretenda recrutar mais os iquiotibiais e glúteos, dê um passo mais longo.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Quadríceps (vasto lateral, vasto medial, vasto intermédio e reto femoral), Glúteo maior.\n" +
                            "Músculos de acção secundária: Isquiotibiais, adutores.");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Afundo com barra");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Nesta variação, a única diferença é que se usa uma barra em vez de halteres, o que altera o centro de gravidade. utilize a variação em que se consegue equilibrar melhor, e/ou sentir melhor os quadríceps e glúteos a trabalhar. Também pode sempre variar, de forma a evitar que a monotonia se instale nos treinos.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento composto (poliarticular).\n" +
                            "Músculos de acção principal: Quadríceps (vasto lateral, vasto medial, vasto intermédio e reto femoral), Glúteo maior.\n" +
                            "Músculos de acção secundária: Isquiotibiais, adutores.\n");
                    exercicios.add(exercicio);

                    exercicio = new Exercicio();
                    exercicio.setNomeExercicio("Extensões de pernas");
                    exercicio.setGrupoMuscular(gm.getId());
                    exercicio.setDescricao("Este é um exercícios de isolamento para os quadríceps. Assegure-se de utilizar pesos que pode movimentar de forma controlada e com boa técnica e realize uma contracção de pico no final do movimento.\n" +
                            "\n" +
                            "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                            "Músculos de acção principal: Quadríceps (Vasto lateral, vasto medial, vasto intermédio e reto femoral)");
                    exercicios.add(exercicio);

                }
                    //Abdomem
                    if (gm.getNomeGrupoMuscular().equals("Abdômen")) {

                        exercicio = new Exercicio();
                        exercicio.setNomeExercicio("Flexão do quadril suspenso em barra fixa");
                        exercicio.setGrupoMuscular(gm.getId());
                        exercicio.setDescricao("Muito mais do que acontece com os exercícios para outras partes corporais, existe uma grande variedade interpessoal em termos de quais exercícios abdominais funcionam melhor para cada pessoa. Mas segundo um estudo em que foram realizadas análises EMG, este é o exercício mais eficiente para trabalhar o reto abdominal.\n" +
                                "\n" +
                                "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                                "Músculos de acção principal: Reto abdominal, oblíquos externos.\n" +
                                "Músculos de acção secundária: Reto femoral");
                        exercicios.add(exercicio);

                        exercicio = new Exercicio();
                        exercicio.setNomeExercicio("Flexão do quadril em banco plano");
                        exercicio.setGrupoMuscular(gm.getId());
                        exercicio.setDescricao("Esta variante da flexão do quadril é mais indicada para os principiantes que ainda não desenvolveram força suficiente nos abdominais para poderem realizar a flexão de quadril em barra fixa.\n" +
                                "\n" +
                                "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                                "Músculos de acção principal: Reto abdominal, oblíquos externos.\n" +
                                "Músculos de acção secundária: Reto femoral");
                        exercicios.add(exercicio);

                        exercicio = new Exercicio();
                        exercicio.setNomeExercicio("Flexão do quadril em banco inclinado");
                        exercicio.setGrupoMuscular(gm.getId());
                        exercicio.setDescricao("Mais uma variante do exercício flexão de quadril, também indicado para principiantes.\n" +
                                "\n" +
                                "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                                "Músculos de acção principal: Reto abdominal, oblíquos externos.\n" +
                                "Músculos de acção secundária: Reto femoral");
                        exercicios.add(exercicio);

                        exercicio = new Exercicio();
                        exercicio.setNomeExercicio("Flexão do quadril em banco inclinado com halter");
                        exercicio.setGrupoMuscular(gm.getId());
                        exercicio.setDescricao("Nesta variação do exercício, utiliza-se um halter para aumentar a resistência do exercício.\n" +
                                "\n" +
                                "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                                "Músculos de acção principal: Reto abdominal, oblíquos externos.\n" +
                                "Músculos de acção secundária: Reto femoral");
                        exercicios.add(exercicio);

                        exercicio = new Exercicio();
                        exercicio.setNomeExercicio("Abdominal com flexão do quadril");
                        exercicio.setGrupoMuscular(gm.getId());
                        exercicio.setDescricao("Neste exercício, deverá mover o tronco e as pernas ao mesmo tempo, fazendo com que se aproximem um do outro e posteriormente se afastem. É um exercício exigente e que trabalha bastante bem o reto do abdómem e oblíquos externos.\n" +
                                "\n" +
                                "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                                "Músculos de acção principal: Reto abdominal, oblíquos externos.\n" +
                                "Músculos de acção secundária: Reto femoral");
                        exercicios.add(exercicio);

                        exercicio = new Exercicio();
                        exercicio.setNomeExercicio("Abdominal em banco inclinado");
                        exercicio.setGrupoMuscular(gm.getId());
                        exercicio.setDescricao("Um exercício de abdominais clássico. Poderá encontrar um banco destes em praticamente todos os ginásios do planeta. Tem a desvantagem de trabalhar também o psoas ilíaco. Deverá evitar este exercício no caso de ter problemas lombares. Para melhores resultados e para minimizar a ocorrência de lesões na região cervical, mantenha as mãos atrás ou à frente do tronco e não na cabeça.\n" +
                                "\n" +
                                "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                                "Músculos de acção principal: Reto abdominal, oblíquos externos.\n" +
                                "Músculos de acção secundária: Reto femoral");
                        exercicios.add(exercicio);

                        exercicio = new Exercicio();
                        exercicio.setNomeExercicio("Abdominal em polia alta");
                        exercicio.setGrupoMuscular(gm.getId());
                        exercicio.setDescricao("Um dos melhores exercícios de abdominais em existência. Tem a vantagem de manter uma tensão / resistência constante ao longo de toda a amplitude do movimento.\n" +
                                "\n" +
                                "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                                "Músculos de acção principal: Reto abdominal, oblíquos externos.\n" +
                                "Músculos de acção secundária: Reto femoral");
                        exercicios.add(exercicio);

                        exercicio = new Exercicio();
                        exercicio.setNomeExercicio("Rotação do tronco com bastão");
                        exercicio.setGrupoMuscular(gm.getId());
                        exercicio.setDescricao("Este exercício trabalha sobretudo os músculos oblíquos externos e internos. Deverá evitar este exercício no caso de ter problemas lombares / espinha dorsal.\n" +
                                "\n" +
                                "Tipo de exercício: Movimento de isolamento (monoarticular).\n" +
                                "Músculos de acção principal: Reto abdominal, oblíquos externos.\n" +
                                "Músculos de acção secundária: Reto femoral");
                        exercicios.add(exercicio);

                        exercicio = new Exercicio();
                        exercicio.setNomeExercicio("Prancha");
                        exercicio.setGrupoMuscular(gm.getId());
                        exercicio.setDescricao("A prancha (plank) é um excelente exercício isométrico para trabalhar os abdominais, praticamento isento de riscos e não requer nenhum tipo de equipamento.\n" +
                                "\n" +
                                "Tipo de exercício: Movimento de isolamento (monoarticular),  isométrico.\n" +
                                "Músculos de acção principal: Reto abdominal, oblíquos externos.\n" +
                                "Músculos de acção secundária: Reto femoral");
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

        for (Exercicio e : listaExercicios) {
            if ((e.getNomeExercicio().equals(exercicioCorrente.getNomeExercicio()))) {
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
        if (verificarIdUsuarioNoExercicio(exercicio, exerciciosListaActivity)) {
            this.dh = new DatabaseHelper(exerciciosListaActivity);
            this.exercicioDao = new ExercicioDao(this.dh.getConnectionSource());
            this.exercicioDao.deleteById(exercicio.getId());
        }

    }

    private boolean verificarIdUsuarioNoExercicio(Exercicio exercicio, ExerciciosListaActivity exerciciosListaActivity) throws SQLException {
        if (exercicio.getIdUsuario() == null) {
            throw new ExercicioNaoCadastradoPeloUsuario("Impossível, este exercicio você não pode apagar!");
        }
        int id = Integer.parseInt(exercicio.getIdUsuario());
        if (id == 1) {
            return true;
        } else
            return false;
    }

    public void verificarExercicioCadastradoPeloUsuario(Exercicio exercicio, ExerciciosListaActivity exerciciosListaActivity) throws SQLException {
        if (exercicio.getIdUsuario() == null) {
            throw new ExercicioNaoCadastradoPeloUsuario("Impossível, você não pode editar esse exercício!");
        } else {
            Intent i = new Intent(exerciciosListaActivity, AdicionarExercicioActivity.class);
            i.putExtra("exercicio", (Parcelable) exercicio);
            exerciciosListaActivity.startActivity(i);
        }

    }

    public void atualizar(Exercicio exercicioCorrente, AdicionarExercicioActivity adicionarExercicioActivity, Exercicio exercicio) throws SQLException {
        this.dh = new DatabaseHelper(adicionarExercicioActivity);
        ExercicioDao treinoDao = new ExercicioDao(this.dh.getConnectionSource());
        UpdateBuilder<Exercicio, Integer> updateBuilder = treinoDao.updateBuilder();
        updateBuilder.updateColumnValue("nomeExercicio", exercicioCorrente.getNomeExercicio());
        updateBuilder.updateColumnValue("descricao", exercicioCorrente.getDescricao());
        updateBuilder.where().eq("id", exercicio.getId());
        updateBuilder.update();
    }
}
