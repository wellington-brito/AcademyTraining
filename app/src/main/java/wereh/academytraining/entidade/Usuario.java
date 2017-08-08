package wereh.academytraining.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
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
    public String nomeUsuario;

    @DatabaseField
    public double peso;

    @DatabaseField(dataType = DataType.DOUBLE)
    public double altura;

    @DatabaseField
    public String genero;

    @DatabaseField
    public double imc;

    @DatabaseField
    public double necessidadesDiariasCalorias;


    @DatabaseField
    public  int idade;

    @DatabaseField
    public  String nivelAtividade;

    public Usuario(){}

    public Usuario(Parcel in) {
        id = in.readInt();
        nomeUsuario = in.readString();
        peso = in.readDouble();
        altura = in.readDouble();
        genero = in.readString();
        imc = in.readDouble();
        necessidadesDiariasCalorias = in.readDouble();
        idade = in.readInt();
        nivelAtividade = in.readString();
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public double getNecessidadesDiariasCalorias() {
        return necessidadesDiariasCalorias;
    }

    public void setNecessidadesDiariasCalorias(double necessidadesDiariasCalorias) {
        this.necessidadesDiariasCalorias = necessidadesDiariasCalorias;
    }


    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNivelAtividade() {
        return nivelAtividade;
    }

    public void setNivelAtividade(String nivelAtividade) {
        this.nivelAtividade = nivelAtividade;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomeUsuario);
        dest.writeDouble(peso);
        dest.writeDouble(altura);
        dest.writeString(genero);
        dest.writeDouble(imc);
        dest.writeDouble(necessidadesDiariasCalorias);
        dest.writeInt(idade);
        dest.writeString(nivelAtividade);
    }
}
