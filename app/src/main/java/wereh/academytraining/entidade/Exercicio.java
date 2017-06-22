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




//            case 1:
//                return(R.drawable.ic_biceps);
//            case 2:
//                return (R.mipmap.ic_launcher);
//            case 3:
//                return(R.drawable.ic_backs);
//            case 4:
//                return(R.drawable.ic_shoulders);
//            case 5:
//                return(R.drawable.ic_legs);
//            case 6:
//                return (R.mipmap.ic_launcher);
//            case 7:
//                return(R.drawable.ic_abs);
            default:
                return (R.mipmap.ic_launcher);
        }
    }
}
