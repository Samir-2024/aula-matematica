import java.util.ArrayDeque;
import java.util.Deque;

public class CalculadoraRPN {

    // Define a prioridade dos operadores
    private static int prioridade(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            default:
                return 0;
        }
    }

    // Verifica se o caractere é um operador
    private static boolean ehOperador(char caractere) {
        return caractere == '+'
                || caractere == '-'
                || caractere == '*'
                || caractere == '/';
    }

    public static String converterParaRPN(String expressao) {

        Deque<Character> pilha = new ArrayDeque<>();
        StringBuilder saida = new StringBuilder();

        String[] tokens = expressao.trim().split("\\s+");

        for (String token : tokens) {

            // Se for um número, vai diretamente para a saída
            if (ehNumero(token)) {
                saida.append(token).append(" ");
            }

            // Abre parêntese: coloca na pilha
            else if (token.equals("(")) {
                pilha.push('(');
            }

            // Fecha parêntese
            else if (token.equals(")")) {

                while (!pilha.isEmpty() && pilha.peek() != '(') {
                    saida.append(pilha.pop()).append(" ");
                }

                if (!pilha.isEmpty() && pilha.peek() == '(') {
                    pilha.pop();
                }
            }

            // Operador
            else if (ehOperador(token.charAt(0))) {

                char operador = token.charAt(0);

                while (!pilha.isEmpty()
                        && pilha.peek() != '('
                        && prioridade(pilha.peek()) >= prioridade(operador)) {

                    saida.append(pilha.pop()).append(" ");
                }

                pilha.push(operador);
            }

            else {
                throw new IllegalArgumentException(
                        "Token inválido: " + token
                );
            }
        }

        // Descarrega os operadores restantes
        while (!pilha.isEmpty()) {

            if (pilha.peek() == '(') {
                throw new IllegalArgumentException(
                        "Parênteses não balanceados."
                );
            }

            saida.append(pilha.pop()).append(" ");
        }

        return saida.toString().trim();
    }
}