

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Cliente {

    private int id;
    private String nome;
    private String cpf;
    private String email;

    private static final String CAMINHO_ARQUIVO = "clientes.txt";

    public Cliente(int id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public void salvarNoArquivo(){
        try(BufferedWriter writer= new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))){
            writer.write(this.toString());
            writer.newLine();
            System.out.println("Cliente "+this.nome + " gravado com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao gravar cliente: " +  e.getMessage());
        }
    }
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }


    @Override
    public String toString() {
        return id + ";" + nome + ";" + cpf + ";" + email;
    }
    public static void main(String[] args) {
        Cliente novo= new Cliente(1,"Astolfo","12345678900","astolfo@gmail.com");

        novo.salvarNoArquivo();
    }
}