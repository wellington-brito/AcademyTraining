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

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.CheckList;
import wereh.academytraining.negocio.CheckListBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.CheckListDao;




public class FragmentAlimentosActivity extends Fragment {

    ListView mListView;
    private List<CheckList> listaCheckLists;
    private DatabaseHelper dh;
    private CheckListBo checkListBo;


    public FragmentAlimentosActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   this.mListView = (ListView)getActivity().findViewById(listViewDieta);
        this.dh = new DatabaseHelper(getContext());

    }

    @Override  // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alimentos, container, false);
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

        this.checkListBo = new CheckListBo();

        try {
            listaCheckLists = this.checkListBo.buscarAlimentosCheckList(this.getContext());
            this.mListView = (ListView)getActivity().findViewById(R.id.checkList);
            this.mListView.setAdapter( new AlimentosAdatper(getContext(), listaCheckLists));
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
//        menu.setHeaderTitle(listaCheckLists.get(info.position).getNomeDieta());
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_check_list, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (getUserVisibleHint()) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Fragment fragment = this;
            if (fragment instanceof FragmentAlimentosActivity) {
                int id = item.getItemId();
            
                    if (id == R.id.action_Menu_Apagar) {
                        try {
                            apagar(listaCheckLists.get(info.position));
                            this.carregarLista();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

//                if (id == R.id.action_Menu_Alterar) {
//                    Intent i = new Intent(this.getContext(), AdicionarDietaActivity.class);
//                    i.putExtra("dieta", (Parcelable) listaCheckLists.get(info.position));
//                    startActivity(i);
//                }

            //        if (id == R.id.action_Menu_Detalhes) {
            //            Intent intent = new Intent(this.getContext(), DadosPlanejamentoActivity.class);
            //            intent.putExtra("planejamento", (Parcelable) listaCheckLists.get(info.position));
            //            startActivity(intent);
            //        }
            }
        }

        return super.onOptionsItemSelected(item);
        //return true;

    }

    private void apagar(CheckList checkList) throws SQLException {
        CheckListBo checkListBo = new CheckListBo();
        try {
            checkListBo.apagar(checkList, this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
