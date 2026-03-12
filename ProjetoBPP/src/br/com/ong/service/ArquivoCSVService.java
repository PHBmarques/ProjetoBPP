package br.com.ong.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArquivoCSVService {
    
    private static final String CAMINHO_CSV = "estados.csv";
    
    public static void lerEstados() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_CSV))) {
            
            String linha;
            int contador = 0;
            
            System.out.println("\n===== ESTADOS BRASILEIROS (CSV) =====");
            System.out.printf("%-5s | %-20s | %s%n", "SIGLA", "NOME", "REGIÃO");
            System.out.println("----------------------------------------");
            
            String primeiraLinha = reader.readLine();
            if (primeiraLinha == null) {
                System.out.println("Arquivo CSV vazio.");
                return;
            }
            
            if (!primeiraLinha.toUpperCase().contains("SIGLA")) {
                processarLinha(primeiraLinha);
                contador++;
            }
            
            while ((linha = reader.readLine()) != null) {
                processarLinha(linha);
                contador++;
            }
            
            System.out.println("----------------------------------------");
            System.out.println("Total de estados lidos: " + contador);
            
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo CSV: " + e.getMessage());
            System.out.println("Certifique-se de que o arquivo 'estados.csv' existe na pasta do projeto.");
        }
    }
    
    private static void processarLinha(String linha) {
        String[] dados = linha.split(";");
        
        if (dados.length >= 2) {
            String sigla = dados[0].trim().toUpperCase();
            String nome = dados[1].trim();
            String regiao = dados.length >= 3 ? dados[2].trim() : "N/A";
            
            System.out.printf("%-5s | %-20s | %s%n", sigla, nome, regiao);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Iniciando leitura do CSV...");
        lerEstados();
    }
}

