package wereh.academytraining.apresentacao;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import wereh.academytraining.R;

public class AnaliseImc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analise_imc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carregarImc();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void carregarImc() {
        //Preenche uma instância de um Bundle com os dados que foram passados
        Bundle b = getIntent().getExtras();
        double imc = b.getDouble("imc");
        TextView imcTextView = (TextView) findViewById(R.id.textViewValorimc);
        DecimalFormat df = new DecimalFormat("0.##");
        String imcFormatado = df.format(imc);
        imcTextView.setText(imcFormatado);
    }

}
