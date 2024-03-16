import java.text.NumberFormat;
import java.util.Scanner;

public class KredytKalkulator {
    public static void main(String[] args) {
        System.out.println("KALKULATOR KREDYTOWY");

        int ileKasy=0;
        double rocznaStopa=0;
        int okresLat=0;

        //ile kredytu
        Scanner loan = new Scanner(System.in);
        System.out.print("Wielkość kredytu (1tys zł  - 1mln zł): ");
        while(true) {
            ileKasy = loan.nextInt();
            if(ileKasy>=1000 && ileKasy<=1_000_000)
                break;
            System.out.print("Podaj numer miedzy 1,000 a 1,000,000: ");
        }
        //stopa procentowa
        Scanner rokStopa = new Scanner(System.in);
        System.out.print("Podaj roczną stopę procentową: ");
        while(true) {
            rocznaStopa = rokStopa.nextDouble();
            if (rocznaStopa>=1 && rocznaStopa<30)
                break;
            System.out.print("Podaj wartość większą niż 0 i mniejszą niż 30: ");
        }

        //Lata
        Scanner okres = new Scanner(System.in);
        System.out.print("Okres kredytu(w latach): ");
        while(true) {
            okresLat = okres.nextInt();
            if(okresLat>=1 && okresLat<=30)
                break;
            System.out.print("Podaj numer między 1 a 30: ");
        }
        double kredyt = policzKredyt(ileKasy,rocznaStopa,okresLat);

        NumberFormat waluta = NumberFormat.getCurrencyInstance();
        String kredytZl = waluta.format(kredyt);
        System.out.print("Hipoteka: " + kredytZl);
    }
    public static double policzKredyt(
            int ileKasy,
            double rocznaStopa,
            int okresLat){
        final byte miesiecy=12;
        final byte procent=100;

        double miesStopa = (rocznaStopa / procent) / miesiecy;
        int liczbaOplat = okresLat * miesiecy;
        double pot = Math.pow(1 + miesStopa, liczbaOplat);
        double kredyt = ileKasy * ((miesStopa * pot) / (pot - 1));
        return kredyt;
    }

}
