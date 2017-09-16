package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.fragments.DialogBoxSistemaTreino;

public class SistemasdeTreinoActivity extends AppCompatActivity {
    int itemEscolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistemasde_treino);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DialogBoxSistemaTreino ds = new DialogBoxSistemaTreino();
        ds.show(getSupportFragmentManager(), "aviso");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        this.itemEscolhido = bundle.getInt("posição");

        carregarTela();
    }

    private void carregarTela() {
        if (this.itemEscolhido == 0 ){
            TextView titulo = (TextView) findViewById(R.id.textViewTitulo);
            TextView desc1 = (TextView) findViewById(R.id.textViewDesc1);
            TextView desc2 = (TextView) findViewById(R.id.textViewDesc2);
            titulo.setText("Método Pirâmide");
            desc1.setText(R.string.Piramide1);
            desc2.setText(R.string.Piramide2);
        }
        if (this.itemEscolhido == 1 ){
            TextView titulo = (TextView) findViewById(R.id.textViewTitulo);
            TextView desc1 = (TextView) findViewById(R.id.textViewDesc1);
            TextView desc2 = (TextView) findViewById(R.id.textViewDesc2);
            titulo.setText("Método Dropset");
            desc1.setText(R.string.Dropset1);
            desc2.setText(R.string.Dropset2);
        }
        if (this.itemEscolhido == 2 ){
            TextView titulo = (TextView) findViewById(R.id.textViewTitulo);
            TextView desc1 = (TextView) findViewById(R.id.textViewDesc1);
            TextView desc2 = (TextView) findViewById(R.id.textViewDesc2);
            titulo.setText("Método Circuito");
            desc1.setText(R.string.Circuito1);
            desc2.setText(R.string.Circuito2);
        }
        if (this.itemEscolhido == 3 ){
            TextView titulo = (TextView) findViewById(R.id.textViewTitulo);
            TextView desc1 = (TextView) findViewById(R.id.textViewDesc1);
            TextView desc2 = (TextView) findViewById(R.id.textViewDesc2);
            titulo.setText("Sistema Negativo");
            desc1.setText(R.string.Negativo1);
            desc2.setText(R.string.Negativo2);
        }
    }


}
