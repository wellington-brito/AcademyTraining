package wereh.academytraining.apresentacao.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.AdicionarDietaActivity;
import wereh.academytraining.apresentacao.AdicionarPlanejamento;
import wereh.academytraining.apresentacao.DadosPlanejamentoActivity;
import wereh.academytraining.entidade.Dieta;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.exceptions.DependenciaDeTreinoException;
import wereh.academytraining.negocio.DietaBo;
import wereh.academytraining.negocio.PlanejamentoBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.DietaDao;

import static wereh.academytraining.R.id.listViewDieta;


public class FragmentDietaActivity extends Fragment {

    ListView mListView;
    private List<Dieta> listaDietas;
    private DatabaseHelper dh;
    private DietaDao dietaDao;


    public FragmentDietaActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mListView = (ListView)getActivity().findViewById(listViewDieta);
        this.dh = new DatabaseHelper(getContext());

    }

    @Override  // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_alimentacao, container, false);
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

        this.dietaDao  = new DietaDao(dh.getConnectionSource());

        try {
            listaDietas = this.dietaDao.queryForAll();
            this.mListView = (ListView)getActivity().findViewById(listViewDieta);
            this.mListView.setAdapter( new DietaAdatper(getContext(), listaDietas));
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
        menu.setHeaderTitle(listaDietas.get(info.position).getNomeDieta());
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_listview_dieta, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (getUserVisibleHint()) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Fragment fragment = this;
            if (fragment instanceof FragmentDietaActivity) {
                int id = item.getItemId();
            //
            //        if (id == R.id.action_Menu_Apagar) {
            //            try {
            //                apagarDieta(info);
            //                this.carregarLista();
            //            } catch (SQLException e) {
            //                e.printStackTrace();
            //            }catch (DependenciaDeTreinoException d){
            //                Toast.makeText(this.getContext(), d.getMessage(), Toast.LENGTH_SHORT).show();
            //            }
            //        }

                if (id == R.id.action_Menu_Alterar) {
                    Intent i = new Intent(this.getContext(), AdicionarDietaActivity.class);
                    i.putExtra("dieta", (Parcelable) listaDietas.get(info.position));
                    startActivity(i);
                }

            //        if (id == R.id.action_Menu_Detalhes) {
            //            Intent intent = new Intent(this.getContext(), DadosPlanejamentoActivity.class);
            //            intent.putExtra("planejamento", (Parcelable) listaDietas.get(info.position));
            //            startActivity(intent);
            //        }
            }
        }

        return super.onOptionsItemSelected(item);
        //return true;

    }
//    private void apagarDieta(AdapterView.AdapterContextMenuInfo info) throws SQLException {
//        Dieta dieta = this.listaDietas.get(info.position);
//        DietaBo dietaBo = new DietaBo();
//        dietaBo.apagarDieta(dieta, this);
//    }

}
