
public class ValidadorEmail {

    public static boolean validar(String email) {

        if (email == null || email.isEmpty()) {
            return false;
        }

        if (!email.contains("@")) {
            return false;
        }

        int posicaoArroba = email.indexOf("@");

        if (posicaoArroba == 0 || posicaoArroba == email.length() - 1) {
            return false;
        }

        String dominio = email.substring(posicaoArroba + 1);

        if (!dominio.contains(".")) {
            return false;
        }

        return true;
    }
    public static void main(String[] args) {
       String email = "teste@gmail.com";

       if(validar(email)){
        System.out.println("Email válido");
       } else {
        System.out.println("Email inválido");
       }
    }
}