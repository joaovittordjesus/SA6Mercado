/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.View;

import com.mycompany.Connection.ProdutoDAO;
import com.mycompany.Model.Produto;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.util.List;

public class VendasView extends JPanel {
    JComboBox<String> produtosComboBox; // Declaração de um JComboBox para exibir produtos
    List<Produto> produtos; // Lista para armazenar produtos

    // Construtor da classe
    public VendasView() {
        super(); // Chama o construtor da classe pai (JPanel)

        produtosComboBox = new JComboBox<>(); // Inicializa o JComboBox
        produtos = new ProdutoDAO().listarTodos(); // Obtém a lista de produtos do banco de dados
        produtosComboBox.addItem("Selecione o Produto"); // Adiciona um item inicial ao JComboBox

        // Preenche o JComboBox com nomes de produtos e códigos de barras
        for (Produto produto : produtos) {
            produtosComboBox.addItem(
                    produto.getNome()
                            + " " + produto.getCodigoBarras()
            );
        }

        add(produtosComboBox); // Adiciona o JComboBox ao painel
    }
}
