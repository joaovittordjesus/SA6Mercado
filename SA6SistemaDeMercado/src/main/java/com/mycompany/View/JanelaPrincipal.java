/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.mycompany.View.ClientePainel.MercadoApp;

public class JanelaPrincipal extends JFrame {
    private JTabbedPane jTPane; // Declaração de uma variável JTabbedPane chamada jTPane

    public JanelaPrincipal() { // Construtor da classe
        jTPane = new JTabbedPane(); // Inicialização da variável jTPane como uma nova instância de JTabbedPane
        add(jTPane); // Adiciona o JTabbedPane ao JFrame

        ProdutoPainel tab1 = new ProdutoPainel(); // Cria uma instância da classe ProdutoPainel chamada tab1
        jTPane.add("Produtos", tab1); // Adiciona um componente (ProdutoPainel) à aba "Produtos" do JTabbedPane

        ClientePainel tab2 = new ClientePainel();
        jTPane.add("Cliente", tab2); // Adiciona um componente (representado pela variável tab2) à aba "Cliente
                                     // JTabbedPane
        // Atenção: a variável tab2 não foi inicializada, então essa linha pode causar
        // um NullPointerException

        setBounds(100, 100, 600, 600); // Define as dimensões e a posição do JFrame na tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento padrão de fechamento do JFrame
    }

    public void Run() { // Método para tornar o JFrame visível
        this.setVisible(true);
    }

    public void run() {
        setDefaultCloseOperation(2);
        pack();
        setVisible(true);

    }

}