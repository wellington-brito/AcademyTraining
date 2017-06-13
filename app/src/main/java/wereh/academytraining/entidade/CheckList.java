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
@DatabaseTable(tableName = "CheckList")
public class CheckList implements Parcelable, Serializable {

    public CheckList(){}

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String alimennto;

    @DatabaseField
    private int idAlimento;

    protected CheckList(Parcel in) {
        id = in.readInt();
        alimennto = in.readString();
        idAlimento = in.readInt();
    }

    public static final Creator<CheckList> CREATOR = new Creator<CheckList>() {
        @Override
        public CheckList createFromParcel(Parcel in) {
            return new CheckList(in);
        }

        @Override
        public CheckList[] newArray(int size) {
            return new CheckList[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAlimento() {
        return alimennto;
    }

    public void setNomeAlimennto(String alimennto) {
        this.alimennto = alimennto;
    }

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(alimennto);
        dest.writeInt(idAlimento);
    }
}
