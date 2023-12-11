/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.View;

import com.mycompany.Controller.ProdutoControl;
import com.mycompany.Model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProdutoPainel extends JPanel {
    private JButton cadastrar, apagar;
    private JTextField codigoBarrasField, nomeField, precoField, quantidadeField;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    public ProdutoPainel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Cadastro Produtos"));
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
        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        botoes.add(apagar = new JButton("Apagar"));
        add(botoes);
        JScrollPane jScrollPane = new JScrollPane();
        add(jScrollPane);
        tableModel = new DefaultTableModel(new Object[][]{},
                new String[]{"Código de Barras", "Nome", "Preço", "Quantidade"});
        table = new JTable(tableModel);
        jScrollPane.setViewportView(table);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    codigoBarrasField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    nomeField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    precoField.setText((String) table.getValueAt(linhaSelecionada, 2));
                    quantidadeField.setText(table.getValueAt(linhaSelecionada, 3).toString());
                }
            }
        });

        ProdutoControl operacoes = new ProdutoControl(tableModel, table);
        cadastrar.addActionListener(e -> {
            operacoes.cadastrar(
                    codigoBarrasField.getText(),
                    nomeField.getText(),
                    precoField.getText(),
                    Integer.parseInt(quantidadeField.getText())
            );
            codigoBarrasField.setText("");
            nomeField.setText("");
            precoField.setText("");
            quantidadeField.setText("");
        });

        apagar.addActionListener(e -> {
            operacoes.apagar(codigoBarrasField.getText());
            codigoBarrasField.setText("");
            nomeField.setText("");
            precoField.setText("");
            quantidadeField.setText("");
        });
    }
}
