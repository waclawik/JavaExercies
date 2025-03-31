package bank;

import java.io.*;
import java.util.*;

public class Bank implements Serializable {
    private String nazwa;
    private List<Konto> konta;

    public Bank(String nazwa) {
        this.nazwa = nazwa;
        this.konta = new ArrayList<>();
    }

    public void utworzKonto(Konto k) { konta.add(k); }

    public void usunKonto(long nrKonta) { konta.removeIf(k -> k.getNumerKonta() == nrKonta); }

    public Konto podajKonto(long nrKonta) {
        for (Konto k : konta) if (k.getNumerKonta() == nrKonta) return k;
        return null;
    }

    public Konto podajKonto(String nazwa) {
        for (Konto k : konta) if (k.getWlasciciel().getNazwa().equalsIgnoreCase(nazwa)) return k;
        return null;
    }

    public double saldoBanku() {
        double suma = 0;
        for (Konto k : konta) suma += k.getSaldo();
        return suma;
    }

    public void wyswietlWgSald() {
        konta.stream().sorted().forEach(System.out::println);
    }

    public void wyswietlWgNazw() {
        konta.stream().sorted(Comparator.comparing(k -> k.getWlasciciel().getNazwa())).forEach(System.out::println);
    }

    public void zapisz(String nazwa) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nazwa))) {
            oos.writeObject(this);
        }
    }

    public static Bank odczytaj(String nazwa) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nazwa))) {
            return (Bank) ois.readObject();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("bank.Bank: " + nazwa + "\n");
        for (Konto k : konta) sb.append(k).append("\n");
        return sb.toString();
    }
}
