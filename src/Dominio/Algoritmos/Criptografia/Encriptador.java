package Dominio.Algoritmos.Criptografia;

public class Encriptador {
    private static int cifra = 7;

    private static String criptografiaCesar(String texto, int chave) {
        StringBuilder resultado = new StringBuilder();
        for (char caractere : texto.toCharArray()) {
            // verifica se é uma letra
            if (Character.isLetter(caractere)) {
                // ajusta para ficar no intervalo do alfabeto
                char base = Character.isLowerCase(caractere) ? 'a' : 'A';
                // aplica a cifra de cesar
                caractere = (char) ((caractere - base + chave) % 26 + base);
            }
            resultado.append(caractere);
        }
        return resultado.toString();
    }

    public static String Encriptar(String texto) {
        return criptografiaCesar(texto, cifra);
    }

    public static String Desencriptar(String texto) {
        return criptografiaCesar(texto, -cifra);
    }

}
