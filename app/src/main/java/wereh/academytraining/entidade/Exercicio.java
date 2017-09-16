package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import wereh.academytraining.R;

/**
 * Created by Were on 05/04/2017.
 */

@DatabaseTable(tableName = "Exercicio")
public class Exercicio implements Parcelable, Serializable{

    public static final String IDGRUPOMUSCULAR_FIELD_NAME = "grupoMuscular";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(unique = true)
    private String nomeExercicio;

    @DatabaseField(columnName = IDGRUPOMUSCULAR_FIELD_NAME )
    private int grupoMuscular;

    @DatabaseField
    private String descricao;

    @DatabaseField
    private String idUsuario;



    public Exercicio(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public int getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(int grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    @Override
    public String toString() {
        return nomeExercicio;
    }

    private Exercicio(Parcel in) {
        id = in.readInt();
        nomeExercicio = in.readString();
        descricao = in.readString();
        idUsuario = in.readString();
        grupoMuscular = in.readInt();
    }

    public static final Creator<Exercicio> CREATOR = new Creator<Exercicio>() {
        @Override
        public Exercicio createFromParcel(Parcel in) {
            return new Exercicio(in);
        }

        @Override
        public Exercicio[] newArray(int size) {
            return new Exercicio[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomeExercicio);
        dest.writeString(descricao);
        dest.writeString(idUsuario);
        dest.writeInt(grupoMuscular);

    }

    public int getExercicioImagen(String position){
        switch (position){
            //Peitoral
            case "Supino Reto":
                return(R.drawable.exercicio_peito_supino_reto_completo);
            case "Supino inclinado":
                return(R.drawable.exercicio_peito_supino_inclinado_completo);
            case "Pullover":
                return(R.drawable.exercicio_peito_pullover);
            case "PeckDeck":
                return(R.drawable.exercicio_peitoral_peck_deck);
            case "Press com cabos":
                return(R.drawable.exercicio_peito_press_com_cabos);
            case "Cross over":
                return(R.drawable.exercicio_peito_crossover_cabos);
            case "Supino declinado":
                return(R.drawable.exercicio_peito_supino_declinado);
            case "Crucifixo declinado":
                return(R.drawable.exercicio_peito_cruxifixo_declinado);
            case "Mergulho (barras paralelas)":
                return(R.drawable.exercicio_peito_mergulho_barras_paralelas);
            case "Supinos com pegadas neutras":
                return(R.drawable.exercicio_peito_supino_pegada_neutra);
            case "Crucifixo reto":
                return(R.drawable.exercicio_peitoral_cruxifixo_reto);
            case "Crucifixo inclinado":
                return(R.drawable.exercicio_peitoral_cruxifixo_inclinado);
            case "Press inclinado com cabos":
                return(R.drawable.exercicio_peito_press_inclinado_cabos);
            case "Flexões":
                return(R.drawable.exercicio_peito_flexoes);
            case "Press em máquinas articuladas":
                return(R.drawable.exercicio_peitoral_press_maquina_articulada);

            //Biceps
            case "Rosca martelo alternada":
                return(R.drawable.exercicio_biceps_rosca_martelo_alternada);
            case "Rosca alternada em banco inclinado":
                return(R.drawable.exercicio_biceps_rosca_alternada_banco_inclinado);
            case "Rosca direta com barra reta":
                return(R.drawable.exercicio_biceps_rosca_direta_barra_reta);
            case "Rosca spider com barra":
                return(R.drawable.exercicio_biceps_rosca_spider_barra);
            case "Rosca martelo com cabos":
                return(R.drawable.exercicio_biceps_rosca_martelo_cabos);
            case "Rosca scott com cabos":
                return(R.drawable.exercicio_biceps_rosca_scott_cabos);
            case "Rosca direta com barra EZ":
                return(R.drawable.exercicio_biceps_rosca_direta_barra_ez);
            case "Rosca concentrada unilateral":
                return(R.drawable.exercicio_biceps_rosca_concentrada_unilateral);
            case "Rosca martelo cruzada ao corpo":
                return(R.drawable.exercicio_biceps_rosca_martelo_cruzada_corpo);
            case "“Drag Curl”":
                return(R.drawable.exercicio_biceps_drag_curl);
            case "Rosca alternada com halteres":
                return(R.drawable.exercicio_biceps_rosca_alternada_halteres);
            case "Rosca simultânea com halteres":
                return(R.drawable.exercicio_biceps_rosca_simultanea_halteres);
            case "Rosca spider com halteres":
                return(R.drawable.exercicio_biceps_rosca_spider_halteres);
            case "Rosca martelo simultânea":
                return(R.drawable.exercicio_biceps_rosca_martelo_simultanea);

           //Tríceps
            case "Fundos em barras paralelas":
                return(R.drawable.fundos_barras_paralelas);
            case "Fundos em máquina":
                return(R.drawable.fundos_maquina);
            case "Supino pegada junta":
                return(R.drawable.supino_pega_junta);
            case "Extensões de tríceps deitado com barra":
                return(R.drawable.extensao_de_triceps_deitado);
            case "Extensões de tríceps sentado com barra":
                return(R.drawable.extensao_triceps_sentado);
            case "Extensões de tríceps sentado com halter":
                return(R.drawable.extensao_triceps_sentado_halter);
            case "Puxada de tríceps":
                return(R.drawable.puxada_triceps);
            case "Puxada de tríceps em supinação":
                return(R.drawable.puxada_para_triceps_em_supinacao);
            case "Puxada de tríceps com corda":
                return(R.drawable.puxada_para_triceps_corda1);
            case "Kickback":
                return(R.drawable.kickback);
            case "Kickback em polia":
                return(R.drawable.kickback_em_polia);


            //Costas
            case "Peso morto / levantamento terra":
                return(R.drawable.peso_morto_levantamento_terra);
            case "Puxada de dorsais em polia alta":
                return(R.drawable.puxada_dorsais_polia_alta);
            case "Puxada de dorsais em polia alta em supinação":
                return(R.drawable.puxada_dorsais_polia_alta_supinacao);
            case "Remada com barra":
                return(R.drawable.remada_com_barra);
            case "Remada com barra em supinação":
                return(R.drawable.remada_com_barra_em_supinacao);
            case "Remada em máquina “hammer”":
                return(R.drawable.remada_maquina_hammer);
            case "Remada em polia baixa”":
                return(R.drawable.remada_polia_baixa);
            case "Remada em polia baixa a 1 mão":
                return(R.drawable.remo_horizontal_a_una_mano_con_mancuernas2);
            case "Remada com halter":
                return(R.drawable.remada_com_halter);
            case "Remada em barra t":
                return(R.drawable.remada_barra_t);
            case "Encolhimentos de ombros com halteres":
                return(R.drawable.encolhimentos_de_ombros_com_halteres);
            case "Encolhimentos de ombros com barra":
                return(R.drawable.encolhimento_barra);
            case "Encolhimentos com barra atrás":
                return(R.drawable.encolhimentos_barra_atras);

            //Deltóides
            case "Press militar com barra":
                return(R.drawable.press_militar_barra);
            case "Press militar com halteres":
                return(R.drawable.press_militar_halteres1);
            case "Remada vertical":
                return(R.drawable.remada_vertical);
            case "Elevações frontais com barra":
                return(R.drawable.elevacoes_frontais_barra);
            case "Elevações frontais com halter / em pronação":
                return(R.drawable.elevacoes_frontais_pronacao);
            case "Elevações frontais com halter / em semi-pronação":
                return(R.drawable.elevacoes_frontais_semi_pronacao);
            case "Elevação lateral com halteres":
                return(R.drawable.elevacoes_laterais_halteres);
            case " Elevação lateral na máquina":
                return(R.drawable.elevacao_lateral_na_maquina);
            case "Elevação lateral na polia":
                return(R.drawable.elevacao_lateral_na_polia);
            case "Voos (elevações posteriores) com halteres":
                return(R.drawable.voos_halteres);
            case "Voos com halteres / cabeça apoiada":
                return(R.drawable.voos_halteres_cabeca_apoiada);
            case "Voos em máquina":
                return(R.drawable.voos_maquina);
            case "Voos em polia alta":
                return(R.drawable.voos_polia_alta);

            //Panturrílhas
            case "Elevações de gêmeos / panturrilhas em pé":
                return(R.drawable.elevacoes_de_gemeos_panturrilhas_em_pe);
            case "Elevações de gémeos / panturrilhas tipo burro":
                return(R.drawable.elevacoes_de_gemeos_tipo_burro_donkey_calf_raises);
            case "Elevações de gémeos / panturrilhas em máquina" :
                return(R.drawable.gemeos_panturrilha_em_maquina);
            case "Elevações de gémeos / panturrilhas sentado":
                return(R.drawable.gemeos_panturrilha_sentado);

            //Quadríceps
            case "Agachamento com barra":
                return(R.drawable.agachamento_com_barra);
            case "Agachamento frontal":
                return(R.drawable.agachamento_frontal);
            case "Agachamento hack em máquina":
                return(R.drawable.agachamento_hack);
            case "Prensa de pernas":
                return(R.drawable.prensa_de_pernas);
            case "Afundo":
                return(R.drawable.afundo);
            case "Afundo com barra":
                return(R.drawable.afundo2);
            case "Extensões de pernas":
                return(R.drawable.extensao_de_pernas);

            //Abdômen
            case "Flexão do quadril suspenso em barra fixa":
                return(R.drawable.flexao_do_quadril_suspenso_barra_fixa_abdominal);
            case "Flexão do quadril em banco plano":
                return(R.drawable.flexao_quadril_banco_abdominal);
            case "Flexão do quadril em banco inclinado":
                return(R.drawable.flexao_quadril_banco_inclinado);
            case "Flexão do quadril em banco inclinado com halter":
                return(R.drawable.flexao_quadril_banco_inclinado_peso);
            case "Abdominal com flexão do quadril":
                return(R.drawable.abdominal_com_flexao_quadril);
            case "Abdominal em banco inclinado":
                return(R.drawable.abdominal_banco);
            case "Abdominal em polia alta":
                return(R.drawable.abdominal_polia_alta);
            case "Rotação do tronco com bastão":
                return(R.drawable.rotacao_tronco_bastao);
            case "Prancha":
                return(R.drawable.prancha_abdominal);




            default:
                return (R.mipmap.ic_launcher);
        }
    }
}
