import java.util.Scanner;

public class Console {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static double readNumber(String promt){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
    public static double readNumber(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            value = SCANNER.nextDouble();
            if (value >= min && value <= max)
                return value;
            System.out.println("Podaj wartość pomiędzy " + min + " a " + max);
        }
    }
}
