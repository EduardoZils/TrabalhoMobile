package fag.edu.com.fretecalculo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import fag.edu.com.fretecalculo.Models.Estado;
import fag.edu.com.fretecalculo.Models.Municipio;
import fag.edu.com.fretecalculo.Util.Mensagem;
import fag.edu.com.fretecalculo.Util.TipoMensagem;

public class CadastroCEP extends AppCompatActivity {


    EditText etNovo;
    Button btCadastrar;
    Spinner spMunicipio, spEstado;

    private List<Estado> estados = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cep);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carregaXML();

        carregaComponentes();

        carregaEventos();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void carregaComponentes() {
        estados = MainActivity.estadoList;


        ArrayAdapter<Estado> adapter = new ArrayAdapter<Estado>(CadastroCEP.this, //Contexto
                R.layout.item_lista, //Layout
                estados); // Lista de Objetos
        spEstado.setAdapter(adapter);

        Estado estado = (Estado) spEstado.getSelectedItem();

        ArrayAdapter<Municipio> adapterMunicipio = new ArrayAdapter<Municipio>(CadastroCEP.this, //Contexto
                R.layout.item_lista, //Layout
                estado.getMunicipios()); // Lista de Objetos
        spMunicipio.setAdapter(adapterMunicipio);
    }

    private void carregaXML() {
        etNovo = findViewById(R.id.etNovoCEP);
        btCadastrar = findViewById(R.id.btCadastrarCEP);
        spMunicipio = findViewById(R.id.spMunicipioCEP);
        spEstado = findViewById(R.id.spEstadoCEP);
    }

    private void carregaEventos() {
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estado estado = (Estado) spEstado.getSelectedItem();
                Municipio municipio = (Municipio) spMunicipio.getSelectedItem();
                List<Integer> ceps;
                if (municipio.getCEP() == null){
                    ceps = new ArrayList<Integer>();
                }else{
                    ceps = municipio.getCEP();
                }
                try {
                    ceps.add(Integer.parseInt(etNovo.getText().toString()));
                    municipio.setCEP(ceps);
                    Mensagem.ExibirMensagem(CadastroCEP.this, "CEP criado com sucesso", TipoMensagem.SUCESSO);
                }catch (Exception ex){
                    Mensagem.ExibirMensagem(CadastroCEP.this, "Erro", TipoMensagem.ERRO);
                }
            }
        });
        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Estado estado = (Estado)spEstado.getItemAtPosition(position);
                ArrayAdapter<Municipio> adapterMunicipio = new ArrayAdapter<Municipio>(CadastroCEP.this, //Contexto
                        R.layout.item_lista, //Layout
                        estado.getMunicipios()); // Lista de Objetos
                spMunicipio.setAdapter(adapterMunicipio);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
