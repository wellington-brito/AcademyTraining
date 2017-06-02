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
@DatabaseTable(tableName = "Dieta")
public class Dieta implements Parcelable, Serializable {

    public Dieta(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDieta() {
        return nomeDieta;
    }

    public void setNomeDieta(String nomeDieta) {
        this.nomeDieta = nomeDieta;
    }

    public int getVezesNaSemana() {
        return vezesNaSemana;
    }

    public void setVezesNaSemana(int vezesNaSemana) {
        this.vezesNaSemana = vezesNaSemana;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nomeDieta;

    @DatabaseField
    private int vezesNaSemana;

    @DatabaseField
    private String observacao;

    @DatabaseField(columnName = "dataInicio", dataType = DataType.DATE_STRING,format = "dd/MM/yyyy")
    private Date dataInicio;

    @DatabaseField
    private int validade;

    protected Dieta(Parcel in) {
        id = in.readInt();
        nomeDieta = in.readString();
        vezesNaSemana = in.readInt();
        observacao = in.readString();
        validade = in.readInt();
    }

    public static final Creator<Dieta> CREATOR = new Creator<Dieta>() {
        @Override
        public Dieta createFromParcel(Parcel in) {
            return new Dieta(in);
        }

        @Override
        public Dieta[] newArray(int size) {
            return new Dieta[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomeDieta);
        dest.writeInt(vezesNaSemana);
        dest.writeString(observacao);
        dest.writeInt(validade);
    }
}
