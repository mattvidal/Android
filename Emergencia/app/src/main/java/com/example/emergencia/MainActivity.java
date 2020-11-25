package com.example.emergencia;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText edtPeso, edtAltura, edtImc, edtSitucao, edNome;
    CheckBox chkIdade;
    RadioButton rbFeminino, rbMasculino;
    // declaração do objeto para o armazenamento das preferências
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtPeso = (EditText) findViewById(R.id.editTextNome);
        edtAltura = (EditText) findViewById(R.id.editTextAltura);
        edtImc = (EditText) findViewById(R.id.editTextImc);
        edtSitucao = (EditText) findViewById(R.id.editTextSituacao);
        chkIdade = (CheckBox) findViewById(R.id.checkBoxIdade);
        rbFeminino = (RadioButton) findViewById(R.id.radioButtonFeminino);
        rbMasculino = (RadioButton) findViewById(R.id.radioButtonMasculino);
        edNome = (EditText) findViewById(R.id.editTextNome);
        // definição do arquivo de preferências com o nome do arquivo
        // e o modo de compartilhamento de dados privado
        prefs = getSharedPreferences("dadosUsuario", MODE_PRIVATE);
        // método para preencher a tela com os dados do arquivo de
        // preferências ou com valores padrão
        iniciarDadosUsuario();
    }

    private void iniciarDadosUsuario() {
        // verifica se o dado com o nome está disponível no arquivo
        // de preferências e preenche o componente, caso contrário
        // preenche o componente com um texto padrão
        if (prefs.contains("nome")) {
            edNome.setText(prefs.getString("nome", ""));
        } else {
            edNome.setText("Digite o seu nome!");
        }
        // verifica se o dado com o peso está disponível no arquivo
        // de preferências e preenche o componente, caso contrário
        // preenche o componente com valor 0.0 (zero.zero)
        if (prefs.contains("peso")) {
            edtPeso.setText(String.valueOf(prefs.getFloat("peso", 0.0f)));
        } else {
            edtPeso.setText("0.0");
        }
        // verifica se o dado com a altura está disponível no arquivo
        // de preferências e preenche o componente, caso contrário
        // preenche o componente com valor 0.0 (zero.zero)
        if (prefs.contains("altura")) {
            edtAltura.setText(String.valueOf(prefs.getFloat("altura", 0.0f)));
        } else {
            edtAltura.setText("0.0");
        }
        // verifica se o dado com o sexo está disponível no arquivo
        // de preferências e seleciona o componente radioButton adequado,
        // com caso contrário seleciona o componente padrão (feminino)
        if (prefs.contains("sexo")) {
            if (prefs.getBoolean("sexo", true)) {
                // caso verdadeiro (true), seleciona o radioButton do sexo feminino
                rbFeminino.setChecked(true);
            } else {
                // caso false (falso), seleciona o radioButton do sexo masculino
                rbMasculino.setChecked(true);
            }
        } else {
            rbFeminino.setChecked(true);
        }
        // verifica se o dado com a idade está disponível no arquivo
        // de preferências e seleciona se o valor foi true o componente checkbox,
        // com caso contrário o checkbox ficará desmarcado
        if (prefs.contains("idade")) {
            if (prefs.getBoolean("idade", true)) {
                // caso verdadeiro (true), seleciona o checkbox
                chkIdade.setChecked(true);
            } else {
                // caso false (falso), desmarca o checkbox
                chkIdade.setChecked(true);
            }
        } else {
            chkIdade.setChecked(false);
        }

    }
    public void salvar(View v){
        // abre o arquivo de preferências para edição
        SharedPreferences.Editor prefUsuario = prefs.edit();
        // determina os pares chave o valor de cada dado do arquivo de preferências
        // para texto (String)
        prefUsuario.putString("nome", edNome.getText().toString());
        // para real (float)
        prefUsuario.putFloat("peso", Float.parseFloat(edtPeso.getText().toString()));
        prefUsuario.putFloat("altura", Float.parseFloat(edtAltura.getText().toString()));
        // para booleano (boolen)
        if(rbFeminino.isChecked()){
            prefUsuario.putBoolean("sexo", true);
        } else{
            prefUsuario.putBoolean("sexo", false);
        }
        if(chkIdade.isChecked()){
            prefUsuario.putBoolean("idade", true);
        } else{
            prefUsuario.putBoolean("idade", false);
        }
        // confirma a alteração
        prefUsuario.apply();
        // mensagem de aviso ao usuário
        Toast.makeText(getApplicationContext(), "Suas preferências foram salvas!\nAguarde alguns instantes para sair.", Toast.LENGTH_LONG).show();
    }




}