import java.io.Serializable;

public class Lokata extends Konto implements Oprocentowalny, Serializable {
    private static long numeracjaLokaty = 500;
    private Okres okres;
    private static final double[] oprocentowania = {1.5, 1.2, 1.0};

    public Lokata(Wlasciciel wlasciciel, Okres okres) {
        super(wlasciciel);
        this.okres = okres;
        try {
            java.lang.reflect.Field field = Konto.class.getDeclaredField("numerKonta");
            field.setAccessible(true);
            field.setLong(this, ++numeracjaLokaty);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void oprocentuj() {
        int index = okres.ordinal();
        saldo += saldo * (oprocentowania[index] / 100.0) / 12.0; // kapitalizacja miesięczna
    }

    @Override
    public String toString() { return super.toString() + ", Lokata: " + okres; }
}
