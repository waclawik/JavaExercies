package bank;

import java.io.Serializable;

/**
 * Klasa reprezentuje konto bankowe.
 */
public class Konto implements Comparable<Konto>, Serializable {
    private static long biezacyNumerKonta = 100;
    private long numerKonta;
    private Wlasciciel wlasciciel;
    protected double saldo;

    /**
     * Konstruktor konta bankowego.
     * @param wlasciciel właściciel konta
     */
    public Konto(Wlasciciel wlasciciel) {
        this.wlasciciel = wlasciciel;
        this.saldo = 0.0;
        this.numerKonta = ++biezacyNumerKonta;
    }

    /**
     * Zwraca numer konta.
     * @return numer konta
     */
    public long getNumerKonta() { return numerKonta; }


    public Wlasciciel getWlasciciel() { return wlasciciel; }

    /**
     * Zwraca saldo konta.
     * @return saldo konta
     */
    public double getSaldo() { return saldo; }

    @Override
    public String toString() {
        return String.format("bank.Konto nr: %d, Właściciel: %s, Saldo: %.2f zł", numerKonta, wlasciciel, saldo);
    }

    public class BrakPieniedzyException extends Exception {
        public BrakPieniedzyException(String msg) { super(msg); }
    }

    /**
     * Wpłaca środki na konto.
     * @param kwota kwota do wpłaty
     */
    public void wplac(double kwota) { if (kwota > 0) saldo += kwota; }

    /**
     * Sprawdza czy można wypłacić określoną kwotę.
     * @param kwota kwota do sprawdzenia
     * @return true jeśli można wypłacić
     */
    public boolean moznaWyplacic(double kwota) { return saldo >= kwota; }

    /**
     * Wypłaca środki z konta.
     * @param kwota kwota do wypłaty
     * @throws BrakPieniedzyException gdy brak środków
     */
    public void wyplac(double kwota) throws BrakPieniedzyException {
        if (moznaWyplacic(kwota)) saldo -= kwota;
        else throw new BrakPieniedzyException("Brak środków na koncie.");
    }

    /**
     * Przelewa środki między kontami.
     * @param k1 konto źródłowe
     * @param k2 konto docelowe
     * @param kwota kwota do przelewu
     * @throws BrakPieniedzyException gdy brak środków
     */
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