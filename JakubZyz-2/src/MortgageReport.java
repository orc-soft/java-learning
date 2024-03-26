import java.text.NumberFormat;

public class MortgageReport {
    private final Calculator calculator;
    private final NumberFormat currency;

    public MortgageReport(Calculator calculator) {
        this.calculator = calculator;
        currency = NumberFormat.getCurrencyInstance();
    }
    public void printPaymentSchedule() {
        System.out.println();
        System.out.println("Harmonogram Opłat");
        System.out.println("-----------------");
        for(double balance : calculator.getRemainingBalances())
            System.out.println(currency.format(balance));
    }
    public void printMortgage() {
        double mortgage = calculator.calculateMortgage();
        NumberFormat currency = this.currency;
        String mortgagePln = currency.format(mortgage);
        System.out.println("""
                \nKREDYT HIPOTECZNY
                -----------------
                Miesięczna opłata:
                """ + mortgagePln);
    }
}
