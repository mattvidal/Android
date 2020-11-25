package com.example.controledenotas;

public class Notas {
    private String nomeDisciplina;
    private Double av1;
    private Double av2;
    private Double av3;
    private Double media;

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public Double getAv1() {
        return av1;
    }

    public void setAv1(Double av1) {
        this.av1 = av1;
    }

    public Double getAv2() {
        return av2;
    }

    public void setAv2(Double av2) {
        this.av2 = av2;
    }

    public Double getAv3() {
        return av3;
    }

    public void setAv3(Double av3) {
        this.av3 = av3;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Notas(){
        nomeDisciplina= "Nome da disciplina...";
        av1= 0.0;
        av2= 0.0;
        av3= 0.0;
    }
    // método de formatação dos dados para exibição na lista
    public String textoLista() {
        String item;
        item = getNomeDisciplina();
        item += "\n";
        item += getAv1();
        item += "    ";
        item += getAv2();
        item += "    ";
        item += getAv3();
        item += "    ";
        return item;
    }
}

