package com.mycompany.Connection;

import com.mycompany.Model.Cliente;
import com.mycompany.Model.Produto;
import com.mycompany.Model.Venda;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    private Connection connection;

    public VendaDAO() {
        this.connection = ConnectionFactory.getConnection();
        criarTabela();
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS vendas (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "ID_CLIENTE INT, " +
                "ID_PRODUTO INT, " +
                "QUANTIDADE INT, " +
                "DATA_VENDA DATE, " +
                "FOREIGN KEY (ID_CLIENTE) REFERENCES clientes(ID), " +
                "FOREIGN KEY (ID_PRODUTO) REFERENCES produtos_mercado(CODIGO_BARRAS))";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Tabela de vendas criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela de vendas: " + e.getMessage(), e);
        }
    }

       public List<Venda> listarTodos() {
    String sql = "SELECT * FROM vendas";
    List<Venda> vendas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int vendaId = rs.getInt("ID");
                int clienteId = rs.getInt("ID_CLIENTE");
                int produtoId = rs.getInt("ID_PRODUTO");
                int quantidade = rs.getInt("QUANTIDADE");
                LocalDate dataVenda = rs.getDate("DATA_VENDA").toLocalDate();

                // Você precisa ajustar a maneira como lida com o ID do cliente aqui
                Cliente cliente = new ClienteDAO().obterClientePorId(clienteId);

                // Você também precisa ajustar a maneira como lida com o ID do produto aqui
                Produto produto = new ProdutoDAO().obterProdutoPorId(produtoId);

                java.sql.Date dataVendaConvertida = java.sql.Date.valueOf(dataVenda);
                Venda venda = new Venda(vendaId, cliente, produto, dataVendaConvertida, quantidade);


                vendas.add(venda);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    return vendas;
    }
 


    public void cadastrar(int idCliente, int idProduto, int quantidade, Date dataVenda) {
        String sql = "INSERT INTO vendas (ID_CLIENTE, ID_PRODUTO, QUANTIDADE, DATA_VENDA) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idProduto);
            stmt.setInt(3, quantidade);
            stmt.setDate(4, dataVenda);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                System.out.println("Venda cadastrada com ID: " + generatedKeys.getLong(1));
            } else {
                throw new SQLException("Falha ao obter o ID da venda cadastrada.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar venda: " + e.getMessage(), e);
        }
    }

    public void atualizar(int id, int idCliente, int idProduto, int quantidade, Date dataVenda) {
        String sql = "UPDATE vendas SET ID_CLIENTE = ?, ID_PRODUTO = ?, QUANTIDADE = ?, DATA_VENDA = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idProduto);
            stmt.setInt(3, quantidade);
            stmt.setDate(4, dataVenda);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Venda atualizada com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda: " + e.getMessage(), e);
        }
    }

    public void apagar(int id) {
        String sql = "DELETE FROM vendas WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Venda apagada com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar venda: " + e.getMessage(), e);
        }
    }
    
    
}