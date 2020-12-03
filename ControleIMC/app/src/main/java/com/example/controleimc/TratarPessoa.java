package com.example.controleimc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TratarPessoa extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    RadioButton rb1, rb2;
    Button bt1, bt2;
    private int acao;
    private long id;

    /*
    rb1: feminino
    rb2: masculino
    Regra para sexo: true para masculino, false para feminino
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratar_pessoa);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        rb1 = (RadioButton) findViewById(R.id.radioButtonFeminino);
        rb2 = (RadioButton) findViewById(R.id.radioButtonMasculino);
        acao = getIntent().getExtras().getInt("acao");
        id = getIntent().getExtras().getLong("id");
        if (acao == -1) {
            // inclusão
            setTitle("Inserir Pessoa");
            bt1.setText("Incluir");
            bt2.setEnabled(false);
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");

        } else {
            // alteração ou exclusão
            setTitle("Alterar ou Excluir Pessoa");
            Pessoa aux = new Pessoa();
            Pessoa_DAO dao = new Pessoa_DAO(this);
            dao.open();
            aux = dao.buscar(id);
            ed1.setText(aux.getNome());
            ed2.setText(String.format("%.1f", aux.getPeso()));
            ed3.setText(String.format("%.1f", aux.getAltura()));
            ed4.setText(String.format("%d", aux.getIdade()));
            //ed5.setText(String.format("%b", aux.getSexo()));

            if(aux.getSexo() == 1) //feminino
            {
                rb2.setChecked(false);
                rb1.setChecked(true);
            }
            else //masculino
            {
                rb2.setChecked(true);
                rb1.setChecked(false);
            }
            dao.close();
        }
    }
    public void alterarInserir(View v) {
        String nome, situacao = " ";
        double peso;
        double altura;
        double imc = 0.0;
        int idade;
        int sexo;

        nome = ed1.getText().toString();
        peso = Double.parseDouble(ed2.getText().toString());
        altura = Double.parseDouble(ed3.getText().toString());
        idade = Integer.parseInt(ed4.getText().toString());
        //sexo = Boolean.parseBoolean(ed5.getText().toString());

        if(rb1.isChecked()) //feminino
        {
            sexo = 1;
        }
        else //masculino
        {
            sexo = 2;
        }

        Pessoa_DAO dao = new Pessoa_DAO(this);
        dao.open();
        if (acao == -1) { // inserção
            dao.inserir(nome, peso, altura, idade, sexo, imc, situacao);
        }
        else{ // alteração
            dao.alterar(id, nome, peso, altura, idade, sexo, imc, situacao);
        }

        dao.close();
        finish();
    }

    public void excluir(View v) {
        // exclusão
        if (acao == 0) {
            Pessoa_DAO dao = new Pessoa_DAO(this);
            dao.open();
            dao.apagar(id);
            dao.close();
        }
        finish();
    }
    public void voltar(View v) {
        finish();
    }
}
