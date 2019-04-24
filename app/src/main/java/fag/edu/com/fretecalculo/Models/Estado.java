package fag.edu.com.fretecalculo.Models;

import java.util.List;

public class Estado {
    private int codigo;
    private String id;
    private String nome;
    private List<Municipio> municipios;

    public Estado() {
    }

    public Estado(int codigo, String id, String nome, List<Municipio> municipios) {
        this.codigo = codigo;
        this.id = id;
        this.nome = nome;
        this.municipios = municipios;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
