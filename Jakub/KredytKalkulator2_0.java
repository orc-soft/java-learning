package Jakub;
import java.text.NumberFormat;
import java.util.Scanner;

public class KredytKalkulator2_0 {
    final static byte miesiecy=12;
    final static byte procent=100;
    public static void main(String[] args) {
        System.out.println("KALKULATOR KREDYTOWY");
        //ile kredytu
        int ileKasy = (int)readNumber
                ("Wielkość kredytu (1tys zł  - 1mln zł): ",1000,1_000_000);

        //stopa procentowa
        double rocznaStopa =(double)readNumber
                ("Podaj roczną stopę procentową: ",0,30);

        //Lata
        int okresLat=(byte)readNumber
                ("Okres kredytu(w latach): ",1,30);

        printKredyt(ileKasy, rocznaStopa, okresLat);

        printHarmonogramOplat(okresLat, ileKasy, rocznaStopa);
    }

    private static void printKredyt(int ileKasy, double rocznaStopa, int okresLat) {
        double kredyt = policzKredyt(ileKasy, rocznaStopa, okresLat);
        NumberFormat waluta = NumberFormat.getCurrencyInstance();
        String kredytZl = waluta.format(kredyt);
        System.out.println();
        System.out.println("KREDYT HIPOTECZNY");
        System.out.println("-----------------");
        System.out.println("Miesięczna opłata: " + kredytZl);
    }

    private static void printHarmonogramOplat(int okresLat, int ileKasy, double rocznaStopa) {
        System.out.println();
        System.out.println("Harmonogram Opłat");
        System.out.println("-----------------");
        for(short miesiac = 1; miesiac<= okresLat *miesiecy; miesiac++){
            double stanKonta=policzStanKonta(ileKasy, rocznaStopa, okresLat,miesiac);
            System.out.println(NumberFormat.getCurrencyInstance().format(stanKonta));
        }
    }

    public static double readNumber(String prompt,double min, double max){
        Scanner skaner = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = skaner.nextDouble();
            if (value>=min && value<=max)
                break;
            System.out.println("Podaj wartość pomiędzy "+min+" a "+max);
        }
        return value;
    }
    public static double policzStanKonta(
            int ileKasy,
            double rocznaStopa,
            int okresLat,
            short oplatyDokonane
    ){

        double miesStopa = (rocznaStopa / procent) / miesiecy;
        int liczbaOplat = okresLat * miesiecy;
        double stanKonta =ileKasy*
                (Math.pow(1+miesStopa,liczbaOplat)-Math.pow(1+miesStopa,oplatyDokonane))
                /(Math.pow(1+miesStopa,liczbaOplat)-1);

        return stanKonta;
    }
    public static double policzKredyt(
            int ileKasy,
            double rocznaStopa,
            int okresLat){
        double miesStopa = (rocznaStopa / procent) / miesiecy;
        int liczbaOplat = okresLat * miesiecy;
        double pot = Math.pow(1 + miesStopa, liczbaOplat);
        double kredyt = ileKasy * ((miesStopa * pot) / (pot - 1));
        return kredyt;
    }

}

