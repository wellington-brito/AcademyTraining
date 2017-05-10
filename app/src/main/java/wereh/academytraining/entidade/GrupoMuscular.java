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

@DatabaseTable(tableName = "GrupoMuscular")
public class GrupoMuscular implements Parcelable, Serializable {

    public GrupoMuscular(){}

    protected GrupoMuscular(Parcel in) {
        id = in.readInt();
        nomeGrupoMuscular = in.readString();
        quantidadeExercicios = in.readInt();
    }

    public static final Creator<GrupoMuscular> CREATOR = new Creator<GrupoMuscular>() {
        @Override
        public GrupoMuscular createFromParcel(Parcel in) {
            return new GrupoMuscular(in);
        }

        @Override
        public GrupoMuscular[] newArray(int size) {
            return new GrupoMuscular[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeGrupoMuscular() {
        return nomeGrupoMuscular;
    }

    public void setNomeGrupoMuscular(String nomeGrupoMuscular) {
        this.nomeGrupoMuscular = nomeGrupoMuscular;
    }

    public int getQuantidadeExercicios() {
        return quantidadeExercicios;
    }

    public void setQuantidadeExercicios(int quantidadeExercicios) {
        this.quantidadeExercicios = quantidadeExercicios;
    }

    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(unique = true)
    public String nomeGrupoMuscular;

    @DatabaseField
    public int quantidadeExercicios;

    @Override
    public String toString() {
        return  nomeGrupoMuscular + '\n' +
                "ExercicioDetalhesActivity =" + quantidadeExercicios;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomeGrupoMuscular);
        dest.writeInt(quantidadeExercicios);
    }

    public int getGrupoImagen(int position){
        switch (position){
            case 0:
                return(R.drawable.ic_peitoral);
            default:
                return R.drawable.ic_biceps;
        }
    }
}
