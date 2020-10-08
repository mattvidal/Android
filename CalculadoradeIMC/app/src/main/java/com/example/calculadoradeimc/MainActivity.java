package com.example.calculadoradeimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    EditText edtPeso, edtAltura, edtImc, edtSituacao;
    CheckBox chkIdade;
    RadioButton rbFeminino, rbMasculino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtPeso = (EditText) findViewById(R.id.editTextPeso);
        edtAltura = (EditText) findViewById(R.id.editTextAltura);
        edtImc = (EditText) findViewById(R.id.editTextImc);
        edtSituacao = (EditText) findViewById(R.id.editTextSituacao);
        chkIdade = (CheckBox) findViewById(R.id.checkBoxIdade);
        rbFeminino = (RadioButton) findViewById(R.id.radioButtonFeminino);
        rbMasculino = (RadioButton) findViewById(R.id.radioButtonMasculino);

    }

    public void calcularImc(View v)
    {
        double peso, altura, imc;
        int idade, sexo;
        String situacao;

        //Entrada
        peso = Double.parseDouble(edtPeso.getText().toString());

        altura = Double.parseDouble(edtAltura.getText().toString());

        if(chkIdade.isChecked())
        {
            idade = 1;
        }
        else
        {
            idade = 2;
        }

        if(rbFeminino.isChecked())
        {
            sexo = 1;
        }
        else
        {
            sexo = 2;
        }

        //Processamento
        imc = peso / Math.pow(altura, 2);

        if(idade == 1)
        {
            if(sexo == 1)
            {
                if(imc < 19.1)
                {
                    situacao = "Abaixo do peso.";
                }
                else if (imc < 25.8)
                {
                    situacao = "Peso normal.";
                }
                else if (imc < 27.3)
                {
                    situacao = "Pouco acima do peso.";
                }
                else if (imc < 32.3)
                {
                    situacao = "Acima do peso.";
                }
                else
                {
                    situacao = "Obesa.";
                }
            }
            else
            {
                if(imc < 20.7)
                {
                    situacao = "Abaixo do peso.";
                }
                else if (imc < 26.4)
                {
                    situacao = "Peso normal.";
                }
                else if (imc < 27.8)
                {
                    situacao = "Pouco acima do peso.";
                }
                else if (imc < 31.1)
                {
                    situacao = "Acima do peso.";
                }
                else
                {
                    situacao = "Obeso.";
                }
            }

        }
        else
        {
            situacao = "Nâo Verificar";
        }

        //Saída
        edtImc.setText(String.format("%.2f", imc));
        edtSituacao.setText(situacao);


    }
}

