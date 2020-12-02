package com.example.controleimc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PessoaSQLiteOpenHelper extends SQLiteOpenHelper {

    // definição dos atributos
    public static final String TABELA = " Pessoa ";
    public static final String COLUNA_ID = " id ";
    public static final String COLUNA_NOME = " nome ";
    public static final String COLUNA_PESO = " peso ";
    public static final String COLUNA_ALTURA = " altura ";
    public static final String COLUNA_IDADE = " idade ";
    public static final String COLUNA_SEXO = " sexo ";

    // nomeia o banco de dados
    private static final String DATABASE_NAME = "pessoas.db";
    // determina a versão do banco
    private static final int DATABASE_VERSION = 1;
    // prepara a criação da tabela se não existir
    private static final String CRIAR_BANCO = " create table "
            + TABELA + "("
            + COLUNA_ID + " integer primary key autoincrement , "
            + COLUNA_NOME + " text not null , "
            + COLUNA_PESO + " double not null , "
            + COLUNA_ALTURA + " double not null , "
            + COLUNA_IDADE + " integer not null , "
            + COLUNA_SEXO + " integer not null ) ;";
    // construtor
    public PessoaSQLiteOpenHelper(Context context) {
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
