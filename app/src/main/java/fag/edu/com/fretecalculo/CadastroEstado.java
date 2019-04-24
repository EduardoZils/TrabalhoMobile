package fag.edu.com.fretecalculo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fag.edu.com.fretecalculo.Models.Estado;
import fag.edu.com.fretecalculo.Util.Mensagem;
import fag.edu.com.fretecalculo.Util.TipoMensagem;

public class CadastroEstado extends AppCompatActivity {

    EditText etSigla, etNome;
    Button btCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estado);

        etNome = findViewById(R.id.etNome);
        etSigla = findViewById(R.id.etSigla);
        btCriar = findViewById(R.id.btCriar);

        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNome.getText().length() > 0 && etSigla.getText().length() > 0){
                    Estado estado = new Estado();
                    estado.setCodigo(MainActivity.estadoList.size() + 1);
                    estado.setNome(etNome.getText().toString());
                    estado.setId(etSigla.getText().toString());
                    MainActivity.estadoList.add(estado);
                    Mensagem.ExibirMensagem(CadastroEstado.this, "Estado criado com sucesso", TipoMensagem.SUCESSO);
                } else{

                    Mensagem.ExibirMensagem(CadastroEstado.this, "Preencha todos os campos", TipoMensagem.ERRO);
                }
            }
        });


    }
}
