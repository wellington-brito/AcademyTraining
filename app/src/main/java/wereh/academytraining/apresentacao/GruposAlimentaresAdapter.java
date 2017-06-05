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
import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.entidade.GrupoMuscular;

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

        GrupoAlimentar gm = this.listaGruposAlimentares.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.grupos, null);

        TextView nomeGm = (TextView)layout.findViewById(R.id.nomeGrupoMuscular);
        nomeGm.setText(gm.getNomeGrupoAlimentar());

        ImageView img = (ImageView)layout.findViewById(R.id.imgView);
        img.setImageResource(gm.getGrupoImagen(position));

        return layout;
    }
}
