package com.example.calculadoraversao2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.VibrationAttributes;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    EditText valor1, valor2, resultado;
    Calculadora calc = new Calculadora();
    RadioButton rbSomar, rbSubtrair, rbMultiplicar, rbDividir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor1 = (EditText) findViewById(R.id.editTextValor1);

        valor2 = (EditText) findViewById(R.id.editTextValor2);

        resultado = (EditText) findViewById(R.id.editTextResultado);

        rbSomar = (RadioButton) findViewById(R.id.radioButtonSomar);

        rbSubtrair = (RadioButton) findViewById(R.id.radioButtonSubtrair);

        rbMultiplicar = (RadioButton) findViewById(R.id.radioButtonMultiplicar);

        rbDividir = (RadioButton) findViewById(R.id.radioButtonDividir);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void calcular(View v)
    {
        if(!valor1.getText().toString().matches("") && !valor2.getText().toString().matches(""))
    {
        calc.setValor1(Double.parseDouble(valor1.getText().toString()));

        calc.setValor2(Double.parseDouble(valor2.getText().toString()));

        if(rbSomar.isChecked())
        {
            resultado.setText(somar());
        }
        else if(rbSubtrair.isChecked())
        {
            resultado.setText(subtrair());
        }
        else if(rbMultiplicar.isChecked())
        {
            resultado.setText(multiplicar());
        }
        else
        {
            resultado.setText(dividir());
        }
    }

}

    public String somar()
    {
        return String.format("%.3f", calc.somar());
    }

    public String subtrair()
    {
        return String.format("%.3f", calc.subtrair());

    }

    public String multiplicar()
    {
        return String.format("%.3f", calc.multiplicar());

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String dividir()
    {
        if(calc.dividir() == null)
        {
            return "É impossível dividir por zero";
        }
        else
        {
            return String.format("%.3f", calc.dividir().getAsDouble());
        }

    }
}