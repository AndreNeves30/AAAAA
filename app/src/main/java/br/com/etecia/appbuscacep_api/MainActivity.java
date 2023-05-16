package br.com.etecia.appbuscacep_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Button btnAlterar, btnExcluir, btnLimpar, btnBuscarCep;
    EditText txtCep, lblCEP, lblLogradouro, lblComplemento, lblBairro, lblCidade;

    Spinner lblEstado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCep = findViewById(R.id.txtCep);
        lblCEP = findViewById(R.id.lblCEP);
        lblLogradouro = findViewById(R.id.lblLogradouro);
        lblComplemento = findViewById(R.id.lblComplemento);
        lblBairro = findViewById(R.id.lblBairro);
        lblCidade = findViewById(R.id.lblCidade);
        btnAlterar = findViewById(R.id.btnAlterar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnLimpar = findViewById(R.id.btnLimpar);

        lblEstado = (Spinner) findViewById(R.id.lblEstado);
        String[] estadoar = getResources().getStringArray(R.array.estado_array);
        lblEstado.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_main, estadoar));




        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String endereco = txtCep.getText().toString().trim();

                try {
                    //preencher o cep no lblResposta do layout
                    CEP retorno = new HttpService(txtCep.getText().toString().trim()).execute().get();
                    lblCEP.setText(retorno.getCep().toString());
                    lblLogradouro.setText(retorno.getLogradouro().toString());
                    lblComplemento.setText(retorno.getComplemento().toString());
                    lblBairro.setText(retorno.getBairro().toString());
                    lblCidade.setText(retorno.getLocalidade().toString());


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblCEP.setText("");
                lblLogradouro.setText("");
                lblComplemento.setText("");
                lblBairro.setText("");
                lblCidade.setText("");
            }
        });
    }
}