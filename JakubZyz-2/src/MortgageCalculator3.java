public class MortgageCalculator3 {
    public static void main(String[] args) {
        System.out.println("KALKULATOR KREDYTOWY");
        /*String fullName = Console.getName();
        String job = Console.getJob();
        String monthlySalary = Console.getMonthlySalary();*/
        int principal = (int) Console.readNumber("Wielkość kredytu (1tys zł  - 1mln zł): ", 1000, 1_000_000);
        double annualInterest = Console.readNumber("Podaj roczną stopę procentową: ", 0, 30);
        byte years = (byte) Console.readNumber("Okres kredytu(w latach): ", 1, 30);

        var calculator = new Calculator(principal,annualInterest,years);
        var report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();
    }

}

