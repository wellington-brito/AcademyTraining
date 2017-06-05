package wereh.academytraining.apresentacao.fragments;

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
import wereh.academytraining.apresentacao.AdicionarPlanejamento;
import wereh.academytraining.apresentacao.DadosPlanejamentoActivity;
import wereh.academytraining.apresentacao.PlanejamentoAdapter;
import wereh.academytraining.entidade.Dieta;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.exceptions.DependenciaDeDietaException;
import wereh.academytraining.exceptions.DependenciaDeTreinoException;
import wereh.academytraining.negocio.DietaBo;
import wereh.academytraining.negocio.PlanejamentoBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.PlanejamentoDao;

import static wereh.academytraining.R.id.listViewFichaDeTreino;

public class FragmentActivityPlanejamentos extends Fragment {

    /// listagem de todos os planejamentos  ou a ficha completa de treino de um determinado período

    ListView mListView;
    private List<Planejamento> listaPlanejamentos;
    private DatabaseHelper dh;
    private PlanejamentoDao planejamentoDao;


    public FragmentActivityPlanejamentos() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mListView = (ListView)getActivity().findViewById(listViewFichaDeTreino);
        this.dh = new DatabaseHelper(getContext());
    }

    @Override  // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ficha_de_treino, container, false);
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

        this.planejamentoDao  = new PlanejamentoDao(dh.getConnectionSource());

        try {
            listaPlanejamentos = this.planejamentoDao.queryForAll();
            this.mListView = (ListView)getActivity().findViewById(listViewFichaDeTreino);
            this.mListView.setAdapter( new PlanejamentoAdapter(getContext(), listaPlanejamentos));
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
        menu.setHeaderTitle(listaPlanejamentos.get(info.position).getNomePlanejamento());
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_listview, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (getUserVisibleHint()) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Fragment fragment = this;
            if (fragment instanceof FragmentActivityPlanejamentos) {
                int id = item.getItemId();

                if (id == R.id.action_Menu_Apagar) {
                    try {
                        List<Dieta> listadDietasAssociadas = carregarDietasAssociadas(info);
                        apagarPlanejamento(info, listadDietasAssociadas);
                        this.carregarLista();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }catch (DependenciaDeTreinoException d){
                        Toast.makeText(this.getContext(), d.getMessage(), Toast.LENGTH_SHORT).show();
                    }catch (DependenciaDeDietaException d){
                        Toast.makeText(this.getContext(), d.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                if (id == R.id.action_Menu_Alterar) {

                    Intent i = new Intent(this.getContext(), AdicionarPlanejamento.class);
                    i.putExtra("planejamento", (Parcelable) listaPlanejamentos.get(info.position));
                    startActivity(i);

                }

                if (id == R.id.action_Menu_Detalhes) {
                    Intent intent = new Intent(this.getContext(), DadosPlanejamentoActivity.class);
                    intent.putExtra("planejamento", (Parcelable) listaPlanejamentos.get(info.position));
                    startActivity(intent);
                }

            }
        }




           return super.onOptionsItemSelected(item);
            //return true;

    }

    private List<Dieta> carregarDietasAssociadas(AdapterView.AdapterContextMenuInfo info) throws SQLException {
        Planejamento planejamento = listaPlanejamentos.get(info.position);
        DietaBo dietaBo = new DietaBo();
        return  dietaBo.buscarDietaPorPlanejamento(planejamento, this);
    }

    private void apagarPlanejamento(AdapterView.AdapterContextMenuInfo info, List<Dieta> listadDietasAssociadas) throws SQLException {
        Planejamento planejamento = listaPlanejamentos.get(info.position);
        PlanejamentoBo planejamentoBo = new PlanejamentoBo();
        planejamentoBo.apagarPlanejamento(planejamento, FragmentActivityPlanejamentos.this,listadDietasAssociadas );
    }
}
