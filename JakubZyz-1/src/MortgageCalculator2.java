import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator2 {
    private static final byte MONTHS = 12;
    private static final byte PERCENT = 100;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("KALKULATOR KREDYTOWY");
        int principal = (int) readNumber("Wielkość kredytu (1tys zł  - 1mln zł): ", 1000, 1_000_000);

        double annualInterest = readNumber("Podaj roczną stopę procentową: ", 0, 30);

        int years = (byte) readNumber("Okres kredytu(w latach): ", 1, 30);

        printMortgage(principal, annualInterest, years);

        printPaymentSchedule(years, principal, annualInterest);
    }

    private static void printMortgage(int principal, double annualInterest, int years) {
        double mortgage = calculateMortgage(principal, annualInterest, years);
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String mortgagePln = currency.format(mortgage);
        System.out.println("""
                \nKREDYT HIPOTECZNY
                -----------------
                Miesięczna opłata:
                """ + mortgagePln);
    }

    private static void printPaymentSchedule(int years, int principle, double annualInterest) {
        System.out.println();
        System.out.println("Harmonogram Opłat");
        System.out.println("-----------------");
        for (short month = 1; month <= years * MONTHS; month++) {
            double accountValue = calculateAccountValue(principle, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(accountValue));
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            value = SCANNER.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Podaj wartość pomiędzy " + min + " a " + max);
        }
        return value;
    }

    public static double calculateAccountValue(int principle, double annualInterest, int years, short paymentsDone) {
        double monthlyInterest = (annualInterest / PERCENT) / MONTHS;
        int numberOfPayments = years * MONTHS;
        double mortgagePatternNumber = Math.pow(1 + monthlyInterest, numberOfPayments);
        double mortgagePatternDone =Math.pow(1 + monthlyInterest, paymentsDone);
        return principle * (mortgagePatternNumber - mortgagePatternDone) / (mortgagePatternNumber - 1);

    }

    public static double calculateMortgage(int principle, double annualInterest, int years) {
        double monthlyInterest = (annualInterest / PERCENT) / MONTHS;
        int numberOfPayments = years * MONTHS;
        double mortgagePatternNumber = Math.pow(1 + monthlyInterest, numberOfPayments);
        return principle * ((monthlyInterest * mortgagePatternNumber) / (mortgagePatternNumber - 1));
    }

}

