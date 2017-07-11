package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Were on 05/04/2017.
 */


@DatabaseTable(tableName = "Alimento")
public class Alimento implements Parcelable, Serializable {


    public Alimento(){}

    public static final String IDGRUPOALIMENTAR_FIELD_NAME = "GrupoAlimentar";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAlimento() {
        return nomeAlimento;
    }

    public void setNomeAlimento(String nomeAlimento) {
        this.nomeAlimento = nomeAlimento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getMedidaCaseira() {
        return medidaCaseira;
    }

    public void setMedidaCaseira(String medidaCaseira) {
        this.medidaCaseira = medidaCaseira;
    }

    public int getGrupoAlimentar() {
        return grupoAlimentar;
    }

    public void setGrupoAlimentar(int grupoAlimentar) {
        this.grupoAlimentar = grupoAlimentar;
    }


    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nomeAlimento;

    @DatabaseField
    private Double peso;

    @DatabaseField
    private String medidaCaseira;

    @DatabaseField(columnName = IDGRUPOALIMENTAR_FIELD_NAME )
    private int grupoAlimentar;

    @DatabaseField
    private int tipoAlimento;

//    @DatabaseField
//    public int calorias; caso o usuário possa cadastrar alimento deverá informaro número de calorias

//    @DatabaseField
//    private String idUsuario;


    public static String getIdgrupoalimentarFieldName() {
        return IDGRUPOALIMENTAR_FIELD_NAME;
    }

    protected Alimento(Parcel in) {
        id = in.readInt();
        nomeAlimento = in.readString();
        peso = in.readDouble();
        medidaCaseira = in.readString();
        grupoAlimentar = in.readInt();
    }

    public static final Creator<Alimento> CREATOR = new Creator<Alimento>() {
        @Override
        public Alimento createFromParcel(Parcel in) {
            return new Alimento(in);
        }

        @Override
        public Alimento[] newArray(int size) {
            return new Alimento[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomeAlimento);
        dest.writeDouble(peso);
        dest.writeString(medidaCaseira);
        dest.writeInt(grupoAlimentar);
    }
}
