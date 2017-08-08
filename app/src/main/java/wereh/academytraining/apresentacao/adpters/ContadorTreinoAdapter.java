package wereh.academytraining.apresentacao.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.TempoGasto;

/**
 * Created by wellington on 06/07/17.
 */

public class
ContadorTreinoAdapter extends BaseAdapter {



    public Context context;
    private List<TempoGasto> listaContagemTreinos;

    public ContadorTreinoAdapter(){}

    public ContadorTreinoAdapter(Context context, List<TempoGasto> listaAlimentos ){
        this.context = context;
        this.listaContagemTreinos = listaAlimentos;
    }

    @Override
    public int getCount() {
        return listaContagemTreinos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaContagemTreinos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TempoGasto tempoGasto = listaContagemTreinos.get(position);
        SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.tempo_gasto_item_lista, null);

        TextView planejamento = (TextView)layout.findViewById(R.id.textViewDia);
        planejamento.setText(tempoGasto.getNomePlnejamento());

        TextView tempo = (TextView)layout.findViewById(R.id.textViewTempo);
        tempo.setText(tempoGasto.getTempo());

        String diaHoraFormatada = formatt.format(tempoGasto.getDiaTreino());
        TextView diaHora = (TextView)layout.findViewById(R.id.textViewData);
        diaHora.setText(diaHoraFormatada);

        return layout;
    }
}
