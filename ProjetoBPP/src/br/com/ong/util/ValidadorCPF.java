package br.com.ong.util;

public class ValidadorCPF {

    public static boolean validar(String cpf) {

        if (cpf == null) return false;

        // Remove pontos e traço se tiver
        cpf = cpf.replaceAll("[^0-9]", "");

        // Deve ter 11 dígitos
        if (cpf.length() != 11) return false;

        // Não pode ser todos números iguais
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            // Cálculo do primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }

            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito >= 10) primeiroDigito = 0;

            // Cálculo do segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }

            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito >= 10) segundoDigito = 0;

            // Verifica se os dígitos calculados batem com os informados
            return primeiroDigito == Character.getNumericValue(cpf.charAt(9)) &&
                   segundoDigito == Character.getNumericValue(cpf.charAt(10));

        } catch (Exception e) {
            return false;
        }
    }
}