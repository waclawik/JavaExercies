import java.util.*;

public class Bank {
    private String nazwa;
    private List<Konto> konta;

    public Bank(String nazwa) {
        this.nazwa = nazwa;
        this.konta = new ArrayList<>();
    }

    public void utworzKonto(Konto k) {
        konta.add(k);
    }

    public void usunKonto(long nrKonta) {
        konta.removeIf(k -> k.getNumerKonta() == nrKonta);
    }

    public Konto podajKonto(long nrKonta) {
        for (Konto k : konta) {
            if (k.getNumerKonta() == nrKonta)
                return k;
        }
        return null;
    }

    public Konto podajKonto(String nazwa) {
        for (Konto k : konta) {
            if (k.getWlasciciel().getNazwa().equalsIgnoreCase(nazwa))
                return k;
        }
        return null;
    }

    public double saldoBanku() {
        double suma = 0;
        for (Konto k : konta) {
            suma += k.getSaldo();
        }
        return suma;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Bank: " + nazwa + "\n");
        for (Konto k : konta) {
            sb.append(k).append("\n");
        }
        return sb.toString();
    }
}
