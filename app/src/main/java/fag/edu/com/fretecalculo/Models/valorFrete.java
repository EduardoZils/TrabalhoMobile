package fag.edu.com.fretecalculo.Models;

import java.util.List;

public class valorFrete {
    int codigo;
    Estado EstadoOrigem;
    Estado EstadoDestino;
    double valor;

    public valorFrete(int codigo, Estado estadoOrigem, Estado estadoDestino, double valor) {
        this.codigo = codigo;
        EstadoOrigem = estadoOrigem;
        EstadoDestino = estadoDestino;
        this.valor = valor;
    }

    public valorFrete() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Estado getEstadoOrigem() {
        return EstadoOrigem;
    }

    public void setEstadoOrigem(Estado estadoOrigem) {
        EstadoOrigem = estadoOrigem;
    }

    public Estado getEstadoDestino() {
        return EstadoDestino;
    }

    public void setEstadoDestino(Estado estadoDestino) {
        EstadoDestino = estadoDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "valorFrete{" +
                "codigo=" + codigo +
                ", EstadoOrigem=" + EstadoOrigem +
                ", EstadoDestino=" + EstadoDestino +
                '}';
    }
}
