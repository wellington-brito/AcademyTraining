package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wellington on 07/05/17.
 */
@DatabaseTable(tableName = "Planejamento")
public class Planejamento implements Parcelable, Serializable {


    public  Planejamento(){

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nomePlanejamento;

    @DatabaseField
    private String objetivo;

    @DatabaseField
    private int vezesNaSemana;

    @DatabaseField
    private String observacao;

    @DatabaseField(dataType = DataType.DATE_STRING,format = "dd-MM-yyyy")
    private Date dataInicio;

    @DatabaseField
    private String validade;


    protected Planejamento(Parcel in) {
        id = in.readInt();
        nomePlanejamento = in.readString();
        objetivo = in.readString();
        vezesNaSemana = in.readInt();
        observacao = in.readString();
        validade = in.readString();
    }

    public static final Creator<Planejamento> CREATOR = new Creator<Planejamento>() {
        @Override
        public Planejamento createFromParcel(Parcel in) {
            return new Planejamento(in);
        }

        @Override
        public Planejamento[] newArray(int size) {
            return new Planejamento[size];
        }
    };

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getNomePlanejamento() {
        return nomePlanejamento;
    }

    public void setNomePlanejamento(String nomePlanejamento) {
        this.nomePlanejamento = nomePlanejamento;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomePlanejamento);
        dest.writeString(objetivo);
        dest.writeInt(vezesNaSemana);
        dest.writeString(observacao);
        dest.writeString(validade);
    }
}