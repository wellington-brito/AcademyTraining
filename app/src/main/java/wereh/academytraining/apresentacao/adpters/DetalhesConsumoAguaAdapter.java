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
import wereh.academytraining.entidade.Agua;

/**
 * Created by wellington on 02/08/17.
 */

public class DetalhesConsumoAguaAdapter extends BaseAdapter {
    private Context context;
    private List<Agua> aguaConsumidaLista;

    public DetalhesConsumoAguaAdapter(Context context, List<Agua> aguaConsumidaLista){
        this.context= context;
        this.aguaConsumidaLista = aguaConsumidaLista;
    }

    @Override
    public int getCount() {
        return aguaConsumidaLista.size();
    }

    @Override
    public Object getItem(int position) {
        return aguaConsumidaLista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Agua agua = aguaConsumidaLista.get(position);
        SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.agua_consumida_item_lista, null);


        TextView mlConsumido = (TextView)layout.findViewById(R.id.textViewAgua);
        mlConsumido.setText(agua.getQuantidadeConsumida()+" ml");

        TextView meta = (TextView)layout.findViewById(R.id.textViewMeta);
        meta.setText(Double.toString(agua.getMetaDIaria())+" ml");

        String diaHoraFormatada = formatt.format(agua.getDia());
        TextView diaHora = (TextView)layout.findViewById(R.id.textViewDia);
        diaHora.setText(diaHoraFormatada);


        return layout;
    }
}
