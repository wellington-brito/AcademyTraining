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
import wereh.academytraining.entidade.AlimentosConsumidos;

/**
 * Created by wellington on 05/06/17.
 */

public class AlimentosConsumidosAdatper extends BaseAdapter {
    private Context context;
    private List<AlimentosConsumidos> checklist;


    public AlimentosConsumidosAdatper(Context context, List<AlimentosConsumidos> listaAlimentosChecklist){
        this.context= context;
        this.checklist = listaAlimentosChecklist;
    }


    @Override
    public int getCount() {
        return checklist.size();
    }

    @Override
    public Object getItem(int position) {
        return checklist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AlimentosConsumidos alimentosConsumidos = checklist.get(position);
        SimpleDateFormat formatt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.alimentos_consumidos_item_lista, null);


        TextView nomeAlimento = (TextView)layout.findViewById(R.id.textViewDia);
        nomeAlimento.setText(alimentosConsumidos.getAlimennto());


        TextView numeroPorcao = (TextView)layout.findViewById(R.id.textViewNumeroPorcoes);
        numeroPorcao.setText(Integer.toString(alimentosConsumidos.getNumeroPorcoes()));
////

        String diaHoraFormatada = formatt.format(alimentosConsumidos.getDia());
        TextView diaHora = (TextView)layout.findViewById(R.id.textViewDiaHora);
        diaHora.setText(diaHoraFormatada);
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
