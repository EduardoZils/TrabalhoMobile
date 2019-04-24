package fag.edu.com.fretecalculo.Models;

import java.util.List;

public class Municipio {
    private int codigo;
    private String nome;
    private List<Integer> CEP;

    public Municipio(int codigo, String nome, List<Integer> CEP) {
        this.codigo = codigo;
        this.nome = nome;
        this.CEP = CEP;
    }

    public Municipio(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Municipio() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getCEP() {
        return CEP;
    }

    public void setCEP(List<Integer> CEP) {
        this.CEP = CEP;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome;
    }
}

