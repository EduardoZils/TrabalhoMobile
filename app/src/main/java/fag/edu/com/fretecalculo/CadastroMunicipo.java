package fag.edu.com.fretecalculo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fag.edu.com.fretecalculo.Models.Estado;
import fag.edu.com.fretecalculo.Models.Municipio;
import fag.edu.com.fretecalculo.Util.Mensagem;
import fag.edu.com.fretecalculo.Util.TipoMensagem;

import static fag.edu.com.fretecalculo.MainActivity.estadoList;

public class CadastroMunicipo extends AppCompatActivity {

    Spinner spEstado;
    EditText etNome, etCodigo;
    Button btCriar;
    private List<Estado> estados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_municipo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carregaXML();
        carregaComponents();
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

    private void carregaXML() {
        spEstado = findViewById(R.id.spEstado);
        etNome = findViewById(R.id.etNomeMunicipio);
        etCodigo = findViewById(R.id.etCodigoMunicipio);
        btCriar = findViewById(R.id.btCriarMunicipio);
    }

    private void carregaComponents() {
        estados = MainActivity.estadoList;

        ArrayAdapter<Estado> adapter = new ArrayAdapter<Estado>(CadastroMunicipo.this, //Contexto
                R.layout.item_lista, //Layout
                estados); // Lista de Objetos
        spEstado.setAdapter(adapter);


    }

    private void carregaEventos() {
        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estado estado = (Estado) spEstado.getSelectedItem();

                List<Municipio> municipios = estado.getMunicipios();
                if(municipios == null){
                    municipios = new ArrayList<Municipio>();
                }
                if (etNome.getText().toString().length() > 0 && etCodigo.getText().toString().length() > 0) {
                    Municipio m = new Municipio();
                    m.setNome(etNome.getText().toString());
                    m.setCodigo(Integer.parseInt(etCodigo.getText().toString()));
                    municipios.add(m);
                    estado.setMunicipios(municipios);
                    Mensagem.ExibirMensagem(CadastroMunicipo.this, "Cidade criada com sucesso", TipoMensagem.SUCESSO);
                } else {
                    Mensagem.ExibirMensagem(CadastroMunicipo.this, "Preencha todos os campos", TipoMensagem.ALERTA);
                }
            }
        });

    }


}
