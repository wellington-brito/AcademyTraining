package wereh.academytraining.apresentacao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.sql.SQLException;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Usuario;
import wereh.academytraining.exceptions.CampoObrigatorioException;
import wereh.academytraining.exceptions.UsuarioCadastradoException;
import wereh.academytraining.negocio.UsuarioBo;

public class AdicionarUsuario extends AppCompatActivity {

    Usuario usuario = null;
    int qntd;
    UsuarioBo usuarioBo;
    private String[] objetivos = new String[]{"Sedentário","Atividade Leve", "Atividade Moderada", "Atividade Intensa"};
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        if (this.usuario != null){
            carregarPerfilUsuario(this.usuario);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, objetivos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.sp= (Spinner) findViewById(R.id.spinneraNivelAtiv);
        this.sp.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DefinirObjetosCampoDeTexto(view);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (CampoObrigatorioException c){
                    Toast.makeText(AdicionarUsuario.this, c.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void DefinirObjetosCampoDeTexto(View view) throws SQLException {
        EditText nome = (EditText) findViewById(R.id.editTextNome);

        EditText peso = (EditText) findViewById(R.id.editTextPeso);
        EditText altura = (EditText) findViewById(R.id.editTextAltura);
        EditText genero = (EditText) findViewById(R.id.editTextGenero);
        EditText idade = (EditText) findViewById(R.id.editTextIdade);
        this.sp = (Spinner) findViewById(R.id.spinneraNivelAtiv);
//        EditText necessidadesDiariasCalorias = (EditText) findViewById(R.id.editTextTmb);
        this.usuarioBo = new UsuarioBo();
        this.usuarioBo.validarCamposDeTexto(nome, peso, altura, genero, idade);
        definirDadosUsuario(nome, peso, altura, genero, idade);
    }


    private void definirDadosUsuario(EditText nome,  EditText peso, EditText altura, EditText genero, EditText idade) throws SQLException {
        Usuario usuarioCorrente = new Usuario();
       // DecimalFormat df = new DecimalFormat("0.##");
        usuarioCorrente.setNomeUsuario(nome.getText().toString());
      //  double p = Double.parseDouble(peso.getText().toString());
       // double a = Double.parseDouble(altura.getText().toString());
       // String pesof = df.format(p);
        usuarioCorrente.setPeso(Double.parseDouble(peso.getText().toString()));
        //String alturaf = df.format(a);
        usuarioCorrente.setAltura(Double.parseDouble(altura.getText().toString()));
        usuarioCorrente.setGenero(genero.getText().toString());
        usuarioCorrente.setIdade(Integer.parseInt(idade.getText().toString()));
        usuarioCorrente.setNivelAtividade((sp.getSelectedItem().toString()));
        double ndc = calcularTmb(usuarioCorrente);
        double imc = calcularImc(usuarioCorrente);
        usuarioCorrente.setNecessidadesDiariasCalorias(ndc);
        usuarioCorrente.setImc(imc);
        verificarUsuario(usuarioCorrente);
    }

    private void verificarUsuario(Usuario usuarioCorrente) throws SQLException {
        this.usuarioBo = new UsuarioBo();
        try {
            if (this.usuarioBo.verificarUsuarioAntesCadastro(usuarioCorrente, AdicionarUsuario.this) == 1) {
                this.usuarioBo.atualizar(usuarioCorrente, this, this.usuario);
                Toast.makeText(this, "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
            if (this.usuarioBo.verificarUsuarioAntesCadastro(usuarioCorrente, AdicionarUsuario.this) == 2) {
                this.usuarioBo.salvar(usuarioCorrente, this);
                Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }catch (UsuarioCadastradoException u){
            Toast.makeText(AdicionarUsuario.this, u.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void carregarPerfilUsuario(Usuario u) {
        EditText nome = (EditText) findViewById(R.id.editTextNome);
        EditText peso = (EditText) findViewById(R.id.editTextPeso);
        EditText altura = (EditText) findViewById(R.id.editTextAltura);
        EditText genero = (EditText) findViewById(R.id.editTextGenero);
        EditText idade = (EditText) findViewById(R.id.editTextIdade);
        this.sp= (Spinner) findViewById(R.id.spinneraNivelAtiv);
        // EditText necessidadesDiariasCalorias = (EditText) findViewById(R.id.editTextTmb);

        nome.setText(u.getNomeUsuario());
        peso.setText(Double.toString(u.getPeso()));
        altura.setText(Double.toString(u.getAltura()));
        genero.setText(u.getGenero());
        idade.setText(Integer.toString(u.getIdade()));

        if(u.getNivelAtividade().equals("Sedentário")){
            this.sp.setSelection(0);
        }else if(u.getNivelAtividade().equals("Atividade Leve")){
            sp.setSelection(1);
        }else if(u.getNivelAtividade().equals("Atividade Moderada")) {
            sp.setSelection(2);
        }else {
            sp.setSelection(3);
        }
       // necessidadesDiariasCalorias.setText(Float.toString(u.getNecessidadesDiariasCalorias()));

    }

    public double calcularTmb(Usuario usuarioCorrente) throws SQLException {
        Usuario u = usuarioCorrente;
        double c1, c2, c3, c4, tmb; // constantes para o cálculo da taxa metabólica basal

        if (u.getGenero().equals("m") || u.getGenero().equals("M")){
            c1 = 66.4730;
            c2 = 13.7516;
            c3 = 5.0033;
            c4 = 6.7550;
            tmb = c1 + (c2 * u.getPeso()) + (c3 * u.getAltura()) - (c4 * u.getIdade());
           return calcularNdcHomens(tmb, u);
        }
        if (u.getGenero().equals("F") || u.getGenero().equals("f")){
            c1 = 655.0955;
            c2 = 9.5634;
            c3 = 1.849;
            c4 = 4.6756;
            tmb = c1 + (c2 * u.getPeso()) + (c3 * u.getAltura()) - (c4 * u.getIdade());
            return  calcularNdcMulheres(tmb, u);
        }
        return 0;
    }

    public  double calcularNdcHomens(double tmb, Usuario u) throws SQLException {
        if (u.getNivelAtividade().equals("Sedentário")){
            return tmb * 1.0;
        }
        if (u.getIdade() <=18 && u.getNivelAtividade().equals("Atividade Leve")){
            return tmb * 1.3;
        }
        if (u.getIdade() > 18 && u.getNivelAtividade().equals("Atividade Leve")){
            return tmb * 1.11;
        }
        if (u.getIdade() <=18 && u.getNivelAtividade().equals("Atividade Moderada")){
            return tmb * 1.26;
        }
        if (u.getIdade() > 18 && u.getNivelAtividade().equals("Atividade Moderada")){
            return tmb * 1.25;
        }
        if (u.getIdade() <=18 && u.getNivelAtividade().equals("Atividade Intensa")){
            return tmb * 1.42;
        }
        if (u.getIdade() > 18 && u.getNivelAtividade().equals("Atividade Intensa")){
            return tmb * 1.48;
        }
        return 0;
    }

    public  double calcularNdcMulheres(double tmb, Usuario u) throws SQLException {
        if (u.getNivelAtividade().equals("Sedentário")){
            return tmb * 1.0;
        }
        if (u.getIdade() <=18 && u.getNivelAtividade().equals("Atividade Leve")){
            return tmb * 1.16;
        }
        if (u.getIdade() > 18 && u.getNivelAtividade().equals("Atividade Leve")){
            return tmb * 1.12;
        }
        if (u.getIdade() <=18 && u.getNivelAtividade().equals("Atividade Moderada")){
            return tmb * 1.31;
        }
        if (u.getIdade() > 18 && u.getNivelAtividade().equals("Atividade Moderada")){
            return tmb * 1.27;
        }
        if (u.getIdade() <=18 && u.getNivelAtividade().equals("Atividade Intensa")){
            return tmb * 1.56;
        }
        if (u.getIdade() > 18 && u.getNivelAtividade().equals("Atividade Intensa")){
            return tmb * 1.45;
        }
        return 0;
    }

    private double calcularImc(Usuario usuarioCorrente) {
        double altura = converteCMparaM(usuarioCorrente.getAltura());
        double imc = (usuarioCorrente.getPeso())/(Math.pow(altura , 2));
        return imc;
    }
    public static double converteCMparaM(Double valorCM){
        return valorCM / 100;
    }

}
