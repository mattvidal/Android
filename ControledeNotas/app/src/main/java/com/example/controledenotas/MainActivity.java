package com.example.controledenotas;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    Intent intent;
    public static final int ACTIVITY_REQUEST_DISCIPLINA = 1;
    private Disciplina_DAO dao;

    private String[] disciplinas;
    private long[] idDisiciplinas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);
        dao = new Disciplina_DAO(this);
        dao.open();

        lista.setOnItemClickListener(this); // Clique no item
    }

    @Override
    protected void onResume() {
        dao.open();
        super.onResume();
        List<Disciplina> listaDisciplinas = dao.getAll();
        disciplinas = new String[listaDisciplinas.size()];
        idDisiciplinas = new long[listaDisciplinas.size()];
        int i = 0;
        Iterator<Disciplina> iterator = listaDisciplinas.iterator();
        while (iterator.hasNext()) {
            Disciplina aux = new Disciplina();
            aux = (Disciplina) iterator.next();
            disciplinas[i] = aux.textoLista();
            idDisiciplinas[i] = aux.getId();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, disciplinas);
        lista.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        dao.close();
        super.onPause();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long ident) {
        long id = idDisiciplinas[position];
        Double media;
        intent = new Intent(getApplicationContext(), TratarDisciplina.class);
        intent.putExtra("acao", 0);
        intent.putExtra("id", id);
        startActivity(intent);

        Disciplina disc = dao.buscar(id);

        if(disc.getA3() > disc.getA1() || disc.getA3() > disc.getA2())
        {
            if(disc.getA1() < disc.getA2())
            {
                media = (disc.getA3() + disc.getA2()) / 2.0;
            }
            else
            {
                media = (disc.getA3() + disc.getA1()) / 2.0;
            }
        }
        else
        {
            media = (disc.getA1() + disc.getA2()) / 2.0;
        }

        Toast.makeText(this, "A média é: " + String.valueOf(media) , Toast.LENGTH_SHORT).show();

    }

    public void incluirDisciplina(View v) {
        intent = new Intent(getApplicationContext(), TratarDisciplina.class);
        intent.putExtra("acao", -1);
        intent.putExtra("id", 0L);
        startActivity(intent);
    }

}
