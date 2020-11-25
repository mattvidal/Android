package com.example.emergencia;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Formulario {

    private String nome;
    private String endereco;
    private String tipoSanguineo;
    private String contatoEmergencia;
    private String telefoneEmergencia;
    private Integer idade;
    private Double peso;
    private Double altura;
    private Boolean diabetes;
    private Boolean hipertensao;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getContatoEmergencia() {
        return contatoEmergencia;
    }

    public void setContatoEmergencia(String contatoEmergencia) {
        this.contatoEmergencia = contatoEmergencia;
    }

    public String getTelefoneEmergencia() {
        return telefoneEmergencia;
    }

    public void setTelefoneEmergencia(String telefoneEmergencia) {
        this.telefoneEmergencia = telefoneEmergencia;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public Boolean getHipertensao() {
        return hipertensao;
    }

    public void setHipertensao(Boolean hipertensao) {
        this.hipertensao = hipertensao;
    }

    public Formulario(){
        nome = "";
        endereco = "";
        tipoSanguineo = "";
        contatoEmergencia = "";
        telefoneEmergencia = "";
        idade = 0;
        peso = 0.0;
        altura = 0.0;
        diabetes = false;
        hipertensao = false;
    }

    public SharedPreferences.Editor salvar(View v, SharedPreferences prefs)
    {
        // abre o arquivo de preferências para edição
        SharedPreferences.Editor prefUsuario = prefs.edit();
        // determina os pares chave o valor de cada dado do arquivo de preferências
        // para texto (String)
        prefUsuario.putString("nome", this.getNome());
        prefUsuario.putString("endereco", this.getEndereco());
        prefUsuario.putString("tipoSang", this.getTipoSanguineo());
        prefUsuario.putString("contatoEmergencia", this.getContatoEmergencia());
        prefUsuario.putString("telefoneEmergencia", this.getTelefoneEmergencia());

        // para real (float)
        prefUsuario.putFloat("peso", Float.parseFloat(this.getPeso().toString()));
        prefUsuario.putFloat("altura", Float.parseFloat(this.getAltura().toString()));

        // para inteiro (int)
        prefUsuario.putInt("idade", this.getIdade());

        // para booleano (boolen)
        if(this.getDiabetes()){
            prefUsuario.putBoolean("diabetes", true);
        } else{
            prefUsuario.putBoolean("diabetes", false);
        }
        if(this.getHipertensao()){
            prefUsuario.putBoolean("hipertensao", true);
        } else{
            prefUsuario.putBoolean("hipertensao", false);
        }

        return prefUsuario;

    }

//Set com os atributos (FEITO)

//Criar o método Salvar, que irá salvar esses atributos no Shared Preferences utilizando o Get

//Criar o método iniciarDadosUsuario, que irá atualizar os dados da classe Formulario
}
