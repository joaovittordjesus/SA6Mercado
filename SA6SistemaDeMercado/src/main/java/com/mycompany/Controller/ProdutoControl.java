package com.mycompany.Controller;

import com.mycompany.Connection.ProdutoDAO;
import com.mycompany.Model.Produto;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProdutoControl {

    private final DefaultTableModel tableModel; // O modelo de tabela que será atualizado
    private final JTable table; // A tabela que exibe os dados

    // Construtor que recebe o modelo de tabela e a tabela como parâmetros
    public ProdutoControl(DefaultTableModel tableModel, JTable table) {
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela com os dados mais recentes do banco de dados
    public void atualizarTabela() {
        // Obtém a lista de todos os produtos do banco de dados usando o ProdutoDAO
        List<Produto> produtos = new ProdutoDAO().listarTodos();
        // Atualiza o modelo de tabela com os dados obtidos
        atualizarTableModel(produtos);
    }

    // Método privado para atualizar o modelo de tabela com uma lista de produtos
    private void atualizarTableModel(List<Produto> produtos) {
        // Limpa todas as linhas existentes na tabela
        tableModel.setRowCount(0);
        // Adiciona as novas linhas com os dados dos produtos à tabela
        for (Produto produto : produtos) {
            // Convertendo o preço para String formatada
            String precoFormatado = String.format("%.2f", produto.getPreco());
            // Convertendo a quantidade para String
            String quantidadeString = String.valueOf(produto.getQuantidade());

            tableModel.addRow(new Object[]{produto.getCodigoBarras(), produto.getNome(),
                    precoFormatado, quantidadeString});
        }
    }

    // Método para cadastrar um novo produto no banco de dados e atualizar a tabela
    public void cadastrar(String codigoBarras, String nome, String preco, int quantidade) {
        // Chama o método de cadastro do ProdutoDAO
        new ProdutoDAO().cadastrar(codigoBarras, nome, preco, quantidade);
        // Atualiza a tabela após o cadastro
        atualizarTabela();
    }

    // Método para editar um produto existente no banco de dados e atualizar a tabela
    public void editar(String codigoBarras, String nome, String preco, int quantidade) {
        // Chama o método de atualização do ProdutoDAO
        new ProdutoDAO().atualizar(codigoBarras, nome, preco, quantidade);
        // Atualiza a tabela após a edição
        atualizarTabela();
    }

    // Método para apagar um produto do banco de dados e atualizar a tabela
    public void apagar(String codigoBarras) {
        // Chama o método de exclusão do ProdutoDAO
        new ProdutoDAO().apagar(codigoBarras);
        // Atualiza a tabela após a exclusão
        atualizarTabela();
    }
}
