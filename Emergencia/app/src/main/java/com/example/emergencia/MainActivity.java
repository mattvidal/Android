package com.example.emergencia;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText editTextNome, editTextIdade, editTextEndereco, editTextPeso, editTextAltura, editTextTipoSang, editTextContatoEmergencia, editTextTelefoneEmergencia;
    CheckBox cbDiabetes, cbHipertensao;
    // declaração do objeto para o armazenamento das preferências
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextIdade = (EditText) findViewById(R.id.editTextIdade);
        editTextEndereco = (EditText) findViewById(R.id.editTextEndereco);
        editTextPeso = (EditText) findViewById(R.id.editTextPeso);
        editTextAltura = (EditText) findViewById(R.id.editTextAltura);
        editTextTipoSang = (EditText) findViewById(R.id.editTextTipoSang);
        editTextContatoEmergencia = (EditText) findViewById(R.id.editTextContatoEmergencia);
        editTextTelefoneEmergencia = (EditText) findViewById(R.id.editTextTelefoneEmergencia);
        cbDiabetes = (CheckBox) findViewById(R.id.cbDiabetes);
        cbHipertensao = (CheckBox) findViewById(R.id.cbHipertensao);

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
            editTextNome.setText(prefs.getString("nome", ""));
        } else {
            editTextNome.setText("");
        }

        if (prefs.contains("endereco")) {
            editTextEndereco.setText(prefs.getString("endereco", ""));
        } else {
            editTextEndereco.setText("");
        }

        if (prefs.contains("tipoSang")) {
            editTextTipoSang.setText(prefs.getString("tipoSang", ""));
        } else {
            editTextTipoSang.setText("");
        }

        if (prefs.contains("contatoEmergencia")) {
            editTextContatoEmergencia.setText(prefs.getString("contatoEmergencia", ""));
        } else {
            editTextContatoEmergencia.setText("");
        }

        if (prefs.contains("telefoneEmergencia")) {
            editTextTelefoneEmergencia.setText(prefs.getString("telefoneEmergencia", ""));
        } else {
            editTextTelefoneEmergencia.setText("");
        }


        // verifica se o dado com o peso está disponível no arquivo
        // de preferências e preenche o componente, caso contrário
        // preenche o componente com valor 0.0 (zero.zero)
        if (prefs.contains("peso")) {
            editTextPeso.setText(String.valueOf(prefs.getFloat("peso", 0.0f)));
        } else {
            editTextPeso.setText("");
        }

        // verifica se o dado com a altura está disponível no arquivo
        // de preferências e preenche o componente, caso contrário
        // preenche o componente com valor 0.0 (zero.zero)
        if (prefs.contains("altura")) {
            editTextAltura.setText(String.valueOf(prefs.getFloat("altura", 0.0f)));
        } else {
            editTextAltura.setText("");
        }

        // verifica se o dado com a idade está disponível no arquivo
        // de preferências e preenche o componente, caso contrário
        // preenche o componente com valor 0.0 (zero.zero)
        if (prefs.contains("idade")) {
            editTextIdade.setText(String.valueOf(prefs.getInt("idade", 0)));
        } else {
            editTextIdade.setText("");
        }

        // verifica se o dado com a idade está disponível no arquivo
        // de preferências e seleciona se o valor foi true o componente checkbox,
        // com caso contrário o checkbox ficará desmarcado
        if (prefs.contains("diabetes")) {
            if (prefs.getBoolean("diabetes", false)) {
                // caso verdadeiro (true), seleciona o checkbox
                cbDiabetes.setChecked(true);
            } else {
                // caso false (falso), desmarca o checkbox
                cbDiabetes.setChecked(false);
            }
        } else {
            cbDiabetes.setChecked(false);
        }

        // verifica se o dado com a idade está disponível no arquivo
        // de preferências e seleciona se o valor foi true o componente checkbox,
        // com caso contrário o checkbox ficará desmarcado
        if (prefs.contains("hipertensao")) {
            if (prefs.getBoolean("hipertensao", false)) {
                // caso verdadeiro (true), seleciona o checkbox
                cbHipertensao.setChecked(true);
            } else {
                // caso false (falso), desmarca o checkbox
                cbHipertensao.setChecked(false);
            }
        } else {
            cbHipertensao.setChecked(false);
        }

    }
    public void salvar(View v){
        // abre o arquivo de preferências para edição
        SharedPreferences.Editor prefUsuario = prefs.edit();
        // determina os pares chave o valor de cada dado do arquivo de preferências
        // para texto (String)
        prefUsuario.putString("nome", editTextNome.getText().toString());
        prefUsuario.putString("endereco", editTextEndereco.getText().toString());
        prefUsuario.putString("tipoSang", editTextTipoSang.getText().toString());
        prefUsuario.putString("contatoEmergencia", editTextContatoEmergencia.getText().toString());
        prefUsuario.putString("telefoneEmergencia", editTextTelefoneEmergencia.getText().toString());

        // para real (float)
        prefUsuario.putFloat("peso", Float.parseFloat(editTextPeso.getText().toString()));
        prefUsuario.putFloat("altura", Float.parseFloat(editTextAltura.getText().toString()));

        // para inteiro (int)
        prefUsuario.putInt("idade", Integer.parseInt(editTextIdade.getText().toString()));

        // para booleano (boolen)
        if(cbDiabetes.isChecked()){
            prefUsuario.putBoolean("diabetes", true);
        } else{
            prefUsuario.putBoolean("diabetes", false);
        }
        if(cbHipertensao.isChecked()){
            prefUsuario.putBoolean("hipertensao", true);
        } else{
            prefUsuario.putBoolean("hipertensao", false);
        }
        // confirma a alteração
        prefUsuario.apply();
        // mensagem de aviso ao usuário
        Toast.makeText(getApplicationContext(), "Suas preferências foram salvas!\nAguarde alguns instantes para sair.", Toast.LENGTH_LONG).show();
    }




}