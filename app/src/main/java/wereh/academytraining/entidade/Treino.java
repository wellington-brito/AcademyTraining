package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Were on 05/04/2017.
 */

@DatabaseTable(tableName = "Treino")
public class Treino implements Parcelable, Serializable {


    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String serie;

    @DatabaseField
    private String repeticao;

    @DatabaseField
    private String carga;

    @DatabaseField
    private String observacao;

    @DatabaseField
    private String nomeExercicio;

    @DatabaseField
    private  String intervalo;

    private int grupoMuscular;

    @DatabaseField
    private int idPlanejamento;

    public Treino() {

    }

    protected Treino(Parcel in) {
        id = in.readInt();
        serie = in.readString();
        repeticao = in.readString();
        carga = in.readString();
        observacao = in.readString();
        nomeExercicio = in.readString();
        intervalo = in.readString();
        idPlanejamento = in.readInt();
    }

    public static final Creator<Treino> CREATOR = new Creator<Treino>() {
        @Override
        public Treino createFromParcel(Parcel in) {
            return new Treino(in);
        }

        @Override
        public Treino[] newArray(int size) {
            return new Treino[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(String repeticao) {
        this.repeticao = repeticao;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNomeExercicio() {
        return nomeExercicio;
    }

    public void setNomeExercicio(String nomeExercicio) {
        this.nomeExercicio = nomeExercicio;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }

    public String getIntervalo() {
        return intervalo;
    }

    public void setIdPlanejamento(int idPlanejamento) {
        this.idPlanejamento = idPlanejamento;
    }
    public int getIdPlanejamento() {
        return idPlanejamento;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(serie);
        dest.writeString(repeticao);
        dest.writeString(carga);
        dest.writeString(observacao);
        dest.writeString(nomeExercicio);
        dest.writeString(intervalo);
        dest.writeInt(idPlanejamento);
    }
}
