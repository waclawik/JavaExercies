import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import bank.*;

public class Okno extends JFrame {
    private JComboBox<Long> cbNrKonta;
    private JTextField tfWlaciciel;
    private JTextField tfSaldo;
    private JPanel pOkno;
    private JComboBox<String> cbOperacja;
    private JTextField tfKwota;
    private JButton wykonajButton;

    private static Bank bank;

    public Okno() {
        this.setContentPane(pOkno);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();

        // 1. Inicjalizacja banku
        try {
            bank = Bank.odczytaj("bank.ser");
        } catch (Exception e) {
            bank = new Bank("Mój Bank");
            bank.utworzKonto(new Konto(new Wlasciciel("Jan Kowalski", "Lipowa 1", "00-001", "Warszawa")));
            bank.utworzKonto(new Konto(new Wlasciciel("Anna Nowak", "Kwiatowa 5", "00-002", "Kraków")));
        }

        // 2. Wypełnianie comboboxów
        for (Konto k : bank.getKonta()) {
            cbNrKonta.addItem(k.getNumerKonta());
        }

        cbOperacja.addItem("wpłata");
        cbOperacja.addItem("wypłata");

        // 3. Obsługa wyboru konta
        cbNrKonta.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    long nr = (long) cbNrKonta.getSelectedItem();
                    Konto konto = bank.podajKonto(nr);
                    tfWlaciciel.setText(konto.getWlasciciel().getNazwa());
                    tfSaldo.setText(String.format("%.2f", konto.getSaldo()));
                }
            }
        });

        // 4. Obsługa przycisku wykonaj
        wykonajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long nr = (long) cbNrKonta.getSelectedItem();
                    Konto konto = bank.podajKonto(nr);
                    double kwota = Double.parseDouble(tfKwota.getText());
                    String operacja = (String) cbOperacja.getSelectedItem();

                    if ("wpłata".equals(operacja)) {
                        konto.wplac(kwota);
                    } else if ("wypłata".equals(operacja)) {
                        konto.wyplac(kwota);
                    }

                    tfSaldo.setText(String.format("%.2f", konto.getSaldo()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Błąd: " + ex.getMessage());
                }
            }
        });

        // 5. Zapis stanu banku przy zamknięciu
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    bank.zapisz("bank.ser");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        Okno okno = new Okno();
        okno.setVisible(true);
    }
}
