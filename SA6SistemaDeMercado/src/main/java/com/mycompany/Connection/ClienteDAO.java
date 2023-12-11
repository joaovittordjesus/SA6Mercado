/*
 * Este é o código fonte para a classe ClienteDAO, que lida com operações de banco de dados relacionadas à entidade Cliente.
 */

package com.mycompany.Connection;

import com.mycompany.Model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection connection;

    // O construtor cria uma instância da classe e obtém uma conexão com o banco de dados usando a ConnectionFactory.
    public ClienteDAO() {
        this.connection = ConnectionFactory.getConnection();
        criarTabela(); // Chama o método para criar a tabela no banco de dados, se não existir.
    }

    // Este método cria a tabela 'clientes' no banco de dados, se ela ainda não existir.
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS clientes (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "NOME VARCHAR(255), " +
                "CPF VARCHAR(14), " +
                "EMAIL VARCHAR(255))";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.execute();
            System.out.println("Tabela de clientes criada com sucesso.");
        } catch (SQLException e) {
            // Em caso de erro, lança uma exceção RuntimeException.
            throw new RuntimeException("Erro ao criar a tabela de clientes: " + e.getMessage(), e);
        }
    }

    // Este método retorna uma lista de todos os clientes presentes na tabela 'clientes'.
    public List<Cliente> listarTodos() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Para cada linha no resultado, cria um objeto Cliente e adiciona à lista.
                Cliente cliente = new Cliente(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("CPF"),
                        rs.getString("EMAIL")
                );
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }

    // Este método cadastra um novo cliente na tabela 'clientes'.
    public void cadastrar(String nome, String cpf, String email) {
        String sql = "INSERT INTO clientes (NOME, CPF, EMAIL) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Define os parâmetros na consulta SQL com os valores fornecidos.
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.executeUpdate(); // Executa a inserção no banco de dados.

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                // Imprime o ID gerado para o cliente recém-cadastrado.
                System.out.println("Cliente cadastrado com ID: " + generatedKeys.getLong(1));
            } else {
                // Em caso de falha ao obter o ID gerado, lança uma exceção SQLException.
                throw new SQLException("Falha ao obter o ID do cliente cadastrado.");
            }

        } catch (SQLException e) {
            // Em caso de erro, lança uma exceção RuntimeException.
            throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage(), e);
        }
    }

    // Este método atualiza as informações de um cliente na tabela 'clientes'.
    public void atualizar(int id, String nome, String cpf, String email) {
        String sql = "UPDATE clientes SET NOME = ?, CPF = ?, EMAIL = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Define os parâmetros na consulta SQL com os valores fornecidos.
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.setInt(4, id);
            stmt.executeUpdate(); // Executa a atualização no banco de dados.
            System.out.println("Cliente atualizado com sucesso");
        } catch (SQLException e) {
            // Em caso de erro, lança uma exceção RuntimeException.
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }

    // Este método apaga um cliente da tabela 'clientes' com base no ID.
    public void apagar(int id) {
        String sql = "DELETE FROM clientes WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Define o parâmetro na consulta SQL com o valor fornecido.
            stmt.setInt(1, id);
            stmt.executeUpdate(); // Executa a exclusão no banco de dados.
            System.out.println("Cliente apagado com sucesso");
        } catch (SQLException e) {
            // Em caso de erro, lança uma exceção RuntimeException.
            throw new RuntimeException("Erro ao apagar cliente: " + e.getMessage(), e);
        }
    }

    // Este método obtém um cliente da tabela 'clientes' com base no ID.
    public Cliente obterClientePorId(int idCliente) {
        String sql = "SELECT * FROM clientes WHERE ID=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Define o parâmetro na consulta SQL com o valor fornecido.
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Se encontrar um resultado, cria um objeto Cliente e o retorna.
                    int id = rs.getInt("ID");
                    String nome = rs.getString("NOME");
                    String email = rs.getString("EMAIL");
                    String telefone = rs.getString("TELEFONE");
                    if (rs.wasNull()) {
                        // Lança uma exceção se encontrar um ID nulo para o cliente.
                        throw new RuntimeException("ID nulo encontrado para o cliente.");
                    }
                    return new Cliente(id, nome, email, telefone);
                } else {
                    // Se não houver resultados, lança uma exceção ou retorna null, dependendo da lógica desejada.
                    throw new RuntimeException("Cliente não encontrado para o ID: " + idCliente);
                }
            }
        } catch (SQLException e) {
            // Em caso de erro, lança uma exceção RuntimeException.
            throw new RuntimeException("Erro ao obter cliente por ID: " + e.getMessage(), e);
        }
    }
}
