package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.adpters.DetalhesConsumoAguaAdapter;
import wereh.academytraining.entidade.Agua;
import wereh.academytraining.entidade.Usuario;
import wereh.academytraining.exceptions.UsuarioCadastradoException;
import wereh.academytraining.negocio.AguaBo;
import wereh.academytraining.negocio.UsuarioBo;

public class DetalhesConsumoAguaActivity extends AppCompatActivity {

    private String[] mlValores = new String[]{"150 ml", "200 ml", "250 ml", "300 ml", "350 ml", "400 ml", "450 ml", "500 ml", "1 Litro", "2 Litros"};
    private ProgressBar firstBar = null;
    Spinner spinnerMlAgua;
    double metaDiariaDeAgua;
    AguaBo aguaBo;
    List<Agua> listaConsumoAgua;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_consumo_agua);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mlValores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinnerMlAgua = (Spinner) findViewById(R.id.spinnerMls);
        this.spinnerMlAgua.setAdapter(adapter);

        firstBar = (ProgressBar) findViewById(R.id.firstBar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        UsuarioBo usuarioBo = new UsuarioBo();

        try {
            calcularConsumodiarioDeAgua(fab);
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Problemas ao salvar seu consumo de agua!", Toast.LENGTH_SHORT).show();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    recuperarDados();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            carregardados();
            carrgarLista();
        } catch (SQLException e) {
            Toast.makeText(this, "Não foi possível recuperar os dados", Toast.LENGTH_SHORT).show();
        }
    }

    private void carrgarLista() {
        this.aguaBo = new AguaBo();

        try {
            this.listaConsumoAgua = this.aguaBo.buscarCoonsumoDeAgua(this);
            this.mListView = (ListView) findViewById(R.id.listViewAguaConsumo);
            this.mListView.setAdapter(new DetalhesConsumoAguaAdapter(this, this.listaConsumoAgua));
            registerForContextMenu(mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregardados() throws SQLException {
        AguaBo aguaBo = new AguaBo();
        this.listaConsumoAgua = aguaBo.buscarCoonsumoDeAgua(this);
        TextView meta = (TextView) findViewById(R.id.textViewMetaDiaria1);
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        for (Agua a : this.listaConsumoAgua) {
            int dia = a.getDia().getDate();
            int diaAguaCorrente = data_atual.getDate();
            if (dia == diaAguaCorrente) {
                configurarBarraDeProgresso(a);
            }
        }
    }

    public void calcularConsumodiarioDeAgua(FloatingActionButton fab) throws SQLException {
        UsuarioBo usuarioBo = new UsuarioBo();
        try {
            Usuario u = usuarioBo.buscarUsuario(this);
            if (u != null) {
                this.metaDiariaDeAgua = u.getPeso() * 35;
                TextView meta = (TextView) findViewById(R.id.textViewMetaDiaria1);
                meta.setText((Double.toString(metaDiariaDeAgua)) + " ml");
            }
        } catch (UsuarioCadastradoException u) {
            fab.setEnabled(false);
            Toast.makeText(this, "Usuário não encontrado " + u.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void recuperarDados() throws SQLException {
        Agua aguaCorrente = new Agua();
        aguaCorrente.setMetaDIaria(this.metaDiariaDeAgua);
        if (spinnerMlAgua.getSelectedItem().toString().equals("150 ml"))
            aguaCorrente.setQuantidadeConsumida(150);
        if (spinnerMlAgua.getSelectedItem().toString().equals("200 ml"))
            aguaCorrente.setQuantidadeConsumida(200);
        if (spinnerMlAgua.getSelectedItem().toString().equals("250 ml"))
            aguaCorrente.setQuantidadeConsumida(250);
        if (spinnerMlAgua.getSelectedItem().toString().equals("300 ml"))
            aguaCorrente.setQuantidadeConsumida(300);
        if (spinnerMlAgua.getSelectedItem().toString().equals("350 ml"))
            aguaCorrente.setQuantidadeConsumida(350);
        if (spinnerMlAgua.getSelectedItem().toString().equals("400 ml"))
            aguaCorrente.setQuantidadeConsumida(400);
        if (spinnerMlAgua.getSelectedItem().toString().equals("450 ml"))
            aguaCorrente.setQuantidadeConsumida(450);
        if (spinnerMlAgua.getSelectedItem().toString().equals("500 ml"))
            aguaCorrente.setQuantidadeConsumida(500);
        if (spinnerMlAgua.getSelectedItem().toString().equals("1 Litro"))
            aguaCorrente.setQuantidadeConsumida(1000);
        if (spinnerMlAgua.getSelectedItem().toString().equals("2 Litros"))
            aguaCorrente.setQuantidadeConsumida(2000);

        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        aguaCorrente.setDia(data_atual);

        salvar(aguaCorrente);
    }

    public void salvar(Agua aguaCorrente) throws SQLException {
        this.aguaBo = new AguaBo();
        List<Agua> listaConsumo = aguaBo.buscarCoonsumoDeAgua(this);

        if (listaConsumo.size() != 0) {
            // chama o update
            for (Agua agua : listaConsumo) {

                int diaCadastrado = agua.getDia().getDate(); // item da posição atual da lista
                int diaAguaCorrente = aguaCorrente.getDia().getDate(); // objeto atual

                if (diaAguaCorrente == diaCadastrado) {
                    int mljáconsumido = agua.getQuantidadeConsumida() + aguaCorrente.getQuantidadeConsumida();
                    aguaCorrente.setQuantidadeConsumida(mljáconsumido); // again
                    if (aguaCorrente.getQuantidadeConsumida() < agua.getMetaDIaria()) {
                        aguaBo.atualizar(aguaCorrente, this, agua);
                        carrgarLista();
                        Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                        configurarBarraDeProgresso(aguaCorrente);
                    }
                }
            }
            for (int i = 0; i < listaConsumo.size(); i++) {
                // chama o salvar
                Agua agua = listaConsumo.get(i);
                int tamanhoLista = listaConsumo.size();
                if (aguaCorrente.getDia().getDate() > agua.getDia().getDate() && tamanhoLista == i+1) {
                    this.aguaBo.salvar(aguaCorrente, this);
                    configurarBarraDeProgresso(aguaCorrente);
                    carrgarLista();
                    Toast.makeText(this, "Salvo com suceso!", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // chama o salvar
            this.aguaBo.salvar(aguaCorrente, this);
            configurarBarraDeProgresso(aguaCorrente);
            carrgarLista();
            Toast.makeText(this, "Salvo com suceso!", Toast.LENGTH_SHORT).show();
        }
    }

    public void configurarBarraDeProgresso(Agua aguacorrente) {
        firstBar.setVisibility(View.VISIBLE);
        firstBar.setMax((int) this.metaDiariaDeAgua);
        firstBar.setProgress(aguacorrente.getQuantidadeConsumida());
        TextView mlconsumido = (TextView) findViewById(R.id.textViewMl);
        mlconsumido.setText(Double.toString(aguacorrente.getQuantidadeConsumida()) + " ml");
    }

    //método para carregar o menu de opçoes no item da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //menu.setHeaderTitle(listaAlimentosConsumidoses.get(info.position).getNomeDieta());
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_listview_suplementos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();
        if (id == R.id.action_Menu_Apagar) {
            try {
                apagar(listaConsumoAgua.get(info.position));
                this.carrgarLista();
                this.carregardados();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void apagar(Agua agua) throws SQLException {
        this.aguaBo = new AguaBo();
        try {
            this.aguaBo.apagar(agua, this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
