package com.mycompany.Connection;

import com.mycompany.Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO() {
        this.connection = ConnectionFactory.getConnection();
        criarTabela();
    }

    // Método para obter um produto por ID
    public Produto obterProdutoPorId(int idProduto) {
        String sql = "SELECT * FROM produtos_mercado WHERE CODIGO_BARRAS=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Cria e retorna um objeto Produto com base nos dados do banco de dados
                    return new Produto(
                            rs.getString("CODIGO_BARRAS"),
                            rs.getString("NOME"),
                            Double.parseDouble(rs.getString("PRECO")),
                            rs.getInt("QUANTIDADE")
                    );
                } else {
                    throw new RuntimeException("Produto não encontrado para o ID: " + idProduto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter produto por ID: " + e.getMessage(), e);
        }
    }

    // Método para criar a tabela no banco de dados, se não existir
    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS produtos_mercado (" +
                "CODIGO_BARRAS VARCHAR(255) PRIMARY KEY, " +
                "NOME VARCHAR(255), " +
                "PRECO DECIMAL(10,2), " +
                "QUANTIDADE INT)";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        }
    }

    // Método para listar todos os produtos no banco de dados
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos_mercado";
        try (Statement stmt = this.connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                // Para cada linha do resultado, cria um objeto Produto e adiciona à lista
                String codigoBarras = resultSet.getString("CODIGO_BARRAS");
                String nome = resultSet.getString("NOME");
                // Correção: Converter a String para double
                double preco = Double.parseDouble(resultSet.getString("PRECO"));
                int quantidade = resultSet.getInt("QUANTIDADE");
                Produto produto = new Produto(codigoBarras, nome, preco, quantidade);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos: " + e.getMessage(), e);
        }
        return produtos;
    }

    // Método para cadastrar um novo produto no banco de dados
    public void cadastrar(String codigoBarras, String nome, String preco, int quantidade) {
        String sql = "INSERT INTO produtos_mercado (CODIGO_BARRAS, NOME, PRECO, QUANTIDADE) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, codigoBarras);
            stmt.setString(2, nome);
            stmt.setString(3, preco);
            stmt.setInt(4, quantidade);
            stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar produto: " + e.getMessage(), e);
        }
    }

    // Método para atualizar um produto no banco de dados
    public void atualizar(String codigoBarras, String nome, String preco, int quantidade) {
        String sql = "UPDATE produtos_mercado SET NOME=?, PRECO=?, QUANTIDADE=? WHERE CODIGO_BARRAS=?";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, preco);
            stmt.setInt(3, quantidade);
            stmt.setString(4, codigoBarras);
            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
    }

    // Método para apagar um produto do banco de dados
    public void apagar(String codigoBarras) {
        String sql = "DELETE FROM produtos_mercado WHERE CODIGO_BARRAS=?";
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, codigoBarras);
            stmt.executeUpdate();
            System.out.println("Produto apagado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar produto: " + e.getMessage(), e);
        }
    }

    // Método para obter a quantidade de um produto no banco de dados
    public int obterQuantidade(String codigoBarras) {
        String sql = "SELECT QUANTIDADE FROM produtos_mercado WHERE CODIGO_BARRAS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigoBarras);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("QUANTIDADE");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter quantidade do produto: " + e.getMessage(), e);
        }
        return 0; // Retornar 0 se não encontrar o produto
    }

    // Método para atualizar a quantidade de um produto no banco de dados
    public void atualizarQuantidade(String codigoBarras, int novaQuantidade) {
        String sql = "UPDATE produtos_mercado SET QUANTIDADE = ? WHERE CODIGO_BARRAS = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setString(2, codigoBarras);
            stmt.executeUpdate();
            System.out.println("Quantidade do produto atualizada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar quantidade do produto: " + e.getMessage(), e);
        }
    }
}
