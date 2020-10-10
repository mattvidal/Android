package com.example.calculadoraversao1;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.OptionalDouble;

public class Calculadora
{
    double valor1, valor2;

    public Calculadora()
    {

    }

    public Calculadora(double v1, double v2)
    {
       setValor1(v1);
       setValor2(v2);
    }

    public void setValor1(double valor)
    {
        valor1 = valor;
    }

    public double getValor1()
    {
        return valor1;
    }

    public void setValor2(double valor)
    {
        valor2 = valor;
    }

    public double getValor2()
    {
        return valor2;
    }

// -------- OPERAÇÕES --------

    public double somar()
    {
        return valor1 + valor2;
    }

    public double subtrair()
    {
        return valor1 - valor2;
    }

    public double multiplicar()
    {
        return valor1 * valor2;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public OptionalDouble dividir()
    {
        if(valor2 == 0.0)
        {
            return null;
        }
        else
        {
            return OptionalDouble.of(valor1 / valor2);
        }

    }



}
