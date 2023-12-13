package com.mycompany.View;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientePainel extends JPanel{

    public class MercadoApp extends JPanel {

    
        public void ClientePainel() {
            
            // Criando elementos da interface gráfica9
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 1, 10, 10)); // Layout de grade com espaçamento
            JLabel lblNome = new JLabel("Nome:");
            JTextField txtNome = new JTextField();
    
            JLabel lblEmail = new JLabel("Email:");
            JTextField txtEmail = new JTextField();
    
            JLabel lblCpf = new JLabel("CPF:");
            JTextField txtCpf = new JTextField();
    
    
            JButton btnCadastrar = new JButton("Cadastrar");
    
            
    
            btnCadastrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja se cadastrar? \n","confirmacao",
                    JOptionPane.YES_NO_OPTION);
                    cadastrarUsuario(txtNome.getText(), txtEmail.getText());
    
                    if (resposta == JOptionPane.YES_OPTION) {
                        cadastrarUsuario(txtNome.getText(), txtEmail.getText());
                    }
                }
            });
    
            // Adicionando os elementos ao layout
            panel.add(lblNome);
            panel.add(txtNome);
            panel.add(lblEmail);
            panel.add(txtEmail);
            panel.add(lblCpf);
            panel.add(txtCpf);
            panel.add(btnCadastrar);
    
            // Adicionando o painel ao JFrame
            add(panel);
    
         
        }
    
        private void cadastrarUsuario(String nome, String email) {
            // Lógica para cadastrar o usuário (pode ser uma chamada a um serviço, salvamento em um banco de dados, etc...)
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
            JOptionPane.showMessageDialog(this, "Bem vindo ao Vip"  + nome);
         
        }
    }
   
}
