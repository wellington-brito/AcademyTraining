package wereh.academytraining.apresentacao.adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.stmt.query.In;

import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.GrupoAlimentar;

/**
 * Created by wellington on 05/06/17.
 */

public class GruposAlimentaresAdapter extends BaseAdapter {

    private Context context;
    private List<GrupoAlimentar> listaGruposAlimentares;

    public GruposAlimentaresAdapter(Context context, List<GrupoAlimentar> GruposAlimentaresAdapter){
        this.context = context;
        this.listaGruposAlimentares = GruposAlimentaresAdapter;
    }

    @Override
    public int getCount() {
        return this.listaGruposAlimentares.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaGruposAlimentares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GrupoAlimentar grupoAlimentar = this.listaGruposAlimentares.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.grupos_alimentares_item_lista, null);

        TextView nomeGa = (TextView)layout.findViewById(R.id.nomeGrupoMuscular);
        nomeGa.setText(grupoAlimentar.getNomeGrupoAlimentar());

        TextView calorias = (TextView)layout.findViewById(R.id.textViewCalo);
        calorias.setText(Integer.toString(grupoAlimentar.getCalorias()));

        ImageView img = (ImageView)layout.findViewById(R.id.imgView);
        img.setImageResource(grupoAlimentar.getGrupoImagen(position));

        return layout;
    }
}
