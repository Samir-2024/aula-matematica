import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Digite a expressão: ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("sair")) {
                System.out.println("\nPrograma encerrado.");
                break;
            }

            if (entrada.isEmpty()) {
                continue;
            }

            try {
                String expressaoNormalizada = normalizarEspacos(entrada);
                String rpn = CalculadoraRPN.converterParaRPN(expressaoNormalizada);
                double resultado = CalculadoraRPN.calcularRPN(rpn);

                System.out.println("Expressão formatada : " + expressaoNormalizada);
                System.out.println("Notação RPN         : " + rpn);
                System.out.println("Resultado           : " + resultado);
                System.out.println("--------------------------------------------------");
            } catch (Exception e) {
                System.out.println("! Erro: " + e.getMessage());
                System.out.println("--------------------------------------------------");
            }
        }

        scanner.close();
    }

    private static String normalizarEspacos(String exp) {
        return exp.replaceAll("([()+*/])", " $1 ")
                  .replaceAll("(?<=\\S)-(?=\\S)", " - ")
                  .trim()
                  .replaceAll("\\s+", " ");
    }
}
