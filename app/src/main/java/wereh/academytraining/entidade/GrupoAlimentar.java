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

    public GrupoAlimentar(){

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nomeGrupoAlimentar;

//    @DatabaseField
//    public int quantidadeAlimetnos;

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

//    public int getQuantidadeAlimetnos() {
//        return quantidadeAlimetnos;
//    }
//
//    public void setQuantidadeAlimetnos(int quantidadeAlimetnos) {
//        this.quantidadeAlimetnos = quantidadeAlimetnos;
    //}

    protected GrupoAlimentar(Parcel in) {
        id = in.readInt();
        nomeGrupoAlimentar = in.readString();
        //quantidadeAlimetnos = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomeGrupoAlimentar);
      //  dest.writeInt(quantidadeAlimetnos);
    }
    public int getGrupoImagen(int position){
        switch (position){
            case 0:
                return(R.drawable.ic_peitoral);
            default:
                return R.drawable.ic_biceps;
        }
    }
}
