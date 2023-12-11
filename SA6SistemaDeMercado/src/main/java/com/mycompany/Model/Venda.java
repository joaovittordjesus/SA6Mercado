/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Model;

import java.util.Date; // Importando a classe Date para representar a data da venda

public class Venda {

    private int id;
    private Cliente cliente;
    private Produto produto;
    private Date dataVenda;
    private int quantidade;
    private double valorTotal;

    // Construtor
    public Venda(int id, Cliente cliente, Produto produto, Date dataVenda, int quantidade) {
        this.id = id;
        this.cliente = cliente;
        this.produto = produto;
        this.dataVenda = dataVenda;
        this.quantidade = quantidade;
        this.valorTotal = calcularValorTotal(); // Calculando o valor total automaticamente ao criar a venda
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.valorTotal = calcularValorTotal(); // Atualizando o valor total ao modificar a quantidade
    }

    public double getValorTotal() {
        return valorTotal;
    }

    // Método para calcular o valor total da venda
    private double calcularValorTotal() {
        return quantidade * produto.getPreco();
    }


}
