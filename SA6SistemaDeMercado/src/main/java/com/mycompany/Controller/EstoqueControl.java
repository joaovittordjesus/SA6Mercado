package com.mycompany.Controller;

import com.mycompany.Connection.ProdutoDAO;

public class EstoqueControl {

    public void adicionarNovoProduto(String codigoBarras, String nome, String preco, int quantidade) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.cadastrar(codigoBarras, nome, preco, quantidade);
    }

    public void atualizarEstoque(String codigoBarras, int quantidadeVendida) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        int quantidadeAtual = produtoDAO.obterQuantidade(codigoBarras);

        if (quantidadeAtual >= quantidadeVendida) {
            int novaQuantidade = quantidadeAtual - quantidadeVendida;
            produtoDAO.atualizarQuantidade(codigoBarras, novaQuantidade);
            System.out.println("Estoque atualizado após venda.");
        } else {
            System.out.println("Erro: Quantidade insuficiente em estoque para a venda.");
        }
    }
}
