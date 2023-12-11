/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.View;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class JanelaPrincipal extends JFrame {
    private JTabbedPane jTPane;
    private Component tab2;

    public JanelaPrincipal() {
        jTPane = new JTabbedPane();
        add(jTPane);
        ProdutoPainel tab1 = new ProdutoPainel();
        jTPane.add("Produtos", tab1);
        jTPane.add("Cliente", tab2);
        setBounds(100, 100, 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Run() {
        this.setVisible(true);
    }
}
