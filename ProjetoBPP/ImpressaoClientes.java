import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImpressaoClientes {

    // Caminho do arquivo (deve ser o mesmo usado na classe Cliente)
    private static final String CAMINHO_ARQUIVO = "clientes.txt";

    // Método para ler os dados do TXT e transformar em uma lista de objetos Cliente
    public static List<Cliente> lerClientesDoArquivo() {
        List<Cliente> clientes = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            
            // Lê cada linha do arquivo até chegar ao fim (null)
            while ((linha = reader.readLine()) != null) {
                
                // O método toString() da classe Cliente salva no formato: id;nome;cpf;email
                String[] dados = linha.split(";");
                
                // Verifica se a linha tem exatamente as 4 informações esperadas
                if (dados.length == 4) {
                    try {
                        int id = Integer.parseInt(dados[0]);
                        String nome = dados[1];
                        String cpf = dados[2];
                        String email = dados[3];
                        
                        // Recria o objeto Cliente e adiciona na lista
                        Cliente cliente = new Cliente(id, nome, cpf, email);
                        clientes.add(cliente);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter ID na linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo (pode estar vazio ou não existir): " + e.getMessage());
        }
        
        return clientes;
    }

    // Método para imprimir a lista de clientes formatada no console
    public static void imprimirRelatorio(List<Cliente> clientes) {
       

        // Verifica se a lista está vazia
        if (clientes == null || clientes.isEmpty()) {
            System.out.println(" NENHUM CLIENTE CADASTRADO NO ARQUIVO");

            return;
        }

        // Itera sobre a lista e imprime os dados
        for (Cliente cliente : clientes) {
            System.out.printf(" %-5d  %-20s  %-14s  %-30s %n", 
                    cliente.getId(), 
                    cliente.getNome(), 
                    cliente.getCpf(), 
                    cliente.getEmail());
        }

    }

    // Main para rodar e testar de forma isolada
    public static void main(String[] args) {
        System.out.println("Buscando dados no arquivo: " + CAMINHO_ARQUIVO + "...\n");
        
        List<Cliente> clientesSalvos = lerClientesDoArquivo();
        
        imprimirRelatorio(clientesSalvos);
    }
}