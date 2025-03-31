import java.io.Serializable;

public class Konto implements Comparable<Konto>, Serializable {
    private static long biezacyNumerKonta = 100;
    private long numerKonta;
    private Wlasciciel wlasciciel;
    protected double saldo;

    public Konto(Wlasciciel wlasciciel) {
        this.wlasciciel = wlasciciel;
        this.saldo = 0.0;
        this.numerKonta = ++biezacyNumerKonta;
    }

    public long getNumerKonta() { return numerKonta; }
    public Wlasciciel getWlasciciel() { return wlasciciel; }
    public double getSaldo() { return saldo; }

    @Override
    public String toString() {
        return String.format("Konto nr: %d, Właściciel: %s, Saldo: %.2f zł", numerKonta, wlasciciel, saldo);
    }

    public class BrakPieniedzyException extends Exception {
        public BrakPieniedzyException(String msg) { super(msg); }
    }

    public void wplac(double kwota) { if (kwota > 0) saldo += kwota; }

    public boolean moznaWyplacic(double kwota) { return saldo >= kwota; }

    public void wyplac(double kwota) throws BrakPieniedzyException {
        if (moznaWyplacic(kwota)) saldo -= kwota;
        else throw new BrakPieniedzyException("Brak środków na koncie.");
    }

    public void przelej(Konto k1, Konto k2, double kwota) throws BrakPieniedzyException {
        if (k1.moznaWyplacic(kwota)) {
            k1.wyplac(kwota);
            k2.wplac(kwota);
        } else throw new BrakPieniedzyException("Nie można przelać – brak środków.");
    }

    @Override
    public int compareTo(Konto o) {
        return Double.compare(o.getSaldo(), this.saldo); // malejąco wg salda
    }
}