/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controller;

import com.mycompany.Connection.ProdutoDAO;
import com.mycompany.Model.Produto;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProdutoControl {

    private final DefaultTableModel tableModel;
    private final JTable table;

    public ProdutoControl(DefaultTableModel tableModel, JTable table) {
        this.tableModel = tableModel;
        this.table = table;
    }

    public void atualizarTabela() {
        List<Produto> produtos = new ProdutoDAO().listarTodos();
        atualizarTableModel(produtos);
    }

    private void atualizarTableModel(List<Produto> produtos) {
        tableModel.setRowCount(0);
        for (Produto produto : produtos) {
            tableModel.addRow(new Object[]{produto.getCodigoBarras(), produto.getNome(),
                    produto.getPreco(), produto.getQuantidade()});
        }
    }

    public void cadastrar(String codigoBarras, String nome, String preco, int quantidade) {
        new ProdutoDAO().cadastrar(codigoBarras, nome, preco, quantidade);
        atualizarTabela();
    }

    public void editar(String codigoBarras, String nome, String preco, int quantidade) {
        new ProdutoDAO().atualizar(codigoBarras, nome, preco, quantidade);
        atualizarTabela();
    }

    public void apagar(String codigoBarras) {
        new ProdutoDAO().apagar(codigoBarras);
        atualizarTabela();
    }
}
