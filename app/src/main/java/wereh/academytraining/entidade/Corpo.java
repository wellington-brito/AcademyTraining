package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wellington on 04/08/17.
 */
@DatabaseTable(tableName = "Corpo")
public class Corpo implements Parcelable, Serializable {
    public Corpo(){}
    @DatabaseField
    public float quadril;

    @DatabaseField
    public float perna;

    @DatabaseField
    public float biceps;

    @DatabaseField(columnName = "dataAlteracao", dataType = DataType.DATE_STRING,format = "dd/MM/yyyy")
    private Date dataAlteracao;


    public static final Creator<Corpo> CREATOR = new Creator<Corpo>() {
        @Override
        public Corpo createFromParcel(Parcel in) {
            return new Corpo(in);
        }

        @Override
        public Corpo[] newArray(int size) {
            return new Corpo[size];
        }
    };

    public float getQuadril() {
        return quadril;
    }

    public void setQuadril(float quadril) {
        this.quadril = quadril;
    }

    public float getPerna() {
        return perna;
    }

    public void setPerna(float perna) {
        this.perna = perna;
    }

    public float getBiceps() {
        return biceps;
    }

    public void setBiceps(float biceps) {
        this.biceps = biceps;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Corpo(Parcel in) {
        quadril = in.readFloat();
        perna = in.readFloat();
        biceps = in.readFloat();
        dataAlteracao = new Date(in.readLong());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(quadril);
        dest.writeFloat(perna);
        dest.writeFloat(biceps);
        dest.writeLong(dataAlteracao.getTime());
    }
}
