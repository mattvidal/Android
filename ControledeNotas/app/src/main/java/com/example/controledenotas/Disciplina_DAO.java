package com.example.controledenotas;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class Disciplina_DAO {
    // banco
    private SQLiteDatabase database;
    // colunas da tabela
    private String[] columns = {DisciplinaSQLiteOpenHelper.COLUNA_ID,
            DisciplinaSQLiteOpenHelper.COLUNA_NOME,
            DisciplinaSQLiteOpenHelper.COLUNA_A1,
            DisciplinaSQLiteOpenHelper.COLUNA_A2,
            DisciplinaSQLiteOpenHelper.COLUNA_A3};
    private DisciplinaSQLiteOpenHelper sqliteOpenHelper;

    // construtor
    public Disciplina_DAO(Context context) {
        sqliteOpenHelper = new DisciplinaSQLiteOpenHelper(context);
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
    public void inserir(String nome, double a1, double a2, double a3) {

        ContentValues values = new ContentValues();
        values.put(DisciplinaSQLiteOpenHelper.COLUNA_NOME, nome);
        values.put(DisciplinaSQLiteOpenHelper.COLUNA_A1, String.valueOf(a1));
        values.put(DisciplinaSQLiteOpenHelper.COLUNA_A2, String.valueOf(a2));
        values.put(DisciplinaSQLiteOpenHelper.COLUNA_A3, String.valueOf(a3));

        long insertId = database.insert(DisciplinaSQLiteOpenHelper.TABELA, null,
                values);
    }

    // alteração
    public void alterar(long id, String nome, double a1, double a2, double a3) {
        // prepara os dados para a atualização
        ContentValues values = new ContentValues();
        values.put(DisciplinaSQLiteOpenHelper.COLUNA_NOME, nome);
        values.put(DisciplinaSQLiteOpenHelper.COLUNA_A1, String.valueOf(a1));
        values.put(DisciplinaSQLiteOpenHelper.COLUNA_A2, String.valueOf(a2));
        values.put(DisciplinaSQLiteOpenHelper.COLUNA_A3, String.valueOf(a3));

        database.update(DisciplinaSQLiteOpenHelper.TABELA, values, DisciplinaSQLiteOpenHelper.COLUNA_ID + "=" + id, null);
    }

    // exclusão
    public void apagar(long id) {

        database.delete(DisciplinaSQLiteOpenHelper.TABELA, DisciplinaSQLiteOpenHelper.COLUNA_ID
                + " = " + id, null);
    }

    // método de busca
    public Disciplina buscar(long id) {
        Cursor cursor = database.query(DisciplinaSQLiteOpenHelper.TABELA,
                columns, DisciplinaSQLiteOpenHelper.COLUNA_ID + " = " + id, null,
                null, null, null);
        cursor.moveToFirst();

        Disciplina disciplina = new Disciplina();
        disciplina.setId(cursor.getLong(0));
        disciplina.setNome(cursor.getString(1));
        disciplina.setA1(cursor.getDouble(2));
        disciplina.setA2(cursor.getDouble(3));
        disciplina.setA3(cursor.getDouble(4));
        cursor.close();

        return disciplina;
    }

    // criação da lista
    public List<Disciplina> getAll() {

        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        Cursor cursor = database.query(DisciplinaSQLiteOpenHelper.TABELA,
                columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Disciplina disciplina = new Disciplina();
            disciplina.setId(cursor.getLong(0));
            disciplina.setNome(cursor.getString(1));
            disciplina.setA1(cursor.getDouble(2));
            disciplina.setA2(cursor.getDouble(3));
            disciplina.setA3(cursor.getDouble(4));
            disciplinas.add(disciplina);
            cursor.moveToNext();
        }
        cursor.close();
        return disciplinas;
    }

}
