package wereh.academytraining.apresentacao.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Dieta;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.negocio.DietaBo;
import wereh.academytraining.negocio.PlanejamentoBo;

/**
 * Created by wellington on 05/06/17.
 */

public class DietaAdatper extends BaseAdapter {
    private Context context;
    private List<Dieta> listaTreinos;

    public DietaAdatper(Context context, List<Dieta> listaTreinostreinos){
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

        Dieta dieta = listaTreinos.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dietas, null);

        TextView nomeDieta = (TextView)layout.findViewById(R.id.txtNomeDieta);
        nomeDieta.setText(dieta.getNomeDieta());


        TextView vezseSemana = (TextView)layout.findViewById(R.id.textViewVezesSeman);
        vezseSemana.setText(Integer.toString(dieta.getVezesNaSemana()));
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
