package wereh.academytraining.entidade;

/**
 * Created by wellington on 19/06/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;


@DatabaseTable(tableName = "TipoAlimento")
public class TipoAlimento implements Parcelable, Serializable {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nomeTipo;

    @DatabaseField
    private String descricao;

    public TipoAlimento(Parcel in) {
        id = in.readInt();
        nomeTipo = in.readString();
        descricao = in.readString();
    }

    public static final Creator<TipoAlimento> CREATOR = new Creator<TipoAlimento>() {
        @Override
        public TipoAlimento createFromParcel(Parcel in) {
            return new TipoAlimento(in);
        }

        @Override
        public TipoAlimento[] newArray(int size) {
            return new TipoAlimento[size];
        }
    };

    public TipoAlimento() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomeTipo);
        dest.writeString(descricao);
    }
}
