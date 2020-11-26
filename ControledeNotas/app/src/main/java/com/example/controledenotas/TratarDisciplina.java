package com.example.controledenotas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TratarDisciplina extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    Button bt1, bt2;
    private int acao;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratar_disciplina);
        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        acao = getIntent().getExtras().getInt("acao");
        id = getIntent().getExtras().getLong("id");
        if (acao == -1) {
            // inclusão
            setTitle("Inserir Disciplina");
            bt1.setText("Incluir");
            bt2.setEnabled(false);
            ed1.setText("Nome Disciplina");
            ed2.setText(String.format("%.1f", 0.0));
            ed3.setText(String.format("%.1f", 0.0));
            ed4.setText(String.format("%.1f", 0.0));
        } else {
            // alteração ou exclusão
            setTitle("Alterar ou Excluir Disciplina");
            Disciplina aux = new Disciplina();
            Disciplina_DAO dao = new Disciplina_DAO(this);
            dao.open();
            aux = dao.buscar(id);
            ed1.setText(aux.getNome());
            ed2.setText(String.format("%.1f", aux.getA1()));
            ed3.setText(String.format("%.1f", aux.getA2()));
            ed4.setText(String.format("%.1f", aux.getA3()));
            dao.close();
        }
    }
    public void alterarInserir(View v) {
        String nome;
        double a1, a2, a3, media;
        nome = ed1.getText().toString();
        a1 = Double.parseDouble(ed2.getText().toString());
        a2 = Double.parseDouble(ed3.getText().toString());
        a3 = Double.parseDouble(ed4.getText().toString());
        Disciplina_DAO dao = new Disciplina_DAO(this);
        dao.open();
        if (acao == -1) { // inserção
            dao.inserir(nome, a1, a2, a3);
        }
        else{ // alteração
            dao.alterar(id, nome, a1, a2, a3);
        }

        if(a3 > a1 || a3 > a2)
        {
            if(a1 < a2)
            {
                media = (a3 + a2) / 2.0;
            }
            else
            {
                media = (a3 + a1) / 2.0;
            }
        }
        else
        {
            media = (a1 + a2) / 2.0;
        }

        Toast.makeText(this, "A média é: " + String.valueOf(media) , Toast.LENGTH_SHORT).show();

        dao.close();
        finish();
    }

    public void excluir(View v) {
        // exclusão
        if (acao == 0) {
            Disciplina_DAO dao = new Disciplina_DAO(this);
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
