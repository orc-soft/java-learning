import java.text.NumberFormat;
import java.util.Scanner;

public class KredytKalkulator {
    public static void main(String[] args) {
        System.out.println("KALKULATOR KREDYTOWY");
        final byte miesiecy=12;
        final byte procent=100;

        //ile kredytu
        Scanner loan = new Scanner(System.in);
        System.out.print("Ile chcesz pożyczyć?: ");
        int ileKasy = loan.nextInt();
        //System.out.println(ileKasy);

        //Roczna stopa
        Scanner rokStopa = new Scanner(System.in);
        System.out.print("Podaj roczną stopę procentową: ");
        double rocznaStopa = rokStopa.nextDouble();
        //System.out.println(rocznaStopa);

        //Lata
        Scanner okres = new Scanner(System.in);
        System.out.print("Okres kredytu(w latach): ");
        int okresLat = okres.nextInt();
        //System.out.println(okresLat);

        //miesięczna stopa
        double miesStopa = (rocznaStopa / procent) / miesiecy;
        //System.out.println(miesStopa);

        //liczba opłat
        int liczbaOplat = okresLat * miesiecy;
        //System.out.println(liczbaOplat);

        //liczenie kredytu
        double pot = Math.pow(1 + miesStopa, liczbaOplat);
        double kredyt = ileKasy * ((miesStopa * pot) / (pot - 1));
        NumberFormat waluta = NumberFormat.getCurrencyInstance();
        String kredytZl = waluta.format(kredyt);
        System.out.print("Hipoteka: " + kredytZl);
    }
}
