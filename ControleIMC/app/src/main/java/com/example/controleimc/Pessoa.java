package com.example.controleimc;

public class Pessoa {
    private long id;
    private String nome;
    private Double peso;
    private Double altura;
    private Double imc;
    private int idade;
    private int sexo;
    private String situacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Pessoa()
    {
        nome = "";
        peso = 0.0;
        altura = 0.0;
        idade = 0;
        situacao = " ";
    }

    // método de formatação dos dados para exibição na lista
    public String textoLista() {
        String item;
        item = getNome();
        item += "\t Peso: " + getPeso();
        item += "\t Altura: " + getAltura();
        item += "\t Idade: " + getIdade();
        item += "\t Sexo: " + ((getSexo() == 1) ? "Feminino" : "Masculino");
        item += "\t IMC: " + String.format("%.2f", getImc());
        item += "\t Situação: " + getSituacao();
        return item;
    }
}
