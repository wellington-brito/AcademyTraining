package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import wereh.academytraining.R;

public class AlimentacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void carregarListaGruposAlimentares(View v) {
        Intent i = new Intent(this, GruposAlimentaresListaActivity.class);
        startActivity(i);
    }

    public void carregarTiposAlimentares(View v) {
        Intent i = new Intent(this, TiposAlimentares.class);
        startActivity(i);
    }
}
