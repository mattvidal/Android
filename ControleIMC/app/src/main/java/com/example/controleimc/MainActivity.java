package com.example.controleimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
     */

    ListView lista;
    Intent intent;
    public static final int ACTIVITY_REQUEST_PESSOA = 1;
    private Pessoa_DAO dao;

    private String[] pessoas;
    private long[] idPessoas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);
        dao = new Pessoa_DAO(this);
        dao.open();

        lista.setOnItemClickListener(this); // Clique no item
    }

    @Override
    protected void onResume() {
        dao.open();
        super.onResume();
        List<Pessoa> listaPessoas = dao.getAll();
        pessoas = new String[listaPessoas.size()];
        idPessoas = new long[listaPessoas.size()];
        int i = 0;
        Iterator<Pessoa> iterator = listaPessoas.iterator();
        while (iterator.hasNext()) {
            Pessoa aux = new Pessoa();
            aux = (Pessoa) iterator.next();
            pessoas[i] = aux.textoLista();
            idPessoas[i] = aux.getId();
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, pessoas);
        lista.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        dao.close();
        super.onPause();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long ident) {
        long id = idPessoas[position];
        Double media;
        intent = new Intent(getApplicationContext(), TratarPessoa.class);
        intent.putExtra("acao", 0);
        intent.putExtra("id", id);
        startActivity(intent);

        Pessoa disc = dao.buscar(id);

        // COLOCAR A SITUAÇÃO DO IMC NO TOAST ABAIXO
        //Toast.makeText(this, "A média é: " + String.valueOf(media) , Toast.LENGTH_SHORT).show();

    }

    public void incluirPessoa(View v) {
        intent = new Intent(getApplicationContext(), TratarPessoa.class);
        intent.putExtra("acao", -1);
        intent.putExtra("id", 0L);
        startActivity(intent);
    }
}