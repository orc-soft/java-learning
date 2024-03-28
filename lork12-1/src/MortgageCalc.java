import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalc {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final NumberFormat BALANCEVALUE = NumberFormat.getCurrencyInstance();
    private static final byte MONTHS = 12;
    private static final int PERCENT = 100;

    public static void main(String[] args) {
        int principal = (int) readNumber("Kwota kredytu (1k-1m): ", 1000, 1_000_000);
        float air = readNumber("Oprocentowanie kredytu: ", 0, 30.00);
        byte period = (byte) readNumber("Okres kredytu (Lata): ", 1, 30);
        calculateMortgage(principal, air, period);
        paymentSchedule(principal, air, period);
    }

    private static float readNumber(String prompt, double min, double max) {
        float value;
        while (true) {
            System.out.print(prompt);
            value = SCAN.nextFloat();
            if (value >= min && value <= max)
                return value;
            System.out.println("Podaj liczbe w przedziale " + min + " do " + max);
        }
    }

    private static void calculateMortgage(int principal, float air, byte period) {
        double mortgage = principal * ((getMonthlyInterestRate(air) * Math.pow(1 + getMonthlyInterestRate(air), getNumberOfPayments(period))) / (Math.pow(1 + getMonthlyInterestRate(air), getNumberOfPayments(period)) - 1));
        NumberFormat mortgageValue = NumberFormat.getCurrencyInstance();
        String result = mortgageValue.format(mortgage);
        System.out.println("-------------------------------------\nTwoja miesieczna rata to: " + result);
    }

    private static void paymentSchedule(int principal, float air, byte period) {
        double balance;
        System.out.println("-------------------------------------\nTwoj harmonogram rat to: ");
        for (int i = 1; i <= getNumberOfPayments(period); ++i) {
            balance = principal * ((Math.pow(1 + getMonthlyInterestRate(air), getNumberOfPayments(period))) - (Math.pow(1 + getMonthlyInterestRate(air), i))) / (Math.pow(1 + getMonthlyInterestRate(air), getNumberOfPayments(period)) - 1);
            System.out.println(BALANCEVALUE.format(balance));
        }
    }

    private static float getMonthlyInterestRate(float air) {
        return (air / PERCENT) / MONTHS;
    }
    
    private static int getNumberOfPayments(byte period) {
        return period * MONTHS;
    }
}