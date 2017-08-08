package wereh.academytraining.apresentacao.adpters;

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

/**
 * Created by Were on 07/04/2017.
 */

public class GruposMuscularesAdapter extends BaseAdapter {

    private Context context;
    private List<GrupoMuscular> listaGruposMusculares;


    public GruposMuscularesAdapter(Context context, List<GrupoMuscular> listaGruposMusculares){
        this.context = context;
        this.listaGruposMusculares = listaGruposMusculares;
    }

    @Override
    public int getCount() {
        return this.listaGruposMusculares.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaGruposMusculares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GrupoMuscular gm = this.listaGruposMusculares.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.grupos_musculares_item_lista, null);

        TextView nomeGm = (TextView)layout.findViewById(R.id.nomeGrupoMuscular);
        nomeGm.setText(gm.getNomeGrupoMuscular());

        ImageView img = (ImageView)layout.findViewById(R.id.imgView);
        img.setImageResource(gm.getGrupoImagen(position));

        return layout;
    }
}
