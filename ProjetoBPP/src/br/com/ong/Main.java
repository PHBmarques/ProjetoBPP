package br.com.ong;

import br.com.ong.model.Cliente;
import br.com.ong.service.ArquivoCSVService;
import br.com.ong.service.ArquivoTXTService;
import br.com.ong.service.LoginService;
import br.com.ong.util.ValidadorCNPJ;
import br.com.ong.util.ValidadorCPF;
import br.com.ong.util.ValidadorEmail;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("===== SISTEMA ONG - LOGIN =====");
        
        boolean logado = false;
        int tentativas = 0;
        final int MAX_TENTATIVAS = 3;
        
        while (!logado && tentativas < MAX_TENTATIVAS) {
            System.out.print("Usuário: ");
            String usuario = scanner.nextLine();
            
            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            
            if (LoginService.validarLogin(usuario, senha)) {
                System.out.println("\nLogin realizado com sucesso!");
                logado = true;
            } else {
                tentativas++;
                System.out.println("Usuário ou senha incorretos. Tentativa " + tentativas + " de " + MAX_TENTATIVAS);
            }
        }
        
        if (!logado) {
            System.out.println("Número máximo de tentativas excedido. Sistema encerrado.");
            scanner.close();
            return;
        }
        
        int opcao = 0;
        do {
            System.out.println("\n===== SISTEMA ONG - MENU PRINCIPAL =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("3 - Excluir Cliente");
            System.out.println("4 - Ler Estados (CSV)");
            System.out.println("5 - Validar CNPJ (teste)");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Digite apenas números de 1 a 6.");
                scanner.nextLine();
                opcao = 0;
                continue;
            }
            
            switch (opcao) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    ArquivoTXTService.listarClientes();
                    break;
                case 3:
                    excluirCliente(scanner);
                    break;
                case 4:
                    ArquivoCSVService.lerEstados();
                    break;
                case 5:
                    testarValidacaoCNPJ(scanner);
                    break;
                case 6:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha entre 1 e 6.");
            }
        } while (opcao != 6);
        
        scanner.close();
    }
    
    private static void cadastrarCliente(Scanner scanner) {
        System.out.println("\n===== CADASTRAR CLIENTE =====");
        
        System.out.print("ID: ");
        int id;
        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("ID inválido! Deve ser um número.");
            scanner.nextLine();
            return;
        }
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        if (!ValidadorCPF.validar(cpf)) {
            System.out.println("CPF inválido! Cadastro cancelado.");
            return;
        }
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (!ValidadorEmail.validar(email)) {
            System.out.println("Email inválido! Cadastro cancelado.");
            return;
        }
        
        Cliente cliente = new Cliente(id, nome, cpf, email);
        ArquivoTXTService.salvarCliente(cliente);
    }
    
    private static void excluirCliente(Scanner scanner) {
        System.out.println("\n===== EXCLUIR CLIENTE =====");
        System.out.print("Digite o CPF para excluir: ");
        String cpf = scanner.nextLine();
        ArquivoTXTService.excluirCliente(cpf);
    }
    
    private static void testarValidacaoCNPJ(Scanner scanner) {
        System.out.println("\n===== VALIDAR CNPJ =====");
        System.out.print("Digite o CNPJ: ");
        String cnpj = scanner.nextLine();
        
        if (ValidadorCNPJ.validar(cnpj)) {
            System.out.println("CNPJ VÁLIDO!");
        } else {
            System.out.println("CNPJ INVÁLIDO!");
        }
    }
}