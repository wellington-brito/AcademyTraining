package wereh.academytraining.apresentacao.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Suplemento;

/**
 * Created by wellington on 31/07/17.
 */

public class SuplementosAdapter extends BaseAdapter {
    private Context context;
    private List<Suplemento> suplementos;

    public SuplementosAdapter(Context context, List<Suplemento> suplementos) {
        this.context = context;
        this.suplementos = suplementos;
    }

    @Override
    public int getCount() {
        return suplementos.size();
    }

    @Override
    public Object getItem(int position) {
        return suplementos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.suplemento_item_lista, null);

        Suplemento suplemento = this.suplementos.get(position);
        TextView nomeAlimento = (TextView) layout.findViewById(R.id.textViewDia);
        nomeAlimento.setText(suplemento.getNome());
        TextView hora = (TextView) layout.findViewById(R.id.textViewHora);
        hora.setText(suplemento.getHorario());
        return layout;
    }
}
