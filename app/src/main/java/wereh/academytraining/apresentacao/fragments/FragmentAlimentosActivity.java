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
import wereh.academytraining.apresentacao.AlimentosPrincipaisLista;
import wereh.academytraining.apresentacao.HomeActivity;
import wereh.academytraining.entidade.CheckList;
import wereh.academytraining.negocio.CheckListBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.CheckListDao;




public class FragmentAlimentosActivity extends Fragment {

//    ListView mListView;
//    private List<CheckList> listaCheckLists;
//    private DatabaseHelper dh;
//    private CheckListBo checkListBo;


    public FragmentAlimentosActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   this.mListView = (ListView)getActivity().findViewById(listViewDieta);
      //  this.dh = new DatabaseHelper(getContext());

    }

    @Override  // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alimentos, container, false);
    }

    @Override
    public void onResume() {
        try {
            super.onResume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
