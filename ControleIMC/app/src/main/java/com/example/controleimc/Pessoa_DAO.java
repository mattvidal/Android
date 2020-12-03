package com.example.controleimc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Pessoa_DAO {

    // banco
    private SQLiteDatabase database;
    // colunas da tabela
    private String[] columns = {PessoaSQLiteOpenHelper.COLUNA_ID,
            PessoaSQLiteOpenHelper.COLUNA_NOME,
            PessoaSQLiteOpenHelper.COLUNA_PESO,
            PessoaSQLiteOpenHelper.COLUNA_ALTURA,
            PessoaSQLiteOpenHelper.COLUNA_IDADE,
            PessoaSQLiteOpenHelper.COLUNA_SEXO,
            PessoaSQLiteOpenHelper.COLUNA_IMC,
            PessoaSQLiteOpenHelper.COLUNA_SITUACAO};
    private PessoaSQLiteOpenHelper sqliteOpenHelper;

    // construtor
    public Pessoa_DAO(Context context) {
        sqliteOpenHelper = new PessoaSQLiteOpenHelper(context);
    }

    // abrir conexão
    public void open() throws SQLException {
        database = sqliteOpenHelper.getWritableDatabase();
    }

    // fechar conexão
    public void close() {
        sqliteOpenHelper.close();
    }

    // inclusão
    public void inserir(String nome, double peso, double altura, int idade, int sexo, double imc, String situacao) {

        double aux = 0.0;

        ContentValues values = new ContentValues();
        values.put(PessoaSQLiteOpenHelper.COLUNA_NOME, nome);
        values.put(PessoaSQLiteOpenHelper.COLUNA_PESO, String.valueOf(peso));
        values.put(PessoaSQLiteOpenHelper.COLUNA_ALTURA, String.valueOf(altura));
        values.put(PessoaSQLiteOpenHelper.COLUNA_IDADE, String.valueOf(idade));
        values.put(PessoaSQLiteOpenHelper.COLUNA_SEXO, Integer.valueOf(sexo));
        //values.put(PessoaSQLiteOpenHelper.COLUNA_IMC, imc);
        //values.put(PessoaSQLiteOpenHelper.COLUNA_SITUACAO, situacao);

        //Processamento
        imc = peso / Math.pow(altura, 2);

        if(idade >= 15)
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

        values.put(PessoaSQLiteOpenHelper.COLUNA_IMC, imc);
        values.put(PessoaSQLiteOpenHelper.COLUNA_SITUACAO, situacao);

        long insertId = database.insert(PessoaSQLiteOpenHelper.TABELA, null,
                values);
    }

    // alteração
    public void alterar(long id, String nome, double peso, double altura, int idade, int sexo, double imc, String situacao) {
        // prepara os dados para a atualização
        ContentValues values = new ContentValues();
        values.put(PessoaSQLiteOpenHelper.COLUNA_NOME, nome);
        values.put(PessoaSQLiteOpenHelper.COLUNA_PESO, String.valueOf(peso));
        values.put(PessoaSQLiteOpenHelper.COLUNA_ALTURA, String.valueOf(altura));
        values.put(PessoaSQLiteOpenHelper.COLUNA_IDADE, String.valueOf(idade));
        values.put(PessoaSQLiteOpenHelper.COLUNA_SEXO, String.valueOf(sexo));
        //values.put(PessoaSQLiteOpenHelper.COLUNA_IMC, imc);
        //values.put(PessoaSQLiteOpenHelper.COLUNA_SITUACAO, situacao);

        //Processamento
        imc = peso / Math.pow(altura, 2);

        if(idade >= 15)
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

        values.put(PessoaSQLiteOpenHelper.COLUNA_IMC, imc);
        values.put(PessoaSQLiteOpenHelper.COLUNA_SITUACAO, situacao);

        database.update(PessoaSQLiteOpenHelper.TABELA, values, PessoaSQLiteOpenHelper.COLUNA_ID + "=" + id, null);
    }

    // exclusão
    public void apagar(long id) {

        database.delete(PessoaSQLiteOpenHelper.TABELA, PessoaSQLiteOpenHelper.COLUNA_ID
                + " = " + id, null);
    }

    // método de busca
    public Pessoa buscar(long id) {
        Cursor cursor = database.query(PessoaSQLiteOpenHelper.TABELA,
                columns, PessoaSQLiteOpenHelper.COLUNA_ID + " = " + id, null,
                null, null, null);
        cursor.moveToFirst();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(cursor.getLong(0));
        pessoa.setNome(cursor.getString(1));
        pessoa.setPeso(cursor.getDouble(2));
        pessoa.setAltura(cursor.getDouble(3));
        pessoa.setIdade(Integer.parseInt(cursor.getString(4)));
        pessoa.setSexo(Integer.parseInt(cursor.getString(5)));
        pessoa.setImc(cursor.getDouble(6));
        pessoa.setSituacao(cursor.getString(7));
        cursor.close();

        return pessoa;
    }

    // criação da lista
    public List<Pessoa> getAll() {

        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        Cursor cursor = database.query(PessoaSQLiteOpenHelper.TABELA,
                columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(cursor.getLong(0));
            pessoa.setNome(cursor.getString(1));
            pessoa.setPeso(cursor.getDouble(2));
            pessoa.setAltura(cursor.getDouble(3));
            pessoa.setIdade(Integer.parseInt(cursor.getString(4)));
            pessoa.setSexo(Integer.parseInt(cursor.getString(5)));
            pessoa.setImc(cursor.getDouble(6));
            pessoa.setSituacao(cursor.getString(7));
            pessoas.add(pessoa);
            cursor.moveToNext();
        }
        cursor.close();
        return pessoas;
    }

}
