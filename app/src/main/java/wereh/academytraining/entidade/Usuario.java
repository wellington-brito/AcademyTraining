package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Were on 04/04/2017.
 */


@DatabaseTable(tableName = "Usuario")
public class Usuario implements Parcelable, Serializable {

    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField
    public  String nomeUsuario;

    @DatabaseField
    public float peso;

    @DatabaseField
    public float altura;

    @DatabaseField
    public String genero;

    @DatabaseField
    public float imc;

    @DatabaseField
    public float tmb;


    public Usuario() {

    }

    private Usuario(Parcel in) {
        id = in.readInt();
        nomeUsuario = in.readString();

        peso = in.readFloat();
        altura = in.readFloat();
        genero = in.readString();
        imc = in.readFloat();
        tmb = in.readFloat();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }


    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public float getTmb() {
        return tmb;
    }

    public void setTmb(float tmb) {
        this.tmb = tmb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomeUsuario);
        dest.writeFloat(peso);
        dest.writeFloat(altura);
        dest.writeString(genero);
        dest.writeFloat(imc);
        dest.writeFloat(tmb);
    }
}
