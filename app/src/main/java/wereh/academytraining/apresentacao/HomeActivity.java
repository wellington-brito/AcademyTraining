package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.apresentacao.fragments.FragmentRotinaAlimentarActivity;
import wereh.academytraining.entidade.Alimento;
import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.GrupoAlimentar;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.apresentacao.fragments.FragmentActivityPlanejamentos;
import wereh.academytraining.negocio.AlimentoBo;
import wereh.academytraining.negocio.ExercicioBo;
import wereh.academytraining.negocio.GrupoAlimentarBo;
import wereh.academytraining.negocio.GruposMuscularesBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.DatabaseManager;

public class HomeActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DatabaseHelper dh;
    static int idtab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        DatabaseManager.init(HomeActivity.this);

        viewPager = (ViewPager) findViewById(R.id.viewpagerAtivityHome);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        try {
            cadastrarGruposMusculares();
            cadastrarGruposAlimentares();
            cadastrarExercicios();
            cadastrarAlimentos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onResume() {
        try {
            super.onResume();
//            int idtab = tabLayout.getSelectedTabPosition();
            Log.i("IDTAB", Integer.toString(idtab));
            TabLayout.Tab tab = tabLayout.getTabAt(idtab);
            tab.select();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void criarPlanejamento(View v) throws SQLException {
        idtab = tabLayout.getSelectedTabPosition();
        Intent i = new Intent(this, AdicionarPlanejamento.class);
        startActivity(i);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentActivityPlanejamentos(), "Planej. Treinos");
        adapter.addFragment(new FragmentRotinaAlimentarActivity(), "Rotina Alimentar");
        // adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_Ficha) {
            idtab = tabLayout.getSelectedTabPosition();
            Intent i = new Intent(this, AdicionarPlanejamento.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_Perfil) {
            idtab = tabLayout.getSelectedTabPosition();
            Log.i("IDTAB", Integer.toString(idtab));
            Intent i = new Intent(this, UsuarioActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_Medidas_Corporais) {
            idtab = tabLayout.getSelectedTabPosition();
            Log.i("IDTAB", Integer.toString(idtab));
            Intent i = new Intent(this, MedidasCorporaisActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_Material) {
            idtab = tabLayout.getSelectedTabPosition();
            Intent i = new Intent(this, FontesReferencias.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_Contador){
            idtab = tabLayout.getSelectedTabPosition();
            Intent i = new Intent(this, ContadorTreino.class);
            startActivity(i);
        }

        if (id == R.id.action_Notificações){
            idtab = tabLayout.getSelectedTabPosition();
            Intent i = new Intent(this, AlertasActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    private void cadastrarGruposMusculares() throws SQLException {
        GruposMuscularesBo gruposMuscularesBo = new GruposMuscularesBo();
        List<GrupoMuscular> listaGruposMusculares = gruposMuscularesBo.buscarGrupos(this);
        if (listaGruposMusculares == null || listaGruposMusculares.size() == 0 ){
            gruposMuscularesBo.cadastrarGruposMusculares(this);
        }
    }

    private void cadastrarGruposAlimentares()throws SQLException {
        GrupoAlimentarBo grupoAlimentarBo = new GrupoAlimentarBo();
        List<GrupoAlimentar> listaGruposAlimentares = grupoAlimentarBo.buscarGrupos(this);
        if (listaGruposAlimentares == null || listaGruposAlimentares.size() == 0 ){
            grupoAlimentarBo.cadastrarGruposAlimentares(this);
        }
    }

    private void cadastrarExercicios() throws SQLException {
        ExercicioBo exercicioBo = new ExercicioBo();
        List<Exercicio> listaExercicios = exercicioBo.buscarExercicios(this);
        if(listaExercicios == null || listaExercicios.size() == 0){
            exercicioBo.cadastrarExercicios(this);
        }
    }

    private void cadastrarAlimentos() throws SQLException {
        AlimentoBo alimentoBo = new AlimentoBo();
        List<Alimento> listaAlimentos = alimentoBo.buscarAlimentos(this);
        if (listaAlimentos == null || listaAlimentos.size() == 0){
            alimentoBo.cadastrarAlimentos(this);
        }
    }

    public void carregarAlimentosPrincipais(View v){
        idtab = tabLayout.getSelectedTabPosition();
        Intent i = new Intent(this, AlimentosConsumidosLista.class);
        startActivity(i);
    }

    public void carregarAlimentacaoActivity(View v){
        idtab = tabLayout.getSelectedTabPosition();
        Intent i = new Intent(HomeActivity.this, AlimentacaoActivity.class);
        startActivity(i);

    }

    public void carregarSuplementosActivity(View v){
        idtab = tabLayout.getSelectedTabPosition();
        Intent i = new Intent(HomeActivity.this, SuplementosActivity.class);
        startActivity(i);

    }

}
