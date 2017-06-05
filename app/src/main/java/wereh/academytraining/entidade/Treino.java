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
    private int serie;

    @DatabaseField
    private int repeticao;

    @DatabaseField
    private int carga;

    @DatabaseField
    private String observacao;

    @DatabaseField
    private String nomeExercicio;

    @DatabaseField
    private  int intervalo;

    private int grupoMuscular;

    @DatabaseField
    private int idPlanejamento;

    public Treino() {

    }

    protected Treino(Parcel in) {
        id = in.readInt();
        serie = in.readInt();
        repeticao = in.readInt();
        carga = in.readInt();
        observacao = in.readString();
        nomeExercicio = in.readString();
        intervalo = in.readInt();
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

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(int repeticao) {
        this.repeticao = repeticao;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
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

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public int getIntervalo() {
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
        dest.writeInt(serie);
        dest.writeInt(repeticao);
        dest.writeInt(carga);
        dest.writeString(observacao);
        dest.writeString(nomeExercicio);
        dest.writeInt(intervalo);
        dest.writeInt(idPlanejamento);
    }
}
