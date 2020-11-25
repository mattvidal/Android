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

    public Formulario formulario = new Formulario();

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


        formulario.setNome(editTextNome.getText().toString());
        formulario.setIdade(Integer.parseInt(editTextIdade.getText().toString()));
        formulario.setEndereco(editTextEndereco.getText().toString());
        formulario.setPeso(Double.parseDouble(editTextPeso.getText().toString()));
        formulario.setAltura(Double.parseDouble(editTextAltura.getText().toString()));
        formulario.setTipoSanguineo(editTextTipoSang.getText().toString());
        formulario.setContatoEmergencia(editTextContatoEmergencia.getText().toString());
        formulario.setTelefoneEmergencia(editTextTelefoneEmergencia.getText().toString());

        if(cbDiabetes.isChecked())
        {
            formulario.setDiabetes(true);
        }
        else
        {
            formulario.setDiabetes(false);
        }

        if(cbHipertensao.isChecked())
        {
            formulario.setHipertensao(true);
        }
        else
        {
            formulario.setHipertensao(false);
        }

        SharedPreferences.Editor prefUsuario = formulario.salvar(v, prefs);

        prefUsuario.apply();
        // mensagem de aviso ao usuário
        Toast.makeText(getApplicationContext(), "Suas preferências foram salvas!\nAguarde alguns instantes para sair.", Toast.LENGTH_LONG).show();
    }




}