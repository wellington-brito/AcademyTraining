package wereh.academytraining.apresentacao;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import wereh.academytraining.apresentacao.adpters.SuplementosAdapter;
import wereh.academytraining.entidade.Suplemento;
import wereh.academytraining.negocio.SuplementoBo;

public class SuplementosActivity extends AppCompatActivity {

    ListView mListViewPreTreino;
    ListView mListViewPosTreino;
    SuplementoBo suplementoBo;
    List<Suplemento> listaSuplementos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suplementos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuplementosActivity.this, AdicionarSuplemento.class);
                startActivity(intent);

            }
        });
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

    private void carregarLista() {
        try {
            this.suplementoBo = new SuplementoBo();
            this.listaSuplementos = this.suplementoBo.carregarListaSuplementos(this);
            this.mListViewPosTreino = (ListView) findViewById(R.id.listViewSuplementos);
            this.mListViewPosTreino.setAdapter(new SuplementosAdapter(this, listaSuplementos));
            registerForContextMenu(mListViewPosTreino);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //método para carregar o menu de opçoes no item da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
       // menu.setHeaderTitle(listaSuplementos.get(info.position).getNome());
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_listview_suplementos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
       final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();
        if (id == R.id.action_Menu_Apagar) {
            new AlertDialog.Builder(this)
                    .setIcon(R.mipmap.ic_delete_black_24dp)
                    .setTitle("Apagando Suplemento")
                    .setMessage("Tem certeza ?")
                    .setPositiveButton("Sim",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    try {
                                apagar(listaSuplementos.get(info.position));

                                carregarLista();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                                }
                            })
                    .setNegativeButton("Não", null)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void apagar(Suplemento suplemento) throws SQLException {
        SuplementoBo suplementoBo = new SuplementoBo();
        try {
            suplementoBo.apagar(suplemento, this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
