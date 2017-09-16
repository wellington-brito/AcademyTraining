package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.fragments.DialogBoxSistemaTreino;

public class ProtocolosHiitActivity extends AppCompatActivity {
    int itemEscolhido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocolos_hiit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DialogBoxSistemaTreino ds = new DialogBoxSistemaTreino();
        ds.show(getSupportFragmentManager(), "aviso");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.itemEscolhido = bundle.getInt("posição");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        carregarTela();
    }

    private void carregarTela() {
        if (this.itemEscolhido == 0 ){
            TextView titulo = (TextView) findViewById(R.id.textViewTitulo);
            TextView desc1 = (TextView) findViewById(R.id.textViewDesc1);
            TextView desc2 = (TextView) findViewById(R.id.textViewDesc2);
            ImageView autor = (ImageView) findViewById(R.id.imageViewAutor);
            autor.setImageResource(R.drawable.gibala);
            titulo.setText("Protocolo Gibala");
            desc1.setText(R.string.Gibala1);
            desc2.setText(R.string.Gibala2);
        }
        if (this.itemEscolhido == 1 ){
            TextView titulo = (TextView) findViewById(R.id.textViewTitulo);
            TextView desc1 = (TextView) findViewById(R.id.textViewDesc1);
            TextView desc2 = (TextView) findViewById(R.id.textViewDesc2);
            ImageView autor = (ImageView) findViewById(R.id.imageViewAutor);
            autor.setImageResource(R.drawable.tabata);
            titulo.setText("Protocolo Tabata");
            desc1.setText(R.string.Tabata1);
            desc2.setText(R.string.Tabata2);
        }
    }
}
