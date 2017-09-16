package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import wereh.academytraining.R;

public class ProtocolosHiitListaActiivity extends AppCompatActivity {
    private String[] protocolos = new String[]{"Protocolo Gibala", "Protocolo Tabata"};
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocolos_hiit_lista_actiivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.mListView = (ListView) findViewById(R.id.listViewProtocolos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, protocolos);
        this.mListView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ProtocolosHiitActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("posição", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
