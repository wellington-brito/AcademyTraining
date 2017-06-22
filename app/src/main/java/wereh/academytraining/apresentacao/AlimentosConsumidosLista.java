package wereh.academytraining.apresentacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.fragments.AlimentosConsumidosAdatper;
import wereh.academytraining.entidade.AlimentosConsumidos;
import wereh.academytraining.negocio.AlimentosConsumidosBo;
import wereh.academytraining.persistencia.DatabaseHelper;

public class AlimentosConsumidosLista extends AppCompatActivity {

    ListView mListView;
    private List<AlimentosConsumidos> listaAlimentosConsumidoses;
    private DatabaseHelper dh;
    private AlimentosConsumidosBo alimentosConsumidosBo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos_consumidos_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarListaAlimentosPricipais);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onResume() {
        try {
            super.onResume();
            this.carregarLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  void carregarLista() throws SQLException {

        this.alimentosConsumidosBo = new AlimentosConsumidosBo();

        try {
            listaAlimentosConsumidoses = this.alimentosConsumidosBo.buscarAlimentosCheckList(this);
            this.mListView = (ListView)findViewById(R.id.alimentosConsumidos);
            this.mListView.setAdapter( new AlimentosConsumidosAdatper(this, listaAlimentosConsumidoses));
            registerForContextMenu(mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //método para carregar o menu de opçoes no item da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
//        menu.setHeaderTitle(listaAlimentosConsumidoses.get(info.position).getNomeDieta());
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_lista_alimentos_consumidos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();




                int id = item.getItemId();

                if (id == R.id.action_Menu_Apagar) {
                    try {
                        apagar(listaAlimentosConsumidoses.get(info.position));
                        this.carregarLista();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

//                if (id == R.id.action_Menu_Alterar) {
//                    Intent i = new Intent(this.getContext(), AdicionarDietaActivity.class);
//                    i.putExtra("dieta", (Parcelable) listaAlimentosConsumidoses.get(info.position));
//                    startActivity(i);
//                }

                //        if (id == R.id.action_Menu_Detalhes) {
                //            Intent intent = new Intent(this.getContext(), DadosPlanejamentoActivity.class);
                //            intent.putExtra("planejamento", (Parcelable) listaAlimentosConsumidoses.get(info.position));
                //            startActivity(intent);
                //        }



        return super.onOptionsItemSelected(item);
        //return true;

    }

    private void apagar(AlimentosConsumidos alimentosConsumidos) throws SQLException {
        AlimentosConsumidosBo alimentosConsumidosBo = new AlimentosConsumidosBo();
        try {
            alimentosConsumidosBo.apagar(alimentosConsumidos, this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
