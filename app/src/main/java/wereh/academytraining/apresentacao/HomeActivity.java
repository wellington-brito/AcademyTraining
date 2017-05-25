package wereh.academytraining.apresentacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wereh.academytraining.R;
import wereh.academytraining.entidade.Exercicio;
import wereh.academytraining.entidade.GrupoMuscular;
import wereh.academytraining.entidade.Planejamento;
import wereh.academytraining.exceptions.NaoExistePlanejamentoException;
import wereh.academytraining.apresentacao.fragments.Alimentacao;
import wereh.academytraining.apresentacao.fragments.FragmentActivityPlanejamentos;
import wereh.academytraining.negocio.ExercicioBo;
import wereh.academytraining.negocio.GruposMuscularesBo;
import wereh.academytraining.persistencia.DatabaseHelper;
import wereh.academytraining.persistencia.DatabaseManager;
import wereh.academytraining.persistencia.PlanejamentoDao;

public class HomeActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DatabaseHelper dh;


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
            cadastrarExercicios();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idtab = tabLayout.getSelectedTabPosition();
                if(idtab == 0) {
                    try {
                        verificarPlanejamentoa();// criar novo treino se já existir um planejamento cadastrado
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (NaoExistePlanejamentoException n){
                        Toast.makeText(HomeActivity.this, n.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void verificarPlanejamentoa() throws SQLException {
        this.dh = new DatabaseHelper(HomeActivity.this);
        PlanejamentoDao planejamentoDao = new PlanejamentoDao(dh.getConnectionSource());
        List<Planejamento> listaPlanejamentos = planejamentoDao.queryForAll();

        if (listaPlanejamentos.size() < 1 || listaPlanejamentos == null) {
            throw new NaoExistePlanejamentoException("Cadastre um planejamento primeiro");
        }else{
            Intent i = new Intent(HomeActivity.this, AdicionarTreinoActivity.class);
            startActivity(i);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentActivityPlanejamentos(), "Planej. Treinos");
        adapter.addFragment(new Alimentacao(), "Dieta");
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
            Intent i = new Intent(this, AdicionarPlanejamento.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_Perfil) {
            Intent i = new Intent(this, UsuarioActivity.class);
            startActivity(i);
            return true;
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

    private void cadastrarExercicios() throws SQLException {
        ExercicioBo exercicioBo = new ExercicioBo();
        List<Exercicio> listaExercicios = exercicioBo.buscarExercicios(this);
        if(listaExercicios == null || listaExercicios.size() == 0){
            exercicioBo.cadastrarExercicios(this);
        }
    }

}
