package wereh.academytraining.apresentacao.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Treino;

/**
 * Created by Were on 16/04/2017.
 */

public class FichaDeTreinoAdapter extends BaseAdapter {

    private Context context;
    private List<Treino> listaTreinos;

    public FichaDeTreinoAdapter(Context context, List<Treino> listaTreinostreinos){
        this.context= context;
        this.listaTreinos = listaTreinostreinos;
    }


    @Override
    public int getCount() {
        return listaTreinos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTreinos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Treino treino = listaTreinos.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.treinos_item_lista, null);

        TextView nomeExercicio = (TextView)layout.findViewById(R.id.txtNomeExercicio);
        nomeExercicio.setText(treino.getNomeExercicio());

//        TextView series = (TextView)layout.findViewById(R.id.txtViewSerie);
//        series.setText(treino.getSerie());
//
//        TextView repeticoes = (TextView)layout.findViewById(R.id.txtViewRepeticao);
//        repeticoes.setText(treino.getRepeticao());
//
//        TextView carga = (TextView)layout.findViewById(R.id.txtViewCarga);
//        carga.setText(treino.getCarga());
//
//        TextView intervalo = (TextView)layout.findViewById(R.id.txtIntervalo);
//        intervalo.setText(treino.getIntervalo());
//
//
//        TextView observacao = (TextView)layout.findViewById(R.id.txtViewObs);
//        observacao.setText(treino.getObservacao());


//        ImageView img = (ImageView)layout.findViewById(R.id.imgView);
//        img.setImageResource(R.mipmap.ic_launcher);

        return layout;
    }
}
