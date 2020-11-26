package com.example.controledenotas;

import android.content.Context ;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DisciplinaSQLiteOpenHelper extends SQLiteOpenHelper {
    // definição dos atributos
    public static final String TABELA = " Disciplina ";
    public static final String COLUNA_ID = " id ";
    public static final String COLUNA_NOME = " nome ";
    public static final String COLUNA_A1 = " a1 ";
    public static final String COLUNA_A2 = " a2 ";
    public static final String COLUNA_A3 = " a3 ";
    // nomeia o banco de dados
    private static final String DATABASE_NAME = "disciplinas.db";
    // determina a versão do banco
    private static final int DATABASE_VERSION = 1;
    // prepara a criação da tabela se não existir
    private static final String CRIAR_BANCO = " create table "
            + TABELA + "("
            + COLUNA_ID + " integer primary key autoincrement , "
            + COLUNA_NOME + " text not null , "
            + COLUNA_A1 + " double not null , "
            + COLUNA_A2 + " double not null , "
            + COLUNA_A3 + " double not null ) ;";
    // construtor
    public DisciplinaSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // criação do banco
    @Override
    public void onCreate ( SQLiteDatabase database ) {
        database . execSQL ( CRIAR_BANCO );
    }
    // atualização do banco
    @Override
    public void onUpgrade ( SQLiteDatabase db , int oldVersion , int newVersion ) {
        db. execSQL (" DROP TABLE IF EXISTS " + TABELA );
        onCreate (db);
    }
}
