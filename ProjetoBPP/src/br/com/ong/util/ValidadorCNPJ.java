package br.com.ong.util;

public class ValidadorCNPJ {
    
    public static boolean validar(String cnpj) {
        if (cnpj == null) return false;
        
        cnpj = cnpj.replaceAll("[^0-9]", "");
        
        if (cnpj.length() != 14) return false;
        
        if (cnpj.matches("(\\d)\\1{13}")) return false;
        
        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            
            int soma = 0;
            for (int i = 0; i < 12; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos1[i];
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 > 9) digito1 = 0;
            
            soma = 0;
            for (int i = 0; i < 13; i++) {
                soma += Character.getNumericValue(cnpj.charAt(i)) * pesos2[i];
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 > 9) digito2 = 0;
            
            return digito1 == Character.getNumericValue(cnpj.charAt(12)) &&
                   digito2 == Character.getNumericValue(cnpj.charAt(13));
                   
        } catch (Exception e) {
            return false;
        }
    }
}