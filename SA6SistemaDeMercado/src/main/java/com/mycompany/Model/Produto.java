package com.mycompany.Model;

public class Produto {

    private String codigoBarras;
    private String nome;
    private double preco;
    private int quantidade;

    // Construtor
    public Produto(String codigoBarras, String nome, double preco, int quantidade) {
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters e Setters

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Métodos adicionais para a SA6

    public void adicionarAoEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    public void vender(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
        } else {
            System.out.println("Estoque insuficiente para realizar a venda.");
        }
    }

    
}