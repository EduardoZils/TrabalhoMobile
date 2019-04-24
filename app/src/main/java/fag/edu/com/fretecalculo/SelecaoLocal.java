package fag.edu.com.fretecalculo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fag.edu.com.fretecalculo.Models.Estado;
import fag.edu.com.fretecalculo.Models.Municipio;
import fag.edu.com.fretecalculo.Util.Mensagem;
import fag.edu.com.fretecalculo.Util.TipoMensagem;

public class SelecaoLocal extends AppCompatActivity {

    int tipoTela = 0;
    Spinner spEstado, spMunicipio;
    AutoCompleteTextView acCEP;
    Button btConfirmar;
    TextView tvItemSelecionado;
    private List<Estado> estados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao_local);
        tipoTela = (int) getIntent().getExtras().get("TIPO");
        carregaXML();
        if (tipoTela == 1) {
            tvItemSelecionado.setText("Destino");
        } else {
            tvItemSelecionado.setText("Origem");
        }


        carregaEventos();
    }

    private void carregaEventos() {

        carregaListas();


        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Estado estado = (Estado)spEstado.getItemAtPosition(position);
                ArrayAdapter<Municipio> adapterMunicipio = new ArrayAdapter<Municipio>(SelecaoLocal.this, //Contexto
                        R.layout.item_lista, //Layout
                        estado.getMunicipios()); // Lista de Objetos
                spMunicipio.setAdapter(adapterMunicipio);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Municipio municipio = (Municipio) spMunicipio.getItemAtPosition(position);
                ArrayAdapter<Integer> adapterMunicipio = new ArrayAdapter<Integer>(SelecaoLocal.this, //Contexto
                        R.layout.item_lista, //Layout
                        municipio.getCEP()); // Lista de Objetos
                acCEP.setAdapter(adapterMunicipio);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tipoTela == 1) {
                    MainActivity.municipioSelD = (Municipio)spMunicipio.getSelectedItem();
                    MainActivity.estadoSelD = (Estado) spEstado.getSelectedItem();
                    MainActivity.selecaoDestino.setText(MainActivity.estadoSelD.getNome().toString() + " - " + MainActivity.municipioSelD.getNome().toString() + " - "+acCEP.getText().toString());
                    finish();
                } else {
                    tvItemSelecionado.setText("Origem");
                    MainActivity.municipioSelO = (Municipio)spMunicipio.getSelectedItem();
                    MainActivity.estadoSelO = (Estado) spEstado.getSelectedItem();
                    MainActivity.selecaoOrigem.setText(MainActivity.estadoSelO.getNome().toString() + " - " + MainActivity.municipioSelO.getNome().toString() + " - "+acCEP.getText().toString());
                    finish();
                }
            }
        });
    }

    private void carregaListas() {
        if(MainActivity.estadoList != null) {
            estados = MainActivity.estadoList;


            ArrayAdapter<Estado> adapter = new ArrayAdapter<Estado>(SelecaoLocal.this, //Contexto
                    R.layout.item_lista, //Layout
                    estados); // Lista de Objetos
            spEstado.setAdapter(adapter);

            Estado estado = (Estado) spEstado.getSelectedItem();

            ArrayAdapter<Municipio> adapterMunicipio = new ArrayAdapter<Municipio>(SelecaoLocal.this, //Contexto
                    R.layout.item_lista, //Layout
                    estado.getMunicipios()); // Lista de Objetos
            spMunicipio.setAdapter(adapterMunicipio);
        } else {
            Mensagem.ExibirMensagem(this,"Não existem estados disponiveis",TipoMensagem.ERRO);
        }
    }

    private void carregaXML() {
        spEstado = findViewById(R.id.spEstado);
        spMunicipio = findViewById(R.id.spMunicipio);
        acCEP = findViewById(R.id.acCEP);
        btConfirmar = findViewById(R.id.btConfirmar);
        tvItemSelecionado = findViewById(R.id.tvItemSelecionado);
    }


}

