package br.com.ong.service;

public class LoginService {
    
    private static final String USUARIO_PADRAO = "admin";
    private static final String SENHA_PADRAO = "Admin@123";
    
    public static boolean validarLogin(String usuario, String senha) {
        if (usuario == null || senha == null) return false;
        return usuario.equals(USUARIO_PADRAO) && senha.equals(SENHA_PADRAO);
    }
    
    public static boolean validarSenhaForte(String senha) {
        if (senha == null || senha.length() < 8) {
            System.out.println("Senha deve ter no mínimo 8 caracteres.");
            return false;
        }
        
        boolean temMaiuscula = false;
        boolean temMinuscula = false;
        boolean temNumero = false;
        boolean temEspecial = false;
        
        for (char c : senha.toCharArray()) {
            if (Character.isUpperCase(c)) temMaiuscula = true;
            else if (Character.isLowerCase(c)) temMinuscula = true;
            else if (Character.isDigit(c)) temNumero = true;
            else temEspecial = true;
        }
        
        if (!temMaiuscula) {
            System.out.println("Senha deve conter pelo menos uma letra maiúscula.");
            return false;
        }
        if (!temMinuscula) {
            System.out.println("Senha deve conter pelo menos uma letra minúscula.");
            return false;
        }
        if (!temNumero) {
            System.out.println("Senha deve conter pelo menos um número.");
            return false;
        }
        if (!temEspecial) {
            System.out.println("Senha deve conter pelo menos um caractere especial (@#$%&*!).");
            return false;
        }
        
        return true;
    }
    
    public static void exibirRequisitosSenha() {
        System.out.println("\nRequisitos para senha forte:");
        System.out.println("- Mínimo 8 caracteres");
        System.out.println("- Pelo menos uma letra maiúscula (A-Z)");
        System.out.println("- Pelo menos uma letra minúscula (a-z)");
        System.out.println("- Pelo menos um número (0-9)");
        System.out.println("- Pelo menos um caractere especial (@#$%&*!)");
    }
}