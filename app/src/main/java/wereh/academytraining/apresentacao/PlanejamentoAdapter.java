package wereh.academytraining.apresentacao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.entidade.Planejamento;

/**
 * Created by wellington on 07/05/17.
 */

public class PlanejamentoAdapter extends BaseAdapter {

    private Context context;
    private List<Planejamento> listaPlanejamento;


    public PlanejamentoAdapter(Context context, List<Planejamento> listaPlanejamento){
        this.context = context;
        this.listaPlanejamento = listaPlanejamento;
    }

    @Override
    public int getCount() {
        return listaPlanejamento.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPlanejamento.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Planejamento gm = listaPlanejamento.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.planejamento_item_lista, null);

        TextView nomePlanj = (TextView)layout.findViewById(R.id.txtNomePlanejamento);
        nomePlanj.setText(gm.getNomePlanejamento());

        TextView objetivo = (TextView)layout.findViewById(R.id.txtObjetivo);
        objetivo.setText(gm.getObjetivo());

        TextView vezesSemana = (TextView)layout.findViewById(R.id.txtViewVezesSemana);
        vezesSemana.setText(Integer.toString(gm.getVezesNaSemana()));





//        ImageView img = (ImageView)layout.findViewById(R.id.imgView);
//        img.setImageResource(gm.getGrupoImagen(position));

        return layout;
    }
}
