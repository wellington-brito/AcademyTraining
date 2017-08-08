package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wellington on 22/05/17.
 */
@DatabaseTable(tableName = "Carga")
public class Carga implements Serializable, Parcelable {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    String nomeExercico;
    @DatabaseField
    int carga;
    @DatabaseField(columnName = "dataAlteracao", dataType = DataType.DATE_STRING,format = "dd/MM/yyyy")
    private Date dataAlteracao;

    public Carga(){}

    protected Carga(Parcel in) {
        id = in.readInt();
        nomeExercico = in.readString();
        carga = in.readInt();
        dataAlteracao = new Date(in.readLong());
    }

    public static final Creator<Carga> CREATOR = new Creator<Carga>() {
        @Override
        public Carga createFromParcel(Parcel in) {
            return new Carga(in);
        }

        @Override
        public Carga[] newArray(int size) {
            return new Carga[size];
        }
    };

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public String getNomeExercico() {
        return nomeExercico;
    }

    public void setNomeExercico(String nomeExercico) {
        this.nomeExercico = nomeExercico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomeExercico);
        dest.writeInt(carga);
        dest.writeLong(dataAlteracao.getTime());
    }
}
