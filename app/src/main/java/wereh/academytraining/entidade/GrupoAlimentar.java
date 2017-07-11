package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import wereh.academytraining.R;

/**
 * Created by wellington on 02/06/17.
 */

@DatabaseTable(tableName = "GrupoAlimentar")
public class GrupoAlimentar implements Parcelable, Serializable {

    public GrupoAlimentar(){}


    protected GrupoAlimentar(Parcel in) {
        id = in.readInt();
        nomeGrupoAlimentar = in.readString();
        calorias = in.readInt();
    }

    public static final Creator<GrupoAlimentar> CREATOR = new Creator<GrupoAlimentar>() {
        @Override
        public GrupoAlimentar createFromParcel(Parcel in) {
            return new GrupoAlimentar(in);
        }

        @Override
        public GrupoAlimentar[] newArray(int size) {
            return new GrupoAlimentar[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeGrupoAlimentar() {
        return nomeGrupoAlimentar;
    }

    public void setNomeGrupoAlimentar(String nomeGrupoAlimentar) {
        this.nomeGrupoAlimentar = nomeGrupoAlimentar;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nomeGrupoAlimentar;

    @DatabaseField
    public int calorias;

    public int getGrupoImagen(int position){
        switch (position){
            case 0:
                return(R.drawable.bread);
            case 1:
                return(R.drawable.broccoli);
            case 2:
                return(R.drawable.apple);
            case 3:
                return(R.drawable.carrot);
            case 4:
                return(R.drawable.meat);
            case 5:
                return(R.drawable.milk);
            case 6:
                return(R.drawable.bowl);
            case 7:
                return(R.drawable.cupcake);
            default:
                return (R.mipmap.ic_launcher);
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomeGrupoAlimentar);
        dest.writeInt(calorias);
    }
}
