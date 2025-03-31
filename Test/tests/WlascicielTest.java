package tests;

import bank.Wlasciciel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WlascicielTest {

    @Test
    void testKonstruktor() {
        Wlasciciel w = new Wlasciciel("nazwa", "ulica", "00-000", "miasto");
        assertEquals("nazwa", w.getNazwa());
    }

    @Test
    void testSetNazwaWyjatek() {
        Wlasciciel w = new Wlasciciel("nazwa", "ulica", "00-000", "miasto");
        assertThrows(IllegalArgumentException.class, () -> w.setNazwa("a"));
    }

    @Test
    void testSetKodWyjatek() {
        Wlasciciel w = new Wlasciciel("nazwa", "ulica", "00-000", "miasto");
        assertThrows(IllegalArgumentException.class, () -> w.setKod("11-222a"));
    }

    @Test
    void testSetKodPoprawny() {
        Wlasciciel w = new Wlasciciel("nazwa", "ulica", "00-000", "miasto");
        w.setKod("11-111");
        assertEquals("11-111", w.getKod());
    }
}

