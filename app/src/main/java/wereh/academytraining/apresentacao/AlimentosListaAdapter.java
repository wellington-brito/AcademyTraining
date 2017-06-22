package wereh.academytraining.apresentacao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Alimento;

/**
 * Created by wellington on 08/06/17.
 */

public class AlimentosListaAdapter extends BaseAdapter {

    public Context context;
    private List<Alimento> listaAlimentos;
   // public  List<Alimento> alimentosSelecionados;


    public Alimento alimento;

    public AlimentosListaAdapter(){}

    public AlimentosListaAdapter(Context context, List<Alimento> listaAlimentos ){
        this.context = context;
        this.listaAlimentos = listaAlimentos;
    }

    @Override
    public int getCount() {
        return listaAlimentos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAlimentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Alimento alimento = listaAlimentos.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.alimentos_item_lista, null);

        TextView nomeAlimento = (TextView)layout.findViewById(R.id.txtNomeAlimento);
        nomeAlimento.setText(alimento.getNomeAlimento());

        TextView peso = (TextView)layout.findViewById(R.id.txtPesoAlimento);
        peso.setText(Double.toString(alimento.getPeso()));

        TextView medida = (TextView)layout.findViewById(R.id.textViewMedida);
        medida.setText(alimento.getMedidaCaseira());


        return layout;
    }


}
