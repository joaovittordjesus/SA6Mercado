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
    JComboBox<String> produtosComboBox;
    List<Produto> produtos;

    public VendasView() {
        super();
        produtosComboBox = new JComboBox<>();
        produtos = new ProdutoDAO().listarTodos();
        produtosComboBox.addItem("Selecione o Produto");
        for (Produto produto : produtos) {
            produtosComboBox.addItem(
                    produto.getNome()
                            + " " + produto.getCodigoBarras()
            );
        }
        add(produtosComboBox);
    }
}
