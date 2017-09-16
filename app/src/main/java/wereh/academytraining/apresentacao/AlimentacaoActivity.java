package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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


    public void descricaoImage(View view){
        if (view.getId() == R.id.imageView8){
            Toast.makeText(this, "Pães, Cereais, Raízes e Tubérculos", Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == R.id.imageView7){
            Toast.makeText(this, "Frutas", Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == R.id.imageView6){
            Toast.makeText(this, "Hortaliças", Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == R.id.imageView5){
            Toast.makeText(this, "Leguminosas", Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == R.id.imageView4){
            Toast.makeText(this, "Carne Bovina, Suína, Peixe, Frango, Ovos", Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == R.id.imageView3){
            Toast.makeText(this, "Produtos Lácteos", Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == R.id.imageView2){
            Toast.makeText(this, "Açúcares", Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == R.id.imageView1){
            Toast.makeText(this, "óleos e Gorduras", Toast.LENGTH_SHORT).show();
        }
    }
}
