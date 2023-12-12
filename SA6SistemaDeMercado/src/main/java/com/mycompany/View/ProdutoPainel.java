/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.View;

import com.mycompany.Controller.ProdutoControl;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ProdutoPainel extends JPanel {
    // Declaração de componentes
    private JButton cadastrar, apagar;
    private JTextField codigoBarrasField, nomeField, precoField, quantidadeField;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    // Construtor da classe
    public ProdutoPainel() {
        super(); // Chama o construtor da classe pai (JPanel)

        // Define o layout do painel como BoxLayout vertical
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Adiciona um rótulo ao painel
        add(new JLabel("Cadastro Produtos"));

        // Configuração do painel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2)); // GridLayout com 4 linhas e 2 colunas
        inputPanel.add(new JLabel("Código de Barras"));
        codigoBarrasField = new JTextField(20); // Cria um campo de texto com tamanho 20
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
        add(inputPanel); // Adiciona o painel de entrada ao painel principal

        // Configuração dos botões
        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar")); // Cria e adiciona o botão "Cadastrar"
        botoes.add(apagar = new JButton("Apagar")); // Cria e adiciona o botão "Apagar"
        add(botoes); // Adiciona o painel de botões ao painel principal

        // Configuração da tabela
        JScrollPane jScrollPane = new JScrollPane();
        add(jScrollPane); // Adiciona um painel de rolagem ao painel principal
        tableModel = new DefaultTableModel(new Object[][]{},
                new String[]{"Código de Barras", "Nome", "Preço", "Quantidade"});
        table = new JTable(tableModel); // Cria uma tabela com base no modelo
        jScrollPane.setViewportView(table); // Define a tabela no painel de rolagem

        // Adiciona um ouvinte de eventos de clique na tabela
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    // Preenche os campos de texto com os valores da linha selecionada
                    codigoBarrasField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    nomeField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    precoField.setText((String) table.getValueAt(linhaSelecionada, 2));
                    quantidadeField.setText(table.getValueAt(linhaSelecionada, 3).toString());
                }
            }
        });

        // Cria uma instância de ProdutoControl para manipular operações na tabela
        ProdutoControl operacoes = new ProdutoControl(tableModel, table);

        // Adiciona ouvinte de eventos para o botão "Cadastrar"
        cadastrar.addActionListener(e -> {
            operacoes.cadastrar(
                    codigoBarrasField.getText(),
                    nomeField.getText(),
                    precoField.getText(),
                    Integer.parseInt(quantidadeField.getText())
            );
            // Limpa os campos de texto após cadastrar
            codigoBarrasField.setText("");
            nomeField.setText("");
            precoField.setText("");
            quantidadeField.setText("");
        });

        // Adiciona ouvinte de eventos para o botão "Apagar"
        apagar.addActionListener(e -> {
            operacoes.apagar(codigoBarrasField.getText());
            // Limpa os campos de texto após apagar
            codigoBarrasField.setText("");
            nomeField.setText("");
            precoField.setText("");
            quantidadeField.setText("");
        });
    }
}
