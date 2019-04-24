package fag.edu.com.fretecalculo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fag.edu.com.fretecalculo.Models.Estado;
import fag.edu.com.fretecalculo.Models.Municipio;
import fag.edu.com.fretecalculo.Models.valorFrete;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static TextView selecaoOrigem, selecaoDestino;
    TextView tvresultadoCalculo;
    Button btCalular, btOrigem, btDestino;
    public static Estado estadoSelO = null;
    public static Estado estadoSelD = null;
    public static Municipio municipioSelO= null;
    public static Municipio municipioSelD = null;
    public static List<Estado> estadoList = new ArrayList<>();
    public static List<valorFrete> valorFreteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        loadData();
        loadXML();
        loadActions();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadData() {

        List<Municipio> cidadeList = new ArrayList<>();
        List<Integer> cepList = new ArrayList<>();
        cepList.add(85960000);
        cepList.add(85903490);
        cepList.add(85906250);
        cidadeList.add(new Municipio(1, "Marechal Cândido Rondon", cepList));
        cepList = new ArrayList<>();

        cepList.add(85915060);
        cepList.add(85903490);
        cepList.add(85903640);
        cidadeList.add(new Municipio(2, "Toledo", cepList));
        cepList = new ArrayList<>();

        cepList.add(85906707);
        cepList.add(85905270);
        cepList.add(85905340);
        cidadeList.add(new Municipio(3, "Curitiba", cepList));
        estadoList.add(new Estado(1, "PR", "Paraná", cidadeList));
        //ESTADO 1

        cidadeList = new ArrayList<>();
        cepList = new ArrayList<>();
        cepList.add(85906735);
        cepList.add(85907436);
        cepList.add(85901047);
        cidadeList.add(new Municipio(4, "Blumenau", cepList));

        cepList = new ArrayList<>();
        cepList.add(85905630);
        cepList.add(85911230);
        cepList.add(85908310);
        cidadeList.add(new Municipio(5, "Chapecó", cepList));

        cepList = new ArrayList<>();
        cepList.add(85914030);
        cepList.add(85904515);
        cepList.add(85902210);
        cidadeList.add(new Municipio(6, "Nagegantes", cepList));
        estadoList.add(new Estado(2, "SC", "Santa-Catarina", cidadeList));

        cidadeList = new ArrayList<>();
        cepList = new ArrayList<>();
        cepList.add(85915215);
        cepList.add(85903240);
        cepList.add(85900020);
        cidadeList.add(new Municipio(7, "Rio Grande", cepList));

        cepList = new ArrayList<>();
        cepList.add(85905010);
        cepList.add(85901210);
        cepList.add(85900270);
        cidadeList.add(new Municipio(8, "Santa Maria", cepList));

        cepList = new ArrayList<>();
        cepList.add(85905050);
        cepList.add(85903650);
        cepList.add(85911106);
        cidadeList.add(new Municipio(9, "Passo Fundo", cepList));
        estadoList.add(new Estado(3, "RS", "Rio Grande Do Sul", cidadeList));

        valorFreteList.add(new valorFrete(1, getEstadoById(1), getEstadoById(1), 2));
        valorFreteList.add(new valorFrete(2, getEstadoById(1), getEstadoById(2), 4));
        valorFreteList.add(new valorFrete(3, getEstadoById(1), getEstadoById(3), 16));
        valorFreteList.add(new valorFrete(4, getEstadoById(2), getEstadoById(1), 32));
        valorFreteList.add(new valorFrete(5, getEstadoById(2), getEstadoById(2), 64));
        valorFreteList.add(new valorFrete(6, getEstadoById(2), getEstadoById(3), 128));
        valorFreteList.add(new valorFrete(7, getEstadoById(3), getEstadoById(1), 256));
        valorFreteList.add(new valorFrete(8, getEstadoById(3), getEstadoById(2), 512));
        valorFreteList.add(new valorFrete(9, getEstadoById(3), getEstadoById(3), 1024));

    }

    private Estado getEstadoById(int i) {
        for (Estado e : estadoList) {
            if (e.getCodigo() == i)
                return e;
        }
        return null;
    }


    private void loadActions() {
        btDestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelecaoLocal.class);
                intent.putExtra("TIPO", 1);
                startActivity(intent);
            }
        });

        btOrigem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelecaoLocal.class);
                intent.putExtra("TIPO", 2);
                startActivity(intent);
            }
        });

        btCalular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(valorFrete f : valorFreteList){
                    if(f.getEstadoDestino().getCodigo() == estadoSelD.getCodigo() && f.getEstadoOrigem().getCodigo() == estadoSelO.getCodigo()){

                        tvresultadoCalculo.setText(String.valueOf(f.getValor()));
                    }

                }
            }
        });
    }

    private void loadXML() {
        btCalular = findViewById(R.id.btCalcular);
        btOrigem = findViewById(R.id.btOrigem);
        btDestino = findViewById(R.id.btDestino);
        selecaoDestino = findViewById(R.id.tvselecaoDestino);
        selecaoOrigem = findViewById(R.id.tvselecaoOrigem);
        tvresultadoCalculo = findViewById(R.id.tvresultadoCalculo);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;
        if (id == R.id.nav_cad_estado) {
            // Handle the camera action
            intent = new Intent(MainActivity.this, CadastroEstado.class);
            intent.putExtra("TIPOCADASTRO", 1);
            startActivity(intent);
        } else if (id == R.id.nav_cad_municipio) {
            intent = new Intent(MainActivity.this, CadastroMunicipo.class);
            intent.putExtra("TIPOCADASTRO", 2);
            startActivity(intent);
        } else if (id == R.id.nav_cad_cep) {
            intent = new Intent(MainActivity.this, CadastroCEP.class);
            intent.putExtra("TIPOCADASTRO", 3);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
