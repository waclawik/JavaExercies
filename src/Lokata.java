public class Lokata extends Konto {
    private static long numeracjaLokaty = 500;
    private Okres okres;

    public Lokata(Wlasciciel wlasciciel, Okres okres) {
        super(wlasciciel);
        this.okres = okres;
        // Nadpisujemy numer konta na potrzeby lokaty
        try {
            java.lang.reflect.Field field = Konto.class.getDeclaredField("numerKonta");
            field.setAccessible(true);
            field.setLong(this, ++numeracjaLokaty);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Lokata: " + okres;
    }
}
