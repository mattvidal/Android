package com.example.controledenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {
    EditText edNome, edAV1, edAV2, edAV3;
    ListView lista1;
    // Define o tamanho da lista
    public static final int TOTAL = 20;
    // Vetor para armazenar os itens que serão inseridos na lista
    public String[] notas = new String[TOTAL];
    // Vetor de objetos Agenda
    public Notas vetNotas[] = new Notas[TOTAL];
    // Paramanter o índice do elemento que setá sendo alterado
    public int indiceItem = 0;
    // ArrayAdapter para relacionar os elementos do vetor com a lista
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edNome = (EditText) findViewById(R.id.editTextNome);
        edAV1 = (EditText) findViewById(R.id.editTextAV1);
        edAV2 = (EditText) findViewById(R.id.editTextAV2);
        edAV3 = (EditText) findViewById(R.id.editTextAV3);
        lista1 = (ListView) findViewById(R.id.listViewNotas);
        // Inicializar o vetor de objetos
        iniciarVetor();
        atualizarLista();
        // Define o método de controle para o clique no item da lista
        lista1.setOnItemClickListener(this);

    }
    private void iniciarVetor(){
        for(int i =0; i<vetNotas.length; i++){
            // Instanciar cada elemento do vetor com um objeto instaciado;
            vetNotas[i] = new Notas();
        }
    }
    private void atualizarLista(){
        for(int i=0; i<notas.length; i++){
            notas[i] = vetNotas[i].textoLista();
        }
        // Preparar a nosso ArrayAdapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notas);
        // preencher a lista
        lista1.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indiceItem = position;
        Double media = 0.0;
        //Toast.makeText(getApplicationContext(),"Você selecionou o item de índice: " + String.valueOf(position), Toast.LENGTH_LONG).show();
        // Carregar os editTexts com os valores do vetor de objetos Notas
        edNome.setText(vetNotas[indiceItem].getNomeDisciplina());
        edAV1.setText(vetNotas[indiceItem].getAv1().toString());
        edAV2.setText(vetNotas[indiceItem].getAv2().toString());
        edAV3.setText(vetNotas[indiceItem].getAv3().toString());


        if(vetNotas[indiceItem].getAv3() > vetNotas[indiceItem].getAv1() || vetNotas[indiceItem].getAv3() > vetNotas[indiceItem].getAv2())
        {
            if(vetNotas[indiceItem].getAv1() < vetNotas[indiceItem].getAv2())
            {
                vetNotas[indiceItem].setMedia((Double) ((Double.parseDouble(edAV3.getText().toString()) + Double.parseDouble(edAV2.getText().toString())) / 2));
            }
            else
            {
                vetNotas[indiceItem].setMedia((Double) ((Double.parseDouble(edAV3.getText().toString()) + Double.parseDouble(edAV1.getText().toString())) / 2));
            }
        }
        else
        {
            vetNotas[indiceItem].setMedia((Double) ((Double.parseDouble(edAV1.getText().toString()) + Double.parseDouble(edAV2.getText().toString())) / 2));
        }

        //Se av3 > av1 OU av3 > av2
            //Se av1 < av2
                // media = av3 + av2 / 2
            //else
                // media = av3 + av1 / 2
        //else
            // media = av1 + av2 / 2

        Toast.makeText(MainActivity.this, "A média é: " + vetNotas[indiceItem].getMedia().toString() , Toast.LENGTH_SHORT).show();

    }
    public void alterar(View v){
        //atualizar o elemento do vetor de objetos Agenda com os novos valores

        if(Double.parseDouble(edAV1.getText().toString()) >= 0.0 && Double.parseDouble(edAV1.getText().toString()) <= 10.0 &&
                Double.parseDouble(edAV2.getText().toString()) >= 0.0 && Double.parseDouble(edAV2.getText().toString()) <= 10.0 &&
                Double.parseDouble(edAV3.getText().toString()) >= 0.0 && Double.parseDouble(edAV3.getText().toString()) <= 10.0)
        {
            vetNotas[indiceItem].setNomeDisciplina(edNome.getText().toString());
            vetNotas[indiceItem].setAv1(Double.parseDouble(edAV1.getText().toString()));
            vetNotas[indiceItem].setAv2(Double.parseDouble(edAV2.getText().toString()));
            vetNotas[indiceItem].setAv3(Double.parseDouble(edAV3.getText().toString()));
            // Atualizar a lista
            atualizarLista();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Nota(s) inválida(s)" , Toast.LENGTH_SHORT).show();
        }

    }
}