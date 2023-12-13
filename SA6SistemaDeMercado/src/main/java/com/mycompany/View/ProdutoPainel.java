/*
 * Esta classe representa um painel de cadastro de produtos em uma aplicação Java Swing.
 * Ela possui campos para inserção de informações do produto, botões de cadastro e exclusão,
 * além de uma tabela que exibe os produtos cadastrados.
 */
package com.mycompany.View;

import com.mycompany.Connection.ProdutoDAO;
import com.mycompany.Controller.ProdutoControl;
import com.mycompany.Model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProdutoPainel extends JPanel {

    // Campos de texto para inserção de dados do produto
    private JTextField codigoBarrasField, nomeField, precoField, quantidadeField;

    // Botões de ação
    private JButton cadastrar, apagar;

    // Tabela para exibir os produtos
    private JTable table;

    // Modelo de tabela padrão
    private DefaultTableModel tableModel;

    // Variável para armazenar a linha selecionada na tabela
    private int linhaSelecionada = -1;

    // Construtor da classe
    public ProdutoPainel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adiciona um rótulo indicando o propósito do painel
        add(new JLabel("Cadastro Produtos"));

        // Painel para entrada de dados do produto
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Código de Barras"));
        codigoBarrasField = new JTextField(20);
        inputPanel.add(codigoBarrasField);
        inputPanel.add(new JLabel("Nome"));
        nomeField = new JTextField(20);
        inputPanel.add(nomeField);
        inputPanel.add(new JLabel("Preço"));
        precoField = new JTextField(20);
        inputPanel.add(precoField);
        inputPanel.add(new JLabel("Quantidade"));
        quantidadeField = new JTextField(20);
        inputPanel.add(quantidadeField);
        add(inputPanel);

        // Painel para botões de ação
        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        botoes.add(apagar = new JButton("Apagar"));
        add(botoes);

        // Adiciona uma tabela para exibir os produtos
        JScrollPane jScrollPane = new JScrollPane();
        add(jScrollPane);
        tableModel = new DefaultTableModel(new Object[][]{},
                new String[]{"Código de Barras", "Nome", "Preço", "Quantidade"});
        table = new JTable(tableModel);
        jScrollPane.setViewportView(table);

        // Atualiza a tabela com os dados mais recentes do banco de dados
        atualizarTabela();

        // Adiciona um ouvinte de mouse para a tabela
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    // Atualiza os campos com os dados da linha selecionada
                    codigoBarrasField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    nomeField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    precoField.setText(table.getValueAt(linhaSelecionada, 2).toString());
                    quantidadeField.setText(table.getValueAt(linhaSelecionada, 3).toString());
                }
            }
        });

        // Cria uma instância do controlador de produtos
        ProdutoControl operacoes = new ProdutoControl(tableModel, table);

        // Adiciona um ouvinte de ação para o botão de cadastrar
        cadastrar.addActionListener(e -> {
            operacoes.cadastrar(
                    codigoBarrasField.getText(),
                    nomeField.getText(),
                    precoField.getText(),
                    Integer.parseInt(quantidadeField.getText())
            );
            // Limpa os campos após o cadastro
            codigoBarrasField.setText("");
            nomeField.setText("");
            precoField.setText("");
            quantidadeField.setText("");
        });

        // Adiciona um ouvinte de ação para o botão de apagar
        apagar.addActionListener(e -> {
            operacoes.apagar(codigoBarrasField.getText());
            // Limpa os campos após a exclusão
            codigoBarrasField.setText("");
            nomeField.setText("");
            precoField.setText("");
            quantidadeField.setText("");
        });
    }

    // Método para atualizar a tabela com os dados mais recentes do banco de dados
    private void atualizarTabela() {
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
            tableModel.addRow(new Object[]{produto.getCodigoBarras(), produto.getNome(),
                produto.getPreco(), produto.getQuantidade()});
        }
    }
}
