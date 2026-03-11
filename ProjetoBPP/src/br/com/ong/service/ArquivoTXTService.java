package br.com.ong.service;

import br.com.ong.model.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoTXTService {

    private static final String CAMINHO_ARQUIVO = "clientes.txt";

    public static void salvarCliente(Cliente cliente) {

        if (cpfJaExiste(cliente.getCpf())) {
            System.out.println("CPF já cadastrado! Cliente não salvo.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(CAMINHO_ARQUIVO, true))) {

            writer.write(cliente.toString());
            writer.newLine();

            System.out.println("Cliente salvo com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    public static void listarClientes() {

        try (BufferedReader reader = new BufferedReader(
                new FileReader(CAMINHO_ARQUIVO))) {

            String linha;

            System.out.println("\n===== LISTA DE CLIENTES =====");
            System.out.printf("%-5s | %-20s | %-14s | %s%n", "ID", "NOME", "CPF", "EMAIL");
            System.out.println("------------------------------------------------------------------");

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 4) {
                    System.out.printf("%-5s | %-20s | %-14s | %s%n", 
                        dados[0], dados[1], dados[2], dados[3]);
                } else {
                    System.out.println(linha);
                }
            }

        } catch (IOException e) {
            System.out.println("Nenhum cliente cadastrado ainda.");
        }
    }

    public static boolean cpfJaExiste(String cpf) {

        try (BufferedReader reader = new BufferedReader(
                new FileReader(CAMINHO_ARQUIVO))) {

            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3 && dados[2].equals(cpf)) {
                    return true;
                }
            }

        } catch (IOException e) {
            return false;
        }

        return false;
    }

    public static void excluirCliente(String cpf) {

        List<String> linhas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(CAMINHO_ARQUIVO))) {

            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length < 3 || !dados[2].equals(cpf)) {
                    linhas.add(linha);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(CAMINHO_ARQUIVO))) {

            for (String l : linhas) {
                writer.write(l);
                writer.newLine();
            }

            System.out.println("Cliente excluído com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao excluir cliente.");
        }
    }
}