import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalc {
    private static final Scanner SCAN = new Scanner(System.in);
    private static final NumberFormat BALANCEVALUE = NumberFormat.getCurrencyInstance();

    public static void main(String[] args) {
        int principal = (int)readNumber("Kwota kredytu (1k-1m): ", 1000, 1_000_000);
        float air = (float)readNumber("Oprocentowanie kredytu: ", 0,30.00);
        byte period = (byte)readNumber("Okres kredytu (Lata): ",1,30);
        calculateMortgage(principal,air,period);
        paymentSchedule(principal,air,period);
    }
    private static double readNumber(String prompt, double min, double max){
        double value;
        while(true) {
            System.out.print(prompt);
            value = SCAN.nextDouble();
            if (value>=min && value<=max)
                break;
            System.out.println("Podaj liczbe w przedziale " + min + " do " + max);
        }
        return value;
    }
    private static void calculateMortgage(int principal, float air, byte period){
        //monthly interest rate
        float mir = (air/100)/12;
        //number of payments
        int payments = period*12;
        double mortgage = principal*((mir*Math.pow(1+mir,payments))/(Math.pow(1+mir,payments)-1));
        NumberFormat mValue = NumberFormat.getCurrencyInstance();
        String result =  mValue.format(mortgage);
        System.out.println("-------------------------------------\nTwoja miesieczna rata to: "+ result);
    }
    private static void paymentSchedule(int principal, float air, byte period){
        float mir = (air/100)/12;
        int payments = period*12;
        double balance;
        System.out.println("-------------------------------------\nTwoj harmonogram rat to: ");
        for (int i=1;i<=payments; ++i){
            balance =  principal*((Math.pow(1+mir,payments))-(Math.pow(1+mir,i)))/(Math.pow(1+mir,payments)-1);
            System.out.println(BALANCEVALUE.format(balance));
        }
    }
}