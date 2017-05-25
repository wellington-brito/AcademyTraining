package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

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



    public Exercicio(){

    }


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
}
