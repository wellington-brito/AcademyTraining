package wereh.academytraining.apresentacao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Exercicio;

/**
 * Created by Were on 07/04/2017.
 */

public class ExerciciosAdapter extends BaseAdapter {
    private Context context;
    private List<Exercicio> listaExercicios;

    public ExerciciosAdapter(Context context, List<Exercicio> listaExercicios ){
        this.context = context;
        this.listaExercicios = listaExercicios;
    }

    @Override
    public int getCount() {
        return listaExercicios.size();
    }

    @Override
    public Object getItem(int position) {
        return listaExercicios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Exercicio exercicio = listaExercicios.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.exercicios, null);

        TextView nomeExercicio = (TextView)layout.findViewById(R.id.nomeExercicio);
        nomeExercicio.setText(exercicio.getNomeExercicio());

        return layout;
    }
}
