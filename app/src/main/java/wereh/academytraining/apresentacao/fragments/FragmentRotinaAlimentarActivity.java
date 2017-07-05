package wereh.academytraining.apresentacao.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wereh.academytraining.R;


public class FragmentRotinaAlimentarActivity extends Fragment {

//    ListView mListView;
//    private List<AlimentosConsumidos> listaCheckLists;
//    private DatabaseHelper dh;
//    private AlimentosConsumidosBo checkListBo;


    public FragmentRotinaAlimentarActivity() {
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
        return inflater.inflate(R.layout.fragment_rotina_alimentar, container, false);
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
