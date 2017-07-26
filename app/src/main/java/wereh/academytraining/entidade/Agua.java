package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wellington on 26/07/17.
 */

@DatabaseTable(tableName = "Alimento")
public class Agua implements Parcelable, Serializable {

    public Agua(){}

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private int metaDiaria;

    @DatabaseField
    private int quantidadeConsumida;

    @DatabaseField(columnName = "dia", dataType = DataType.DATE_STRING,format = "dd/MM/yyyy")
    private Date dia;

    protected Agua(Parcel in) {
        id = in.readInt();
        metaDiaria = in.readInt();
        quantidadeConsumida = in.readInt();
        dia = new Date(in.readLong());
    }

    public static final Creator<Agua> CREATOR = new Creator<Agua>() {
        @Override
        public Agua createFromParcel(Parcel in) {
            return new Agua(in);
        }

        @Override
        public Agua[] newArray(int size) {
            return new Agua[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMetaDIaria() {
        return metaDiaria;
    }

    public void setMetaDIaria(int metaDIaria) {
        this.metaDiaria = metaDIaria;
    }

    public int getQuantidadeConsumida() {
        return quantidadeConsumida;
    }

    public void setQuantidadeConsumida(int quantidadeConsumida) {
        this.quantidadeConsumida = quantidadeConsumida;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(metaDiaria);
        dest.writeInt(quantidadeConsumida);
        dest.writeLong(dia.getTime());
    }
}
