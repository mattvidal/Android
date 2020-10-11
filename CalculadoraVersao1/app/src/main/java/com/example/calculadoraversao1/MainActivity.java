package com.example.calculadoraversao1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText valor1, valor2, resultado;
    Calculadora calc = new Calculadora();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor1 = (EditText) findViewById(R.id.editTextValor1);

        valor2 = (EditText) findViewById(R.id.editTextValor2);

        resultado = (EditText) findViewById(R.id.editTextResultado);
    }

    public void somar(View v)
    {
        if(!valor1.getText().toString().matches("") && !valor2.getText().toString().matches(""))
        {
            calc.setValor1(Double.parseDouble(valor1.getText().toString()));

            calc.setValor2(Double.parseDouble(valor2.getText().toString()));

            resultado.setText(String.format("%.3f", calc.somar()));
        }

    }

    public void subtrair(View v)
    {
        if(!valor1.getText().toString().matches("") && !valor2.getText().toString().matches(""))
        {
            calc.setValor1(Double.parseDouble(valor1.getText().toString()));

            calc.setValor2(Double.parseDouble(valor2.getText().toString()));

            resultado.setText(String.format("%.3f", calc.subtrair()));
        }

    }

    public void multiplicar(View v)
    {
        if(!valor1.getText().toString().matches("") && !valor2.getText().toString().matches(""))
        {
            calc.setValor1(Double.parseDouble(valor1.getText().toString()));

            calc.setValor2(Double.parseDouble(valor2.getText().toString()));

            resultado.setText(String.format("%.3f", calc.multiplicar()));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void dividir(View v)
    {
        if(!valor1.getText().toString().matches("") && !valor2.getText().toString().matches(""))
        {
            calc.setValor1(Double.parseDouble(valor1.getText().toString()));

            calc.setValor2(Double.parseDouble(valor2.getText().toString()));

            if(calc.dividir() == null)
            {
                resultado.setText("É impossível dividir por zero");
            }
            else
            {
                resultado.setText(String.format("%.3f", calc.dividir().getAsDouble()));
            }
        }


    }
}