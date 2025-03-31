package bank;
import java.util.regex.*;

/**
 * Klasa reprezentuje właściciela konta bankowego
 */
public class Wlasciciel {
    private String nazwa;
    private String ulica;
    private String kod;
    private String miejscowosc;

    /**
     * Konstruktor właściciela konta.
     * @param nazwa nazwa właściciela
     * @param ulica ulica
     * @param kod kod pocztowy w formacie XX-XXX
     * @param miejscowosc miejscowość
     */
    public Wlasciciel(String nazwa, String ulica, String kod, String miejscowosc) {
        setNazwa(nazwa);
        setUlica(ulica);
        setKod(kod);
        setMiejscowosc(miejscowosc);
    }

    /**
     * Zwraca nazwę właściciela.
     * @return nazwa
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia nazwę właściciela.
     * @param nazwa nowa nazwa
     * @throws IllegalArgumentException jeśli nazwa ma mniej niż 3 znaki
     */
    public void setNazwa(String nazwa) {
        if (nazwa == null || nazwa.length() < 3)
            throw new IllegalArgumentException("Nazwa musi mieć co najmniej 3 znaki");
        this.nazwa = nazwa;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        if (ulica == null || ulica.length() < 3)
            throw new IllegalArgumentException("Ulica musi mieć co najmniej 3 znaki");
        this.ulica = ulica;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        if (!kod.matches("\\d{2}-\\d{3}"))
            throw new IllegalArgumentException("Kod musi być w formacie 00-000");
        this.kod = kod;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        if (miejscowosc == null || miejscowosc.length() < 3)
            throw new IllegalArgumentException("Miejscowość musi mieć co najmniej 3 znaki");
        this.miejscowosc = miejscowosc;
    }

    @Override
    public String toString() {
        return nazwa;
    }
}
