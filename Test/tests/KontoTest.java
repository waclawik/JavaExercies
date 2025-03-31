package tests;

import bank.Konto;
import bank.Wlasciciel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KontoTest {

    @Test
    void testKonstruktor() {
        Konto k = new Konto(new Wlasciciel("nazwa", "ulica", "00-000", "miasto"));
        assertEquals(101, k.getNumerKonta());
        assertEquals(0, k.getSaldo());
    }

    @Test
    void testWplacWyplacMoznaWyplacic() throws Konto.BrakPieniedzyException {
        Konto k = new Konto(new Wlasciciel("nazwa", "ulica", "00-000", "miasto"));
        k.wplac(1000);
        assertEquals(1000, k.getSaldo());
        assertFalse(k.moznaWyplacic(2000));
    }

    @Test
    void testWyplacException() {
        Konto k = new Konto(new Wlasciciel("nazwa", "ulica", "00-000", "miasto"));
        assertThrows(Konto.BrakPieniedzyException.class, () -> k.wyplac(2000));
    }

    @Test
    void testPrzelew() throws Konto.BrakPieniedzyException {
        Konto k1 = new Konto(new Wlasciciel("nazwa1", "ulica1", "00-000", "miasto1"));
        Konto k2 = new Konto(new Wlasciciel("nazwa2", "ulica2", "00-000", "miasto2"));
        k1.wplac(1000);
        k1.przelej(k1, k2, 500);
        assertEquals(500, k1.getSaldo());
        assertEquals(500, k2.getSaldo());
    }
}

