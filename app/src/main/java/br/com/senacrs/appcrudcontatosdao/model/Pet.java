package br.com.senacrs.appcrudcontatosdao.model;

import java.io.Serializable;

public class Pet implements Serializable{
    private int id;
    private int imagemR;
    private String nomePet;
    private String nomeDono;
    private String telefone;
    private String cep;
    private String endereco;

    public Pet(int imagemR, String nomePet, String nomeDono, String telefone, String cep, String endereco) {
        this.imagemR = imagemR;
        this.nomePet = nomePet;
        this.nomeDono = nomeDono;
        this.telefone = telefone;
        this.cep = cep;
        this.endereco = endereco;
    }

    public Pet(int id, int imagemR, String nomePet, String nomeDono, String telefone, String cep, String endereco) {
        this.id = id;
        this.imagemR = imagemR;
        this.nomePet = nomePet;
        this.nomeDono = nomeDono;
        this.telefone = telefone;
        this.cep = cep;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public int getImagemR() {
        return imagemR;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
