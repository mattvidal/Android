package com.example.controleimc;

public class Pessoa {
    private long id;
    private String nome;
    private Double peso;
    private Double altura;
    private int idade;
    private Boolean sexo;

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

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public Pessoa()
    {
        nome = "";
        peso = 0.0;
        altura = 0.0;
        idade = 0;
    }

    // método de formatação dos dados para exibição na lista
    public String textoLista() {
        String item;
        item = getNome();
        item += "\nNome: " + String.format(" %3.1f", getNome());
        item += "\t Peso: " + String.format(" %3.1f", getPeso());
        item += "\t Altura: " + String.format(" %3.1f", getAltura());
        item += "\t Idade: " + String.format(" %3.1f", getIdade());
        item += "\t Sexo: " + String.format(" %3.1f", getSexo());
        return item;
    }
}
