/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Logs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistrosSistemas {

    private static final String CAMINHO_ARQUIVO_LOG = "atividades_sistema.log";

    public void registrarAtividade(String mensagem) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dataHoraFormatada = agora.format(formatter);

        String registro = "[" + dataHoraFormatada + "] " + mensagem;

        try (PrintWriter writer = new PrintWriter(new FileWriter(CAMINHO_ARQUIVO_LOG, true))) {
            writer.println(registro);
            System.out.println("Atividade registrada com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao registrar atividade: " + e.getMessage());
            e.printStackTrace(); // Adição da stack trace na saída de erro
        }
    }
}