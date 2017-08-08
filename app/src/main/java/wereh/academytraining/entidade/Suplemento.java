package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.security.PublicKey;

/**
 * Created by wellington on 28/07/17.
 */
@DatabaseTable(tableName = "Suplemento")
public class Suplemento implements Serializable, Parcelable {

    public static final String HORARIO_FIELD_NAME = "horario";

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String horario;


    public  Suplemento(){}


    public Suplemento(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        horario = in.readString();
    }

    public static final Creator<Suplemento> CREATOR = new Creator<Suplemento>() {
        @Override
        public Suplemento createFromParcel(Parcel in) {
            return new Suplemento(in);
        }

        @Override
        public Suplemento[] newArray(int size) {
            return new Suplemento[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(horario);
    }
}
