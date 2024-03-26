import java.text.NumberFormat;
import java.util.Scanner;

public class krisumortgage {

    private static final byte PERCENT = 100;
    private static final byte MONTHS_IN_YEAR = 12;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("WELCOME TO THE GODLY MORTGAGE CALCULATOR");

        int principal = (int) readNumber("Loan Amount (1k zloty - 1mln zloty): ", 1000, 1_000_000);
        double annualInterest = readNumber("Annual Interest Rate (0-30%): ", 0, 30);
        int years = (int) readNumber("Loan Period (years): ", 1, 30);

        printMortgage(principal, annualInterest, years);
        printPaymentSchedule(principal, annualInterest, years);
    }

    private static void printMortgage(int principal, double annualInterest, int years) {
        double mortgage = calculateMortgage(principal, annualInterest, years);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String formattedMortgage = currencyFormatter.format(mortgage);

        System.out.println("\nMORTGAGE");
        System.out.println("|||||||||||||||||");
        System.out.println("Monthly Payment: " + formattedMortgage);
    }

    private static void printPaymentSchedule(int principal, double annualInterest, int years) {
        System.out.println("\nPAYMENTS SCHEDULE");
        System.out.println("|||||||||||||||||");

        for (int month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            double remainingBalance = calculateRemainingBalance(principal, annualInterest, years, month);
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
            System.out.println(currencyFormatter.format(remainingBalance));
        }
    }

    public static double readNumber(String prompt, double min, double max) {
        double value;
        do {
            System.out.print(prompt);
            value = SCANNER.nextDouble();
            if (value < min || value > max) {
                System.out.println("Enter a value between " + min + " and " + max);
            }
        } while (value < min || value > max);
        return value;
    }

    private static double calculateRemainingBalance(int principal, double annualInterest, int years, int paymentsMade) {
        double monthlyInterest = (annualInterest / PERCENT) / MONTHS_IN_YEAR;
        int numberOfPayments = years * MONTHS_IN_YEAR;
        double mortgagePaymentNumber = Math.pow(1 + monthlyInterest, numberOfPayments);
        double mortgagePaymentDone = Math.pow(1 + monthlyInterest, paymentsMade);
        return principal * (mortgagePaymentNumber - mortgagePaymentDone) / (mortgagePaymentNumber - 1);
    }

    private static double calculateMortgage(int principal, double annualInterest, int years) {
        double monthlyInterest = (annualInterest / PERCENT) / MONTHS_IN_YEAR;
        int numberOfPayments = years * MONTHS_IN_YEAR;
        double mortgagePaymentNumber = Math.pow(1 + monthlyInterest, numberOfPayments);
        return principal * (monthlyInterest * mortgagePaymentNumber) / (mortgagePaymentNumber - 1);
    }
}
