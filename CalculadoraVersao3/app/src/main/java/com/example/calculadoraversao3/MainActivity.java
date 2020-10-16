package com.example.calculadoraversao3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText valor1, valor2, resultado;
    Spinner spinner;

    com.example.calculadoraversao2.Calculadora calc = new com.example.calculadoraversao2.Calculadora();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor1 = (EditText) findViewById(R.id.editTextValor1);
        valor2 = (EditText) findViewById(R.id.editTextValor2);
        resultado = (EditText) findViewById(R.id.editTextResultado);
        spinner = (Spinner) findViewById(R.id.spinner);

    }

    public void calcular(View v) {

        if (!valor1.getText().toString().matches("") && !valor2.getText().toString().matches("")) {

            calc.setValor1(Double.parseDouble(valor1.getText().toString()));
            calc.setValor2(Double.parseDouble(valor2.getText().toString()));

            if (spinner.getSelectedItem().toString().equals("Somar")) {
                resultado.setText(somar());
            } else {
                if (spinner.getSelectedItem().toString().equals("Subtrair")) {
                    resultado.setText(subtrair());
                } else {
                    if (spinner.getSelectedItem().toString().equals("Multiplicar")) {
                        resultado.setText(multiplicar());
                    } else {
                        if (spinner.getSelectedItem().toString().equals("Dividir"))
                            resultado.setText(dividir());
                    }
                }
            }
        }
    }

    public String somar() {

        return String.format("%.3f", calc.somar());
    }
    public String subtrair() {

        return String.format("%.3f", calc.subtrair());

    }

    public String multiplicar() {
        return String.format("%.3f", calc.multiplicar());

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String dividir() {
        if(calc.dividir() == null) {
            return "É impossível dividir por zero";
        }
        else{
            return String.format("%.3f", calc.dividir().getAsDouble());
        }

    }


}



