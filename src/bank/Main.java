package bank;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        Test1();
        Test2();
        Test3();
        Test4();
    }


    public static void Test1()
    {
        System.out.println("=== Test: bank.Wlasciciel ===");
        try {
            Wlasciciel w1 = new Wlasciciel("Jan Kowalski", "Lipowa 12", "01-234", "Warszawa");
            System.out.println("Poprawnie utworzony właściciel: " + w1);
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }

        try {
            Wlasciciel w2 = new Wlasciciel("An", "Li", "12345", "WW");
        } catch (Exception e) {
            System.out.println("Oczekiwany błąd: " + e.getMessage());
        }
    }

    public static void Test2()
    {
        System.out.println("\n=== Test: bank.Konto ===");
        Wlasciciel wlasciciel = new Wlasciciel("Adam Malysz", "Skoczków 10", "43-400", "Wisla");
        Konto konto = new Konto(wlasciciel);

        System.out.println(konto);
        konto.wplac(500);
        System.out.println("Po wpłacie: " + konto);

        try {
            konto.wyplac(200);
            System.out.println("Po wypłacie: " + konto);
        } catch (Konto.BrakPieniedzyException e) {
            System.out.println(e.getMessage());
        }

        try {
            konto.wyplac(1000); // za dużo
        } catch (Konto.BrakPieniedzyException e) {
            System.out.println("Oczekiwany wyjątek: " + e.getMessage());
        }
    }

    public static void Test3()
    {
        System.out.println("\n=== Test: bank.Bank ===");
        Bank bank = new Bank("Mój bank.Bank");

        Konto k1 = new Konto(new Wlasciciel("Ela", "Spacerowa 1", "00-001", "Warszawa"));
        Konto k2 = new Konto(new Wlasciciel("Franek", "Zielona 2", "11-111", "Gdańsk"));

        k1.wplac(1000);
        k2.wplac(500);

        bank.utworzKonto(k1);
        bank.utworzKonto(k2);

        System.out.println("bank.Konto o nr: " + k1.getNumerKonta() + " => " + bank.podajKonto(k1.getNumerKonta()));
        System.out.println("bank.Konto właściciela 'Ela': " + bank.podajKonto("Ela"));

        System.out.println("Saldo banku: " + bank.saldoBanku() + " zł");

        bank.usunKonto(k1.getNumerKonta());
        System.out.println("Po usunięciu konta:");
        System.out.println(bank);
    }

    public static void Test4()
    {
        System.out.println("\n=== Test: bank.Lokata ===");
        Wlasciciel wlascLok = new Wlasciciel("Zenon", "Lokacyjna 8", "44-555", "Katowice");
        Lokata lokata = new Lokata(wlascLok, Okres.TRZYMIESIECZNA);
        lokata.wplac(2000);

        System.out.println("Utworzono lokatę:");
        System.out.println(lokata);
    }
}