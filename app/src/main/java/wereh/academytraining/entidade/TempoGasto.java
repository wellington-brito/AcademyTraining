package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wellington on 01/07/17.
 */
@DatabaseTable(tableName = "TempoGasto")
public class TempoGasto implements Parcelable, Serializable{

    public TempoGasto(){}

    public TempoGasto(Parcel in) {
        id = in.readInt();
        nomePlnejamento = in.readString();
        tempo = in.readString();
    }

    public static final Creator<TempoGasto> CREATOR = new Creator<TempoGasto>() {
        @Override
        public TempoGasto createFromParcel(Parcel in) {
            return new TempoGasto(in);
        }

        @Override
        public TempoGasto[] newArray(int size) {
            return new TempoGasto[size];
        }
    };



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePlnejamento() {
        return nomePlnejamento;
    }

    public void setNomePlnejamento(String nomePlnejamento) {
        this.nomePlnejamento = nomePlnejamento;
    }

    public Date getDiaTreino() {
        return diaTreino;
    }

    public void setDiaTreino(Date diaTreino) {
        this.diaTreino = diaTreino;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    @DatabaseField(generatedId = true)
    int id;

    @DatabaseField
    String nomePlnejamento;

    @DatabaseField
    Date diaTreino;

    @DatabaseField
    String tempo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomePlnejamento);
        dest.writeString(tempo);
    }
}
