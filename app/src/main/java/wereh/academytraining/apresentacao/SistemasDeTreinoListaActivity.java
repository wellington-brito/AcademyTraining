package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.fragments.DialogBoxSistemaTreino;

public class SistemasDeTreinoListaActivity extends AppCompatActivity {
    private String[] sistemas = new String[]{"Método Pirâmide", "Método Dropset", "Método Circuito", "Sistema Negativo"};
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistemas_de_treino_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mListView = (ListView) findViewById(R.id.listViewListaSistemaTreinos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sistemas);
        this.mListView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), SistemasdeTreinoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("posição", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
