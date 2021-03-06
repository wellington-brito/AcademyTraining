package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Were on 05/04/2017.
 */
@DatabaseTable(tableName = "AlimentosConsumidos")
public class AlimentosConsumidos implements Parcelable, Serializable {

    public AlimentosConsumidos(){}

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String alimennto;

    @DatabaseField
    private int idAlimento;

    @DatabaseField
    private int numeroPorcoes;

    @DatabaseField
    private int idGrupoAlimentar;

    @DatabaseField
    private Date diaHora;

    protected AlimentosConsumidos(Parcel in) {
        id = in.readInt();
        alimennto = in.readString();
        idAlimento = in.readInt();
        numeroPorcoes = in.readInt();
        idGrupoAlimentar = in.readInt();
        diaHora = new Date(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(alimennto);
        dest.writeInt(idAlimento);
        dest.writeInt(numeroPorcoes);
        dest.writeInt(idGrupoAlimentar);
        dest.writeLong(diaHora.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AlimentosConsumidos> CREATOR = new Creator<AlimentosConsumidos>() {
        @Override
        public AlimentosConsumidos createFromParcel(Parcel in) {
            return new AlimentosConsumidos(in);
        }

        @Override
        public AlimentosConsumidos[] newArray(int size) {
            return new AlimentosConsumidos[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlimennto() {
        return alimennto;
    }

    public void setAlimennto(String alimennto) {
        this.alimennto = alimennto;
    }

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public int getNumeroPorcoes() {
        return numeroPorcoes;
    }

    public void setNumeroPorcoes(int numeroPorcoes) {
        this.numeroPorcoes = numeroPorcoes;
    }

    public int getIdGrupoAlimentar() {
        return idGrupoAlimentar;
    }

    public void setIdGrupoAlimentar(int idGrupoAlimentar) {
        this.idGrupoAlimentar = idGrupoAlimentar;
    }

    public Date getDia() {
        return diaHora;
    }

    public void setDia(Date dia) {
        this.diaHora = dia;
    }
}
